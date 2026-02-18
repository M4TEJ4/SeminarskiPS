package kontroleri;

import cordinator.Cordinator;
import domen.Klijent;
import forme.PrikazKlijenataForm;
import forme.model.ModelTabeleKlijent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

public class PrikazKlijenataController {

    private final PrikazKlijenataForm pkf;

    public PrikazKlijenataController(PrikazKlijenataForm pkf) {
        this.pkf = pkf;
        addActionListener();
    }

    public void otvoriFormu() {
        pripremiFormu();
        pkf.setVisible(true);
    }

    public void pripremiFormu() {
        List<Klijent> klijenti = Komunikacija.getInstanca().ucitajKlijente();
        ModelTabeleKlijent mtk = new ModelTabeleKlijent(klijenti);
        pkf.getjTableKlijenti().setModel(mtk);
    }

    private void addActionListener() {

        pkf.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pkf.getjTableKlijenti().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pkf, "Morate izabrati klijenta za brisanje.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                ModelTabeleKlijent mtk = (ModelTabeleKlijent) pkf.getjTableKlijenti().getModel();
                Klijent k = mtk.getLista().get(red);

                try {
                    Komunikacija.getInstanca().obrisiKlijenta(k);
                    JOptionPane.showMessageDialog(pkf, "Klijent uspešno obrisan.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    pripremiFormu();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(pkf, "Sistem ne može da obriše klijenta.", "Greška", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        pkf.addBtnAzurirajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pkf.getjTableKlijenti().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pkf, "Morate izabrati klijenta za izmenu.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                ModelTabeleKlijent mtk = (ModelTabeleKlijent) pkf.getjTableKlijenti().getModel();
                Klijent k = mtk.getLista().get(red);

                Cordinator.getInstanca().dodajParam("klijent", k);
                Cordinator.getInstanca().otvoriIzmeniKlijentaFormu();
            }
        });

        pkf.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ime = pkf.getjTextFieldIme().getText().trim();
                String prezime = pkf.getjTextFieldPrezime().getText().trim();
                String tel = pkf.getjTextFieldBrojTelefona().getText().trim();

                ModelTabeleKlijent mtk = (ModelTabeleKlijent) pkf.getjTableKlijenti().getModel();
                mtk.pretrazi(ime, prezime, tel);

                if (mtk.getLista().isEmpty()) {
                    JOptionPane.showMessageDialog(pkf, "Sistem ne može da pronađe klijente po zadatim kriterijumima.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        pkf.addBtnResetujActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pripremiFormu();
            }
        });
    }

    public void osveziFormu() {
        pripremiFormu();
    }
}
