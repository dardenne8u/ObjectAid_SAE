package com.example.objectaid_sae.model;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

public class Analyseur {

    private final Classe classe;
    private final Class introspection;


    public Analyseur(String className) throws ClassNotFoundException {
        this.introspection = Class.forName(className);
        this.classe = new Classe();
    }

    public Classe analyseClasse() {
        genAttributs();
        genMethods();
        return this.classe;
    }

    private String getSignature(int code) {
        String res = "";
        if(Modifier.isPrivate(code)) res += "-";
        else if (Modifier.isProtected(code)) res += "#";
        else res += "+";

        if(Modifier.isStatic(code)) res += " {static}";
        else if (Modifier.isAbstract(code)) res += " {abstract}";
        return res;
    }

    private void genAttributs() {
        List<Field> herited = new ArrayList<>(List.of(introspection.getFields()));
        List<Field> declared = new ArrayList<>(List.of(introspection.getDeclaredFields()));
        herited.removeIf(declared::contains);
        genAttributsInClass(Classe.HERITED, herited);
        genAttributsInClass(Classe.DECLARED, declared);
    }

    private void genAttributsInClass(int code,List<Field> fields) {
        String res;
        for (Field f : fields) {
            res = getSignature(f.getModifiers());
            res += f.getName();
            res += " : " + f.getType().getSimpleName();
            this.classe.addAttribut(code, res);
        }
    }

    private void genMethods() {
        List<Method> herited = new ArrayList<>(List.of(introspection.getMethods()));
        List<Method> declared = new ArrayList<>(List.of(introspection.getDeclaredMethods()));
        herited.removeIf(declared::contains);
        genMethodsInClass(Classe.HERITED, herited);
        genMethodsInClass(Classe.DECLARED, declared);
    }

    private void genMethodsInClass(int code, List<Method> methods) {
        String res;
        int i;
        for(Method m : methods) {
            res = getSignature(m.getModifiers());
            res += m.getName() + "(";
            i = 0;
            for(Parameter p : m.getParameters()) {
                if(i>0) res += ", ";
                res += p.getType().getSimpleName();
                i++;
            }
            res += ") : " + m.getReturnType().getSimpleName();
            this.classe.addMethode(code, res);
        }
    }

    private void genConstructeurs() {
        String res;
        for(Constructor c : this.introspection.getConstructors()) {
            res = getSignature(c.getModifiers());
        }

    }


}
