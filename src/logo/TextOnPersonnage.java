package logo;

public class TextOnPersonnage extends DecorateurPersonnage{
    public TextOnPersonnage(Personnage personnage, String texte) {
        super(texte, 10, personnage);
        this.decalX = 300; this.decalY = 350;
    }

    @Override
    public MyImage getLogo() {
        MyImage image = this.personnage.getLogo();
        image.textOver(this.nomIm, this.decalX, this.decalY);
        return image;
    }
}
