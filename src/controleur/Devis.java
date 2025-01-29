package controleur;

public class Devis {
	private int iddevis;
	private String datedevis, etatdevis;
	
	public Devis(int iddevis, String datedevis, String etatdevis) {
		super();
		this.iddevis = iddevis;
		this.datedevis = datedevis;
		this.etatdevis = etatdevis;
	}

	public Devis (String datedevis, String etatdevis ) {
		
		this.iddevis = 0;
		this.datedevis = datedevis;
		this.etatdevis = etatdevis;
	}
	
     public int getIddevis() {
		return iddevis;
	}

	public void setIddevis(int iddevis) {
		this.iddevis = iddevis;
	}

	public String getDatedevis() {
		return datedevis;
	}

	public void setDatedevis(String datedevis) {
		this.datedevis = datedevis;
	}

	public String getEtatdevis() {
		return etatdevis;
	}

	public void setEtatdevis(String etatdevis) {
		this.etatdevis = etatdevis;
	}

	
	
}
