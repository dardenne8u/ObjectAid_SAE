package com.example.objectaid_sae.vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class VueCreation extends HBox {

    public VueCreation(String type, EventHandler<ActionEvent> handler){
        setVisible(true);
        Label l = new Label("entrez le nom de votre " + type);
        TextField txt = new TextField();
        Button valider = new Button("Valider");
        getChildren().addAll(l,txt,valider);
        valider.setOnAction(handler);
    }
}
