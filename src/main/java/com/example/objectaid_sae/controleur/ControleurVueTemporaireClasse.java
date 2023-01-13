package com.example.objectaid_sae.controleur;

import com.example.objectaid_sae.model.Classe;
import com.example.objectaid_sae.model.Fleche;
import com.example.objectaid_sae.model.Model;
import com.example.objectaid_sae.vue.VueCentre;
import com.example.objectaid_sae.vue.VueFichiers;
import com.example.objectaid_sae.vue.menuContextuel.VueCheckClass;
import com.example.objectaid_sae.vue.menuContextuel.VueCreation;
import com.example.objectaid_sae.vue.menuContextuel.VueDependences;
import com.example.objectaid_sae.vue.menuContextuel.VueSousMenuClassExt;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

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

        // (^[+# -])?\\s[a-z_][a-zA-Z0-9_]\\(([a-zA-Z0-9])|[a-zA-Z0-9]\\s:[a-zA-Z0-9_]+
        String pattern = "(^[+# -]\\s?)?[a-z][\\w -]+\\(((\\w+,?\\s?)?)+\\)\\s?:\\s?\\w+";
        return methode.matches(pattern);
    }


    public boolean affichageCoteDroit(Node n) {
        VueCentre pane = (VueCentre) n.getParent().getParent();
        double wp = pane.getWidth();
        double x = n.getParent().getLayoutX();
        if (x >= wp / 2) return false;
        return true;
    }

    @Override
    public void handle(ActionEvent evt) {
        // recuperation de la source de l'event et verification de sa classe
        if (evt.getSource().getClass() == Button.class) { //cas ou la source est un boutton
            Button src = (Button) evt.getSource();
            //bouton valider on ajoute donc un attribut ou une methode
            if (src.getText().contains("Valider")) {
                String nom = ((TextField) (src.getParent().getChildrenUnmodifiable().get(1))).getText();
                //cas ou on ajoute une methode
                if (((Label) (src.getParent().getChildrenUnmodifiable().get(0))).getText().contains("methode")) {
                    // verification de la validite et ajout
                    if (estMethode(nom)) classe.getMethodes().get(Classe.DECLARED).add(nom);
                } else {
                    // si ce n'est aps une methode c'est un attribut
                    // verification de la validite et ajout
                    if (estAttribut(nom)) {
                        classe.getAttributs().get(Classe.DECLARED).add(nom);
                        ((Pane) (src.getParent().getParent())).getChildren().remove(src.getParent());
                    }
                }
                // la classe a ete modifiee, on le notifie a ses observateurs
                classe.notifierObservateurs();
                // boutton afficher on veux donc changer l'affichage de la classe
            } else if (src.getText().contains("Afficher")) {
                // on cree la vue contenant les checkbox
                VueCheckClass ch = new VueCheckClass(this);
                if (MenuTemp != null) ((Pane) (src.getParent().getParent())).getChildren().remove(MenuTemp); // on retire le menu temporaire de la vueCentre s'il existe deja
                if (affichageCoteDroit(src)) {
                    //on test dans quelle moitier de l'application se trouve le bouton pour afficher le menu supplementaire du cote le plus ergonomique
                    ch.setLayoutX(src.getParent().getLayoutX() + src.getWidth());
                } else {
                    ch.setLayoutX(src.getParent().getLayoutX() - ch.getWidth());
                }
                ch.setLayoutY(src.getParent().getLayoutY());
                // ajoute de la nouvelle vue au Pane principale
                ((Pane) (src.getParent().getParent())).getChildren().add(ch);
                for (Node node : ch.getChildren()) {
                    // on coche ou non les checkbox selon l'etat de la classe
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
                        case "dépendances":
                            check.setSelected(classe.isAfficheDependances());
                            break;
                    }
                }
                MenuTemp = ch;
                // cas ou on a cliquer sur le bouton classe externe
            } else if (src.getText().equals("Classes externes")) {
                // recuperation du pane central
                final Pane pane = (Pane) src.getParent().getParent();
                // on retire le menu temporaire de la vueCentre s'il existe deja
                if (MenuTemp != null) ((Pane) (src.getParent().getParent())).getChildren().remove(MenuTemp);
                //creation du menu contextuel contenant les classes externe
                VueSousMenuClassExt.classeExt.setLayoutY(src.getParent().getLayoutY());
                // on ajoute a la classe a laquelle le menu est lie la nouvelle vue comme observateur
                classe.ajouterObservateur(VueSousMenuClassExt.classeExt);
                // ajout au Pane central de la nouvelle vue
                pane.getChildren().add(VueSousMenuClassExt.classeExt);
                VueSousMenuClassExt.classeExt.notifier(classe);
                // selection de l'affichage du cote le plus ergonomique
                if (affichageCoteDroit(src)) {
                    VueSousMenuClassExt.classeExt.setLayoutX(src.getParent().getLayoutX() + src.getWidth());
                } else {
                    VueSousMenuClassExt.classeExt.setLayoutX(src.getParent().getLayoutX() - VueSousMenuClassExt.classeExt.getWidth());
                }
                MenuTemp = VueSousMenuClassExt.classeExt;
                // cas du bouton nouvelle dependance
            } else if (src.getText().equals("Nouvelle dépendance")) {
                // recuperation du pane central
                final Pane pane = (Pane) src.getParent().getParent();
                // on retire le menu temporaire de la vueCentre s'il existe deja
                if (MenuTemp != null) ((Pane) (src.getParent().getParent())).getChildren().remove(MenuTemp);
                //positionnement, ajout et observation de la vueDependance
                VueDependences.vueDependences.setLayoutY(src.getParent().getLayoutY());
                classe.ajouterObservateur(VueDependences.vueDependences);
                pane.getChildren().add(VueDependences.vueDependences);
                VueDependences.vueDependences.notifier(classe);
                //positionement du cote ergonomique
                if (affichageCoteDroit(src)) {
                    VueDependences.vueDependences.setLayoutX(src.getParent().getLayoutX() + src.getWidth());
                } else {
                    VueDependences.vueDependences.setLayoutX(src.getParent().getLayoutX() - VueDependences.vueDependences.getWidth());
                }
                MenuTemp = VueDependences.vueDependences;
                // cas du bouton supprimer la classe
            } else if (src.getText().equals("Supprimer la classe")) {
                //on supprime la classe et ses fleches du model
                Model.getModel().getClasses().remove(classe);
                Model.getModel().getFleches().removeAll(Model.getModel().findFleche(classe));
                // recuperation de la vueCentre
                VueCentre vc = (VueCentre) src.getParent().getParent();
                // recuperation de l'arboressance sur le bord pour gerer les checkbox
                VueFichiers vf = (VueFichiers) ((BorderPane) vc.getParent()).getLeft();
                HBox treeI = vf.findBoxRelativeToClasse(classe);
                if (treeI != null) ((CheckBox) treeI.getChildrenUnmodifiable().get(0)).setSelected(false);
                vc.getChildren().remove(vc.findVueClasse(classe));
                // suppression des menusTep, des fleches et actualisation des fleches
                vc.supprimerMenusTemp();
                vc.supprimerFleches();
                for (Fleche f : Model.getModel().getFleches()) {
                    if (f.isAffiche()) vc.getChildren().add(f.getFabrique().fabriquer());
                }
                // cas du bouton generer squelette
            } else if (src.getText().equals("Générer squelette")) {
                // choix du repertoire d'enregistrement
                DirectoryChooser chooser = new DirectoryChooser();
                File file = chooser.showDialog(null);
                // initialisation des donnees
                String nomClasse = classe.getType().substring(classe.getType().lastIndexOf(" "));
                if (file == null) return;
                if(!file.isDirectory()) return;
                file = new File(file.getAbsolutePath() + "\\" + nomClasse + ".java");
                // ecriture dans le fichier
                try {
                    if (!file.exists()) file.createNewFile();
                    BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                    bw.write(classe.genSquelette());
                    bw.close();
                } catch(Exception ignored) {}
            } else {
                // dernier boutton, appartion de la vue pour creer une attribut ou une methode
                VueCreation v;
                //test pour savoir si c'est une methode ou un attribut
                if (src.getText().contains("methode")) v = new VueCreation("methode", this);
                else v = new VueCreation("attribut", this);
                // affichage du cote  ergonomique
                if (affichageCoteDroit(src)) {
                    v.setLayoutX(src.getParent().getLayoutX() + src.getWidth());
                } else {
                    v.setLayoutX(src.getParent().getLayoutX() - v.getWidth());
                }
                // ajout dans le pane
                ((Pane) (src.getParent().getParent())).getChildren().add(v);
                v.setLayoutY(src.getParent().getLayoutY());
                if (MenuTemp != null) ((Pane) (src.getParent().getParent())).getChildren().remove(MenuTemp);
                MenuTemp = v;
            }


        } else {
            // =si la source n'est pas un bouton c'est un checkBox, on veux donc modifier l'affichage de la classe
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
                case "dépendances":
                    // dans le cas des dependances il faut actualiser les fleches
                    classe.setAfficheDependances(src.isSelected());
                    Fleche.actualiserFleches((VueCentre) src.getParent().getParent());
                    break;
            }
        }

    }
}
