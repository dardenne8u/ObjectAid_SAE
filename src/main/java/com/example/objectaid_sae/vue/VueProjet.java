package com.example.objectaid_sae.vue;

import com.example.objectaid_sae.controleur.ControleurButtonProjet;
import com.example.objectaid_sae.observateur.Observateur;
import com.example.objectaid_sae.observateur.Sujet;
import javafx.beans.Observable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class VueProjet  extends GridPane implements Observateur{

    Button saveImg, saveUml, giveTitle, cancel, valider;
    TextField pathImg, pathUml, nameTitle;
    Label err;


    public VueProjet(ControleurButtonProjet cont){

        ControleurButtonProjet controleurButtonProjet = new ControleurButtonProjet(this);

        setPadding(new Insets(10, 10, 10, 10));
        setVgap(8);
        setHgap(10);
        setMaxWidth(Double.MAX_VALUE);



        // Création des éléments de la vue
        saveImg = new Button("Enregistrer au format image");
        saveImg.setMaxWidth(Double.MAX_VALUE);
        saveUml = new Button("Enregistrer au format UML");
        saveUml.setMaxWidth(Double.MAX_VALUE);
        giveTitle = new Button("Donner un titre au projet");
        giveTitle.setMaxWidth(Double.MAX_VALUE);
        cancel = new Button("Annuler");
        valider = new Button("Valider");
        pathImg = new TextField();
        pathImg.setMaxWidth(250);
        pathImg.setPromptText("Chemin du dossier");
        pathImg.setVisible(false);
        pathUml = new TextField();
        pathUml.setMaxWidth(250);
        pathUml.setPromptText("Chemin du dossier");
        pathUml.setVisible(false);
        nameTitle = new TextField();
        nameTitle.setMaxWidth(250);
        nameTitle.setPromptText("Nom");

        err = new Label("Dossier Introuvable");
        err.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 14));
        err.setVisible(false);


        cancel.setOnAction(cont);
        valider.setOnAction(cont);


        saveImg.setOnAction(event -> {if(pathImg.isVisible()){
            pathImg.setVisible(false);
            pathImg.clear();
        }else{
            pathImg.clear();
            pathImg.setVisible(true);
        }});

        saveUml.setOnAction(event -> {if(pathUml.isVisible()){
            pathUml.setVisible(false);
            pathUml.clear();
        }else{
            pathUml.clear();
            pathUml.setVisible(true);
        }});




        // Ajout des éléments au GridPane
        add(saveImg, 0, 0);
        add(pathImg, 1, 0, 4, 1);
        add(saveUml, 0, 1);
        add(pathUml, 1, 1, 4, 1);
        add(giveTitle, 0, 2);
        add(nameTitle, 1, 2, 4, 1);

        add(cancel, 0, 3);
        add(valider, 5, 3);
        add(err,1, 3 );




    }


    @Override
    public void notifier(Sujet s) {

    }


    public TextField getPathImg() {
        return pathImg;
    }

    public TextField getPathUml() {
        return pathUml;
    }
}
