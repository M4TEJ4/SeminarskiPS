package kontroleri;

import cordinator.Cordinator;
import domen.Klijent;
import domen.SamostalanTrening;
import forme.DodajSamostalanTreningForm;
import forme.FormaMod;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

public class DodajSamostalanTreningController {

    private final DodajSamostalanTreningForm forma;

    public DodajSamostalanTreningController(DodajSamostalanTreningForm forma) {
        this.forma = forma;
        addActionListener();
    }

    public void otvoriFormu(FormaMod mod) {
        pripremiFormu(mod);
        forma.setVisible(true);
    }

    private void addActionListener() {

        // DODAJ
        forma.addBtnSacuvajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj();
            }
        });

        // IZMENI
        forma.addBtnIzmeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni();
            }
        });
    }

    private void dodaj() {
        try {
            Klijent k = (Klijent) forma.getjComboBoxKlijenti().getSelectedItem();
            boolean teretana = forma.getjCheckBoxTeretana().isSelected();
            boolean kardio = forma.getjCheckBoxKardio().isSelected();

            if (k == null) {
                JOptionPane.showMessageDialog(forma, "Morate izabrati klijenta.", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            SamostalanTrening s = new SamostalanTrening(k, teretana, kardio);

            Komunikacija.getInstanca().dodajSamostalanTrening(s);

            JOptionPane.showMessageDialog(forma, "Samostalan trening uspešno dodat.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);

            Cordinator.getInstanca().osveziFormu();
            forma.dispose();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(forma, "Greška prilikom dodavanja treninga.", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void izmeni() {
        try {
            SamostalanTrening s = (SamostalanTrening) Cordinator.getInstanca().vratiParam("samostalanTrening");

            Klijent k = (Klijent) forma.getjComboBoxKlijenti().getSelectedItem();
            boolean teretana = forma.getjCheckBoxTeretana().isSelected();
            boolean kardio = forma.getjCheckBoxKardio().isSelected();

            s.setKlijent(k);
            s.setTeretana(teretana);
            s.setKardio(kardio);

            Komunikacija.getInstanca().azurirajSamostalanTrening(s);

            JOptionPane.showMessageDialog(forma, "Samostalan trening uspešno izmenjen.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);

            Cordinator.getInstanca().osveziFormu();
            forma.dispose();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(forma, "Greška prilikom izmene treninga.", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void pripremiFormu(FormaMod mod) {

        // Ucitaj sve klijente u combobox
        List<Klijent> klijenti = Komunikacija.getInstanca().ucitajKlijente();
        forma.getjComboBoxKlijenti().removeAllItems();
        for (Klijent k : klijenti) {
            forma.getjComboBoxKlijenti().addItem(k);
        }

        switch (mod) {
            case DODAJ:
                forma.getjButtonDodaj().setVisible(true);
                forma.getjButtonIzmeni().setVisible(false);

                // Ako je selektovan klijent sa glavne forme — postavi ga
                Klijent izabrani = (Klijent) Cordinator.getInstanca().vratiParam("klijent");
                if (izabrani != null) {
                    forma.getjComboBoxKlijenti().setSelectedItem(izabrani);
                }

                forma.getjCheckBoxTeretana().setSelected(false);
                forma.getjCheckBoxKardio().setSelected(false);
                break;

            case IZMENI:
                forma.getjButtonDodaj().setVisible(false);
                forma.getjButtonIzmeni().setVisible(true);

                SamostalanTrening s = (SamostalanTrening) Cordinator.getInstanca().vratiParam("samostalanTrening");

                if (s != null) {
                    forma.getjComboBoxKlijenti().setSelectedItem(s.getKlijent());
                    forma.getjCheckBoxTeretana().setSelected(s.isTeretana());
                    forma.getjCheckBoxKardio().setSelected(s.isKardio());
                }
                break;
        }
    }
}
