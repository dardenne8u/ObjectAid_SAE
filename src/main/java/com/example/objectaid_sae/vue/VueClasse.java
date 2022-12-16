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
import java.util.List;
import java.util.Map;

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


         this.getChildren().addAll(titre, this.separer());

         //DEUXIEME PARTIE : ATTRIBUTS
        Map<Integer, List<String>> attributsMap = classe.getAttributs();
        this.afficherContenant(attributsMap, false, Classe.DECLARED );
        this.getChildren().add(this.separer());

        //TROISIEME PARTIE : METHODES
        Map<Integer, List<String>> methodesMap = classe.getMethodes();
        this.afficherContenant(methodesMap, true, Classe.DECLARED);
        this.getChildren().add(this.separer());

    }

    public Rectangle separer(){
        return new Rectangle(150, 2, Color.BLACK);
    }

    public void afficherContenant(Map<Integer, List<String>> map, boolean afficherHeritage, int typeAttribut){
       if (map != null) {
           int i = 1;
           if (typeAttribut == Classe.DECLARED) i = 1;
           else if (typeAttribut == Classe.HERITED) i = 2;

           List<String> element = map.get(i);
           for (String elemC : element) {
               Label elem = new Label(elemC);
               this.getChildren().add(elem);
           }
           if (afficherHeritage) {
               afficherContenant(map, false, Classe.HERITED);
           }

       }
    }
}
