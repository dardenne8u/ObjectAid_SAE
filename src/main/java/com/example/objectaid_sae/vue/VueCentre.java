package com.example.objectaid_sae.vue;

import com.example.objectaid_sae.controleur.ControleurCentreClique;
import javafx.scene.layout.Pane;

public class VueCentre extends Pane {

    public VueCentre() {
        setOnMouseClicked(new ControleurCentreClique(this));
    }

    public void supprimerMenusTemp() {
        getChildren().removeIf(n -> n.getClass() == VueCheckClass.class || n.getClass() == VueMenuTemporaire.class || n.getClass() == VueCreation.class || n.getClass() == VueAffichageGlobal.class);
        VueMenuTemporaire.VUE_TEMP.setCacher(true);
        VueMenuTemporaire.VUE_TEMP.notifier(null);
    }

    public void supprimerFleches(){
        getChildren().removeIf(n -> n.getClass() == VueFleche.class);
    }
}
