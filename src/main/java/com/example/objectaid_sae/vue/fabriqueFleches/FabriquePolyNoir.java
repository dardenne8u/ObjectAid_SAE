package com.example.objectaid_sae.vue.fabriqueFleches;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;

public class FabriquePolyNoir implements FabriquePolygone{
    @Override
    public Polygon fabriquer() {
        Polygon poly = new Polygon();
        poly.getPoints().addAll(new Double[]{
                0.0, 0.0,
                0.0, 20.0,
                20.0, 0.0,

        });
        poly.setFill(Paint.valueOf("black"));
        poly.setStroke(Paint.valueOf("black"));
        return poly;
    }
}