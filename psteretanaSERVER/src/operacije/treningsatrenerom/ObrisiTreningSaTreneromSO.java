package operacije.treningsatrenerom;

import domen.Klijent;
import domen.TreningSaTrenerom;
import operacije.ApstraktnaGenerickaOperacija;

public class ObrisiTreningSaTreneromSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof TreningSaTrenerom)) {
            throw new Exception("Sistem ne može da obriše trening sa trenerom: neispravan objekat.");
        }

        TreningSaTrenerom t = (TreningSaTrenerom) param;

        if (t.getKlijent() == null) throw new Exception("Klijent je obavezan.");
        Klijent k = t.getKlijent();
        if (k.getIdKlijent() <= 0) throw new Exception("ID klijenta nije ispravan.");
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.delete((TreningSaTrenerom) param);
    }
}
