package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controleur.Alume;
import controleur.Controleur;

import controleur.Technicien;

public class VueConnexion extends JFrame implements ActionListener {
	private JButton btSeConnecter = new JButton("Se Connecter");
	private JButton btAnnuler = new JButton("Annuler");
	private JTextField txtEmail = new JTextField("a@gmail.com");
	private JPasswordField txtMdp = new JPasswordField("123");
	
	private JPanel panelForm = new JPanel ();
	public VueConnexion() {
		this.setTitle("Application ALUME_2025");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(100, 100, 500, 600);
		this.setLayout(null);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.darkGray);
		
		//ajout de l'image logo
		ImageIcon uneImage= new ImageIcon("src/images/alume_logo.png");
		JLabel unLogo = new JLabel(uneImage);
		unLogo.setBounds(100, 40, 300, 220);
		this.add(unLogo);
		
		
		//Construction du panel Formulaire
		this.panelForm.setBounds(40, 280, 400, 240);
		this.panelForm.setBackground(Color.darkGray);
		this.panelForm.setLayout(new GridLayout(3,2)); //Matrice de 3 lignes et de 2 colonnes
		this.panelForm.add(new JLabel("Email"));
		this.panelForm.add(this.txtEmail);
		this.panelForm.add(new JLabel("MDP"));
		this.panelForm.add(this.txtMdp);
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btSeConnecter);
		
		this.add(this.panelForm); //ajout du panel dans la fenetre
		
		//Rendre les boutonns ecoutables
		this.btAnnuler.addActionListener(this);
		this.btSeConnecter.addActionListener(this);
		
		this.setVisible(true);
		
    }
	

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btAnnuler) {
            this.txtEmail.setText("");
            this.txtMdp.setText("");
        } else if (e.getSource() == this.btSeConnecter) {
            String email = this.txtEmail.getText();
            String mdp = new String(this.txtMdp.getPassword());

            // Vérification de la présence du technicien dans la base de données
            Technicien unTechnicien = Controleur.selectWhereTechnicien(email, mdp);

            if (unTechnicien == null) {
                JOptionPane.showMessageDialog(this, "Veuillez vérifier vos identifiants !",
                        "Erreur de Connexion", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Bienvenue  " + unTechnicien.getNom()+" " + "  " + unTechnicien.getPrenom(),
                        "Connexion à Orange Application", JOptionPane.INFORMATION_MESSAGE);

                Alume.rendreVisible(false); // Fermeture de la connexion
                Alume.creerVueGenerale(true); // Ouverture du logiciel
            }
        }
    }
}
