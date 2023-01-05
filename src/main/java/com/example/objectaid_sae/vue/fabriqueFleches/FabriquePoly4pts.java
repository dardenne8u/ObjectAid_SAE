package com.example.objectaid_sae.vue.fabriqueFleches;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;

public class FabriquePoly4pts implements FabriquePolygone{
    @Override
    public Polygon fabriquer() {
        Polygon poly = new Polygon();

        double[] c1 = {0.0, 0.0};
        double[] c2 = {0.0, 20.0};
        double[] c4 = {20.0, 0.0};
        double xc3 = c4[0]/10;
        double yc3 = c2[1]/10;
        double[] c3 = {xc3, yc3};

        poly.getPoints().addAll(new Double[]{
                c1[0], c1[1],
                c2[0], c2[1],
                c3[0], c3[1],
                c4[0], c4[1],
        });
        poly.setFill(Paint.valueOf("black"));
        poly.setStroke(Paint.valueOf("black"));
        return poly;
    }
}
