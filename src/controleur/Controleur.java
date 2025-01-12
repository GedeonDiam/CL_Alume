package controleur;

import java.util.ArrayList;

import modele.Modele;


public class Controleur {
	/**********************Gestion des clients *****************************/
	public static void insertClient (Client unClient) {
		//on va controller les données avant insertion
		
		
		//on appelle le modele pour insertion
		Modele.insertClient(unClient);
	}
	
}