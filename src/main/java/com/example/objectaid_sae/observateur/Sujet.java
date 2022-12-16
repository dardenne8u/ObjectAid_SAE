package com.example.objectaid_sae.observateur;

public interface Sujet {

    void ajouterObservateur(Observateur o);

    void notifierObservateurs();
}
