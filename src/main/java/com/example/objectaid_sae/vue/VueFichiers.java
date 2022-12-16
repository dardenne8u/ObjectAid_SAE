package com.example.objectaid_sae.vue;

import com.example.objectaid_sae.observateur.Observateur;
import com.example.objectaid_sae.observateur.Sujet;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.io.File;

public class VueFichiers extends BorderPane implements Observateur {



    public VueFichiers(String path){
        File f = new File(path);
        TreeView<HBox> arbre = new TreeView<>(tree(f));
        this.setLeft(arbre);




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


public TreeItem<HBox> creerItem(String text){
        HBox box = new HBox();
    CheckBox check = new CheckBox();
    check.setDisable(true);
    Text t = new Text(text);

    box.getChildren().addAll(check, t);

       return new TreeItem<HBox>(box);
}






    @Override
    public void notifier(Sujet s) {

    }
}
