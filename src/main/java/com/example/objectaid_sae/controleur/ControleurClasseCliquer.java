package com.example.objectaid_sae.controleur;

import com.example.objectaid_sae.model.Classe;
import com.example.objectaid_sae.vue.VueClasse;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ControleurClasseCliquer implements EventHandler<MouseEvent> {

    Classe mod;

    public ControleurClasseCliquer (Classe s){
        this.mod = s;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        VueClasse vue = (VueClasse) mouseEvent.getSource();
        mod.setAfficheAttributsDeclare(true);
        mod.setAfficheAttributsHerite(true);
        mod.notifierObservateurs();

        System.out.println("Vue cliquée !");
        System.out.println("Source : " +vue);
    }
}
