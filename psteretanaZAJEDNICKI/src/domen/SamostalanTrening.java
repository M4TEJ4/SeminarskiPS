package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SamostalanTrening implements ApstraktniDomenskiObjekat {

    private Klijent klijent;
    private boolean teretana;
    private boolean kardio;

    public SamostalanTrening() {
    }

    public SamostalanTrening(Klijent klijent, boolean teretana, boolean kardio) {
        this.klijent = klijent;
        this.teretana = teretana;
        this.kardio = kardio;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

    public boolean isTeretana() {
        return teretana;
    }

    public void setTeretana(boolean teretana) {
        this.teretana = teretana;
    }

    public boolean isKardio() {
        return kardio;
    }

    public void setKardio(boolean kardio) {
        this.kardio = kardio;
    }

    @Override
    public String toString() {
        return klijent + " (samostalan)";
    }

    @Override
    public String vratiNazivTabele() {
        return "samostalantrening";
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "idKlijent, teretana, kardio";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return klijent.getIdKlijent() + ", " + (teretana ? 1 : 0) + ", " + (kardio ? 1 : 0);
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "idKlijent=" + klijent.getIdKlijent();
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "teretana=" + (teretana ? 1 : 0) + ", kardio=" + (kardio ? 1 : 0);
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            SamostalanTrening s = new SamostalanTrening();

            Klijent k = new Klijent();
            k.setIdKlijent(rs.getInt("idKlijent"));
            k.setIme(rs.getString("ime"));
            k.setPrezime(rs.getString("prezime"));
            s.setKlijent(k);

            s.setTeretana(rs.getInt("teretana") == 1);
            s.setKardio(rs.getInt("kardio") == 1);

            lista.add(s);
        }
        return lista;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        SamostalanTrening s = new SamostalanTrening();

        Klijent k = new Klijent();
        k.setIdKlijent(rs.getInt("idKlijent"));
        k.setIme(rs.getString("ime"));
        k.setPrezime(rs.getString("prezime"));
        s.setKlijent(k);

        s.setTeretana(rs.getInt("teretana") == 1);
        s.setKardio(rs.getInt("kardio") == 1);

        return s;
    }
}
