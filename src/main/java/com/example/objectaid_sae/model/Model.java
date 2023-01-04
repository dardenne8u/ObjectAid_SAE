package com.example.objectaid_sae.model;

import com.example.objectaid_sae.observateur.Observateur;
import com.example.objectaid_sae.observateur.Sujet;

import java.util.ArrayList;
import java.util.List;

public class Model implements Sujet {
    private List<Classe> classes;
    private List<Observateur> observateurs;

    /**
     * Constructeur qui cree un modele
     */
    public Model() {
        this.classes = new ArrayList<>();
        this.observateurs = new ArrayList<>();
    }

    /**
     * Methode qui ajoute une classe au modele
     * @param classe la classe a ajouter
     */
    public void addClasse(Classe classe) {
        if(this.classes.contains(classe))
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
}
