package com.example.objectaid_sae.controleur;

import com.example.objectaid_sae.vue.VueCentre;
import com.example.objectaid_sae.vue.VueFichiers;
import com.example.objectaid_sae.vue.VueHaut;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;

public class ControleurZoom implements EventHandler<ScrollEvent> {
   final double SCALE_DELTA = 1.1;
   Group content;
   private VueCentre vueCentre;
   static int nbDefilement;

   public ControleurZoom(Group group, VueCentre vueCentre){
       this.content = group;
       this.vueCentre = vueCentre;
   }

    @Override
    public void handle(ScrollEvent scrollEvent) {
        scrollEvent.consume();
        if (scrollEvent.getDeltaY()==0){
            return;
        }
        double scaleFactor = (scrollEvent.getDeltaY() > 0) ? SCALE_DELTA : 1 / SCALE_DELTA;
        content.setScaleX(content.getScaleX() * scaleFactor);
        content.setScaleY(content.getScaleY() * scaleFactor);
        nbDefilement++;

        System.out.println("Scale_X : " + content.getScaleX());
        System.out.println("Scale_Y : " + content.getScaleY());
        System.out.println(scaleFactor);

        System.out.println("\n\nNBDEFIL" + nbDefilement);

        if(scaleFactor >= 1) {

            System.out.println("RENTRE IF");

            if (nbDefilement < 4) {
                content.setLayoutX(nbDefilement * 10 * content.getScaleX());
                System.out.println("Content_X : " + content.getLayoutX());
                content.setLayoutY(nbDefilement * 10 * scaleFactor);
                System.out.println("Content_Y : " + content.getLayoutY());
            } else {
                content.setLayoutX(nbDefilement * 10.5 * scaleFactor);
                System.out.println("Content_X : " + content.getLayoutX());
                content.setLayoutY(nbDefilement * 12 * scaleFactor);
                System.out.println("Content_Y : " + content.getLayoutY());
            }

        } else {
            System.out.println("RENTRE ELSE");
            content.setLayoutX(nbDefilement * 10 * (-content.getScaleX()));
            System.out.println("Content_X : " + content.getLayoutX());
            content.setLayoutY(nbDefilement * 10 * (-scaleFactor));
            System.out.println("Content_Y : " + content.getLayoutY());
        }



    }
}
