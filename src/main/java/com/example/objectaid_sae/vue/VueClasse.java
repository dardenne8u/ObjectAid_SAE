package com.example.objectaid_sae.vue;

import com.example.objectaid_sae.model.Classe;
import com.example.objectaid_sae.observateur.Observateur;
import com.example.objectaid_sae.observateur.Sujet;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class VueClasse extends VBox implements Observateur {
    @Override
    public void notifier(Sujet s) {
        Classe classe = (Classe) s;
        this.getChildren().clear();
        this.setWidth(300);
        this.setStyle("-fx-background-color:#D3D3D3");
        this.setAlignment(Pos.CENTER);


        // PREMIERE PARTIE : TITRE
        Label titre = new Label("Classe");
        Rectangle separation = new Rectangle(150, 2, Color.BLACK);

         this.getChildren().addAll(titre, separation);

         //DEUXIEME PARTIE : ATTRIBUTS

    }
}
