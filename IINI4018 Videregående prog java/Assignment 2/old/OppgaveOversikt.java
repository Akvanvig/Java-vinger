/*
	Øving 2
	27.02.2018
	Anders Kvanvig
*/

class OppgaveOversikt {
  private Student[] studenter = new Student[5];
  private int antStud = 0;

  public boolean regNyStudent(String navn) {
    boolean elevRegistrert = true;
    //Sjekker om eleven er registrert tidligere
    for (int i = 0; i < antStud; i++) {
      if (studenter[i].getNavn().equals(navn)) {
        elevRegistrert = false;
      }
    }
    //Legger til ekstra plass i tabellen om nødvendig
    if (studenter.length == antStud && elevRegistrert) {
      Student[] nyTab = new Student[studenter.length + 5];
      for (int i = 0; i < antStud; i++) {
        nyTab[i] = studenter[i];
      }
      studenter = nyTab;
    }
    //Legger til den nye studenten
    if (elevRegistrert) {
      studenter[antStud] = new Student(navn);
      antStud++;
    }
    return elevRegistrert;
  }

  public int finnAntStud() {
    return antStud;
  }

  public int finnAntOppgaver(String navn) {
    int antOppg = -1;
    for (Student elev:studenter) {
      if (elev.getNavn().equals(navn)) {
        antOppg = elev.getAntOppg();
        break;
      }
    }
    return antOppg;
  }

  public void okAntOppg(String navn, int antall) {
    for (Student elev:studenter) {
      if (elev.getNavn().equals(navn)) {
        elev.setAntOppg(elev.getAntOppg() + antall);
        break;
      }
    }
  }

  public String[] finnAlleNavn() {
    String[] navneliste = new String[antStud];
    for (int i = 0; i < antStud; i++) {
      navneliste[i] = studenter[i].getNavn();
    }
    return navneliste;
  }

  public String toString() {
    String resultat = "";
    for (int i=0; i<antStud;i++) {
      resultat += studenter[i].getNavn() + " - " + studenter[i].getAntOppg() + ",   ";
    }
    return resultat;
  }
}
