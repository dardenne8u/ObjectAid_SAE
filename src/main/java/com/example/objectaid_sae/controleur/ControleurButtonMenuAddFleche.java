package com.example.objectaid_sae.controleur;

import com.example.objectaid_sae.model.Classe;
import com.example.objectaid_sae.model.Fleche;
import com.example.objectaid_sae.model.Model;
import com.example.objectaid_sae.vue.VueCentre;
import com.example.objectaid_sae.vue.menuContextuel.VueDependences;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ControleurButtonMenuAddFleche implements EventHandler {
    @Override
    public void handle(Event event) {
        Button btn = (Button) event.getSource();
        Classe depart = VueDependences.vueDependences.getClasseActuel();
        String nomDep = depart.getType().substring(depart.getType().lastIndexOf(" ")+1);
        Classe arriver = VueDependences.vueDependences.getClasseChoisie();
        String nomFin = arriver.getType().substring(arriver.getType().lastIndexOf(" ")+1);
        String link = nomDep;
        String type="";
        switch (btn.getText()) {
            case "Heritage":
                type = "--|>";
                break;
            case "Implementation":
                type = "..|>";
                break;
            case "Utilisation":
                type = "-->";
                break;
            default:
                System.out.println("Problème");
        }
        link += " "+ type +" " + nomFin;
        System.out.println(link);
        depart.addDependencies(link);
        Fleche.creerFleches(depart,(VueCentre) btn.getParent().getParent());
     }
}
