package kontroleri;

import cordinator.Cordinator;
import domen.Trener;
import forme.DodajTreneraForm;
import forme.FormaMod;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DodajTreneraController {

    private final DodajTreneraForm dtf;

    public DodajTreneraController(DodajTreneraForm dtf) {
        this.dtf = dtf;
        addActionListener();
    }

    public void otvoriFormu(FormaMod mod) {
        pripremiFormu(mod);
        dtf.setVisible(true);
    }

    private void addActionListener() {

        dtf.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }
        });

        dtf.izmeniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni(e);
            }
        });
    }

    private void dodaj(ActionEvent e) {
        try {
            String ime = dtf.getjTextFieldIme().getText().trim();
            String prezime = dtf.getjTextFieldPrezime().getText().trim();
            String korisnickoIme = dtf.getjTextFieldKorisnickoIme().getText().trim();
            String lozinka = dtf.getjTextFieldLozinka().getText().trim();

            Trener t = new Trener(-1, ime, prezime, korisnickoIme, lozinka);

            Komunikacija.getInstanca().dodajTrenera(t);

            JOptionPane.showMessageDialog(dtf, "Trener uspešno dodat.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
            dtf.dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(dtf, "Greška prilikom dodavanja trenera.", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void izmeni(ActionEvent e) {
        try {
            int id = Integer.parseInt(dtf.getjTextFieldID().getText().trim());

            String ime = dtf.getjTextFieldIme().getText().trim();
            String prezime = dtf.getjTextFieldPrezime().getText().trim();
            String korisnickoIme = dtf.getjTextFieldKorisnickoIme().getText().trim();
            String lozinka = dtf.getjTextFieldLozinka().getText().trim();

            Trener t = new Trener(id, ime, prezime, korisnickoIme, lozinka);

            Komunikacija.getInstanca().azurirajTrenera(t);

            JOptionPane.showMessageDialog(dtf, "Trener uspešno izmenjen.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
            dtf.dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(dtf, "Greška prilikom izmene trenera.", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void pripremiFormu(FormaMod mod) {
        switch (mod) {
            case DODAJ:
                dtf.getjTextFieldID().setEnabled(false);
                dtf.getjButtonIzmeni().setVisible(false);
                dtf.getjButtonDodaj().setVisible(true);
                dtf.getjButtonDodaj().setEnabled(true);

                dtf.getjTextFieldID().setText("");
                dtf.getjTextFieldIme().setText("");
                dtf.getjTextFieldPrezime().setText("");
                dtf.getjTextFieldKorisnickoIme().setText("");
                dtf.getjTextFieldLozinka().setText("");
                break;

            case IZMENI:
                dtf.getjButtonDodaj().setVisible(false);
                dtf.getjButtonIzmeni().setVisible(true);
                dtf.getjButtonIzmeni().setEnabled(true);

                Trener t = (Trener) Cordinator.getInstanca().vratiParam("trener");

                dtf.getjTextFieldID().setEnabled(false);
                dtf.getjTextFieldID().setText(String.valueOf(t.getIdTrener()));
                dtf.getjTextFieldIme().setText(t.getIme());
                dtf.getjTextFieldPrezime().setText(t.getPrezime());
                dtf.getjTextFieldKorisnickoIme().setText(t.getKorisnickoIme());
                dtf.getjTextFieldLozinka().setText(t.getLozinka());
                break;

            default:
                throw new AssertionError();
        }
    }
}