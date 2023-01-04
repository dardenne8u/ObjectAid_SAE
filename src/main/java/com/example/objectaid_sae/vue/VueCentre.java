package com.example.objectaid_sae.vue;

import com.example.objectaid_sae.controleur.ControleurCentreClique;
import javafx.scene.layout.Pane;

public class VueCentre extends Pane {

    public VueCentre() {
        setOnMouseClicked(new ControleurCentreClique(this));
    }

    public void supprimerMenusTemp() {
        getChildren().removeIf(n -> n.getClass() == VueCheckClass.class || n.getClass() == VueMenuTemporaire.class || n.getClass() == VueCreation.class);
        VueMenuTemporaire.VUE_TEMP.setCacher(true);
    }
}
