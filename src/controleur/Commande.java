package controleur;

public class Commande {
	private int idcommande;
	private String codecom, etatcom, codedevis;
	
	public Commande(int idcommande, String codecom, String etatcom, String codedevis) {
		
		this.idcommande = idcommande;
		this.codecom = codecom;
		this.etatcom = etatcom;
		this.codedevis = codedevis;	
	}
    public Commande( String codecom, String etatcom, String codedevis) {
		
		this.idcommande = 0;
		this.codecom = codecom;
		this.etatcom = etatcom;
		this.codedevis = codedevis;
	}

	public int getIdcommande() {
		return idcommande;
	}

	public void setIdcommande(int idcommande) {
		this.idcommande = idcommande;
	}

	public String getCodecom() {
		return codecom;
	}

	public void setCodecom(String codecom) {
		this.codecom = codecom;
	}

	public String getEtatcom() {
		return etatcom;
	}

	public void setEtatcom(String etatcom) {
		this.etatcom = etatcom;
	}

	public String getCodedevis() {
		return codedevis;
	}

	public void setCodedevis(String codedevis) {
		this.codedevis = codedevis;
	}

	
	
}
