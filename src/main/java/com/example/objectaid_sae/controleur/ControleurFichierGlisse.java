package com.example.objectaid_sae.controleur;

import com.example.objectaid_sae.model.Analyseur;
import com.example.objectaid_sae.model.Classe;
import com.example.objectaid_sae.model.Fichier;
import com.example.objectaid_sae.model.Model;
import com.example.objectaid_sae.vue.VueClasse;
import com.example.objectaid_sae.vue.VueMenuTemporaire;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;


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
        Pane centre = (Pane) center;
        if (center.contains(mouseEvent.getSceneX(), mouseEvent.getSceneY())&& !source.contains(mouseEvent.getSceneX(), mouseEvent.getSceneY())){
            HBox h = (HBox)mouseEvent.getSource();
            CheckBox cb =(CheckBox) h.getChildren().get(0);
            if (cb.isSelected()) return;
            Label l = (Label)h.getChildren().get(2);
            Label t = (Label) h.getChildren().get(1);
            Pane p = (Pane)source.getParent();
            String abs = l.getText();
            if(abs.contains("java")){
                try {
                    BufferedReader r = new BufferedReader(new FileReader(abs));
                    String line = r.readLine();
                    System.out.println(line);
                    while (!line.contains("package ")) line = r.readLine();
                        line = line.split("package ")[1].split(";")[0];
                        System.out.println(line);
                        Classe c = new Analyseur(line+"."+t.getText().split("\\.")[0]).analyseClasse();
                        c.setX(mouseEvent.getSceneX()-p.getWidth());
                        c.setType(t.getText());
                        c.setY(mouseEvent.getSceneY());
                        cb.setSelected(true);
                        VueClasse vue = new VueClasse();
                        vue.setOnMouseClicked(new ControleurClasseCliquer(c));
                        c.ajouterObservateur(vue);
                        c.notifierObservateurs();
                        centre.getChildren().add(vue);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println("coos ds le Pane : " + (mouseEvent.getSceneX()-p.getWidth()) + " " + mouseEvent.getSceneY());
        }
    }
}