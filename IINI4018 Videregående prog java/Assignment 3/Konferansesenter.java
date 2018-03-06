/*
	Øving 3
	27.02.2018
	Anders Kvanvig
*/
import java.util.*;
import java.time.*;

class Konferansesenter{
    private ArrayList<Rom> moterom = new ArrayList<Rom>();
    private ArrayList<Kunde> kunder = new ArrayList<Kunde>();
    private String navn;

    public Konferansesenter(String navn) {
      this.navn = navn;
    }
    //Returnerer true om rom reserveres, false om ikke ledig
    public boolean reserverRom(LocalDateTime fraTid,LocalDateTime tilTid, int antPers,String navn, String tlfNummer) {
        boolean reservert = false;
        Kunde kunden = null;
        //Sjekker navn/nummer mot kundeliste og oppretter eventuellt ny kunde
        //En person med samme navn, men forskjellig nummer vil regnes som ny kunde samme med samme nummer, endret navn
        for (Kunde kunde:kunder) {
            if (kunde.getNavn().equals(navn) && kunde.getTlf().equals(tlfNummer)) {
                kunden = kunde;
            }
        }
        if (kunden == null) {
            kunder.add(new Kunde(navn, tlfNummer));
            kunden = kunder.get(kunder.size() - 1);
        }

        Collections.sort(moterom);
        //Finner det minste ledige rommet som er stort nok og ledig og setter av det
        for (Rom rom: moterom) {
            if (rom.getAntPers() >= antPers) {
                reservert = rom.reserver(fraTid, tilTid, kunden);
                //Hvis rommet er bekreftet reservert brytes for-løkken
                if (reservert) {
                    break;
                }
            }
        }
        return reservert;
    }

    //Registrer nytt rom
    public boolean regRom(int romnummer, int persTillatt) {
        boolean ledig = true;
        //Sjekker om romnummeret er ledig
        for (Rom rom : moterom) {
            if (rom.getRomnummer() == romnummer) {
                ledig = false;
            }
        }
        //Oppretter rommet
        if (ledig) {
            moterom.add(new Rom(romnummer, persTillatt));
        }
        return ledig;
    }

    //Forutsetter gyldig Indeks
    public Rom getRomIndeks(int romIndeks) {
        return moterom.get(romIndeks);
    }

    //Returnerer et rom med romnummer og antPers lik -1 om ikke eksisterer
    public Rom getRom(int romnummer) {
        Rom resultatRom = new Rom(-1, -1);
        for (Rom rom:moterom) {
            if (rom.getRomnummer() == romnummer) {
                resultatRom = rom;
            }
        }
        return resultatRom;
    }

    public String getAlleRomnummer() {
        String resultatTekst = "";
        for (Rom rom:moterom) {
            resultatTekst += rom.getRomnummer() + ", størrelse: " + rom.getAntPers() + " personer\n";
        }
        return resultatTekst;
    }

    public int getAntallRom() {
        return moterom.size();
    }

}
