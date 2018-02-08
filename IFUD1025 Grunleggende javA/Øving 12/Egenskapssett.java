class Egenskapssett {
  private String[] fargene; //= {"spar", "kl\u00f8ver", "hjerter", "ruter"}
  private int minValoer;
  private int maxValoer;

  Egenskapssett(String[] fargene, int minVerdi, int maxVerdi) {
    this.fargene = fargene;
    minValoer = minVerdi;
    maxValoer = maxVerdi;
  }

  Egenskapssett(int minVerdi, int maxVerdi) {
    fargene = new String[] {"spar", "kl\u00f8ver", "hjerter", "ruter"};
    minValoer = minVerdi;
    maxValoer = maxVerdi;
  }

  public int getMinValoer() {
    return minValoer;
  }

  public int getMaxValoer() {
    return maxValoer;
  }

  public int finnAntallValoerer() {
    return (maxValoer - minValoer + 1);
  }

  public int finnAntallKortIKortstokken() {
    return (fargene.length * finnAntallValoerer());
  }

  public int[] finnValoerene() {
    int[] resultat = new int[maxValoer - minValoer + 1];
    int teller = minValoer;
    for (int i = 0; i < resultat.length; i++) {
      resultat[i] = teller;
      teller ++;
    }
    return resultat;
  }

  public Kort[] finnKortstokken() {
    int antallKort = finnAntallKortIKortstokken();
    int[] valoerene = finnValoerene();
    Kort[] kortstokk = new Kort[antallKort];
    int teller = 0;

    //Lager en liste over alle kortene sortert etter type, og deretter verdi
    for (String farge: fargene) {
      for (int verdi: valoerene) {
        kortstokk[teller] = new Kort(farge, verdi);
        teller++;
      }
    }

    return kortstokk;
  }
}
