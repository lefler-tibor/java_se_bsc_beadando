package hu.training360.javasetraining.vizsga.beadando;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TermekTeszt {
    @Test
    public void letrehoz()  {
        Termek t1 = new Termek("t1","tv", 100_000);

        assertThat(t1.getCikkszam(), is("t1"));
        assertThat(t1.getNev(), is("tv"));
        assertThat(t1.getAr(), is(100000));
    }
}
