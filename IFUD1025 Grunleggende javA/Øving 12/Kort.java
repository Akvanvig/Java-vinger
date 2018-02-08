class Kort implements Comparable<Kort> {
  private String farge;
  private int valoer;

  Kort(String type, int verdi) {
    farge = type;
    valoer = verdi;
  }

  public boolean equals(Kort annetKort) {
    return valoer == annetKort.getValoer();
  }

  public int compareTo(Kort annetKort) {
    return (valoer - annetKort.getValoer());
  }

  public String toString() {
    return farge + " " + valoer;
  }

  public int getValoer() {
    return valoer;
  }
  public String getFarge() {
    return farge;
  }
}
