package com.example.objectaid_sae.vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class VueAffichageGlobal extends GridPane {

    public VueAffichageGlobal(EventHandler<ActionEvent> handler){
        Button mAttDec = new Button("masquer attributs déclarés");
        Button mAttHer = new Button("masquer attributs hérités");
        Button mMtDec = new Button("masquer méthodes déclarées");
        Button mMetHer = new Button("masquer méthodes héritées");
        Button aAttDec = new Button("afficher attributs déclarés");
        Button aAttHer = new Button("afficher attributs hérités");
        Button aMtDec = new Button("afficher méthodes déclarées");
        Button aMetHer = new Button("afficher méthodes héritées");
        addColumn(0,mAttDec,mAttHer,mMetHer,mMtDec);
        addColumn(1,aAttDec,aAttHer,aMetHer,aMtDec);
        for(Node n : getChildren()){
            ((Button)n).setOnAction(handler);
            GridPane.setVgrow(n, Priority.ALWAYS);
            ((Button) n).setMaxWidth(Double.MAX_VALUE);
        }
    }
}
