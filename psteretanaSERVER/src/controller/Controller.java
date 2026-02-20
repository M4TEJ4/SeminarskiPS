/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Trener;
import operacije.login.LoginOperacija;
import domen.Klijent;
import domen.PlanTreninga;
import domen.SamostalanTrening;
import java.util.List;
import operacije.klijenti.AzurirajKlijentaSO;
import operacije.klijenti.DodajKlijentaSO;
import operacije.klijenti.ObrisiKlijentaSO;
import operacije.klijenti.UcitajKlijenteSO;
import domen.Trener;
import domen.TreningSaTrenerom;
import java.util.List; 
import domen.Vezba;
import java.util.List;
import operacije.plantreninga.AzurirajPlanTreningaSO;
import operacije.plantreninga.DodajPlanTreningaSO;
import operacije.plantreninga.ObrisiPlanTreningaSO;
import operacije.plantreninga.UcitajPlanoveTreningaSO;
import operacije.samostalnitrening.AzurirajSamostalanTreningSO;
import operacije.samostalnitrening.DodajSamostalanTreningSO;
import operacije.samostalnitrening.ObrisiSamostalanTreningSO;
import operacije.samostalnitrening.UcitajSamostalneTreningeSO;
import operacije.treningsatrenerom.AzurirajTreningSaTreneromSO;
import operacije.treningsatrenerom.DodajTreningSaTreneromSO;
import operacije.treningsatrenerom.ObrisiTreningSaTreneromSO;
import operacije.treningsatrenerom.UcitajTreningeSaTreneromSO;
import operacije.vezbe.AzurirajVezbuSO;
import operacije.vezbe.DodajVezbuSO;
import operacije.vezbe.ObrisiVezbuSO;
import operacije.vezbe.UcitajVezbeSO;
public class Controller {
    private static  Controller instance;

    private Controller() {
    }
    public static Controller getInstance (){
             if(instance==null){
                 instance=new Controller();
             }
             return instance;
     }

    public Trener login(Trener t) throws Exception {
        LoginOperacija operacija = new LoginOperacija();
        operacija.izvrsi(t, null);

        System.out.println("KLASA CONTROLLER: " + operacija.getTrener());
        return operacija.getTrener(); // može biti null ako je neuspešan login
    }
    public List<Klijent> ucitajKlijente() throws Exception {
        UcitajKlijenteSO operacija = new UcitajKlijenteSO();
        operacija.izvrsi(null, null);
        return operacija.getKlijenti();
    }

    public void dodajKlijenta(Klijent k) throws Exception {
        DodajKlijentaSO operacija = new DodajKlijentaSO();
        operacija.izvrsi(k, null);
    }

    public void azurirajKlijenta(Klijent k) throws Exception {
        AzurirajKlijentaSO operacija = new AzurirajKlijentaSO();
        operacija.izvrsi(k, null);
    }

        public void obrisiKlijenta(Klijent k) throws Exception {
            ObrisiKlijentaSO operacija = new ObrisiKlijentaSO();
            operacija.izvrsi(k, null);
        }
        public List<Vezba> ucitajVezbe() throws Exception {
           UcitajVezbeSO operacija = new UcitajVezbeSO();
           operacija.izvrsi(null, null);
           return operacija.getVezbe();
       }

       public void dodajVezbu(Vezba v) throws Exception {
           DodajVezbuSO operacija = new DodajVezbuSO();
           operacija.izvrsi(v, null);
       }

       public void azurirajVezbu(Vezba v) throws Exception {
           AzurirajVezbuSO operacija = new AzurirajVezbuSO();
           operacija.izvrsi(v, null);
       }

       public void obrisiVezbu(Vezba v) throws Exception {
           ObrisiVezbuSO operacija = new ObrisiVezbuSO();
           operacija.izvrsi(v, null);
       }
       public List<SamostalanTrening> ucitajSamostalneTreninge() throws Exception {
           UcitajSamostalneTreningeSO operacija = new UcitajSamostalneTreningeSO();
        operacija.izvrsi(null, null);
        return operacija.getTreninzi();
    }

    public void dodajSamostalanTrening(SamostalanTrening s) throws Exception {
        DodajSamostalanTreningSO operacija = new DodajSamostalanTreningSO();
        operacija.izvrsi(s, null);
    }

    public void azurirajSamostalanTrening(SamostalanTrening s) throws Exception {
        AzurirajSamostalanTreningSO operacija = new AzurirajSamostalanTreningSO();
        operacija.izvrsi(s, null);
    }

    public void obrisiSamostalanTrening(SamostalanTrening s) throws Exception {
        ObrisiSamostalanTreningSO operacija = new ObrisiSamostalanTreningSO();
        operacija.izvrsi(s, null);
    }
        public List<TreningSaTrenerom> ucitajTreningeSaTrenerom() throws Exception {
            UcitajTreningeSaTreneromSO operacija = new UcitajTreningeSaTreneromSO();
            operacija.izvrsi(null, null);
            return operacija.getTreninzi();
        }

        public void dodajTreningSaTrenerom(TreningSaTrenerom t) throws Exception {
            DodajTreningSaTreneromSO operacija = new DodajTreningSaTreneromSO();
            operacija.izvrsi(t, null);
        }

        public void azurirajTreningSaTrenerom(TreningSaTrenerom t) throws Exception {
            AzurirajTreningSaTreneromSO operacija = new AzurirajTreningSaTreneromSO();
            operacija.izvrsi(t, null);
        }

        public void obrisiTreningSaTrenerom(TreningSaTrenerom t) throws Exception {
            ObrisiTreningSaTreneromSO operacija = new ObrisiTreningSaTreneromSO();
            operacija.izvrsi(t, null);
        }
        public List<PlanTreninga> ucitajPlanoveTreninga(String kljuc) throws Exception {
            UcitajPlanoveTreningaSO operacija = new UcitajPlanoveTreningaSO();
            operacija.izvrsi(null, kljuc);
            return operacija.getPlanovi();
        }

        public int dodajPlanTreninga(PlanTreninga p) throws Exception {
            DodajPlanTreningaSO operacija = new DodajPlanTreningaSO();
            operacija.izvrsi(p, null);
            return operacija.getNoviPlanId();
        }

        public void azurirajPlanTreninga(PlanTreninga p) throws Exception {
            AzurirajPlanTreningaSO operacija = new AzurirajPlanTreningaSO();
            operacija.izvrsi(p, null);
        }

        public void obrisiPlanTreninga(PlanTreninga p) throws Exception {
            ObrisiPlanTreningaSO operacija = new ObrisiPlanTreningaSO();
            operacija.izvrsi(p, null);
        }
}
