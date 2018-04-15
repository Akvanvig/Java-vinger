/*
	Øving 9
	15.04.2018
	Anders Kvanvig
*/

import java.time.LocalDate;

class Individ {
    private final String individnavn;
    private final LocalDate fodselsdato;

    public Individ(String navn, LocalDate fodselsdato) {
        this.individnavn = navn;
        this.fodselsdato = fodselsdato;
    }

    public String getIndividnavn() {
        return individnavn;
    }

    public LocalDate getFodselsdato() {
        return fodselsdato;
    }
}
