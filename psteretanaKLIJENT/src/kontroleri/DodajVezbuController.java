package kontroleri;

import cordinator.Cordinator;
import domen.Vezba;
import forme.DodajVezbuForm;
import forme.FormaMod;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

public class DodajVezbuController {

    private final DodajVezbuForm dvf;

    public DodajVezbuController(DodajVezbuForm dvf) {
        this.dvf = dvf;
        addActionListener();
    }

    public void otvoriFormu(FormaMod mod) {
        pripremiFormu(mod);
        dvf.setVisible(true);
    }

    private void addActionListener() {

        dvf.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }
        });

        dvf.izmeniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni(e);
            }
        });
    }

    private void dodaj(ActionEvent e) {
        try {
            String naziv = dvf.getjTextFieldNaziv().getText().trim();
            String grupaMisica = dvf.getjTextFieldGrupaMisica().getText().trim();
            String oprema = dvf.getjTextFieldOprema().getText().trim();

            Vezba v = new Vezba(-1, naziv, grupaMisica, oprema);

            Komunikacija.getInstanca().dodajVezbu(v);

            JOptionPane.showMessageDialog(dvf, "Vežba uspešno dodata.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
            dvf.dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(dvf, "Greška prilikom dodavanja vežbe.", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void izmeni(ActionEvent e) {
        try {
            int id = Integer.parseInt(dvf.getjTextFieldID().getText().trim());

            String naziv = dvf.getjTextFieldNaziv().getText().trim();
            String grupaMisica = dvf.getjTextFieldGrupaMisica().getText().trim();
            String oprema = dvf.getjTextFieldOprema().getText().trim();

            Vezba v = new Vezba(id, naziv, grupaMisica, oprema);

            Komunikacija.getInstanca().azurirajVezbu(v);

            JOptionPane.showMessageDialog(dvf, "Vežba uspešno izmenjena.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
            dvf.dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(dvf, "Greška prilikom izmene vežbe.", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void pripremiFormu(FormaMod mod) {
        switch (mod) {
            case DODAJ:
                dvf.getjTextFieldID().setEnabled(false);
                dvf.getjButtonAzuriraj().setVisible(false);
                dvf.getjButtonDodaj().setVisible(true);
                dvf.getjButtonDodaj().setEnabled(true);
                break;

            case IZMENI:
                dvf.getjButtonDodaj().setVisible(false);
                dvf.getjButtonAzuriraj().setVisible(true);
                dvf.getjButtonAzuriraj().setEnabled(true);

                Vezba v = (Vezba) Cordinator.getInstanca().vratiParam("vezba");
                dvf.getjTextFieldID().setText(String.valueOf(v.getIdVezba()));
                dvf.getjTextFieldNaziv().setText(v.getNaziv());
                dvf.getjTextFieldGrupaMisica().setText(v.getGrupaMisica());
                dvf.getjTextFieldOprema().setText(v.getOprema());
                break;

            default:
                throw new AssertionError();
        }
    }
}
