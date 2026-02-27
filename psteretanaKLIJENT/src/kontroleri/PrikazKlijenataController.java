package kontroleri;

import cordinator.Cordinator;
import domen.Klijent;
import domen.SamostalanTrening;
import domen.TreningSaTrenerom;
import forme.PrikazKlijenataForm;
import forme.model.ModelTabeleKlijent;
import forme.model.ModelTabeleSamostalanTrening;
import forme.model.ModelTabeleTreningSaTrenerom;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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

        // OBRISI KLIJENTA
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
                    osveziSamostalneTreninge();
                    osveziTreningeSaTrenerom();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(pkf, "Sistem ne može da obriše klijenta.", "Greška", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        // AZURIRAJ KLIJENTA
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

        // PRETRAZI
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

        // RESETUJ
        pkf.addBtnResetujActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pripremiFormu();
                osveziSamostalneTreninge();
                osveziTreningeSaTrenerom();
            }
        });

        // ==============================
        // SAMOSTALNI TRENINZI (CRUD)
        // ==============================

        pkf.addBtnPrikaziSamostalneTreningeActionListener(e -> osveziSamostalneTreninge());

        pkf.addBtnDodajSamostalanTreningActionListener(e -> { 
            Cordinator.getInstanca().otvoriDodajSamostalanTreningFormu();
        });

        pkf.addBtnIzmeniSamostalanTreningActionListener(e -> {
            int redTr = pkf.getjTableSamostalnitreninzi().getSelectedRow();
            if (redTr == -1) {
                JOptionPane.showMessageDialog(pkf, "Morate izabrati trening za izmenu.", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            ModelTabeleSamostalanTrening mts =
                    (ModelTabeleSamostalanTrening) pkf.getjTableSamostalnitreninzi().getModel();
            SamostalanTrening s = mts.getLista().get(redTr);

            Cordinator.getInstanca().dodajParam("samostalanTrening", s);
            Cordinator.getInstanca().otvoriIzmeniSamostalanTreningFormu();
        });

        pkf.addBtnObrisiSamostalanTreningActionListener(e -> {
            int redTr = pkf.getjTableSamostalnitreninzi().getSelectedRow();
            if (redTr == -1) {
                JOptionPane.showMessageDialog(pkf, "Morate izabrati trening za brisanje.", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            ModelTabeleSamostalanTrening mts =
                    (ModelTabeleSamostalanTrening) pkf.getjTableSamostalnitreninzi().getModel();
            SamostalanTrening s = mts.getLista().get(redTr);

            int confirm = JOptionPane.showConfirmDialog(pkf,
                    "Da li ste sigurni da želite da obrišete trening?",
                    "Potvrda", JOptionPane.YES_NO_OPTION);

            if (confirm != JOptionPane.YES_OPTION) return;

            try {
                Komunikacija.getInstanca().obrisiSamostalanTrening(s);
                JOptionPane.showMessageDialog(pkf, "Trening uspešno obrisan.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                osveziSamostalneTreninge();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(pkf, "Sistem ne može da obriše trening.", "Greška", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });

        // ==============================
        // TRENINZI SA TRENEROM (CRUD)
        // ==============================

        pkf.addBtnPrikaziTreningeSaTreneromActionListener(e -> osveziTreningeSaTrenerom());

        pkf.addBtnDodajTreningSaTreneromActionListener(e -> { 
            Cordinator.getInstanca().otvoriDodajTreningSaTreneromFormu();
        });

        
        
        pkf.addBtnIzmeniTreningSaTreneromActionListener(e -> {
            int redTr = pkf.getjTableTreninziSaTrenerom().getSelectedRow();
            if (redTr == -1) {
                JOptionPane.showMessageDialog(pkf, "Morate izabrati trening za izmenu.", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            ModelTabeleTreningSaTrenerom mt =
                    (ModelTabeleTreningSaTrenerom) pkf.getjTableTreninziSaTrenerom().getModel();

            TreningSaTrenerom t = mt.getLista().get(redTr);

            Cordinator.getInstanca().dodajParam("treningSaTrenerom", t);
            Cordinator.getInstanca().otvoriIzmeniTreningSaTreneromFormu();
        });

        pkf.addBtnObrisiTreningSaTreneromActionListener(e -> {
            int redTr = pkf.getjTableTreninziSaTrenerom().getSelectedRow();
            if (redTr == -1) {
                JOptionPane.showMessageDialog(pkf, "Morate izabrati trening za brisanje.", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            ModelTabeleTreningSaTrenerom mt =
                    (ModelTabeleTreningSaTrenerom) pkf.getjTableTreninziSaTrenerom().getModel();

            TreningSaTrenerom t = mt.getLista().get(redTr);

            int confirm = JOptionPane.showConfirmDialog(pkf,
                    "Da li ste sigurni da želite da obrišete trening sa trenerom?",
                    "Potvrda", JOptionPane.YES_NO_OPTION);

            if (confirm != JOptionPane.YES_OPTION) return;

            try {
                Komunikacija.getInstanca().obrisiTreningSaTrenerom(t);
                JOptionPane.showMessageDialog(pkf, "Trening uspešno obrisan.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                osveziTreningeSaTrenerom();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(pkf, "Sistem ne može da obriše trening.", "Greška", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });
    }

    public void osveziFormu() {
        pripremiFormu();
    }

    public void osveziSamostalneTreninge() {
        int red = pkf.getjTableKlijenti().getSelectedRow();
        if (red == -1) {
            pkf.getjTableSamostalnitreninzi().setModel(new ModelTabeleSamostalanTrening(new ArrayList<>()));
            return;
        }

        ModelTabeleKlijent mtk = (ModelTabeleKlijent) pkf.getjTableKlijenti().getModel();
        Klijent k = mtk.getLista().get(red);

        List<SamostalanTrening> svi = Komunikacija.getInstanca().ucitajSamostalneTreninge();

        List<SamostalanTrening> njegovi = svi.stream()
                .filter(s -> s.getKlijent() != null && s.getKlijent().getIdKlijent() == k.getIdKlijent())
                .collect(Collectors.toList());

        pkf.getjTableSamostalnitreninzi().setModel(new ModelTabeleSamostalanTrening(njegovi));
    }

    public void osveziTreningeSaTrenerom() {
        int red = pkf.getjTableKlijenti().getSelectedRow();
        if (red == -1) {
            pkf.getjTableTreninziSaTrenerom().setModel(new ModelTabeleTreningSaTrenerom(new ArrayList<>()));
            return;
        }

        ModelTabeleKlijent mtk = (ModelTabeleKlijent) pkf.getjTableKlijenti().getModel();
        Klijent k = mtk.getLista().get(red);

        List<TreningSaTrenerom> svi = Komunikacija.getInstanca().ucitajTreningeSaTrenerom();

        List<TreningSaTrenerom> njegovi = svi.stream()
                .filter(t -> t.getKlijent() != null && t.getKlijent().getIdKlijent() == k.getIdKlijent())
                .collect(Collectors.toList());

        pkf.getjTableTreninziSaTrenerom().setModel(new ModelTabeleTreningSaTrenerom(njegovi));
    }
}
