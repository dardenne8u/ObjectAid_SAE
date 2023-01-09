package com.example.objectaid_sae.controleur;

import com.example.objectaid_sae.model.Classe;
import com.example.objectaid_sae.vue.VueCentre;
import com.example.objectaid_sae.vue.VueCheckClass;
import com.example.objectaid_sae.vue.VueCreation;
import com.example.objectaid_sae.vue.VueSousMenuClassExt;
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



    public boolean affichageCoteDroit(Node n){
        VueCentre pane = (VueCentre) n.getParent().getParent();
        double wp = pane.getWidth();
        double x =  n.getParent().getLayoutX();
        System.out.println("wp : " + wp + "\nx : " + x);
        if(x >= wp/2 )return false;
        return true;
    }

    @Override
    public void handle(ActionEvent evt) {

        if (evt.getSource().getClass() == Button.class) {
            Button src = (Button) evt.getSource();



            if (src.getText().contains("Valider")) {
                String nom = ((TextField) (src.getParent().getChildrenUnmodifiable().get(1))).getText();
                if (((Label) (src.getParent().getChildrenUnmodifiable().get(0))).getText().contains("methode")) {
                    if (estMethode(nom)) classe.getMethodes().get(Classe.DECLARED).add(nom);}
                else {
                    if (estAttribut(nom)) {
                        System.out.println("ceci est un attribut");
                        classe.getAttributs().get(Classe.DECLARED).add(nom);
                        ((Pane) (src.getParent().getParent())).getChildren().remove(src.getParent());
                    }
                }
            }
            else if (src.getText().contains("Afficher")) {
                VueCheckClass ch = new VueCheckClass(this);
                System.out.println("vue check");
                if (MenuTemp != null) ((Pane) (src.getParent().getParent())).getChildren().remove(MenuTemp);
                if( affichageCoteDroit(src)) {
                    ch.setLayoutX(src.getParent().getLayoutX() + src.getWidth());
                }else{
                    ch.setLayoutX(src.getParent().getLayoutX() - ch.getWidth());
                }
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
                        case "méthodes héritées":
                            check.setSelected(classe.isAfficheMethodeHerite());
                            break;
                        case "constructeur":
                            check.setSelected(classe.isAfficheConstructeur());
                            break;
                    }
                }
                MenuTemp = ch;
            }
            else if(src.getText().equals("Classes externes")) {
                final Pane pane = (Pane) src.getParent().getParent();

                VueSousMenuClassExt.classeExt.setLayoutY(src.getParent().getLayoutY());
                classe.ajouterObservateur(VueSousMenuClassExt.classeExt);
                pane.getChildren().add(VueSousMenuClassExt.classeExt);
                VueSousMenuClassExt.classeExt.notifier(classe);
                if( affichageCoteDroit(src)) {
                    VueSousMenuClassExt.classeExt.setLayoutX(src.getParent().getLayoutX() + src.getWidth());
                }else{
                    VueSousMenuClassExt.classeExt.setLayoutX(src.getParent().getLayoutX() - VueSousMenuClassExt.classeExt.getWidth());
                }
            }
            else{
                VueCreation v;
                if (src.getText().contains("methode")) v = new VueCreation("methode", this);
                else v = new VueCreation("attribut", this);
                if( affichageCoteDroit(src)) {
                    v.setLayoutX(src.getParent().getLayoutX() + src.getWidth());
                }else{
                    v.setLayoutX(src.getParent().getLayoutX() - v.getWidth());
                }
                ((Pane) (src.getParent().getParent())).getChildren().add(v);
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
                case "méthodes héritées":
                    classe.setAfficheMethodeHerite(src.isSelected());
                    break;
                case "constructeur":
                    classe.setAfficheConstructeur(src.isSelected());
                    break;
            }
        }

    }
}
