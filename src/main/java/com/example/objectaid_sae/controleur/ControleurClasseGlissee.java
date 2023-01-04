package com.example.objectaid_sae.controleur;

import com.example.objectaid_sae.model.Classe;
import com.example.objectaid_sae.observateur.Sujet;
import com.example.objectaid_sae.vue.VueClasse;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class ControleurClasseGlissee implements EventHandler<MouseEvent> {

    Classe s;
    private double width;

    /**
     * contructeur creant un controleur pour glisser la classe passee en parametre
     *
     * @param s, la classe deplacee
     */
    public ControleurClasseGlissee(Sujet s) {
        this.s = (Classe) s;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        final VueClasse vue = (VueClasse) mouseEvent.getSource();
        final Pane center = (Pane) vue.getParent();

        // definition de la position x
        double mouseX = mouseEvent.getSceneX() - center.getLayoutX();
        mouseX = Math.max(0, mouseX); // minimum
        mouseX = Math.min((center.getLayoutX() + center.getWidth() - this.width ), mouseX); // maximum

        // definition de la position y
        double mouseY = mouseEvent.getSceneY() - center.getLayoutY();
        mouseY = Math.max(0, mouseY); // minimum
        mouseY = Math.min((center.getLayoutY() + center.getHeight() - vue.getHeight()), mouseY); // maximum

        s.setX(mouseX);
        s.setY(mouseY);
        s.notifierObservateurs();
    }


    public void set(double w) {
        this.width = w;
    }

}
