package com.example.objectaid_sae.vue;

import com.example.objectaid_sae.controleur.ControleurButtonNewClass;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;

public class VueHaut extends GridPane {

    public VueHaut(){
        setGridLinesVisible(true);
        setMinHeight(50);
        setMaxWidth(Double.MAX_VALUE);
        Button prj = new Button("projet");
        GridPane.setHgrow(prj, Priority.ALWAYS);
        GridPane.setVgrow(prj, Priority.ALWAYS);
        prj.setMaxWidth(Double.MAX_VALUE);
        prj.setMaxHeight(Double.MAX_VALUE);
        Button aff = new Button("affichage");
        GridPane.setHgrow(aff, Priority.ALWAYS);
        GridPane.setVgrow(aff, Priority.ALWAYS);
        aff.setMaxWidth(Double.MAX_VALUE);
        aff.setMaxHeight(Double.MAX_VALUE);
        Button general = new Button("general");
        GridPane.setHgrow(general, Priority.ALWAYS);
        GridPane.setVgrow(general, Priority.ALWAYS);
        general.setMaxWidth(Double.MAX_VALUE);
        general.setMaxHeight(Double.MAX_VALUE);
        Button genAll = new Button("tout generer");
        genAll.setMaxWidth(Double.MAX_VALUE);
        genAll.setMaxHeight(Double.MAX_VALUE);
        GridPane.setHgrow(genAll, Priority.ALWAYS);
        GridPane.setVgrow(genAll, Priority.ALWAYS);
        Button nouvelleClasse = new Button("Nouvelle classe");
        nouvelleClasse.setOnAction(new ControleurButtonNewClass());
        nouvelleClasse.setMaxWidth(Double.MAX_VALUE);
        nouvelleClasse.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(nouvelleClasse, Priority.ALWAYS);
        GridPane.setHgrow(nouvelleClasse,Priority.ALWAYS);

        addRow(0,prj,aff,general,genAll,nouvelleClasse);

    }
}
