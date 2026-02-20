package operacije.treneri;

import domen.Trener;
import operacije.ApstraktnaGenerickaOperacija;

public class AzurirajTreneraSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Trener)) {
            throw new Exception("Sistem ne može da ažurira trenera: neispravan objekat.");
        }

        Trener t = (Trener) param;

        if (t.getIdTrener() <= 0) {
            throw new Exception("ID trenera nije ispravan.");
        }
        if (t.getIme() == null || t.getIme().trim().length() < 2) {
            throw new Exception("Ime mora imati bar 2 slova.");
        }
        if (t.getPrezime() == null || t.getPrezime().trim().length() < 2) {
            throw new Exception("Prezime mora imati bar 2 slova.");
        }
        if (t.getKorisnickoIme() == null || t.getKorisnickoIme().trim().length() < 3) {
            throw new Exception("Korisničko ime mora imati bar 3 karaktera.");
        }
        if (t.getLozinka() == null || t.getLozinka().trim().length() < 4) {
            throw new Exception("Lozinka mora imati bar 4 karaktera.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.edit((Trener) param);
    }
}