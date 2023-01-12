package logo;

public abstract class DecorateurPersonnage extends Personnage {

    protected int decalX;

    protected int decalY;

    protected Personnage personnage;

    public DecorateurPersonnage(String nomIm, double prix, Personnage personnage) {
        super(nomIm, prix);
        this.personnage = personnage;
    }

    @Override
    public MyImage getLogo() {
        MyImage image = this.personnage.getLogo();
        image.paintOver(this.nomIm, this.decalX, this.decalY);
        return image;
    }


    public double combienCaCoute() {
        return this.prix + this.personnage.combienCaCoute();
    }
}
