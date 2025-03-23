package vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controleur.Controleur;
import controleur.Alume;
import controleur.Technicien;

public class PanelProfil extends PanelPrincipal implements ActionListener {
	
	private Technicien techConnecte;
	private JTextArea txtInfos = new JTextArea();
	
	private JButton btModifier = new JButton("Modifier");
	
	private JPanel PanelForm = new JPanel();
	
	private JTextField txtNom = new JTextField();
	private JTextField txtPrenom = new JTextField();
	private JTextField txtEmail = new JTextField();
	private JTextField txtSpecialite = new JTextField();
	private JPasswordField txtMdp1 = new JPasswordField();
	private JPasswordField txtMdp2 = new JPasswordField();
	
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btValider = new JButton("Valider");
	
	
	public PanelProfil() {
		super("Gestion du profil");
		
		this.techConnecte = Alume.getTechConnecte();
		
		this.txtInfos.setText(
				"\n________________________INFOS PROFIL________________________\n\n"
				+ "	Nom Technicien :" + this.techConnecte.getNom()+ "\n\n"
				+ "	Spécialité : " + this.techConnecte.getSpecialite()+ "\n\n"
				+ "	Email Technicien : " + this.techConnecte.getEmail()+ "\n\n"
				+ "_____________________________________________________\n");
		
		this.txtInfos.setBackground(Color.cyan);
		this.txtInfos.setBounds(50, 100, 300, 250);
		this.txtInfos.setEditable(false);
		this.add(this.txtInfos);
		
		this.btModifier.setBounds(150, 380, 120, 40);
		this.add(this.btModifier);
		
		//installation du panel form
		this.PanelForm.setBackground(Color.cyan);
		this.PanelForm.setBounds(400, 100, 400, 300);
		this.PanelForm.setLayout(new GridLayout(8,2));
		
		this.PanelForm.add(new JLabel("Nom du technicien :"));
		this.PanelForm.add(this.txtNom);
		this.PanelForm.add(new JLabel("Spécialité du technicien :"));
		this.PanelForm.add(this.txtSpecialite);
		this.PanelForm.add(new JLabel("Email du technicien :"));
		this.PanelForm.add(this.txtEmail);
		this.PanelForm.add(new JLabel("Mot de passe : "));
		this.PanelForm.add(this.txtMdp1);
		this.PanelForm.add(new JLabel("Confirmation :"));
		this.PanelForm.add(this.txtMdp2);
		this.PanelForm.add(this.btAnnuler);
		this.PanelForm.add(this.btValider);
		this.add(this.PanelForm);
		
		this.PanelForm.setVisible(false);
		
		//rendre les boutons ecoutables 
		this.btModifier.addActionListener(this);
		this.btAnnuler.addActionListener(this);
		this.btValider.addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btModifier) {
			this.PanelForm.setVisible(true);
			this.txtNom.setText(techConnecte.getNom());
			this.txtPrenom.setText(techConnecte.getPrenom());
			this.txtSpecialite.setText(techConnecte.getSpecialite());
			this.txtEmail.setText(techConnecte.getEmail());
		}
		else if(e.getSource() == this.btAnnuler) {
			this.PanelForm.setVisible(false);
		}
		else if(e.getSource() == this.btValider) {
			String nom = this.txtNom.getText();
			String specialite = this.txtSpecialite.getText();
			String email = this.txtEmail.getText();
			String mdp1 = new String (this.txtMdp1.getPassword());
			String mdp2 = new String (this.txtMdp2.getPassword());
			
			if(nom.equals("")  || specialite.equals("") || email.equals("")
					|| mdp1.equals("") || mdp2.equals("")) {
				JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.");
			}else if(! mdp1.equals(mdp2)) {
				JOptionPane.showMessageDialog(this, "Veuillez vérifier les mdps");
			}else {
				//on actualise les données du technicien
				techConnecte.setNom(nom);
				techConnecte.setSpecialite(specialite);
				techConnecte.setEmail(email);
				techConnecte.setMdp(mdp1);
				
				//on réalise la modification des données dans la BDD
				Controleur.updateTechnicien(techConnecte);
				
				// on actualise le Text Area
				this.txtInfos.setText(
						"\n________________________INFOS PROFIL________________________\n\n"
						+ "	Nom Technicien :" + this.techConnecte.getNom()+ "\n\n"
						+ "	Spécialité : " + this.techConnecte.getSpecialite()+ "\n\n"
						+ "	Email Technicien : " + this.techConnecte.getEmail()+ "\n\n"
						+ "_____________________________________________________\n");
				
				//on rend le formulaire invisible
				this.PanelForm.setVisible(false);
			
			}
			
			
		}
	}

}
