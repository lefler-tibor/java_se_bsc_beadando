package hu.training360.javasetraining.vizsga.beadando;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

public class Raktar {
    private Map<Termek, Integer> keszlet;

    public Raktar() {

        this.keszlet = new HashMap<Termek, Integer>();
    }

    public Map<Termek, Integer> getKeszlet() {
        return keszlet;
    }

    public void keszletBovit(Termek termek, int mennyiseg) {
        if (termek != null && mennyiseg > 0) {
            if (!this.keszlet.containsKey(termek)) {
                this.keszlet.put(termek, mennyiseg);
            } else {
                this.keszlet.put(termek, this.keszlet.get(termek) + mennyiseg);
            }
        } else {
            throw new IllegalArgumentException("A készletezendő termék paraméter üres vagy a mennyiség nem megfelelő!");
        }
    }

    public void keszletCsokkent(Termek termek, int mennyiseg) {
        if (termek != null && mennyiseg > 0) {
            if (this.keszlet.containsKey(termek)) {
                if (this.keszlet.get(termek) < mennyiseg) {
                    throw new IllegalArgumentException("A raktáron lévő készlet kevesebb, mint a kiadandó mennyiség!");
                } else {
                    this.keszlet.put(termek, this.keszlet.get(termek) - mennyiseg);
                }
            }
        } else {
            throw new IllegalArgumentException("A termék paraméter üres vagy a mennyiség nem megfelelő!");
        }
    }
}
