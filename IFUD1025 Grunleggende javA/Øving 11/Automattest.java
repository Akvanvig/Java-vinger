import static javax.swing.JOptionPane.*;
/**
 * Automattest
 *
 */
class Automattest {
  public static void main(String[] args) {
    /*
    * Vi lager automaten:
    * Prisen kan oppgis i inntil 200 valutasorter og
    * det er plass til 5 mynter.
    */
    Automat automaten = new Automat(200, 5);
    /*
    Vi setter prisen for tre valutaer: EUR, USD og NOK.
    */
    final String[] VALUTAMULIGHETER = {"EUR", "USD", "NOK"}; // 3 stk
    final double[] PRISENE = {0.12, 0.16, 1.0}; // også 3 stk
    for (int i = 0; i < VALUTAMULIGHETER.length; i++) {
      automaten.settPris(VALUTAMULIGHETER[i], PRISENE[i]);
    }
    /*
    * Så lenge bøssen ikke er full, vil følgende store løkke gå.
    * I løkken leser vi inn mynten og tippingen,
    * kaller Automats spill()-metode, og
    * skriver ut trekningen og antall rette.
    */
    do {
      /*
      Vi leser inn mynten.
      */
      int valuta = showOptionDialog(null,
        automaten.finnPrislisten() + "\n\nDin mynts valuta:",
        "Legg p\u00e5 en mynt (del 1)",
        DEFAULT_OPTION,
        PLAIN_MESSAGE, null,
        VALUTAMULIGHETER,
        VALUTAMULIGHETER[0]);
      String valutanavn = VALUTAMULIGHETER[valuta];
      String innlestVerdi = showInputDialog(null,
        "Hvert tippetall koster "
        + automaten.finnPrisEttTall(valutanavn) + " "
        + valutanavn + "."
        + "\n\nDin mynts verdi i "
        + valutanavn
        + ":", "Legg på en mynt (del 2)", QUESTION_MESSAGE);
      double verdi = Double.parseDouble(innlestVerdi);
      /*
      Vi leser inn tippingen.
      */
      int antallTippetall = automaten.finnAntallTippetall(valutanavn, verdi);
      int[] tippingen = new int[antallTippetall];
      final String[] TALLMULIGHETER // litt stygt med "0"... "9" direkte
        = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
      for (int i = 0; i < antallTippetall; i++) {
        tippingen[i] = showOptionDialog(null,
        "Tipp et tall:",
        "Tipping (tall nr " + (i + 1) + " av " + antallTippetall + ")",
        DEFAULT_OPTION,
        PLAIN_MESSAGE,
        null,
        TALLMULIGHETER,
        TALLMULIGHETER[0]);
      }
      /*
      Vi lager spilleren og skriver ut informasjon om spilleren.
      */
      Spiller spilleren = new Spiller(valutanavn, verdi, tippingen);
      System.out.println("Dine data: " + spilleren);
      /* Vi spiller og skriver ut resultatene. */
      Trekning trekningen = automaten.spill(spilleren);
      int[] tallene = trekningen.getTrekningen();
      System.out.print("Følgende tall ble trukket: ");

      for (int j = 0; j < tallene.length; j++) {
        System.out.print(tallene[j] + " -");
      }

      System.out.println("\nDu har da " + automaten.finnAntallRette(trekningen, spilleren) + " rette");
      System.out.println("--------------------------------------------");

    } while (!automaten.erBoessenFull());
    /*
    Vi skriver ut innholdet av den fulle bøssen.
    */
    System.out.println(automaten.finnBoessen());
  }
}
// Automattest
