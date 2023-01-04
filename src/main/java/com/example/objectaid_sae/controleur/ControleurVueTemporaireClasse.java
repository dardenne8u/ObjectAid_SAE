package com.example.objectaid_sae.controleur;

import com.example.objectaid_sae.model.Classe;
import com.example.objectaid_sae.vue.VueCheckClass;
import com.example.objectaid_sae.vue.VueCreation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ControleurVueTemporaireClasse implements EventHandler<ActionEvent> {

    Classe classe;
    VBox MenuTemp;

    public ControleurVueTemporaireClasse(Classe classe) {
        this.classe = classe;
    }



    public boolean estAttribut(String attribut) {
        if (attribut == null || attribut.trim().length() == 0) {
            return false;
        }
        // La chaîne doit être au format "symbole nom: type"
        String pattern = "^\\s*[+# -]\\s*[a-zA-Z0-9_]+\\s*:\\s*[a-zA-Z0-9_]+\\s*$";
        return attribut.matches(pattern);
    }

    public boolean estMethode(String methode) {
        if (methode == null || methode.trim().length() == 0) {
            return false;
        }
        // La chaîne doit être au format "nom(arg1: type1, arg2: type2, ...): typeRetour"
        String pattern = "^\\s*[+# -]\\s*[a-zA-Z0-9_]+\\s*\\(([a-zA-Z0-9_]+\\s*:\\s*[a-zA-Z0-9_]+\\s*,\\s*)*([a-zA-Z0-9_]+\\s*:\\s*[a-zA-Z0-9_]+)\\s*\\)\\s*:\\s*[a-zA-Z0-9_]+\\s*$";
        return methode.matches(pattern);
    }



    @Override
    public void handle(ActionEvent evt) {

        if (evt.getSource().getClass() == Button.class) {
            Button src = (Button) evt.getSource();
            if (src.getText().contains("Valider")) {
                String nom = ((TextField) (src.getParent().getChildrenUnmodifiable().get(1))).getText();
                if (((Label) (src.getParent().getChildrenUnmodifiable().get(0))).getText().contains("methode"))
                    if(estMethode(nom))classe.getMethodes().get(Classe.DECLARED).add(nom);
                else if(estAttribut(nom)) {
                    classe.getAttributs().get(Classe.DECLARED).add(nom);
                    ((Pane) (src.getParent().getParent())).getChildren().remove(src.getParent());
                }
            }
            else if (src.getText().contains("Afficher")) {
                VueCheckClass ch = new VueCheckClass(this);
                System.out.println("vue check");
                if (MenuTemp != null) ((Pane) (src.getParent().getParent())).getChildren().remove(MenuTemp);
                ch.setLayoutX(src.getParent().getLayoutX() + src.getWidth());
                ch.setLayoutY(src.getParent().getLayoutY());
                ((Pane) (src.getParent().getParent())).getChildren().add(ch);
                for(Node node : ch.getChildren()) {
                    CheckBox check = (CheckBox) node;
                    switch (check.getText()) {
                        case "attributs declarés":
                            check.setSelected(classe.isAfficheAttributsDeclare());
                            break;
                        case "attributs hérités":
                            check.setSelected(classe.isAfficheAttributsHerite());
                            break;
                        case "méthodes déclarées":
                            check.setSelected(classe.isAfficheMethodeDeclare());
                            break;
                        case "méthodes hérités":
                            check.setSelected(classe.isAfficheMethodeHerite());
                            break;
                    }
                }
                MenuTemp = ch;
            }
            else{
                VueCreation v;
                if (src.getText().contains("methode")) v = new VueCreation("methode", this);
                else v = new VueCreation("attribut", this);
                System.out.println("vue cree");
                ((Pane) (src.getParent().getParent())).getChildren().add(v);
                v.setLayoutX(src.getParent().getLayoutX() + src.getWidth());
                v.setLayoutY(src.getParent().getLayoutY());
                if (MenuTemp != null) ((Pane) (src.getParent().getParent())).getChildren().remove(MenuTemp);
                MenuTemp = v;
            }


        } else {
            CheckBox src = (CheckBox) evt.getSource();
            switch (src.getText()) {
                case "attributs declarés":
                    classe.setAfficheAttributsDeclare(src.isSelected());
                    break;
                case "attributs hérités":
                    classe.setAfficheAttributsHerite(src.isSelected());
                    break;
                case "méthodes déclarées":
                    classe.setAfficheMethodeDeclare(src.isSelected());
                    break;
                case "méthodes hérités":
                    classe.setAfficheMethodeHerite(src.isSelected());
                    break;
            }
        }
        classe.notifierObservateurs();
    }
}