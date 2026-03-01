package cordinator;

import domen.Trener;
import forme.DodajKlijentaForm;
import forme.DodajPlanTreningaForm;
import forme.DodajSamostalanTreningForm;
import forme.DodajTreneraForm;
import forme.DodajTreningSaTreneromForm;
import forme.DodajVezbuForm;
import forme.FormaMod;
import forme.GlavnaForma;
import forme.LoginForma;
import forme.PrikazKlijenataForm;
import forme.PrikazTreneraForm;
import forme.PrikazVezbiForm;

import java.util.HashMap;
import java.util.Map;

import kontroleri.DodajKlijentaController;
import kontroleri.DodajPlanTreningaController;
import kontroleri.DodajSamostalanTreningController;
import kontroleri.DodajTreneraController;
import kontroleri.DodajTreningSaTreneromController;
import kontroleri.DodajVezbuController;
import kontroleri.LoginController;
import kontroleri.PrikazKlijenataController;
import kontroleri.PrikazPlanovaTreningaController;
import kontroleri.PrikazTreneraController;
import kontroleri.PrikazVezbiController;

public class Cordinator {

    private static Cordinator instanca;

    private Trener ulogovani;
    private final Map<String, Object> parametri;

    private LoginController loginConroller;

    private PrikazKlijenataController pkController;
    private DodajKlijentaController dkController;

    private PrikazVezbiController pvController;
    private DodajVezbuController dvController;

    private DodajSamostalanTreningController dstController;
    private DodajTreningSaTreneromController tstController;

    private PrikazPlanovaTreningaController ppController;
    private DodajPlanTreningaController dpController;

    private PrikazTreneraController ptController;
    private DodajTreneraController dtController;

    // jedna jedina instanca glavne forme
    private GlavnaForma glavnaForma;

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

    public Trener getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(Trener ulogovani) {
        this.ulogovani = ulogovani;
        System.out.println("ULOGOVANI trener: " + ulogovani);
    }

    public void otvoriLoginFormu() {
        loginConroller = new LoginController(new LoginForma());
        dodajParam("aktivnaForma", "login");
        loginConroller.otvoriFormu();
    }

    public void otvoriGlavnuFormu() {
        if (glavnaForma == null) {
            glavnaForma = new GlavnaForma();
            ppController = new PrikazPlanovaTreningaController(glavnaForma);
        }

        dodajParam("aktivnaForma", "plan");
        ppController.otvoriFormu();
    }

    public void otvoriPrikazPlanovaTreningaFormu() {
        otvoriGlavnuFormu();
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

    public void otvoriDodajSamostalanTreningFormu() {
        dstController = new DodajSamostalanTreningController(new DodajSamostalanTreningForm());
        dstController.otvoriFormu(FormaMod.DODAJ);
    }

    public void otvoriIzmeniSamostalanTreningFormu() {
        dstController = new DodajSamostalanTreningController(new DodajSamostalanTreningForm());
        dstController.otvoriFormu(FormaMod.IZMENI);
    }

    public void otvoriDodajTreningSaTreneromFormu() {
        tstController = new DodajTreningSaTreneromController(new DodajTreningSaTreneromForm());
        tstController.otvoriFormu(FormaMod.DODAJ);
    }

    public void otvoriIzmeniTreningSaTreneromFormu() {
        tstController = new DodajTreningSaTreneromController(new DodajTreningSaTreneromForm());
        tstController.otvoriFormu(FormaMod.IZMENI);
    }

    public void otvoriDodajPlanTreningaFormu() {
        dpController = new DodajPlanTreningaController(new DodajPlanTreningaForm());
        dpController.otvoriFormu(FormaMod.DODAJ);
    }

    public void otvoriIzmeniPlanTreningaFormu() {
        dpController = new DodajPlanTreningaController(new DodajPlanTreningaForm());
        dpController.otvoriFormu(FormaMod.IZMENI);
    }

    public void otvoriPrikazTreneraFormu() {
        ptController = new PrikazTreneraController(new PrikazTreneraForm());
        dodajParam("aktivnaForma", "trener");
        ptController.otvoriFormu();
    }

    public void otvoriDodajTreneraFormu() {
        dtController = new DodajTreneraController(new DodajTreneraForm());
        dtController.otvoriFormu(FormaMod.DODAJ);
    }

    public void otvoriIzmeniTreneraFormu() {
        dtController = new DodajTreneraController(new DodajTreneraForm());
        dtController.otvoriFormu(FormaMod.IZMENI);
    }

    public void osveziFormu() {
        Object aktivnaForma = parametri.get("aktivnaForma");

        if ("klijent".equals(aktivnaForma) && pkController != null) {
            pkController.osveziFormu();
            pkController.osveziSamostalneTreninge();
            pkController.osveziTreningeSaTrenerom();
        } else if ("vezba".equals(aktivnaForma) && pvController != null) {
            pvController.osveziFormu();
        } else if ("plan".equals(aktivnaForma) && ppController != null) {
            ppController.osveziFormu();
        } else if ("trener".equals(aktivnaForma) && ptController != null) {
            ptController.osveziFormu();
        }
    }
}