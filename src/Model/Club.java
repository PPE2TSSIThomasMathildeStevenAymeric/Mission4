package Model;

public class Club {

  protected int ClubNum;

  protected Joueur ClubJoueurs;

  protected Equipe ClubEquipe;

public Club() {
	
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



}