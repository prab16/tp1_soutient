package drugware_v2;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PrescriptionTest {

	public Prescription pres;
	public List<Prescription> liste;
	
	@Before
	public void setUp() throws Exception {
		pres= new Prescription();
		liste = new ArrayList<>() ;
	}

	@After
	public void tearDown() throws Exception {
		pres = null;
		liste = null;
	}

	@Test
	public void testAfficherDosesPossible() {
		pres = new Prescription("Nom1",170,4);
		assertEquals("Dose Possible,3 comprimé(s) de 50 mg,1 comprimé(s) de 20 mg.", pres.afficherDosesPossible());
		
	}
	
	@Test
	public void	testcreeNouvPrescription(){
		pres.creeNouvPrescription("nom", 20.0, 69, liste);
		pres = liste.get(0);
		assertEquals(69,pres.getRenouvellements());
		
	}
	
	@Test
	public void	testcreeNouvPrescriptionAddition(){
		liste.add(new Prescription("nom", 45.3, 1));
		pres.creeNouvPrescription("nom", 20.0, 69, liste);
		assertEquals(1,liste.size());
		
	}
	
}
