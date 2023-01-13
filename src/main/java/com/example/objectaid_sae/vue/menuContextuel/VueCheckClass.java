package com.example.objectaid_sae.vue.menuContextuel;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class VueCheckClass extends VBox {

    /**
     * VueCheckClass permettant
     * d'afficher ou non des
     * attributs et méthodes
     * @param handler
     */
    public VueCheckClass(EventHandler<ActionEvent> handler){
        setBorder(new Border(new BorderStroke(Color.valueOf("#9E9E9E"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        CheckBox attdec = new CheckBox("attributs declarés");
        CheckBox atther = new CheckBox("attributs hérités");
        CheckBox metdec = new CheckBox("méthodes déclarées");
        CheckBox mether = new CheckBox("méthodes héritées");
        CheckBox constructeur = new CheckBox("constructeur");
        CheckBox dependances = new CheckBox("dépendances");
        this.setWidth(130);
        this.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        getChildren().addAll(attdec,atther,metdec,mether, constructeur, dependances);
        dependances.setOnAction(handler);
        constructeur.setOnAction(handler);
        attdec.setOnAction(handler);
        atther.setOnAction(handler);
        metdec.setOnAction(handler);
        mether.setOnAction(handler);


    }




}
