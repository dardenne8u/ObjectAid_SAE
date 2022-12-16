package com.example.objectaid_sae.model;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private List<Classe> classes;

    public Model() {
        this.classes = new ArrayList<>();
    }

    public void addClasse(Classe classe) {
        if(this.classes.contains(classe))
            this.classes.add(classe);
    }

    public List<Classe> getClasses() {
        return this.classes;
    }
}
