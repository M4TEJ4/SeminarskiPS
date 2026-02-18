package kontroleri;

import cordinator.Cordinator;
import domen.Vezba;
import forme.PrikazVezbiForm;
import forme.model.ModelTabeleVezba;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

public class PrikazVezbiController {

    private final PrikazVezbiForm pvf;

    public PrikazVezbiController(PrikazVezbiForm pvf) {
        this.pvf = pvf;
        addActionListener();
    }

    public void otvoriFormu() {
        pripremiFormu();
        pvf.setVisible(true);
    }

    public void pripremiFormu() {
        List<Vezba> vezbe = Komunikacija.getInstanca().ucitajVezbe();
        ModelTabeleVezba mtv = new ModelTabeleVezba(vezbe);
        pvf.getjTableVezbe().setModel(mtv);
    }

    private void addActionListener() {

        pvf.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pvf.getjTableVezbe().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pvf, "Morate izabrati vežbu za brisanje.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                ModelTabeleVezba mtv = (ModelTabeleVezba) pvf.getjTableVezbe().getModel();
                Vezba v = mtv.getLista().get(red);

                try {
                    Komunikacija.getInstanca().obrisiVezbu(v);
                    JOptionPane.showMessageDialog(pvf, "Vežba uspešno obrisana.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    pripremiFormu();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(pvf, "Sistem ne može da obriše vežbu.", "Greška", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        pvf.addBtnAzurirajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pvf.getjTableVezbe().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pvf, "Morate izabrati vežbu za izmenu.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                ModelTabeleVezba mtv = (ModelTabeleVezba) pvf.getjTableVezbe().getModel();
                Vezba v = mtv.getLista().get(red);

                Cordinator.getInstanca().dodajParam("vezba", v);
                Cordinator.getInstanca().otvoriIzmeniVezbuFormu();
            }
        });

        pvf.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String naziv = pvf.getjTextFieldNaziv().getText().trim();
                String grupa = pvf.getjTextFieldGrupaMisica().getText().trim();
                String oprema = pvf.getjTextFieldOprema().getText().trim();

                ModelTabeleVezba mtv = (ModelTabeleVezba) pvf.getjTableVezbe().getModel();
                mtv.pretrazi(naziv, grupa, oprema);

                if (mtv.getLista().isEmpty()) {
                    JOptionPane.showMessageDialog(pvf, "Sistem ne može da pronađe vežbe po zadatim kriterijumima.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        pvf.addBtnResetujActionListener(new ActionListener() {
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
