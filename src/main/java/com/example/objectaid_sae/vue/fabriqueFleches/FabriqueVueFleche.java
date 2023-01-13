package com.example.objectaid_sae.vue.fabriqueFleches;

import com.example.objectaid_sae.model.Fleche;
import com.example.objectaid_sae.vue.VueCentre;
import com.example.objectaid_sae.vue.VueClasse;
import com.example.objectaid_sae.vue.VueFleche;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;

import java.util.Arrays;

public abstract class FabriqueVueFleche {

    /**
     * attributs de la classe
     * attribut fleche stock la fleche qui sert de model a la fabrique
     * attribut centre stock la VueCentre oo ajouter la fleche
     */
    protected Fleche fleche;
    protected VueCentre centre;

    /**
     * constructeur de FabriqueVueFleche
     * @param f Fleche servant de model
     * @param centre Vue ou ajouter la fleche apres creation
     */
    public FabriqueVueFleche(Fleche f, VueCentre centre){
        fleche = f;
        this.centre = centre;
    }

    /**
     * methode genererLigne, sert a generer la ligne pour la fleche
     * @param len correspond a la longueur de la fleche
     * @return Line, ligne de la longueur correspondante
     */
    public abstract Line genererLigne(double len);

    /**
     * methode genererPointe, generer la pointe d ela fleche
     * @return Polygon pointe de la fleche
     */
    public abstract Polygon genererPointe();

    /**
     * methode fabriquer retourne la VueFleche correspondant a la fleche en attribut
     * @return VueFleche contenant la fleche placee au bon endroit dans la vueCentre
     */
    public abstract VueFleche fabriquer();

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
        // initialisation des objets visuels
        VueFleche res = new VueFleche();
        Rotate r = new Rotate();
        // creation de l'objet de rotation
        r.setPivotX(0);
        r.setPivotY(0);
        r.setAngle(inclinaison);
        // placement des objets dans la VueFleche et inclinaison des elements
        l.setTranslateY(offset);
        l.setTranslateX(decall);
        poly.setRotate(45);
        poly.setTranslateY(offset+5);
        poly.setTranslateX(-10 + decall);
        res.getChildren().addAll(poly,l);
        res.setTranslateY(y);
        res.setTranslateX(x);
        res.getTransforms().add(r);
        return res;
    }

    /**
     * methode getOffset, calcule la distance entre le bord de la Vueclasse et son centre
     * @param angle angle de la fleche
     * @param width largeur de la classe
     * @param height hauteur de la classe
     * @param decallage decallage de la fleche sur le cote
     * @return VueFleche, la vueFleche correspondant a la fleche en attribut
     */
    protected double getOffset(double angle, double width, double height, double decallage){
        // definition de la longueur a partir de laquelle on est plus au bord de la classe
        double breakpoint = Math.sqrt((width*width)/4 + (height*height)/4) - decallage;
        // clacule de l'angle et de ses cos et sin
        double absAngle = Math.abs((angle) % 180) / (180 / Math.PI);
        double cos = Math.cos(absAngle);
        double sin = Math.sin(absAngle);
        // passage du decallage en negatif
        decallage = Math.abs(decallage)*-1;
        double offset;
        // clacule de la distance entre le debut de la fleche et le centre de la classe
        if (cos != 0) offset = Math.abs(((width/2)+decallage)/cos) + 2*sin*decallage;
        else offset =  Math.abs(((height/2))/Math.sin(absAngle)) + 2*cos*decallage;
        if (offset > breakpoint) offset = Math.abs(((height/2))/Math.sin(absAngle))+ 2*cos*decallage;
        return offset;
    }

    /**
     * methode faireFleche, clacule les données de la fleche avant d'appeler la methode build pour assembler les composants
     * @param x1 coordonnee x du centre de la classe de depart
     * @param y1 coordonnee y du centre de la classe de depart
     * @param x2 coordonnee x du centre de la classe d'arrivee
     * @param y2 coordonnee y du centre de la classe d'arrivee
     * @return VueFleche, la vueFleche correspondant a la fleche en attribut
     */
    protected VueFleche faireFleche(double x1, double y1, double x2, double y2){
        // recuperation des vues des classes concernées
        VueClasse vc1 = (VueClasse) centre.getChildren().get(centre.getChildren().indexOf(new VueClasse(fleche.getDepart())));
        VueClasse vc2 = (VueClasse) centre.getChildren().get(centre.getChildren().indexOf(new VueClasse(fleche.getArrivee())));
        //recuperation des tailles
        double widthC1 = vc1.getWidth();
        double widthC2 = vc2.getWidth();
        double heightC1 = vc1.getHeight();
        double heightC2 = vc2.getHeight();
        // calcule des valeurs pour creer la ligne
        double len = Math.sqrt(Math.pow((x1-x2),2)+Math.pow(((y1+vc1.getHeight()/2)-(y2+ vc2.getHeight()/2)),2));
        double inclinaison = Math.atan2((y2 + vc2.getHeight()/2) - (y1+vc1.getHeight()/2), x2 - x1)* 180/Math.PI;
        double decallage = fleche.getOffsetLateral();
        double offsetC1 = getOffset(inclinaison,widthC1,heightC1, decallage);
        double offsetC2 = getOffset(inclinaison,widthC2,heightC2, decallage);
        // generation de la Vue
        return build(genererLigne(len-(offsetC1 + offsetC2)), genererPointe() ,inclinaison+90,offsetC2,x2 + widthC2/2,y2 + heightC2/2,len, decallage);
    }
}
