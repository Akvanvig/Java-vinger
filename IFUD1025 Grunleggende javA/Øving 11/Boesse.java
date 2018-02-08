class Boesse {
  private Mynt[] myntene;
  private int antallMynter;

  Boesse(int tabellstorrelse) {
    this.antallMynter = 0;
    myntene = new Mynt[tabellstorrelse - 1];
  }

  public boolean erFull() {
    if (myntene.length == antallMynter) {
      return true;
    }
    else {
      return false;
    }
  }

  public void leggPaaMynt(Mynt mynten) {
    if (! erFull()) {
      myntene[antallMynter] = mynten;
      antallMynter ++;
    }
  }
}
