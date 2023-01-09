package com.example.objectaid_sae.controleur;

import com.example.objectaid_sae.vue.VueCentre;
import com.example.objectaid_sae.vue.VueNewClasse;
import com.example.objectaid_sae.vue.VueProjet;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class ControleurButtonProjet implements EventHandler<ActionEvent> {

    Pane pane;


    private void openProjetWindow() {
        // Création de la deuxième fenêtre
        Stage projetStage = new Stage();
        projetStage.setTitle("Projet");
        //button.setOnAction(event -> secondaryStage.close());
       VueProjet root = new VueProjet(this);
        Scene scene = new Scene(root);
        projetStage.setScene(scene);
        projetStage.setResizable(false);
        projetStage.show();
    }


    @Override
    public void handle(ActionEvent evt) {
        if (evt.getSource().getClass() == Button.class) {
            Button src = (Button) evt.getSource();
            System.out.println(src.getText());
            if (src.getText().equals("Projet")) {
                /*
                BorderPane borderPane = (BorderPane) src.getParent();
                pane = (VueCentre) borderPane.getCenter();
                WritableImage image = new WritableImage((int) pane.getWidth(), (int) pane.getHeight());
                pane.snapshot(null, image);
                 */
                openProjetWindow();
            }


        }
    }
}
