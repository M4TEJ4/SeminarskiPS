package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Trener implements ApstraktniDomenskiObjekat {

    private int idTrener;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String lozinka;

    public Trener() {
    }

    public Trener(int idTrener, String ime, String prezime, String korisnickoIme, String lozinka) {
        this.idTrener = idTrener;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
    }

    public int getIdTrener() {
        return idTrener;
    }

    public void setIdTrener(int idTrener) {
        this.idTrener = idTrener;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public String vratiNazivTabele() {
        return "trener";
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime, prezime, korisnickoIme, lozinka";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + ime + "', '" + prezime + "', '" + korisnickoIme + "', '" + lozinka + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "idTrener=" + idTrener;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "ime='" + ime + "', prezime='" + prezime + "', korisnickoIme='" + korisnickoIme + "', lozinka='" + lozinka + "'";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            Trener t = new Trener();
            t.setIdTrener(rs.getInt("idTrener"));
            t.setIme(rs.getString("ime"));
            t.setPrezime(rs.getString("prezime"));
            t.setKorisnickoIme(rs.getString("korisnickoIme"));
            t.setLozinka(rs.getString("lozinka"));
            lista.add(t);
        }
        return lista;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        Trener t = new Trener();
        t.setIdTrener(rs.getInt("idTrener"));
        t.setIme(rs.getString("ime"));
        t.setPrezime(rs.getString("prezime"));
        t.setKorisnickoIme(rs.getString("korisnickoIme"));
        t.setLozinka(rs.getString("lozinka"));
        return t;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Trener other = (Trener) obj;
        if (!Objects.equals(this.korisnickoIme, other.korisnickoIme)) {
            return false;
        }
        return Objects.equals(this.lozinka, other.lozinka);
    }
    
    
    
    
    
    
}
