package hu.training360.javasetraining.vizsga.beadando;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class VevoTeszt {
    @Test
    public void letrehoz()  {
        Vevo v1 = new Vevo("Teszt Elek", "6000 Kecskemét", "301234567", "teszt.elek@teszt.hu");

        assertThat(v1.getNev(), is("Teszt Elek"));
        assertThat(v1.getCim(), is("6000 Kecskemét"));
        assertThat(v1.getTelefonszam(), is("301234567"));
        assertThat(v1.getEmail(), is("teszt.elek@teszt.hu"));
    }
}
