package com.example.objectaid_sae.controleur;

import com.example.objectaid_sae.model.Analyseur;
import com.example.objectaid_sae.model.Classe;
import com.example.objectaid_sae.model.Model;
import com.example.objectaid_sae.vue.VueClasse;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.BufferedReader;
import java.io.FileReader;


public class ControleurFichierGlisse implements EventHandler<MouseEvent> {

    Model mod;

    public ControleurFichierGlisse(Model m){
        mod = m;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        Node source = (Node) mouseEvent.getSource();
        if (mouseEvent.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {
            while (source.getClass() != TreeView.class) source = source.getParent();
        }
        BorderPane bp = (BorderPane) source.getParent().getParent();
        Node center = bp.getCenter();
        Pane centre = (Pane) center;
        if (center.contains(mouseEvent.getSceneX(), mouseEvent.getSceneY()) && !source.contains(mouseEvent.getSceneX(), mouseEvent.getSceneY())) {
            HBox h = (HBox) mouseEvent.getSource();
            CheckBox cb = (CheckBox) h.getChildren().get(0);
            if (cb.isSelected()) return;
            Label l = (Label) h.getChildren().get(2);
            Label t = (Label) h.getChildren().get(1);
            String abs = l.getText();
            if (abs.contains("java")) {
                try {
                    BufferedReader r = new BufferedReader(new FileReader(abs));
                    String line = r.readLine();
                    while (!line.contains("package ")) line = r.readLine();
                    line = line.split("package ")[1].split(";")[0];
                    Classe c = new Analyseur(line + "." + t.getText().split("\\.")[0]).analyseClasse();
                    c.setX(mouseEvent.getSceneX() - centre.getLayoutX());
                    c.setType(t.getText());
                    c.setY(mouseEvent.getSceneY() - centre.getLayoutY());
                    cb.setSelected(true);
                    VueClasse vue = new VueClasse(c);
                    centre.getChildren().add(vue);
                    mod.addClasse(c);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}