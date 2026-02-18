package operacije.vezbe;

import domen.Vezba;
import operacije.ApstraktnaGenerickaOperacija;

public class ObrisiVezbuSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Vezba)) {
            throw new Exception("Sistem ne može da obriše vežbu: neispravan objekat.");
        }

        Vezba v = (Vezba) param;
        if (v.getIdVezba() <= 0) {
            throw new Exception("ID vežbe nije ispravan.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.delete((Vezba) param);
    }
}
