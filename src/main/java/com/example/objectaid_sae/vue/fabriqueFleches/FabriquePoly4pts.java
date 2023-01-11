package com.example.objectaid_sae.vue.fabriqueFleches;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;

public class FabriquePoly4pts implements FabriquePolygone{
    @Override
    public Polygon fabriquer() {
        Polygon poly = new Polygon(0.0, 0.0, 0.0, 20.0, 2.0, 2.0, 20.0, 0.0);
        poly.setFill(Paint.valueOf("black"));
        poly.setStroke(Paint.valueOf("black"));
        return poly;
    }
}
