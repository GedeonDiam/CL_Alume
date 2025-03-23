package controleur;

import modele.Modele;
import vue.VueConnexion;
import vue.VueGenerale;

public class Alume {
	private static VueGenerale uneVueG;
	private static VueConnexion uneVueConnexion;
	public static Technicien techConnecte;
	
	public static Technicien getTechConnecte() {
		return techConnecte;
	}
	
	public static void setTechConnecte(Technicien techConnecte) {
		Alume.techConnecte = techConnecte;
	}
	
	private static VueGenerale uneVueGenerale;
	
	public static void main(String[] args) {
		uneVueConnexion = new VueConnexion();
	
	}
	
	public static void creerVueGenerale (boolean action) {
		if (action == true) {
			uneVueG = new VueGenerale ();
		}else {
			uneVueG.dispose();
		}
	}
	
	public static void creerVueG(boolean action) {
		if(action == true) {
			uneVueG = new VueGenerale();
		}else {
			uneVueG.dispose();
		}
	}
	
	public static void rendreVisible (boolean action) {
		uneVueConnexion.setVisible(action);
	}
  }