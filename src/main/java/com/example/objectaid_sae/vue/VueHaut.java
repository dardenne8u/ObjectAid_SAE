package com.example.objectaid_sae.vue;

import com.example.objectaid_sae.controleur.ControleurAffichageGlobal;
import com.example.objectaid_sae.controleur.ControleurButtonNewClass;
import com.example.objectaid_sae.controleur.ControleurButtonProjet;
import com.example.objectaid_sae.model.Model;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;

/**
 * Vue qui affiche la partie superieure de l'application.
 */
public class VueHaut extends GridPane {

    /**
     * Constructeur de la VueHaut, pour le menu de l'application
     */
    public VueHaut(){
       // setGridLinesVisible(true);
        setMinHeight(50);
        setMaxWidth(Double.MAX_VALUE);
        this.setStyle("-fx-background-color: #c0600d");

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setHalignment(HPos.CENTER);
        column1.setPercentWidth(65);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setHalignment(HPos.CENTER);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setHalignment(HPos.CENTER);
        ColumnConstraints column4 = new ColumnConstraints();
        column4.setHalignment(HPos.CENTER);
        getColumnConstraints().addAll(column1, column2, column3, column4); // each get 50% of width
        setHgap(10); setVgap(10); setPadding(new Insets(0, 10, 10, 0));


        Pane paneTitre = new Pane();
        Image titre_image = new Image(getClass().getResource("/img/Titre1.png").toExternalForm());
        ImageView imageView = new ImageView(titre_image);
        imageView.setFitHeight(120);
        imageView.setFitWidth(450);

        paneTitre.getChildren().add(imageView);

        add(imageView, 0,0, 1, 3);


        ArrayList<Button> buttons = new ArrayList<>();


        Button nouvelleClasse = new Button("Nouvelle classe");
        nouvelleClasse.setOnAction(new ControleurButtonNewClass());
        nouvelleClasse.setMaxWidth(150);
        GridPane.setVgrow(nouvelleClasse, Priority.ALWAYS);
        GridPane.setHgrow(nouvelleClasse,Priority.ALWAYS);
        setConstraints(nouvelleClasse, 3, 1);

        Button prj = new Button("Projet");
        prj.setOnAction(new ControleurButtonProjet(null));
        prj.setFont(Font.font(null, FontWeight.BOLD, 16));
        GridPane.setHgrow(prj, Priority.ALWAYS);
        GridPane.setVgrow(prj, Priority.ALWAYS);

        setConstraints(prj, 2, 1);


        Button aff = new Button("Affichage");
        aff.setFont(Font.font(null, FontWeight.BOLD, 16));
        aff.setOnAction(new ControleurAffichageGlobal());


        setConstraints(aff, 2, 2);



        Button general = new Button("Tout supprimer");
        general.setFont(Font.font(null, FontWeight.BOLD, 16));
        GridPane.setHgrow(general, Priority.ALWAYS);
        GridPane.setVgrow(general, Priority.ALWAYS);
        general.setOnAction(event -> {
            BorderPane pane = (BorderPane) ((Node)event.getSource()).getParent().getParent();
            ((VueCentre)(pane.getCenter())).getChildren().clear();
            pane.setLeft(new VueFichiers(VueFichiers.PATH));
            Model.resetModel();
        });

        setConstraints(general, 3, 2);







        buttons.add(prj); buttons.add(aff); buttons.add(general); buttons.add(nouvelleClasse);

        for (Button but : buttons){
            but.setStyle("-fx-background-color:#00727a");
            but.setEffect(new DropShadow(10.0, 1.0, 1.0, Color.valueOf("83420CFF")));
            but.setMaxWidth(nouvelleClasse.getMaxWidth());
            but.setMaxHeight(30);
            but.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
            but.setTextFill(Paint.valueOf("white"));
        }

        this.setEffect(new DropShadow());

        getChildren().addAll(prj,aff,general,nouvelleClasse);

    }
}
