package com.example.objectaid_sae.controleur;

import com.example.objectaid_sae.model.Classe;
import com.example.objectaid_sae.model.Model;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;


public class ControleurFichierGlisse implements EventHandler<MouseEvent> {

    Model mod;
    @Override
    public void handle(MouseEvent mouseEvent) {
        //if (mouseEvent.getEventType().equals(MouseEvent.MOUSE_PRESSED)) System.out.println("pressed on " + mouseEvent.getSceneX() + " " + mouseEvent.getSceneY());
        Node source = (Node) mouseEvent.getSource();
        if (mouseEvent.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {//System.out.println("release on " + mouseEvent.getSceneX() + " " + mouseEvent.getSceneY());
            while (source.getClass() != TreeView.class) source = source.getParent();
        }
        if (!source.contains(mouseEvent.getSceneX(), mouseEvent.getSceneY())){
            Classe c = new Classe();
            c.setX(mouseEvent.getSceneX());
            c.setY(mouseEvent.getSceneY());
        }

    }
}