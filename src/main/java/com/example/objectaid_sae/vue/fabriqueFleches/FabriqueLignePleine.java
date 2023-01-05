package com.example.objectaid_sae.vue.fabriqueFleches;

import javafx.scene.shape.Line;

public class FabriqueLignePleine implements FabriqueLigne{

    private double xc1;
    private double yc1;
    private double xc2;
    private double yc2;

    public FabriqueLignePleine(double xc1, double yc1, double xc2, double yc2){
        this.xc1 = xc1;
        this.yc1 = yc1;
        this.xc2 = xc2;
        this.yc2 = yc2;
    }


    @Override
    public Line fabriquer() {
        return new Line(xc1, yc1, xc2, yc2);
    }
}
