package com.example.objectaid_sae.controleur;

import com.example.objectaid_sae.model.Classe;
import com.example.objectaid_sae.model.Fleche;
import com.example.objectaid_sae.model.Model;
import com.example.objectaid_sae.vue.VueAffichageGlobal;
import com.example.objectaid_sae.vue.VueCentre;
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
        if(txt.contains("Affichage")) {
            VueCentre centre = (VueCentre) (((BorderPane) (((Button) actionEvent.getSource()).getParent()).getParent())).getCenter();
            VueAffichageGlobal vaff = new VueAffichageGlobal(this);
            centre.supprimerMenusTemp();
            centre.getChildren().add(vaff);
            vaff.setLayoutY(0);
            double maxX = ((((Button) actionEvent.getSource()).getParent()).getScene().getWidth() / 5 - ((Pane) (((BorderPane) (((Button) actionEvent.getSource()).getParent()).getParent())).getLeft()).getWidth());
            vaff.setTranslateX(Math.max(0, maxX));
        } else{
            VueCentre centre = (VueCentre) src.getParent().getParent();
            boolean value = txt.contains("afficher");
            if(txt.contains("attributs")) if(txt.contains("hérit")) for (Classe c : mod.getClasses()) c.setAfficheAttributsHerite(value);
                else for (Classe c : mod.getClasses()) c.setAfficheAttributsDeclare(value);
            else if (txt.contains("hérit")) for (Classe c : mod.getClasses()) c.setAfficheMethodeHerite(value);
            else if (txt.contains("dépen")) for (Fleche f : mod.getFleches()) {
                f.setCache(!value);
                if (value){
                    FabriqueVueFleche fab;
                    switch (f.getType()) {
                        case "-->":
                            fab = new FabriqueVueFlecheUtilisation(f,centre);
                            break;
                        case "--|>":
                            fab = new FabriqueVueFlecheExtends(f,centre);
                            break;
                        default:
                            fab = new FabriqueVueFlecheImplement(f,centre);
                            break;
                    }
                    centre.getChildren().add(fab.fabriquer());
                }
                else centre.supprimerFleches();
            }
            else for (Classe c : mod.getClasses()) c.setAfficheMethodeDeclare(value);


            String[] split = txt.split(" ");
            if(split[0].equals("afficher")){
                split[0] = "masquer";
            } else {
                split[0] = "afficher";
            }
            String nouveauText = "";
            for (int i = 0; i<split.length; i++){
                nouveauText += split[i]+ " ";
            }
            src.setText(nouveauText);
        }
    }
}
