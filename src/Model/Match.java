package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Match {
	
	protected int idConcours;
	protected int idMatch;
	protected Equipe equipe1;
	protected Equipe equipe2;
	protected Equipe equipegagnante;
	protected Equipe equipePerdante;
	protected int ConcoursTourNum;
	
	
	
	public Match(int idConcours, Equipe equipe1, Equipe equipe2, int pConcoursTourNum) {
		super();
		this.idConcours = idConcours;
		this.equipe1 = equipe1;
		this.equipe2 = equipe2;
		this.ConcoursTourNum = pConcoursTourNum;
	}
	
	public Match(int idConcours, Equipe equipe1, Equipe equipe2, int pConcoursTourNum, Equipe pEquipe) {
		super();
		this.idConcours = idConcours;
		this.equipe1 = equipe1;
		this.equipe2 = equipe2;
		this.ConcoursTourNum = pConcoursTourNum;
		this.equipegagnante = pEquipe;
		this.equipePerdante = pEquipe;
	}
	
	public Match(int pidMatch, int idConcours, Equipe equipe1, Equipe equipe2, int pConcoursTourNum, Equipe pEquipeGagnante, Equipe pEquipePerdante) {
		super();
		this.idMatch = pidMatch;
		this.idConcours = idConcours;
		this.equipe1 = equipe1;
		this.equipe2 = equipe2;
		this.ConcoursTourNum = pConcoursTourNum;
		this.equipegagnante = pEquipeGagnante;
		this.equipePerdante = pEquipePerdante;
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
	
	public void addToDB() throws SQLException {
		String requeteStocke = "CALL ajouterMatch(" + this.idConcours + "," + this.equipe1.getIdEquipe() + "," + this.equipe2.getIdEquipe() + "," + this.ConcoursTourNum + ")";
		ConnexionSQL bdd = new ConnexionSQL();
		bdd.requeteSansDonnes(requeteStocke);
		
		String requeteStocke2 = "CALL getLastMatch(" + this.idConcours + ")";
		ResultSet tmp = bdd.requeteRetourneDonnees(requeteStocke2);
		tmp.next();
		int id_Match = tmp.getInt(1);
		
		this.setIdMatch(id_Match);
	}
	
	public void addToDBBlanc() throws SQLException {
		String requeteStocke = "CALL ajouterMatchBlanc(" + this.idConcours + "," + this.equipe1.getIdEquipe() + "," + this.equipe2.getIdEquipe() + "," + this.ConcoursTourNum  + "," + this.equipe1.getIdEquipe() + ")";
		ConnexionSQL bdd = new ConnexionSQL();
		bdd.requeteSansDonnes(requeteStocke);
		
		String requeteStocke2 = "CALL getLastMatch(" + this.idConcours + ")";
		ResultSet tmp = bdd.requeteRetourneDonnees(requeteStocke2);
		tmp.next();
		int id_Match = tmp.getInt(1);
		
		this.setIdMatch(id_Match);
		
	}
	
	public static ArrayList<Match> randomizeRencontre(ArrayList<Equipe> listeEquipe, Concours leConcours) throws SQLException {
		ArrayList<Match> listeMatchRandom = new ArrayList<Match>();
		ArrayList<Equipe> listeEquipeRestante = listeEquipe;
		
		
		while (listeEquipeRestante.size() >= 2) {
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
			
			System.out.println("match : " + equipe1.getNomEquipe() + " - " + equipe2.getNomEquipe());
			
			Match unMatch = new Match(leConcours.getConcNum(), equipe1, equipe2, leConcours.getConcTourNum());
			unMatch.addToDB();
			listeMatchRandom.add(unMatch);
			
			// Créer un match avec comme paramètre l'id du concours, l'id equipe1 et l'id equipe2
		}
		
		if (listeEquipeRestante.size() == 1) {
			// Faire gagner l'équipe = match blanc
			// créer un match mais mettre directement gg l'équipe
			Equipe equipeRestante = listeEquipeRestante.get(0);
			Match unMatch = new Match(leConcours.getConcNum(), equipeRestante, equipeRestante, leConcours.getConcTourNum(), equipeRestante);
			unMatch.addToDBBlanc();
			listeMatchRandom.add(unMatch);
		}
		
		return listeMatchRandom;
		
	}


	public Equipe getEquipePerdante() {
		return equipePerdante;
	}

	public void setEquipePerdante(Equipe equipePerdante) {
		this.equipePerdante = equipePerdante;
	}

	public int getConcoursTourNum() {
		return ConcoursTourNum;
	}

	public void setConcoursTourNum(int concoursTourNum) {
		ConcoursTourNum = concoursTourNum;
	}
	
	public static Match[] getAllMatchOfConcours(int pNum) throws SQLException {
		
		ArrayList<Match> lesMatchtmp = new ArrayList<Match>();  
		
		ConnexionSQL bdd = new ConnexionSQL();
		String requeteStocke = "CALL getAllMatchOfConcours(" + pNum + ")";
		ResultSet tmp = bdd.requeteRetourneDonnees(requeteStocke);
		
		while(tmp.next()) {
			int id_match = tmp.getInt(1);
			int idConcours = tmp.getInt(2);
			int idEquipe1 = tmp.getInt(3);
			int idEquipe2 = tmp.getInt(4);
			int idEquipeGagnante = tmp.getInt(5);
			int idEquipePerdante = tmp.getInt(6);
			int tour = tmp.getInt(7);
			
			Equipe equipe1 = Equipe.getEquipeByID(idEquipe1);
			Equipe equipe2 = Equipe.getEquipeByID(idEquipe2);
			Equipe equipeGagnante = null;
			Equipe equipePerdante = null;
			
			if (idEquipe1 == idEquipeGagnante) {
				equipeGagnante = equipe1;
				equipePerdante = equipe2;
			} else {
				equipeGagnante = equipe2;
				equipePerdante = equipe1;
			}
			
			Match unMatch = new Match(id_match, idConcours, equipe1, equipe2, tour, equipeGagnante, equipePerdante);
			lesMatchtmp.add(unMatch);
		}
		Match[] lesMatch = new Match[lesMatchtmp.size()];
		int i = 0;
		
		for (Match tmpMatch : lesMatchtmp) {
			lesMatch[i] = tmpMatch;
			i++;
		}
		// Pour chaque match, mettre dans le tableau puis retournera le tableau
		
		return lesMatch;
		
	}

}
