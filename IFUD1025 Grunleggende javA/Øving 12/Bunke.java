import java.util.*;

class Bunke {
  private Egenskapssett egenskapssettet;
  private Kort[] kortene;
  private int antallKort;

  Bunke(String[] fargene, int minVerdi, int maxVerdi) {
    egenskapssettet = new Egenskapssett(fargene, minVerdi, maxVerdi);
    kortene = egenskapssettet.finnKortstokken();
    antallKort = kortene.length;
  }

  Bunke(int minVerdi, int maxVerdi) {
    egenskapssettet = new Egenskapssett(minVerdi, maxVerdi);
    kortene = egenskapssettet.finnKortstokken();
    antallKort = kortene.length;
  }

  Bunke(Egenskapssett egenskapssettet) {
    this.egenskapssettet = egenskapssettet;
    kortene = egenskapssettet.finnKortstokken();
    antallKort = kortene.length;
  }

  public int getAntallKort() {
    return antallKort;
  }

//Senker antallKort med 1, og setter hva som pleide å være øverste kort til null
  public Kort fjernToppen() {
    antallKort --;
    Kort resultat = new Kort(kortene[antallKort].getFarge(), kortene[antallKort].getValoer());
    kortene[antallKort] = null;

    return resultat;
  }

  public void snu() {
    int teller = 0;
    Kort[] snudd = new Kort[kortene.length];
    //Begynner bakerst i kortstokken og teller meg ned til index 0 samtidig som jeg kopierer
    //kortene over i arrayen "snudd"
    for (int i = antallKort - 1; i >= 0; i --) {
      snudd[teller] = kortene[i];
      teller ++;
    }
    kortene = snudd;
  }

  public void stokk() {

    int[] flyttet = new int[antallKort];
    Kort[] stokket = new Kort[kortene.length];
    Random tilfeldig = new Random();
    //Lager en liste over hvilke kort som er brukt
    for (int i = 0; i < antallKort; i++) {
      flyttet[i] = 1;
    }

    //Flytter over ett og ett kort trukket tilfeldig
    for (int i = 0; i < antallKort; i++) {
      int trukket = tilfeldig.nextInt(antallKort);
      boolean leterEtterKort = true;
      int antRunder = 0;

      //Bruker ei while-løkke for å sjekke om kort[trukket] er brukt
      while (leterEtterKort) {
        if (trukket == antallKort) {
          trukket = 0;
          antRunder ++;
        }
        //Hvis kortet ikke er brukt, blir det lagt over i stokket, og markert som brukt
        if (flyttet[trukket] == 1) {
          stokket[i] = new Kort(kortene[trukket].getFarge(), kortene[trukket].getValoer());
          flyttet[trukket] = 0;
          leterEtterKort = false;
        }
        //Over kortet var brukt, sjekker vi neste kort i rekken for å se om det er tatt.
        else {
          trukket ++;
        }
        //Hvis det har blitt gjennomført to runder, vil jakten på kort avbrytes for å unngå en evig loop
        //Om dette hender er det en feil i programmet :(
        //(Brukt under tidlig testing, burde ikke forkomme nå)
        if (antRunder == 2) {
          leterEtterKort = false;
        }
      }
    }
    kortene = stokket;
  }

  public String toString() {
    String tekst = "";
    for (int i = 0; i < antallKort; i++) {
      tekst += kortene[i].toString() + " - ";
    }
    return tekst;
  }
}
