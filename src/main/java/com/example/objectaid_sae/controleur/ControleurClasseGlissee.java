package com.example.objectaid_sae.controleur;

import com.example.objectaid_sae.model.Classe;
import com.example.objectaid_sae.model.Fleche;
import com.example.objectaid_sae.observateur.Sujet;
import com.example.objectaid_sae.vue.VueCentre;
import com.example.objectaid_sae.vue.VueClasse;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * Controleur qui permet de deplacer une classe dans le pane
 */
public class ControleurClasseGlissee implements EventHandler<MouseEvent> {

    /**
     * sujet Classe de la classe glissee
     */
    Classe s;

    /**
     * contructeur creant un controleur pour glisser la classe passee en parametre
     *
     * @param s, la classe deplacee
     */
    public ControleurClasseGlissee(Sujet s) {

        this.s = (Classe) s;

    }

    /**
     * methode handle pour gerer le deplacement des classes dans le pane central
     * @param mouseEvent l'evenement dragged de la souris sur la classe
     */
    @Override
    public void handle(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseButton.SECONDARY) return;
        final VueClasse vue = (VueClasse) mouseEvent.getSource();
        final VueCentre center = (VueCentre) vue.getParent();

        if (mouseEvent.getEventType() == MouseEvent.MOUSE_DRAGGED)
            //suppression des menus temporaires
            center.supprimerMenusTemp();
        // definition de la position x
        double mouseX = mouseEvent.getSceneX() - center.getLayoutX();
        mouseX = Math.max(0,mouseX); // minimum
        mouseX = Math.min((center.getWidth() - vue.getWidth()), mouseX); // maximum

        // definition de la position y
        double mouseY = mouseEvent.getSceneY() - center.getLayoutY();
        mouseY = Math.min(center.getHeight() - vue.getHeight(),mouseY); // maximum
        mouseY = Math.max(0, mouseY);// minimum
        s.setX(mouseX);
        s.setY(mouseY);
        s.notifierObservateurs();
        // actualisation des fleches
        Fleche.actualiserFleches(center);
    }

    /**
     * retourne la classe selectionnee par la souris, enregistree dans le controleur
     * @return la Classe
     */
    public Classe getClasse() {
        return s;
    }
}
