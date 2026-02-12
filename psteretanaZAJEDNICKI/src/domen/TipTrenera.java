package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TipTrenera implements ApstraktniDomenskiObjekat {

    private int idTipTrenera;
    private String naziv;
    private String opis;

    public TipTrenera() {
    }

    public TipTrenera(int idTipTrenera, String naziv, String opis) {
        this.idTipTrenera = idTipTrenera;
        this.naziv = naziv;
        this.opis = opis;
    }

    public int getIdTipTrenera() {
        return idTipTrenera;
    }

    public void setIdTipTrenera(int idTipTrenera) {
        this.idTipTrenera = idTipTrenera;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public String vratiNazivTabele() {
        return "tiptrenera";
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naziv, opis";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + naziv + "', '" + opis + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "idTipTrenera=" + idTipTrenera;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "naziv='" + naziv + "', opis='" + opis + "'";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            TipTrenera tt = new TipTrenera();
            tt.setIdTipTrenera(rs.getInt("idTipTrenera"));
            tt.setNaziv(rs.getString("naziv"));
            tt.setOpis(rs.getString("opis"));
            lista.add(tt);
        }
        return lista;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        TipTrenera tt = new TipTrenera();
        tt.setIdTipTrenera(rs.getInt("idTipTrenera"));
        tt.setNaziv(rs.getString("naziv"));
        tt.setOpis(rs.getString("opis"));
        return tt;
    }
}
