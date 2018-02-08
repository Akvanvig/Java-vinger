/**
  * Kortspilltest
  *
  */

class Kortspilltest {
  public static void main(String[] args) {
    Egenskapssett egenskapssettet = new Egenskapssett(3, 4);
    System.out.println("Informasjon om egenskapssettet:");
    System.out.println("Minste val\u00f8r: " + egenskapssettet.getMinValoer());
    System.out.println("St\u00f8rste val\u00f8r: " + egenskapssettet.getMaxValoer());
    System.out.println("Antall val\u00f8rer: " + egenskapssettet.finnAntallValoerer());
    String resultat = "Alle val\u00f8rene: ";
    int[] valoerene = egenskapssettet.finnValoerene();
    for (int i = 0; i < egenskapssettet.finnAntallValoerer(); i++) {
      resultat += valoerene[i] + " ";
    }
    System.out.println(resultat);

    System.out.println("Antall kort i kortstokk bestemt av egenskapssettet: "
        + egenskapssettet.finnAntallKortIKortstokken()); // kaller en ekstra metode

    Bunke bunken = new Bunke(egenskapssettet);
    System.out.println("En ny bunke med " + bunken.getAntallKort() + " kort:");
    System.out.println(bunken);  // får her indirekte testet Egenskapssett-metoden finnKortstokken()
    bunken.stokk();
    System.out.println("Bunken etter stokking:");
    System.out.println(bunken);

    System.out.println("\nFjerner tre kort fra toppen av bunken, nemlig:");
    System.out.println(bunken.fjernToppen());
    System.out.println(bunken.fjernToppen());
    System.out.println(bunken.fjernToppen());

    System.out.println("Bunken med " + bunken.getAntallKort() + " kort:");
    System.out.println(bunken);

    System.out.println("Etter \u00e5 ha snudd bunken:");
    bunken.snu();
    System.out.println(bunken);

    bunken.stokk();
    System.out.println("Bunken etter ny stokking:");
    System.out.println(bunken);
  }
} // Kortspilltest


/* Kjøring:
Informasjon om egenskapssettet:
Minste valør: 3
Største valør: 4
Antall valører: 2
Alle valørene: 3 4
Antall kort i kortstokk bestemt av egenskapssettet: 8
En ny bunke med 8 kort:
spar 3 - spar 4 - kløver 3 - kløver 4 - hjerter 3 - hjerter 4 - ruter 3 - ruter
4 -
Bunken etter stokking:
spar 4 - hjerter 3 - hjerter 4 - kløver 3 - ruter 3 - kløver 4 - ruter 4 - spar
3 -

Fjerner tre kort fra toppen av bunken, nemlig:
spar 3
ruter 4
kløver 4
Bunken med 5 kort:
spar 4 - hjerter 3 - hjerter 4 - kløver 3 - ruter 3 -
Etter å ha snudd bunken:
ruter 3 - kløver 3 - hjerter 4 - hjerter 3 - spar 4 -
Bunken etter ny stokking:
kløver 3 - spar 4 - hjerter 4 - ruter 3 - hjerter 3 -
*/
