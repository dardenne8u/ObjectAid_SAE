package com.example.objectaid_sae.vue;

import com.example.objectaid_sae.model.Analyseur;
import com.example.objectaid_sae.model.Classe;
import com.example.objectaid_sae.observateur.Observateur;
import com.example.objectaid_sae.observateur.Sujet;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class VueSousMenuClassExt extends VBox implements Observateur {
    @Override
    public void notifier(Sujet s) {
        final Classe classe = (Classe) s;
        final Pane centre = (Pane) this.getParent();
        this.getChildren().clear();
        if(classe.getPackageExternes().size() > 0) {
            for(String packageExt : classe.getPackageExternes()) {
                Button lab = new Button(packageExt);
                lab.setOnAction(event -> {
                    Classe c;
                    try {
                        c = new Analyseur(packageExt).analyseClasse();
                        VueClasse vue = new VueClasse(c);
                        centre.getChildren().add(vue);
                    } catch (ClassNotFoundException e) {
                        System.out.println("Class not found : " + packageExt);
                    }
                });
                this.getChildren().add(lab);
            }
        } else {
            this.getChildren().add(new Label("Aucun classe"));
        }

    }
}
