package operacije.klijenti;

import domen.Klijent;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

public class UcitajKlijenteSO extends ApstraktnaGenerickaOperacija {

    private List<Klijent> klijenti;

    @Override
    protected void preduslovi(Object param) throws Exception {
        // nema posebnih preduslova
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        klijenti = broker.getAll(new Klijent(), "");
    }

    public List<Klijent> getKlijenti() {
        return klijenti;
    }
}
