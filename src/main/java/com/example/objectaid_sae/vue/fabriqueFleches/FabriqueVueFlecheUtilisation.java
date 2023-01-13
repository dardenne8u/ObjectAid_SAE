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
    public Polygon genererPointe(){
        Polygon poly = new Polygon(0.0, 0.0, 0.0, 20.0, 2.0, 2.0, 20.0, 0.0);
        poly.setFill(Paint.valueOf("black"));
        poly.setStroke(Paint.valueOf("black"));
        return poly;
    }

    public Line genererLigne(double len){
        return  new Line(0, 0, 0, len);
    }
    public FabriqueVueFlecheUtilisation(Fleche f, VueCentre centre){
        super(f,centre);
    }
    @Override
    public VueFleche fabriquer() {
        return faireFleche(fleche.getDepart().getX(), fleche.getDepart().getY(), fleche.getArrivee().getX(), fleche.getArrivee().getY());
    }

    protected VueFleche build(Line l, Polygon poly, double inclinaison, double offset, double x, double y, double len, double decall){
        VueFleche res = super.build(l,poly,inclinaison,offset,x,y,len,decall);
        String attribut = fleche.getNom();
        Label name = new Label(attribut.substring(attribut.lastIndexOf(" ")+1));
        Label card = new Label(fleche.getCardinalites());
        HBox hb = new HBox();
        ImageView iv = getImage(attribut);
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
