package kontroleri;

import cordinator.Cordinator;
import domen.Klijent;
import domen.TreningSaTrenerom;
import forme.DodajTreningSaTreneromForm;
import forme.FormaMod;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

public class DodajTreningSaTreneromController {

    private final DodajTreningSaTreneromForm forma;

    public DodajTreningSaTreneromController(DodajTreningSaTreneromForm forma) {
        this.forma = forma;
        addActionListener();
    }

    public void otvoriFormu(FormaMod mod) {
        pripremiFormu(mod);
        forma.setVisible(true);
    }

    private void addActionListener() {
        forma.getjButtonSacuvaj().addActionListener(e -> dodaj());
        forma.getjButtonIzmeni().addActionListener(e -> izmeni());
    }

    private void dodaj() {
        try {
            Klijent k = (Klijent) forma.getjComboBoxKlijenti().getSelectedItem();
            String nivo = forma.getjTextFieldNivoPodrske().getText().trim();
            String zdrav = forma.getjTextFieldZdravstvenoStanje().getText().trim();

            if (k == null) {
                JOptionPane.showMessageDialog(forma, "Morate izabrati klijenta.", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            TreningSaTrenerom t = new TreningSaTrenerom(k, nivo, zdrav);
            Komunikacija.getInstanca().dodajTreningSaTrenerom(t);

            JOptionPane.showMessageDialog(forma, "Trening sa trenerom uspešno dodat.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
            Cordinator.getInstanca().osveziFormu();
            forma.dispose();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(forma, "Greška prilikom dodavanja.", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void izmeni() {
        try {
            TreningSaTrenerom t = (TreningSaTrenerom) Cordinator.getInstanca().vratiParam("treningSaTrenerom");
            if (t == null) {
                JOptionPane.showMessageDialog(forma, "Nije prosleđen trening za izmenu.", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Klijent k = (Klijent) forma.getjComboBoxKlijenti().getSelectedItem();
            String nivo = forma.getjTextFieldNivoPodrske().getText().trim();
            String zdrav = forma.getjTextFieldZdravstvenoStanje().getText().trim();

            if (k == null) {
                JOptionPane.showMessageDialog(forma, "Morate izabrati klijenta.", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            t.setKlijent(k);
            t.setNivoPodrske(nivo);
            t.setZdravstvenoStanje(zdrav);

            Komunikacija.getInstanca().azurirajTreningSaTrenerom(t);

            JOptionPane.showMessageDialog(forma, "Trening sa trenerom uspešno izmenjen.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
            Cordinator.getInstanca().osveziFormu();
            forma.dispose();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(forma, "Greška prilikom izmene.", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void pripremiFormu(FormaMod mod) {

        List<Klijent> klijenti = Komunikacija.getInstanca().ucitajKlijente();
        if (klijenti == null) klijenti = new ArrayList<>();

        forma.getjComboBoxKlijenti().removeAllItems();
        for (Klijent k : klijenti) {
            forma.getjComboBoxKlijenti().addItem(k);
        }

        if (mod == FormaMod.DODAJ) {
            forma.getjButtonSacuvaj().setVisible(true);
            forma.getjButtonIzmeni().setVisible(false);

            Klijent izabrani = (Klijent) Cordinator.getInstanca().vratiParam("klijent");
            if (izabrani != null) {
                forma.getjComboBoxKlijenti().setSelectedItem(izabrani);
            }

            forma.getjTextFieldNivoPodrske().setText("");
            forma.getjTextFieldZdravstvenoStanje().setText("");

        } else {
            forma.getjButtonSacuvaj().setVisible(false);
            forma.getjButtonIzmeni().setVisible(true);

            TreningSaTrenerom t = (TreningSaTrenerom) Cordinator.getInstanca().vratiParam("treningSaTrenerom");
            if (t != null) {
                forma.getjComboBoxKlijenti().setSelectedItem(t.getKlijent());
                forma.getjTextFieldNivoPodrske().setText(t.getNivoPodrske());
                forma.getjTextFieldZdravstvenoStanje().setText(t.getZdravstvenoStanje());
            }
        }
    }
}