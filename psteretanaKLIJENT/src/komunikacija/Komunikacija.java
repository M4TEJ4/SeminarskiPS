/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

  
import domen.Trener;
import java.io.IOException;
import java.net.Socket; 

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
 
    

}
