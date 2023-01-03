package com.example.objectaid_sae.controleur;

import com.example.objectaid_sae.model.Classe;
import com.example.objectaid_sae.vue.VueCreation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class ControleurVueTemporaireClasse implements EventHandler<ActionEvent> {

    Classe classe;

    public ControleurVueTemporaireClasse(Classe classe) {
        this.classe = classe;
    }

    @Override
    public void handle(ActionEvent evt) {

        if (evt.getSource().getClass() == Button.class) {
            Button src = (Button) evt.getSource();
            if (src.getText().contains("Valider")) {
                String nom = ((TextField) (src.getParent().getChildrenUnmodifiable().get(1))).getText();
                if (((Label) (src.getParent().getChildrenUnmodifiable().get(0))).getText().contains("methode"))
                    classe.getMethodes().get(Classe.DECLARED).add(nom);
                else classe.getAttributs().get(Classe.DECLARED).add(nom);
                ((Pane) (src.getParent().getParent())).getChildren().remove(src.getParent());
            }else if (src.getText().equals("Afficher")) {}
            else{
                VueCreation v;
                if (src.getText().contains("methode")) v = new VueCreation("methode", this);
                else v = new VueCreation("attribut", this);
                System.out.println("vue cree");
                ((Pane) (src.getParent().getParent())).getChildren().add(v);
                v.setLayoutX(src.getParent().getLayoutX() + src.getWidth());
                v.setLayoutY(src.getParent().getLayoutY());
            }


        } else {
            CheckBox src = (CheckBox) evt.getSource();
            switch (src.getText()) {
                case "attributs declarés":
                    classe.setAfficheAttributsDeclare(src.isSelected());
                    break;
                case "attributs hérités":
                    classe.setAfficheAttributsHerite(src.isSelected());
                    break;
                case "méthodes déclarées":
                    classe.setAfficheMethodeDeclare(src.isSelected());
                    break;
                case "méthodes hérités":
                    classe.setAfficheMethodeHerite(src.isSelected());
                    break;
            }
        }
        classe.notifierObservateurs();
    }
}
