package kontroleri;

import cordinator.Cordinator;
import domen.PlanTreninga;
import forme.GlavnaForma;
import forme.model.ModelTabelePlanTreninga;

import java.util.List;
import javax.swing.JOptionPane;

import komunikacija.Komunikacija;

public class PrikazPlanovaTreningaController {

    private final GlavnaForma gf;

    public PrikazPlanovaTreningaController(GlavnaForma gf) {
        this.gf = gf;
        addActionListener();
    }

    public void pripremiPlanove() {
        String uslov = " INNER JOIN trener trener ON (trener.idTrener = plantreninga.idTrener) "
                     + " INNER JOIN klijent klijent ON (klijent.idKlijent = plantreninga.idKlijent) ";

        List<PlanTreninga> planovi = Komunikacija.getInstanca().ucitajPlanoveTreninga(uslov);
        gf.getjTablePlanovi().setModel(new ModelTabelePlanTreninga(planovi));
    }

    private void addActionListener() {

        gf.addBtnDodajPlanActionListener(e -> {
            Cordinator.getInstanca().otvoriDodajPlanTreningaFormu();
        });

        gf.addBtnIzmeniPlanActionListener(e -> {
            int red = gf.getjTablePlanovi().getSelectedRow();
            if (red == -1) {
                JOptionPane.showMessageDialog(gf, "Morate izabrati plan za izmenu.", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            ModelTabelePlanTreninga mt = (ModelTabelePlanTreninga) gf.getjTablePlanovi().getModel();
            PlanTreninga p = mt.getLista().get(red);

            Cordinator.getInstanca().dodajParam("planTreninga", p);
            Cordinator.getInstanca().otvoriIzmeniPlanTreningaFormu();
        });

        gf.addBtnObrisiPlanActionListener(e -> {
            int red = gf.getjTablePlanovi().getSelectedRow();
            if (red == -1) {
                JOptionPane.showMessageDialog(gf, "Morate izabrati plan za brisanje.", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            ModelTabelePlanTreninga mt = (ModelTabelePlanTreninga) gf.getjTablePlanovi().getModel();
            PlanTreninga p = mt.getLista().get(red);

            int confirm = JOptionPane.showConfirmDialog(gf,
                    "Da li ste sigurni da želite da obrišete plan treninga?",
                    "Potvrda", JOptionPane.YES_NO_OPTION);

            if (confirm != JOptionPane.YES_OPTION) return;

            try {
                Komunikacija.getInstanca().obrisiPlanTreninga(p);
                JOptionPane.showMessageDialog(gf, "Plan treninga uspešno obrisan.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                pripremiPlanove();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(gf, "Sistem ne može da obriše plan treninga.", "Greška", JOptionPane.ERROR_MESSAGE);
            }
        });

        gf.addBtnOsveziPlanoveActionListener(e -> pripremiPlanove());
    }

    public void osveziFormu() {
        pripremiPlanove();
    }

    public void otvoriFormu() {
        pripremiPlanove();
        gf.setVisible(true);    
        gf.setLocationRelativeTo(null);
    }
}