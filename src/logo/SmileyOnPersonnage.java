package logo;

public class SmileyOnPersonnage extends DecorateurPersonnage{
    public SmileyOnPersonnage(Personnage personnage) {
        super("img/Smiley.png", 0.7, personnage);
        this.decalX=260; this.decalY=210;
    }
}
