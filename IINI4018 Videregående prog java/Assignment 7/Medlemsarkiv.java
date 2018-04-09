/*
	Øving 7
	09.04.2018
	Anders Kvanvig
*/

import java.util.*;
import java.time.*;

class Medlemsarkiv {
    private ArrayList<BonusMedlem> medlemmer = new ArrayList<BonusMedlem>();

    public int nyMedlem(Personalia pers, LocalDate innmeldt) {
        int medlNr = finnLedigNr();
        medlemmer.add(new BasicMedlem(medlNr, pers, innmeldt));
        return medlNr;
    }

    public int finnPoeng(int medlNr, String passord) {
        int poeng = 0;
        for (BonusMedlem medlem : medlemmer) {
            if (medlem.getMedlnr() == medlNr) {
                if (medlem.okPassord(passord)) {
                    return medlem.getPoeng();
                }
                else {
                    break;
                }
            }
        }
        return -1;
    }

    public boolean registrerPoeng(int medlNr, int antPoeng) {
        for (BonusMedlem medlem : medlemmer) {
            if (medlem.getMedlnr() == medlNr) {
                medlem.registrerPoeng(antPoeng);
                return true;
            }
        }
        return false;
    }

    public String printMedlem(int medlNr) {
        for (BonusMedlem medlem : medlemmer) {
            if (medlem.getMedlnr() == medlNr) {
                return medlem.toString();
            }
        }
        return "Ingen medlemmer med gitt nummer funnet";
    }

    public void sjekkMedlemmer(LocalDate dato) {
        for (int i = 0; i < medlemmer.size(); i++) {
            String klasse = medlemmer.get(i).getClass().getSimpleName();
            int poeng = medlemmer.get(i).finnKvalPoeng(dato);
            switch (klasse) {
                case "BasicMedlem": //Hvis en er basic-medlem og oppgraderes til gull vil en først bli oppgradert til sølv, for så å falle gjennom til gull
                    if (poeng >= 25000) {
                        medlemmer.set(i, new SoelvMedlem(medlemmer.get(i).getMedlnr(),
                                                        medlemmer.get(i).getPers(),
                                                        medlemmer.get(i).getInnmeldtDato(),
                                                        medlemmer.get(i).getPoeng()));
                    }
                case "SoelvMedlem":
                    if (poeng >= 75000) {
                        medlemmer.set(i, new GullMedlem(medlemmer.get(i).getMedlnr(),
                                                        medlemmer.get(i).getPers(),
                                                        medlemmer.get(i).getInnmeldtDato(),
                                                        medlemmer.get(i).getPoeng()));
                    }
                    break;
            }
        }
    }

    private int finnLedigNr() {
        int ledigNummer = 0;
        boolean ledig = false;
        Random rand = new Random();
        while (!ledig) {
            ledig = true;
            ledigNummer = 1000 + rand.nextInt(99000);
            for (BonusMedlem medlem : medlemmer) {
                if (medlem.getMedlnr() == ledigNummer) {
                    ledig = false;
                    break;
                }
            }
        }
        return ledigNummer;
    }
}
