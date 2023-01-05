package com.example.objectaid_sae.vue;

import com.example.objectaid_sae.controleur.ControleurButtonNewClass;
import com.example.objectaid_sae.controleur.ControleurAffichageGlobal;
import com.example.objectaid_sae.model.Model;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class VueHaut extends GridPane {

    public VueHaut(){
        setGridLinesVisible(true);
        setMinHeight(50);
        setMaxWidth(Double.MAX_VALUE);
        Button prj = new Button("Projet");
        prj.setFont(Font.font(null, FontWeight.BOLD, 16));
        GridPane.setHgrow(prj, Priority.ALWAYS);
        GridPane.setVgrow(prj, Priority.ALWAYS);
        prj.setMaxWidth(Double.MAX_VALUE);
        prj.setMaxHeight(Double.MAX_VALUE);
        Button aff = new Button("Affichage");
        aff.setFont(Font.font(null, FontWeight.BOLD, 16));
        aff.setOnAction(new ControleurAffichageGlobal());
        GridPane.setHgrow(aff, Priority.ALWAYS);
        GridPane.setVgrow(aff, Priority.ALWAYS);
        aff.setMaxWidth(Double.MAX_VALUE);
        aff.setMaxHeight(Double.MAX_VALUE);
        Button general = new Button("Général");
        general.setFont(Font.font(null, FontWeight.BOLD, 16));
        GridPane.setHgrow(general, Priority.ALWAYS);
        GridPane.setVgrow(general, Priority.ALWAYS);
        general.setMaxWidth(Double.MAX_VALUE);
        general.setMaxHeight(Double.MAX_VALUE);
        Button genAll = new Button("Tout Générer");
        genAll.setFont(Font.font(null, FontWeight.BOLD, 16));
        genAll.setMaxWidth(Double.MAX_VALUE);
        genAll.setMaxHeight(Double.MAX_VALUE);
        GridPane.setHgrow(genAll, Priority.ALWAYS);
        GridPane.setVgrow(genAll, Priority.ALWAYS);
        Button nouvelleClasse = new Button("Nouvelle classe");
        nouvelleClasse.setFont(Font.font(null, FontWeight.BOLD, 16));
        nouvelleClasse.setOnAction(new ControleurButtonNewClass());
        nouvelleClasse.setMaxWidth(Double.MAX_VALUE);
        nouvelleClasse.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(nouvelleClasse, Priority.ALWAYS);
        GridPane.setHgrow(nouvelleClasse,Priority.ALWAYS);

        addRow(0,prj,aff,general,genAll,nouvelleClasse);

    }
}
