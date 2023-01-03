package com.example.objectaid_sae.vue;

import com.example.objectaid_sae.controleur.ControleurVueTemporaireClasse;
import com.example.objectaid_sae.model.Classe;
import com.example.objectaid_sae.observateur.Observateur;
import com.example.objectaid_sae.observateur.Sujet;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class VueMenuTemporaire extends VBox implements Observateur {

    public static VueMenuTemporaire VUE_TEMP = new VueMenuTemporaire();

    public boolean cacher;

    public VueMenuTemporaire(){
        this.cacher=true;
            }

    public void init(){
        VBox sousMenu= new VBox();
        CheckBox attdec = new CheckBox("attributs declarés");
        CheckBox atther = new CheckBox("attributs hérités");
        CheckBox metdec = new CheckBox("méthodes déclarées");
        CheckBox mether = new CheckBox("méthodes héritées");

        sousMenu.getChildren().addAll(attdec,atther,metdec,mether );

    }


    @Override
    public void notifier(Sujet s) {
        if (!this.cacher){
            ControleurVueTemporaireClasse cont = new ControleurVueTemporaireClasse((Classe) s);
//            Classe classe = (Classe) s;
//            this.getChildren().clear();
            this.setWidth(200);
            this.setStyle("-fx-background-color:#F2F3F4");
            this.setAlignment(Pos.CENTER);

            Button Afficher = new Button("Afficher");
            Afficher.setMinWidth(this.getWidth());
            this.getChildren().addAll(Afficher);


            // creation d'attribut ou methode

            Button newMethode = new Button("Ajouter une methode");
            newMethode.setMinWidth(this.getWidth());
            newMethode.setOnAction(cont);
            Button newAttribut = new Button("ajouter un attribut");
            newAttribut.setOnAction(cont);
            newAttribut.setMinWidth(this.getWidth());
            getChildren().addAll( newAttribut, newMethode);




        } else {
            this.getChildren().clear();
        }
    }

    public boolean isCacher(){
        return this.cacher;
    }

    public void setCacher(boolean cach){
        this.cacher = cach;
    }
}
