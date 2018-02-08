import static javax.swing.JOptionPane.*;

class testDrikk {
  public static void main(String[] args) {

    //Legger inn vin, øl og konjakk
    Drikk vin = new Drikk("vin", 12);
    Drikk ol = new Drikk("\u00f8l", 4.7);
    Drikk konjakk = new Drikk("konjakk", 43);


    //Henter vekt fra bruker, bruker do while løkke for å gå gjennom minst en gang,
    //samt passe på at bruker får skrevet inn et tall
    double vekt = 60;
    boolean sporVekt;
    do {
      try {
        vekt = Integer.parseInt(showInputDialog("Hvor mye veier du? (I hele kg)"));
        sporVekt = false;
      }
      catch(NumberFormatException nfe) {
        System.out.println("Du skrev inn noe feil, pr\u00f8v p\u00e5 nytt");
        sporVekt = true;
      }
    } while (sporVekt);

    //Finner ut om bruker er en mann eller kvinne (0 = mann, 1 = kvinne, 2 = avbrutt)
    boolean velgKjonn;
    boolean mann;
    int kjonn;
    do {
      kjonn = showConfirmDialog(null, "Er du en mann?");
      if (kjonn == 2) {
        velgKjonn = true;
      }
      else {
        velgKjonn = false;
      }
    } while (velgKjonn);

    if (kjonn == 0) {
      mann = true;
    }
    else {
      mann = false;
    }

    //Lar bruker velge drikk/avslutte
    int valg;
    boolean fortsettDrikking = true;
    double alkoholkonsentrasjon = 0;
    final String[] MULIGHETER = {"vin", "\u00f8l", "konjakk", "avslutt"};
/*
    final int vin = 0;
    final int \u00f8l = 1;
    final int konjakk = 2;
    final int avslutt = 3;
*/
    //Bruker en while-løkke ettersom en ikke vet hvor mange ganger det skal kjøres gjennom
    while (fortsettDrikking) {
      valg = showOptionDialog(null, "Velg", "Drikk", DEFAULT_OPTION, PLAIN_MESSAGE, null, MULIGHETER, MULIGHETER[0]);
      if (valg == 3) {
        fortsettDrikking = false;
      }
      else{

        //Sjekker hvor mye av den spesifiserte drikken
        boolean sporMengde;
        int mengde = 0;
        do {
          try {
            mengde = Integer.parseInt(showInputDialog("Hvor mye drakk du (I hele milliliter)"));
            sporMengde = false;
          }
          catch(NumberFormatException nfe) {
            System.out.println("Du skrev inn noe feil, pr\u00f8v p\u00e5 nytt");
            sporMengde = true;
          }
        } while (sporMengde);

        //Henter nye tall for alkoholkonsentrasjonen
        double okningKonsentrasjon = 0;
        if (valg == 0) {
          okningKonsentrasjon = vin.finnAlkoholkonsentrasjonIBlod(mengde, vekt, mann);
        }
        else if (valg == 1) {
          okningKonsentrasjon = ol.finnAlkoholkonsentrasjonIBlod(mengde, vekt, mann);
        }
        else {
          okningKonsentrasjon = konjakk.finnAlkoholkonsentrasjonIBlod(mengde, vekt, mann);
        }

        //legger til den ekstra alkoholen i totalen
        alkoholkonsentrasjon = (alkoholkonsentrasjon + okningKonsentrasjon);

        //Skriver svar til bruker
        if (alkoholkonsentrasjon > 0.2) {
          showMessageDialog(null, "Du kan ikke kj\u00f8re bil if\u00f8lge norsk lov. \nDin promille er " + alkoholkonsentrasjon);
        }
        else {
          showMessageDialog(null, "Du kan enda kj\u00f8re bil if\u00f8lge norsk lov. \nDin promille er " + alkoholkonsentrasjon);
        }
      }
    }
  }
}
