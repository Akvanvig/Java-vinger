/*
	Øving 1
	11.02.2018
	Anders Kvanvig
*/

class Student {
  private final String navn;
  private int antOppg;

  public Student(String navn) {
    this.navn = navn;
  }

  public String getNavn() {
    return navn;
  }

  public int getAntOppg() {
    return antOppg;
  }

  public void setAntOppg (int antOppg) {
    if (antOppg < 0) {
      throw new IllegalArgumentException("Ugyldig antall oppgaver");
    }
    this.antOppg = antOppg;
  }

  public String toString() {
    return navn + " antall Oppgaver: " + antOppg;
  }
}
