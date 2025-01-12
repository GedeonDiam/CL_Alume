package controleur;

public class Technicien {
	private int idtechnicien;
	private String nom, prenom, specialite, email;
	
	public Technicien(int idtechnicien, String nom, String prenom, String specialite, String email) {
		
		this.idtechnicien = idtechnicien;
		this.nom = nom;
		this.prenom = prenom;
		this.specialite = specialite;
		this.email = email;
		
	}

	public int getIdtechnicien() {
		return idtechnicien;
	}

	public void setIdtechnicien(int idtechnicien) {
		this.idtechnicien = idtechnicien;
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

	public String getSpecialite() {
		return specialite;
	}

	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Technicien( String nom, String prenom, String specialite, String email) {
		
		this.idtechnicien = 0;
		this.nom = nom;
		this.prenom = prenom;
		this.specialite = specialite;
		this.email = email;
	}
}
