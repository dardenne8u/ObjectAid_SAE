package com.example.objectaid_sae.vue.fabriqueFleches;

import com.example.objectaid_sae.model.Fleche;
import com.example.objectaid_sae.vue.VueClasse;
import com.example.objectaid_sae.vue.VueFleche;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;

public class FabriqueVueFlecheImplement extends FabriqueVueFleche {

    Fleche fleche;
    FabriquePolyBlanc fbpoly;


    public FabriqueVueFlecheImplement(Fleche f){
        fleche = f;
        fbpoly = new FabriquePolyBlanc(0,-10,0,-10);
    }
    @Override
    public VueFleche fabriquer() {
        return faireFleche(fleche.getDepart().getX(), fleche.getDepart().getY(), fleche.getArrivee().getX(), fleche.getArrivee().getY());
    }

    private VueFleche faireFleche(double x1, double y1, double x2, double y2){
        VueClasse vc1 = new VueClasse(fleche.getDepart());
        VueClasse vc2 = new VueClasse(fleche.getArrivee());
        vc1.notifier(fleche.getDepart());
        vc2.notifier(fleche.getArrivee());
        double widthC1 = vc1.getWidth();
        double len = Math.sqrt(Math.pow((x1-x2),2)+Math.pow((y1-y2),2));
        double inclinaison = Math.atan2(y2 - y1, x2 - x1)* 180/Math.PI + 90;
        double offset = (widthC1/2)/Math.cos(Math.abs(((inclinaison-90)%180)/(180/Math.PI)));
        if (x1>x2) offset = -offset;
        FabriqueLignePoint fbLigne = new FabriqueLignePoint(0,0,0,len-(2*offset));
        return build(fbLigne, fbpoly,inclinaison,offset,x2,y2);
    }
}
