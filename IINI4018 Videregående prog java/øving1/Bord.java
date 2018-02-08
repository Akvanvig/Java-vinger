import java.util.*;

class Bord {
  private String[] bordene;

  public Bord(int antBord) {
    bordene = new String[antBord];
  }

  public int antLedige() {
    int ledige = 0;
    for (int i = 0; i < bordene.length; i++) {
      if (bordene[i] == null) {
        ledige++;
      }
    }
    return ledige;
  }

  public int antOpptatte() {
    int opptatte = 0;
    for (int i = 0; i < bordene.length; i++) {
      if (bordene[i] == null) {
        opptatte++;
      }
    }
    return opptatte;
  }

  public boolean reserver(int antBord, String navn) {
    if (antBord > antLedige) {
      for (int i = 0; antBord > 0; i++) {
        if (antBord > 0 && bordene[i] == null) {
          bordene[i] = navn;
          antBord--;
        }
      }
      return true;
    }
    else {
      return false;
    }
  }
//hehe
  public int[] getReservasjoner(String navn) {
    ArrayList<int> bordReservert = new ArrayList<int>();
    for (int i = 0; i < bordene.length; i++ ){
      if (res.equals(bordene[i])) {
        bordReservert.add(i);
      }
    }
    //Gjør arraylisten om til array og returnerer
    int[] reservasjoner = new int[ bordReservert.size() ];
    bordReservert.toArray( reservasjoner );
    return reservasjoner;
  }

}


//Er det vaskeligere å overtale service/babed folk til å endre vaner?
  //IT-folk er gode, andre kanskje ikke fullt så medgjørelige :)
