/*
	Øving 7
	09.04.2018
	Anders Kvanvig
*/

import java.time.*;
import java.time.temporal.ChronoUnit;

class BonusMedlem {
    private final int medlNr;
    private final Personalia pers;
    private final LocalDate innmeldtDato;
    private int poeng = 0;

    public BonusMedlem(int medlNr, Personalia pers, LocalDate dato) {
        this.medlNr = medlNr;
        this.pers = pers;
        this.innmeldtDato = dato;
    }

    public BonusMedlem(int medlNr, Personalia pers, LocalDate dato, int poeng) {
        this.medlNr = medlNr;
        this.pers = pers;
        this.innmeldtDato = dato;
        this.poeng = poeng;
    }

    public int getMedlnr() {
        return this.medlNr;
    }

    public Personalia getPers() {
        return this.pers;
    }

    public LocalDate getInnmeldtDato() {
        return this.innmeldtDato;
    }

    public int getPoeng() {
        return this.poeng;
    }

    public int finnKvalPoeng(LocalDate dato) {
        long dagerMellom = ChronoUnit.DAYS.between(this.innmeldtDato, dato);
        if (dagerMellom <= 365) {
            return this.poeng;
        }
        else {
            return 0;
        }
    }

    public boolean okPassord(String passord) {
        return pers.okPassord(passord);
    }

    public void registrerPoeng(int regPoeng) {
        this.poeng += regPoeng;
    }

    public String toString() {
        return (this.getClass().getSimpleName() + " " + poeng + ", " + pers.getFornavn() + " " + pers.getEtternavn() + ", " + innmeldtDato);
    }
}

class BasicMedlem extends BonusMedlem {
    public BasicMedlem(int medlNr, Personalia pers, LocalDate dato) {
        super(medlNr, pers, dato);
    }

    public BasicMedlem(int medlNr, Personalia pers, LocalDate dato, int poeng) {
        super(medlNr, pers, dato, poeng);
    }
}

class SoelvMedlem extends BonusMedlem {
    public SoelvMedlem(int medlNr, Personalia pers, LocalDate dato) {
        super(medlNr, pers, dato);
    }

    public SoelvMedlem(int medlNr, Personalia pers, LocalDate dato, int poeng) {
        super(medlNr, pers, dato, poeng);
    }

    public void registrerPoeng(int regPoeng) {
        regPoeng = (int) (regPoeng * 1.2);
        super.registrerPoeng(regPoeng);
    }
}

class GullMedlem extends BonusMedlem {
    public GullMedlem(int medlNr, Personalia pers, LocalDate dato) {
        super(medlNr, pers, dato);
    }

    public GullMedlem(int medlNr, Personalia pers, LocalDate dato, int poeng) {
        super(medlNr, pers, dato, poeng);
    }

    public void registrerPoeng(int regPoeng) {
        regPoeng = (int) (regPoeng * 1.5);
        super.registrerPoeng(regPoeng);
    }
}
