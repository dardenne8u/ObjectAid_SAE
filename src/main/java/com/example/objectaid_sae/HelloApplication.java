package com.example.objectaid_sae;

import com.example.objectaid_sae.vue.VueCentre;
import com.example.objectaid_sae.vue.VueFichiers;
import com.example.objectaid_sae.vue.VueHaut;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage){
        BorderPane bp = new BorderPane();
        Scene scene = new Scene(bp, 1000, 600);
        stage.setTitle("ObjectAid SAE");
        stage.setScene(scene);
        Pane pane = new VueCentre();
        bp.setTop(new VueHaut());
        pane.setBorder(new Border(new BorderStroke(Color.valueOf("#9E9E9E"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        bp.setCenter(pane);
        bp.setLeft(new VueFichiers(VueFichiers.PATH));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}