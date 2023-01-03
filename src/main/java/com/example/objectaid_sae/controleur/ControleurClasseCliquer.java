package com.example.objectaid_sae.controleur;

import com.example.objectaid_sae.model.Classe;
import com.example.objectaid_sae.vue.VueClasse;
import com.example.objectaid_sae.vue.VueMenuTemporaire;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class ControleurClasseCliquer implements EventHandler<MouseEvent> {

    private VueMenuTemporaire temp;
    private Classe classe;

    public ControleurClasseCliquer(Classe classe) {
        this.temp = VueMenuTemporaire.VUE_TEMP;
        this.classe = classe;
    }

    public ControleurClasseCliquer (VueMenuTemporaire menutemp, Classe classe){
        this.temp = menutemp;
        this.classe = classe;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        VueClasse vue = (VueClasse) mouseEvent.getSource();

        if (mouseEvent.getSource() instanceof VueClasse){
            if(temp.isCacher())
                ((Pane) ((VueClasse) mouseEvent.getSource()).getParent()).getChildren().add(temp);
            else
                ((Pane) temp.getParent()).getChildren().remove(temp);
            temp.setCacher(!temp.isCacher());
//            temp.notifier(classe);

            temp.setLayoutX(mouseEvent.getX());
            temp.setLayoutY(mouseEvent.getY());
            System.out.println("Source : " +vue);
        }
        /*
        if (mouseEvent.getSource() instanceof VueMenuTemporaire){
            classe.setAfficheAttributsHerite(true);
            classe.setAfficheAttributsDeclare(true);
            classe.setAfficheMethodeDeclare(true);
            classe.setAfficheMethodeHerite(true);
        }*/

        temp.notifier(classe);

        System.out.println("Vue cliquée !");

    }
}
