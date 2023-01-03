package com.example.objectaid_sae.controleur;

import com.example.objectaid_sae.model.Classe;
import com.example.objectaid_sae.observateur.Sujet;
import com.example.objectaid_sae.vue.VueClasse;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class ControleurClasseGlissee implements EventHandler<MouseEvent> {

    Classe s;
    public ControleurClasseGlissee(Sujet s){
        this.s = (Classe) s;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        System.out.println("rentré dans event");
        double Xmouse = mouseEvent.getSceneX();
        double Ymouse = mouseEvent.getSceneY();
        System.out.println(Xmouse + "     " + Ymouse);

        VueClasse vc = (VueClasse) mouseEvent.getSource();
        s.setX(Xmouse-280);
        s.setY(Ymouse);
        System.out.println(s.getX() + "     " +s.getY());
        s.notifierObservateurs();
    }
}
