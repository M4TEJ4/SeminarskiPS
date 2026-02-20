package operacije.plantreninga;

import domen.PlanTreninga;
import domen.StavkaPlanaTreninga;
import operacije.ApstraktnaGenerickaOperacija;

import java.util.ArrayList;
import java.util.List;

public class UcitajPlanoveTreningaSO extends ApstraktnaGenerickaOperacija {

    private List<PlanTreninga> planovi;

    @Override
    protected void preduslovi(Object param) throws Exception {
        // nema posebnih preduslova
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        // 1) učitaj planove (join na trener/klijent već imaš u PlanTreninga.vratiListu kroz alias-e)
       
        List<PlanTreninga> lista = broker.getAll(new PlanTreninga(), kljuc);

        // 2) za svaki plan učitaj stavke
        for (PlanTreninga p : lista) {
            String uslov =
            " INNER JOIN vezba vezba ON (vezba.idVezba = stavkaplanatreninga.idVezba) "
          + " WHERE stavkaplanatreninga.idPlanTreninga=" + p.getIdPlanTreninga()
          + " ORDER BY stavkaplanatreninga.rb";
            List<StavkaPlanaTreninga> stavke = broker.getAll(new StavkaPlanaTreninga(), uslov);
            p.setStavke(stavke != null ? stavke : new ArrayList<>());
        }

        planovi = lista;
    }

    public List<PlanTreninga> getPlanovi() {
        return planovi;
    }
}