package logo;

public class ChapeauOnPersonnage extends DecorateurPersonnage{
    public ChapeauOnPersonnage(Personnage personnage) {
        super("img/Chapeau.png", 0.6, personnage);
        this.decalX = 280; this.decalY = 42;
    }
}
