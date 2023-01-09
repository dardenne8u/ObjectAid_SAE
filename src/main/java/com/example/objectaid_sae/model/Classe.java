package com.example.objectaid_sae.model;

import com.example.objectaid_sae.observateur.Observateur;
import com.example.objectaid_sae.observateur.Sujet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Classe implements Sujet {

    /**
     * constante qui correspond à un attribut déclaré
     */
    public static final int DECLARED = 1;
    /**
     * constant qui correspond à un attrbut hérité
     */
    public static final int HERITED = 2;
    /**
     * booleen permettant de savoir si les attributs et méthodes doivent etre affiches dans
     * l'application ou non
     */
    private boolean afficheAttributsDeclare;
    private boolean afficheAttributsHerite;
    private boolean afficheMethodeDeclare;
    private boolean afficheMethodeHerite;
    private final boolean afficheConstructeur;
    /**
     * liste des attributs et methodes de la classe. 1 si declare, 2 si herite
     */
    private final Map<Integer, List<String>> attributs;
    private final Map<Integer, List<String>> methodes;
    /**
     * Liste des constructeurs de la classe
     */
    private final List<String> constructeurs;
    /**
     * signature de la classe (interface...)
     */
    private String type;
    /**
     * coordonnees x,y de la classe dans le pane une fois affichee
     */
    private double x,y;
    /**
     * liste des obervateurs de la classe
     */
    private final ArrayList<Observateur> observateurs;
    /**
     * listes de dependances de la classe
     */
    private final List<String> dependencies;

    private List<String> packageClassExternes;

    //METHODES


    public void setType(String type) {
        this.type = type;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Classe() {
        this.packageClassExternes = new ArrayList<>();
        this.attributs = new HashMap<>();
        this.attributs.put(DECLARED, new ArrayList<>());
        this.attributs.put(HERITED, new ArrayList<>());

        this.methodes = new HashMap<>();
        this.methodes.put(DECLARED, new ArrayList<>());
        this.methodes.put(HERITED, new ArrayList<>());
        this.constructeurs = new ArrayList<>();
        this.observateurs = new ArrayList<>();
        this.dependencies = new ArrayList<>();

        afficheAttributsDeclare = afficheAttributsHerite = afficheMethodeDeclare = afficheConstructeur = afficheMethodeHerite = true;
    }


    public void addPackageExternes(String packageExt) {
        if(!this.packageClassExternes.contains(packageExt))
            this.packageClassExternes.add(packageExt);
    }

    public List<String> getPackageExternes() {
        return this.packageClassExternes;
    }

    public void addAttribut(int type, String attribut) {
        if (!this.attributs.get(type).contains(attribut))
            this.attributs.get(type).add(attribut);
        notifierObservateurs();
    }

    public void removeAttribut(int type, String attribut) {
        this.attributs.get(type).remove(attribut);
        notifierObservateurs();
    }

    public void addMethode(int type, String methode) {
        if (!this.methodes.get(type).contains(methode))
            this.methodes.get(type).add(methode);
        notifierObservateurs();
    }

    public void removeMethode(int type, String methode) {
        this.methodes.get(type).remove(methode);
        notifierObservateurs();
    }

    public void addConstructeur(String constructeur) {
        if (!this.constructeurs.contains(constructeur))
            this.constructeurs.add(constructeur);
    }

    public void addDependencies(String dependence) {
        if (!this.dependencies.contains(dependence))
            this.dependencies.add(dependence);
    }


    public boolean isAfficheAttributsDeclare() {
        return afficheAttributsDeclare;
    }

    public boolean isAfficheAttributsHerite() {
        return afficheAttributsHerite;
    }

    public boolean isAfficheMethodeDeclare() {
        return afficheMethodeDeclare;
    }

    public boolean isAfficheMethodeHerite() {
        return afficheMethodeHerite;
    }

    public boolean isAfficheConstructeur() {
        return afficheConstructeur;
    }

    public Map<Integer, List<String>> getAttributs() {
        return attributs;
    }

    public Map<Integer, List<String>> getMethodes() {
        return methodes;
    }

    public List<String> getConstructeurs() {
        return constructeurs;
    }

    public String getType() {
        return type;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setAfficheAttributsDeclare(boolean afficheAttributsDeclare) {
        this.afficheAttributsDeclare = afficheAttributsDeclare;
        notifierObservateurs();
    }

    public void setAfficheAttributsHerite(boolean afficheAttributsHerite) {
        this.afficheAttributsHerite = afficheAttributsHerite;
        notifierObservateurs();
    }

    public void setAfficheMethodeDeclare(boolean afficheMethodeDeclare) {
        this.afficheMethodeDeclare = afficheMethodeDeclare;
        notifierObservateurs();
    }

    public void setAfficheMethodeHerite(boolean afficheMethodeHerite) {
        this.afficheMethodeHerite = afficheMethodeHerite;
        notifierObservateurs();
    }

    @Override
    public void ajouterObservateur(Observateur o) {
        this.observateurs.add(o);
    }

    @Override
    public void notifierObservateurs() {
        for (Observateur obs : observateurs) {
            obs.notifier(this);
        }
    }

    @Override
    public String toString() {
        return "Classe{" +
                "\n, attributs=" + attributs +
                "\n, methodes=" + methodes +
                "\n, constructeurs=" + constructeurs +
                "\n, dependence=" + dependencies +
                "\n, type='" + type + '\'' +
                '}';
    }

    public List<String> getDependencies() {
        return dependencies;
    }
}
