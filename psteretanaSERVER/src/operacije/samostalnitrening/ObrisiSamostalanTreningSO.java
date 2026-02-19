package operacije.samostalnitrening;

import domen.Klijent;
import domen.SamostalanTrening;
import operacije.ApstraktnaGenerickaOperacija;

public class ObrisiSamostalanTreningSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof SamostalanTrening)) {
            throw new Exception("Sistem nije mogao da obriše samostalan trening: neispravan objekat.");
        }

        SamostalanTrening s = (SamostalanTrening) param;

        if (s.getKlijent() == null) {
            throw new Exception("Klijent je obavezan.");
        }

        Klijent k = s.getKlijent();
        if (k.getIdKlijent() <= 0) {
            throw new Exception("ID klijenta nije ispravan.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.delete((SamostalanTrening) param);
    }
}
