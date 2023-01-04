package com.example.objectaid_sae.vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

public class VueCheckClass extends VBox {

    public VueCheckClass(EventHandler<ActionEvent> handler){
        setVisible(true);
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
