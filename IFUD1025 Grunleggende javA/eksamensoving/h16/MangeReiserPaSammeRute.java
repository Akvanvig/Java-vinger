class MangeReiserPaSammeRute {
  private Reise[] reisene;

  public Reise finnGjennomsnittsreisen() {
    boolean alleFullfort = true;
    //Sjekker om alle reisene er fullført
    for (int i = 0; i < reisene.length; i ++) {
      if (! reisene[i].erSlutt()) {
        alleFullfort = false;
      }
    }
    Reise snittReise;

    if (alleFullfort) {
      int antallStopp = reisene[0].getNeste();

      int[] resPaa = new int[antallStopp];
      int[] resAv = new int[antallStopp];
      //Sjekker ett og ett stopp for snitt
      for (int i = 0; i < antallStopp; i++) {
        double snittPaa = 0.0;
        double snittAv = 0.0;
        //Sjekker alle rutene på det angitte stoppet
        for (int j = 0; j < reisene.length; j++) {
          int[] paa = reisene[j].getPa();
          int[] av = reisene[j].getAv();

          snittPaa = snittPaa + paa[i];
          snittAv = snittAv + av[i];
        }
        //Deler opp på antall ruter, runder av og legger inn i snittReise
        snittPaa = snittPaa / reisene.length;
        snittAv = snittAv / reisene.length;
        resPaa[i] = (int) (snittPaa + 0.5);
        resAv[i] = (int) (snittAv + 0.5);
      }
      snittReise = new Reise(resAv, resPaa);
    }
    else {
      snittReise = new Reise(0);
    }

    return snittReise;
  }
}
