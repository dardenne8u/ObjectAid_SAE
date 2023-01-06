package com.example.objectaid_sae.vue.fabriqueFleches;

import com.example.objectaid_sae.model.Fleche;
import com.example.objectaid_sae.vue.VueFleche;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;

public class FabriqueVueFlecheUtilisation implements FabriqueVueFleche{

    Fleche fleche;
    FabriquePoly4pts fbpoly;


    public FabriqueVueFlecheUtilisation(Fleche f){
        fleche = f;
        fbpoly = new FabriquePoly4pts();
    }
    @Override
    public VueFleche fabriquer() {
        return faireFleche(fleche.getDepart().getX(), fleche.getDepart().getY(), fleche.getArrivee().getX(), fleche.getArrivee().getY());
    }

    private VueFleche faireFleche(double x1, double y1, double x2, double y2){
        double len = Math.sqrt(Math.pow((x1-x2),2)+Math.pow((y1-y2),2));
        double inclinaison = Math.atan2(y2 - y1, x2 - x1)* 180/Math.PI+90;
        VueFleche res = new VueFleche();
        Polygon poly;
        poly = fbpoly.fabriquer();
        res.getChildren().add(poly);
        FabriqueLignePleine fbLigne = new FabriqueLignePleine(0,0,0,len);
        Line l = fbLigne.fabriquer();
        res.getChildren().add(l);
        Rotate r = new Rotate();
        r.setPivotX(0);
        r.setPivotY(0);
        r.setAngle(inclinaison);
        (res.getChildren().get(0)).setRotate(45);
        (res.getChildren().get(0)).setLayoutY(10);
        res.setLayoutY(y2);
        res.setLayoutX(y1);
        res.getTransforms().add(r);
        return res;
    }
}
