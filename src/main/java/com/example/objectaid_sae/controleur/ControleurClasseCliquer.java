package com.example.objectaid_sae.controleur;

import com.example.objectaid_sae.model.Classe;
import com.example.objectaid_sae.vue.VueCentre;
import com.example.objectaid_sae.vue.VueClasse;
import com.example.objectaid_sae.vue.menuContextuel.VueMenuTemporaire;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class ControleurClasseCliquer implements EventHandler<MouseEvent> {

    private VueMenuTemporaire temp;
    private Classe classe;

    public ControleurClasseCliquer(Classe classe) {
        this.temp = VueMenuTemporaire.VUE_TEMP;
        this.classe = classe;
    }

    public ControleurClasseCliquer(VueMenuTemporaire menutemp, Classe classe) {
        this.temp = menutemp;
        this.classe = classe;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        VueClasse vue = (VueClasse) mouseEvent.getSource();
        if(mouseEvent.getButton() == MouseButton.PRIMARY) return;
        if (mouseEvent.getSource() instanceof VueClasse) {
            final VueCentre pane = ((VueCentre) ((VueClasse) mouseEvent.getSource()).getParent());
            if (temp.isCacher()) {
                temp.setCacher(false);
                pane.getChildren().add(temp);
            }
            else
                pane.supprimerMenusTemp();
            //temp.notifier(classe);

            temp.setLayoutX(mouseEvent.getSceneX() - pane.getLayoutX());
            temp.setLayoutY(mouseEvent.getSceneY() - pane.getLayoutY());
            System.out.println("Source : " + vue);
        }
        if (mouseEvent.getSource() instanceof VueMenuTemporaire){
            classe.setAfficheAttributsHerite(true);
            classe.setAfficheAttributsDeclare(true);
            classe.setAfficheMethodeDeclare(true);
            classe.setAfficheMethodeHerite(true);
        }

        temp.notifier(classe);

    }
}