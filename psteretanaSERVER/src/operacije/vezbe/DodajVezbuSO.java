package operacije.vezbe;

import domen.Vezba;
import operacije.ApstraktnaGenerickaOperacija;

public class DodajVezbuSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Vezba)) {
            throw new Exception("Sistem ne može da doda vežbu: neispravan objekat.");
        }

        Vezba v = (Vezba) param;

        if (v.getNaziv() == null || v.getNaziv().trim().length() < 2) {
            throw new Exception("Naziv vežbe mora imati bar 2 karaktera.");
        }
        if (v.getGrupaMisica() == null || v.getGrupaMisica().trim().isEmpty()) {
            throw new Exception("Grupa mišića je obavezna.");
        }
        if (v.getOprema() == null || v.getOprema().trim().isEmpty()) {
            throw new Exception("Oprema je obavezna.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.add((Vezba) param);
    }
}
