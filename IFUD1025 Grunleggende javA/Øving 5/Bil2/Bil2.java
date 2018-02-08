class Bil2 {
  private String regnr;
  private String merke;
  private int årsmodell;
  private int hastighet;
  private Boolean motorenIGang;

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
