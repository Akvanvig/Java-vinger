class Passordhjelper {
private String passordet;

Passordhjelper(String forslaget) {
  this.passordet = fjernUlovligeTegn(forslaget);
}

public void setPassord(String passord) {
  this.passordet = fjernUlovligeTegn(passord);
}

private String fjernUlovligeTegn(String tekst) {
  char[] tegnListe = tekst.toCharArray();
  String nyttPassord = "";
  for (int index = 0; index < tegnListe.length; index++) {
      if (Passordtegn.erPassordtegn(tegnListe[index])) {
        nyttPassord = nyttPassord + tegnListe[index];
      }
  }
  return nyttPassord;
}

  public int finnPassordstyrken() {
    int passordstyrke = 0;
    char[] tegnListe = passordet.toCharArray();

    //Sjekke passordets lengde (8 tegn eller lengre gir poeng)
    if (tegnListe.length > 7) {
      passordstyrke ++;
    }

    //Sjekker om passordet inneholder minst en stor bokstav, en liten bokstav, ett siffer og ett spesialtegn
    boolean storBokstav = false;
    boolean litenBokstav = false;
    boolean siffer = false;
    boolean spesialtegn = false;

    for (int i = 0; i < tegnListe.length; i ++) {
      char tegn = tegnListe[i];
      if (Passordtegn.erStorBokstav(tegn)) {
        storBokstav = true;
      }
      if (Passordtegn.erLitenBokstav(tegn)) {
        litenBokstav = true;
      }
      if (Passordtegn.erSiffer(tegn)) {
        siffer = true;
      }
      if (Passordtegn.erSpesialtegn(tegn)) {
        spesialtegn = true;
      }
    }

    //Øker passordstyrke basert på hvilke krav passordet innfrir
    if (storBokstav) {
      passordstyrke ++;
    }
    if (litenBokstav) {
      passordstyrke ++;
    }
    if (siffer) {
      passordstyrke ++;
    }
    if (spesialtegn) {
      passordstyrke ++;
    }

    return passordstyrke;
  }

  public boolean isPassord(String passord) {
    if (passordet.equals(passord)) {
      return true;
    }
    else {
      return false;
    }
  }

  public String toString() {
    return passordet;
  }
}
