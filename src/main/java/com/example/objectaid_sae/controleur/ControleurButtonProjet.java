package com.example.objectaid_sae.controleur;

import com.example.objectaid_sae.model.Model;
import com.example.objectaid_sae.observateur.Observateur;
import com.example.objectaid_sae.vue.VueCentre;
import com.example.objectaid_sae.vue.VueNewClasse;
import com.example.objectaid_sae.vue.VueProjet;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.*;

public class ControleurButtonProjet implements EventHandler<ActionEvent> {

    Pane pane;
    VueProjet vueProjet;

    public ControleurButtonProjet(VueProjet vueProjet){
        this.vueProjet = (VueProjet) vueProjet;
    }


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
            if (src.getText().equals("Valider")){

                //enregistrer img
                String string_enregistrer_img = ((TextField) src.getParent().getChildrenUnmodifiable().get(1)).getText();
                //enregistrer UML
                String string_enregistrer_uml = ((TextField) src.getParent().getChildrenUnmodifiable().get(3)).getText();
                //modifier le titre
                String string_changer_nom = ((TextField) src.getParent().getChildrenUnmodifiable().get(5)).getText();

                //verification dossier existant :
                File file_verif = new File(string_enregistrer_img);
                if (file_verif.exists()){
                    System.out.println("existant !");
                }else {
                    System.out.println("inexistant");

                }


                if (string_enregistrer_img != null){
                    //this.saveImg(this.pane, string_enregistrer_img);
                }
                if (string_enregistrer_uml != null){
                   /* try {
                        this.enregUML(string_enregistrer_uml);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*/
                }
            }



        }
    }


    public void enregUML(String path) throws IOException {
        FileWriter fileWriter = new FileWriter(path, true);

        Model model = Model.getModel();
        String string_uml_model = model.enregistrerUML();
        System.out.println(string_uml_model);
        fileWriter.write(string_uml_model);
        fileWriter.close();
    }
}
