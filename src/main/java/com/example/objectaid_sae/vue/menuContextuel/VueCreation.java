package com.example.objectaid_sae.vue.menuContextuel;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class VueCreation extends VBox {

    /**
     * Permet de faire une vue creant
     * des attributs et methods
     * @param type  attributs ou methodes
     * @param handler controleur
     */
    public VueCreation(String type, EventHandler<ActionEvent> handler){
        setBorder(new Border(new BorderStroke(Color.valueOf("#9E9E9E"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        Label l = new Label("Entrez le nom de votre " + type);
        TextField txt = new TextField();
        if (type.equals("attribut")){
            txt.setPromptText("symbole nom: type");
        }else{
            txt.setPromptText("symbole nom(arg1:type1,...):typeRetour");
        }
        txt.setMinWidth(250);
        this.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        this.setWidth(252);
        Button valider = new Button("Valider");
        getChildren().addAll(l,txt,valider);
        valider.setOnAction(handler);
    }
}
