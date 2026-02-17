package cordinator;

import domen.Trener;
import forme.GlavnaForma;
import forme.LoginForma;
import java.util.HashMap;
import java.util.Map;
import kontroleri.GlavnaFormaController;
import kontroleri.LoginController;

public class Cordinator {

    private static Cordinator instanca;

    private Trener ulogovani;
    private final Map<String, Object> parametri;

    private GlavnaFormaController glavnaFormaController;
    private LoginController loginConroller;

    private Cordinator() {
        parametri = new HashMap<>();
    }

    public static Cordinator getInstanca() {
        if (instanca == null) instanca = new Cordinator();
        return instanca;
    }

    public void dodajParam(String s, Object o) {
        parametri.put(s, o);
    }

    public Object vratiParam(String s) {
        return parametri.get(s);
    }

    public void otvoriGlavnuFormu() {
        glavnaFormaController = new GlavnaFormaController(new GlavnaForma());
        dodajParam("aktivnaForma", "glavna");
        glavnaFormaController.otvoriFormu();
    }

    public void otvoriLoginFormu() {
        loginConroller = new LoginController(new LoginForma());
        dodajParam("aktivnaForma", "login");
        loginConroller.otvoriFormu();
    }

    public Trener getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(Trener ulogovani) {
        this.ulogovani = ulogovani;
        System.out.println("ULOGOVANI trener: " + ulogovani);
    }
}
