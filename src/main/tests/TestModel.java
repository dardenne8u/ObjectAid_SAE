import com.example.objectaid_sae.model.Classe;
import com.example.objectaid_sae.model.Fleche;
import com.example.objectaid_sae.model.Model;
import com.example.objectaid_sae.vue.VueCentre;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
public class TestModel {
        private Model model;
        private Classe classe1;
        private Classe classe2;
        private Fleche fleche1;
        private Fleche fleche2;
        private VueCentre vueCentre;

        @BeforeEach
        public void init() {
            model = Model.getModel();
            classe1 = new Classe();
            classe2 = new Classe();
            fleche1 = new Fleche(classe1, classe2,"..|>","",vueCentre);
            fleche2 = new Fleche(classe2, classe1,"--|>","",vueCentre);
        }

        @AfterEach
        public void reset() {
            Model.resetModel();
        }

        @Test
        public void testAddClasse() {
            model.addClasse(classe1);
            assertTrue(model.getClasses().contains(classe1));
        }

        @Test
        public void testGetClasses() {
            model.addClasse(classe1);
            model.addClasse(classe2);
            List<Classe> expected = new ArrayList<>();
            expected.add(classe1);
            expected.add(classe2);
            assertEquals(expected, model.getClasses());
        }

        @Test
        public void testAddFleche() {
            model.addFleche(fleche1);
            assertTrue(model.getFleches().contains(fleche1));
        }

        @Test
        public void testFindFleche() {
            model.addFleche(fleche1);
            model.addFleche(fleche2);
            List<Fleche> expected = new ArrayList<>();
            expected.add(fleche1);
            expected.add(fleche2);
            assertEquals(expected, model.findFleche(classe1));
        }

        @Test
        public void testGetAToB() {
            model.addFleche(fleche1);
            model.addFleche(fleche2);
            List<Fleche> expected = new ArrayList<>();
            expected.add(fleche1);
            assertEquals(expected, model.getAToB(classe1, classe2));
        }
    }

