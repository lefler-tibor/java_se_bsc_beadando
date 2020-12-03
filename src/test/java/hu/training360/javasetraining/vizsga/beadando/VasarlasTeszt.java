package hu.training360.javasetraining.vizsga.beadando;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

public class VasarlasTeszt {
    Termek termek1 = new Termek("t1","tv", 100_000);
    Termek termek2 = new Termek("t2","telefon", 800_000);

    Raktar raktar = new Raktar();

    Vevo vevo = new Vevo("Tibi", "Kecskemét", "+36301234567", "teszt@tesz.hu");

    @Test
    public void letrehoz()  {
        Vasarlas vasarlas = new Vasarlas();

        assertThat(vasarlas, is(notNullValue()));
    }

    @Test
    public void kosarbaTesz1()  {
        raktar.keszletBovit(termek1,10);
        raktar.keszletBovit(termek2, 20);

        Vasarlas vasarlas = new Vasarlas();

        vasarlas.kosarbaTesz(termek1, raktar);
        assertThat(vasarlas.getKosar().get(termek1), is(1));
        vasarlas.kosarbaTesz(termek2, raktar);
        assertThat(vasarlas.getKosar().get(termek2), is(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void kosarbaTesz2() {
        Vasarlas vasarlas = new Vasarlas();
        vasarlas.kosarbaTesz(null, raktar);
    }

    @Test
    public void kosarbaTesz3() {
        Vasarlas vasarlas = new Vasarlas();

        try {
            vasarlas.kosarbaTesz(termek1, raktar);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "A termék elfogyott!");
        }
    }

    @Test
    public void kosarbaTesz4() {
        raktar.keszletBovit(termek1, 10);
        raktar.keszletCsokkent(termek1, 10);

        Vasarlas vasarlas = new Vasarlas();

        try {
            vasarlas.kosarbaTesz(termek1, raktar);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "A termék elfogyott!");
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void kosarbaMennyisegNovel1() {
        raktar.keszletBovit(termek1, 10);

        Vasarlas vasarlas = new Vasarlas();
        vasarlas.kosarbaTesz(termek1, raktar);
        vasarlas.kosarTermekMennyisegNovel(termek1, 0, raktar);
    }

    @Test(expected = IllegalArgumentException.class)
    public void kosarbaMennyisegNovel2() {
        raktar.keszletBovit(termek1, 10);

        Vasarlas vasarlas = new Vasarlas();
        vasarlas.kosarbaTesz(termek1, raktar);
        vasarlas.kosarTermekMennyisegNovel(null, 1, raktar);
    }

    @Test(expected = RuntimeException.class)
    public void kosarbaMennyisegNovel3() {
        raktar.keszletBovit(termek1, 1);

        Vasarlas vasarlas = new Vasarlas();
        vasarlas.kosarbaTesz(termek1, raktar);
        vasarlas.kosarTermekMennyisegNovel(termek1, 2, raktar);
    }

    @Test
    public void kosarbaMennyisegNovel4() {
        raktar.keszletBovit(termek1, 10);

        Vasarlas vasarlas = new Vasarlas();
        vasarlas.kosarbaTesz(termek1, raktar);
        vasarlas.kosarTermekMennyisegNovel(termek1, 2, raktar);

        assertThat(vasarlas.getKosar().get(termek1), is(3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void kosarbolMennyisegCsokkent1() {
        raktar.keszletBovit(termek1, 10);

        Vasarlas vasarlas = new Vasarlas();
        vasarlas.kosarbaTesz(termek1, raktar);
        vasarlas.kosarTermekMennyisegNovel(termek1, 2, raktar);

        vasarlas.kosarTermekMennyisegCsokkent(null, 1, raktar);
    }

    @Test(expected = IllegalArgumentException.class)
    public void kosarbolMennyisegCsokkent2() {
        raktar.keszletBovit(termek1, 10);

        Vasarlas vasarlas = new Vasarlas();
        vasarlas.kosarbaTesz(termek1, raktar);
        vasarlas.kosarTermekMennyisegNovel(termek1, 2, raktar);

        vasarlas.kosarTermekMennyisegCsokkent(termek1, 0, raktar);
    }

    @Test
    public void kosarbolMennyisegCsokkent3() {
        raktar.keszletBovit(termek1, 10);

        Vasarlas vasarlas = new Vasarlas();
        vasarlas.kosarbaTesz(termek1, raktar);
        vasarlas.kosarTermekMennyisegNovel(termek1, 2, raktar);

        vasarlas.kosarTermekMennyisegCsokkent(termek1, 2, raktar);

        assertThat(vasarlas.getKosar().get(termek1), is(1));
    }

    @Test
    public void kosarbolMennyisegCsokkent4() {
        raktar.keszletBovit(termek1, 10);

        Vasarlas vasarlas = new Vasarlas();
        vasarlas.kosarbaTesz(termek1, raktar);
        vasarlas.kosarTermekMennyisegNovel(termek1, 2, raktar);

        vasarlas.kosarTermekMennyisegCsokkent(termek1, 4, raktar);

        assertThat(vasarlas.getKosar().get(termek1), is(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void kosarbolKivesz1() {
        raktar.keszletBovit(termek1, 10);

        Vasarlas vasarlas = new Vasarlas();
        vasarlas.kosarbaTesz(termek1, raktar);

        vasarlas.kosarbolKivesz(null, raktar);
    }

    @Test
    public void kosarbolKivesz2() {
        raktar.keszletBovit(termek1, 10);

        Vasarlas vasarlas = new Vasarlas();

        vasarlas.kosarbaTesz(termek1, raktar);
        vasarlas.kosarTermekMennyisegNovel(termek1, 2, raktar);

        assertThat(vasarlas.getKosar().get(termek1), is(3));
        assertThat(raktar.getKeszlet().get(termek1), is(7));

        vasarlas.kosarbolKivesz(termek1, raktar);
        assertFalse(vasarlas.getKosar().containsKey(termek1));
        assertThat(raktar.getKeszlet().get(termek1), is(10));
    }

    @Test
    public void rendelesLeadasa() {
        raktar.keszletBovit(termek1, 10);

        Vasarlas vasarlas = new Vasarlas();

        vasarlas.kosarbaTesz(termek1, raktar);
        vasarlas.kosarTermekMennyisegNovel(termek1, 2, raktar);

        Rendeles rendeles = vasarlas.rendelesLeadasa(vevo, Helyszin.ONLINE, FizetesiMod.KARTYA, KezbesitesiMod.FUTAR);

        assertThat(rendeles, is(notNullValue()));
        assertThat(rendeles.getValasztottTermekek().get(termek1), is(3));
        assertEquals(rendeles.getRendelesAllapot(), RendelesAllapot.BOOKED);
        assertEquals(rendeles.getKezbesitesiMod(), KezbesitesiMod.FUTAR);
        assertEquals(rendeles.getFizetesiMod(), FizetesiMod.KARTYA);
        assertEquals(rendeles.getHelyszin(), Helyszin.ONLINE);
        assertEquals(rendeles.getVevo().getNev(), "Tibi");
    }

    @Test(expected = IllegalArgumentException.class)
    public void futarnakAtadva1() {
        raktar.keszletBovit(termek1, 10);

        Vasarlas vasarlas = new Vasarlas();

        vasarlas.kosarbaTesz(termek1, raktar);

        vasarlas.futarnakAtadva(null);
    }

    @Test
    public void futarnakAtadva2() {
        raktar.keszletBovit(termek1, 10);

        Vasarlas vasarlas = new Vasarlas();

        vasarlas.kosarbaTesz(termek1, raktar);

        Rendeles rendeles = vasarlas.rendelesLeadasa(vevo, Helyszin.ONLINE, FizetesiMod.KARTYA, KezbesitesiMod.FUTAR);
        vasarlas.futarnakAtadva(rendeles);

        assertEquals(rendeles.getRendelesAllapot(), RendelesAllapot.IN_PROGRESS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void futarKiszallitVisszajelez1() {
        raktar.keszletBovit(termek1, 10);

        Vasarlas vasarlas = new Vasarlas();

        vasarlas.kosarbaTesz(termek1, raktar);

        Rendeles rendeles = vasarlas.rendelesLeadasa(vevo, Helyszin.ONLINE, FizetesiMod.KARTYA, KezbesitesiMod.FUTAR);
        vasarlas.futarKiszallitVisszajelez(rendeles, true, "Minden OK!");
    }

    @Test(expected = IllegalArgumentException.class)
    public void futarKiszallitVisszajelez2() {
        raktar.keszletBovit(termek1, 10);

        Vasarlas vasarlas = new Vasarlas();

        vasarlas.kosarbaTesz(termek1, raktar);

        Rendeles rendeles = vasarlas.rendelesLeadasa(vevo, Helyszin.ONLINE, FizetesiMod.KARTYA, KezbesitesiMod.FUTAR);
        vasarlas.futarnakAtadva(rendeles);
        vasarlas.futarKiszallitVisszajelez(rendeles, false, "");
    }

    @Test
    public void futarKiszallitVisszajelez3() {
        raktar.keszletBovit(termek1, 10);

        Vasarlas vasarlas = new Vasarlas();

        vasarlas.kosarbaTesz(termek1, raktar);

        Rendeles rendeles = vasarlas.rendelesLeadasa(vevo, Helyszin.ONLINE, FizetesiMod.KARTYA, KezbesitesiMod.FUTAR);
        vasarlas.futarnakAtadva(rendeles);

        vasarlas.futarKiszallitVisszajelez(rendeles, true, "");

        assertEquals(rendeles.getRendelesAllapot(), RendelesAllapot.DELIVERED);
    }

    @Test
    public void futarKiszallitVisszajelez4() {
        raktar.keszletBovit(termek1, 10);

        Vasarlas vasarlas = new Vasarlas();

        vasarlas.kosarbaTesz(termek1, raktar);

        Rendeles rendeles = vasarlas.rendelesLeadasa(vevo, Helyszin.ONLINE, FizetesiMod.KARTYA, KezbesitesiMod.FUTAR);
        vasarlas.futarnakAtadva(rendeles);

        vasarlas.futarKiszallitVisszajelez(rendeles, false, "Nincs ilyen cím!");

        assertEquals(rendeles.getRendelesAllapot(), RendelesAllapot.FAILED_DELIVERED);
        assertEquals(rendeles.getMegjegyzes(), "Nincs ilyen cím!");
    }
}
