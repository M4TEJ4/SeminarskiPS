package operacije.treneri;

import domen.Trener;
import operacije.ApstraktnaGenerickaOperacija;

public class ObrisiTreneraSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Trener)) {
            throw new Exception("Sistem nije mogao da obriše trenera: neispravan objekat.");
        }

        Trener t = (Trener) param;
        if (t.getIdTrener() <= 0) {
            throw new Exception("ID trenera nije ispravan.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.delete((Trener) param);
    }
}