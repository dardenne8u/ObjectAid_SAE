package com.example.objectaid_sae.controleur;

import com.example.objectaid_sae.model.Analyseur;
import com.example.objectaid_sae.model.Classe;
import com.example.objectaid_sae.model.Fleche;
import com.example.objectaid_sae.model.Model;
import com.example.objectaid_sae.vue.VueCentre;
import com.example.objectaid_sae.vue.VueClasse;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Controleur qui permet de recuperer une classe presente dans l'arborescence des fichiers
 * et de la glisser dans le graphe pour la faire apparaitre
 */
public class ControleurFichierGlisse implements EventHandler<MouseEvent> {

    /**
     * L'evenement du controleur
     */
    private MouseEvent mouseEvent;

    /**
     * le pane ou apparaitra la classe selectionnee
     */
    private Pane centre;

    /**
     * methode intervenant lorsqu'on veut afficher tout un dossier dans le graphe
     * @param abs le nom du dossier
     * @param cb la checkbox du dossier, qui sera selectionnee lorsque tout sera affiche
     * @param mod le modele de l'application
     */
    public void afficherDossier(String abs, CheckBox cb, Model mod) {
        File file = new File(abs);
        if (file.isDirectory()) {
            File[] allDFile = file.listFiles();
            for (File value : allDFile) {
                if (value.isDirectory()) {
                    afficherDossier(value.getAbsolutePath(), cb, mod);
                } else {
                    afficherUneClasse(value.getAbsolutePath(), cb, mod);
                }
            }
        }
    }


    /**
     * methode permettant d'afficher une classe dans le graphe
     * @param abs la signature de la classe
     * @param cb la checkbox de la classe, qui sera cochée quand la classe aura sa vue
     * @param mod le modele
     */
    public void afficherUneClasse(String abs, CheckBox cb, Model mod) {
        try {
            String nom = abs.substring(abs.lastIndexOf("\\") + 1, abs.lastIndexOf("."));
            BufferedReader r = new BufferedReader(new FileReader(abs));
            String line = r.readLine();
            while (!line.contains("package ")) line = r.readLine();
            line = line.split("package ")[1].split(";")[0];
            Classe c = new Analyseur(line + "." + nom).analyseClasse();
            if(Model.getModel().getClasses().contains(c)) return;
            // definition de la position x
            double mouseX = mouseEvent.getSceneX() - centre.getLayoutX();

            // definition de la position y
            double mouseY = mouseEvent.getSceneY() - centre.getLayoutY();
            mouseY = Math.max(0, mouseY);
            c.setX(mouseX);
            c.setY(mouseY);
            VueClasse vue = new VueClasse(c);
            vue.setMaxHeight(centre.getHeight());
            cb.setSelected(true);
            centre.getChildren().add(vue);
            Fleche.creerFleches(c, (VueCentre) centre);
            mod.addClasse(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * methode permettant de recuperer les branches filles du TreeItem passee en parametre
     * @param branche le treeItem dont on veut recuperer les branches
     */
    private void recupChildrenBranch(TreeItem<HBox> branche) {
        if(branche!=null){
        for (TreeItem<HBox> other : branche.getChildren()) {
            final HBox box = other.getValue();
            final CheckBox check = (CheckBox) box.getChildrenUnmodifiable().get(0);
            check.setSelected(true);
            if (!other.isLeaf()) recupChildrenBranch(other);
        }
        }
    }

    /**
     * methode qui permet de creer et retourner une TreeItem a partir d'un chemin de
     * dosser.
     * @param abPath le chemin du fichier dont on veut trouver le TreeItem
     * @param tree le treeItem
     * @return un treeItem du dossier
     */
    private TreeItem<HBox> trouverTIWithPath(String abPath, TreeItem<HBox> tree) {
         String verif = ((Label) tree.getValue().getChildrenUnmodifiable().get(2)).getText();
         if(verif.equals(abPath))return tree;

        for (TreeItem<HBox> branch : tree.getChildren()) {
            if (!branch.isLeaf()) {
                String temp = ((Label) branch.getValue().getChildrenUnmodifiable().get(2)).getText();
                if (temp.equals(abPath)) {
                    return branch;
                }
                TreeItem<HBox> foundTree = trouverTIWithPath(abPath, branch);
                if (foundTree != null) {
                    return foundTree;
                }
            }
        }
        return null;
    }

    /**
     * methode du demarrage du controleur. Permet d'afficher dans le graphe ce qui est
     * selectionne (dossier ou classe)
     * @param mouseEvent l'evenement
     */
    @Override
    public void handle(MouseEvent mouseEvent) {
        Model mod = Model.getModel();
        Node source = (Node) mouseEvent.getSource();
        if (mouseEvent.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {
            while (source.getClass() != TreeView.class) source = source.getParent();
        }
        BorderPane bp = (BorderPane) source.getParent().getParent();
        Node center = bp.getCenter();
        centre = (Pane) center;
        this.mouseEvent = mouseEvent;
        if (center.contains(mouseEvent.getSceneX(), mouseEvent.getSceneY()) && !source.contains(mouseEvent.getSceneX(), mouseEvent.getSceneY())) {
            HBox h = (HBox) mouseEvent.getSource();
            CheckBox cb = (CheckBox) h.getChildren().get(0);
            if (cb.isSelected()) return;
            Label l = (Label) h.getChildren().get(2);
            String abs = l.getText();
            if (abs.contains(".java")) {
                afficherUneClasse(abs, cb, mod);
            } else {
                afficherDossier(abs, cb, mod);
                TreeView t = (TreeView) h.getParent().getParent().getParent().getParent().getParent();
                String path = ((Label) h.getChildren().get(2)).getText();
                recupChildrenBranch(trouverTIWithPath(path, t.getRoot()));
            }
        }
    }
}