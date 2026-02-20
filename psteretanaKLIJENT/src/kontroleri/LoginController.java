package kontroleri;

import cordinator.Cordinator;
import domen.Trener;
import forme.LoginForma;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import komunikacija.Komunikacija;

public class LoginController {

    private final LoginForma lf;

    public LoginController(LoginForma lf) {
        this.lf = lf;
        addActionListeners();
    }

    private void addActionListeners() {
        lf.loginAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prijava(e);
            }
        });
    }

    private void prijava(ActionEvent e) {
        String ki = lf.getjTextFieldUsername().getText().trim();
        String pass = String.valueOf(lf.getjPasswordField1().getPassword());

        if (ki.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(lf, "Unesi korisničko ime i lozinku.", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Komunikacija.getInstanca().konekcija();

            Trener ulogovani = Komunikacija.getInstanca().login(ki, pass);

            if (ulogovani == null) {
                JOptionPane.showMessageDialog(lf, "Prijava na sistem neuspešna.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
            } else {
                Cordinator.getInstanca().setUlogovani(ulogovani);
                JOptionPane.showMessageDialog(lf, "Prijava na sistem uspešna.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);

                Cordinator.getInstanca().otvoriPrikazPlanovaTreningaFormu();
                lf.dispose();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(lf, "Greška pri prijavi: " + ex.getMessage(), "GREŠKA", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void otvoriFormu() {
        lf.setVisible(true);
    }
}
