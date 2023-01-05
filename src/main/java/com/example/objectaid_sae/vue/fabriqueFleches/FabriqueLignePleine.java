package com.example.objectaid_sae.vue.fabriqueFleches;

import javafx.scene.shape.Line;

public class FabriqueLignePleine implements FabriqueLigne{



    @Override
    public Line fabriquer() {
        return new Line();
    }
}
