package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statementb;
import java.util.ArrayList;

import controleur.Client;
import controleur.Technicien;

public class Modele {
	private static Connexion uneConnexion= new Connexion ("localhost:3307", "alume", "root", "");
	
	/************************Gestion des clients*************************/
	
	public static void insertClient (Client unClient) {
		String requete ="insert into client values (null,'"+ unClient.getNom()+"','"+unClient.getVille() + "','"+unClient.getRue()+ "','" +unClient.getNumrue()+ "','"+unClient.getCodepostal()+ "','" + unClient.getEmail()+ "','"+ unClient.getTel()+"');";
		executerRequete(requete);
	}
	
	public static void deleteClient (int idclient) {
		String requete ="delete from client where idclient ="+ idclient +";";
		executerRequete (requete);
	}
	
	public static void updateClient (Client unClient) {
		String requete ="update client set nom = '" + unClient.getNom()
		+"', ville = '" + unClient.getVille() + "', rue = '" + unClient.getRue() 
		+ "',  email = '" + unClient.getEmail() + "', tel = '" + unClient.getTel() + "', numrue = '" + unClient.getNumrue()
		+ " where idclient = " +unClient.getIdclient()+";";
		executerRequete (requete); 
	}
	
	public static Client selectWhereClient (int idclient) {
		String requete = "select * from client where idclient =" + idclient +";";
		Client unClient =null;
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement();
			ResultSet lesResultats = unStat.executeQuery(requete); //fetch
			
			// parcours des resultats et extraction d'un client
			
			if (lesResultats.next()) {
				//instanciation d'un client
				unClient = new Client (
							lesResultats.getInt ("idclient"), lesResultats.getString("nom"),
							lesResultats.getString ("ville"), lesResultats.getString("rue"),
							lesResultats.getString ("email"), lesResultats.getString("tel"),
							lesResultats.getString("numrue"), requete
							);
			}
			unStat.close();
			uneConnexion.seDeconnecter();
		}
		
		catch (SQLException exp) {
			System.out.println ("Erreur d'execution de la requette : "+ requete);
		}
		return unClient;
		
	}
	
	public static ArrayList<Client> selectAllClients () {
		
		
		String requete = "select * from client; ";
		ArrayList<Client> lesClients = new ArrayList<Client>();
	
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement();
			ResultSet lesResultats = unStat.executeQuery(requete); //fetchAll
			
			// parcours des resultats et extraction des clients
			
			while (lesResultats.next()) {
				//instanciation d'un client
				Client unClient = new Client (
						lesResultats.getInt ("idclient"), lesResultats.getString("nom"),
						lesResultats.getString ("ville"), lesResultats.getString("rue"),
						lesResultats.getString ("email"), lesResultats.getString("tel"),
						lesResultats.getString("numrue"), requete
						);
				//ajout du client dans lesClients
				lesClients.add(unClient);
			}
			unStat.close();
			uneConnexion.seDeconnecter();
		}
		
		catch (SQLException exp) {
			System.out.println ("Erreur d'execution de la requette : "+ requete);
		}
		return lesClients;
		
	}
	
	
	public static ArrayList<Client> selectLikeClients (String filtre) {
		
		
		String requete = "select * from client where nom like '%"+filtre+"%' or  "
				+ "ville like '%"+ filtre+"%' or  "
				+ "codepostal like '%"+ filtre+"%' or  "
				+ "rue like '%"+ filtre+"%' or  "
				+ "numrue like '%"+ filtre+"%' or  "
				+ "email like '%"+ filtre+"%' or  "
				 + "tel like '%"+ filtre+"%';";
		ArrayList<Client> lesClients = new ArrayList<Client>();
		
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement();
			ResultSet lesResultats = unStat.executeQuery(requete); //fetchAll
			
			// parcours des resultats et extraction des clients
			
			while (lesResultats.next()) {
				//instanciation d'un client
				Client unClient = new Client (
						lesResultats.getInt ("idclient"), lesResultats.getString("nom"),
						lesResultats.getString ("ville"), lesResultats.getString("rue"),
						lesResultats.getString ("email"), lesResultats.getString("tel"),
						lesResultats.getString("numrue"), requete
						);
				//ajut du client dans lesClients
				lesClients.add(unClient);
			}
			unStat.close();
			uneConnexion.seDeconnecter();
		}
		
		catch (SQLException exp) {
			System.out.println ("Erreur d'execution de la requette : "+ requete);
		}
		return lesClients;
			
		}
	
	
	
	
	
	
	
	
/************************Gestion des Techniciens*************************/
	
	public static void insertTechnicien (Technicien unTechnicien) {
		String requete ="insert into technicien values (null,'"+ unTechnicien.getNom()+"','"+unTechnicien.getPrenom() +
				"','"+unTechnicien.getSpecialite()+ "','" + unTechnicien.getEmail()+"');";
		executerRequete(requete);
		
	}
	
	public static void deleteTechnicien (int idtechnicien) {
		String requete ="delete from technicien where idtechnicien ="+ idtechnicien +";";
		executerRequete (requete);
	}
	public static void updateTechnicien (Technicien unTechnicien) {
		String requete ="update technicien set nom='"+ unTechnicien.getNom()+"', prenom='"+unTechnicien.getPrenom() +
				"',specialite='"+unTechnicien.getSpecialite()+ "',email='" + unTechnicien.getEmail()+ " where idclient = " +unTechnicien.getIdtechnicien()+";";
		executerRequete(requete);
		
	}
	
	
	public static Technicien selectWhereTechnicien (int idtechnicien) {
		String requete = "select * from technicien where idtechnicien =" + idtechnicien +";";
		Technicien unTechnicien =null;
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement();
			ResultSet lesResultats = unStat.executeQuery(requete); //fetch
			
			// parcours des resultats et extraction d'un client
			
			if (lesResultats.next()) {
				//instanciation d'un client
				unTechnicien = new Technicien (
							lesResultats.getInt ("idtechnicien"), lesResultats.getString("nom"),
							lesResultats.getString ("prenom"), lesResultats.getString("specialite"),
							lesResultats.getString ("email")
							);
			}
			unStat.close();
			uneConnexion.seDeconnecter();
		}
		
		catch (SQLException exp) {
			System.out.println ("Erreur d'execution de la requette : "+ requete);
		}
		return unTechnicien;
		
	}
	
	public static ArrayList<Technicien> selectAllTechniciens () {
		
		
		String requete = "select * from technicien; ";
		ArrayList<Technicien> lesTechniciens = new ArrayList<Technicien>();
	
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement();
			ResultSet lesResultats = unStat.executeQuery(requete); //fetchAll
			
			// parcours des resultats et extraction des clients
			
			while (lesResultats.next()) {
				//instanciation d'un client
				Technicien unTechnicien = new Technicien (
						lesResultats.getInt ("idtechnicien"), lesResultats.getString("nom"),
						lesResultats.getString ("prenom"), lesResultats.getString("specialite"),
						lesResultats.getString ("email")
						);
				 
				//ajout du client dans lesTechniciens
				lesTechniciens.add(unTechnicien);
			}
			unStat.close();
			uneConnexion.seDeconnecter();
		}
		
		catch (SQLException exp) {
			System.out.println ("Erreur d'execution de la requette : "+ requete);
		}
		return lesTechniciens;
		
	}
	
public static ArrayList<Technicien> selectLikeTechniciens (String filtre) {
	
	
	String requete = "select * from technicien where nom like '%"+filtre+"%' or  "
			+ "prenom like '%"+ filtre+"%' or  "
			+ "adresse like '%"+ filtre+"%' or  "
			 + "specialite like '%"+ filtre+"%'; ";
	
	ArrayList<Technicien> lesTechniciens = new ArrayList<Technicien>();
	
	try {
		uneConnexion.seConnecter();
		Statement unStat = uneConnexion.getMaConnexion().createStatement();
		ResultSet lesResultats = unStat.executeQuery(requete); //fetchAll
		
		// parcours des resultats et extraction des clients
		
		while (lesResultats.next()) {
			//instanciation d'un client
			Technicien unTechnicien = new Technicien (
					lesResultats.getInt ("idtechnicien"), lesResultats.getString("nom"),
					lesResultats.getString ("prenom"), lesResultats.getString("specialite"),
					lesResultats.getString ("email")
					);
			//ajout du client dans les Techniciens
			lesTechniciens.add(unTechnicien);
		}
		unStat.close();
		uneConnexion.seDeconnecter();
	}
	
	catch (SQLException exp) {
		System.out.println ("Erreur d'execution de la requette : "+ requete);
	}
	return lesTechniciens;
		
	}
	
}
