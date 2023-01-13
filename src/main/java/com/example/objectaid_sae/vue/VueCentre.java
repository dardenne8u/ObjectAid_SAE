package com.example.objectaid_sae.vue;

import com.example.objectaid_sae.controleur.ControleurCentreClique;
import com.example.objectaid_sae.model.Classe;
import com.example.objectaid_sae.vue.menuContextuel.*;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class VueCentre extends Pane {

    public VueCentre() {
        setOnMouseClicked(new ControleurCentreClique(this));
    }

    /**
     * Permet de supprimer tout
     * les menus contextuels d'ouvert
     */
    public void supprimerMenusTemp() {
        Class[] menusTemps = {VueCheckClass.class, VueMenuTemporaire.class, VueCreation.class, VueAffichageGlobal.class, VueSousMenuClassExt.class, VueDependences.class};
        ArrayList<Class> menus = new ArrayList<>(List.of(menusTemps));
        getChildren().removeIf(n -> menus.contains(n.getClass()));
        VueMenuTemporaire.VUE_TEMP.setCacher(true);
        VueMenuTemporaire.VUE_TEMP.notifier(null);
    }

    /**
     * Permet de supprimer toutes
     * les fleches
     */
    public void supprimerFleches(){
        getChildren().removeIf(n -> n.getClass() == VueFleche.class);
    }

    /**
     * Permet de trouver la vue classe en relation
     * a la classe donnee en parametre
     * @param classe Classe donnee en parametre
     * @return une vue classe non null si elle trouve
     */
    public VueClasse findVueClasse(Classe classe) {
        VueClasse res = null;
        for(Node node : this.getChildren()) {
            if(node instanceof VueClasse) {
                VueClasse v = (VueClasse) node;
                if(v.isForClasse(classe)) {
                    res = v;
                    break;
                }
            }
        }
        return res;
    }
}
