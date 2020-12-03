package hu.training360.javasetraining.vizsga.beadando;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class RaktarTeszt {
    @Test
    public void letrehoz()  {
        Raktar r = new Raktar();

        assertThat(r, is(notNullValue()));
    }

    @Test(expected=IllegalArgumentException.class)
    public void keszletBovitTeszt1() {
        Termek termek1 = new Termek("t1","tv", 300_000);

        Raktar r = new Raktar();
        r.keszletBovit(termek1, 0);
    }

    @Test(expected=IllegalArgumentException.class)
    public void keszletBovitTeszt2() {
        Raktar r = new Raktar();
        r.keszletBovit(null, 10);
    }

    @Test
    public void keszletBovitTeszt3() {
        Termek termek1 = new Termek("t1","tv", 300_000);
        Termek termek2 = new Termek("t2","telefon", 120_000);

        Raktar r = new Raktar();
        r.keszletBovit(termek1, 10);
        r.keszletBovit(termek2, 100);

        assertTrue(r.getKeszlet().containsKey(termek1));
        assertThat(r.getKeszlet().get(termek1), is(10));
        assertTrue(r.getKeszlet().containsKey(termek2));
        assertThat(r.getKeszlet().get(termek2), is(100));

        r.keszletBovit(termek2, 100);

        assertThat(r.getKeszlet().get(termek2), is(200));
    }

    @Test(expected=IllegalArgumentException.class)
    public void keszletCsokkentTeszt1() {
        Termek termek1 = new Termek("t1","tv", 300_000);

        Raktar r = new Raktar();
        r.keszletCsokkent(termek1, 0);
    }

    @Test(expected=IllegalArgumentException.class)
    public void keszletCsokkentTeszt2() {
        Raktar r = new Raktar();
        r.keszletCsokkent(null, 10);
    }

    @Test
    public void keszletCsokkentTeszt3() {
        Termek termek1 = new Termek("t1","tv", 300_000);
        Termek termek2 = new Termek("t2","telefon", 120_000);

        Raktar r = new Raktar();
        r.keszletBovit(termek1, 10);
        r.keszletBovit(termek2, 200);

        r.keszletCsokkent(termek2, 20);
        assertThat(r.getKeszlet().get(termek2), is(180));
    }

    @Test
    public void keszletCsokkentTeszt4() {
        Termek termek1 = new Termek("t1","tv", 300_000);

        Raktar r = new Raktar();
        r.keszletBovit(termek1, 10);

        try {
            r.keszletCsokkent(termek1, 20);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "A raktáron lévő készlet kevesebb, mint a kiadandó mennyiség!");
        }
    }
}
