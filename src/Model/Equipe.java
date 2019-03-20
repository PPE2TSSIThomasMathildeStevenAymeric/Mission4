package Model;

import java.util.ArrayList;


public class Equipe {

	protected int idEquipe;
	protected String nomEquipe;
	protected int idConcours;
	protected ArrayList<Joueur> lesJoueurs;
	
	public Equipe(String nomEquipe, int idConcours, ArrayList<Joueur> desJoueurs ) {
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
	
	
}