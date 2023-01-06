package com.example.objectaid_sae.model;

import com.example.objectaid_sae.observateur.Observateur;
import com.example.objectaid_sae.observateur.Sujet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Classe implements Sujet {
    public static final int DECLARED = 1;
    public static final int HERITED = 2;

    private boolean afficheAttributsDeclare, afficheAttributsHerite, afficheMethodeDeclare, afficheMethodeHerite, afficheConstructeur;
    //integer : 1 = declared, 2 = herited
    private Map<Integer, List<String>> attributs, methodes;
    private List<String> constructeurs;

    private String type;
    private double x,y;

    private ArrayList<Observateur> observateurs;

    private List<String> dependencies;

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

    public void addAttribut(int type, String attribut) {
        if(!this.attributs.get(type).contains(attribut))
            this.attributs.get(type).add(attribut);
        notifierObservateurs();
    }

    public void removeAttribut(int type, String attribut) {
        this.attributs.get(type).remove(attribut);
        notifierObservateurs();
    }

    public void addMethode(int type, String methode) {
        if(!this.methodes.get(type).contains(methode))
            this.methodes.get(type).add(methode);
        notifierObservateurs();
    }

    public void removeMethode(int type, String methode) {
        this.methodes.get(type).remove(methode);
        notifierObservateurs();
    }

    public void addConstructeur(String constructeur) {
        if(!this.constructeurs.contains(constructeur))
            this.constructeurs.add(constructeur);
    }

    public void addDependencies(String dependence) {
        if(!this.dependencies.contains(dependence))
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
        for (Observateur obs : observateurs){
            obs.notifier(this);
        }
    }

    @Override
    public String toString() {
        return "Classe{" +
                "\n, attributs=" + attributs +
                "\n, methodes=" + methodes +
                "\n, constructeurs=" + constructeurs +
                "\n, dependence="+ dependencies +
                "\n, type='" + type + '\'' +
                '}';
    }
}
