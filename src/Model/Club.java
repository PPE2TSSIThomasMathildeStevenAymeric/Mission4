package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Club {

  protected int ClubNum;
  
  protected String ClubNom;

  protected Joueur ClubJoueurs;

  protected Equipe ClubEquipe;
  
  static ConnexionSQL bdd = new ConnexionSQL();

public Club() {
	
}

public Club(int clubNum, String clubNom) {
	ClubNum = clubNum;
	ClubNom = clubNom;
}

public Club(int clubNum, Joueur clubJoueurs, Equipe clubEquipe) {
	ClubNum = clubNum;
	ClubJoueurs = clubJoueurs;
	ClubEquipe = clubEquipe;
}

public int getClubNum() {
	return ClubNum;
}

public void setClubNum(int clubNum) {
	ClubNum = clubNum;
}

public Joueur getClubJoueurs() {
	return ClubJoueurs;
}

public void setClubJoueurs(Joueur clubJoueurs) {
	ClubJoueurs = clubJoueurs;
}

public Equipe getClubEquipe() {
	return ClubEquipe;
}

public void setClubEquipe(Equipe clubEquipe) {
	ClubEquipe = clubEquipe;
}

public String getClubNom() {
	return ClubNom;
}

public void setClubNom(String nom) {
	ClubNom = nom;
}

public static Club getClubById(int id) throws SQLException {
	Club leClub = null;
	
	String requeteStocke = "CALL getLeClub("+id+")";
	
	ResultSet resultbdd = bdd.requeteRetourneDonnees(requeteStocke);
		
	while(resultbdd.next()) {
		int idClub = resultbdd.getInt(1);
		String nomClub = resultbdd.getString(2);
		
		leClub = new Club(idClub, nomClub);
	}
	
	return leClub;
}

public static ArrayList<Club> getAllClub() throws SQLException{
	ArrayList<Club> lesClubs = new ArrayList<Club>();
	
	String requeteStocke = "CALL getClubs()";
	
	ResultSet resultbdd = bdd.requeteRetourneDonnees(requeteStocke);
		
	while(resultbdd.next()) {
		
		int id_Club = resultbdd.getInt(1);
			
		Club unClub = Club.getClubById(id_Club);
		lesClubs.add(unClub);   
	}
	
	
	return lesClubs;
}


}