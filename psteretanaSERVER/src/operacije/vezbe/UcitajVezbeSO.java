package operacije.vezbe;

import domen.Vezba;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

public class UcitajVezbeSO extends ApstraktnaGenerickaOperacija {

    private List<Vezba> vezbe;

    @Override
    protected void preduslovi(Object param) throws Exception {
        // nema
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        vezbe = broker.getAll(new Vezba(), "");
    }

    public List<Vezba> getVezbe() {
        return vezbe;
    }
}
