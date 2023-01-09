package com.example.objectaid_sae.vue.fabriqueFleches;

import com.example.objectaid_sae.vue.VueFleche;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;

public abstract class FabriqueVueFleche {
    public abstract VueFleche fabriquer();

    protected VueFleche build(FabriqueLigne fbLigne, FabriquePolygone fbpoly, double inclinaison, double offset, double x, double y){
        VueFleche res = new VueFleche();
        Polygon poly;
        poly = fbpoly.fabriquer();
        Line l = fbLigne.fabriquer();
        Rotate r = new Rotate();
        r.setPivotX(0);
        r.setPivotY(0);
        r.setAngle(inclinaison);
        l.setLayoutY(offset);
        poly.setRotate(45);
        poly.setLayoutX(-10);
        poly.setLayoutY(offset);
        res.getChildren().add(poly);
        res.getChildren().add(l);
        res.setLayoutY(y);
        res.setLayoutX(x);
        res.getTransforms().add(r);
        return res;
    }
}
