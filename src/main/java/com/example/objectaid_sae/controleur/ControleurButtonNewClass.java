package com.example.objectaid_sae.controleur;

import com.example.objectaid_sae.model.Classe;
import com.example.objectaid_sae.model.Fleche;
import com.example.objectaid_sae.model.Model;
import com.example.objectaid_sae.vue.VueCentre;
import com.example.objectaid_sae.vue.VueClasse;
import com.example.objectaid_sae.vue.VueNewClasse;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ControleurButtonNewClass implements EventHandler<ActionEvent> {

    private VueCentre stock;
    private void openSecondaryWindow() {
            // Création de la deuxième fenêtre
            Stage secondaryStage = new Stage();
            secondaryStage.setTitle("Nouvelle Classe");
            //button.setOnAction(event -> secondaryStage.close());
            VueNewClasse root = new VueNewClasse(this);
            Scene scene = new Scene(root, 350, 300);
            secondaryStage.setScene(scene);
            secondaryStage.setResizable(false);
            secondaryStage.show();
        }

    @Override
    public void handle(ActionEvent evt) {
        Model mod = Model.getModel();
        if (evt.getSource().getClass() == Button.class) {
            Button src = (Button) evt.getSource();
            src.setEffect(new DropShadow(0.0, 2.0, 2.0, Color.TRANSPARENT));

            if (src.getText().equals("Nouvelle classe")) {
                Parent temp = src.getParent();
                while(!(temp instanceof BorderPane)) {
                    temp = temp.getParent();
                }
                BorderPane borderPane = (BorderPane) temp;
                stock = (VueCentre) borderPane.getCenter();
                openSecondaryWindow();
            }
            if (src.getText().equals("Annuler")) {
                ((Stage) src.getScene().getWindow()).close();
            }
            if (src.getText().equals("Valider")) {
                Classe newClasse= new Classe();
                if (((CheckBox) src.getParent().getChildrenUnmodifiable().get(13)).isSelected()){
                    newClasse.setPackageName(((TextField) src.getParent().getChildrenUnmodifiable().get(14)).getText());
                }
                VueNewClasse vnc = (VueNewClasse) ((Button) evt.getSource()).getParent();
                if(!vnc.matchName()) return;
                newClasse.setType(vnc.getTypeClass());
                if(((CheckBox) src.getParent().getChildrenUnmodifiable().get(4)).isSelected()){

                    newClasse.addDependencies((((TextField) src.getParent().getChildrenUnmodifiable().get(1)).getText())+" ..|> " +  (((TextField) src.getParent().getChildrenUnmodifiable().get(5)).getText()));
                }

                if(((CheckBox) src.getParent().getChildrenUnmodifiable().get(6)).isSelected()){

                    newClasse.addDependencies((((TextField) src.getParent().getChildrenUnmodifiable().get(1)).getText())+" --|> " +  (((TextField) src.getParent().getChildrenUnmodifiable().get(7)).getText()));
                }
                VueClasse vC = new VueClasse(newClasse);
                vC.setMaxHeight(stock.getHeight());
                mod.addClasse(newClasse);
                stock.getChildren().add(vC);
                Fleche.creerFleches(newClasse, stock);
                stock = null;
                newClasse.notifierObservateurs();
                ((Stage) src.getScene().getWindow()).close();





            }

        }



    }
}
