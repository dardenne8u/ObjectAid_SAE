package com.example.objectaid_sae.vue;

import com.example.objectaid_sae.model.Analyseur;
import com.example.objectaid_sae.model.Classe;
import com.example.objectaid_sae.model.Fleche;
import com.example.objectaid_sae.model.Model;
import com.example.objectaid_sae.observateur.Observateur;
import com.example.objectaid_sae.observateur.Sujet;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class VueSousMenuClassExt extends VBox implements Observateur {
    public static final VueSousMenuClassExt classeExt = new VueSousMenuClassExt();
    @Override
    public void notifier(Sujet s) {
        final Classe classe = (Classe) s;
        final Pane centre = (Pane) this.getParent();
        this.setWidth(100);
        this.getChildren().clear();
        if(classe.getPackageExternes().size() > 0) {
            for(String packageExt : classe.getPackageExternes()) {
                Button lab = new Button(packageExt);
                lab.setMinWidth(100);
                for(Classe cTemp : Model.getModel().getClasses()) {
                    String name = packageExt.substring(packageExt.lastIndexOf(".")+1);
                    System.out.println(cTemp.getType() + "  " + name);
                    if(cTemp.getType().contains(name)) {
                        lab.setDisable(true);
                    }
                }
                lab.setOnAction(event -> {
                    Classe c;
                    try {
                        c = new Analyseur(packageExt).analyseClasse();
                        VueClasse vue = new VueClasse(c);
                        c.setAfficheAttributsDeclare(false);
                        c.setAfficheMethodeDeclare(false);
                        c.setAfficheAttributsHerite(false);
                        c.setAfficheConstructeur(false);
                        c.setAfficheMethodeHerite(false);
                        centre.getChildren().add(vue);
                        Fleche.creerFleches(c, (VueCentre) centre);
                        Model.getModel().addClasse(c);
                        lab.setDisable(true);
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
