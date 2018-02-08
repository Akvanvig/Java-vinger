class Drikk {
  private String navn;
  private double alkoholprosent;

  Drikk(String navn, double alkoholprosent) {
    this.navn = navn;
    this.alkoholprosent = alkoholprosent;
  }

  public String getNavn() {
    return navn;
  }

  public double getAlkoholprosent() {
    return alkoholprosent;
  }

  public double finnGramAlkohol(int mlDrikk) {
    return ((alkoholprosent / 100) * 0.79 * mlDrikk);
  }

  public double finnAlkoholkonsentrasjonIBlod(int mlDrikk, double kroppsvekt, boolean mann) {
    if (mann) {
      return (finnGramAlkohol(mlDrikk) / (kroppsvekt * 0.68));
    }
    else {
      return (finnGramAlkohol(mlDrikk) / (kroppsvekt * 0.55));
    }

  }
}
