class Gate {
  private String gatenavn;
  private int[] vektene;

  Gate(String gatenavn, int antall) {
    this.gatenavn = gatenavn;
    this.vektene = new int[antall];
  }

  Gate(String gatenavn, int[] vekt) {
    this.gatenavn = gatenavn;
    this.vektene = new int[vekt.length];
    for (int i = 0; i < vekt.length; i++) {
      this.vektene[i] = vekt[i];
    }
  }

  public void kast(int nr, int vekt) {
    vektene[nr - 1] = vektene[nr - 1] + vekt;
  }

  public boolean erFull(int nr) {
    if (vektene[nr - 1] > 999) {
      return true;
    }
    else {
      return false;
    }
  }

  public void tom(int nr) {
    vektene[nr - 1] = 0;
  }

  private int finnSidensMinsteGatenr(boolean like) {
    if (like) {
      return 2;
    }
    else {
      return 1;
    }
  }

  public void tom(boolean like) {
    int gatenr = finnSidensMinsteGatenr(like) - 1;
    while (gatenr < vektene.length) {
      vektene[gatenr] = 0;
      gatenr += 2;
    }
  }

  public boolean finsToFulleVedSidenAvHverandre(boolean like) {
    int gatenr = finnSidensMinsteGatenr(like) - 1;
    int antRekke = 0;
    boolean resultat = false;

    for (int i = gatenr; i < vektene.length; i += 2) {
      if (vektene[i] > 999) {
        antRekke ++;
        if (antRekke == 2) {
          resultat = true;
        }
      }
      else {
        antRekke = 0;
      }
    }
    return resultat;
  }

  public int[] getVektene() {
    int[] resultat = new int[vektene.length];
    for (int i = 0; i < vektene.length; i++) {
      resultat[i] = vektene[i];
    }
    return resultat;
  }

  public String toString() {
    String utskrift = "Odde siden av gaten \n";
    for (int i = 0; i < vektene.length; i += 2) {
      utskrift += "( nr." + (i+1) + ", vekt " + vektene[i] + ") \n";
    }
    utskrift += "Like siden av gaten \n";
    for (int i = 1; i < vektene.length; i += 2) {
      utskrift += "( nr." + (i+1) + ", vekt " + vektene[i] + ") \n";
    }
    return utskrift;
  }
}
