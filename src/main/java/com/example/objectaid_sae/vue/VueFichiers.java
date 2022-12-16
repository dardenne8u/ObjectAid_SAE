package com.example.objectaid_sae.vue;

import com.example.objectaid_sae.HelloApplication;
import com.example.objectaid_sae.model.Fichier;
import com.example.objectaid_sae.observateur.Observateur;
import com.example.objectaid_sae.observateur.Sujet;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.File;

public class VueFichiers extends Pane implements Observateur {

    private TreeView<HBox> arbre;

    public VueFichiers(String path) {
        File f = new File(path);
        arbre = new TreeView<>(tree(f));
         this.getChildren().add(arbre);


    }

    public TreeItem<HBox> tree(File file) {
        TreeItem<HBox> res = null;
        if (file.exists()) {
            res = creerItem(file.getName());
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    if (files[i].isDirectory()) {
                        res.getChildren().add(tree(files[i]));
                    } else {
                        res.getChildren().add(creerItem(files[i].getName()));
                    }
                }
            }

        }
        return res;
    }


    public TreeItem<HBox> creerItem(String text) {
        HBox box = new HBox();
        box.setSpacing(5);
        CheckBox check = new CheckBox();
        check.setDisable(true);
        Text t = new Text(text);

        box.getChildren().addAll(check, t);

        TreeItem<HBox> res = new TreeItem<HBox>(box);
        res.setExpanded(true);
        return res;
    }


    @Override
    public void notifier(Sujet s) {
        Fichier f = (Fichier)s;
        arbre = new TreeView<>(tree(new File(f.getPathDepart())));
    }
}
