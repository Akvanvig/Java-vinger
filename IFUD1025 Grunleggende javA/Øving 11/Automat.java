class Automat {
  private Prisliste prislisten;
  private Boesse boessen;

  Automat(int tabellStrPris, int tabellStrBoesse) {
    prislisten = new Prisliste(tabellStrPris);
    boessen = new Boesse(tabellStrBoesse);
  }

  public Trekning spill(Spiller spilleren) {
    boessen.leggPaaMynt(spilleren.betal());
    Trekning trekningen = new Trekning();
    return trekningen;
  }

  public void settPris(String valutanavn, double prisen) {
    prislisten.settPris(valutanavn, prisen);
  }

  public boolean erBoessenFull() {
    return boessen.erFull();
  }

  public double finnPrisEttTall(String valutanavn) {
    return prislisten.getPris(valutanavn);
  }

  public int finnAntallTippetall(String valutanavn, double belop) {
    return prislisten.finnAntallTippetall(valutanavn, belop);
  }

  public String finnPrislisten() {
    return prislisten.toString();
  }

  public Boesse finnBoessen() {
    return boessen;
  }

  public int finnAntallRette(Trekning trekningen, Spiller spilleren) {
    return trekningen.finnAntallRette(spilleren.getTippingen());
  }
}
