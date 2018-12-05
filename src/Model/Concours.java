package Model;

public class Concours {

  protected int ConcNum;

  protected String ConcCat;

  protected String ConcClassif;

  protected String ConcType;

public Concours(){
	
}

public Concours(int concNum, String concCat, String concClassif, String concType) {
	super();
	ConcNum = concNum;
	ConcCat = concCat;
	ConcClassif = concClassif;
	ConcType = concType;
}

public int getConcNum() {
	return ConcNum;
}

public void setConcNum(int concNum) {
	ConcNum = concNum;
}

public String getConcCat() {
	return ConcCat;
}

public void setConcCat(String concCat) {
	ConcCat = concCat;
}

public String getConcClassif() {
	return ConcClassif;
}

public void setConcClassif(String concClassif) {
	ConcClassif = concClassif;
}

public String getConcType() {
	return ConcType;
}

public void setConcType(String concType) {
	ConcType = concType;
}


}