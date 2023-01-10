package com.example.objectaid_sae.vue;

import com.example.objectaid_sae.controleur.ControleurAffichageGlobal;
import com.example.objectaid_sae.controleur.ControleurButtonNewClass;
import com.example.objectaid_sae.controleur.ControleurButtonProjet;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;

public class VueHaut extends GridPane {

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
        getColumnConstraints().addAll(column1, column2, column3, column4); // each get 50% of width*/
        setHgap(10); setVgap(10); setPadding(new Insets(0, 0, 10, 0));


        Pane paneTitre = new Pane();
        Image titre_image = new Image(getClass().getResource("/img/Titre1.png").toExternalForm());
        ImageView imageView = new ImageView(titre_image);
        imageView.setFitHeight(120);
        imageView.setFitWidth(450);

        paneTitre.getChildren().add(imageView);

        add(imageView, 0,0, 1, 3);


        ArrayList<Button> buttons = new ArrayList<>();


        Button nouvelleClasse = new Button("Nouvelle classe");
        nouvelleClasse.setFont(Font.font(null, FontWeight.BOLD, 16));
        nouvelleClasse.setOnAction(new ControleurButtonNewClass());
        nouvelleClasse.setMaxWidth(150);
        nouvelleClasse.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(nouvelleClasse, Priority.ALWAYS);
        GridPane.setHgrow(nouvelleClasse,Priority.ALWAYS);
        setConstraints(nouvelleClasse, 3, 1);

        Button prj = new Button("Projet");
        prj.setOnAction(new ControleurButtonProjet(null));
        prj.setFont(Font.font(null, FontWeight.BOLD, 16));
        GridPane.setHgrow(prj, Priority.ALWAYS);
        GridPane.setVgrow(prj, Priority.ALWAYS);
        prj.setMaxWidth(nouvelleClasse.getMaxWidth());
        prj.setMaxHeight(Double.MAX_VALUE);
        setConstraints(prj, 2, 1);


        Button aff = new Button("Affichage");
        aff.setFont(Font.font(null, FontWeight.BOLD, 16));
        aff.setOnAction(new ControleurAffichageGlobal());
        GridPane.setHgrow(aff, Priority.ALWAYS);
        GridPane.setVgrow(aff, Priority.ALWAYS);
        aff.setMaxWidth(nouvelleClasse.getMaxWidth());
        aff.setMaxHeight(Double.MAX_VALUE);
        setConstraints(aff, 2, 2);



        Button general = new Button("Général");
        general.setFont(Font.font(null, FontWeight.BOLD, 16));
        GridPane.setHgrow(general, Priority.ALWAYS);
        GridPane.setVgrow(general, Priority.ALWAYS);
        general.setMaxWidth(nouvelleClasse.getMaxWidth());
        general.setMaxHeight(Double.MAX_VALUE);
        setConstraints(general, 3, 2);







        buttons.add(prj); buttons.add(aff); buttons.add(general); buttons.add(nouvelleClasse);

        for (Button but : buttons){
            but.setStyle("-fx-background-color:#00727a; -fx-border-color: #024438");
        }

        getChildren().addAll(prj,aff,general,nouvelleClasse);

    }
}
