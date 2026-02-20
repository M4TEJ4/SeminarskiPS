package kontroleri;

import cordinator.Cordinator;
import domen.Trener;
import forme.PrikazTreneraForm;
import forme.model.ModelTabeleTrener;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

public class PrikazTreneraController {

    private final PrikazTreneraForm ptf;

    public PrikazTreneraController(PrikazTreneraForm ptf) {
        this.ptf = ptf;
        addActionListener();
    }

    public void otvoriFormu() {
        pripremiFormu();
        ptf.setVisible(true);
    }

    public void pripremiFormu() {
        List<Trener> treneri = Komunikacija.getInstanca().ucitajTrenere();
        ModelTabeleTrener mtt = new ModelTabeleTrener(treneri);
        ptf.getjTableTreneri().setModel(mtt);
    }

    private void addActionListener() {

      

        // AZURIRAJ
        ptf.addBtnAzurirajActionListener(e -> {
            int red = ptf.getjTableTreneri().getSelectedRow();
            if (red == -1) {
                JOptionPane.showMessageDialog(ptf, "Morate izabrati trenera za izmenu.", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            ModelTabeleTrener mtt = (ModelTabeleTrener) ptf.getjTableTreneri().getModel();
            Trener t = mtt.getLista().get(red);

            Cordinator.getInstanca().dodajParam("trener", t);
            Cordinator.getInstanca().otvoriIzmeniTreneraFormu();
        });

        // OBRISI
        ptf.addBtnObrisiActionListener(e -> {
            int red = ptf.getjTableTreneri().getSelectedRow();
            if (red == -1) {
                JOptionPane.showMessageDialog(ptf, "Morate izabrati trenera za brisanje.", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            ModelTabeleTrener mtt = (ModelTabeleTrener) ptf.getjTableTreneri().getModel();
            Trener t = mtt.getLista().get(red);

            int confirm = JOptionPane.showConfirmDialog(ptf,
                    "Da li ste sigurni da želite da obrišete trenera?",
                    "Potvrda", JOptionPane.YES_NO_OPTION);

            if (confirm != JOptionPane.YES_OPTION) return;

            try {
                Komunikacija.getInstanca().obrisiTrenera(t);
                JOptionPane.showMessageDialog(ptf, "Trener uspešno obrisan.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                pripremiFormu();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(ptf, "Sistem ne može da obriše trenera.", "Greška", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });

        // PRETRAZI
        ptf.addBtnPretraziActionListener(e -> {
            String ime = ptf.getjTextFieldIme().getText().trim();
            String prezime = ptf.getjTextFieldPrezime().getText().trim();
            String user = ptf.getjTextFieldKorisnickoIme().getText().trim();

            ModelTabeleTrener mtt = (ModelTabeleTrener) ptf.getjTableTreneri().getModel();
            mtt.pretrazi(ime, prezime, user);

            if (mtt.getLista().isEmpty()) {
                JOptionPane.showMessageDialog(ptf, "Sistem ne može da pronađe trenere po zadatim kriterijumima.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
            }
        });

        // RESETUJ
        ptf.addBtnResetujActionListener(e -> {
            pripremiFormu();
        });
    }

    public void osveziFormu() {
        pripremiFormu();
    }
}