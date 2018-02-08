class Reise {
  private int neste;
  private int[] av;
  private int[] pa;

  Reise(int antHoldeplasser) {
    av = new int[antHoldeplasser];
    pa = new int[antHoldeplasser];
  }

  Reise(int[] ruteAv, int[] rutePaa) {
    int ant = ruteAv.length;
    av = new int[ant];
    pa = new int[ant];

    for (int i = 0; i < ant; i ++) {
      av[i] = ruteAv[i];
      pa[i] = rutePaa[i];
    }
  }

  public int[] getAv() {
    int ant = av.length;
    int[] resultatAv = new int[ant];

    for (int i = 0; i < ant; i ++) {
      resultatAv[i] = av[i];
    }

    return resultatAv;
  }

  public int[] getPa() {
    int ant = pa.length;
    int[] resultatPa = new int[ant];

    for (int i = 0; i < ant; i ++) {
      resultatPa[i] = pa[i];
    }

    return resultatPa;
  }

  public int getNeste() {
    return neste;
  }

  public boolean erSlutt() {
    return av.length == neste;
  }

  public void ankommerHoldeplass(int antallAv, int antallPaa) {
    if (! erSlutt()) {
      av[neste] = antallAv;
      pa[neste] = antallPaa;
      neste ++;
    }
  }

  public int[] finnAntallOmbord() {
    int[] resultat = new int[av.length];
    for (int i = 0; i < resultat.length; i ++) {
      //Om vi er på første stopp er det ingen på bussen, og vi trenger derfor bare å telle de som går på
      if (i == 0) {
        resultat[i] = pa[i];
      }
      //Om vi er forbi første stopp må vi telle de som går på og de som sitter på bussen,
      //og subtrahere de som går av
      else {
        resultat[i] = resultat[i - 1] + pa[i] - av[i];
      }
    }

    return resultat;
  }

  public int finnAntallOmbord(int nr) {
    if (nr < neste && nr > -1) {
      int[] tabellOmbord = finnAntallOmbord();
      return tabellOmbord[nr];
    }
    else {
      return 0;
    }
  }

  public int finnMaksimumOmbord() {
    int[] tabellOmbord = finnAntallOmbord();
    int maksPassasjerer = 0;
    for (int i = 0; i < tabellOmbord.length; i++) {
      if (tabellOmbord[i] > maksPassasjerer) {
        maksPassasjerer = tabellOmbord[i];
      }
    }
    return maksPassasjerer;
  }
}
