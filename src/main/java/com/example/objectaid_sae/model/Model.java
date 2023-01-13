package com.example.objectaid_sae.model;

import com.example.objectaid_sae.observateur.Observateur;
import com.example.objectaid_sae.observateur.Sujet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Model implements Sujet {
    private List<Classe> classes;
    private List<Observateur> observateurs;

    private List<Fleche> fleches;

    private static Model mod;

    public static synchronized Model getModel(){
        if (mod == null) mod = new Model();
        return mod;
    }

    public static synchronized void resetModel(){
        mod = null;
    }

    /**
     * Constructeur qui cree un modele
     */
    private Model() {
        this.classes = new ArrayList<>();
        this.observateurs = new ArrayList<>();
        fleches = new ArrayList<>();
    }

    /**
     * Methode qui ajoute une classe au modele
     * @param classe la classe a ajouter
     */
    public void addClasse(Classe classe) {
        if(!this.classes.contains(classe))
            this.classes.add(classe);
    }

    /**
     * methode qui retourne la liste des classes du modèle
     * @return la liste des classes
     */
    public List<Classe> getClasses() {
        return this.classes;
    }

    /**
     * methode qui ajoute un observateur au modele
     * @param o l'observateur a ajouter
     */
    @Override
    public void ajouterObservateur(Observateur o) {
        if(!this.observateurs.contains(o))
            this.observateurs.add(o);
    }

    /**
     * Methode qui appelle la methode notifier de tous les observateurs du modele
     */
    @Override
    public void notifierObservateurs() {
        for(Observateur o : this.observateurs) {
            o.notifier(this);
        }
    }

    /**
     * Permet de recuperer les fleches
     * contenus dans le model
     * @return List des fleches du model
     */
    public List<Fleche> getFleches() {
        return fleches;
    }

    /**
     * Permet d'ajouter une
     * fleche dans le model
     * @param f Fleche a ajoute
     */
    public void addFleche(Fleche f){
        if(f != null)
        fleches.add(f);
    }

    /**
     * Permet d'enregistrer les donnees sous
     * format UML
     * @return string du plantUML
     */
    public String enregistrerUML(){
        String UML = "@startuml\n" +
                "'https://plantuml.com/class-diagram\n" +
                "left to right direction\n" +
                "\n";
        for(Classe classeC : this.classes){
            String classeC_String = classeC.toString();
            UML += classeC_String + "\n";
        }

        UML += "@enduml\n";

        return UML;

    }

    /**
     * Permet de trouver les fleches
     * qui correspondent a une classe
     * @param classe Classe pour laquelle on cherche
     * @return List de fleche en relation a la classe
     */
    public List<Fleche> findFleche(Classe classe) {
        List<Fleche> flechesList = new ArrayList<>();
        for(Fleche fleche : this.fleches) {
            if(fleche.getDepart().equals(classe) || fleche.getArrivee().equals(classe))
                flechesList.add(fleche);
        }
        return flechesList;
    }

    /**
     * Donne toute les fleches qui vont
     * de dep a arr
     * @param dep Classe de depart
     * @param arr Classe d'arriver
     * @return
     */
    public List<Fleche> getAToB(Classe dep, Classe arr){
        List<Fleche> flechesList = new ArrayList<>();
        for(Fleche fleche : this.fleches) {
            if(fleche.getDepart().equals(dep) && fleche.getArrivee().equals(arr))
                flechesList.add(fleche);
        }
        return flechesList;
    }
}
