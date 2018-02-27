/*
	Øving 1
	11.02.2018
	Anders Kvanvig
*/
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
    if (antBord <= antLedige()) {
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

  public int[] getReservasjoner(String navn) {
    int ant = 0;
    //G�r gjennom listen over bord for � se hvor mange bord personen har reservert
    //Hadde en fra starten brukt Arraylist kunne dette v�rt unng�tt ettersom en slipper � vite st�rrelsen n�r en oppretter variabelen.
    for (int i = 0; i < bordene.length; i++) {
      if (bordene[i] != null && bordene[i].equals(navn)) {
        ant++;
      }
    }
    //G�r deretter gjennom og legger bordene det gjelder inn i en array for � returneres
    int[] reservasjoner = new int[ant];
    int i = 0;
    for (int j = 0; j < bordene.length; j++) {
      if (bordene[i] != null && bordene[j].equals(navn)) {
        reservasjoner[i] = j;
        i++;
      }
    }
    return reservasjoner;
  }

  public void frigiBord(int[] bordnummer) {
    //G�r gjennom bordnumrene gitt, sjekker om de er gyldige indekser og setter de lik null
    for (int i = 0; i < bordnummer.length ; i++) {
      if (bordnummer[i] >= 0 && bordnummer[i] < bordene.length) {
        bordene[bordnummer[i]] = null;
      }
    }
  }

  public String[] getBordene() {
    return bordene;
  }

}
