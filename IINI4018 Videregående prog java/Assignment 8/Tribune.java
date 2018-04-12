
import java.util.*;

abstract class Tribune {
    private final String tribunenavn;
    private final int kapasitet;
    private final int pris;

    public Tribune(String tribunenavn, int kapasitet, int pris) {
        this.tribunenavn = tribunenavn;
        this.kapasitet = kapasitet;
        this.pris = pris;
    }

    public int getKapasitet() {
        return kapasitet;
    }

    public int getPris() {
        return pris;
    }

    public String getTribunenavn() {
        return tribunenavn;
    }

    public abstract int finnAntallSolgteBilletter();

    public int finnInntekt() {
        return pris * finnAntallSolgteBilletter(); //finnAntallSolgteBilletter() vil leses fra underklassen (f.eks. VIP.finnAntallSolgteBilletter()).
    }

    public abstract Billett[] kjopBilletter(int antBilletter);

    public abstract Billett[] kjopBilletter(String[] navnPers);
}

class Staa extends Tribune {
    private int antSolgteBilletter;

    public Staa(String tribunenavn, int kapasitet, int pris) {
        super(tribunenavn, kapasitet, pris);
        antSolgteBilletter = 0;
    }

    public int finnAntallSolgteBilletter() {
        return antSolgteBilletter;
    }

    public Billett[] kjopBilletter(int antBilletter) {
        if ((antSolgteBilletter + antBilletter) <= getKapasitet()) {
            ArrayList<Billett> billetter = new ArrayList<Billett>();
            for (int i = 0; i < antBilletter; i++) {
                billetter.add(new StaaplassBillett(getTribunenavn(), getPris()));
            }
            antSolgteBilletter += antBilletter;
            return billetter.toArray(new Billett[antBilletter]);
        }
        else {
            return null;
        }
    }

    public Billett[] kjopBilletter(String[] navnPers) {
        int antBilletter = navnPers.length;
        return kjopBilletter(antBilletter);
    }
}

class Sitte extends Tribune {
    private int[] antOpptatt; // tabellstørrelse: antall rader

    public Sitte(String tribunenavn, int kapasitet, int pris, int antRader) {
        super(tribunenavn, kapasitet, pris);
        antOpptatt = new int[antRader];
    }

    public int finnAntallSolgteBilletter() {
        int solgt = 0;
        for (int rad : antOpptatt) {
            solgt += rad;
        }
        return solgt;
    }

    public Billett[] kjopBilletter(int antBilletter) {
        int seterPrRad = (int) (getKapasitet() / antOpptatt.length);

        for (int i = 0; i < antBilletter; i++) {    //for hver enkelt rad fra bunnen sjekkes det etter nok ledige plasser
            if (antOpptatt[i] + antBilletter <= antBilletter) {
                ArrayList<Billett> billetter = new ArrayList<Billett>();
                for (int j = 0; j < antBilletter; j++) {    //Billetter opprettes for hver enkelt plass
                    billetter.add(new SitteplassBillett(getTribunenavn(), getPris(), i, (antOpptatt[i] + j)));
                }
                antOpptatt[i] += antBilletter;
                return billetter.toArray(new Billett[antBilletter]);
            }
        }
        return null;
    }

    public Billett[] kjopBilletter(String[] navnPers) {
        int antPers = navnPers.length;
        return kjopBilletter(antPers);
    }
}

class VIP extends Tribune {
    private String[][] tilskuer; // tabellstørrelse: antall rader * antall plasser pr rad

    public VIP(String tribunenavn, int kapasitet, int pris, int antRader, int antPlasserPrRad) {
        super(tribunenavn, kapasitet, pris);
        tilskuer = new String[antRader][antPlasserPrRad];
    }

    public int finnAntallSolgteBilletter() {
        int solgt = 0;
        for (String[] rad : tilskuer) {
            for (String sete : rad) {
                if (!sete.equals(null)) {
                    solgt++;
                }
            }
        }
        return solgt;
    }

    public Billett[] kjopBilletter(int antBilletter) {
        return null;
    }

    public Billett[] kjopBilletter(String[] navnPers) {
        int antPers = navnPers.length;
        for (int i = 0; i < tilskuer.length; i++) {    //Tribunen fra bunnen, rad for rad, og fyller hver rad fra en kant for å gjøre ting enkelt.
            for (int j = 0; j < tilskuer[i].length; j++) {
                if (tilskuer[i][j].equals(null)) {
                    if (tilskuer[i].length <= (j + antPers)) {  //Hvis det er flere personer enn ledige seter på raden, går søket til neste rad
                        break;
                    }
                    else {  //Ellers opprettes billetter, og setene markeres som fyllt
                        ArrayList<Billett> billetter = new ArrayList<Billett>();
                        for (int k = 0; k < antPers; k++) {
                            billetter.add(new SitteplassBillett(getTribunenavn(), getPris(), i, (j + k)));
                            tilskuer[i][j+k] = navnPers[k];
                        }
                        return billetter.toArray(new Billett[antPers]);
                    }
                }
            }
        }
        return null;    //Hvis ikke det er nok seter på noen av radene
    }
}
