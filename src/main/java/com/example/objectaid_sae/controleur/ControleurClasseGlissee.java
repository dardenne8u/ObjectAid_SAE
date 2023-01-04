package com.example.objectaid_sae.controleur;

import com.example.objectaid_sae.model.Classe;
import com.example.objectaid_sae.observateur.Sujet;
import com.example.objectaid_sae.vue.VueCheckClass;
import com.example.objectaid_sae.vue.VueClasse;
import com.example.objectaid_sae.vue.VueCreation;
import com.example.objectaid_sae.vue.VueMenuTemporaire;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.util.ConcurrentModificationException;

public class ControleurClasseGlissee implements EventHandler<MouseEvent> {

    Classe s;
    private double xS;
    private double yS;
    private double width;

    public ControleurClasseGlissee(Sujet s) {

        this.s = (Classe) s;

    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        final VueClasse vue = (VueClasse) mouseEvent.getSource();
        final Pane center = (Pane) vue.getParent();


        if (mouseEvent.getEventType() == MouseEvent.MOUSE_CLICKED) {
            // TODO : A revoir la position du curseur
            this.xS = mouseEvent.getSceneX();
            this.yS = mouseEvent.getSceneY();

        } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_DRAGGED) {
            //suppression des menus temporaires
            try {
                for (Node n : center.getChildren()) {
                    if (n.getClass() == VueCheckClass.class || n.getClass() == VueMenuTemporaire.class || n.getClass() == VueCreation.class)
                        center.getChildren().remove(n);
                }
            }
            catch (ConcurrentModificationException ignored) {}
            // recuperation des valeurs
            // definition de la position x
            double mouseX = mouseEvent.getSceneX() - center.getLayoutX();
            mouseX = Math.max(0, mouseX); // minimum
            mouseX = Math.min((center.getLayoutX() + center.getWidth() - this.width-250), mouseX); // maximum

            // definition de la position y
            double mouseY = mouseEvent.getSceneY() - center.getLayoutY();
            mouseY = Math.max(0, mouseY); // minimum
            mouseY = Math.min((center.getLayoutY() + center.getHeight() - vue.getHeight()), mouseY); // maximum

            s.setX(mouseX);
            s.setY(mouseY);
            s.notifierObservateurs();
        }
    }

    public void set(double w) {
        this.width = w;
    }

}
