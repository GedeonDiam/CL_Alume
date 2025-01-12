package controleur;

public class Particulier {
	private int idparticulier;
	private String nom, prenom;
	
public Particulier( String nom, String prenom, int idparticulier) {
		
		this.idparticulier = idparticulier;
		this.nom = nom;
		this.prenom = prenom;
}

public Particulier(String nom, String prenom) {
	
	this.idparticulier = 0;
	this.nom = nom;
	this.prenom = prenom;
}

public int getIdparticulier() {
	return idparticulier;
}

public void setIdparticulier(int idparticulier) {
	this.idparticulier = idparticulier;
}

public String getNom() {
	return nom;
}

public void setNom(String nom) {
	this.nom = nom;
}

public String getPrenom() {
	return prenom;
}

public void setPrenom(String prenom) {
	this.prenom = prenom;
}


		
}
