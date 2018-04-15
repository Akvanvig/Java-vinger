/*
	Øving 9
	15.04.2018
	Anders Kvanvig
*/

import java.time.LocalDate;

class TestDyrehage {
    private static Dyrehage dyrehage = new Dyrehage();
    private static Art[] arter = {new DyrSomIndivider("hund", "bjeff", "ulv", "5-6", false), new DyrSomIndivider("ulv", "grr", "ulv", "5-6", true)};

    public static void main(String[] args) {
        //Test 1 registrere arter
        boolean test1 = true;
        for (Art art : arter) {
            test1 = test1 && dyrehage.registrerNyArt(art);
        }
        if (test1) { System.out.println("Test 1 / 7 fullført: arter opprettet"); }

        //Test finnArt (navn)
        Art art1 = dyrehage.finnArt("hund");
        if (art1.getLatNavn().equals("bjeff")) { System.out.println("Test 2 / 7 fullført: hund funnet"); }

        //Test finnArt (indeks)
        Art art2 = dyrehage.finnArt(1);
        if (art2.getLatNavn().equals("grr")) { System.out.println("Test 3 / 7 fullført: ulv funnet"); }

        //Test finnAntArter
        if (dyrehage.finnAntArter() == 2) { System.out.println("Test 4 / 7 fullført: 2 dyr funnet"); }

        //Test registrerNyttIndivid
        if (dyrehage.registrerNyttIndivid("hund", "Dino", LocalDate.of(2015, 2, 15))) { System.out.println("Test 5 / 7 fullført: hundeindivid registrert"); }

        //Test finnIndivid
        if (dyrehage.finnIndivid("hund", "Dino").getFodselsdato().equals(LocalDate.of(2015, 2, 15))) { System.out.println("Test 6 / 7 fullført: hundeindivid funnet"); }

        //Test finnFDatoIndivid
        if (dyrehage.finnFDatoIndivid("hund", "Dino").equals(LocalDate.of(2015, 2, 15))) { System.out.println("Test 7 / 7 fullført: fødselsdato funnet"); }

    }
}
