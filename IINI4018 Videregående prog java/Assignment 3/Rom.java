/*
	Øving 3
	06.03.2018
	Anders Kvanvig
*/
import java.util.*;
import java.time.*;

class Rom implements Comparable<Rom>{
    private int romnummer;
    private int antPers; //Maks antall personer rommet er satt opp for
    private ArrayList<Reservasjon> reservasjoner = new ArrayList<Reservasjon>();

    //Konstruktør
    public Rom(int romnummer, int antPers) {
        this.romnummer = romnummer;
        this.antPers = antPers;
    }

    //Romreservasjon
    public boolean reserver(LocalDateTime fraTid, LocalDateTime tilTid, Kunde kunden) {
        //Sjekker først for reservasjoner på samme tidspunkt
        boolean opptatt = false;
        for (Reservasjon res:reservasjoner) {
            if (res.overlapp(fraTid, tilTid)) {
                opptatt = true;
            }
        }
        //Legger så inn reservasjon om det er ledig
        if (!opptatt) {
            reservasjoner.add(new Reservasjon(fraTid, tilTid, kunden));
        }

        return !opptatt;
    }

    //Brukes til å sortere lista med rom etter størrelse
    public int compareTo(Rom annetRom) {
        return (antPers - annetRom.getAntPers());
	}

    public int getAntPers() {
        return antPers;
    }

    public int getRomnummer() {
        return romnummer;
    }

    //Skriver ut info om rommet
    public String toString() {
        String resultatTekst =  "Romnummer: " + romnummer + "\nAntall Personer: " + antPers +
                                "\nReservasjoner: ";
        LocalDateTime naa = LocalDateTime.now();

        //Legger til alle fremtidige reservasjoner
        for (Reservasjon res:reservasjoner) {
            if (res.getTilTid().isAfter(naa)) {
                resultatTekst += "\n" + res.toString();
            }
        }
        return resultatTekst;
    }

    //Testprogram Rom
    public static void main(String[] args) {
        Rom test = new Rom(101, 5);
        System.out.println("test reservasjon");
        boolean a = test.reserver(LocalDateTime.of(2018, 3, 21, 10, 0), LocalDateTime.of(2018, 3, 21, 14, 0), new Kunde("Lars", "112"));
        boolean b = test.reserver(LocalDateTime.of(2018, 3, 21, 8, 0), LocalDateTime.of(2018, 3, 21, 10, 0), new Kunde("Elise", "110"));
        boolean c = test.reserver(LocalDateTime.of(2018, 3, 21, 9, 0), LocalDateTime.of(2018, 3, 21, 12, 0), new Kunde("Kristian", "113"));
        System.out.println(a + " " + b + " " + c);

        System.out.println("");
        System.out.println(test.toString());

        System.out.println("");
        System.out.println("Antall Personer = " + test.getAntPers());
        System.out.println("Romnummer = " + test.getRomnummer());
        System.out.println("Compare mindre: " + test.compareTo(new Rom(102, 4)));
        System.out.println("Compare større: " + test.compareTo(new Rom(103, 6)));

    }
}
