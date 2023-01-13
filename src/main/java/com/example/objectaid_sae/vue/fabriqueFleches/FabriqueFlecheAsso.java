package com.example.objectaid_sae.vue.fabriqueFleches;

import com.example.objectaid_sae.model.Fleche;
import com.example.objectaid_sae.vue.VueCentre;
import com.example.objectaid_sae.vue.VueFleche;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

public class FabriqueFlecheAsso extends FabriqueVueFleche{

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
        Line line = new Line(0, 0, 0, len);
        line.getStrokeDashArray().addAll(10.0);
        return line;
    }

    /**
     * constructeur de FabriqueFlecheAsso
     * @param f Fleche servant de model
     * @param centre Vue ou ajouter la fleche apres creation
     */
    public FabriqueFlecheAsso(Fleche f, VueCentre centre) {
        super(f, centre);
    }

    /**
     * methode fabriquer retourne la VueFleche correspondant a la fleche en attribut
     * @return VueFleche contenant la fleche placee au bon endroit dans la vueCentre
     */
    @Override
    public VueFleche fabriquer() {
        return faireFleche(fleche.getDepart().getX(), fleche.getDepart().getY(), fleche.getArrivee().getX(), fleche.getArrivee().getY());
    }
}
