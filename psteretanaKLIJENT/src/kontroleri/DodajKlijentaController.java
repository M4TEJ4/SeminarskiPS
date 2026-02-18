package kontroleri;

import cordinator.Cordinator;
import domen.Klijent;
import domen.Pol;
import forme.DodajKlijentaForm;
import forme.FormaMod;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

public class DodajKlijentaController {

    private final DodajKlijentaForm dkf;

    public DodajKlijentaController(DodajKlijentaForm dkf) {
        this.dkf = dkf;
        addActionListener();
    }

    public void otvoriFormu(FormaMod mod) {
        pripremiFormu(mod);
        dkf.setVisible(true);
    }

    private void addActionListener() {

        dkf.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }
        });

        dkf.izmeniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni(e);
            }
        });
    }

    private void dodaj(ActionEvent e) {
        try {
            String ime = dkf.getjTextFieldIme().getText().trim();
            String prezime = dkf.getjTextFieldPrezime().getText().trim();
            String datumString = dkf.getjTextFieldDatumRodjenja().getText().trim();
            String brojGodinaStr = dkf.getjTextFieldBrojGodina().getText().trim();
            Pol pol = (Pol) dkf.getjComboBoxPol().getSelectedItem();
            String visinaStr = dkf.getjTextFieldVisina().getText().trim();
            String tezinaStr = dkf.getjTextFieldTezina().getText().trim();
            String bmrStr = dkf.getjTextFieldBMR().getText().trim();
            String brojTelefona = dkf.getjTextFieldBrojTelefona().getText().trim();

            Date datumRodjenja = new SimpleDateFormat("dd.MM.yyyy").parse(datumString);
            int brojGodina = Integer.parseInt(brojGodinaStr);
            double visina = Double.parseDouble(visinaStr);
            double tezina = Double.parseDouble(tezinaStr);
            double bmr = Double.parseDouble(bmrStr);

            Klijent k = new Klijent(-1, ime, prezime, datumRodjenja, brojGodina, pol, visina, tezina, bmr, brojTelefona);

            Komunikacija.getInstanca().dodajKlijenta(k);

            JOptionPane.showMessageDialog(dkf, "Klijent uspešno dodat.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
            dkf.dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(dkf, "Greška prilikom dodavanja klijenta.", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void izmeni(ActionEvent e) {
        try {
            int id = Integer.parseInt(dkf.getjTextFieldID().getText().trim());

            String ime = dkf.getjTextFieldIme().getText().trim();
            String prezime = dkf.getjTextFieldPrezime().getText().trim();
            String datumString = dkf.getjTextFieldDatumRodjenja().getText().trim();
            String brojGodinaStr = dkf.getjTextFieldBrojGodina().getText().trim();
            Pol pol = (Pol) dkf.getjComboBoxPol().getSelectedItem();
            String visinaStr = dkf.getjTextFieldVisina().getText().trim();
            String tezinaStr = dkf.getjTextFieldTezina().getText().trim();
            String bmrStr = dkf.getjTextFieldBMR().getText().trim();
            String brojTelefona = dkf.getjTextFieldBrojTelefona().getText().trim();

            Date datumRodjenja = new SimpleDateFormat("dd.MM.yyyy").parse(datumString);
            int brojGodina = Integer.parseInt(brojGodinaStr);
            double visina = Double.parseDouble(visinaStr);
            double tezina = Double.parseDouble(tezinaStr);
            double bmr = Double.parseDouble(bmrStr);

            Klijent k = new Klijent(id, ime, prezime, datumRodjenja, brojGodina, pol, visina, tezina, bmr, brojTelefona);

            Komunikacija.getInstanca().azurirajKlijenta(k);

            JOptionPane.showMessageDialog(dkf, "Klijent uspešno izmenjen.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
            dkf.dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(dkf, "Greška prilikom izmene klijenta.", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void pripremiFormu(FormaMod mod) {
        switch (mod) {
            case DODAJ:
                dkf.getjTextFieldID().setEnabled(false);
                dkf.getjButtonIzmeni().setVisible(false);
                dkf.getjButtonDodaj().setVisible(true);
                dkf.getjButtonDodaj().setEnabled(true);

                popuniPol();
                break;

            case IZMENI:
                dkf.getjButtonDodaj().setVisible(false);
                dkf.getjButtonIzmeni().setVisible(true);
                dkf.getjButtonIzmeni().setEnabled(true);

                popuniPol();

                Klijent k = (Klijent) Cordinator.getInstanca().vratiParam("klijent");
                dkf.getjTextFieldID().setText(String.valueOf(k.getIdKlijent()));
                dkf.getjTextFieldIme().setText(k.getIme());
                dkf.getjTextFieldPrezime().setText(k.getPrezime());
                dkf.getjTextFieldDatumRodjenja().setText(new SimpleDateFormat("dd.MM.yyyy").format(k.getDatumRodjenja()));
                dkf.getjTextFieldBrojGodina().setText(String.valueOf(k.getBrojGodina()));
                dkf.getjComboBoxPol().setSelectedItem(k.getPol());
                dkf.getjTextFieldVisina().setText(String.valueOf(k.getVisina()));
                dkf.getjTextFieldTezina().setText(String.valueOf(k.getTezina()));
                dkf.getjTextFieldBMR().setText(String.valueOf(k.getBMR()));
                dkf.getjTextFieldBrojTelefona().setText(k.getBrojTelefona());
                break;

            default:
                throw new AssertionError();
        }
    }

    private void popuniPol() {
        dkf.getjComboBoxPol().removeAllItems();
        dkf.getjComboBoxPol().addItem(Pol.MUSKI);
        dkf.getjComboBoxPol().addItem(Pol.ZENSKI);
         
    }
}
