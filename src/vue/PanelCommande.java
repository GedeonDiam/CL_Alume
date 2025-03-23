package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import controleur.Commande;
import controleur.Controleur;
import controleur.Tableau;

public class PanelCommande extends PanelPrincipal implements ActionListener {

    public PanelCommande(String titre) {
		super(titre);
		// TODO Auto-generated constructor stub
	}

	private JPanel panelForm = new JPanel();
    private JPanel panelListe = new JPanel();

    private JComboBox<String> EtatCom = new JComboBox<>(new String[] { "en attente", "annulee", "livree", "en preparation", "confirmee" });
    private JTextField txtCodeDevis = new JTextField();

    private JButton btAnnuler = new JButton("Annuler");
    private JButton btValider = new JButton("Valider");
    private JButton btSupprimer = new JButton("Supprimer");

    private JTable uneTable;
    private Tableau unTableau;

    private JPanel panelFiltre = new JPanel();
    private JTextField txtFiltre = new JTextField();
    private JButton btFiltrer = new JButton("Filtrer");

    private JLabel lbNbCommandes = new JLabel();

    public PanelCommande() {
        super("Gestion des commandes");

        // Installation du bouton supprimer
        this.btSupprimer.setBounds(40, 150, 300, 40);
        this.add(this.btSupprimer);
        this.btSupprimer.setVisible(false);
        this.btSupprimer.setBackground(Color.red);
        this.btSupprimer.addActionListener(this);

        // Formulaire
        this.panelForm.setBackground(new Color(59, 125, 221));
        this.panelForm.setBounds(40, 80, 300, 100);
        this.panelForm.setLayout(new GridLayout(3, 2));

        this.panelForm.add(new JLabel("État de la commande"));
        this.panelForm.add(EtatCom);

        this.panelForm.add(new JLabel("Code Devis"));
        this.panelForm.add(txtCodeDevis);

        this.panelForm.add(btAnnuler);
        this.panelForm.add(btValider);

        this.add(this.panelForm);

        this.btAnnuler.addActionListener(this);
        this.btValider.addActionListener(this);

        // JTable
        String entetes[] = { "ID Commande", "État", "Code Devis" };
        this.unTableau = new Tableau(this.obtenirDonnees(""), entetes);
        this.uneTable = new JTable(this.unTableau);
        JScrollPane uneScroll = new JScrollPane(this.uneTable);
        uneScroll.setBounds(400, 80, 500, 220);
        this.add(uneScroll);

        // MouseListener pour JTable
        this.uneTable.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int numLigne;
                if (e.getClickCount() >= 1) {
                    numLigne = uneTable.getSelectedRow();

                    EtatCom.setSelectedItem(unTableau.getValueAt(numLigne, 1).toString());
                    txtCodeDevis.setText(unTableau.getValueAt(numLigne, 2).toString());

                    btSupprimer.setVisible(true);
                    btValider.setText("Modifier");
                }
            }

            @Override public void mousePressed(MouseEvent e) {}
            @Override public void mouseReleased(MouseEvent e) {}
            @Override public void mouseEntered(MouseEvent e) {}
            @Override public void mouseExited(MouseEvent e) {}
        });

        // Panel Filtre
        this.panelFiltre.setBackground(new Color(59, 125, 221));
        this.panelFiltre.setBounds(400, 50, 500, 20);
        this.panelFiltre.setLayout(new GridLayout(1, 3));
        this.panelFiltre.add(new JLabel("Filtrer par État:"));
        this.panelFiltre.add(txtFiltre);
        this.panelFiltre.add(btFiltrer);
        btFiltrer.addActionListener(this);
        this.add(panelFiltre);

        // Label nombre de commandes
        lbNbCommandes.setBounds(550, 320, 400, 20);
        lbNbCommandes.setText("Nombre de commandes : " + unTableau.getRowCount());
        this.add(lbNbCommandes);
    }

    public Object[][] obtenirDonnees(String filtre) {
        ArrayList<Commande> lesCommandes = (filtre.equals("")) ? 
            Controleur.selectAllCommandes() : 
            Controleur.selectLikeCommandes(filtre);

        Object[][] matrice = new Object[lesCommandes.size()][3];
        int i = 0;
        for (Commande com : lesCommandes) {
            matrice[i][0] = com.getIdcommande();
            matrice[i][1] = com.getEtatcom();
            matrice[i][2] = com.getCodedevis();
            i++;
        }
        return matrice;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btAnnuler) {
            this.txtCodeDevis.setText("");
            
            this.btSupprimer.setVisible(false);
            this.btValider.setText("Valider");
            
        } else if (e.getSource() == this.btValider && this.btValider.getText().equals("Valider")) {
            
            String etat = this.EtatCom.getSelectedItem().toString();
            String codeDevis = this.txtCodeDevis.getText();
            
            // Création d'une nouvelle commande
            Commande uneCommande = new Commande(etat, codeDevis);
            
            // Insertion dans la base de données
            Controleur.insertCommande(uneCommande);
            
            JOptionPane.showMessageDialog(this, "Commande ajoutée avec succès.");
            
               
                // Actualisation du tableau
                this.unTableau.setDonnees(this.obtenirDonnees(""));
                
                // Réinitialisation des champs
                this.EtatCom.setSelectedIndex(0);
                this.txtCodeDevis.setText("");
                
            
        } else if (e.getSource() == this.btValider && this.btValider.getText().equals("Modifier")) {
            
           // on récupere les données y compris d'id
        	
        	int numLigne, idcommande; 
        	numLigne = this.uneTable.getSelectedRow();
            
            int idCommande = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString());
            String etat = this.EtatCom.getSelectedItem().toString();
            String codeDevis = this.txtCodeDevis.getText();
            
            if (codeDevis.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Le code devis ne peut pas être vide.");
                return;
            }
            
           
                
                // Modification de la commande
            Commande uneCommande = new Commande(etat, codeDevis);
                Controleur.updateCommande(uneCommande);
                
                JOptionPane.showMessageDialog(this, "Commande modifiée avec succès.");
                
                // Actualisation du tableau
                this.unTableau.setDonnees(this.obtenirDonnees(""));
                
                // Réinitialisation des champs
                this.EtatCom.setSelectedIndex(0);
                this.txtCodeDevis.setText("");
                
                this.btSupprimer.setVisible(false);
                this.btValider.setText("Valider");
           
            
        } else if (e.getSource() == this.btSupprimer) {
            
            int numLigne = this.uneTable.getSelectedRow();
            
            if (numLigne == -1) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une commande à supprimer.");
                return;
            }
            
            int idCommande = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString());
            
            int choix = JOptionPane.showConfirmDialog(this, "Supprimer cette commande ?", "Confirmation", JOptionPane.YES_NO_OPTION);
            
            if (choix == JOptionPane.YES_OPTION) {
                Controleur.deleteCommande(idCommande);
                
                JOptionPane.showMessageDialog(this, "Commande supprimée avec succès.");
                
                // Actualisation du tableau
                this.unTableau.setDonnees(this.obtenirDonnees(""));
                
                // Réinitialisation des champs
                this.EtatCom.setSelectedIndex(0);
                this.txtCodeDevis.setText("");
                
                this.btSupprimer.setVisible(false);
                this.btValider.setText("Valider");
            }
            
        } else if (e.getSource() == this.btFiltrer) {
            String filtre = this.txtFiltre.getText();
            
            // Actualisation du tableau avec le filtre
            this.unTableau.setDonnees(this.obtenirDonnees(filtre));
        }
    }

}
