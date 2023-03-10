package com.example.objectaid_sae.controleur;

import com.example.objectaid_sae.vue.VueCentre;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class ControleurCentreClique implements EventHandler<MouseEvent> {

    VueCentre vue;

    public ControleurCentreClique(VueCentre vueC){
        vue = vueC;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        if(mouseEvent.getButton() == MouseButton.SECONDARY) return;
        vue.supprimerMenusTemp();
    }
}
