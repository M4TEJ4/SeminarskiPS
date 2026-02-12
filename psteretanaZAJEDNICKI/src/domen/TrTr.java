package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TrTr implements ApstraktniDomenskiObjekat {

    private Trener trener;
    private TipTrenera tipTrenera;

    private Date datumSticanja;
    private String nivoStrucnosti;

    public TrTr() {
    }

    public TrTr(Trener trener, TipTrenera tipTrenera, Date datumSticanja, String nivoStrucnosti) {
        this.trener = trener;
        this.tipTrenera = tipTrenera;
        this.datumSticanja = datumSticanja;
        this.nivoStrucnosti = nivoStrucnosti;
    }

    public Trener getTrener() {
        return trener;
    }

    public void setTrener(Trener trener) {
        this.trener = trener;
    }

    public TipTrenera getTipTrenera() {
        return tipTrenera;
    }

    public void setTipTrenera(TipTrenera tipTrenera) {
        this.tipTrenera = tipTrenera;
    }

    public Date getDatumSticanja() {
        return datumSticanja;
    }

    public void setDatumSticanja(Date datumSticanja) {
        this.datumSticanja = datumSticanja;
    }

    public String getNivoStrucnosti() {
        return nivoStrucnosti;
    }

    public void setNivoStrucnosti(String nivoStrucnosti) {
        this.nivoStrucnosti = nivoStrucnosti;
    }

    @Override
    public String toString() {
        return trener + " - " + tipTrenera + " (" + nivoStrucnosti + ")";
    }

    @Override
    public String vratiNazivTabele() {
        return "trtr";
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "idTrener, idTipTrenera, datumSticanja, nivoStrucnosti";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return trener.getIdTrener() + ", " + tipTrenera.getIdTipTrenera() + ", '" +
                new java.sql.Date(datumSticanja.getTime()) + "', '" + nivoStrucnosti + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "idTrener=" + trener.getIdTrener() + " AND idTipTrenera=" + tipTrenera.getIdTipTrenera();
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "datumSticanja='" + new java.sql.Date(datumSticanja.getTime()) + "', nivoStrucnosti='" + nivoStrucnosti + "'";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            TrTr x = new TrTr();

            Trener t = new Trener();
            t.setIdTrener(rs.getInt("idTrener"));
            t.setIme(rs.getString("trenerIme"));
            t.setPrezime(rs.getString("trenerPrezime"));
            x.setTrener(t);

            TipTrenera tt = new TipTrenera();
            tt.setIdTipTrenera(rs.getInt("idTipTrenera"));
            tt.setNaziv(rs.getString("tipNaziv"));
            tt.setOpis(rs.getString("tipOpis"));
            x.setTipTrenera(tt);

            x.setDatumSticanja(rs.getDate("datumSticanja"));
            x.setNivoStrucnosti(rs.getString("nivoStrucnosti"));

            lista.add(x);
        }
        return lista;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        TrTr x = new TrTr();

        Trener t = new Trener();
        t.setIdTrener(rs.getInt("idTrener"));
        t.setIme(rs.getString("trenerIme"));
        t.setPrezime(rs.getString("trenerPrezime"));
        x.setTrener(t);

        TipTrenera tt = new TipTrenera();
        tt.setIdTipTrenera(rs.getInt("idTipTrenera"));
        tt.setNaziv(rs.getString("tipNaziv"));
        tt.setOpis(rs.getString("tipOpis"));
        x.setTipTrenera(tt);

        x.setDatumSticanja(rs.getDate("datumSticanja"));
        x.setNivoStrucnosti(rs.getString("nivoStrucnosti"));

        return x;
    }
}
