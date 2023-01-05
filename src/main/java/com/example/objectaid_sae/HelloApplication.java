package com.example.objectaid_sae;

import com.example.objectaid_sae.controleur.ControleurClasseCliquer;
import com.example.objectaid_sae.controleur.ControleurClasseGlissee;
import com.example.objectaid_sae.controleur.ControleurFichierGlisse;
import com.example.objectaid_sae.model.Classe;
import com.example.objectaid_sae.model.Model;
import com.example.objectaid_sae.vue.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Pane pane = new VueCentre();
        BorderPane bp = new BorderPane();
        bp.setTop(new VueHaut());

        // setup du borderPane
        pane.setBorder(new Border(new BorderStroke(Color.valueOf("#9E9E9E"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        bp.setCenter(pane);

        bp.setLeft(new VueFichiers("./src/main/java/com/example/objectaid_sae"));
        Scene scene = new Scene(bp, 800, 600);
        stage.setTitle("ObjectAid SAE");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}