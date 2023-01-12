package com.example.objectaid_sae.vue.fabriqueFleches;

import com.example.objectaid_sae.model.Fleche;
import com.example.objectaid_sae.vue.VueCentre;
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

    public abstract VueFleche fabriquer();

    protected VueFleche build(Line l, Polygon poly, double inclinaison, double offset, double x, double y, double len){
        VueFleche res = new VueFleche();
        Rotate r = new Rotate();
        Label name = new Label(fleche.getNom());
        Label card = new Label(fleche.getCardinalites());
        card.setTranslateX(15);
        card.setTranslateY(offset);
        r.setPivotX(0);
        r.setPivotY(0);
        r.setAngle(inclinaison);
        l.setTranslateY(offset);
        poly.setRotate(45);
        poly.setTranslateY(offset+5);
        poly.setTranslateX(-10);
        card.setRotate(-inclinaison);
        name.setRotate(-inclinaison);
        name.setAlignment(Pos.CENTER);
        res.getChildren().addAll(poly,l,name,card);
        res.setTranslateY(y + fleche.getOffsetLateral());
        res.setTranslateX(x + fleche.getOffsetLateral());
        name.setTranslateY(len/2);
        res.getTransforms().add(r);
        return res;
    }
    protected double getOffset(double angle, double width, double height){
        double breakpoint = Math.sqrt((width*width)/4 + (height*height)/4) - fleche.getOffsetLateral();
        double absAngle = Math.abs((angle - 90 % 180) / (180 / Math.PI));
        double cos = Math.cos(absAngle);
        double offset;
        if (cos != 0) offset = Math.abs((width/2)/cos);
        else offset =  Math.abs(height/2/Math.sin(absAngle));
        if (offset > breakpoint) offset = Math.abs(height/2/Math.sin(absAngle));
        return offset;
    }
}
