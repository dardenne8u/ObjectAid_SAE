package com.example.objectaid_sae.vue;

import com.example.objectaid_sae.model.Classe;
import com.example.objectaid_sae.observateur.Observateur;
import com.example.objectaid_sae.observateur.Sujet;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class VueMenuTemporaire extends VBox implements Observateur {

    public boolean cacher;

    public VueMenuTemporaire(Classe classe){
        this.cacher=false;
    }

    @Override
    public void notifier(Sujet s) {
        if (this.cacher==false){
            Classe classe = (Classe) s;
            this.getChildren().clear();
            this.setWidth(200);
            this.setStyle("-fx-background-color:#F2F3F4");
            this.setAlignment(Pos.CENTER);

            Label att = new Label("Attributs :");
            Button attDeclare = new Button("attributs declarés");
            Button attHerite = new Button("attributs hérités");
            this.getChildren().addAll(att, attDeclare, attHerite);



            Label meth = new Label("Methodes :");
            Button methDeclaree = new Button("méthodes déclarées");
            Button methHeritee = new Button("méthodes héritées");

            this.getChildren().addAll(meth, methDeclaree, methHeritee);


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
