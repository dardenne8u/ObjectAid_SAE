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

    protected Fleche fleche;
    protected VueCentre centre;
    public FabriqueVueFleche(Fleche f, VueCentre centre){
        fleche = f;
        this.centre = centre;
    }

    public abstract Line genererLigne(double len);

    public abstract Polygon genererPointe();

    public abstract VueFleche fabriquer();

    protected VueFleche build(Line l, Polygon poly, double inclinaison, double offset, double x, double y, double len, double decall){
        VueFleche res = new VueFleche();
        Rotate r = new Rotate();
        r.setPivotX(0);
        r.setPivotY(0);
        r.setAngle(inclinaison);
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
    protected double getOffset(double angle, double width, double height, double decallage){
        double breakpoint = Math.sqrt((width*width)/4 + (height*height)/4) - decallage;
        double absAngle = Math.abs((angle) % 180) / (180 / Math.PI);
        double cos = Math.cos(absAngle);
        double sin = Math.sin(absAngle);
        decallage = Math.abs(decallage)*-1;
        double offset;
        if (cos != 0) offset = Math.abs(((width/2)+decallage)/cos) + 2*sin*decallage;
        else offset =  Math.abs(((height/2))/Math.sin(absAngle)) + 2*cos*decallage;
        if (offset > breakpoint) offset = Math.abs(((height/2))/Math.sin(absAngle))+ 2*cos*decallage;
        return offset;
    }

    protected VueFleche faireFleche(double x1, double y1, double x2, double y2){
        VueClasse vc1 = (VueClasse) centre.getChildren().get(centre.getChildren().indexOf(new VueClasse(fleche.getDepart())));
        VueClasse vc2 = (VueClasse) centre.getChildren().get(centre.getChildren().indexOf(new VueClasse(fleche.getArrivee())));
        double widthC1 = vc1.getWidth();
        double widthC2 = vc2.getWidth();
        double heightC1 = vc1.getHeight();
        double heightC2 = vc2.getHeight();
        double len = Math.sqrt(Math.pow((x1-x2),2)+Math.pow(((y1+vc1.getHeight()/2)-(y2+ vc2.getHeight()/2)),2));
        double inclinaison = Math.atan2((y2 + vc2.getHeight()/2) - (y1+vc1.getHeight()/2), x2 - x1)* 180/Math.PI;
        double decallage = fleche.getOffsetLateral();
        double offsetC1 = getOffset(inclinaison,widthC1,heightC1, decallage);
        double offsetC2 = getOffset(inclinaison,widthC2,heightC2, decallage);
        return build(genererLigne(len-(offsetC1 + offsetC2)), genererPointe() ,inclinaison+90,offsetC2,x2 + widthC2/2,y2 + heightC2/2,len, decallage);
    }
}
