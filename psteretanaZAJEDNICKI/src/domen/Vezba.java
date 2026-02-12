package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Vezba implements ApstraktniDomenskiObjekat {

    private int idVezba;
    private String naziv;
    private String grupaMisica;
    private String oprema;

    public Vezba() {
    }

    public Vezba(int idVezba, String naziv, String grupaMisica, String oprema) {
        this.idVezba = idVezba;
        this.naziv = naziv;
        this.grupaMisica = grupaMisica;
        this.oprema = oprema;
    }

    public int getIdVezba() {
        return idVezba;
    }

    public void setIdVezba(int idVezba) {
        this.idVezba = idVezba;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getGrupaMisica() {
        return grupaMisica;
    }

    public void setGrupaMisica(String grupaMisica) {
        this.grupaMisica = grupaMisica;
    }

    public String getOprema() {
        return oprema;
    }

    public void setOprema(String oprema) {
        this.oprema = oprema;
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public String vratiNazivTabele() {
        return "vezba";
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naziv, grupaMisica, oprema";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + naziv + "', '" + grupaMisica + "', '" + oprema + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "idVezba=" + idVezba;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "naziv='" + naziv + "', grupaMisica='" + grupaMisica + "', oprema='" + oprema + "'";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            Vezba v = new Vezba();
            v.setIdVezba(rs.getInt("idVezba"));
            v.setNaziv(rs.getString("naziv"));
            v.setGrupaMisica(rs.getString("grupaMisica"));
            v.setOprema(rs.getString("oprema"));
            lista.add(v);
        }
        return lista;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        Vezba v = new Vezba();
        v.setIdVezba(rs.getInt("idVezba"));
        v.setNaziv(rs.getString("naziv"));
        v.setGrupaMisica(rs.getString("grupaMisica"));
        v.setOprema(rs.getString("oprema"));
        return v;
    }
}
