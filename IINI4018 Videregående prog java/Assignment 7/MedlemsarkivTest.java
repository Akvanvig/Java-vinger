/*
	Øving 7
	09.04.2018
	Anders Kvanvig
*/

import java.time.*;

class MedlemsarkivTest {
    public static void main(String[] args) {
        Medlemsarkiv a = new Medlemsarkiv();
        Personalia b1 = new Personalia("Erik", "Larsen", "erla@hotmail.com", "iii");
        Personalia b2 = new Personalia("Alf", "Knudsen", "alkn@hotmail.com", "iii");
        Personalia b3 = new Personalia("Lise", "Ødegaard", "Liod@hotmail.com", "iii");

        int nr1 = a.nyMedlem(b1, LocalDate.of(2018, 1, 1));
        System.out.println(a.registrerPoeng(nr1, 60000));
        System.out.println(a.printMedlem(nr1));

        int nr2 = a.nyMedlem(b2, LocalDate.of(2018, 1, 1));
        System.out.println(a.registrerPoeng(nr2, 160000));
        System.out.println(a.printMedlem(nr2));

        int nr3 = a.nyMedlem(b3, LocalDate.of(2017, 1, 1));
        System.out.println(a.registrerPoeng(nr3, 160000));
        System.out.println(a.printMedlem(nr3));

        a.sjekkMedlemmer(LocalDate.of(2018, 4, 9));

        System.out.println(a.printMedlem(nr1));
        System.out.println(a.printMedlem(nr2));
        System.out.println(a.printMedlem(nr3));

        System.out.println(a.finnPoeng(nr1, "iii"));
    }
}
