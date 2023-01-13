package com.example.objectaid_sae.model;

import com.example.objectaid_sae.vue.VueCentre;
import com.example.objectaid_sae.vue.fabriqueFleches.*;

/**
 * Classe qui represente une fleche de dependance
 */
public class Fleche {

    //ATTRIBUTS
    /**
     * le type et le nom de la fleche
     */
    private final String type, nom;
    /**
     * La classe d'arrivee de la fleche
     */
    private final Classe arrivee;
    /**
     * la classe de depart de la fleche
     */
    private final Classe depart;

    /**
     * Represente les cardinalites de la fleche
     */
    private String cardinalites;

    /**
     * ?
     */
    private double offsetLateral;

    /**
     * La fabrique qui gerera l'affichage de la fleche
     */
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
     * @param nom nomd e la liaison
     * @param centre VueCentre ou trouver les vueClasses
     */
    public Fleche(Classe debut, Classe fin, String type, String nom, VueCentre centre) {
        this.type = type;
        this.arrivee = fin;
        this.depart = debut;
        this.nom = nom;
        this.cardinalites = "";
        setFabrique(centre);
        int nbAutresFleches = Model.getModel().getAToB(depart,arrivee).size() + Model.getModel().getAToB(arrivee,depart).size();
        offsetLateral = Math.pow(-1,nbAutresFleches+1)*10*nbAutresFleches;
    }

    //METHODES

    // genere toutes les fleches d'une classe donnée et les ajoutes à la vue

    /**
     * methode creerFleches, cree toutes les fleches ayant un lien avec la classe passee en parametre, ajoute leur vue a la VueCentre
     * utilise les classe stockees dans le model et stock les nouvelles fleches dans le model
     * @param c Classe dont on veux creeer toutes les fleches
     * @param centre VueCentre où ajouter les fleches
     */
    public static void creerFleches(Classe c, VueCentre centre) {
        Model mod = Model.getModel();
        String nom = c.getType();
        nom = nom.substring(nom.lastIndexOf(" ")).replace(" ","");
        for (Classe cl : mod.getClasses()) {
            for (String dep : cl.getDependencies()) {
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

    /**
     * methode actualiserFleches, actualise les fleches presentes dans le model
     * @param centre VueCentre ou ajouter les VueFleches
     */
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

    /**
     * methode getCardinalites renvoie les cardinalites d'arrivee de la fleche
     * @return cardinalites chaine de caractere contenant la cardinalite
     */

    public String getCardinalites() {
        return cardinalites;
    }

    /**
     * methoded getNom retourne le nom de la liaison pour les fleches d'utilisation
     * @return String nom de la liaison
     */
    public String getNom() {
        return nom;
    }

    /**
     * cree la fabriqueVueFleche de la fleche en fonction de son type
     * @param centre vueCentre ou trouver les vues des classes
     */
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

    /**
     * methode getFabrique retourne la fabrique correspondant a la fleche
     * @return FabriqueVueFleche fabricant la VueFleche
     */

    public FabriqueVueFleche getFabrique() {
        return fabrique;
    }

    /**
     * methode getOffsetLateral, retourne le decallage sur le cote de la fleche pour la placee correcetment
     * pour eviter la superposition
     * @return
     */
    public double getOffsetLateral() {
        return offsetLateral;
    }
}
