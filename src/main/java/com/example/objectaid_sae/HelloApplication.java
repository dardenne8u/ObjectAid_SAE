package com.example.objectaid_sae;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Pane pane = new Pane();
        VueClasse vue = new VueClasse();
        vue.notifier(new Model());
        pane.getChildren().add(vue);
        Scene scene = new Scene(pane, 320, 240);
        TreeItem<String> treeee = new TreeItem<>("coucou");
        treeee.getChildren().add(new TreeItem<>("coucou2"));
        TreeView<String> tree = new TreeView<>(treeee);
        Scene scene = new Scene(tree);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}