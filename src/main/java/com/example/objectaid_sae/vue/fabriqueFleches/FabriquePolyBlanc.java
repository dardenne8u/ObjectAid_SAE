package com.example.objectaid_sae.vue.fabriqueFleches;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;

public class FabriquePolyBlanc implements FabriquePolygone{

    private double xc1;
    private double yc1;
    private double xc2;
    private double yc2;

    public FabriquePolyBlanc(double xc1, double yc1, double xc2, double yc2){
        this.xc1 = xc1;
        this.yc1 = yc1;
        this.xc2 = xc2;
        this.yc2 = yc2;
    }


    @Override
    public Polygon fabriquer() {
        Polygon poly = new Polygon();
        poly.getPoints().addAll(new Double[]{
                0.0, 0.0,
                (Math.cos(Math.acos(xc2/29600)+45)*20), (((Math.sin(Math.asin(yc2/29600)+45)*20))),
                ((Math.cos(Math.acos(xc2/29600)-45)*20)), ((Math.sin(Math.asin(yc2/29600)-45)*20)),

        });
        poly.setFill(Paint.valueOf("white"));
        poly.setStroke(Paint.valueOf("black"));
        return poly;
    }
}
