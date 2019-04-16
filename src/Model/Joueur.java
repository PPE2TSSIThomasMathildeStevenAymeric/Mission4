package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Joueur {

	protected int JNum;
	protected Club JClub;
	
	protected String prenom;
	protected String nom;
	protected String JSexe;
	protected String JCat;
	protected String nomClub;
	protected int numClub;
	
	static ConnexionSQL bdd = new ConnexionSQL();

	public Joueur(String prenom, String nom, String jSexe, String jCat) {
		super();
		this.prenom = prenom;
		this.nom = nom;
		JSexe = jSexe;
		JCat = jCat;
	}
	
	public Joueur(int pID, String prenom, String nom, String pNomClub, String jSexe, String jCat, int pNumClub) {
		super();
		this.JNum = pID;
		this.prenom = prenom;
		this.nom = nom;
		this.JSexe = jSexe;
		this.JCat = jCat;
		this.nomClub = pNomClub;
		this.numClub = pNumClub;
	}
	
	public static Joueur[] getAllJoueur() throws SQLException {
		ArrayList<Joueur> lesJoueursTmp = new ArrayList<Joueur>();  
		
		//String requete = "SELECT numlicencie, prenomlicencie, nomlicencie, designation_Club, sexelicencie, categorielicencie, id_ClubLicencie FROM licencie INNER JOIN club ON licencie.id_ClubLicencie = club.numAffiliation_Club";
		String requeteStocke = "CALL getAllJoueur()";
		ResultSet tmp = bdd.requeteRetourneDonnees(requeteStocke);
		
		while(tmp.next()) {
			int id = tmp.getInt(1);
			String prenom = tmp.getString(2);
			String nom = tmp.getString(3);
			String nomClub = tmp.getString(4);
			String sexe = tmp.getString(5);
			String categorieNom = tmp.getString(6);
			int numClub = tmp.getInt(7);
			
			Joueur unJoueur = new Joueur(id, prenom, nom, nomClub, sexe, categorieNom, numClub);
			lesJoueursTmp.add(unJoueur);
		}
		Joueur[] lesJoueurs = new Joueur[lesJoueursTmp.size()];
		int i = 0;
		
		for (Joueur tmpJoueur : lesJoueursTmp) {
			lesJoueurs[i] = tmpJoueur;
			i++;
		}
		// Pour chaque user, mettre dans le tableau puis retournera le tableau
		
		return lesJoueurs;
	}
	
	public static Joueur getJoueurByID(int pID) throws SQLException {
		// String requete = "SELECT numlicencie, prenomlicencie, nomlicencie, designation_Club, sexelicencie, categorielicencie, id_ClubLicencie FROM licencie INNER JOIN club ON licencie.id_ClubLicencie = club.numAffiliation_Club WHERE numlicencie=" + pID;
		String requeteStocke = "CALL getJoueurById(" + pID + ")";
		ResultSet tmp = bdd.requeteRetourneDonnees(requeteStocke);
		
		tmp.next();
		
		int id = tmp.getInt(1);
		String prenom = tmp.getString(2);
		String nom = tmp.getString(3);
		String nomClub = tmp.getString(4);
		String sexe = tmp.getString(5);
		String categorieNom = tmp.getString(6);
		int numClub = tmp.getInt(7);
		
		Joueur unJoueur = new Joueur(id, prenom, nom, nomClub, sexe, categorieNom, numClub);
		return unJoueur;
		
	}

	public Joueur() {

	}

	public int getJNum() {
		return JNum;
	}

	public void setJNum(int jNum) {
		JNum = jNum;
	}

	public Club getClub() {
		return JClub;
	}

	public void setClub(Club jClub) {
		JClub = jClub;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getSexe() {
		return JSexe;
	}

	public void setSexe(String jSexe) {
		JSexe = jSexe;
	}

	public String getCat() {
		return JCat;
	}

	public void setCat(String jCat) {
		JCat = jCat;
	}

	public String getNomClub() {
		return this.nomClub;
	}

	public void setNomClub(String jClub) {
		this.nomClub = jClub;
	}
	
	public int getNumClub() {
		return this.numClub;
	}

	public void setNumClub(int pNumClub) {
		this.numClub = pNumClub;
	}



}