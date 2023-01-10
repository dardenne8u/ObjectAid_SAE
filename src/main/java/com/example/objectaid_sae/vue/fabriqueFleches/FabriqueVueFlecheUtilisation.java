package com.example.objectaid_sae.vue.fabriqueFleches;

import com.example.objectaid_sae.model.Fleche;
import com.example.objectaid_sae.vue.VueCentre;
import com.example.objectaid_sae.vue.VueClasse;
import com.example.objectaid_sae.vue.VueFleche;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;

public class FabriqueVueFlecheUtilisation extends FabriqueVueFleche{

    FabriquePoly4pts fbpoly;



    public FabriqueVueFlecheUtilisation(Fleche f, VueCentre centre){
        super(f,centre);
        fbpoly = new FabriquePoly4pts();

    }
    @Override
    public VueFleche fabriquer() {
        return faireFleche(fleche.getDepart().getX(), fleche.getDepart().getY(), fleche.getArrivee().getX(), fleche.getArrivee().getY());
    }

    private VueFleche faireFleche(double x1, double y1, double x2, double y2){
        VueClasse vc1 = (VueClasse) centre.getChildren().get(centre.getChildren().indexOf(new VueClasse(fleche.getDepart())));
        VueClasse vc2 = (VueClasse) centre.getChildren().get(centre.getChildren().indexOf(new VueClasse(fleche.getArrivee())));
        vc2.notifier(fleche.getArrivee());
        double widthC1 = vc1.getWidth();
        double widthC2 = vc2.getWidth();
        double heightC1 = vc1.getHeight();
        double heightC2 = vc2.getHeight();
        double len = Math.sqrt(Math.pow((x1-x2),2)+Math.pow((y1-y2),2));
        double inclinaison = Math.atan2(y2 - y1, x2 - x1)* 180/Math.PI + 90;
        double offsetC1 = getOffset(inclinaison,widthC1,  heightC1);
        double offsetC2 = getOffset(inclinaison,widthC2,heightC2);
        FabriqueLignePleine fbLigne = new FabriqueLignePleine(0,0,0,len-(offsetC1 + offsetC2));
        return build(fbLigne, fbpoly,inclinaison,offsetC2,x2,y2,len);
    }
}
