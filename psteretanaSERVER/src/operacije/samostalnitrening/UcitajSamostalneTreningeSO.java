package operacije.samostalnitrening;

import domen.SamostalanTrening;
import domen.ApstraktniDomenskiObjekat;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

public class UcitajSamostalneTreningeSO extends ApstraktnaGenerickaOperacija {

    private List<SamostalanTrening> treninzi;

    @Override
    protected void preduslovi(Object param) throws Exception {
        // nema posebnih preduslova
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        // Očekujemo da broker.getAll pravi: SELECT * FROM <tabela> <dodatak>
        // pa ovde dodajemo join da dobijemo ime/prezime
        List<ApstraktniDomenskiObjekat> lista =
                broker.getAll(new SamostalanTrening(),
                        " JOIN klijent ON (samostalantrening.idKlijent = klijent.idKlijent)");

        treninzi = (List<SamostalanTrening>) (List<?>) lista;
    }

    public List<SamostalanTrening> getTreninzi() {
        return treninzi;
    }
}
