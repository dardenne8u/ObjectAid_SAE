package com.example.objectaid_sae.observateur;

/**
 * Interface Observateur
 */
public interface Observateur {

    /**
     * methode de mise a jour des vues
     * @param s le sujet mettant a jour la vue
     */
    void notifier(Sujet s);
}
