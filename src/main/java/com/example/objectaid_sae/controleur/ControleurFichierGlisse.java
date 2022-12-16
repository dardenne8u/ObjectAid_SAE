package com.example.objectaid_sae.controleur;

import com.example.objectaid_sae.model.Classe;
import com.example.objectaid_sae.model.Model;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;


public class ControleurFichierGlisse implements EventHandler<MouseEvent> {

    Model mod;
    @Override
    public void handle(MouseEvent mouseEvent) {
        //if (mouseEvent.getEventType().equals(MouseEvent.MOUSE_PRESSED)) System.out.println("pressed on " + mouseEvent.getSceneX() + " " + mouseEvent.getSceneY());
        Node source = (Node) mouseEvent.getSource();
        if (mouseEvent.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {//System.out.println("release on " + mouseEvent.getSceneX() + " " + mouseEvent.getSceneY());
            while (source.getClass() != TreeView.class) source = source.getParent();
        }
        BorderPane bp = (BorderPane)source.getParent().getParent();
        Node center = bp.getCenter();
        if (center.contains(mouseEvent.getSceneX(), mouseEvent.getSceneY())&& !source.contains(mouseEvent.getSceneX(), mouseEvent.getSceneY())){
            HBox h = (HBox)mouseEvent.getSource();
            Text t = (Text)h.getChildren().get(1);
            Classe c = new Classe();
            /*c.setX(mouseEvent.getSceneX());
            String nom = t.getText();
            c.setY(mouseEvent.getSceneY());*/
            System.out.println("bon endroit");
        }
        else {
            System.out.println("pas bon endroit");
        }

    }
}