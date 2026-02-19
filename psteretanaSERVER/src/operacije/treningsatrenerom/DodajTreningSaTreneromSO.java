package operacije.treningsatrenerom;

import domen.Klijent;
import domen.TreningSaTrenerom;
import operacije.ApstraktnaGenerickaOperacija;

public class DodajTreningSaTreneromSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof TreningSaTrenerom)) {
            throw new Exception("Sistem ne može da doda trening sa trenerom: neispravan objekat.");
        }

        TreningSaTrenerom t = (TreningSaTrenerom) param;

        if (t.getKlijent() == null) throw new Exception("Klijent je obavezan.");
        Klijent k = t.getKlijent();
        if (k.getIdKlijent() <= 0) throw new Exception("ID klijenta nije ispravan.");

        if (t.getNivoPodrske() == null || t.getNivoPodrske().trim().isEmpty()) {
            throw new Exception("Nivo podrške je obavezan.");
        }
        if (t.getZdravstvenoStanje() == null || t.getZdravstvenoStanje().trim().isEmpty()) {
            throw new Exception("Zdravstveno stanje je obavezno.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.add((TreningSaTrenerom) param);
    }
}
