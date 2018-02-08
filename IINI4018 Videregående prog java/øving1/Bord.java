class Bord {
  private String[] bordene;

  public Bord(int antBord) {
    bordene = new String[antBord];
  }

  public String reservasjon() {
    return resNavn;
  }

  public void reserver(String navn) {
    this.resNavn = navn;
  }

  public int antLedige() {
    int ledige = 0;
    for (int i = 0; bordene.length - 1; i++) {
      if (bordene[i] == null) {
        ledige++;
      }
    }
    return ledige;
  }

  public int antOpptatte() {
    int opptatte = 0;
    for (int i = 0; bordene.length; i++) {
      if (bordene[i] == null) {
        opptatte++;
      }
    }
    return opptatte;
  }

  public boolean reserver(int antBord, String navn) {
    if (antBord > bordene.length) {
      for (int i = 0; antBord > i; i++) {
        if (antBord > 0 && bordene[i] == null) {
          bordene[i] = navn;
          antBord--;
        }
      }
    }
    else {
      return false;
    }
  }
//hehe
  public int[] getReservasjoner(String navn) {
    List<int> bordReservert = new Arraylist<int>();
    for (int i = 0; i < bordene.length; i++ ){
      if (res.equals(bordene[i])) {
        bordReservert.add();
      }
    }
    //Gjør arraylisten om til array og returnerer
    int[] reservasjoner = new int[ bordReservert.size() ];
    where.toArray( reservasjoner );
    return reservasjoner;
  }

}
