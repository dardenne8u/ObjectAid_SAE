package com.example.objectaid_sae.vue.fabriqueFleches;

import com.example.objectaid_sae.model.Fleche;
import com.example.objectaid_sae.vue.VueCentre;
import com.example.objectaid_sae.vue.VueClasse;
import com.example.objectaid_sae.vue.VueFleche;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;

public class FabriqueVueFlecheExtends extends FabriqueVueFleche{

        public Polygon genererPointe(){
        Polygon poly = new Polygon(0.0, 0.0, 0.0, 20.0, 20.0, 0.0);
        poly.setFill(Paint.valueOf("white"));
        poly.setStroke(Paint.valueOf("black"));
        return poly;
    }

    public Line genererLigne(double len){
        return  new Line(0, 0, 0, len);
    }
    public FabriqueVueFlecheExtends(Fleche f, VueCentre centre){
        super(f,centre);
        System.out.println("extend");
    }
    @Override
    public VueFleche fabriquer() {
        return faireFleche(fleche.getDepart().getX(), fleche.getDepart().getY(), fleche.getArrivee().getX(), fleche.getArrivee().getY());
    }
}
