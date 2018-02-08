/*
Stjerner.java - 2017-02-17 (yyyy.mm.dd)

Programmet tar inn et tall fra bruker, og tegner deretter opp x antall linjer med x antall stjerner
*/

import static javax.swing.JOptionPane.*;

class Stjerner {
  public static void main(String[] args) {
    String strAntall = showInputDialog("Skriv inn antall rader du \u00f8nsker");
    int Antall;
    String beskjed;

    //Sjekker tallet bruker skriver inn
    try {
      Antall = Integer.parseInt(strAntall);
      if (Antall < 1) {
        beskjed = "Du har skrevet inn et for lite tall! \nAntall er satt til en.";
        Antall = 1;
        showMessageDialog(null, beskjed);
      }
    }
    catch (NumberFormatException nfe) {
      beskjed = "Du har skrevet inn noe feil! \nAntall er satt til tre.";
      Antall = 3;
      showMessageDialog(null, beskjed);
    }

    //Bruker variabelen Linje til å tegne inn korrekt antall stjerner før det gis en print kommando
    String Linje;
    //Første løkke står for å gå til neste linje
    for (int i = 0; i < Antall; i++) {
      Linje = "";
      //Andre løkke tegner opp riktig antall stjerner
      for (int j = 0; j < i + 1; j++) {
        Linje += "*";
      }
      System.out.println(Linje);
    }
  }
}
