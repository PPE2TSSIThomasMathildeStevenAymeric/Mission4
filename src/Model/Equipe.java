package Model;

public class Equipe {

  protected int ENum;

  protected Club EClubNum;

  protected Joueur EJNum;

    public Club myClub;

public Equipe() {
	
}

public Equipe(int eNum, Club eClubNum, Joueur eJNum, Club myClub) {
	super();
	ENum = eNum;
	EClubNum = eClubNum;
	EJNum = eJNum;
	this.myClub = myClub;
}

public int getENum() {
	return ENum;
}

public void setENum(int eNum) {
	ENum = eNum;
}

public Club getEClubNum() {
	return EClubNum;
}

public void setEClubNum(Club eClubNum) {
	EClubNum = eClubNum;
}

public Joueur getEJNum() {
	return EJNum;
}

public void setEJNum(Joueur eJNum) {
	EJNum = eJNum;
}

public Club getMyClub() {
	return myClub;
}

public void setMyClub(Club myClub) {
	this.myClub = myClub;
}



}