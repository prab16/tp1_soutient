// auteurs: Maud El-Hachem
// 2015
package drugware_v2;

import java.util.List;

import javax.swing.JOptionPane;

public class Prescription {

	private String nomMedicament; // le médicament prescrit
	private double dose; // a quelle dose
	private int renouvellementsRestants; // le nombre de renouvellementsRestants
											// possibles
	private String dosesPossible[] = { "10", "20", "50" };
	private int CptDose[]= new int [dosesPossible.length]; //Compter chaque dose

	public Prescription(){
	}
	
	public Prescription(String nomMedicament, double dose,
			int renouvellementsRestants) {

		this.nomMedicament = nomMedicament;
		this.dose = dose;
		this.renouvellementsRestants = renouvellementsRestants;
	}

	/**
	 * @return the nomMedicament
	 */
	public String getMedicamentAPrendre() {
		return nomMedicament;
	}

	/**
	 * @param nomMedicament
	 *            the nomMedicament to set
	 */
	public void setMedicamentAPrendre(String nomMedicament) {
		this.nomMedicament = nomMedicament;
	}

	/**
	 * @return the dose
	 */
	public double getDose() {
		return dose;
	}

	/**
	 * @param dose
	 *            the dose to set
	 */
	public void setDose(double dose) {
		this.dose = dose;
	}

	/**
	 * @return the renouvellementsRestants
	 */
	public int getRenouvellements() {
		return renouvellementsRestants;
	}

	/**
	 * @param renouvellementsRestants
	 *            the renouvellementsRestants to set
	 */
	public void setRenouvellements(int renouvellementsRestants) {
		this.renouvellementsRestants = renouvellementsRestants;
	}

	// retourne un String contenant les caractéristiques de la prescription
	public String afficherPrescription() {
		return new String(this.nomMedicament + " " + this.dose + " Renouv: "
				+ (this.renouvellementsRestants));
	}

	public String afficherDosesPossible(){
		String message = "Dose Possible";
		double doses = getDose();
		double doseCourrante;
		
		
		for(int i= dosesPossible.length -1 ;i>=0 && doses > 0;i--){
			doseCourrante = Double.parseDouble(dosesPossible[i]);
			while(doses >= doseCourrante && doses > 0){
				CptDose[i]++;
				doses -= Double.parseDouble(dosesPossible[i]);
			}
		}
		
		for(int i=CptDose.length -1 ;i>=0;i--){
			if(CptDose[i]>0){
				message += (","+CptDose[i]+" comprimé(s) de "+dosesPossible[i]+" mg");
			}
		}
		
		message +=".";
		
		return message;
		
	}
	
	
	public void creeNouvPrescription(String nomMarque, double doses, int renouvellement, List<Prescription> liste){		
		boolean trouver = false;
				
			for (Prescription prescription : liste) {

				if (prescription.getMedicamentAPrendre().equalsIgnoreCase(nomMarque)) {
					trouver = true;
					prescription.setDose(doses);
					prescription.setRenouvellements(renouvellement);
				}
			}
					
				
			if (!trouver) {				
				Prescription pres = new Prescription(nomMarque,doses,renouvellement);
				liste.add(pres);
			}
	}
}
