package com.example.objectaid_sae.vue.fabriqueFleches;

import com.example.objectaid_sae.model.Fleche;
import com.example.objectaid_sae.vue.VueCentre;
import com.example.objectaid_sae.vue.VueClasse;
import com.example.objectaid_sae.vue.VueFleche;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;

public class FabriqueVueFlecheUtilisation extends FabriqueVueFleche{

    /**
     * methode genererPointe, generer la pointe d ela fleche
     * @return Polygon pointe de la fleche
     */
    public Polygon genererPointe(){
        Polygon poly = new Polygon(0.0, 0.0, 0.0, 20.0, 2.0, 2.0, 20.0, 0.0);
        poly.setFill(Paint.valueOf("black"));
        poly.setStroke(Paint.valueOf("black"));
        return poly;
    }
    /**
     * methode genererLigne, sert a generer la ligne pour la fleche
     * @param len correspond a la longueur de la fleche
     * @return Line, ligne de la longueur correspondante
     */

    public Line genererLigne(double len){
        return  new Line(0, 0, 0, len);
    }

    /**
     * constructeur de FabriqueVueFlecheUtilisation
     * @param f Fleche servant de model
     * @param centre Vue ou ajouter la fleche apres creation
     */
    public FabriqueVueFlecheUtilisation(Fleche f, VueCentre centre){
        super(f,centre);
    }
    /**
     * methode fabriquer retourne la VueFleche correspondant a la fleche en attribut
     * @return VueFleche contenant la fleche placee au bon endroit dans la vueCentre
     */
    @Override
    public VueFleche fabriquer() {
        return faireFleche(fleche.getDepart().getX(), fleche.getDepart().getY(), fleche.getArrivee().getX(), fleche.getArrivee().getY());
    }

    /**
     * methode Build initialise et assemble la VueFleche avec tous ses elements
     * @param l Ligne correspondant au corps de la fleche
     * @param poly Polygone correspondant a la pointe de la fleche
     * @param inclinaison angle de la fleche par rapport au plan vertical
     * @param offset distance par rapport au centre de la classe d'arrivée de la fleche
     * @param x coordonnee x du centre de la classe d'arrivee de la fleche
     * @param y coordonnee y du centre de la classe d'arrivee de la fleche
     * @param len longueur de de la fleche
     * @param decall decallage sur le cote de la fleche, evite la superposition de fleches
     * @return VueFleche, la vueFleche correspondant a la fleche en attribut
     */
    protected VueFleche build(Line l, Polygon poly, double inclinaison, double offset, double x, double y, double len, double decall){
        // creation de la base de la fleche
        VueFleche res = super.build(l,poly,inclinaison,offset,x,y,len,decall);
        // initialisation des objets visuels
        String attribut = fleche.getNom();
        Label name = new Label(attribut.substring(attribut.lastIndexOf(" ")+1));
        Label card = new Label(fleche.getCardinalites());
        HBox hb = new HBox();
        ImageView iv = getImage(attribut);
        // ajout et placement des nouveaux objets
        if (iv != null) hb.getChildren().addAll(iv,name);
        card.setTranslateX(15);
        card.setTranslateY(offset);
        card.setRotate(-inclinaison);
        hb.setRotate(-inclinaison);
        hb.setAlignment(Pos.CENTER);
        res.getChildren().addAll(hb,card);
        hb.setTranslateY(len/2);
        return res;
    }

    private ImageView getImage(String attribut){
        Image i = null;
        if (attribut.contains("+")) i = VueClasse.imgPublic;
        else if (attribut.contains("-")) i = VueClasse.imgPrivate;
        else if(attribut.contains("#")) i = VueClasse.imgProtected;
        ImageView iv = null;
        if (i!= null) {
            iv = new ImageView(i);
            iv.setFitHeight(10);
            iv.setFitWidth(10);
        }
        return iv;
    }

}
