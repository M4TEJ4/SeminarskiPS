package operacije.plantreninga;

import domen.PlanTreninga;
import operacije.ApstraktnaGenerickaOperacija;

public class ObrisiPlanTreningaSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof PlanTreninga)) {
            throw new Exception("Sistem ne može da obriše plan treninga: neispravan objekat.");
        }

        PlanTreninga p = (PlanTreninga) param;

        if (p.getIdPlanTreninga() <= 0) {
            throw new Exception("ID plana treninga nije ispravan.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        PlanTreninga plan = (PlanTreninga) param;

      
        broker.deleteWhere("stavkaplanatreninga", "idPlanTreninga=" + plan.getIdPlanTreninga());

       
        broker.delete(plan);
    }
}