/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import controller.Controller;
import domen.Klijent;
import domen.Trener;
 
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.Odgovor;
import komunikacija.Posiljalac;
import komunikacija.Primalac;
import komunikacija.Zahtev;

/**
 *
 * @author student
 */
public class ObradaKlijentskihZahteva extends Thread {
    Socket socket;
    Posiljalac posiljalac;
    Primalac primalac;
    boolean kraj=false;
    public ObradaKlijentskihZahteva(Socket socket) {
        this.socket=socket;
        posiljalac = new Posiljalac(socket);
        primalac= new Primalac(socket);
    }
 
    @Override
    public void run() {
        while(!kraj){
            try {
            Zahtev zahtev = (Zahtev) primalac.primi();
            Odgovor odgovor = new Odgovor();
            switch (zahtev.getOperacija()) {
            case LOGIN:
                Trener t = (Trener) zahtev.getParametar();
                t = Controller.getInstance().login(t);
                odgovor.setOdgovor(t);
                break;
            case UCITAJ_KLIJENTE:
                List<Klijent> klijenti = Controller.getInstance().ucitajKlijente();
                odgovor.setOdgovor(klijenti);
                break;

            case DODAJ_KLIJENTA:
                Klijent noviKlijent = (Klijent) zahtev.getParametar();
                Controller.getInstance().dodajKlijenta(noviKlijent);
                odgovor.setOdgovor(null);
                break;

            case AZURIRAJ_KLIJENTA:
                Klijent izmenjeniKlijent = (Klijent) zahtev.getParametar();
                Controller.getInstance().azurirajKlijenta(izmenjeniKlijent);
                odgovor.setOdgovor(null);
                break;

            case OBRISI_KLIJENTA:
                try {
                    Klijent klijentZaBrisanje = (Klijent) zahtev.getParametar();
                    Controller.getInstance().obrisiKlijenta(klijentZaBrisanje);
                    odgovor.setOdgovor(null);
                } catch (Exception e) {
                    odgovor.setOdgovor(e);
                }
                break;

                    

                default:
                    System.out.println("GRESKA, TA OPERACIJA NE POSTOJI");
                    
                    
                    
            }
            posiljalac.posalji(odgovor);
             } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
        } 
    }
    public void prekini(){
        kraj=true;
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        interrupt();
    }
    
    

}
