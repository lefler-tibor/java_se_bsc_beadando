package hu.training360.javasetraining.vizsga.beadando;

import java.util.HashMap;
import java.util.Map;

public class Vasarlas {
    private Map<Termek, Integer> kosar;

    public Vasarlas() {
        this.kosar = new HashMap<Termek, Integer>();
    }

    public Map<Termek, Integer> getKosar() {
        return kosar;
    }

    public void kosarbaTesz(Termek termek, Raktar raktar) {
        if (termek == null) {
            throw new IllegalArgumentException("Nincs kiválasztott termék!");
        }
        if (!raktar.getKeszlet().containsKey(termek) || raktar.getKeszlet().get(termek) == 0) {
            throw new IllegalArgumentException("A termék elfogyott!");
        }
        raktar.keszletCsokkent(termek, 1);
        this.kosar.put(termek, 1);
    }

    public void kosarTermekMennyisegNovel(Termek termek, int db, Raktar raktar) {
        if (termek == null || db < 1) {
            throw new IllegalArgumentException("Nincs termék átadva vagy a mennyiség nem megfelelő!");
        }
        if (raktar.getKeszlet().get(termek) == 0) {
            throw new RuntimeException("A termékből nincs több! A művelet nem végrehajtható!");
        }
        if (this.kosar.containsKey(termek)) {
            raktar.keszletCsokkent(termek, db);
            this.kosar.put(termek, this.kosar.get(termek) + db);
        }
    }

    public void kosarTermekMennyisegCsokkent(Termek termek, int db, Raktar raktar) {
        if (termek == null || db < 1) {
            throw new IllegalArgumentException("Nincs termék vagy a mennyiség nem megfelelő!");
        }
        if (this.kosar.containsKey(termek)) {
            if (this.kosar.get(termek) < db) {
                raktar.keszletBovit(termek, this.kosar.get(termek));
                this.kosar.put(termek, 0);
            } else {
                raktar.keszletBovit(termek, db);
                this.kosar.put(termek, this.kosar.get(termek) - db);
            }
        }
    }

    public void kosarbolKivesz(Termek termek, Raktar raktar) {
        if (termek == null) {
            throw new IllegalArgumentException("A termék paraméter üres vagy a mennyiség nem megfelelő!");
        }
        if (this.kosar.containsKey(termek)) {
            raktar.keszletBovit(termek, this.kosar.get(termek));
            this.kosar.remove(termek);
        }
    }

    public Rendeles rendelesLeadasa(Vevo vevo, Helyszin helyszin, FizetesiMod fizetesiMod, KezbesitesiMod kezbesitesiMod) {
        Rendeles rendeles = new Rendeles(kezbesitesiMod, fizetesiMod, helyszin, vevo, kosar);
        return rendeles;
    }

    public void futarnakAtadva(Rendeles rendeles) {
        if (rendeles == null) {
            throw new IllegalArgumentException("Nincs átveendő rendelés!");
        }
        rendeles.setRendelesAllapot(RendelesAllapot.IN_PROGRESS);
    }

    public void futarKiszallitVisszajelez(Rendeles rendeles, boolean sikeresKiszallitas, String megjegyzes) {
        if (!RendelesAllapot.IN_PROGRESS.equals(rendeles.getRendelesAllapot())) {
            throw new IllegalArgumentException("Ez a rendelés még nincs a futárszolgálatnál leadva!");
        }

        if (sikeresKiszallitas) {
            rendeles.setRendelesAllapot(RendelesAllapot.DELIVERED);

        } else if (megjegyzes == null || megjegyzes.isBlank()) {
            throw new IllegalArgumentException("Sikertelen kiszállítás esetén szükséges megadni a sikertelenség okát!");

        } else {
            rendeles.setRendelesAllapot(RendelesAllapot.FAILED_DELIVERED);
            rendeles.setMegjegyzes(megjegyzes);
        }
    }
}
