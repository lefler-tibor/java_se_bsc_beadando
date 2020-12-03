package hu.training360.javasetraining.vizsga.beadando;

public class Termek {
    private String cikkszam;
    private String nev;
    private int ar;

    public Termek(String cikkszam, String nev, int ar) {
        this.cikkszam = cikkszam;
        this.nev = nev;
        this.ar = ar;
    }

    public String getCikkszam() {
        return cikkszam;
    }

    public String getNev() {
        return nev;
    }

    public int getAr() {
        return ar;
    }
}
