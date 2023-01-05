package com.example.objectaid_sae.controleur;

import com.example.objectaid_sae.model.Classe;
import com.example.objectaid_sae.model.Model;
import com.example.objectaid_sae.vue.VueAffichageGlobal;
import com.example.objectaid_sae.vue.VueCentre;
import com.example.objectaid_sae.vue.VueCheckClass;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class ControleurAffichageGlobal implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent actionEvent) {
        Model mod = Model.getModel();
        Button src = (Button) actionEvent.getSource();
        String txt = src.getText();
        if(txt.contains("affichage")) {
            VueCentre centre = (VueCentre) (((BorderPane) (((Button) actionEvent.getSource()).getParent()).getParent())).getCenter();
            VueAffichageGlobal vaff = new VueAffichageGlobal(this);
            centre.getChildren().add(vaff);
            vaff.setLayoutY(0);
            double maxX = ((((Button) actionEvent.getSource()).getParent()).getScene().getWidth() / 5 - ((Pane) (((BorderPane) (((Button) actionEvent.getSource()).getParent()).getParent())).getLeft()).getWidth());
            vaff.setTranslateX(Math.max(0, maxX));
        } else{
            boolean value = txt.contains("afficher");
            if(txt.contains("attributs")) if(txt.contains("hérit")) for (Classe c : mod.getClasses()) c.setAfficheAttributsHerite(value);
                else for (Classe c : mod.getClasses()) c.setAfficheAttributsDeclare(value);
            else if (txt.contains("hérit")) for (Classe c : mod.getClasses()) c.setAfficheMethodeHerite(value);
            else for (Classe c : mod.getClasses()) c.setAfficheMethodeDeclare(value);

        }
    }
}
