package hu.training360.javasetraining.vizsga.beadando;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class RendelesTeszt {
    @Test
    public void letrehoz()  {
        Termek termek1 = new Termek("t1","tv", 100_000);
        Termek termek2 = new Termek("t2","telefon", 800_000);

        Raktar raktar = new Raktar();
        raktar.keszletBovit(termek1,10);
        raktar.keszletBovit(termek2, 20);

        Vevo vevo = new Vevo("Tibi", "Kecskemét", "+36301234567", "teszt@tesz.hu");

        Vasarlas vasarlas = new Vasarlas();
        vasarlas.kosarbaTesz(termek1, raktar);
        vasarlas.kosarTermekMennyisegNovel(termek1, 2, raktar);
        vasarlas.kosarbaTesz(termek2, raktar);

        Rendeles rendeles = new Rendeles(KezbesitesiMod.FUTAR, FizetesiMod.KARTYA, Helyszin.ONLINE, vevo, vasarlas.getKosar());

        assertEquals(rendeles.getRendelesAllapot(), RendelesAllapot.BOOKED);
        assertThat(rendeles.getValasztottTermekek().get(termek1), is(3));
        assertThat(rendeles.getValasztottTermekek().get(termek2), is(1));
        assertEquals(rendeles.getKezbesitesiMod(), KezbesitesiMod.FUTAR);
        assertEquals(rendeles.getFizetesiMod(), FizetesiMod.KARTYA);
        assertEquals(rendeles.getHelyszin(), Helyszin.ONLINE);
    }
}
