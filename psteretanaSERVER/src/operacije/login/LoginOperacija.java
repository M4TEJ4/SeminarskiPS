package operacije.login;

import domen.Trener;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

public class LoginOperacija extends ApstraktnaGenerickaOperacija {

    private Trener trener;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Trener)) {
            throw new Exception("Ne može da se uloguje.");
        }
        Trener t = (Trener) param;
        if (t.getKorisnickoIme() == null || t.getKorisnickoIme().trim().isEmpty()
                || t.getLozinka() == null || t.getLozinka().isEmpty()) {
            throw new Exception("Korisničko ime i lozinka su obavezni.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        List<Trener> sviTreneri = broker.getAll((Trener) param, "");

        if (sviTreneri.contains((Trener) param)) {
            for (Trener t : sviTreneri) {
                if (t.equals((Trener) param)) {
                    trener = t;
                    return;
                }
            }
        }
        trener = null;
    }

    public Trener getTrener() {
        return trener;
    }
}
