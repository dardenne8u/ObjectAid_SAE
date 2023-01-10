package com.example.objectaid_sae.model;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

public class Analyseur {


    public static String packageProjet;
    /**
     * Classe stockant les valeurs
     * de l'introspection
     */
    private final Classe classe;

    /**
     * La classe java a introspecte
     */
    private final Class introspection;


    /**
     * Constructeur qui cree une classe vide
     * et charge la classe a intropecte
     * @param className nom de la classe compile
     * @throws ClassNotFoundException Si le nom de la classe compile n'est pas bon
     */
    public Analyseur(String className) throws ClassNotFoundException {
        this.introspection = Class.forName(className);
        this.classe = new Classe();
    }

    /**
     * Methode qui retour une classe
     * apres analyse de la classe
     * @return classe apres analyse
     */
    public Classe analyseClasse() {
        genAttributs();
        genMethods();
        genConstructeurs();
        genClassSignature();
        genImplementAndExtendsDependencies();
        return this.classe;
    }

    /**
     * Methode analysant les dependances
     * vis-a-vis des implementations et
     * des heritages
     */
    private void genImplementAndExtendsDependencies() {

        // Implementations
        String link;
        List<Class> interfaces = List.of(introspection.getInterfaces());
        for(Class inter : interfaces) {
            link = introspection.getSimpleName() + " ..|> " + inter.getSimpleName();
            if(!inter.getPackageName().contains(packageProjet)){
                classe.addPackageExternes(inter.getName());
            }

            classe.addDependencies(link);
        }

        // Heritage
        Class superClass = introspection.getSuperclass();
        if(superClass != null) {
            link = introspection.getSimpleName() + " --|> " + superClass.getSimpleName();
            classe.addDependencies(link);
            if(!superClass.getPackageName().contains(packageProjet)){
                classe.addPackageExternes(superClass.getName());
            }

        }
    }

    /**
     * Methode analysant la signature
     * d'une classe
     * exemple : abstract class Example
     * interface Example
     */
    private void genClassSignature() {
        int code = introspection.getModifiers();
        String res;
        if(Modifier.isInterface(code)) res = "interface";
        else if (Modifier.isAbstract(code)) res = "abstract class";
        else res = "class";

        res += " " + introspection.getSimpleName();
        this.classe.setType(res);
    }

    /**
     * Permet de savoir si
     * c'est prive, protegee, public
     * et static ou abstract
     * @param code Code de l'introspection
     * @return Un string correspondant au plantUML
     */
    private String getSignature(int code) {
        String res = "";
        if(Modifier.isPrivate(code)) res += "-";
        else if (Modifier.isProtected(code)) res += "#";
        else res += "+";

        if(Modifier.isStatic(code)) res += " {static}";
        else if (Modifier.isAbstract(code)) res += " {abstract}";
        return res + " ";
    }

    /**
     * Permet de generer l'introspection
     * des attributs
     */
    private void genAttributs() {
        List<Field> herited = new ArrayList<>(List.of(introspection.getFields()));
        List<Field> declared = new ArrayList<>(List.of(introspection.getDeclaredFields()));
        herited.removeIf(declared::contains);
        genAttributsInClass(Classe.HERITED, herited);
        genAttributsInClass(Classe.DECLARED, declared);
    }

    /**
     * Genere l'introspection en fonction
     * du code et de la list donnee
     * @param code code de la map (herited, declared)
     * @param fields list des attributs
     */
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

    /**
     * Permet de generer un lien entre deux classes
     * en prenant en compte l'attribut
     * @param field attribut faisant le lien
     * @return boolean pour exprimer si le lien a ete cree
     */
    private boolean genLink(Field field) {
        Class<?> type = field.getType();
        String typeName = type.getSimpleName();
        if(notClassicType(typeName)) return false; // lien non genere
        if(notClassicType(typeName)) return false;
        if(!type.getPackageName().contains(packageProjet)) {
            classe.addPackageExternes(type.getName());
        }
        String cardinalite = " \"0..1\"";

        if(type.isArray()) { // verification si l'attribut est un tableau -> []
            cardinalite = " \"0..*\"";
            typeName = typeName.substring(0, typeName.lastIndexOf("["));
        } else if (isContainsMoreThanOneValue(typeName)) { // verifie si l'attribut est une Collection
            cardinalite  = " \"0..*\"";
            typeName = field.getGenericType().getTypeName();
            // typeName = Collection<package.ClassName>
            typeName = typeName.substring(typeName.lastIndexOf(".")+1, typeName.length()-1);
            // typeName = ClassName
            if(notClassicType(typeName)) return false; // lien non genere
        }

        String link = introspection.getSimpleName() + " -->" + cardinalite + typeName + " : " + getSignature(field.getModifiers()) +field.getName();
        classe.addDependencies(link);
        return true;
    }

    /**
     * Permet de verifier si un attribut
     * est une collection
     * @param type String du type de l'attribut
     * @return vrai si c'est une collection, faux sinon
     */
    private boolean isContainsMoreThanOneValue(String type) {
        boolean isList = type.contains("List");
        boolean isMap = type.contains("Map");
        boolean isSet = type.contains("Set");
        return isSet || isList || isMap;
    }

    /**
     * Permet de vérifier que le type
     * n'est pas une classe correspondant a
     * int, double, float, long ou String
     * @param simpleName type de l'attribut
     * @return vrai si ca correspond, sinon faux
     */
    private boolean notClassicType(String simpleName) {
        boolean string = simpleName.contains("String");
        boolean integer = simpleName.contains("Integer");
        boolean doubleClass = simpleName.contains("Double");
        boolean floatClass = simpleName.contains("Float");
        boolean longClass = simpleName.contains("Long");
        return string || integer || doubleClass || floatClass || longClass;
    }


    /**
     * Permet de generer toute les methodes
     * de la class a analysee
     */
    private void genMethods() {
        List<Method> herited = new ArrayList<>(List.of(introspection.getMethods()));
        List<Method> declared = new ArrayList<>(List.of(introspection.getDeclaredMethods()));
        herited.removeIf(declared::contains);
        genMethodsInClass(Classe.HERITED, herited);
        genMethodsInClass(Classe.DECLARED, declared);
    }

    /**
     * Genere le string des methods a partir
     * de la list donnee en parametre et le stock
     * dans la map avec le code donnee en parametre
     * @param code code pour la map, utiliser les constantes(HERITED, DECLARED)
     * @param methods liste des methods dans la classe
     */
    private void genMethodsInClass(int code, List<Method> methods) {
        StringBuilder res;
        int i;
        for(Method m : methods) {
            res = new StringBuilder(getSignature(m.getModifiers()));
            res.append(m.getName()).append("(");
            i = 0;
            for(Parameter p : m.getParameters()) {
                if(i>0) res.append(", ");
                res.append(p.getType().getSimpleName());
                i++;
            }
            res.append(") : ").append(m.getReturnType().getSimpleName());
            this.classe.addMethode(code, res.toString());
        }
    }

    /**
     * Genere les constructeur de la classe
     * a analyse
     */
    private void genConstructeurs() {
        StringBuilder res;
        int i;
        for(Constructor c : this.introspection.getConstructors()) {
            res = new StringBuilder(getSignature(c.getModifiers()));
            res.append(introspection.getSimpleName()).append("(");
            // res = + ClassName(
            i = 0;
            for(Parameter p : c.getParameters()) {
                if(i>0) res.append(", "); // ajoute une virgule si un argument ou plus a ete mis
                // Ajout du type de l'argument p
                res.append(p.getType().getSimpleName());
                i++;
            }
            res.append(")");
            this.classe.addConstructeur(res.toString());
        }

    }

    public static void main(String[] args) throws ClassNotFoundException {
        Analyseur.packageProjet = "com.example.objectaid_sae";
        System.out.println(
        new Analyseur("com.example.objectaid_sae.tests.classTest").analyseClasse());

    }

}
