package com.example.objectaid_sae.model;

import com.example.objectaid_sae.observateur.Observateur;
import com.example.objectaid_sae.observateur.Sujet;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;

public class Fleche implements Sujet {

    //ATTRIBUTS
    private String type;
    private Classe arrivee;
    private Classe depart;
    private boolean cache;
    private List<Observateur> observateurs;

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
    public Fleche(Classe debut, Classe fin, String type) {
        this.type = type;
        this.arrivee = fin;
        this.depart = debut;
        this.cache = false;
        observateurs = new ArrayList<>();
    }

    //METHODES

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
    public boolean isCache() {
        return this.cache;
    }

    public void setCache(boolean cache) {
        this.cache = cache;
    }

    @Override
    public void ajouterObservateur(Observateur o) {
        observateurs.add(o);
    }

    @Override
    public void notifierObservateurs() {
        for(Observateur o : observateurs)o.notifier(this);
    }
}
