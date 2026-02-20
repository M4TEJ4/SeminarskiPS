package operacije.treneri;

import domen.Trener;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

public class UcitajTrenereSO extends ApstraktnaGenerickaOperacija {

    private List<Trener> treneri;

    @Override
    protected void preduslovi(Object param) throws Exception {
        // nema posebnih preduslova
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        treneri = broker.getAll(new Trener(), "");
    }

    public List<Trener> getTreneri() {
        return treneri;
    }
}