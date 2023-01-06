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
        genConstructeurs();
        genClassSignature();
        genImplementAndExtendsDependencies();
        return this.classe;
    }

    private void genImplementAndExtendsDependencies() {
        String link;
        List<Class> interfaces = List.of(introspection.getInterfaces());
        for(Class inter : interfaces) {
            link = introspection.getSimpleName() + " ..|> " + inter.getSimpleName();
            classe.addDependencies(link);
        }

        Class superClass = introspection.getSuperclass();
        if(superClass != null) {
            link = introspection.getSimpleName() + " --|> " + superClass.getSimpleName();
            classe.addDependencies(link);
        }
    }

    private void genClassSignature() {
        int code = introspection.getModifiers();
        String res;
        if(Modifier.isAbstract(code)) res = "abstract class";
        else if (Modifier.isInterface(code)) res = "interface";
        else res = "class";

        res += " " + introspection.getSimpleName();
        this.classe.setType(res);
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
        Class type;
        for (Field f : fields) {
            type = f.getType();
            if(type.getSimpleName().matches("^[A-Z][A-z]+"))
                if(genLink(f))
                    continue;
            res = getSignature(f.getModifiers());
            res += f.getName();
            res += " : " + type.getSimpleName();
            this.classe.addAttribut(code, res);
        }
    }

    private boolean genLink(Field field) {
        Class type = field.getType();
        String typeName = type.getSimpleName();
        if(notClassicType(typeName)) return false;
        String cardinalite = " \"0..1\" ";
        if(type.isArray()) {
            cardinalite = " \"0..*\" ";
            typeName = typeName.substring(0, typeName.lastIndexOf("["));
        } else if (isContainsMoreThanOneValue(typeName)) {
            cardinalite  = " \"0..*\" ";
            typeName = field.getGenericType().getTypeName();
            typeName = typeName.substring(typeName.lastIndexOf(".")+1, typeName.length()-1);
            if(notClassicType(typeName)) return false;
        }

        String link = introspection.getSimpleName() + " -->" + cardinalite + typeName + " : " + getSignature(field.getModifiers()) +field.getName();
        classe.addDependencies(link);
        return true;
    }

    private boolean isContainsMoreThanOneValue(String type) {
        boolean isList = type.contains("List");
        boolean isMap = type.contains("Map");
        boolean isSet = type.contains("Set");
        return isSet || isList || isMap;
    }

    private boolean notClassicType(String simpleName) {
        boolean string = simpleName.contains("String");
        boolean integer = simpleName.contains("Integer");
        boolean doubleClass = simpleName.contains("Double");
        boolean floatClass = simpleName.contains("Float");
        boolean longClass = simpleName.contains("Long");
        return string || integer || doubleClass || floatClass || longClass;
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
        int i;
        for(Constructor c : this.introspection.getConstructors()) {
            res = getSignature(c.getModifiers());
            res += c.getName() + "(";
            i = 0;
            for(Parameter p : c.getParameters()) {
                if(i>0) res += ", ";
                res += p.getType().getSimpleName();
                i++;
            }
            res += ")";
            this.classe.addConstructeur(res);
        }

    }

    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println(
        new Analyseur("com.example.objectaid_sae.tests.classTest").analyseClasse());

    }

}
