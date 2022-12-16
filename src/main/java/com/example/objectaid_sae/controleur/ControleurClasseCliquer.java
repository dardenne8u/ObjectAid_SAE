package com.example.objectaid_sae.controleur;

import com.example.objectaid_sae.model.Classe;
import com.example.objectaid_sae.vue.VueClasse;
import com.example.objectaid_sae.vue.VueMenuTemporaire;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ControleurClasseCliquer implements EventHandler<MouseEvent> {

    private VueMenuTemporaire temp;
    private Classe classe;

    public ControleurClasseCliquer (VueMenuTemporaire menutemp, Classe classe){
        this.temp = menutemp;
        this.classe = classe;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        VueClasse vue = (VueClasse) mouseEvent.getSource();

        if (mouseEvent.getSource() instanceof VueClasse){
            temp.setCacher(!temp.isCacher());
            temp.notifier(classe);
            temp.setLayoutX(mouseEvent.getSceneX());
            temp.setLayoutY(mouseEvent.getSceneY());
            System.out.println("Source : " +vue);
        }
        if (mouseEvent.getSource() instanceof VueMenuTemporaire){
            classe.setAfficheAttributsHerite(true);
            classe.setAfficheAttributsDeclare(true);
            classe.setAfficheMethodeDeclare(true);
            classe.setAfficheMethodeHerite(true);
        }

        classe.notifierObservateurs();


        System.out.println("Vue cliquée !");

    }
}
