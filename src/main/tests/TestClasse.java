import com.example.objectaid_sae.model.Classe;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


public class TestClasse {
    @Test
    public void testSetType() {
        Classe c = new Classe();
        c.setType("interface Test");
        assertEquals("interface Test", c.getType());
    }

    @Test
    public void testSetX() {
        Classe c = new Classe();
        c.setX(100);
        assertEquals(100, c.getX(), 0.01);
    }

    @Test
    public void testSetY() {
        Classe c = new Classe();
        c.setY(200);
        assertEquals(200, c.getY(), 0.01);
    }

    @Test
    public void testAddAttribut() {
        Classe c = new Classe();
        c.addAttribut(Classe.DECLARED, "attribut");
        assertTrue(c.getAttributs().get(c.DECLARED).contains("attribut"));
    }

    @Test
    public void testRemoveAttribut() {
        Classe c = new Classe();
        c.addAttribut(Classe.DECLARED, "attribut");
        c.removeAttribut(Classe.DECLARED, "attribut");
        assertFalse(c.getAttributs().get(c.DECLARED).contains("attribut"));
    }

    @Test
    public void testAddMethod() {
        Classe c = new Classe();
        c.addMethode(Classe.DECLARED, "method()");
        assertTrue(c.getMethodes().get(c.DECLARED).contains("method()"));
    }

    @Test
    public void testRemoveMethod() {
        Classe c = new Classe();
        c.addMethode(Classe.DECLARED, "method()");
        c.removeMethode(Classe.DECLARED, "method()");
        assertFalse(c.getMethodes().get(c.DECLARED).contains("method()"));
    }

    @Test
    public void testAddConstructeur() {
        Classe c = new Classe();
        c.addConstructeur("constructeur()");
        assertTrue(c.getConstructeurs().contains("constructeur()"));
    }


    @Test
    public void testAddDependency() {
        Classe c = new Classe();
        Classe c2 = new Classe();
        c.setType("interface Test");
        c2.setType("Class ok");
        c.addDependencies("ok ..|> Test");
        assertTrue(c.getDependencies().contains("ok ..|> Test"));
    }


    @Test
    public void test() {
        Classe c = new Classe();
        Classe c2 = new Classe();
        c.setType("interface Test");
        c2.setType("Class ok");
        c.addDependencies("ok ..|> Test");
        assertTrue(c.getDependencies().contains("ok ..|> Test"));
    }

    @Test
    public void testSetPackageName() {
        Classe c = new Classe();
        c.setPackageName("com.example.package");
        assertEquals("com.example.package", c.getPackageName());
    }

    @Test
    public void testAddPackageExternes() {
        Classe c = new Classe();
        c.addPackageExternes("com.example.package");
        assertTrue(c.getPackageExternes().contains("com.example.package"));
    }

    //permet de tester la méthode genSquelette ainsi que : genSqueletteSignature(),
    // genSqueletteAttributs(), genSqueletteAvecLink() et genSqueletteMethods()
    //Sinon cette méthode est non fonctionnelle
    @Test
    public void testGenSquelette() {
        Classe c = new Classe();
        c.setPackageName("pack");
        c.setType("interface test");
        c.addAttribut(Classe.DECLARED, "attribut:int");
        c.addMethode(Classe.DECLARED, "+ methode():void");
        c.addDependencies("test ..|> ok");
        String squel ="package pack;\n" +
                "public class test implement ok { \n" +
                "\tpublic int attribut;\n" +
                "\n" +
                "\tpublic void methode(){}\n" +
                "}" ;
        assertEquals(squel, c.genSquelette());

    }
}