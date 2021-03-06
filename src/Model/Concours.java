package Model;

import java.util.ArrayList;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Concours {

	protected String ConcNom;
	protected int ConcNum;
	protected Date ConcDate;
	protected String ConcNature;
	protected String ConcCat;
	protected String ConcSexe;
	protected int ConcTourNum;
	protected int ConcTermine;

	public Concours() {

	}

	public Concours(int pID, String ConcNom, Date concDate, String concNature, String concCat, String concSexe) {

		this.ConcNom = ConcNom;
		this.ConcDate = concDate;
		this.ConcNature = concNature;
		this.ConcCat = concCat;
		this.ConcSexe = concSexe;
		this.ConcNum = pID;
		
	}
	
	public Concours(int pID, String ConcNom, Date concDate, String concNature, int concCat, String concSexe) {

		this.ConcNom = ConcNom;
		this.ConcDate = concDate;
		this.ConcNature = concNature;
		
		if (concCat == 1) {
			this.ConcCat = "Enfant";
		} else if (concCat == 2) {
			this.ConcCat = "Adulte";
		} else if (concCat == 3) {
			this.ConcCat = "Senior";
		}
		
		this.ConcSexe = concSexe;
		this.ConcNum = pID;
		
	}
	
	public Concours(int pID, String ConcNom, Date concDate, String concNature, String concCat, String concSexe, int concTourNum, int ConcTermineBool) {

		this.ConcNom = ConcNom;
		this.ConcDate = concDate;
		this.ConcNature = concNature;
		this.ConcCat = concCat;
		this.ConcSexe = concSexe;
		this.ConcNum = pID;
		this.ConcTourNum = concTourNum;
		this.ConcTermine = ConcTermineBool;
		
	}
	
	public static Concours getConcoursDB(int pID) throws SQLException {
		Concours unConcours = null;
		
		ConnexionSQL bdd = new ConnexionSQL();

		// On va rechercher l'id dans la bdd
		// String requete2 = "SELECT id_concours,date_concours,nature,categorie,sexe FROM concours WHERE id_concours=" + pID;
		String requete2Stocke =  "CALL getConcoursById(" + pID + ")";
		ResultSet result = bdd.requeteRetourneDonnees(requete2Stocke);
		result.next();
		
		int pID_database = result.getInt(1);
		Date pDate = result.getDate(2);
		String pNature = result.getString(3);
		String pCategorie = result.getString(4);
		String pSexe = result.getString(5);
		int pTourNum = result.getInt(6);
		int pTerminer = result.getInt(7);
		
		unConcours = new Concours(pID_database, "test", pDate, pNature, pCategorie, pSexe, pTourNum, pTerminer);
		
		bdd.fermerConnexion();
		
		return unConcours;
	}

	public String getConcNom() {
		return ConcNom;
	}

	public void setConcNom(String ConcNom) {
		this.ConcNom = ConcNom;
	}

	public int getConcNum() {
		return ConcNum;
	}

	public void setConcNum(int concNum) {
		ConcNum = concNum;
	}

	public Date getConcDate() {
		return ConcDate;
	}

	public void setConcDate(Date concDate) {
		ConcDate = concDate;
	}

	public String getConcNature() {
		return ConcNature;
	}

	public void setConcNature(String concNature) {
		ConcNature = concNature;
	}

	public String getConcCat() {
		return ConcCat;
	}

	public void setConcCat(String concCat) {
		ConcCat = concCat;
	}

	public String getConcSexe() {
		return this.ConcSexe;
	}

	public void setConcSexe(String concSexe) {
		this.ConcSexe = concSexe;
	}
	

	@Override
	public String toString() {
		return "Concours [Num=" + ConcNum + ", Nature=" + ConcNature + ", Categorie=" + ConcCat + ", Sexe="
				+ ConcSexe + "]";
	}

	public int getConcTourNum() {
		return ConcTourNum;
	}

	public void setConcTourNum(int concTourNum) {
		ConcTourNum = concTourNum;
	}

	public int isConcTermine() {
		return ConcTermine;
	}

	public void setConcTermine(int concTermine) throws SQLException {
		ConcTermine = concTermine;
		
		String requeteStocke = "CALL updateConcoursTermine(" + this.ConcNum + "," + concTermine + ")";
		ConnexionSQL bdd = new ConnexionSQL();
		bdd.requeteSansDonnes(requeteStocke);
		
		//bdd.fermerConnexion();
	}
	
	public ArrayList<Equipe> equipeEnListe() throws SQLException {
		ArrayList<Equipe> lesEquipesTmp = new ArrayList<Equipe>();
		
		String requeteStocke = "CALL getAllEquipeGagnanteLastTour(" + (this.ConcTourNum - 1) + "," + this.getConcNum() + ")";
		ConnexionSQL bdd = new ConnexionSQL();
		ResultSet resultbdd = bdd.requeteRetourneDonnees(requeteStocke);
			
		while(resultbdd.next()) {
			
			int id_Equipe = resultbdd.getInt(1);
				
			Equipe uneEquipe = Equipe.getEquipeByID(id_Equipe);
			lesEquipesTmp.add(uneEquipe);   
		}
		
		bdd.fermerConnexion();
		
		return lesEquipesTmp;
	}

	public void increaseTourNum() throws SQLException {
		this.ConcTourNum = this.ConcTourNum + 1;
		
		String requeteStocke = "CALL updateTourConcours(" + this.ConcNum + "," + this.ConcTourNum + ")";
		ConnexionSQL bdd = new ConnexionSQL();
		bdd.requeteSansDonnes(requeteStocke);
		
		//bdd.fermerConnexion();
	}
	
	public Equipe findEquipeGagnante() throws SQLException {
		Equipe equipeGagnante = null;
		
		ConnexionSQL bdd = new ConnexionSQL();
		String requeteStocke2 = "CALL getEquipeGagnanteConcours(" + this.ConcNum + "," + this.ConcTourNum + ")";
		ResultSet tmp = bdd.requeteRetourneDonnees(requeteStocke2);
		tmp.next();
		int id_Equipe = tmp.getInt(1);
		
		equipeGagnante = Equipe.getEquipeByID(id_Equipe);
		
		bdd.fermerConnexion();
		
		return equipeGagnante;
	}
	
	public boolean verifcationIsDernierTour() throws SQLException {
		// Si = 1, dernier tour
		boolean reponse = false;
		
		ConnexionSQL bdd = new ConnexionSQL();
		String requeteStocke2 = "CALL getNbMatchDernierTour (" + this.ConcNum + "," + this.ConcTourNum + ")";
		ResultSet tmp = bdd.requeteRetourneDonnees(requeteStocke2);
		tmp.next();
		int count = tmp.getInt(1);
		
		if (count == 1) {
			reponse = true;
		}
		bdd.fermerConnexion();
		return reponse;
		
	}
	
}