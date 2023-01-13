package logo;

public class CandyOnPersonnage extends DecorateurPersonnage{
    public CandyOnPersonnage(Personnage personnage) {
        super("img/Candy.png", 0.3, personnage);
        this.decalX = 441; this.decalY=202;
    }
}
