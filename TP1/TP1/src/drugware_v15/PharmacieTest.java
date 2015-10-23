package drugware_v15;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PharmacieTest {
	private Pharmacie unePharma;
	private List<Client> lesClients;
	private List<Medicament> lesMedicaments;
	private List<Prescription> lesPrescription;

	@Before
	public void setUp() throws Exception {
		unePharma = new Pharmacie();
		this.lesMedicaments = new ArrayList<>();
		this.lesClients = new ArrayList<>();
		this.lesPrescription = new ArrayList<>();		
	}

	@After
	public void tearDown() throws Exception {
		unePharma = null;
	}

	@Test
	public void testPharmacie() {
		assertNotNull(unePharma);
	}

	@Test
	public void testGetLesClients() {
		assertEquals(unePharma.getLesClients(), lesClients);
	}

	@Test
	public void testSetLesClients() {
		unePharma.setLesClients(lesClients);
		assertEquals(unePharma.getLesClients(), lesClients);
	}

	@Test
	public void testGetLesMedicaments() {
		assertEquals(unePharma.getLesMedicaments(), lesMedicaments);
	}

	@Test
	public void testSetLesMedicaments() {
		unePharma.setLesMedicaments(lesMedicaments);
		assertEquals(unePharma.getLesMedicaments(), lesMedicaments);
	}


	@Test
	public void testSiClientExiste() {
		Client client =  new Client("sosa", "sosa", "sosa");
		unePharma.getLesClients().add(client);
		assertTrue(unePharma.siClientExiste("sosa"));
	}
	@Test
	public void testSiClientExisteMovaisNAM() {
		Client client =  new Client("sosa", "sosa", "sosa");
		unePharma.getLesClients().add(client);
		assertFalse(unePharma.siClientExiste("sos"));
	}
	@Test
	public void testNotSiClientExiste() {
		assertFalse(unePharma.siClientExiste("sosa"));
	}

	@Test
	public void testAjouterClient() {
		unePharma.ajouterClient("sosa", "sosa", "sosa");
		assertTrue(unePharma.siClientExiste("sosa"));
	}

	@Test
	public void testGetPrescriptionsClient() {
		Client client =  new Client("sosa", "sosa", "sosa");
		unePharma.getLesClients().add(client);
		assertEquals(unePharma.getPrescriptionsClient("sosa"), lesPrescription);
	}
	@Test
	public void testGetPrescriptionsClientMovaisNAM() {
		Client client =  new Client("sosa", "sosa", "sosa");
		unePharma.getLesClients().add(client);
		assertEquals(unePharma.getPrescriptionsClient("sos"), null);
	}
	@Test
	public void testNotGetPrescriptionsClient() {
		assertEquals(unePharma.getPrescriptionsClient("sosa"), null);
	}
	


	@Test
	public void testServirPrescription() {
		Client client =  new Client("sosa", "sosa", "sosa");
		Prescription pre = new Prescription("sosa", 10, 1);
		unePharma.getLesClients().add(client);
		unePharma.getPrescriptionsClient("sosa").add(pre);
		assertTrue(unePharma.servirPrescription("sosa", "sosa"));
	}
	@Test
	public void testServirPrescriptionPasDoses() {
		Client client =  new Client("sosa", "sosa", "sosa");
		Prescription pre = new Prescription("sosa", 10, 0);
		unePharma.getLesClients().add(client);
		unePharma.getPrescriptionsClient("sosa").add(pre);
		assertFalse(unePharma.servirPrescription("sosa", "sosa"));
	}
	@Test
	public void testServirPrescriptionMovaisMedicament() {
		Client client =  new Client("sosa", "sosa", "sosa");
		Prescription pre = new Prescription("sosa", 10, 0);
		unePharma.getLesClients().add(client);
		unePharma.getPrescriptionsClient("sosa").add(pre);
		assertFalse(unePharma.servirPrescription("sosa", "sos"));
	}
	@Test
	public void testServirPrescriptionPasDosesMovaisNAM() {
		Client client =  new Client("sosa", "sosa", "sosa");
		Prescription pre = new Prescription("sosa", 10, 0);
		unePharma.getLesClients().add(client);
		unePharma.getPrescriptionsClient("sosa").add(pre);
		assertFalse(unePharma.servirPrescription("sos", "sosa"));
	}

	@Test
	public void testTrouverInteraction() {
		RemplirListes list = new RemplirListes();
		list.remplirListeMedicaments(lesMedicaments);
		unePharma.setLesMedicaments(lesMedicaments);
		assertTrue(unePharma.trouverInteraction("�som�prazole", "clopidogrel"));
		
	}
	@Test
	public void testTrouverInteractionInverse() {
		RemplirListes list = new RemplirListes();
		list.remplirListeMedicaments(lesMedicaments);
		unePharma.setLesMedicaments(lesMedicaments);
		assertTrue(unePharma.trouverInteraction("clopidogrel", "�som�prazole"));
		
	}
	@Test
	public void testTrouverFalseInteraction() {
		RemplirListes list = new RemplirListes();
		list.remplirListeMedicaments(lesMedicaments);
		unePharma.setLesMedicaments(lesMedicaments);
		assertFalse(unePharma.trouverInteraction("�som�prazole", "�som�prazole"));
		
	}
	
	

	


}
