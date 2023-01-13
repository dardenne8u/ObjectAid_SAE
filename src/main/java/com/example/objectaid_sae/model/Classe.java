package com.example.objectaid_sae.model;

import com.example.objectaid_sae.observateur.Observateur;
import com.example.objectaid_sae.observateur.Sujet;

import java.util.*;

public class Classe implements Sujet {

    /**
     * constante qui correspond à un attribut declare
     */
    public static final int DECLARED = 1;
    /**
     * constant qui correspond à un attrbut herite
     */
    public static final int HERITED = 2;
    /**
     * booleen permettant de savoir si les attributs et methodes doivent etre affiches dans
     * l'application ou non
     */
    private boolean afficheAttributsDeclare;
    private boolean afficheAttributsHerite;
    private boolean afficheMethodeDeclare;
    private boolean afficheMethodeHerite;
    private boolean afficheConstructeur;
    private boolean afficheDependances;
    /**
     * liste des attributs et methodes de la classe. 1 si declare, 2 si herite
     */
    private final Map<Integer, List<String>> attributs;
    private final Map<Integer, List<String>> methodes;
    /**
     * Liste des constructeurs de la classe
     */
    private final List<String> constructeurs;
    /**
     * signature de la classe (interface...)
     */
    private String type;
    /**
     * coordonnees x,y de la classe dans le pane une fois affichee
     */
    private double x, y;
    /**
     * liste des obervateurs de la classe
     */
    private final ArrayList<Observateur> observateurs;
    /**
     * listes de dependances de la classe
     */
    private final List<String> dependencies;

    /**
     * liste des package des classes Externes de la classe
     */

    private List<String> packageClassExternes;

    /**
     * String contenant le nom du package de la classe
     */
    private String packageName;

    //METHODES

    /**
     * methode qui affecte la signature de la classe
     * @param  type String contenant la signature de la classe
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * methode qui modifie la coordonee X de la classe
     * @param  x cooredonee X de la classe
     */

    public void setX(double x) {
        this.x = x;
    }

    /**
     * methode qui modifie la coordonee y de la classe
     * @param  y cooredonee y de la classe
     */


    public void setY(double y) {
        this.y = y;
    }

    /**
     * Constructeur de la classe qui initialise les attribut.
     */

    public Classe() {
        this.packageName = "";
        this.packageClassExternes = new ArrayList<>();
        this.attributs = new HashMap<>();
        this.attributs.put(DECLARED, new ArrayList<>());
        this.attributs.put(HERITED, new ArrayList<>());

        this.methodes = new HashMap<>();
        this.methodes.put(DECLARED, new ArrayList<>());
        this.methodes.put(HERITED, new ArrayList<>());
        this.constructeurs = new ArrayList<>();
        this.observateurs = new ArrayList<>();
        this.dependencies = new ArrayList<>();

        afficheAttributsDeclare = afficheMethodeDeclare = afficheConstructeur = afficheDependances = true;
        afficheAttributsHerite = afficheMethodeHerite = false;
    }

    /**
     * methode pour obtenir le nom du package de la classe
     * @return le nom du package de la classe
     */
    public String getPackageName() {
        return this.packageName;
    }

    /**
     * methode qui modifie le nom du package de la classe
     * @param name nom du package
     */

    public void setPackageName(String name) {
        this.packageName = name;
    }

    /**
     * methode qui ajoute un package Externe à la classe
     * @param packageExt nom du package Externe
     */
    public void addPackageExternes(String packageExt) {
        if (!this.packageClassExternes.contains(packageExt))
            this.packageClassExternes.add(packageExt);
    }

    /**
     * methode qui donne les noms des packages externes
     * @return Liste contenant les noms des packages externes
     */
    public List<String> getPackageExternes() {
        return this.packageClassExternes;
    }

    /**
     * methode qui ajoute un attribut à la classe
     * @param type declare ou herite
     * @param attribut attribut en question à ajouter
     */
    public void addAttribut(int type, String attribut) {
        if (!this.attributs.get(type).contains(attribut))
            this.attributs.get(type).add(attribut);
        notifierObservateurs();
    }

    /**
     * methode qui supprime un attribut de la classe
     * @param type declare ou herite
     * @param attribut attribut en question à supprimer
     */
    public void removeAttribut(int type, String attribut) {
        this.attributs.get(type).remove(attribut);
        notifierObservateurs();
    }

    /**
     * methode qui ajoute une methode à la classe
     * @param type declare ou herite
     * @param methode methode en question à ajouter
     */

    public void addMethode(int type, String methode) {
        if (!this.methodes.get(type).contains(methode))
            this.methodes.get(type).add(methode);
        notifierObservateurs();
    }

    /**
     * methode qui supprime une methode de la classe
     * @param type declare ou herite
     * @param methode methode en question à supprimer
     */

    public void removeMethode(int type, String methode) {
        this.methodes.get(type).remove(methode);
        notifierObservateurs();
    }

    /**
     * methode qui permet d'ajouter un constructeur à la classe
     * @param constructeur constructeur a ajouter
     */

    public void addConstructeur(String constructeur) {
        if (!this.constructeurs.contains(constructeur))
            this.constructeurs.add(constructeur);
    }

    /**
     * methode qui permet d'ajouter des dependances a la classe
     * @param dependence dependance a ajouter a la classe
     */
    public void addDependencies(String dependence) {
        if (!this.dependencies.contains(dependence))
            this.dependencies.add(dependence);
    }

    /**
     * permet de savoir l'etat du boolean pour l'affichage des attributs declares
     * @return l'etat du boolean en question
     */
    public boolean isAfficheAttributsDeclare() {
        return afficheAttributsDeclare;
    }

    /**
     * permet de savoir l'etat du boolean pour l'affichage des attributs herites
     * @return l'etat du boolean en question
     */
    public boolean isAfficheAttributsHerite() {
        return afficheAttributsHerite;
    }

    /**
     * permet dde savoir l'etat du boolean pour l'affichage des methodes declares
     * @return l'etat du boolean en question
     */
    public boolean isAfficheMethodeDeclare() {
        return afficheMethodeDeclare;
    }
    /**
     * permet dde savoir l'etat du boolean pour l'affichage des methodes herite
     * @return l'etat du boolean en question
     */
    public boolean isAfficheMethodeHerite() {
        return afficheMethodeHerite;
    }

    /**
     * permet dde savoir l'etat du boolean pour l'affichage des constructeurs
     * @return l'etat du boolean en question
     */
    public boolean isAfficheConstructeur() {
        return afficheConstructeur;
    }

    /**
     * permet de recuperer les listes des attributs de la classe (herites et declares)
     * @return la map contenant les deux listes
     */
    public Map<Integer, List<String>> getAttributs() {
        return attributs;
    }


    /**
     * permet de recuperer les listes des methodes de la classe (herites et declares)
     * @return la map contenant les deux listes
     */
    public Map<Integer, List<String>> getMethodes() {
        return methodes;
    }

    /**
     * permet d'obtenir les constructeur de la classe
     * @return Liste de constructeur de cette classe
     */
    public List<String> getConstructeurs() {
        return constructeurs;
    }

    /**
     * donne la signature de la classe
     * @return le string contenant la signature de la classe
     */
    public String getType() {
        return type;
    }

    /**
     * donne les coordonées X de la classe
     * @return double correspondant au cooredonées X de la classe
     */
    public double getX() {
        return x;
    }

    /**
     * donne les coordonées Y de la classe
     * @return double correspondant au cooredonées Y de la classe
     */
    public double getY() {
        return y;
    }


    public void setAfficheAttributsDeclare(boolean afficheAttributsDeclare) {
        this.afficheAttributsDeclare = afficheAttributsDeclare;
        notifierObservateurs();
    }

    public void setAfficheAttributsHerite(boolean afficheAttributsHerite) {
        this.afficheAttributsHerite = afficheAttributsHerite;
        notifierObservateurs();
    }

    public void setAfficheMethodeDeclare(boolean afficheMethodeDeclare) {
        this.afficheMethodeDeclare = afficheMethodeDeclare;
        notifierObservateurs();
    }

    public void setAfficheMethodeHerite(boolean afficheMethodeHerite) {
        this.afficheMethodeHerite = afficheMethodeHerite;
        notifierObservateurs();
    }

    @Override
    public void ajouterObservateur(Observateur o) {
        this.observateurs.add(o);
    }

    @Override
    public void notifierObservateurs() {
        for (Observateur obs : observateurs) {
            obs.notifier(this);
        }
    }

    @Override
    public String toString() {

        String s = this.type+"{\n";
        //attributs :
        List<String> listAtt = attributs.get(1);
        Iterator<String> iterAtt1 = listAtt.iterator();
        while (iterAtt1.hasNext()){
            s += iterAtt1.next() + "{\n";
        }
        List<String> listAt = attributs.get(2);
        Iterator<String> iterAt2 = listAt.iterator();
        while (iterAt2.hasNext()){
            s += iterAt2.next() + "\n";
        }


        //constructeurs
        Iterator<String> iterConst = constructeurs.iterator();
        while(iterConst.hasNext()){
            s+= iterConst.next() + "\n";
        }

        //methode
        List<String> listmeth1 = methodes.get(1);
        Iterator<String> iterMeth1 = listmeth1.iterator();
        while (iterMeth1.hasNext()){
            s += iterMeth1.next() + "\n";
        }
        List<String> listmeth2 = methodes.get(2);
        Iterator<String> itermeth2 = listmeth2.iterator();
        while (itermeth2.hasNext()){
            s += itermeth2.next() + "\n";
        }
        s += "}\n";

        Iterator<String> iterateur_dépendance = dependencies.iterator();
        while (iterateur_dépendance.hasNext()){
            s+= iterateur_dépendance.next() + "\n";
        }


        return s;
    }

    /**
     * permet d'obtenir la list des dependances de cette classe
     * @return liste des dependance de cette classe
     */
    public List<String> getDependencies() {
        return dependencies;
    }

    public void setAfficheConstructeur(boolean selected) {
        this.afficheConstructeur = selected;
        this.notifierObservateurs();
    }

    /**
     * permet de savoir l'etat du boolean concernant les dependances
     * @return le boolean correposndant a l'affichage des dependances
     */

    public boolean isAfficheDependances() {
        return afficheDependances;
    }

    public void setAfficheDependances(boolean afficheDependances) {
        this.afficheDependances = afficheDependances;
    }

    /**
     * methode qui permet de generer le squelette de la classe
     * @return le string contenant le squelette de la classe
     */
    public String genSquelette() {
        String squelette = "";
        if(!packageName.equals(""))
            squelette += "package " + this.packageName + ";\n\n";
        squelette += genSqueletteSignature() + " { \n";
        squelette += genSqueletteAttributs();
        squelette += genSqueletteAvecLink() + "\n";
        squelette += genSqueletteMethods();
        squelette += "}";
        return squelette;
    }

    /**
     * genere le squelette pour la signature de la classe
     * @return string contenant le squelette de la signature de la classe
     */
    private String genSqueletteSignature() {
        String signature = convertToSignature(this.type);
        String extendsClass = "";
        String implementClass = "";

        for (String line : this.dependencies) {
            String type = line.substring(line.indexOf(">")+2);
            if (line.contains("--|>")) {
                if(line.contains("Object"))
                    continue;
                extendsClass = type;
            } else if (line.contains("..|>")) {
                if(implementClass.equals("")){
                    implementClass = type;
                } else {
                    implementClass += ", " + type;
                }
            }
        }
        if(!extendsClass.equals(""))
            signature += " extends " + extendsClass;
        if(!implementClass.equals(""))
            signature += " implement " + implementClass;
        return signature;
    }

    /**
     * genere le squelette concernant les dependances
     * @return string contenant le squelettes des dependances de la classe
     */
    private String genSqueletteAvecLink() {
        StringBuilder builder = new StringBuilder();
        for (String line : this.dependencies) {
            String card = "";
            if (line.contains("\""))
                card = line.substring(line.indexOf("\"") + 1, line.lastIndexOf("\""));
            String type, name;
            if (line.contains(":")) {
                type = line.substring(line.lastIndexOf("\"") + 2, line.indexOf(":") - 1);
                name = line.substring(line.indexOf(":") + 2);
            } else {
                type = line.substring(line.lastIndexOf("\"") + 1);
                name = "public attribut";
            }
            name = name.replace("-", "private");
            name = name.replace("+", "public");
            name = name.replace("#", "protected");
            String[] parts = name.split(" ");
            if (line.contains("-->"))
                if (card.contains("0..*")) {
                    builder.append("\t" + parts[0] + " Collection<" + type + "> " + parts[1] + ";\n");
                } else {
                    builder.append("\t" + parts[0] + " " + type + " " + parts[1] + ";\n");
                }
        }
        return builder.toString();
    }
    /**
     * genere le squelette pour les attributs de la classe
     * @return string contenant le squelette des attributs de la classe
     */
    private String genSqueletteAttributs() {
        StringBuilder builder = new StringBuilder();
        for (int key : this.attributs.keySet()) {
            for (String line : this.attributs.get(key)) {
                builder.append("\t" + convertToSignature(line) + ";\n");
            }
        }
        return builder.toString();
    }
    /**
     * genere le squelette concernant les methodes
     * @return string contenant le squelettes des methodes de la classe
     */
    private String genSqueletteMethods() {
        StringBuilder builder = new StringBuilder();
        for (String line : this.constructeurs) {
            builder.append("\t" + convertToSignature(line) + "{}\n");
        }
        for (String line : this.methodes.get(DECLARED)) {
            builder.append("\t" + convertToSignature(line) + "{}\n");
        }

        return builder.toString();
    }
    /**
     * permet de transformer les symboles (+,- et #) en string (public, private, protected)
     * @return string contenant les symboles traduit en mots
     */
    private String convertToSignature(String line) {
        if (line.contains("-"))
            line = line.replace("-", "private");
        else if (line.contains("#"))
            line = line.replace("#", "protected");
        else if (line.contains("+"))
            line = line.replace("+", "public");
        else
            line = "public " + line;

        if (line.contains("static") || line.contains("abstract")) {
            line = line.replace("{", "");
            line = line.replace("}", "");
        }
        if (line.contains(":")) {
            String returnType = line.substring(line.lastIndexOf(" ") + 1);
            line = line.substring(0, line.indexOf(":") - 1);
            String[] parts = line.split(" ");
            line = "";
            for (int i = 0; i < parts.length - 1; i++) {
                line += parts[i] + " ";
            }
            line += returnType + " ";
            line += parts[parts.length - 1];
        }
        return line;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Classe classe = (Classe) o;
        return Objects.equals(type, classe.type) && Objects.equals(packageName, classe.packageName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, packageName);
    }
}
