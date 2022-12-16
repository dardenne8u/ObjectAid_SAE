package com.example.objectaid_sae;

import com.example.objectaid_sae.controleur.ControleurClasseCliquer;
import com.example.objectaid_sae.model.Classe;
import com.example.objectaid_sae.model.Model;
import com.example.objectaid_sae.vue.VueClasse;
import com.example.objectaid_sae.vue.VueMenuTemporaire;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Pane pane = new Pane();

        Classe classe = new Classe();
        VueMenuTemporaire vueTemp = new VueMenuTemporaire(classe);
        vueTemp.setCacher(false);
        classe.ajouterObservateur(vueTemp);
        VueClasse vue = new VueClasse(classe, vueTemp);
        classe.ajouterObservateur(vue);
        classe.addAttribut(1, "- premier: String");
        classe.addAttribut(1, "- deuxieme: int");
        classe.addMethode(1, "+ methode1 () : String");
        classe.addMethode(2, "+ methode2 () : int");
        vue.notifier(classe);

        pane.getChildren().addAll(vue, vueTemp);
        Scene scene = new Scene(pane, 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}