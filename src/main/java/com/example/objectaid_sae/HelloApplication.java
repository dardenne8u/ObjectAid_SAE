package com.example.objectaid_sae;

import com.example.objectaid_sae.controleur.ControleurClasseCliquer;
import com.example.objectaid_sae.controleur.ControleurFichierGlisse;
import com.example.objectaid_sae.model.Classe;
import com.example.objectaid_sae.model.Model;
import com.example.objectaid_sae.vue.VueClasse;
import com.example.objectaid_sae.vue.VueFichiers;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Pane pane = new Pane();
        BorderPane bp = new BorderPane();
        /*Classe classe = new Classe();
        classe.addAttribut(1, "- premier: String");
        classe.addAttribut(1, "- deuxieme: int");
        classe.addMethode(1, "+ methode1 () : String");
        classe.addMethode(2, "+ methode2 () : int");
        VueClasse vue = new VueClasse(classe);
        vue.notifier(classe);
        ControleurClasseCliquer controleurClasseCliquer = new ControleurClasseCliquer(classe);
        pane.getChildren().add(vue);*/
        bp.setCenter(pane);
        bp.setLeft(new VueFichiers("C:/xampp/htdocs/WEB/ObjectAid_SAE/src/main/java"));
        Scene scene = new Scene(bp, 800, 600);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}