package com.example.objectaid_sae.vue;

import com.example.objectaid_sae.controleur.ControleurClasseCliquer;
import com.example.objectaid_sae.controleur.ControleurClasseGlissee;
import com.example.objectaid_sae.model.Classe;
import com.example.objectaid_sae.observateur.Observateur;
import com.example.objectaid_sae.observateur.Sujet;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.List;
import java.util.Map;

public class VueClasse extends VBox implements Observateur {

    private ControleurClasseGlissee controleurClasseGlissee;

    /**
     * Constructeur creant une VueClasse et la liant aux controleurs associes
     * @param classe, le sujet de la classe a afficher
     */
    public VueClasse (Classe classe){
        this.controleurClasseGlissee = new ControleurClasseGlissee(classe);
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, new ControleurClasseCliquer(classe));
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, controleurClasseGlissee);
        this.addEventHandler(MouseEvent.MOUSE_DRAGGED, controleurClasseGlissee);
        classe.ajouterObservateur(this);
        notifier(classe);
    }


    /**
     * methode mettant a jour l'affichage dans la vue
     * @param s, la classe a afficher
     */
    @Override
    public void notifier(Sujet s) {
        Classe classe = (Classe) s;
        this.getChildren().clear();
        this.setMaxWidth(200);
        this.setWidth(this.getMaxWidth());
        this.setStyle("-fx-background-color:#D3D3D3");
        this.setAlignment(Pos.CENTER_LEFT);
        this.setSpacing(8);
        setLayoutX(classe.getX());
        setLayoutY(classe.getY());


        // PREMIERE PARTIE : TITRE
        Label titre = new Label(classe.getType());
         this.getChildren().addAll(titre, this.separer());

         //DEUXIEME PARTIE : ATTRIBUTS
        Map<Integer, List<String>> attributsMap = classe.getAttributs();
        if (classe.isAfficheAttributsDeclare()) this.afficherContenant(attributsMap, Classe.DECLARED );
        if (classe.isAfficheAttributsHerite()) this.afficherContenant(attributsMap, Classe.HERITED );
        this.getChildren().add(this.separer());


        //TROISIEME PARTIE : METHODES ET CONSTRUCTEURS
        if(classe.isAfficheConstructeur()) {
            for(String constructeur: classe.getConstructeurs()) {
                Label elem = new Label(constructeur);
                elem.setWrapText(true);
                this.getChildren().add(elem);
            }
        }


        Map<Integer, List<String>> methodesMap = classe.getMethodes();
        if (classe.isAfficheMethodeDeclare()) this.afficherContenant(methodesMap, Classe.DECLARED);
        if (classe.isAfficheMethodeHerite()) this.afficherContenant(methodesMap, Classe.HERITED);

        this.getChildren().add(this.separer());

        this.controleurClasseGlissee.set((250-this.getWidth())+(2*this.getWidth()));
    }

    /**
     * methode creant un rectangle fin de séparation
     * @return le rectangle de séparation
     */
    public Rectangle separer(){
        Rectangle rec = new Rectangle(150, 2, Color.BLACK);
        rec.setWidth(this.getWidth());
        return rec;
    }


    public void afficherContenant(Map<Integer, List<String>> map, int typeAttribut){
       if (map != null) {

           List<String> element = map.get(typeAttribut);
           for (String elemC : element) {
               Label elem = new Label(elemC);
               elem.setWrapText(true);
               this.getChildren().add(elem);
           }

       }


    }
}
