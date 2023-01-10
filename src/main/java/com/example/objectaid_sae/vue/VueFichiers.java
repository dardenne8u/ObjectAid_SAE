package com.example.objectaid_sae.vue;

import com.example.objectaid_sae.controleur.ControleurFichierGlisse;
import com.example.objectaid_sae.model.Analyseur;
import com.example.objectaid_sae.model.Fichier;
import com.example.objectaid_sae.observateur.Observateur;
import com.example.objectaid_sae.observateur.Sujet;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.io.*;
import java.util.List;

public class VueFichiers extends GridPane implements Observateur {

    private TreeView<HBox> arbre;

    public VueFichiers(String path) {
        File f = new File(path);
        if (f.exists()) {
            arbre = new TreeView<>(tree(f));
            for(File file : f.listFiles()) {
                if(file.getName().contains(".java")) {
                    try {
                        BufferedReader br = new BufferedReader(new FileReader(file));
                        String line = br.readLine();
                        if(line != null) {
                            Analyseur.packageProjet = line.substring(line.indexOf(" ")+1, line.indexOf(";"));
                        } else {
                            Analyseur.packageProjet = "";
                        }
                    } catch (IOException e) {
                        Analyseur.packageProjet = "";
                    }
                }
            }
        }

        getChildren().add(arbre);
        arbre.minHeightProperty().bind(heightProperty());

    }

    public TreeItem<HBox> tree(File file) {
        TreeItem<HBox> res = null;
        res = creerItem(file);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    res.getChildren().add(tree(files[i]));
                } else {
                    res.getChildren().add(creerItem(files[i]));
                }
            }
        }
        return res;
    }


    public TreeItem<HBox> creerItem(File f) {
        HBox box = new HBox();
        box.setSpacing(5);
        CheckBox check = new CheckBox();
        check.setDisable(true);
        Label name = new Label(f.getName());
        Label path = new Label(f.getAbsolutePath());
        path.setVisible(false);


        box.getChildren().addAll(check, name, path);
        box.setOnMouseReleased(new ControleurFichierGlisse());
        TreeItem<HBox> res = new TreeItem<HBox>(box);
        res.setExpanded(true);
        return res;
    }


    @Override
    public void notifier(Sujet s) {
        Fichier f = (Fichier) s;
        arbre = new TreeView<>(tree(new File(f.getPathDepart())));

    }
}
