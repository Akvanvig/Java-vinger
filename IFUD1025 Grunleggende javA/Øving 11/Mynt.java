class Mynt {
  private String valutanavn;
  private double verdi;

  Mynt(String valutanavn, double verdi) {
    this.valutanavn = valutanavn;
    this.verdi = verdi;
  }

  public double getVerdi(){
    return verdi;
  }

  public String getValuta() {
    return valutanavn;
  }

  public void nullstill(){
    verdi = 0;
  }
}
