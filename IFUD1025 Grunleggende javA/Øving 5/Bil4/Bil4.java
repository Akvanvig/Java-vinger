class Bil4 {
  private String regnr;
  private String merke;
  private int årsmodell;
  private int hastighet;
  private Boolean motorenIGang;

//Lager konstruktøren
  Bil4(String regnr, String bilmerke, int årsmodell) {
    this.regnr = regnr;
    merke = bilmerke;
    this.årsmodell = årsmodell;
    hastighet = 0;
    motorenIGang = false;
  }

//Brukes for å hente ut nåværende verdier
  public String getRegnr() {
    return regnr;
  }
  public String getMerke() {
    return merke;
  }
  public int getArsmodell() {
    return årsmodell;
  }
  public int getHastighet() {
    return hastighet;
  }
  public Boolean isMotorenIGang() {
    return motorenIGang;
  }

  //Brukes for å starte/stoppe bilen
  public void start() {
    motorenIGang = true;
    hastighet = 0;
  }
  public void stopp() {
    motorenIGang = false;
    hastighet = 0;
  }

  //Brukes for å endre hastighet
  public void okFart(int okning) {
    if (motorenIGang == true && (hastighet + okning) < 300) {
      hastighet = hastighet + okning;
    }
  }
  public void brems(int reduksjon) {
    if (motorenIGang == true && (hastighet - reduksjon) >= 0) {
      hastighet = hastighet - reduksjon;
    }
  }
}
