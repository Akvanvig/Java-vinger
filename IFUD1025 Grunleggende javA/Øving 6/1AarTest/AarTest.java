/*
Enkelnorsk.java - 2017-02-13 (yyyy.mm.dd)

Programmet tar inn et årstall fra bruker, og gir tilbake litt informasjon til bruker
*/

import static javax.swing.JOptionPane.*;

class AarTest {
  public static void main(String[] args) {
    String årstallLest = showInputDialog("\u00c5rstall?");
    int årstall = Integer.parseInt(årstallLest);
    Aar år = new Aar(årstall);

    String tekst = "\u00c5ret er " + år.getAar()
        + "\nI fjor var det " + år.beregnIFjor()
        + "\nNeste \u00e5r blir det " + år.beregnNesteAar()
        + "\nFor tre \u00e5r siden var det " + år.beregnOmNoenAar(-3)
        + "\nOm tre \u00e5r blir det " + år.beregnOmNoenAar(3);
    if (år.isSkuddaar()) {
      tekst += "\n" + årstall + " er skudd\u00e5r";
    } else {
      tekst += "\n" + årstall + " er ikke skudd\u00e5r";
    }
    showMessageDialog(null, tekst);
  }
} // AarTest

/* Hvis brukeren f.eks. oppgir 2016 som årstall, skal utskriften bli:
Året er 2016
I fjor var det 2015
Neste år blir det 2017
For tre år siden var det 2013
Om tre år blir det 2019
2016 er skuddår
*/
