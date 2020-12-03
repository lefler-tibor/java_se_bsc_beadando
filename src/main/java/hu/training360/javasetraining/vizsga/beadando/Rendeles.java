package hu.training360.javasetraining.vizsga.beadando;

import java.util.Map;

public class Rendeles {
    private KezbesitesiMod kezbesitesiMod;
    private FizetesiMod fizetesiMod;
    private RendelesAllapot rendelesAllapot;
    private Helyszin helyszin;
    private Vevo vevo;
    private Map<Termek, Integer> valasztottTermekek;
    private String megjegyzes;

    public Rendeles(KezbesitesiMod kezbesitesiMod, FizetesiMod fizetesiMod, Helyszin helyszin, Vevo vevo, Map<Termek, Integer> valasztottTermekek) {
        this.kezbesitesiMod = kezbesitesiMod;
        this.fizetesiMod = fizetesiMod;
        this.helyszin = helyszin;
        this.vevo = vevo;
        this.valasztottTermekek = valasztottTermekek;

        if (Helyszin.ONLINE.equals(helyszin)) {
            this.rendelesAllapot = RendelesAllapot.BOOKED;
        } else {
            this.rendelesAllapot = RendelesAllapot.DELIVERED;
        }
    }

    public KezbesitesiMod getKezbesitesiMod() {
        return kezbesitesiMod;
    }

    public FizetesiMod getFizetesiMod() {
        return fizetesiMod;
    }

    public RendelesAllapot getRendelesAllapot() {
        return rendelesAllapot;
    }

    public Helyszin getHelyszin() {
        return helyszin;
    }

    public Vevo getVevo() {
        return vevo;
    }

    public Map<Termek, Integer> getValasztottTermekek() {
        return valasztottTermekek;
    }

    public String getMegjegyzes() {
        return megjegyzes;
    }

    public void setRendelesAllapot(RendelesAllapot rendelesAllapot) {
        this.rendelesAllapot = rendelesAllapot;
    }

    public void setMegjegyzes(String megjegyzes) {
        this.megjegyzes = megjegyzes;
    }
}
