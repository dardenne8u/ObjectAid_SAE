package logo;

public class CrazyFrog extends Personnage{
    public CrazyFrog() {
        super("img/CrazyFrog.jpg", 4);
    }

    @Override
    public double combienCaCoute() {
        return this.prix;
    }
}
