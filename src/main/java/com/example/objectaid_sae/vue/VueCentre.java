package com.example.objectaid_sae.vue;

import com.example.objectaid_sae.controleur.ControleurCentreClique;
import com.example.objectaid_sae.controleur.ControleurZoom;
import com.example.objectaid_sae.vue.menuContextuel.*;
import javafx.scene.Group;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class VueCentre extends AnchorPane {

    public Group content;

    public VueCentre() {
        super();
        setOnMouseClicked(new ControleurCentreClique(this));
        this.content = new Group();
        this.content.setStyle("-fx-border-color: black");

        getChildren().add(content);
        addEventHandler(ScrollEvent.SCROLL, new ControleurZoom(this.content, this));
    }

    public void supprimerMenusTemp() {
        Class[] menusTemps = {VueCheckClass.class, VueMenuTemporaire.class, VueCreation.class, VueAffichageGlobal.class, VueSousMenuClassExt.class, VueDependences.class};
        ArrayList<Class> menus = new ArrayList<>(List.of(menusTemps));
        getChildren().removeIf(n -> menus.contains(n.getClass()));
        VueMenuTemporaire.VUE_TEMP.setCacher(true);
        VueMenuTemporaire.VUE_TEMP.notifier(null);
    }

    public void supprimerFleches(){
        getChildren().removeIf(n -> n.getClass() == VueFleche.class);
    }
}
