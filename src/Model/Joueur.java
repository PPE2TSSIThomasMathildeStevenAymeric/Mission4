package Model;

public class Joueur {

  protected int JNum;

  protected Boolean JSexe;

  protected String JCat;

  protected Club JClub;

  protected Equipe JEquipe;

public Joueur() {
	
}

public Joueur(int jNum, Boolean jSexe, String jCat, Club jClub, Equipe jEquipe) {
	super();
	JNum = jNum;
	JSexe = jSexe;
	JCat = jCat;
	JClub = jClub;
	JEquipe = jEquipe;
}

public int getJNum() {
	return JNum;
}

public void setJNum(int jNum) {
	JNum = jNum;
}

public Boolean getJSexe() {
	return JSexe;
}

public void setJSexe(Boolean jSexe) {
	JSexe = jSexe;
}

public String getJCat() {
	return JCat;
}

public void setJCat(String jCat) {
	JCat = jCat;
}

public Club getJClub() {
	return JClub;
}

public void setJClub(Club jClub) {
	JClub = jClub;
}

public Equipe getJEquipe() {
	return JEquipe;
}

public void setJEquipe(Equipe jEquipe) {
	JEquipe = jEquipe;
}



}