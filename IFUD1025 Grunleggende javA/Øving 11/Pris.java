class Pris {
  private String valutanavn;
  private double prisIValuta;

  Pris(String valutanavn, double prisIValuta) {
    this.valutanavn = valutanavn;
    this.prisIValuta = prisIValuta;
  }

  public int finnAntallTippetall(double belop) {
    double antallTall = belop / prisIValuta;
    return (int) antallTall; //Dette vil fjerne desimalpunktene, dvs. (int) 4.98 --> 4
    //Om en ønsker å returnere tallet som double kan Math.floor() funksjonen brukes
  }

  public String getValutaNavn() {
    return valutanavn;
  }

  public double getVerdi() {
    return prisIValuta;
  }
}
