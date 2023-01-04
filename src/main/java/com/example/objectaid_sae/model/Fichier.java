package com.example.objectaid_sae.model;

import com.example.objectaid_sae.observateur.Observateur;
import com.example.objectaid_sae.observateur.Sujet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fichier implements Sujet {
    private String path;

    private Map<String, String> filePath;

    private List<Observateur> observateurs;

    /**
     * Constructeur qui cree un Fichier
     * @param path le chemin vers le projet que l'on veut traiter
     * @throws FileNotFoundException si le path n'est pas trouve
     */
    public Fichier(String path) throws FileNotFoundException {
        this.path = path;
        File f = new File(path);
        if (!f.exists()) throw new FileNotFoundException("Fichier n'existe pas");
        observateurs = new ArrayList<>();
        filePath = new HashMap<>();
        tree(f);
    }

    /**
     * methode qui cree la Map associee au projet passe en parametre et construire l'arborescence des
     * fichiers
     * @param files l'objet File correspondant
     */
    public void tree(File files) {
        filePath.put(files.getName(), files.getAbsolutePath());
        if (files.isDirectory()) {
            for(File f : files.listFiles()) {
                if(f.isDirectory()) tree(f);
                else filePath.put(f.getName(), f.getAbsolutePath());
            }
        }
    }

    /**
     * methode qui retourne le chemin vers le fichier a partir du nom
     * @param name le nom du fichier
     * @return la chaine de caractere du chemin vers le fichier trouve
     */
    public String getPath(String name) {
        return filePath.get(name);
    }

    /**
     * methode qui retourne le chemin vers le fichier de depart (permier fichier dans l'arbre)
     * @return le chemin vers le fichier de depart
     */
    public String getPathDepart() {
        return this.path;
    }

    /**
     * methode qui ajoute un observateur au fichier
     * @param o l'observateur a ajouter
     */
    @Override
    public void ajouterObservateur(Observateur o) {
        this.observateurs.add(o);
    }

    /**
     * methode qui appelle la methode notifier de tous les observateurs
     */
    @Override
    public void notifierObservateurs() {
        for(Observateur o : this.observateurs)
            o.notifier(this);
    }
}
