package com.example.objectaid_sae.vue;

import com.example.objectaid_sae.model.Classe;
import com.example.objectaid_sae.observateur.Observateur;
import com.example.objectaid_sae.observateur.Sujet;
import com.example.objectaid_sae.vue.fabriqueFleches.*;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

public class VueFleche extends Pane implements Observateur {

    public Classe c1;
    public Classe c2;

    public VueFleche (Sujet s1, Sujet s2){
        this.c1 = (Classe) s1;
        this.c2 = (Classe) s2;
    }

    @Override
    public void notifier(Sujet s) {
        double xc1 = c1.getX();
        double xc2 = c2.getX();
        double yc1 = c1.getY();
        double yc2 = c2.getY();

        FabriqueLigne f = new FabriqueLignePoint(xc1, yc1, xc2, yc2);
        FabriquePolygone fP = new FabriquePolyBlanc(xc1, yc1, xc2, yc2);
        Polygon fpp = fP.fabriquer();


        this.getChildren().addAll(f.fabriquer(), fpp);
        
    }
}
