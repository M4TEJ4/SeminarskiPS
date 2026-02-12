package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Klijent implements ApstraktniDomenskiObjekat {

    private int idKlijent;
    private String ime;
    private String prezime;
    private Date datumRodjenja;
    private int brojGodina;
    private Pol pol;
    private double visina;
    private double tezina;
    private double BMR;
    private String brojTelefona;

    public Klijent() {
    }

    public Klijent(int idKlijent, String ime, String prezime, Date datumRodjenja, int brojGodina, Pol pol,
                   double visina, double tezina, double BMR, String brojTelefona) {
        this.idKlijent = idKlijent;
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.brojGodina = brojGodina;
        this.pol = pol;
        this.visina = visina;
        this.tezina = tezina;
        this.BMR = BMR;
        this.brojTelefona = brojTelefona;
    }

    public int getIdKlijent() {
        return idKlijent;
    }

    public void setIdKlijent(int idKlijent) {
        this.idKlijent = idKlijent;
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

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public int getBrojGodina() {
        return brojGodina;
    }

    public void setBrojGodina(int brojGodina) {
        this.brojGodina = brojGodina;
    }

    public Pol getPol() {
        return pol;
    }

    public void setPol(Pol pol) {
        this.pol = pol;
    }

    public double getVisina() {
        return visina;
    }

    public void setVisina(double visina) {
        this.visina = visina;
    }

    public double getTezina() {
        return tezina;
    }

    public void setTezina(double tezina) {
        this.tezina = tezina;
    }

    public double getBMR() {
        return BMR;
    }

    public void setBMR(double BMR) {
        this.BMR = BMR;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public String vratiNazivTabele() {
        return "klijent";
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime, prezime, datumRodjenja, brojGodina, pol, visina, tezina, BMR, brojTelefona";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + ime + "', '" + prezime + "', '" + new java.sql.Date(datumRodjenja.getTime()) + "', " +
                brojGodina + ", '" + pol.name() + "', " + visina + ", " + tezina + ", " + BMR + ", '" + brojTelefona + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "idKlijent=" + idKlijent;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "ime='" + ime + "', prezime='" + prezime + "', datumRodjenja='" + new java.sql.Date(datumRodjenja.getTime()) +
                "', brojGodina=" + brojGodina + ", pol='" + pol.name() + "', visina=" + visina + ", tezina=" + tezina +
                ", BMR=" + BMR + ", brojTelefona='" + brojTelefona + "'";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            Klijent k = new Klijent();
            k.setIdKlijent(rs.getInt("idKlijent"));
            k.setIme(rs.getString("ime"));
            k.setPrezime(rs.getString("prezime"));
            k.setDatumRodjenja(rs.getDate("datumRodjenja"));
            k.setBrojGodina(rs.getInt("brojGodina"));
            k.setPol(Pol.valueOf(rs.getString("pol")));
            k.setVisina(rs.getDouble("visina"));
            k.setTezina(rs.getDouble("tezina"));
            k.setBMR(rs.getDouble("BMR"));
            k.setBrojTelefona(rs.getString("brojTelefona"));
            lista.add(k);
        }
        return lista;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        Klijent k = new Klijent();
        k.setIdKlijent(rs.getInt("idKlijent"));
        k.setIme(rs.getString("ime"));
        k.setPrezime(rs.getString("prezime"));
        k.setDatumRodjenja(rs.getDate("datumRodjenja"));
        k.setBrojGodina(rs.getInt("brojGodina"));
        k.setPol(Pol.valueOf(rs.getString("pol")));
        k.setVisina(rs.getDouble("visina"));
        k.setTezina(rs.getDouble("tezina"));
        k.setBMR(rs.getDouble("BMR"));
        k.setBrojTelefona(rs.getString("brojTelefona"));
        return k;
    }
}
