package operacije.klijenti;

import domen.Klijent;
import operacije.ApstraktnaGenerickaOperacija;

public class AzurirajKlijentaSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Klijent)) {
            throw new Exception("Sistem ne može da ažurira klijenta: neispravan objekat.");
        }

        Klijent k = (Klijent) param;

        if (k.getIdKlijent() <= 0) {
            throw new Exception("ID klijenta nije ispravan.");
        }
        if (k.getIme() == null || k.getIme().trim().length() < 2) {
            throw new Exception("Ime mora imati bar 2 slova.");
        }
        if (k.getPrezime() == null || k.getPrezime().trim().length() < 2) {
            throw new Exception("Prezime mora imati bar 2 slova.");
        }
        if (k.getDatumRodjenja() == null) {
            throw new Exception("Datum rođenja je obavezan.");
        }
        if (k.getBrojGodina() <= 0) {
            throw new Exception("Broj godina mora biti veći od 0.");
        }
        if (k.getPol() == null) {
            throw new Exception("Pol je obavezan.");
        }
        if (k.getVisina() <= 0) {
            throw new Exception("Visina mora biti veća od 0.");
        }
        if (k.getTezina() <= 0) {
            throw new Exception("Težina mora biti veća od 0.");
        }
        if (k.getBMR() <= 0) {
            throw new Exception("BMR mora biti veći od 0.");
        }
        if (k.getBrojTelefona() == null || k.getBrojTelefona().trim().isEmpty()) {
            throw new Exception("Broj telefona je obavezan.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.edit((Klijent) param);
    }
}
