package com.example.objectaid_sae.controleur;

import com.example.objectaid_sae.model.Classe;
import com.example.objectaid_sae.model.Model;
import com.example.objectaid_sae.vue.VueCentre;
import com.example.objectaid_sae.vue.VueClasse;
import com.example.objectaid_sae.vue.VueNewClasse;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ControleurButtonNewClass implements EventHandler<ActionEvent> {

    private VueCentre stock;
    private void openSecondaryWindow() {
            // Création de la deuxième fenêtre
            Stage secondaryStage = new Stage();
            secondaryStage.setTitle("Nouvelle Classe");
            //button.setOnAction(event -> secondaryStage.close());
            VueNewClasse root = new VueNewClasse(this);
            Scene scene = new Scene(root, 350, 270);
            secondaryStage.setScene(scene);
            secondaryStage.setResizable(false);
            secondaryStage.show();
        }

    @Override
    public void handle(ActionEvent evt) {
        Model mod = Model.getModel();
        if (evt.getSource().getClass() == Button.class) {
            Button src = (Button) evt.getSource();
            if (src.getText().equals("Nouvelle classe")) {
                Parent temp = src.getParent();
                while(!(temp instanceof BorderPane)) {
                    temp = temp.getParent();
                    System.out.println(temp.getClass());
                }
                BorderPane borderPane = (BorderPane) temp;
                stock = (VueCentre) borderPane.getCenter();
                System.out.println(stock);
                openSecondaryWindow();
            }
            if (src.getText().equals("Annuler")) {
                ((Stage) src.getScene().getWindow()).close();
            }
            if (src.getText().equals("Valider")) {
                Classe newClasse= new Classe();
                mod.addClasse(newClasse);
                newClasse.addAttribut(1, "test");
                VueClasse vC = new VueClasse(newClasse);
                VueNewClasse vnc = (VueNewClasse) ((Button) evt.getSource()).getParent();
                newClasse.setType(((TextField)(src.getParent().getChildrenUnmodifiable().get(1))).getText());
                stock.getChildren().add(vC);
                stock = null;
                newClasse.notifierObservateurs();
                ((Stage) src.getScene().getWindow()).close();
                // TODO : c'est pas fini
            }
        }


    }
}
