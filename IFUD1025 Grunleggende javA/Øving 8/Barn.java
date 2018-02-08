class Barn {
  private String navn;
  private char sisteBokstav = 'Z';
  private int antallPoeng;

  Barn(String navn) {
    this.navn = navn;
    this.antallPoeng = 0;
  }

  public char getSisteBokstav() {
    return this.sisteBokstav;
  }

  public String getNavn() {
    return navn;
  }

  public int getPoeng() {
    return antallPoeng;
  }

  public void setBokstav(char bokstav) {
    this.sisteBokstav = bokstav;
  }

  public void trekk(Kortstokk kort) {
    this.sisteBokstav = kort.finnKort();
  }

  public void okPoeng() {
    this.antallPoeng ++;
  }

  public boolean vilBeggeBytte(Barn annetBarn) {
    //Henter den numeriske verdien for bokstaven de forskjellige fikk og sammenligner disse
    int mTallverdi = (int) 'M';
    int barn1Bokstav = (int) this.sisteBokstav;
    int barn2Bokstav = (int) annetBarn.getSisteBokstav();

    boolean erLike = barn1Bokstav > mTallverdi && barn2Bokstav > mTallverdi;
    return erLike;
    /*if (barn1Bokstav > mTallverdi && barn2Bokstav > mTallverdi) {
      return true;
    }
    else {
      return false;
    }*/
  }

  public void bytt(Barn annetBarn) {
    //Hvis begge ønsker å bytte vil dette utføres
    if (vilBeggeBytte(annetBarn)) {
      char bokstav1 = annetBarn.getSisteBokstav();
      char bokstav2 = this.getSisteBokstav(); //Kan også bruker 'this.sisteBokstav'
      this.setBokstav(bokstav1);
      annetBarn.setBokstav(bokstav2);
    }
  }

  public void sammenlign(Barn annetBarn) {
    //Henter den numeriske verdien for bokstavene til brukerne
    int barn1Bokstav = (int) this.sisteBokstav;
    int barn2Bokstav = (int) annetBarn.getSisteBokstav();

    if (barn1Bokstav < barn2Bokstav) {
      this.okPoeng();
    }
    else if (barn1Bokstav > barn2Bokstav) {
      annetBarn.okPoeng();
    }
  }
}
