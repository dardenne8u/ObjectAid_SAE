package com.example.objectaid_sae.vue.fabriqueFleches;

import javafx.scene.shape.Line;

public class FabriqueLignePoint implements FabriqueLigne{
    @Override
    public Line fabriquer() {
        Line line = new Line();
        line.getStrokeDashArray().addAll(10.0);
        return line;
    }
}
