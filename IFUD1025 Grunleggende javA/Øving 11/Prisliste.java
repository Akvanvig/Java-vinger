class Prisliste {
  private Pris[] prisene;
  private int antallPriser;

  Prisliste(int tabellstorrelse) {
    antallPriser = 0;
    prisene = new Pris[tabellstorrelse - 1];
  }

  public void settPris(String valutanavn, double prisen) {
    prisene[antallPriser] = new Pris(valutanavn, prisen);
    antallPriser ++;
  }

  public double getPris(String valutanavn) {
    boolean feilValuta = true;
    int index = 0;
    double resultat = 0;

    while (feilValuta && index < prisene.length) {
      if (prisene[index].getValutaNavn().equals(valutanavn)) {
        resultat = prisene[index].getVerdi();
        feilValuta = false;
      }
      index++;
    }
    return resultat;
  }

  public int finnAntallTippetall(String valutanavn, double belop) {
    int antallTall = 0;

    for (int i = 0; i < antallPriser; i++) {
      if (prisene[i].getValutaNavn().equals(valutanavn)) {
        antallTall = prisene[i].finnAntallTippetall(belop);
      }
    }
    return antallTall; //returnerer 0 om ikke valutaen blir funnet
  }

  public String toString() {
    String tekst1 = "Pris pr. tippetall \n";
    for (int i = 0; i < antallPriser; i++) {
      tekst1 += prisene[i].getValutaNavn() + " " + prisene[i].getVerdi() + "\n";
    }
    return tekst1;
  }
}
