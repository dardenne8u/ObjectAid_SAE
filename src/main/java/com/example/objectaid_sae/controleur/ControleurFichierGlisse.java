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


public class ControleurFichierGlisse implements EventHandler<MouseEvent> {

    private MouseEvent mouseEvent;
    private Pane centre;
    private TreeItem<HBox> treeActuel;
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


    public void afficherUneClasse(String abs, CheckBox cb, Model mod){
        try {
            String nom = abs.substring(abs.lastIndexOf("\\")+1, abs.lastIndexOf("."));
            BufferedReader r = new BufferedReader(new FileReader(abs));
            String line = r.readLine();
            while (!line.contains("package ")) line = r.readLine();
            line = line.split("package ")[1].split(";")[0];
            Classe c = new Analyseur(line + "."+ nom).analyseClasse();
            VueClasse vue = new VueClasse(c);
            cb.setSelected(true);
            centre.getChildren().add(vue);
            // definition de la position x
            double mouseX = mouseEvent.getSceneX() - centre.getLayoutX();
            mouseX = Math.max(vue.getWidth()/2,mouseX); // minimum
            mouseX = Math.min((centre.getWidth()+ centre.getLayoutX() - vue.getWidth()/2), mouseX); // maximum
            // definition de la position y
            double mouseY = mouseEvent.getSceneY() - centre.getLayoutY();
            mouseY = Math.min((centre.getLayoutY() + centre.getHeight() - vue.getHeight()/2 - 53), mouseY); // maximum
            mouseY = Math.max(100,mouseY -100); // minimum
            c.setX(mouseX);
            c.setY(mouseY);
            vue.notifier(c);
            Fleche.creerFleches(c, (VueCentre) centre);
            mod.addClasse(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
            System.out.println(h.getParent().getClass().getSimpleName());
            CheckBox cb = (CheckBox) h.getChildren().get(0);
            if (cb.isSelected()) return;
            Label l = (Label) h.getChildren().get(2);
            String abs = l.getText();
            if (abs.contains(".java")) {
                afficherUneClasse(abs, cb, mod);
            }else{
                afficherDossier(abs, cb, mod);
            }
        }
    }
}