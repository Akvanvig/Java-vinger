/*
GjettAntall.java - 2017-02-17 (yyyy.mm.dd)

Programmet lar bruker skrive inn en tekst for så å gjette hvor mange ganger et brukergitt tegn brukes
*/
import static javax.swing.JOptionPane.*;

class GjettAntall {
  public static void main(String[] args) {
    //Henter inn tekst
    String tekst = showInputDialog("Skriv inn en tekst");
    System.out.println("Du skrev teksten: " + tekst);
    boolean test1 = true;
    char tegn = 'e';
    String beskjed;

    //Henter tegn fra bruker
    while (test1) {
      String strTegn = showInputDialog("Skriv et tegn");
      //Sjekker om det ble skrevet inn noe
      if (strTegn.length() > 0) {
        tegn = strTegn.charAt(0);
        test1 = false;
        beskjed = "Du valgte tegnet: " + tegn;
      }
      else {
        beskjed = "Du skrev ikke inn noe tegn, pr\u00f8v p\u00e5 nytt";
      }
      System.out.println(beskjed);
    }

    //Teller antall av tegnet
    int antall = 0;
    int antTegn = 0;
    antall = tekst.length();
    //gjør om teksten til en char-array
    char[] TekstTegn = tekst.toCharArray();
    for (int i = 0; i < antall; i++) {
      //Sjekker hvor mange av tegnene som er lik det oppgitte tegnet
      if (TekstTegn[i] == tegn) {
        antTegn++;
      }
    }

    //Spør om bruker ønsker å gjette, bruker do while løkke fordi denne kjører gjennom en gang uansett
    boolean test2 = true;
    do {
      String svar = showInputDialog("\u00f8nsker du \u00e5 gjette antall " + tegn + "-er?");
      if (svar.equals("ja")) {
        try {
          int gjette = Integer.parseInt(showInputDialog("Hvor mange " + tegn + "-er tror du det er?"));
          System.out.println("Du gjettet " + gjette + " " + tegn + "-er");
          //Sjekker om svaret stemmer
          if (gjette == antTegn) {
            beskjed = "Du gjettet riktig";
            test2 = false;
          }
          else {
            beskjed = "Du gjettet feil.";
          }
          System.out.println(beskjed);
        }
        catch (NumberFormatException nfe) {
          System.out.println("Du skrev inn noe feil!");
        }
      }
      else {
        test2 = false;
        System.out.println("Antall forekomster av tegnet '" + tegn + "' er " + antTegn);
      }
    } while (test2);

  }
}
