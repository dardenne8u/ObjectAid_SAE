package logo;


import org.w3c.dom.Text;

public class Main
{
   
    public static void main(String args[])
    {
//        ReneLaTaupe l = new ReneLaTaupe();
//        MyImage i = l.getLogo();
//        i.paintOver("img/Sunglasses.png", 255, 76);
        
//        i.paintOver("img/Chapeau.png", 280,42);
        DecorateurPersonnage d = new TextOnPersonnage(new LunetteOnPersonnage(new ChapeauOnPersonnage(new CandyOnPersonnage(new SmileyOnPersonnage(new ReneLaTaupe())))), "THE rene");
        MyImage i = d.getLogo();
        System.out.println(d.combienCaCoute() + "€");
        i.display();  // Permet l'affichage dans une fenetre de l'image associee
        
        
    }
        
}
