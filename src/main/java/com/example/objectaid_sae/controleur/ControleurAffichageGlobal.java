package com.example.objectaid_sae.controleur;

import com.example.objectaid_sae.model.Classe;
import com.example.objectaid_sae.model.Fleche;
import com.example.objectaid_sae.model.Model;
import com.example.objectaid_sae.vue.VueAffichageGlobal;
import com.example.objectaid_sae.vue.VueCentre;
import com.example.objectaid_sae.vue.VueFichiers;
import com.example.objectaid_sae.vue.VueHaut;
import com.example.objectaid_sae.vue.fabriqueFleches.FabriqueVueFleche;
import com.example.objectaid_sae.vue.fabriqueFleches.FabriqueVueFlecheExtends;
import com.example.objectaid_sae.vue.fabriqueFleches.FabriqueVueFlecheImplement;
import com.example.objectaid_sae.vue.fabriqueFleches.FabriqueVueFlecheUtilisation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class ControleurAffichageGlobal implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent actionEvent) {
        Model mod = Model.getModel();
        Button src = (Button) actionEvent.getSource();
        String txt = src.getText();
        if (txt.contains("Affichage")) {
            VueCentre centre = (VueCentre) (((BorderPane) (((Button) actionEvent.getSource()).getParent()).getParent())).getCenter();
            VueHaut haut = (VueHaut) (((BorderPane) (((Button) actionEvent.getSource()).getParent()).getParent())).getTop();
            VueFichiers gauche = (VueFichiers) (((BorderPane) (((Button) actionEvent.getSource()).getParent()).getParent())).getLeft();

            Button afficher = (Button) haut.getChildren().get(2);
            VueAffichageGlobal vaff = new VueAffichageGlobal(this);
            centre.supprimerMenusTemp();
            centre.getChildren().add(vaff);
            vaff.setLayoutY(0);
            vaff.setLayoutX(afficher.getLayoutX() - gauche.getWidth());
        } else {
            VueCentre centre = (VueCentre) src.getParent().getParent();
            boolean value = txt.contains("afficher");
            if (txt.contains("attributs"))
                if (txt.contains("hérit")) for (Classe c : mod.getClasses()) c.setAfficheAttributsHerite(value);
                else for (Classe c : mod.getClasses()) c.setAfficheAttributsDeclare(value);
            else if (txt.contains("hérit")) for (Classe c : mod.getClasses()) c.setAfficheMethodeHerite(value);
            else if (txt.contains("dépen")) {
                for (Classe c : mod.getClasses()) c.setAfficheDependances(value);
                centre.supprimerFleches();
                for (Fleche f : Model.getModel().getFleches()) {
                    if (!f.isCache()) centre.getChildren().add(f.getFabrique().fabriquer());
                }
            }
            else for (Classe c : mod.getClasses()) c.setAfficheMethodeDeclare(value);


            String[] split = txt.split(" ");
            if (split[0].equals("afficher")) {
                split[0] = "masquer";
            } else {
                split[0] = "afficher";
            }
            String nouveauText = "";
            for (int i = 0; i < split.length; i++) {
                nouveauText += split[i] + " ";
            }
            src.setText(nouveauText);
        }
    }
}
