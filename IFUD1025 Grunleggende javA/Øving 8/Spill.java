class Spill {
  private Barn spiller1;
  private Barn spiller2;
  private Kortstokk kortene = new Kortstokk();
  private int antRunder;
  private int nrRunde = 0;

  Spill(String navn1, String navn2, int antRunder) {
    this.spiller1 = new Barn(navn1);
    this.spiller2 = new Barn(navn2);
    this.antRunder = antRunder;
  }

  public String visResultat() {
    return "Runde nummer \t" + nrRunde + "\n" + spiller1.getPoeng() + "\t" + spiller1.getSisteBokstav() + "\t" + spiller1.getNavn() + "\n" + spiller2.getPoeng() + "\t" + spiller2.getSisteBokstav() + "\t" + spiller2.getNavn();
  }

  public void trekkKort() {
    spiller1.trekk(kortene);
    spiller2.trekk(kortene);
  }

  public void bytte() {
    spiller1.bytt(spiller2);
  }

  public void sjekkVinner() {
    spiller1.sammenlign(spiller2);
    nrRunde ++;
  }

  public boolean spillFerdig() {
    return nrRunde >= antRunder;
  }
}
