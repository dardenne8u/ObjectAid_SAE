package com.example.objectaid_sae.vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class VueCreation extends VBox {

    public VueCreation(String type, EventHandler<ActionEvent> handler){
        Label l = new Label("entrez le nom de votre " + type);
        TextField txt = new TextField();
        Button valider = new Button("Valider");
        getChildren().addAll(l,txt,valider);
        valider.setOnAction(handler);
    }
}
