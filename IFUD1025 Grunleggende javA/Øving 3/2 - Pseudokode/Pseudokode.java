/*
Pseudokode.java - 2017-02-13 (yyyy.mm.dd)

Programmet lar bruker skrive inn en tekststreng, for så å lokalisere den første c-en(hvis det er noen)
*/
import static javax.swing.JOptionPane.*;

class pseudo {
  public static void main(String[] args) {
    String tekststreng = showInputDialog("Skriv inn noe tekst");
    int pos = tekststreng.indexOf('c');
    String beskjed;

    //Hvis det ikke finnes noen c-er i teksten, vil posisjonen gis som -1
    //Sjekker derfor om pos er større enn dette
    if (pos > -1) {
      beskjed = "Den første c-en i denne teksten er på posisjon " + pos;
    }
    else {
      beskjed = "Det finnes ingen c-er i denne teksten";
    }

    showMessageDialog(null, beskjed);
  }
}
