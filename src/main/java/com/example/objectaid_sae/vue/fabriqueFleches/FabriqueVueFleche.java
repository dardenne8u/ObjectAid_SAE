package com.example.objectaid_sae.vue.fabriqueFleches;

import com.example.objectaid_sae.model.Fleche;
import com.example.objectaid_sae.vue.VueCentre;
import com.example.objectaid_sae.vue.VueFleche;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;

public abstract class FabriqueVueFleche {

    protected Fleche fleche;
    protected VueCentre centre;
    public FabriqueVueFleche(Fleche f, VueCentre centre){
        fleche = f;
        this.centre = centre;
    }

    public abstract VueFleche fabriquer();

    protected VueFleche build(Line l, Polygon poly, double inclinaison, double offset, double x, double y, double len){
        VueFleche res = new VueFleche();
        Rotate r = new Rotate();
        Label name = new Label(fleche.getCardinalites()[0] + fleche.getNom() + fleche.getCardinalites()[1]);
        r.setPivotX(0);
        r.setPivotY(0);
        r.setAngle(inclinaison);
        l.setTranslateY(offset);
        poly.setRotate(45);
        poly.setTranslateX(-10);
        poly.setTranslateY(offset);
        name.setRotate(-inclinaison);
        name.setAlignment(Pos.CENTER);
        res.getChildren().add(poly);
        res.getChildren().add(l);
        res.setTranslateY(y);
        res.setTranslateX(x);
        name.setTranslateY(len/2);
        res.getTransforms().add(r);
        res.getChildren().add(name);
        return res;
    }
    protected double getOffset(double angle, double width, double height){
        double breakpoint = Math.sqrt((width*width)/4 + (height*height)/4);
        double absAngle = Math.abs((angle - 90 % 180) / (180 / Math.PI));
        double cos = Math.cos(absAngle);
        double offset;
        if (cos != 0) offset = Math.abs((width/2)/cos);
        else offset =  Math.abs(height/2/Math.sin(absAngle));
        if (offset > breakpoint) offset = Math.abs(height/2/Math.sin(absAngle));
        return offset;
    }
}
