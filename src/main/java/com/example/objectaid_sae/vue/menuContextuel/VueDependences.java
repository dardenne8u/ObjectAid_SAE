package com.example.objectaid_sae.vue.menuContextuel;

import com.example.objectaid_sae.controleur.ControleurButtonMenuAddFleche;
import com.example.objectaid_sae.model.Classe;
import com.example.objectaid_sae.model.Model;
import com.example.objectaid_sae.observateur.Observateur;
import com.example.objectaid_sae.observateur.Sujet;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class VueDependences extends VBox implements Observateur {
    public static final VueDependences vueDependences = new VueDependences();
    private boolean classeChoisie = false;
    private Classe classeSelection;
    private Classe sujet;
    @Override
    public void notifier(Sujet s) {
        this.sujet = (Classe) s;
        this.getChildren().clear();
        if(classeChoisie)
            vueChoisirFleche();
        else
            vueChoisirClass();

    }

    /**
     * Permet de mettre l'affichage
     * pour choisir une classe existante
     * dans le model
     */
    private void vueChoisirClass() {
        final Model model = Model.getModel();
        for(Classe c : model.getClasses()) {
            if(c == sujet) continue;
            Button temp = new Button(c.getType().substring(c.getType().lastIndexOf(" ")+1));
            temp.setOnAction(event -> {
                classeChoisie = true;
                classeSelection = c;
                this.notifier(sujet);
            });
            this.getChildren().add(temp);
        }
    }

    /**
     * Permet de mettre l'affichage pour
     * choisir une fleche parmis celle qui
     * existe
     */
    private void vueChoisirFleche() {
        Button ext = new Button("Heritage");
        Button imp = new Button("Implementation");
        Button use = new Button("Utilisation");
        ControleurButtonMenuAddFleche cont = new ControleurButtonMenuAddFleche();
        ext.setOnAction(cont);
        imp.setOnAction(cont);
        use.setOnAction(cont);
        this.getChildren().addAll(ext, imp, use);
    }

    /**
     * Permet de clear les donnees
     * stocker dans la vue
     */
    public void clear() {
        this.sujet = null;
        this.classeSelection = null;
        this.classeChoisie = false;
    }

    public Classe getClasseChoisie() {
        return this.classeSelection;
    }

    public Classe getClasseActuel() {return this.sujet;}


}
