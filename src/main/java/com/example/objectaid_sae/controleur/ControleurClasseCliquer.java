package com.example.objectaid_sae.controleur;

import com.example.objectaid_sae.model.Model;
import com.example.objectaid_sae.observateur.Sujet;
import com.example.objectaid_sae.vue.VueClasse;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.ResourceBundle;

public class ControleurClasseCliquer implements EventHandler<MouseEvent> {

    Sujet mod;

    public ControleurClasseCliquer (Sujet s){
        this.mod = s;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        VueClasse vue = (VueClasse) mouseEvent.getSource();
        System.out.println("Vue cliquée !");
        System.out.println("Source : " +vue);
    }
}
