package Model;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Match {
	
	protected int idConcours;
	protected int idMatch;
	protected Equipe equipe1;
	protected Equipe equipe2;
	protected Equipe equipegagnante;
	
	public Match(int idConcours, Equipe equipe1, Equipe equipe2) {
		super();
		this.idConcours = idConcours;
		this.equipe1 = equipe1;
		this.equipe2 = equipe2;
	}
	
	public int getIdConcours() {
		return idConcours;
	}
	public void setIdConcours(int idConcours) {
		this.idConcours = idConcours;
	}
	public int getIdMatch() {
		return idMatch;
	}
	public void setIdMatch(int idMatch) {
		this.idMatch = idMatch;
	}
	public Equipe getEquipe1() {
		return equipe1;
	}
	public void setEquipe1(Equipe equipe1) {
		this.equipe1 = equipe1;
	}
	public Equipe getEquipe2() {
		return equipe2;
	}
	public void setEquipe2(Equipe equipe2) {
		this.equipe2 = equipe2;
	}
	public Equipe getEquipegagnante() {
		return equipegagnante;
	}
	public void setEquipegagnante(Equipe equipegagnante) {
		this.equipegagnante = equipegagnante;
	}
	
	public static ArrayList<Match> randomizeRencontre(ArrayList<Equipe> listeEquipe, int idConcours) {
		ArrayList<Match> listeMatch = null;
		ArrayList<Equipe> listeEquipeRestante = listeEquipe;
		
		while (listeEquipeRestante.size() < 2) {
			int nbequipe = listeEquipeRestante.size();
			
			Equipe equipe1 = null;
			Equipe equipe2 = null;
			
			// On fait un while pour éviter qu'une équipe fasse un match ... contre elle même
			while (equipe1 == equipe2) {
				equipe1 = listeEquipeRestante.get(ThreadLocalRandom.current().nextInt(0, nbequipe));
				equipe2 = listeEquipeRestante.get(ThreadLocalRandom.current().nextInt(0, nbequipe));
			}
			listeEquipeRestante.remove(equipe1);
			listeEquipeRestante.remove(equipe2);
			
			Match unMatch = new Match(idConcours, equipe1, equipe2);
			listeMatch.add(unMatch);
			// Créer un match avec comme paramètre l'id du concours, l'id equipe1 et l'id equipe2
		}
		
		if (listeEquipeRestante.size() == 1) {
			// Faire gagner l'équipe = match blanc
			// créer un match mais mettre directement gg l'équipe
		}
		
		return listeMatch;
		
	}

}
