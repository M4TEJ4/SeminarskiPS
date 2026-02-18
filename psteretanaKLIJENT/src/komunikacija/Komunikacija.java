/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

  
import domen.Klijent;
import domen.Trener;
import java.io.IOException;
import java.net.Socket; 
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author student
 */
public class Komunikacija {
     private Socket soket;
    private Posiljalac posiljalac;
    private Primalac primalac;
     private static Komunikacija instanca;
    private Komunikacija() {
    }   
     
    public static Komunikacija getInstanca() {
        if (instanca == null) {
            instanca = new Komunikacija();
        }
        return instanca;
    }

      public void konekcija()  {
         try {
             soket = new Socket("localhost", 9000);
             posiljalac = new Posiljalac(soket);
             primalac = new Primalac(soket);
         } catch (IOException ex) {
             System.out.println("SERVER NIJE POVEZAN");
         }
         
      }

    public Trener login(String korisnickoIme, String lozinka) throws Exception {
        Trener t = new Trener();
        t.setKorisnickoIme(korisnickoIme);
        t.setLozinka(lozinka);

        Zahtev z = new Zahtev(Operacija.LOGIN, t);
        posiljalac.posalji(z);

        Odgovor o = (Odgovor) primalac.primi();
        Object odgovor = o.getOdgovor();

        if (odgovor instanceof Exception) {
            throw (Exception) odgovor;
        }

        return (Trener) odgovor; // može biti null
    }
    public void dodajKlijenta(Klijent k) {
       Zahtev zahtev = new Zahtev(Operacija.DODAJ_KLIJENTA, k);
       posiljalac.posalji(zahtev);

       Odgovor odg = (Odgovor) primalac.primi();

       if (odg.getOdgovor() == null) {
           System.out.println("USPEH");
       } else {
           System.out.println("GRESKA");
           ((Exception) odg.getOdgovor()).printStackTrace();
       }
   }

    public void azurirajKlijenta(Klijent k) {
        Zahtev zahtev = new Zahtev(Operacija.AZURIRAJ_KLIJENTA, k);
        posiljalac.posalji(zahtev);

        Odgovor odg = (Odgovor) primalac.primi();

        if (odg.getOdgovor() == null) {
            System.out.println("USPEH");
            cordinator.Cordinator.getInstanca().osveziFormu();
        } else {
            System.out.println("GRESKA");
            ((Exception) odg.getOdgovor()).printStackTrace();
        }
    }

    public void obrisiKlijenta(Klijent k) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_KLIJENTA, k);
        posiljalac.posalji(zahtev);

        Odgovor odg = (Odgovor) primalac.primi();

        if (odg.getOdgovor() == null) {
            System.out.println("USPEH");
        } else {
            System.out.println("GRESKA");
            ((Exception) odg.getOdgovor()).printStackTrace();
            throw new Exception("Sistem ne može da obriše klijenta.");
        }
    }

    public List<Klijent> ucitajKlijente() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_KLIJENTE, null);
        List<Klijent> klijenti = new ArrayList<>();

        posiljalac.posalji(zahtev);

        Odgovor odg = (Odgovor) primalac.primi();
        klijenti = (List<Klijent>) odg.getOdgovor();

        return klijenti;
    }

    

}
