package com.example.objectaid_sae.controleur;

import com.example.objectaid_sae.model.Model;
import com.example.objectaid_sae.observateur.Observateur;
import com.example.objectaid_sae.vue.VueCentre;
import com.example.objectaid_sae.vue.VueProjet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.embed.swing.SwingFXUtils;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.io.*;

public class ControleurButtonProjet implements EventHandler<ActionEvent> {

    Pane pane;
    VueProjet vueProjet;

    boolean exit = true;

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

    public void saveImg(Pane pane, String path) throws IOException {
        WritableImage image = new WritableImage((int) pane.getWidth(), (int) pane.getHeight());
        pane.snapshot(new SnapshotParameters(), image);
        // Enregistrer l'image dans un fichier
        File file = new File(path);
        ImageIO.write(SwingFXUtils.fromFXImage(image,null), "png", file);
    }


    @Override
    public void handle(ActionEvent evt) {
        if (evt.getSource().getClass() == Button.class) {
            Button src = (Button) evt.getSource();
            if (src.getText().equals("Projet")) {
                BorderPane borderPane = (BorderPane) src.getParent().getParent();
                pane = (VueCentre) borderPane.getCenter();
                openProjetWindow();
            }
            if (src.getText().equals("Annuler")){
                ((Stage) src.getScene().getWindow()).close();
            }
            if (src.getText().equals("Valider")){

                //enregistrer img
                String string_enregistrer_img = ((TextField) src.getParent().getChildrenUnmodifiable().get(1)).getText();
                //enregistrer UML
                String string_enregistrer_uml = ((TextField) src.getParent().getChildrenUnmodifiable().get(3)).getText();
                //modifier le titre
                String string_nom = ((TextField) src.getParent().getChildrenUnmodifiable().get(5)).getText();

                //verification dossier img existant :
                File file_verif_img = new File(string_enregistrer_img);
                if (file_verif_img.exists()){
                        if (string_nom != null){
                        String img_nom = string_enregistrer_img + "\\" + string_nom + ".png";
                            exit=true;
                            try{saveImg(pane, img_nom);

                    } catch (IOException e) {
                               e.printStackTrace();
                            }
                        }
                }else {
                    ((Label) src.getParent().getChildrenUnmodifiable().get(8)).setVisible(true);
                    exit = false;
                }


                //verification dossier uml existant :
                File file_verif = new File(string_enregistrer_uml);
                if (file_verif.exists()){

                    if (string_nom != null){
                        String uml_nom = string_enregistrer_uml + "\\" + string_nom;
                        exit=true;
                    try {
                        this.enregUML(uml_nom);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    }
                } else {
                    src.getParent().getChildrenUnmodifiable().get(8).setVisible(true);
                    exit=false;
                }
                    if(exit)((Stage) src.getScene().getWindow()).close();
            }






        }
    }


    public void enregUML(String path) throws IOException {
        String path_extension = path + ".puml";
        FileWriter fileWriter = new FileWriter(path_extension, false);

        Model model = Model.getModel();
        String string_uml_model = model.enregistrerUML();
        System.out.println(string_uml_model);
        fileWriter.write(string_uml_model);
        fileWriter.close();
    }
}
