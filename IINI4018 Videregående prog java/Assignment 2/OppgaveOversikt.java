/*
	Øving 2
	27.02.2018
	Anders Kvanvig
*/
import java.util.*;

class OppgaveOversikt {
  private ArrayList<Student> studenter = new ArrayList<Student>();

  public boolean regNyStudent(String navn) {
    boolean elevRegistrert = true;
    //Sjekker om eleven er registrert tidligere
    for (Student elev:studenter) {
      if (elev.getNavn().equals(navn)) {
        elevRegistrert = false;
      }
    }
    //Legger til den nye studenten
    if (elevRegistrert) {
      studenter.add(new Student(navn));
    }
    return elevRegistrert;
  }

  public int finnAntStud() {
    return studenter.size();
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
    ArrayList<String> navneliste = new ArrayList<String>();
    for (Student elev:studenter) {
      navneliste.add(elev.getNavn());
    }
    return navneliste.toArray(new String[navneliste.size()]);
  }

  public String toString() {
    String resultat = "";
    for (Student elev:studenter) {
      resultat += elev.getNavn() + " - " + elev.getAntOppg() + ",   ";
    }
    return resultat;
  }
}
