package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Equipe {

	protected int idEquipe;
	protected String nomEquipe;
	protected int idConcours;
	protected Concours leConcours;
	protected ArrayList<Joueur> lesJoueurs;
	
	public Equipe(String nomEquipe, int idConcours, ArrayList<Joueur> desJoueurs ) {
		this.nomEquipe = nomEquipe;
		this.idConcours = idConcours;
		this.lesJoueurs = desJoueurs;
	}
	
	public Equipe(int id_equipe, String nomEquipe, int idConcours, ArrayList<Joueur> desJoueurs ) {
		this.idEquipe = id_equipe;
		this.nomEquipe = nomEquipe;
		this.idConcours = idConcours;
		this.lesJoueurs = desJoueurs;
	}

	public int getIdEquipe() {
		return idEquipe;
	}

	public void setIdEquipe(int idEquipe) {
		this.idEquipe = idEquipe;
	}

	public String getNomEquipe() {
		return nomEquipe;
	}

	public void setNomEquipe(String nomEquipe) {
		this.nomEquipe = nomEquipe;
	}

	public int getIdConcours() {
		return idConcours;
	}

	public void setIdConcours(int idConcours) {
		this.idConcours = idConcours;
	}

	public ArrayList<Joueur> getLesJoueurs() {
		return lesJoueurs;
	}

	public void setLesJoueurs(ArrayList<Joueur> lesJoueurs) {
		this.lesJoueurs = lesJoueurs;
	}
	
	public void addJoueur(Joueur unJoueur) {
		this.lesJoueurs.add(unJoueur);
	}
	
	public String ToString() {
		return this.nomEquipe;
	}
	
	public void addToDB() throws SQLException {
		String requeteStocke = "CALL ajouterEquipe('" + this.nomEquipe + "'," + this.idConcours + ")";
		ConnexionSQL bdd = new ConnexionSQL();
		bdd.requeteSansDonnes(requeteStocke);
		
		String requete2Stocke = "CALL getLastEquipeID()";
		ResultSet pId = bdd.requeteRetourneDonnees(requete2Stocke);
		pId.next();
		this.idEquipe = pId.getInt(1);
		
		
		for(Joueur pJoueur : lesJoueurs) {
			String requete3Stocke = "CALL ajouterJoueurEquipe(" + this.idEquipe + "," + pJoueur.JNum + ")";
			bdd.requeteSansDonnes(requete3Stocke);
		}
		
		
		bdd.fermerConnexion();
		
	}
	
	public void removeFromDB() throws SQLException {
		String requeteStocke = "CALL removeEquipe(" + this.idEquipe + ")";
		ConnexionSQL bdd = new ConnexionSQL();
		bdd.requeteSansDonnes(requeteStocke);
		
	}

	public static ArrayList<Equipe> getAllEquipeByConcoursID(int pConcours) throws SQLException {
		ArrayList<Equipe> lesEquipesTmp = new ArrayList<Equipe>();
		
		String requeteStocke = "CALL getAllEquipeByConcoursID(" + pConcours + ")";
		ConnexionSQL bdd = new ConnexionSQL();
		ResultSet tmp = bdd.requeteRetourneDonnees(requeteStocke);
		
		while(tmp.next()) {
			int id_equipe = tmp.getInt(1);
			String nom_equipe = tmp.getString(2);
			int id_concours = tmp.getInt(3);
			
			ArrayList<Joueur> lesJoueurstmp = new ArrayList<Joueur>();
			String requeteStocke2 = "CALL getAllJoueursInEquipe(" + id_equipe + ")";
			ResultSet tmp2 = bdd.requeteRetourneDonnees(requeteStocke2);
			
			while(tmp2.next()) {
				int id = tmp2.getInt(1);
				String prenom = tmp2.getString(2);
				String nom = tmp2.getString(3);
				String nomClub = tmp2.getString(4);
				String sexe = tmp2.getString(5);
				String categorieNom = tmp2.getString(6);
				int numClub = tmp2.getInt(7);
				
				Joueur unJoueur = new Joueur(id, prenom, nom, nomClub, sexe, categorieNom, numClub);
				lesJoueurstmp.add(unJoueur);
			}
			
			Equipe uneEquipe = new Equipe(id_equipe, nom_equipe, id_concours, lesJoueurstmp);
			lesEquipesTmp.add(uneEquipe);
		}
		bdd.fermerConnexion();
	
		return lesEquipesTmp;
	}
	
	public static Equipe getEquipeByID(int pID) throws SQLException {
		Equipe uneEquipe = null;
		
		String requeteStocke = "CALL getEquipeByID(" + pID + ")";
		ConnexionSQL bdd = new ConnexionSQL();
		ResultSet tmp = bdd.requeteRetourneDonnees(requeteStocke);
		
		while(tmp.next()) {
			int id_equipe = tmp.getInt(1);
			String nom_equipe = tmp.getString(2);
			int id_concours = tmp.getInt(3);
			
			ArrayList<Joueur> lesJoueurstmp = new ArrayList<Joueur>();
			String requeteStocke2 = "CALL getAllJoueursInEquipe(" + id_equipe + ")";
			ResultSet tmp2 = bdd.requeteRetourneDonnees(requeteStocke2);
			
			while(tmp2.next()) {
				int id = tmp2.getInt(1);
				String prenom = tmp2.getString(2);
				String nom = tmp2.getString(3);
				String nomClub = tmp2.getString(4);
				String sexe = tmp2.getString(5);
				String categorieNom = tmp2.getString(6);
				int numClub = tmp2.getInt(7);
				
				Joueur unJoueur = new Joueur(id, prenom, nom, nomClub, sexe, categorieNom, numClub);
				lesJoueurstmp.add(unJoueur);
			}
			
			 uneEquipe = new Equipe(id_equipe, nom_equipe, id_concours, lesJoueurstmp);
			
		}
		bdd.fermerConnexion();
	
		return uneEquipe;
	}
	
	
}