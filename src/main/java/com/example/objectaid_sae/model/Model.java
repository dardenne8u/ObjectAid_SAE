package com.example.objectaid_sae.model;

import com.example.objectaid_sae.observateur.Observateur;
import com.example.objectaid_sae.observateur.Sujet;

import java.util.ArrayList;
import java.util.List;

public class Model implements Sujet {
    private List<Classe> classes;
    private List<Observateur> observateurs;

    public Model() {
        this.classes = new ArrayList<>();
        this.observateurs = new ArrayList<>();
    }

    public void addClasse(Classe classe) {
        if(this.classes.contains(classe))
            this.classes.add(classe);
    }

    public List<Classe> getClasses() {
        return this.classes;
    }

    @Override
    public void ajouterObservateur(Observateur o) {
        if(!this.observateurs.contains(o))
            this.observateurs.add(o);
    }

    @Override
    public void notifierObservateurs() {
        for(Observateur o : this.observateurs) {
            o.notifier(this);
        }
    }
}
