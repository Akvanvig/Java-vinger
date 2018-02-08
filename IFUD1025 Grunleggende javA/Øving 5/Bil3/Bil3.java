class Bil3 {
  private String regnr;
  private String merke;
  private int årsmodell;
  private int hastighet;
  private Boolean motorenIGang;

//Lager konstruktøren
  Bil3(String regnr, String bilmerke, int årsmodell) {
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

//Brukes for å endre hastighet
  public void setHastighet(int hastighet) {
    this.hastighet = hastighet;
  }
}
