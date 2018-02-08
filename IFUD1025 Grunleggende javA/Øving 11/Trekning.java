import java.util.concurrent.ThreadLocalRandom;
import java.util.Arrays;

class Trekning {
  private int[] trekningen;

  Trekning() {
    trekningen = new int[4];
    for (int i = 0; i < trekningen.length; i++) {
      trekningen[i] = ThreadLocalRandom.current().nextInt(0, 9 + 1); //+1 ettersom det øverste tallet visstnok ble utelatt
    }
    Arrays.sort(trekningen);
  }

  public int[] getTrekningen() {
    int[] resultat = new int[trekningen.length];
    for (int i = 0; i < trekningen.length; i++) {
      resultat[i] = trekningen[i];
    }
    Arrays.sort(resultat);
    return resultat;
  }

  public int finnAntallRette(int[] gjettet) {
    Arrays.sort(gjettet);
    boolean fortsettTest = true;
    int rette = 0;
    int gj = 0; //gjettet index
    int tr = 0; //trukket index

    while (fortsettTest) {
      //Sjekker først om de er like, stemmer dette, vil det legges til en rett og begge index-ene økes
      //ettersom disse tallene nå er "brukt opp".
      if (gjettet[gj] == trekningen[tr]) {
        rette++;
        gj++;
        tr++;
      }
      //Om gjettet er større enn trukket, vil den gå til neste index på trukket, hvor den finner
      //et like stort eller større tall ettersom de er sortert etter størrelse.
      else if (gjettet[gj] > trekningen[tr]) {
        tr++;
      }
      //om gjettet er mindre enn trukket, vil den gå til neste index på gjettet og finne et like stort
      //eller større tall enn forrige på gjettet ettersom de er sortert.
      else if (gjettet[gj] < trekningen[tr]) {
        gj++;
      }

      //Hvis noen av index-ene blir større enn fire vil løkken avsluttes ettersom alle tall er sjekket.
      if (gj > gjettet.length - 1 || tr > 3) {
        fortsettTest = false;
      }
    }

    return rette;
  }
}
