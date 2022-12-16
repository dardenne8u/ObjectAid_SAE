package com.example.objectaid_sae.vue;

import com.example.objectaid_sae.model.Classe;
import com.example.objectaid_sae.observateur.Observateur;
import com.example.objectaid_sae.observateur.Sujet;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class VueClasse extends VBox implements Observateur {
    @Override
    public void notifier(Sujet s) {
        Classe classe = (Classe) s;
        this.getChildren().clear();

        Rectangle part1 = new Rectangle();
        part1.setFill(Color.web("#D3D3D3"));
        part1.setHeight(50);

    }
}
