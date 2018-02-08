/*
Enkelnorsk.java - 2017-02-13 (yyyy.mm.dd)

Programmer tar inn tekst fra bruker, og sjekker om det følger visse regler
*/
import static javax.swing.JOptionPane.*;

class Enkelnorsk {
  public static void main(String[] args) {
    String tekststreng = showInputDialog("Skriv inn noe tekst");
    String beskjed;

    int bokstavtest1 = tekststreng.indexOf('c') + tekststreng.indexOf('q') + tekststreng.indexOf('w') + tekststreng.indexOf('x') + tekststreng.indexOf('z');
    int bokstavtest2 = tekststreng.indexOf('\u00e6') + tekststreng.indexOf('\u00f8') + tekststreng.indexOf('\u00e5');

    if (bokstavtest1 == -5 && bokstavtest2 != -3) {
      beskjed = "Denne teksten oppfyller kravene satt for \u00e5 kalles 'Enkelnorsk'";
    }
    else {
      beskjed = "Denne teksten oppfyller ikke kravene satt for \u00e5 kalles 'Enkelnorsk'";
    }

    showMessageDialog(null, beskjed);
  }
}
