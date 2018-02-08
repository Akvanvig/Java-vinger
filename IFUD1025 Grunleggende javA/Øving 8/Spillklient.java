/*
Spillklient.java - 2017-03-25 (yyyy.mm.dd)

Programmet simulerer et spill om å trekke kort for å få lavest mulig verdi, hvor bruker selv får velge antall runder som skal kjøres
*/
import static javax.swing.JOptionPane.*;

class Spillklient {
  public static void main(String[] args) {
    boolean fortsetteSpill = false;
    final String[] MULIGHETER = {"Nytt Spill", "Avslutt"};
    int valg = 1;

    do {
      //bruker får muligheten til å starte et nytt spill, eller avslutte programmet
      valg = showOptionDialog(null, "\u00f8nsker du \u00e5 starte et nytt spill?", "Kortspill", DEFAULT_OPTION, PLAIN_MESSAGE, null, MULIGHETER, MULIGHETER[0]);
      if (valg == 0) {
        fortsetteSpill = true;

        //Tar inn et gitt antall runder i spillet det skal trekkes kort.
        boolean tallTest = true;
        int antRunder = 0;
        while (tallTest) {
          String antallRunderStr = showInputDialog("Hvor mange runder skal det spilles?");
          //Prøver å gjøre gjøre hva bruker har skrevet inn til en Integer
          try {antRunder = Integer.parseInt(antallRunderStr); tallTest = false;}
          catch (NumberFormatException nfe) {tallTest = true;}
        }

        //Henter navnet til spillerne og oppretter spillet
        String spiller1 = showInputDialog("Hva er navnet til spiller 1?");
        String spiller2 = showInputDialog("Hva er navnet til spiller 2?");
        Spill spill1 = new Spill(spiller1, spiller2, antRunder);

        //Gjennomfører gitt antall runder
        while (! spill1.spillFerdig()) {
          //Trekker nye kort
          spill1.trekkKort();
          //bytter om begge ønsker det(Fikk kort dårligere enn 'M')
          spill1.bytte();
          //Sjekker resultat av runden
          spill1.sjekkVinner();
          //Viser resultat til bruker
          System.out.println(spill1.visResultat());
        }
      }
      else {
        fortsetteSpill = false;
      }
    } while (fortsetteSpill);
  }
}
