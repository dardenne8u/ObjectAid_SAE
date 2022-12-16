package com.example.objectaid_sae.vue;

import com.example.objectaid_sae.observateur.Observateur;
import com.example.objectaid_sae.observateur.Sujet;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;

import java.io.File;

public class VueFichiers extends BorderPane implements Observateur {



    public VueFichiers(String path){
        File f = new File(path);
        TreeView<String> arbre = new TreeView<>(tree(f));
        this.setLeft(arbre);




    }

    public TreeItem<String> tree(File file) {
        TreeItem<String> res = null;
            if (file.exists()) {
                res = new TreeItem<>(file.getName());
                    if (file.isDirectory()) {
                        File[] files = file.listFiles();
                        for (int i = 0; i < files.length; i++) {
                            if (files[i].isDirectory()) {
                                res.getChildren().add(tree(files[i]));
                            } else {
                                res.getChildren().add(new TreeItem<>(files[i].getName()));
                            }
                        }
                    }

                }
        return res;
            }









    @Override
    public void notifier(Sujet s) {

    }
}
