package com.example.objectaid_sae.vue;

import com.example.objectaid_sae.controleur.ControleurClasseCliquer;
import com.example.objectaid_sae.controleur.ControleurClasseGlissee;
import com.example.objectaid_sae.model.Classe;
import com.example.objectaid_sae.observateur.Observateur;
import com.example.objectaid_sae.observateur.Sujet;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.List;
import java.util.Map;

public class VueClasse extends VBox implements Observateur {

    private ControleurClasseGlissee controleurClasseGlissee;

    private static final Image imgClasse = new Image(VueClasse.class.getResource("/img/Class.png").toExternalForm());
    private static final Image imgAbstract = new Image(VueClasse.class.getResource("/img/Abstract.png").toExternalForm());
    private static final Image imgInterface = new Image(VueClasse.class.getResource("/img/Interface.png").toExternalForm());
    public static final Image imgPublic = new Image(VueClasse.class.getResource("/img/plus.png").toExternalForm());
    public static final Image imgPrivate = new Image(VueClasse.class.getResource("/img/moin.png").toExternalForm());
    public static final Image imgProtected = new Image(VueClasse.class.getResource("/img/pr.png").toExternalForm());

    /**
     * Constructeur creant une VueClasse et la liant aux controleurs associes
     *
     * @param classe, le sujet de la classe a afficher
     */
    public VueClasse(Classe classe) {
        this.controleurClasseGlissee = new ControleurClasseGlissee(classe);
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, new ControleurClasseCliquer(classe));
        this.addEventHandler(MouseEvent.MOUSE_DRAGGED, controleurClasseGlissee);
        classe.ajouterObservateur(this);
        notifier(classe);
    }

    public HBox visibilite(String value) {
        HBox boite = new HBox();
        String[] table = value.split(" ");
        boolean pub = true;
        boolean pri = false;
        boolean pro = false;
        for (int i = 0; i < table.length; i++) {
            if (table[i].contains("-")) {
                pri = true;
                pub = false;
                break;
            } else if (table[i].contains("#")) {
                pub = false;
                pro = true;
                break;
            }
        }
        ImageView iv;
        Label txt = new Label(value);
        if (pub) {
            iv = new ImageView(imgPublic);
            if (value.contains("+")) txt = new Label(value.replace("+", ""));
        } else if (pri) {
            iv = new ImageView(imgPrivate);
            txt = new Label(value.replace("-", ""));
        } else {
            iv = new ImageView(imgProtected);
            txt = new Label(value.replace("#", ""));
        }
        txt.setWrapText(true);
        txt.setPadding(new Insets(0, 0, 0, 3.0));
        iv.setFitHeight(10);
        iv.setFitWidth(10);
        iv.setPreserveRatio(true);
        boite.getChildren().addAll(iv, txt);

        return boite;
    }

    /**
     * methode mettant a jour l'affichage dans la vue
     *
     * @param s, la classe a afficher
     */
    @Override
    public void notifier(Sujet s) {
        Classe classe = (Classe) s;
        this.getChildren().clear();
        this.setMaxWidth(200);
        this.setWidth(this.getMaxWidth());
        this.setStyle("-fx-background-color:#b0c9cc; -fx-border-color: black");
        this.setAlignment(Pos.CENTER_LEFT);

        this.setSpacing(8);
        setLayoutX(classe.getX());
        setLayoutY(classe.getY());


        // PREMIERE PARTIE : TITRE

        String typeTitre = classe.getType();
        String[] table = typeTitre.split(" ");
        boolean inter = false;
        boolean abst = false;
        for (int i = 0; i < table.length; i++) {
            if (table[i].contains("abstract")) {
                abst = true;
                break;
            } else if (table[i].contains("interface")) {
                inter = true;
                break;
            }
        }
        ImageView imgVTop;
        if (inter) imgVTop = new ImageView(imgInterface);
        else if (abst) imgVTop = new ImageView(imgAbstract);
        else imgVTop = new ImageView(imgClasse);
        imgVTop.setFitHeight(20);
        imgVTop.setFitWidth(20);
        imgVTop.setPreserveRatio(true);

        HBox top = new HBox();
        top.setPadding(new Insets(5, 0, 0, 5));
        Label titre = new Label(" " + table[table.length - 1]);
        titre.setPadding(new Insets(3.0));
        titre.setAlignment(Pos.CENTER);
        top.getChildren().addAll(imgVTop, titre);
        this.getChildren().addAll(top, this.separer());

        //DEUXIEME PARTIE : ATTRIBUTS
        Map<Integer, List<String>> attributsMap = classe.getAttributs();
        if (classe.isAfficheAttributsDeclare()) this.afficherContenant(attributsMap, Classe.DECLARED);
        if (classe.isAfficheAttributsHerite()) this.afficherContenant(attributsMap, Classe.HERITED);
        this.getChildren().add(this.separer());


        //TROISIEME PARTIE : METHODES ET CONSTRUCTEURS
        if (classe.isAfficheConstructeur()) {
            for (String constructeur : classe.getConstructeurs()) {
                HBox box = visibilite(constructeur);
                // Label elem = new Label(constructeur);
                box.setPadding(new Insets(0, 0, 0, 3.0));
                // elem.setWrapText(true);
                this.getChildren().add(box);
            }
        }


        Map<Integer, List<String>> methodesMap = classe.getMethodes();
        if (classe.isAfficheMethodeDeclare()) this.afficherContenant(methodesMap, Classe.DECLARED);
        if (classe.isAfficheMethodeHerite()) this.afficherContenant(methodesMap, Classe.HERITED);

        setLayoutX(classe.getX());
        setLayoutY(classe.getY());
    }

    /**
     * methode creant un rectangle fin de séparation
     *
     * @return le rectangle de séparation
     */
    public Rectangle separer() {
        Rectangle rec = new Rectangle(150, 1, Color.BLACK);
        rec.setWidth(this.getWidth());
        return rec;
    }


    public void afficherContenant(Map<Integer, List<String>> map, int typeAttribut) {
        if (map != null) {

            List<String> element = map.get(typeAttribut);
            for (String elemC : element) {
                HBox elem = visibilite(elemC);
                //Label elem = new Label(elemC);
                elem.setPadding(new Insets(0, 0, 0, 3.0));
                // elem.setTextFill(Paint.valueOf("black"));
                // elem.setWrapText(true);
                this.getChildren().add(elem);
            }

        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != getClass()) return false;
        return (((VueClasse) obj).controleurClasseGlissee.getClasse().getType().equals(controleurClasseGlissee.getClasse().getType()));
    }

    public boolean isForClasse(Classe classe) {
        return controleurClasseGlissee.getClasse().getType().equals(classe.getType());
    }
}
