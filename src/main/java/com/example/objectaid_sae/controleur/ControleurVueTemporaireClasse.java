package com.example.objectaid_sae.controleur;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

public class ControleurVueTemporaireClasse implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent evt) {

        if (evt.getSource().getClass() == Button.class) {
            Button src = (Button) evt.getSource();
        } else {
            CheckBox src = (CheckBox) evt.getSource();
        }

    }
}
