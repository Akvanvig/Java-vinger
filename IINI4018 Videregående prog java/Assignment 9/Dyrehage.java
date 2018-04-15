/*
	Øving 9
	15.04.2018
	Anders Kvanvig
*/

import java.util.*;
import java.time.LocalDate;

class Dyrehage {
    private ArrayList<Art> arter = new ArrayList<Art>();

    public boolean registrerNyArt(Art nyArt) {
        for (Art art : arter) {
            if (art.getNorskNavn().equals(nyArt.getNorskNavn())) {
                return false;
            }
        }
        //Hvis arten ikke er lagt inn vil ikke funksjonen ha blitt stoppet enda, og arten kan legges til
        arter.add(nyArt);
        return true;
    }

    public Art finnArt(String artsNavn) {
        for (Art art : arter) {
            if (art.getNorskNavn().equals(artsNavn) || art.getLatNavn().equals(artsNavn)) {
                return art;
            }
        }
        //Hvis ingen arter med gitt navn er funnet (hverken latisk eller norsk)
        return null;
    }

    public Art finnArt(int artsIndeks) {
        if (artsIndeks < 0 || artsIndeks >= arter.size()) { //Hvis artsindeks er ugyldig
            return null;
        }
        return arter.get(artsIndeks);
    }

    public int finnAntArter() {
        return arter.size();
    }

    public boolean registrerNyttIndivid(String artsnavn, String individnavn, LocalDate fodselsdato) {
        for (Art art : arter) {
            //Hvis artsnavn stemmer med enten Latinsk eller norsk navn, og objekttypen er "DyrSomIndivider"
            if ((art.getNorskNavn().equals(artsnavn) || art.getLatNavn().equals(artsnavn)) && art instanceof DyrSomIndivider) {
                DyrSomIndivider arten = (DyrSomIndivider) art;
                return arten.registrerIndivid(individnavn, fodselsdato);
            }
        }
        return false;
    }

    public Individ finnIndivid(String artsnavn, String individnavn) {
        for (Art art : arter) {
            //Hvis artsnavn stemmer med enten Latinsk eller norsk navn, og objekttypen er "DyrSomIndivider"
            if ((art.getNorskNavn().equals(artsnavn) || art.getLatNavn().equals(artsnavn)) && art instanceof DyrSomIndivider) {
                DyrSomIndivider arten = (DyrSomIndivider) art;
                return arten.finnIndivid(individnavn);
            }
        }
        return null;
    }

    public LocalDate finnFDatoIndivid(String artsnavn, String individnavn) {
        for (Art art : arter) {
            //Hvis artsnavn stemmer med enten Latinsk eller norsk navn, og objekttypen er "DyrSomIndivider"
            if ((art.getNorskNavn().equals(artsnavn) || art.getLatNavn().equals(artsnavn)) && art instanceof DyrSomIndivider) {
                DyrSomIndivider arten = (DyrSomIndivider) art;
                return arten.finnFDatoIndivid(individnavn);
            }
        }
        return null;
    }
}

/*
*TestProgram for klassen dyrehage:
*/
/*
class TestDyrehage {
    private static Dyrehage dyrehage = new Dyrehage();
    private static Art[] arter = {new Art("hund", "bjeff", "ulv"), new Art("katt", "mjau", "katt"), new Art("ulv", "grr", "ulv")};

    public static void main(String[] args) {
        //Test registrerNyArt
        boolean test1 = true;
        for (Art art : arter) {
            test1 = test1 && dyrehage.registrerNyArt(art);
        }
        if (test1) {
            System.out.println("Test 1 / 4 fullført: arter opprettet");
        }

        //Test finnArt (navn)
        Art art1 = dyrehage.finnArt("hund");
        if (art1.getLatNavn().equals("bjeff")) {
            System.out.println("Test 2 / 4 fullført: hund funnet");
        }

        //Test finnArt (indeks)
        Art art2 = dyrehage.finnArt(2);
        if (art2.getLatNavn().equals("grr")) {
            System.out.println("Test 3 / 4 fullført: ulv funnet");
        }

        //Test finnAntArter
        if (dyrehage.finnAntArter() == 3) {
            System.out.println("Test 4 / 4 fullført: 3 dyr funnet");
        }
    }
}
*/
