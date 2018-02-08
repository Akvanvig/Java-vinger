import java.util.Arrays;

class Spiller {
  private Mynt mynten;
  private int[] tippingen;

  Spiller(String myntenhet, double myntverdi, int[] tippingen) {
    this.mynten = new Mynt(myntenhet, myntverdi);
    this.tippingen = new int[tippingen.length];
    for (int i = 0; i < tippingen.length; i++) {
      this.tippingen[i] = tippingen[i];
    }
    Arrays.sort(this.tippingen);
  }

  public Mynt betal() {
    Mynt resultat = new Mynt(mynten.getValuta(), mynten.getVerdi());
    mynten.nullstill();
    return resultat;
  }

  public String toString() {
    String tekst = "";
    for (int i = 0; i < tippingen.length; i++) {
      tekst += tippingen[i] + " - ";
    }
    tekst += mynten.getValuta() + " " + mynten.getVerdi();
    return tekst;
  }

  public int[] getTippingen() {
    return tippingen;
  }
}
