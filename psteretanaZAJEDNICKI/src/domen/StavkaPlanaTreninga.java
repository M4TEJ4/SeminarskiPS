package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StavkaPlanaTreninga implements ApstraktniDomenskiObjekat {

    private int rb;
    private int brojSerija;
    private int brojPonavljanja;
    private String napomena;

    private int planId; // FK na PlanTreninga
    private Vezba vezba;

    public StavkaPlanaTreninga() {
    }

    public StavkaPlanaTreninga(int rb, int brojSerija, int brojPonavljanja, String napomena, int planId, Vezba vezba) {
        this.rb = rb;
        this.brojSerija = brojSerija;
        this.brojPonavljanja = brojPonavljanja;
        this.napomena = napomena;
        this.planId = planId;
        this.vezba = vezba;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public int getBrojSerija() {
        return brojSerija;
    }

    public void setBrojSerija(int brojSerija) {
        this.brojSerija = brojSerija;
    }

    public int getBrojPonavljanja() {
        return brojPonavljanja;
    }

    public void setBrojPonavljanja(int brojPonavljanja) {
        this.brojPonavljanja = brojPonavljanja;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public Vezba getVezba() {
        return vezba;
    }

    public void setVezba(Vezba vezba) {
        this.vezba = vezba;
    }

    @Override
    public String toString() {
        return rb + ". " + (vezba != null ? vezba.getNaziv() : "") + " (" + brojSerija + "x" + brojPonavljanja + ")";
    }

    @Override
    public String vratiNazivTabele() {
        return "stavkaplanatreninga";
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "rb, brojSerija, brojPonavljanja, napomena, idPlanTreninga, idVezba";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return rb + ", " + brojSerija + ", " + brojPonavljanja + ", '" + napomena + "', " + planId + ", " + vezba.getIdVezba();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "idPlanTreninga=" + planId + " AND rb=" + rb;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "brojSerija=" + brojSerija + ", brojPonavljanja=" + brojPonavljanja + ", napomena='" + napomena + "', idVezba=" + vezba.getIdVezba();
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            StavkaPlanaTreninga s = new StavkaPlanaTreninga();
            s.setRb(rs.getInt("rb"));
            s.setBrojSerija(rs.getInt("brojSerija"));
            s.setBrojPonavljanja(rs.getInt("brojPonavljanja"));
            s.setNapomena(rs.getString("napomena"));
            s.setPlanId(rs.getInt("idPlanTreninga"));

            Vezba v = new Vezba();
            v.setIdVezba(rs.getInt("vezba.idVezba"));
            v.setNaziv(rs.getString("vezba.naziv"));
            v.setGrupaMisica(rs.getString("vezba.grupaMisica"));
            v.setOprema(rs.getString("vezba.oprema"));
            s.setVezba(v);

            lista.add(s);
        }
        return lista;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        StavkaPlanaTreninga s = new StavkaPlanaTreninga();
        s.setRb(rs.getInt("rb"));
        s.setBrojSerija(rs.getInt("brojSerija"));
        s.setBrojPonavljanja(rs.getInt("brojPonavljanja"));
        s.setNapomena(rs.getString("napomena"));
        s.setPlanId(rs.getInt("idPlanTreninga"));

        Vezba v = new Vezba();
        v.setIdVezba(rs.getInt("vezba.idVezba"));
        v.setNaziv(rs.getString("vezba.naziv"));
        v.setGrupaMisica(rs.getString("vezba.grupaMisica"));
        v.setOprema(rs.getString("vezba.oprema"));
        s.setVezba(v);

        return s;
    }
}
