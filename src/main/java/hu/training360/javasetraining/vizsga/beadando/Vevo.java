package hu.training360.javasetraining.vizsga.beadando;

public class Vevo {
    private String nev;
    private String cim;
    private String telefonszam;
    private String email;

    public Vevo(String nev, String cim, String telefonszam, String email) {
        this.nev = nev;
        this.cim = cim;
        this.telefonszam = telefonszam;
        this.email = email;
    }

    public String getNev() {
        return nev;
    }

    public String getCim() {
        return cim;
    }

    public String getTelefonszam() {
        return telefonszam;
    }

    public String getEmail() {
        return email;
    }
}
