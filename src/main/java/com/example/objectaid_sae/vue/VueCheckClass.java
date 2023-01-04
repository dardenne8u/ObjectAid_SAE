package com.example.objectaid_sae.vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class VueCheckClass extends VBox {

    public VueCheckClass(EventHandler<ActionEvent> handler){
        setBorder(new Border(new BorderStroke(Color.valueOf("#9E9E9E"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        CheckBox attdec = new CheckBox("attributs declarés");
        CheckBox atther = new CheckBox("attributs hérités");
        CheckBox metdec = new CheckBox("méthodes déclarées");
        CheckBox mether = new CheckBox("méthodes héritées");
        getChildren().addAll(attdec,atther,metdec,mether );
        attdec.setOnAction(handler);
        atther.setOnAction(handler);
        metdec.setOnAction(handler);
        mether.setOnAction(handler);


    }




}
