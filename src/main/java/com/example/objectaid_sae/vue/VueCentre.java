package com.example.objectaid_sae.vue;

import com.example.objectaid_sae.controleur.ControleurCentreClique;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class VueCentre extends Pane {

    public VueCentre() {
        setOnMouseClicked(new ControleurCentreClique(this));
    }

    public void supprimerMenusTemp() {
        Class[] menusTemps = {VueCheckClass.class, VueMenuTemporaire.class, VueCreation.class, VueAffichageGlobal.class, VueSousMenuClassExt.class};
        ArrayList<Class> menus = new ArrayList<>(List.of(menusTemps));
        getChildren().removeIf(n -> menus.contains(n.getClass()));
        VueMenuTemporaire.VUE_TEMP.setCacher(true);
        VueMenuTemporaire.VUE_TEMP.notifier(null);
    }

    public void supprimerFleches(){
        getChildren().removeIf(n -> n.getClass() == VueFleche.class);
    }
}
