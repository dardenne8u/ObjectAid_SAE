package com.example.objectaid_sae.vue;

import com.example.objectaid_sae.controleur.ControleurCentreClique;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.ConcurrentModificationException;

public class VueCentre extends Pane {

    public VueCentre(){
        //setOnMouseClicked(new ControleurCentreClique(this));
    }

    public void supprimerMenusTemp(){
            getChildren().removeIf(n -> n.getClass() == VueCheckClass.class || n.getClass() == VueMenuTemporaire.class || n.getClass() == VueCreation.class);
    }
}
