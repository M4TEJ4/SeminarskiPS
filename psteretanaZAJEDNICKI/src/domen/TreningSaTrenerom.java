package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TreningSaTrenerom implements ApstraktniDomenskiObjekat {

    private Klijent klijent;  
    private String nivoPodrske;
    private String zdravstvenoStanje;

    public TreningSaTrenerom() {
    }

    public TreningSaTrenerom(Klijent klijent, String nivoPodrske, String zdravstvenoStanje) {
        this.klijent = klijent;
        this.nivoPodrske = nivoPodrske;
        this.zdravstvenoStanje = zdravstvenoStanje;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

    public String getNivoPodrske() {
        return nivoPodrske;
    }

    public void setNivoPodrske(String nivoPodrske) {
        this.nivoPodrske = nivoPodrske;
    }

    public String getZdravstvenoStanje() {
        return zdravstvenoStanje;
    }

    public void setZdravstvenoStanje(String zdravstvenoStanje) {
        this.zdravstvenoStanje = zdravstvenoStanje;
    }

    @Override
    public String toString() {
        return klijent + " (sa trenerom)";
    }

    @Override
    public String vratiNazivTabele() {
        return "treningsatrenerom";
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "idKlijent, nivoPodrske, zdravstvenoStanje";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return klijent.getIdKlijent() + ", '" + nivoPodrske + "', '" + zdravstvenoStanje + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "idKlijent=" + klijent.getIdKlijent();
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "nivoPodrske='" + nivoPodrske + "', zdravstvenoStanje='" + zdravstvenoStanje + "'";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            TreningSaTrenerom t = new TreningSaTrenerom();

            Klijent k = new Klijent();
            k.setIdKlijent(rs.getInt("idKlijent"));
            k.setIme(rs.getString("ime"));
            k.setPrezime(rs.getString("prezime"));
            t.setKlijent(k);

            t.setNivoPodrske(rs.getString("nivoPodrske"));
            t.setZdravstvenoStanje(rs.getString("zdravstvenoStanje"));

            lista.add(t);
        }
        return lista;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        TreningSaTrenerom t = new TreningSaTrenerom();

        Klijent k = new Klijent();
        k.setIdKlijent(rs.getInt("idKlijent"));
        k.setIme(rs.getString("ime"));
        k.setPrezime(rs.getString("prezime"));
        t.setKlijent(k);

        t.setNivoPodrske(rs.getString("nivoPodrske"));
        t.setZdravstvenoStanje(rs.getString("zdravstvenoStanje"));

        return t;
    }
}
