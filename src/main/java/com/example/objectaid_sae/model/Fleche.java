package com.example.objectaid_sae.model;

import com.example.objectaid_sae.vue.VueCentre;
import com.example.objectaid_sae.vue.fabriqueFleches.*;

public class Fleche {

    //ATTRIBUTS
    private final String type, nom;
    private final Classe arrivee;
    private final Classe depart;

    private String cardinalites;
    private int offsetLateral;

    private FabriqueVueFleche fabrique;

    //CONSTRUCTEURS

    /**
     * constructeur qui cree un modele fleche enregistrant la classe de depart et la classe d'arrivee
     * de la fleche ainsi que son type. La classe d'arrivee est celle qui aura visuellement
     * la pointe de la fleche
     *
     * @param debut la classe de debut
     * @param fin   la classe de fin
     * @param type  le type de fleche
     */
    public Fleche(Classe debut, Classe fin, String type, String nom, VueCentre centre) {
        this.type = type;
        this.arrivee = fin;
        this.depart = debut;
        this.nom = nom;
        this.cardinalites = "";
        setFabrique(centre);
        offsetLateral = (int) Math.pow(-1,Math.floor(Model.getModel().getAToB(depart,arrivee).size()/2))*10;
    }

    //METHODES

    // genere toutes les fleches d'une classe donnée et les ajoutes à la vue
    public static void creerFleches(Classe c, VueCentre centre) {
        Model mod = Model.getModel();
        String nom = c.getType();
        nom = nom.substring(nom.lastIndexOf(" ")).replace(" ","");
        for (Classe cl : mod.getClasses()) {
            for (String dep : cl.getDependencies()) {
                System.out.println(dep);
                if (dep.contains(" " + nom)) {
                    Fleche f;
                    if (dep.contains(".|>")) f = new Fleche(cl, c, "..|>", "", centre);
                    else if (dep.contains("-|>")) f = new Fleche(cl, c, "--|>", "", centre);
                    else if (dep.contains("->")) {
                        f = new Fleche(cl, c, "-->", dep.substring(dep.lastIndexOf(":")), centre);
                        f.cardinalites = dep.split("\"")[1];
                    }
                    else f = new Fleche(cl,c,"..>","", centre);
                    mod.addFleche(f);
                    centre.getChildren().add(f.getFabrique().fabriquer());
                }
            }
            for (String dep2 : c.getDependencies()) {
                System.out.println(dep2);
                String temp = cl.getType().substring(cl.getType().lastIndexOf(" ") + 1);
                if (dep2.contains(" " +temp)){
                    Fleche f;
                    if (dep2.contains(".|>")) f = new Fleche(c, cl, "..|>", "", centre);
                    else if (dep2.contains("-|>")) f = new Fleche(c, cl, "--|>", "", centre);
                    else if (dep2.contains("->")){
                        f = new Fleche(c, cl, "-->", dep2.substring(dep2.lastIndexOf(":")), centre);
                        f.cardinalites = dep2.split("\"")[1];
                    }
                    else f = new Fleche(c,cl,"..>","", centre);
                    mod.addFleche(f);
                    centre.getChildren().add(f.getFabrique().fabriquer());
                }
            }
        }
    }

    public static void actualiserFleches(VueCentre centre){
        centre.supprimerFleches();
        for (Fleche f : Model.getModel().getFleches()){
            if (f.isAffiche()) centre.getChildren().add(f.fabrique.fabriquer());
        }
    }

    /**
     * retourne le type
     *
     * @return le type
     */
    public String getType() {
        return type;
    }

    /**
     * retourne la classe d'arrivee de la fleche
     *
     * @return la classe d'arrivee
     */
    public Classe getArrivee() {
        return arrivee;
    }

    /**
     * retourne la classe de depart de la fleche
     *
     * @return la classe de depart
     */
    public Classe getDepart() {
        return depart;
    }

    /**
     * retourne le boolean cache pour savoir si visuellement la fleche est cachee ou visible
     *
     * @return true si la fleche est cachee.
     */
    public boolean isAffiche() {
        return this.depart.isAfficheDependances();
    }

    public String getCardinalites() {
        return cardinalites;
    }

    public String getNom() {
        return nom;
    }

    private void setFabrique(VueCentre centre) {
        switch (type) {
            case "-->":
                fabrique = new FabriqueVueFlecheUtilisation(this, centre);
                break;
            case "--|>":
                fabrique = new FabriqueVueFlecheExtends(this, centre);
                break;
            case "..>" :
                fabrique = new FabriqueFlecheAsso(this,centre);
                break;
            default:
                fabrique = new FabriqueVueFlecheImplement(this, centre);
                break;
        }
    }

    public FabriqueVueFleche getFabrique() {
        return fabrique;
    }

    public int getOffsetLateral() {
        return offsetLateral;
    }
}
