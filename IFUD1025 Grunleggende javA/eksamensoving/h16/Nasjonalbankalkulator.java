class Nasjonalbankalkulator {
  private String landkoden;
  private int ibanlengden;

  private static boolean erBokstav(char tegn) {
    return tegn >= 'A' && tegn <= 'Z';
  }
  private static boolean erSiffer(char tegn) {
    return tegn >= '0' && tegn <= '9';
  }

  private boolean krav1ErOppfylt(String streng) {
    //Sjekker om det er riktig lengde på tekststrengen
    if (streng.length() == ibanlengden) {
      char[] tegnene = new char[4];
      for (int i = 0; i < 4; i++) {
        tegnene[i] = streng.charAt(i);
      }
      //Sjekker om de første fire tegnene er på formen "bokstav bokstav siffer siffer"
      if (erBokstav(tegnene[0]) && erBokstav(tegnene[1]) && erSiffer(tegnene[2]) && erSiffer(tegnene[3])) {
        //Sjekker om landskoden stemmer
        String kode = "" + tegnene[0] + tegnene[1];
        if ((kode).equals(landkoden)) {
          return true;
        }
        else {return false;}
      }
      else {return false;}
    }
    else {return false;}
  }

  private static boolean krav2ErOppfylt(String streng) {
    char[] tegnene = new char[streng.length()];
    boolean godkjent = true;

    for (int i = 0; i < streng.length(); i++) {
      if ( !erBokstav(streng.charAt(i)) && !erSiffer(streng.charAt(i))) {
        godkjent = false;
      }
    }
    return godkjent;
  }

  private static boolean krav3ErOppfylt(String streng) {
    int lengde = streng.length();
    int teller = 0;
    char[] tegnene = new char[lengde];

    //Flytter de fire første tegnene bakerst
    for (int i = 4; i < lengde; i++) {
      tegnene[teller] = streng.charAt(i);
      teller ++;
    }
    for (int i = 0; i < 4; i ++) {
      tegnene[teller] = streng.charAt(i);
      teller ++;
    }

    //Gjør det deretter om til en tekstreng igjen med bare siffer
    String tekstreng = "";
    //Går gjennom ett og ett tegn
    for (int i = 0; i < lengde; i++) {
      //hvis tegnet er en bokstav vil det gjøres om til et tall
      if (erBokstav(tegnene[i])) {
        int bokstavNummer = 10;
        //Bruker en løkke med char for å finne tegnet som er brukt.
        for (char j = 'A'; j <= 'Z'; j++) {
          if (tegnene[i] == j) {
            tekstreng = tekstreng + bokstavNummer;
          }
          bokstavNummer ++;
        }
      }
      else {
        tekstreng = tekstreng + tegnene[i];
      }
    }

    //Sjekker om modulus 97 er lik 1
    int numeriskStreng = Integer.parseInt(tekstreng);
    if (numeriskStreng % 97 == 1) {
      return true;
    }
    else {
      return false;
    }
  }

  public boolean gyldigIban(String streng) {
    if (krav1ErOppfylt(streng) && krav2ErOppfylt(streng) && krav3ErOppfylt(streng)) {
      return true;
    }
    else {
      return false;
    }
  }

  public static String finnGruppert(String streng) {
    String resultat = "";
    for (int i = 1; i <= streng.length(); i++) {
      resultat = resultat + streng.charAt(i - 1);
      if (i % 4 == 0) {
        resultat = resultat + " ";
      }
    }
    return resultat;
  }
}
