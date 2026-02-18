/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Trener;
import operacije.login.LoginOperacija;
import domen.Klijent;
import java.util.List;
import operacije.klijenti.AzurirajKlijentaSO;
import operacije.klijenti.DodajKlijentaSO;
import operacije.klijenti.ObrisiKlijentaSO;
import operacije.klijenti.UcitajKlijenteSO;
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

}
