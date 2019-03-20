package Model;

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

	public Concours() {

	}

	public Concours(int pID, String ConcNom, Date concDate, String concNature, String concCat, String concSexe)
			throws SQLException {

		this.ConcNom = ConcNom;
		this.ConcDate = concDate;
		this.ConcNature = concNature;
		this.ConcCat = concCat;
		this.ConcSexe = concSexe;
		this.ConcNum = pID;
		
	}
	
	public static Concours getConcoursDB(int pID) throws SQLException {
		Concours unConcours = null;
		
		ConnexionSQL bdd = new ConnexionSQL();

		// On va rechercher l'id dans la bdd
		String requete2 = "SELECT id_concours,date_concours,nature,categorie,sexe FROM Concours WHERE id_concours=" + pID;
		ResultSet result = bdd.requeteRetourneDonnees(requete2);
		result.next();
		
		int pID_database = result.getInt(1);
		Date pDate = result.getDate(2);
		String pNature = result.getString(3);
		String pCategorie = result.getString(4);
		String pSexe = result.getString(5);
		
		unConcours = new Concours(pID_database, "test", pDate, pNature, pCategorie, pSexe);
		
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

}