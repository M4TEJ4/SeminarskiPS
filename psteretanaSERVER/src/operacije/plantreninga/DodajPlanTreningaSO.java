package operacije.plantreninga;

import domen.PlanTreninga;
import domen.StavkaPlanaTreninga;
import operacije.ApstraktnaGenerickaOperacija;

import java.util.List;

public class DodajPlanTreningaSO extends ApstraktnaGenerickaOperacija {

    private int noviPlanId;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof PlanTreninga)) {
            throw new Exception("Sistem ne može da doda plan treninga: neispravan objekat.");
        }

        PlanTreninga p = (PlanTreninga) param;

        if (p.getDatumPocetka() == null || p.getDatumKraja() == null) {
            throw new Exception("Datumi početka i kraja su obavezni.");
        }
        if (p.getDatumKraja().before(p.getDatumPocetka())) {
            throw new Exception("Datum kraja ne može biti pre datuma početka.");
        }
        if (p.getBrojTreningaNedeljno() <= 0) {
            throw new Exception("Broj treninga nedeljno mora biti veći od 0.");
        }
        if (p.getUkupanBrojVezbi() < 0) {
            throw new Exception("Ukupan broj vežbi ne može biti negativan.");
        }
        if (p.getFaktorAktivnosti() <= 0) {
            throw new Exception("Faktor aktivnosti mora biti veći od 0.");
        }
        if (p.getDnevniUnosKalorija() <= 0) {
            throw new Exception("Dnevni unos kalorija mora biti veći od 0.");
        }
        if (p.getTrener() == null || p.getTrener().getIdTrener() <= 0) {
            throw new Exception("Trener je obavezan.");
        }
        if (p.getKlijent() == null || p.getKlijent().getIdKlijent() <= 0) {
            throw new Exception("Klijent je obavezan.");
        }

        
         if (p.getStavke() == null || p.getStavke().isEmpty()) {
             throw new Exception("Plan mora imati bar jednu stavku.");
         }

        if (p.getStavke() != null) {
            validirajStavke(p.getStavke());
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        PlanTreninga plan = (PlanTreninga) param;

       
        broker.add(plan);
        noviPlanId = plan.getIdPlanTreninga();

         

       
        if (plan.getStavke() != null) {
            int rb = 1;
            for (StavkaPlanaTreninga s : plan.getStavke()) {
                s.setPlanId(noviPlanId);
                s.setRb(rb++);
                broker.add(s);
            }
        }
    }

    private void validirajStavke(List<StavkaPlanaTreninga> stavke) throws Exception {
        int i = 1;
        for (StavkaPlanaTreninga s : stavke) {
            if (s == null) throw new Exception("Stavka plana ne sme biti null.");
            if (s.getBrojSerija() <= 0) throw new Exception("Broj serija mora biti > 0 (stavka " + i + ").");
            if (s.getBrojPonavljanja() <= 0) throw new Exception("Broj ponavljanja mora biti > 0 (stavka " + i + ").");
            if (s.getNapomena() == null) s.setNapomena("");
            if (s.getVezba() == null || s.getVezba().getIdVezba() <= 0) {
                throw new Exception("Vežba je obavezna (stavka " + i + ").");
            }
            i++;
        }
    }

    public int getNoviPlanId() {
        return noviPlanId;
    }
}