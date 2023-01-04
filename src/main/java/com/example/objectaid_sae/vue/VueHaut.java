package com.example.objectaid_sae.vue;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class VueHaut extends GridPane {

    public VueHaut(){
        Button prj = new Button("projet");
        Button aff = new Button("affichage");
        Button general = new Button("general");
        Button genAll = new Button("tout generer");
        Button nouvelleClasse = new Button("nouvelle classe");

        addRow(0,prj,aff,general,genAll,nouvelleClasse);
    }
}
