package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlanTreninga implements ApstraktniDomenskiObjekat {

    private int idPlanTreninga;
    private Date datumPocetka;
    private Date datumKraja;
    private int brojTreningaNedeljno;
    private int ukupanBrojVezbi;
    private double faktorAktivnosti;
    private int dnevniUnosKalorija;

    private Trener trener;
    private Klijent klijent;

    private List<StavkaPlanaTreninga> stavke;

    public PlanTreninga() {
        stavke = new ArrayList<>();
    }

    public PlanTreninga(int idPlanTreninga, Date datumPocetka, Date datumKraja, int brojTreningaNedeljno,
                        int ukupanBrojVezbi, double faktorAktivnosti, int dnevniUnosKalorija,
                        Trener trener, Klijent klijent) {
        this.idPlanTreninga = idPlanTreninga;
        this.datumPocetka = datumPocetka;
        this.datumKraja = datumKraja;
        this.brojTreningaNedeljno = brojTreningaNedeljno;
        this.ukupanBrojVezbi = ukupanBrojVezbi;
        this.faktorAktivnosti = faktorAktivnosti;
        this.dnevniUnosKalorija = dnevniUnosKalorija;
        this.trener = trener;
        this.klijent = klijent;
        this.stavke = new ArrayList<>();
    }

    public int getIdPlanTreninga() {
        return idPlanTreninga;
    }

    public void setIdPlanTreninga(int idPlanTreninga) {
        this.idPlanTreninga = idPlanTreninga;
    }

    public Date getDatumPocetka() {
        return datumPocetka;
    }

    public void setDatumPocetka(Date datumPocetka) {
        this.datumPocetka = datumPocetka;
    }

    public Date getDatumKraja() {
        return datumKraja;
    }

    public void setDatumKraja(Date datumKraja) {
        this.datumKraja = datumKraja;
    }

    public int getBrojTreningaNedeljno() {
        return brojTreningaNedeljno;
    }

    public void setBrojTreningaNedeljno(int brojTreningaNedeljno) {
        this.brojTreningaNedeljno = brojTreningaNedeljno;
    }

    public int getUkupanBrojVezbi() {
        return ukupanBrojVezbi;
    }

    public void setUkupanBrojVezbi(int ukupanBrojVezbi) {
        this.ukupanBrojVezbi = ukupanBrojVezbi;
    }

    public double getFaktorAktivnosti() {
        return faktorAktivnosti;
    }

    public void setFaktorAktivnosti(double faktorAktivnosti) {
        this.faktorAktivnosti = faktorAktivnosti;
    }

    public int getDnevniUnosKalorija() {
        return dnevniUnosKalorija;
    }

    public void setDnevniUnosKalorija(int dnevniUnosKalorija) {
        this.dnevniUnosKalorija = dnevniUnosKalorija;
    }

    public Trener getTrener() {
        return trener;
    }

    public void setTrener(Trener trener) {
        this.trener = trener;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

    public List<StavkaPlanaTreninga> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaPlanaTreninga> stavke) {
        this.stavke = stavke;
    }

    @Override
    public String toString() {
        return "Plan " + idPlanTreninga + " (" + datumPocetka + " - " + datumKraja + ")";
    }

    @Override
    public String vratiNazivTabele() {
        return "plantreninga";
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "datumPocetka, datumKraja, brojTreningaNedeljno, ukupanBrojVezbi, faktorAktivnosti, dnevniUnosKalorija, idTrener, idKlijent";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + new java.sql.Date(datumPocetka.getTime()) + "', '" + new java.sql.Date(datumKraja.getTime()) + "', " +
                brojTreningaNedeljno + ", " + ukupanBrojVezbi + ", " + faktorAktivnosti + ", " + dnevniUnosKalorija + ", " +
                trener.getIdTrener() + ", " + klijent.getIdKlijent();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "idPlanTreninga=" + idPlanTreninga;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "datumPocetka='" + new java.sql.Date(datumPocetka.getTime()) +
                "', datumKraja='" + new java.sql.Date(datumKraja.getTime()) +
                "', brojTreningaNedeljno=" + brojTreningaNedeljno +
                ", ukupanBrojVezbi=" + ukupanBrojVezbi +
                ", faktorAktivnosti=" + faktorAktivnosti +
                ", dnevniUnosKalorija=" + dnevniUnosKalorija +
                ", idTrener=" + trener.getIdTrener() +
                ", idKlijent=" + klijent.getIdKlijent();
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            PlanTreninga p = new PlanTreninga();
            p.setIdPlanTreninga(rs.getInt("idPlanTreninga"));
            p.setDatumPocetka(rs.getDate("datumPocetka"));
            p.setDatumKraja(rs.getDate("datumKraja"));
            p.setBrojTreningaNedeljno(rs.getInt("brojTreningaNedeljno"));
            p.setUkupanBrojVezbi(rs.getInt("ukupanBrojVezbi"));
            p.setFaktorAktivnosti(rs.getDouble("faktorAktivnosti"));
            p.setDnevniUnosKalorija(rs.getInt("dnevniUnosKalorija"));

            Trener t = new Trener();
            t.setIdTrener(rs.getInt("trener.idTrener"));
            t.setIme(rs.getString("trener.ime"));
            t.setPrezime(rs.getString("trener.prezime"));
            p.setTrener(t);

            Klijent k = new Klijent();
            k.setIdKlijent(rs.getInt("klijent.idKlijent"));
            k.setIme(rs.getString("klijent.ime"));
            k.setPrezime(rs.getString("klijent.prezime"));
            p.setKlijent(k);

            p.setStavke(new ArrayList<>());
            lista.add(p);
        }
        return lista;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        PlanTreninga p = new PlanTreninga();
        p.setIdPlanTreninga(rs.getInt("idPlanTreninga"));
        p.setDatumPocetka(rs.getDate("datumPocetka"));
        p.setDatumKraja(rs.getDate("datumKraja"));
        p.setBrojTreningaNedeljno(rs.getInt("brojTreningaNedeljno"));
        p.setUkupanBrojVezbi(rs.getInt("ukupanBrojVezbi"));
        p.setFaktorAktivnosti(rs.getDouble("faktorAktivnosti"));
        p.setDnevniUnosKalorija(rs.getInt("dnevniUnosKalorija"));

        Trener t = new Trener();
        t.setIdTrener(rs.getInt("trener.idTrener"));
        t.setIme(rs.getString("trener.ime"));
        t.setPrezime(rs.getString("trener.prezime"));
        p.setTrener(t);

        Klijent k = new Klijent();
        k.setIdKlijent(rs.getInt("klijent.idKlijent"));
        k.setIme(rs.getString("klijent.ime"));
        k.setPrezime(rs.getString("klijent.prezime"));
        p.setKlijent(k);

        p.setStavke(new ArrayList<>());
        return p;
    }
}
