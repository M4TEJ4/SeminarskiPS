package cordinator;

import domen.Trener;
import forme.DodajKlijentaForm;
import forme.DodajVezbuForm;
import forme.DodajSamostalanTreningForm;
import forme.FormaMod;
import forme.GlavnaForma;
import forme.LoginForma;
import forme.PrikazKlijenataForm;
import forme.PrikazVezbiForm;
import java.util.HashMap;
import java.util.Map;
import kontroleri.DodajKlijentaController;
import kontroleri.DodajVezbuController;
import kontroleri.DodajSamostalanTreningController;
import kontroleri.GlavnaFormaController;
import kontroleri.LoginController;
import kontroleri.PrikazKlijenataController;
import kontroleri.PrikazVezbiController;

public class Cordinator {

    private static Cordinator instanca;

    private Trener ulogovani;
    private final Map<String, Object> parametri;

    private GlavnaFormaController glavnaFormaController;
    private LoginController loginConroller;

    private PrikazKlijenataController pkController;
    private DodajKlijentaController dkController;

    private PrikazVezbiController pvController;
    private DodajVezbuController dvController;

    // NOVO
    private DodajSamostalanTreningController dstController;

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

    public void otvoriPrikazKlijenataFormu() {
        pkController = new PrikazKlijenataController(new PrikazKlijenataForm());
        dodajParam("aktivnaForma", "klijent");
        pkController.otvoriFormu();
    }

    public void otvoriDodajKlijentaFormu() {
        dkController = new DodajKlijentaController(new DodajKlijentaForm());
        dkController.otvoriFormu(FormaMod.DODAJ);
    }

    public void otvoriIzmeniKlijentaFormu() {
        dkController = new DodajKlijentaController(new DodajKlijentaForm());
        dkController.otvoriFormu(FormaMod.IZMENI);
    }

    public void otvoriPrikazVezbiFormu() {
        pvController = new PrikazVezbiController(new PrikazVezbiForm());
        dodajParam("aktivnaForma", "vezba");
        pvController.otvoriFormu();
    }

    public void otvoriDodajVezbuFormu() {
        dvController = new DodajVezbuController(new DodajVezbuForm());
        dvController.otvoriFormu(FormaMod.DODAJ);
    }

    public void otvoriIzmeniVezbuFormu() {
        dvController = new DodajVezbuController(new DodajVezbuForm());
        dvController.otvoriFormu(FormaMod.IZMENI);
    }

    // NOVO: SamostalanTrening forma
    public void otvoriDodajSamostalanTreningFormu() {
        dstController = new DodajSamostalanTreningController(new DodajSamostalanTreningForm());
        dstController.otvoriFormu(FormaMod.DODAJ);
    }

    public void otvoriIzmeniSamostalanTreningFormu() {
        dstController = new DodajSamostalanTreningController(new DodajSamostalanTreningForm());
        dstController.otvoriFormu(FormaMod.IZMENI);
    }

    public void osveziFormu() {
        Object aktivnaForma = parametri.get("aktivnaForma");

        if ("klijent".equals(aktivnaForma) && pkController != null) {
            pkController.osveziFormu();
            pkController.osveziSamostalneTreninge(); // NOVO
        } else if ("vezba".equals(aktivnaForma) && pvController != null) {
            pvController.osveziFormu();
        }
    }
}
