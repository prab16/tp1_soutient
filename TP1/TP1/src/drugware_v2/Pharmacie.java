// auteurs: Maud El-Hachem
// 2015
package drugware_v2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;


public class Pharmacie {
	private List<Client> lesClients;
	private List<Medicament> lesMedicaments;
	private String message;
	private String err;
	//Pre-load
	private boolean errClients = false;
	private boolean errMedicaments = false;
	private boolean errPrescriptions = false;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public boolean preLoadClient(){
		boolean sortie = lireClients();
		if(!sortie){
			setErrClients(true);
			err = "Le fichier Clients.txt est introuvable. Fermeture de l'application!";
			JOptionPane.showMessageDialog(null,
					err,
					"Problème", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
		return sortie;
	}
	
	public void preLoadMedicament(){
		boolean sortie = lireMedicaments();
		if(!sortie){
			setErrMedicaments(true);
			err = "Le fichier Medicaments.txt est introuvable. Fermeture de l'application!";
			JOptionPane.showMessageDialog(null,
					err,
					"Problème", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
	}
	
	public void preLoadPresciption(){
		boolean sortie = lirePrescriptions();
		if(!sortie){
			setErrPrescriptions(true);
			err = "Le fichier Prescriptions.txt est introuvable. Fermeture de l'application!";
			JOptionPane.showMessageDialog(null,
					err,
					"Problème", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
	}

	public Pharmacie() {
		this.lesMedicaments = new ArrayList<>();
		this.lesClients = new ArrayList<>();
		
		if(preLoadClient()){
			preLoadPresciption();
		}
		
		preLoadMedicament();
	}

	/**
	 * @return the lesClients
	 */
	public List<Client> getLesClients() {
		return lesClients;
	}

	/**
	 * @param lesClients
	 *            the lesClients to set
	 */
	public void setLesClients(List<Client> lesClients) {
		this.lesClients = lesClients;
	}

	/**
	 * @return the lesMedicaments
	 */
	public List<Medicament> getLesMedicaments() {
		return lesMedicaments;
	}

	/**
	 * @param lesMedicaments
	 *            the lesMedicaments to set
	 */
	public void setLesMedicaments(List<Medicament> lesMedicaments) {
		this.lesMedicaments = lesMedicaments;
	}

	public boolean lireClients() {
		boolean sucess = true;
		Fichiers fichier = new Fichiers();
		fichier.lireClients(lesClients);
	//	RemplirListes list = new RemplirListes();
	//	list.remplirListeClients(lesClients);
		if(lesClients.isEmpty()){
			sucess = false;
		}
		
		return sucess;
	}

	public boolean lireMedicaments() {
		boolean sucess = true;
		Fichiers fichier = new Fichiers();
		fichier.lireMedicaments(lesMedicaments);
		//RemplirListes list = new RemplirListes();
		//list.remplirListeMedicaments(lesMedicaments);
		
		if(lesMedicaments.isEmpty()){
			sucess = false;
		}
		
		return sucess;
	}

	public boolean lirePrescriptions() {
		boolean sucess = true;
		Fichiers fichier = new Fichiers();
		fichier.lirePrescriptions(lesClients);
		//RemplirListes list = new RemplirListes();
		//list.remplirListePrescriptions(lesClients);
		if(lesClients.get(0).getPrescriptions().isEmpty()){
			sucess = false;
		}
		
		return sucess;

	}

	public boolean siClientExiste(String NAM) {
		for (Iterator<Client> it = lesClients.iterator(); it.hasNext();) {
			Client itClient = it.next();
			if (itClient.getNAM().equals(NAM)) {
				return true;
			}
		}
		return false;
	}

	public void ajouterClient(String NAM, String nom, String prenom) {
		this.lesClients.add(new Client(NAM, nom, prenom));
	}

	public List<Prescription> getPrescriptionsClient(String NAM) {
		for (Iterator<Client> it = lesClients.iterator(); it.hasNext();) {
			Client client = it.next();
			if (client.getNAM().equals(NAM)) {
				return client.getPrescriptions();
			}
		}
		return null;
	}

	public boolean servirPrescription(String NAM, String medicament) {
		boolean delivree = false;
		for (Iterator<Client> it = lesClients.iterator(); it.hasNext();) {
			Client itClient = it.next();
			if (itClient.getNAM().equals(NAM)) {
				for (Iterator<Prescription> it2 = itClient.getPrescriptions()
						.iterator(); it2.hasNext();) {
					Prescription courante = it2.next();
					if (courante.getMedicamentAPrendre().equalsIgnoreCase(medicament))
						if (courante.getRenouvellements() >= 1) {
							courante.setRenouvellements(courante
									.getRenouvellements() - 1);
							message = courante.afficherDosesPossible();
							delivree = true;
						}
				}
			}
		}
		return delivree;
	}
	

	public boolean trouverInteraction(String medicament1, String medicament2) {
		for (Iterator<Medicament> it = lesMedicaments.iterator(); it.hasNext();) {
			Medicament courant = it.next();
			if (courant.getNomMolecule().equalsIgnoreCase(medicament1))
				for (int i = 0; i < courant.getInteractions().length; i++)
					if (courant.getInteractions()[i]
							.equalsIgnoreCase(medicament2))
						return true;
			if (courant.getNomMolecule().equalsIgnoreCase(medicament2))
				for (int i = 0; i < courant.getInteractions().length; i++)
					if (courant.getInteractions()[i]
							.equalsIgnoreCase(medicament1))
						return true;
		}
		return false;
	}

	public void ecrireClients() {
		Fichiers fichier = new Fichiers();
		fichier.ecrireClients(lesClients);
	}

	public void ecrirePrescriptions() {
		Fichiers fichier = new Fichiers();
		fichier.ecrirePrescriptions(lesClients);
	}

	public boolean isErrClients() {
		return errClients;
	}

	public void setErrClients(boolean errClients) {
		this.errClients = errClients;
	}

	public boolean isErrMedicaments() {
		return errMedicaments;
	}

	public void setErrMedicaments(boolean errMedicaments) {
		this.errMedicaments = errMedicaments;
	}

	public boolean isErrPrescriptions() {
		return errPrescriptions;
	}

	public void setErrPrescriptions(boolean errPrescriptions) {
		this.errPrescriptions = errPrescriptions;
	}
}
