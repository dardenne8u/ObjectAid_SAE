package logo;

public class LunetteOnPersonnage extends  DecorateurPersonnage{
    public LunetteOnPersonnage(Personnage personnage) {
        super("img/Sunglasses.png", 0.5, personnage);
        this.decalX = 255;
        this.decalY = 76;
    }
}
