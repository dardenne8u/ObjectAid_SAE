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

    public Fichier(String path) throws FileNotFoundException {
        this.path = path;
        File f = new File(path);
        if (!f.exists()) throw new FileNotFoundException("Fichier n'existe pas");
        observateurs = new ArrayList<>();
        filePath = new HashMap<>();
        tree(f);
    }

    public void tree(File files) {
        filePath.put(files.getName(), files.getAbsolutePath());
        if (files.isDirectory()) {
            for(File f : files.listFiles()) {
                if(f.isDirectory()) tree(f);
                else filePath.put(f.getName(), f.getAbsolutePath());
            }
        }
    }

    public String getPath(String name) {
        return filePath.get(name);
    }

    public String getPathDepart() {
        return this.path;
    }

    @Override
    public void ajouterObservateur(Observateur o) {
        this.observateurs.add(o);
    }

    @Override
    public void notifierObservateurs() {
        for(Observateur o : this.observateurs)
            o.notifier(this);
    }
}
