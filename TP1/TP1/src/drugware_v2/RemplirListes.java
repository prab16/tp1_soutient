package drugware_v2;

import java.util.List;

public class RemplirListes {
private Client client1 = new Client("ELHM123456782","ElHachem"," Maud");;
private Client client2  = new Client("DUFO12345678","Dufort"," JeanRene" );;
private Client client3 = new Client("PELA12345678","Peladeau","PK" );;
	
	
	public void remplirListeClients(List<Client> lesClients){
		client1 = new Client("ELHM123456782","ElHachem"," Maud");
		client2 = new Client("DUFO12345678","Dufort"," JeanRene" );
		client3 = new Client("PELA12345678","Peladeau","PK" );
		lesClients.add(client1);
		lesClients.add(client2);
		lesClients.add(client3);
	}
	
	public void remplirListeMedicaments(List<Medicament> lesMedicaments){
		
		Medicament med1 = new Medicament();
		med1.setNomMarque("Nexium");
		med1.setNomMolecule("ésoméprazole");
		String[] usage = {"traiter les symptômes de RGO","soigner les brûlures d'estomac","traiter les ulcères d'estomac"};
		med1.setUsages(usage);
		double[] dose = {20,40};
		med1.setDosesPossibles(dose);
		med1.setUnite("mg");
		String[] interaction  = {"clopidogrel","dabigatran","ifosfamide"};
		med1.setInteractions(interaction);
		lesMedicaments.add(med1);
		Medicament med2 = new Medicament();
		med2.setNomMarque("Plavix");
		med2.setNomMolecule("clopidogrel");
		String[] usages = {"prévenir les crises cardiaques, les accidents vasculaires cérébraux et certains autres problèmes circulatoires chez les personnes atteintes d'athérosclérose"};
		med2.setUsages(usages);
		double[] doses = {75,300};
		med2.setDosesPossibles(doses);
		med2.setUnite("mg");
		String[] interactions  = {"fluvoxamine","ibuprofène","naproxène","gemfibrozil"};
		med2.setInteractions(interactions);
		lesMedicaments.add(med2);
	}
	
	public void remplirListePrescriptions(List<Client> lesClients){
		Prescription pres1 = new Prescription("Botox", 50.0, 0);
		client1.getPrescriptions().add(pres1);
		Prescription pres2 = new Prescription("Plavix", 300.0, 3);
		client2.getPrescriptions().add(pres2);
		Prescription pres3 = new Prescription("Nexium", 40.0, 2);
		client2.getPrescriptions().add(pres3);
		Prescription pres4 = new Prescription("Nexium", 20.0, 3);
		client3.getPrescriptions().add(pres4);
	}
}
