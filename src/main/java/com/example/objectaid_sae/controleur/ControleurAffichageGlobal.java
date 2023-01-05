package com.example.objectaid_sae.controleur;

import com.example.objectaid_sae.model.Classe;
import com.example.objectaid_sae.model.Model;
import com.example.objectaid_sae.vue.VueCentre;
import com.example.objectaid_sae.vue.VueCheckClass;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class ControleurAffichageGlobal implements EventHandler<ActionEvent> {

    private Model mod;

    public ControleurAffichageGlobal(Model m) {
        mod = m;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (actionEvent.getSource().getClass() == Button.class) {
            VueCentre centre = (VueCentre) (((BorderPane) (((Button) actionEvent.getSource()).getParent()).getParent())).getCenter();
            VueCheckClass vCheck = new VueCheckClass(this);
            centre.getChildren().add(vCheck);
            vCheck.setLayoutY(0);
            double maxX = ((((Button) actionEvent.getSource()).getParent()).getScene().getWidth() / 5 - ((Pane) (((BorderPane) (((Button) actionEvent.getSource()).getParent()).getParent())).getLeft()).getWidth());
            vCheck.setTranslateX(Math.max(0, maxX));
        } else {
            CheckBox src = (CheckBox) actionEvent.getSource();
            switch (src.getText()) {
                case "attributs declarés":
                    for(Classe classe : mod.getClasses()) {
                        classe.setAfficheAttributsDeclare(src.isSelected());
                    }
                    break;
                case "attributs hérités":
                    for(Classe classe : mod.getClasses()) {
                        classe.setAfficheAttributsHerite(src.isSelected());
                    }
                    break;
                case "méthodes déclarées":
                    for(Classe classe : mod.getClasses()) {
                        classe.setAfficheMethodeDeclare(src.isSelected());
                    }
                    break;
                case "méthodes hérités":
                    for(Classe classe : mod.getClasses()){
                    classe.setAfficheMethodeHerite(src.isSelected());
                }
                    break;
            }

        }
    }
}
