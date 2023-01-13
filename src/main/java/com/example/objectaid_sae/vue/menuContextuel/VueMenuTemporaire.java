package com.example.objectaid_sae.vue.menuContextuel;

import com.example.objectaid_sae.controleur.ControleurVueTemporaireClasse;
import com.example.objectaid_sae.model.Classe;
import com.example.objectaid_sae.observateur.Observateur;
import com.example.objectaid_sae.observateur.Sujet;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class VueMenuTemporaire extends VBox implements Observateur {

    /**
     * VUE_TEMP
     */
    public static VueMenuTemporaire VUE_TEMP = new VueMenuTemporaire();

    /**
     * Permet de cacher la vue
     */
    public boolean cacher;

    /**
     * Creer la vue
     */
    public VueMenuTemporaire(){
        this.cacher=true;
    }


    /**
     * Permet de notifier
     * la vue en fonction
     *
     * @param s
     */
    @Override
    public void notifier(Sujet s) {
        if (!this.cacher){
            ControleurVueTemporaireClasse cont = new ControleurVueTemporaireClasse((Classe) s);
            this.setWidth(200);
            this.setStyle("-fx-background-color:#F2F3F4");
            this.setAlignment(Pos.CENTER);

            Button Afficher = new Button("Afficher");
            Afficher.setOnAction(cont);
            Afficher.setMinWidth(this.getWidth());
            this.getChildren().addAll(Afficher);

            Button classExternes = new Button("Classes externes");
            classExternes.setMinWidth(this.getWidth());
            this.getChildren().addAll(classExternes);
            classExternes.setOnAction(cont);

            Button nouvDependance = new Button("Nouvelle dépendance");
            nouvDependance.setMinWidth(this.getWidth());
            this.getChildren().addAll(nouvDependance);
            nouvDependance.setOnAction(cont);

            Button genSquelette = new Button("Générer squelette");
            genSquelette.setMinWidth(this.getWidth());
            this.getChildren().addAll(genSquelette);
            genSquelette.setOnAction(cont);


            // creation d'attribut ou methode

            Button newMethode = new Button("Ajouter une methode");
            newMethode.setMinWidth(this.getWidth());
            newMethode.setOnAction(cont);
            Button newAttribut = new Button("ajouter un attribut");
            newAttribut.setOnAction(cont);
            newAttribut.setMinWidth(this.getWidth());
            getChildren().addAll( newAttribut, newMethode);


            // supression de classe

            Button supr = new Button("Supprimer la classe");
            supr.setMinWidth(this.getWidth());
            supr.setOnAction(cont);
            getChildren().add(supr);
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
