/*
	Øving 3
	06.03.2018
	Anders Kvanvig
*/
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import static javax.swing.JOptionPane.*;

class Klientprogram {
    public static void main(String[] args) {
        String navnSenter = "";
        String[] muligheter = {"Registrer nytt rom", "Ny Reservasjon", "Hent Rominfo (indeks)", "Hent Rominfo (romnummer)", "Alle Rom", "Avslutt"};
        boolean fortsett = true;

        while (navnSenter.equals("")) {
            navnSenter = showInputDialog("Hva heter ditt konferansehotell?");
        }
        Konferansesenter test = new Konferansesenter(navnSenter);

        while (fortsett) {
            int valg = showOptionDialog(null, "Hva ønsker du å gjøre?",  navnSenter, DEFAULT_OPTION, PLAIN_MESSAGE, null, muligheter, muligheter[0]);

            switch (valg) {
                case 0: //Nytt rom
                    int antPers = 0;
                    int romnummer = 0;
                    while (antPers < 1) {
                        try {
                            antPers = Integer.parseInt(showInputDialog("Hvor mange personer er det plass til? (Må være større enn 0)"));
                        }
                        catch (NumberFormatException nfe) {
                            showMessageDialog(null, "Skriv inn et tall");
                        }
                    }
                    while (romnummer < 1) {
                        try {
                            romnummer = Integer.parseInt(showInputDialog("Hva er romnummeret? (Må være større enn 0)"));
                        }
                        catch (NumberFormatException nfe) {
                            showMessageDialog(null, "Skriv inn et tall");
                        }
                    }
                    test.regRom(romnummer, antPers);
                    break;

                case 1: //Ny reservasjon LocalDateTime fraTid,LocalDateTime tilTid, int antPers,String navn, String tlfNummer
                    String navnKunde = "";
                    String tlfKunde = "";
                    int antPersKunde = 0;
                    LocalDateTime fraTid = null;
                    LocalDateTime tilTid = null;
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

                    //Henter Kundens navn, telefonnummer og personer det må være plass til
                    while (navnKunde.equals("")) { navnKunde = showInputDialog("Hva heter kunden?"); }
                    while (tlfKunde.equals("")) { tlfKunde = showInputDialog("Hva er kundens telefonnummer?"); }
                    while (antPersKunde < 1) {
                        try {
                            antPersKunde = Integer.parseInt(showInputDialog("Hvor mange personer må det være plass til"));
                        }
                        catch (NumberFormatException nfe) {
                            showMessageDialog(null, "Skriv inn et tall");
                        }
                    }

                    //Henter fra og til tider
                    while (fraTid == null) {
                        try {
                            String strFraTid = showInputDialog(null, "Når skal det reserveres fra? (yyyy-MM-dd HH:mm)", "2018-03-12 13:00");
                            fraTid = LocalDateTime.parse(strFraTid, formatter);
                        }
                        catch (Exception e) {
                            showMessageDialog(null, "Skriv inn på angitt format");
                        }
                    }
                    while (tilTid == null) {
                        try {
                            String strTilTid = showInputDialog(null, "Når skal det reserveres til? (yyyy-MM-dd HH:mm)", "2018-03-12 15:00");
                            tilTid = LocalDateTime.parse(strTilTid, formatter);
                        }
                        catch (Exception e) {
                            showMessageDialog(null, "Skriv inn på angitt format");
                        }
                    }

                    //Prøver å reservere på gitt tidspunkt
                    boolean romReservert = test.reserverRom(fraTid, tilTid, antPersKunde, navnKunde, tlfKunde);
                    if (romReservert) {
                        showMessageDialog(null, "Rommet har blitt reservert");
                    }
                    else {
                        showMessageDialog(null, "Ingen ledige rom på dette tidspunktet");
                    }
                    break;

                case 2: //Hent rominfo (indeks)
                    int romIndeks = -1;
                    int hoyesteIndeks = test.getAntallRom() - 1;
                    while ((romIndeks < 0 || romIndeks > hoyesteIndeks) && !(hoyesteIndeks < 0)) {
                        try {
                            romIndeks = Integer.parseInt(showInputDialog("Hvilket rom ønsker du å se info om? (mellom 0, og " + hoyesteIndeks + ")"));
                        }
                        catch (NumberFormatException nfe) {
                            showMessageDialog(null, "Skriv inn et tall");
                        }
                    }
                    String romIndeksInfo = test.getRomIndeks(romIndeks).toString();
                    showMessageDialog(null, romIndeksInfo);
                    break;

                case 3: //Hent rominfo (romnummer)
                    romnummer = -1;
                    while (romnummer < 0) {
                        try {
                            romnummer = Integer.parseInt(showInputDialog("Hvilket rom ønsker du å se info om?"));
                        }
                        catch (NumberFormatException nfe) {
                            showMessageDialog(null, "Skriv inn et tall");
                        }
                    }
                    Rom rommet = test.getRom(romnummer);
                    if (rommet.getAntPers() == -1) {
                        showMessageDialog(null, "Rommet eksisterer ikke");
                    }
                    else {
                        showMessageDialog(null, rommet.toString());
                    }
                    break;

                case 4: //Alle rom
                    showMessageDialog(null, test.getAlleRomnummer());
                    break;
                    
                case 5: //Avslutt
                    fortsett = false;
            }
        }

    }
}
