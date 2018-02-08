/*
Skuddaar.java - 2017-02-13 (yyyy.mm.dd)

Programmet lar bruker skrive inn et år, for så å sjekke om dette er et skuddår
*/
import static javax.swing.JOptionPane.*;

class Skuddaar {
  public static void main(String[] args) {
    String TestAar = showInputDialog("Skriv inn et årstall, for å sjekke om det var et skuddår");
    String Beskjed = "";
    int Aar = 0;
    boolean Svar = false;

    try {
      Aar = Integer.parseInt(TestAar);
    }
    catch(NumberFormatException nfe) {
      Aar = 0;
    }
    //Sjekker om det er kan divideres på 4
    if (Aar % 4 == 0) {
      //Sjekker om det kan divideres på 100
      if (Aar % 100 == 0) {
        //Sjekker om det kan divideres på 400
        if (Aar % 400 == 0) {
          Svar = true;
        }
        else {
          Svar = false;
        }
      }
      else {
        Svar = true;
      }
    }
    else{
      Svar = false;
    }

    //Bestemmer hvilket svar som skal gis til bruker
    if (Svar) {
      Beskjed = "Året " + Aar + " er et skuddår";
    }
    else {
      Beskjed = "Året " + Aar + " er ikke et skuddår";
    }

    //Skriver svar til bruker
    showMessageDialog(null, Beskjed);
  }
}
