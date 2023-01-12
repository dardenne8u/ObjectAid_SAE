package logo;


public class ReneLaTaupe extends Personnage{

    
    /**
     * Constructeur
     */
    public ReneLaTaupe() {
        super("img/Taupe.jpg", 3.65);
    }

    /**
     * @return le prix du logo
     */
    public double combienCaCoute(){
        return prix;
    }
}
