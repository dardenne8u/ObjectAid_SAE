package logo;

public abstract class Personnage {
    /**
     * Chemin d'acces au fichier
     * contenant l'image de fond du logo
     */
    protected String nomIm;

    /**
     * Prix du logo
     */
    protected double prix;

    public Personnage(String nomIm, double prix) {
        this.nomIm = nomIm;
        this.prix = prix;
    }

    public MyImage getLogo() {
        return new MyImage(nomIm);
    }

    public void display() {
        this.getLogo().display();
    }

    public abstract double combienCaCoute();


}
