package kontroleri;

import cordinator.Cordinator;
import domen.PlanTreninga;
import domen.StavkaPlanaTreninga;
import domen.Trener;
import domen.Klijent;
import domen.Vezba;
import forme.DodajPlanTreningaForm;
import forme.FormaMod;
import forme.model.ModelTabeleStavkaPlanaTreninga;

import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

public class DodajPlanTreningaController {

    private final DodajPlanTreningaForm dpf;

    public DodajPlanTreningaController(DodajPlanTreningaForm dpf) {
        this.dpf = dpf;
        addActionListener();
    }

    public void otvoriFormu(FormaMod mod) {
        pripremiFormu(mod);
        dpf.setVisible(true);
    }

    private void pripremiFormu(FormaMod mod) {
        // tabela stavki
        dpf.getjTableStavke().setModel(new ModelTabeleStavkaPlanaTreninga(new java.util.ArrayList<>()));

        // popuni combo-e iz baze
        popuniVezbe();
        popuniKlijente();

        switch (mod) {
            case DODAJ:
                dpf.getjTextFieldID().setEnabled(false);
                dpf.getjButtonIzmeni().setVisible(false);
                dpf.getjButtonDodaj().setVisible(true);

                // trener = ulogovani
                Trener ulogovani = Cordinator.getInstanca().getUlogovani();
                if (ulogovani != null) {
                    dpf.getjTextFieldTrener().setText(ulogovani.getIme() + " " + ulogovani.getPrezime());
                }

                // u DODAJ modu: nema više prosleđivanja klijenta kroz Cordinator,
                // korisnik bira klijenta iz combo box-a
                // (ako želiš da bude prazno dok ne izabere)
                // dpf.getjComboBoxKlijenti().setSelectedIndex(-1);

                break;

            case IZMENI:
                dpf.getjButtonDodaj().setVisible(false);
                dpf.getjButtonIzmeni().setVisible(true);

                PlanTreninga p = (PlanTreninga) Cordinator.getInstanca().vratiParam("planTreninga");
                if (p == null) {
                    JOptionPane.showMessageDialog(dpf, "Nije prosleđen plan treninga.", "Greška", JOptionPane.ERROR_MESSAGE);
                    dpf.dispose();
                    return;
                }

                dpf.getjTextFieldID().setText(String.valueOf(p.getIdPlanTreninga()));
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                dpf.getjTextFieldDatumPocetka().setText(p.getDatumPocetka() != null ? sdf.format(p.getDatumPocetka()) : "");
                dpf.getjTextFieldDatumKraja().setText(p.getDatumKraja() != null ? sdf.format(p.getDatumKraja()) : "");
                dpf.getjTextFieldTreningaNedeljno().setText(String.valueOf(p.getBrojTreningaNedeljno()));
                dpf.getjTextFieldUkupnoVezbi().setText(String.valueOf(p.getUkupanBrojVezbi()));
                dpf.getjTextFieldFaktorAktivnosti().setText(String.valueOf(p.getFaktorAktivnosti()));
                dpf.getjTextFieldKalorije().setText(String.valueOf(p.getDnevniUnosKalorija()));

                if (p.getTrener() != null) {
                    dpf.getjTextFieldTrener().setText(p.getTrener().getIme() + " " + p.getTrener().getPrezime());
                }

                // u IZMENI modu: selektuj klijenta u combobox-u
                if (p.getKlijent() != null) {
                    selektujKlijentaUCombo(p.getKlijent());
                }

                ModelTabeleStavkaPlanaTreninga mts =
                        (ModelTabeleStavkaPlanaTreninga) dpf.getjTableStavke().getModel();
                mts.setLista(p.getStavke());

                break;

            default:
                throw new AssertionError();
        }
    }

    private void addActionListener() {

        dpf.addBtnDodajStavkuActionListener(e -> dodajStavku());
        dpf.addBtnObrisiStavkuActionListener(e -> obrisiStavku());

        dpf.dodajAddActionListener(e -> sacuvajDodavanje(e));
        dpf.izmeniAddActionListener(e -> sacuvajIzmenu(e));
    }

    private void dodajStavku() {
        try {
            Vezba v = (Vezba) dpf.getjComboBoxVezbe().getSelectedItem();
            if (v == null) {
                JOptionPane.showMessageDialog(dpf, "Morate izabrati vežbu.", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int serije = Integer.parseInt(dpf.getjTextFieldSerije().getText().trim());
            int pon = Integer.parseInt(dpf.getjTextFieldPonavljanja().getText().trim());
            String napomena = dpf.getjTextFieldNapomena().getText().trim();

            StavkaPlanaTreninga s = new StavkaPlanaTreninga();
            s.setVezba(v);
            s.setBrojSerija(serije);
            s.setBrojPonavljanja(pon);
            s.setNapomena(napomena);

            ModelTabeleStavkaPlanaTreninga mts =
                    (ModelTabeleStavkaPlanaTreninga) dpf.getjTableStavke().getModel();
            mts.dodaj(s);

            dpf.getjTextFieldSerije().setText("");
            dpf.getjTextFieldPonavljanja().setText("");
            dpf.getjTextFieldNapomena().setText("");

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(dpf, "Greška prilikom dodavanja stavke.", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void obrisiStavku() {
        int red = dpf.getjTableStavke().getSelectedRow();
        if (red == -1) {
            JOptionPane.showMessageDialog(dpf, "Morate izabrati stavku za brisanje.", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ModelTabeleStavkaPlanaTreninga mts =
                (ModelTabeleStavkaPlanaTreninga) dpf.getjTableStavke().getModel();
        mts.obrisi(red);
    }

    private void sacuvajDodavanje(ActionEvent e) {
        try {
            PlanTreninga plan = pokupiPlanSaForme(false);

            int noviId = Komunikacija.getInstanca().dodajPlanTreninga(plan);

            JOptionPane.showMessageDialog(dpf, "Plan treninga uspešno dodat. ID=" + noviId,
                    "Uspeh", JOptionPane.INFORMATION_MESSAGE);
            dpf.dispose();

            Cordinator.getInstanca().osveziFormu();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(dpf, ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void sacuvajIzmenu(ActionEvent e) {
        try {
            PlanTreninga plan = pokupiPlanSaForme(true);

            Komunikacija.getInstanca().azurirajPlanTreninga(plan);

            JOptionPane.showMessageDialog(dpf, "Plan treninga uspešno izmenjen.",
                    "Uspeh", JOptionPane.INFORMATION_MESSAGE);
            dpf.dispose();

            Cordinator.getInstanca().osveziFormu();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(dpf, ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    private PlanTreninga pokupiPlanSaForme(boolean izmena) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        int id = izmena ? Integer.parseInt(dpf.getjTextFieldID().getText().trim()) : -1;

        Date dp = sdf.parse(dpf.getjTextFieldDatumPocetka().getText().trim());
        Date dk = sdf.parse(dpf.getjTextFieldDatumKraja().getText().trim());

        int brNed = Integer.parseInt(dpf.getjTextFieldTreningaNedeljno().getText().trim());
        int ukupnoVezbi = Integer.parseInt(dpf.getjTextFieldUkupnoVezbi().getText().trim());
        double faktor = Double.parseDouble(dpf.getjTextFieldFaktorAktivnosti().getText().trim());
        int kalorije = Integer.parseInt(dpf.getjTextFieldKalorije().getText().trim());

        Trener trener = Cordinator.getInstanca().getUlogovani();
        if (trener == null) throw new Exception("Nije ulogovan trener.");

        // Klijent se sada bira iz combo box-a (i u dodavanju i u izmeni)
        Klijent klijent = (Klijent) dpf.getjComboBoxKlijenti().getSelectedItem();
        if (klijent == null) throw new Exception("Morate izabrati klijenta.");

        ModelTabeleStavkaPlanaTreninga mts =
                (ModelTabeleStavkaPlanaTreninga) dpf.getjTableStavke().getModel();

        PlanTreninga p = new PlanTreninga();
        p.setIdPlanTreninga(id);
        p.setDatumPocetka(dp);
        p.setDatumKraja(dk);
        p.setBrojTreningaNedeljno(brNed);
        p.setUkupanBrojVezbi(ukupnoVezbi);
        p.setFaktorAktivnosti(faktor);
        p.setDnevniUnosKalorija(kalorije);
        p.setTrener(trener);
        p.setKlijent(klijent);
        p.setStavke(mts.getLista());

        return p;
    }

    private void popuniVezbe() {
        dpf.getjComboBoxVezbe().removeAllItems();
        List<Vezba> vezbe = Komunikacija.getInstanca().ucitajVezbe();
        for (Vezba v : vezbe) {
            dpf.getjComboBoxVezbe().addItem(v);
        }
    }

    private void popuniKlijente() {
        dpf.getjComboBoxKlijenti().removeAllItems();
        List<Klijent> klijenti = Komunikacija.getInstanca().ucitajKlijente();
        for (Klijent k : klijenti) {
            dpf.getjComboBoxKlijenti().addItem(k);
        }
        dpf.getjComboBoxKlijenti().setSelectedIndex(-1);
    }

    private void selektujKlijentaUCombo(Klijent target) {
        for (int i = 0; i < dpf.getjComboBoxKlijenti().getItemCount(); i++) {
            Klijent k = dpf.getjComboBoxKlijenti().getItemAt(i);
            if (k != null && target != null && k.getIdKlijent() == target.getIdKlijent()) {
                dpf.getjComboBoxKlijenti().setSelectedIndex(i);
                return;
            }
        }
        // Ako ga nema u listi (ne bi trebalo), ostavi prazno
        dpf.getjComboBoxKlijenti().setSelectedIndex(-1);
    }
}