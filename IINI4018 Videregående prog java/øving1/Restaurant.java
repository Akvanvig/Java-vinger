import java.util.*;

class Restaurant {
  private String navn;
  private int etbAar;
  private Bord bordene;

  Restaurant(String navn, int etbAar, int antBord) {
    this.navn = navn;
    this.etbAar = etbAar;
    bordene = new Bord(antBord);
  }

  public String getNavn() {
    return this.navn;
  }

  public void setNavn(String navn) {
    this.navn = navn;
  }

  public int getEtbAar() {
    return this.etbAar;
  }

  public int getAlder() {
    int year = Calendar.getInstance().get(Calendar.YEAR);
    return year - etbAar;
  }

  public int getLedige() {
    return bordene.antLedige();
  }

  public int getOpptatte() {
    return bordene.antOpptatte();
  }

  public void frigiBord(int[] bordnummer) {
    bordene.frigiBord(bordnummer);
  }
}
