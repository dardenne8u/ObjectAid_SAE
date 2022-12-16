package com.example.objectaid_sae.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Classe {
    public static final int DECLARED = 1;
    public static final int HERITED = 2;

    private boolean afficheAttributsDeclare, afficheAttributsHerite, afficheMethodeDeclare, afficheMethodeHerite, afficheConstructeur;
    private Map<Integer, List<String>> attributs, methodes;
    private List<String> constructeurs;

    private String type;
    private double x,y;

    public Classe() {
        this.attributs = new HashMap<>();
        this.methodes = new HashMap<>();
        this.constructeurs = new ArrayList<>();
    }

    public void addAttribut(int type, String attribut) {
        if(!this.attributs.get(type).contains(attribut))
            this.attributs.get(type).add(attribut);
    }

    public void removeAttribut(int type, String attribut) {
        this.attributs.get(type).remove(attribut);
    }

    public void addMethode(int type, String methode) {
        if(!this.methodes.get(type).contains(methode))
            this.methodes.get(type).add(methode);
    }

    public void removeMethode(int type, String methode) {
        this.methodes.get(type).remove(methode);
    }

    public void addConstructeur(String constructeur) {
        if(!this.constructeurs.contains(constructeur))
            this.constructeurs.add(constructeur);
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
    }

    public void setAfficheAttributsHerite(boolean afficheAttributsHerite) {
        this.afficheAttributsHerite = afficheAttributsHerite;
    }

    public void setAfficheMethodeDeclare(boolean afficheMethodeDeclare) {
        this.afficheMethodeDeclare = afficheMethodeDeclare;
    }

    public void setAfficheMethodeHerite(boolean afficheMethodeHerite) {
        this.afficheMethodeHerite = afficheMethodeHerite;
    }
}