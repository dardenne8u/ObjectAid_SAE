package com.example.objectaid_sae.model;

import com.example.objectaid_sae.observateur.Observateur;
import com.example.objectaid_sae.observateur.Sujet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Model implements Sujet {

    /**
     * Liste des classes présentes dans le diagramme
     */
    private List<Classe> classes;

    /**
     * Liste des observateurs du modèle
     */
    private List<Observateur> observateurs;

    /**
     * Liste des dépendances entre les classes
     */
    private List<Fleche> fleches;

    /**
     * Model, pour le patron Singleton
     */
    private static Model mod;

    /**
     * Methode qui cree une instance de Model si le Model n'existe pas. Sinon retourne le model
     * @return l'instance de Model
     */
    public static synchronized Model getModel(){
        if (mod == null) mod = new Model();
        return mod;
    }

    /**
     * Permet de reinitialiser le modele a 0, supprimer toutes ses donnees
     */
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
     * retourne la liste de fleches du modele
     * @return une liste de fleches
     */
    public List<Fleche> getFleches() {
        return fleches;
    }

    /**
     * Permet d'ajouter une fleche a la liste
     * @param f la fleche a ajouter
     */
    public void addFleche(Fleche f){
        if(f != null)
        fleches.add(f);
    }

    /**
     * permet d'enregistrer le modele au format UML en recuperant toutes les donnees des classes
     * @return String, le contenu complet lisible par un format UML
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
     * permet de retourner une liste de fleches a partir de sa classe de depart
     * @param classe la classe de depart des fleches recherchees
     * @return une liste de fleches
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
     * Retourne toutes les fleches allant d'une classe depart vers une classe d'arrivee
     * @param dep la classe de depart de la fleche
     * @param arr la classe d'arrivee de la fleche
     * @return la liste de fleche trouvee
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
