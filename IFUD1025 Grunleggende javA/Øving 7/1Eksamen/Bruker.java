class Bruker {
  private String brukeren;
  private Passordhjelper hjelperen;
  private boolean paLogget = false;

  Bruker(String navn, String passord) {
    this.brukeren = navn;
    //Ettersom allerede "hjelperen" er opprettet ovenfor måtte det lages en funksjon for å sette inn passordet i etterkant
    //this.hjelperen.setPassord(passord);
    this.hjelperen = new Passordhjelper(passord);
  }

  public void loggIn(String navn, String passord) {
    if (brukeren.equals(navn) && hjelperen.isPassord(passord)) {
      paLogget = true;
    }
  }

  public void loggUt() {
    paLogget = false;
  }

  public String toString() {
    return "Brukernavn: " + brukeren + " passord: " + hjelperen.toString() + " påloggingsstatus: " + paLogget;
  }

  public int finnPassordStyrken() {
    return finnPassordStyrken();
  }
}
