package operacije.treningsatrenerom;

import domen.TreningSaTrenerom;
import domen.ApstraktniDomenskiObjekat;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

public class UcitajTreningeSaTreneromSO extends ApstraktnaGenerickaOperacija {

    private List<TreningSaTrenerom> treninzi;

    @Override
    protected void preduslovi(Object param) throws Exception {
        // nema posebnih preduslova
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        List<ApstraktniDomenskiObjekat> lista =
                broker.getAll(new TreningSaTrenerom(),
                        " JOIN klijent ON (treningsatrenerom.idKlijent = klijent.idKlijent)");

        treninzi = (List<TreningSaTrenerom>) (List<?>) lista;
    }

    public List<TreningSaTrenerom> getTreninzi() {
        return treninzi;
    }
}
