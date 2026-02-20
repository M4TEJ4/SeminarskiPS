/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

  
import domen.Klijent;
import domen.PlanTreninga;
import domen.SamostalanTrening;
import domen.Trener;
import domen.TreningSaTrenerom;
import java.io.IOException;
import java.net.Socket; 
import java.util.ArrayList;
import java.util.List;
import domen.Vezba;
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
    public void dodajVezbu(Vezba v) {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_VEZBU, v);
        posiljalac.posalji(zahtev);

        Odgovor odg = (Odgovor) primalac.primi();

        if (odg.getOdgovor() == null) {
            System.out.println("USPEH");
        } else {
            System.out.println("GRESKA");
            ((Exception) odg.getOdgovor()).printStackTrace();
        }
    }

    public void azurirajVezbu(Vezba v) {
        Zahtev zahtev = new Zahtev(Operacija.AZURIRAJ_VEZBU, v);
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

    public void obrisiVezbu(Vezba v) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_VEZBU, v);
        posiljalac.posalji(zahtev);

        Odgovor odg = (Odgovor) primalac.primi();

        if (odg.getOdgovor() == null) {
            System.out.println("USPEH");
        } else {
            System.out.println("GRESKA");
            ((Exception) odg.getOdgovor()).printStackTrace();
            throw new Exception("Sistem ne može da obriše vežbu.");
        }
    }

    public List<Vezba> ucitajVezbe() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_VEZBE, null);
        List<Vezba> lista = new ArrayList<>();

        posiljalac.posalji(zahtev);

        Odgovor odg = (Odgovor) primalac.primi();
        lista = (List<Vezba>) odg.getOdgovor();

        return lista;
    }
public void dodajSamostalanTrening(SamostalanTrening s) {
    Zahtev zahtev = new Zahtev(Operacija.DODAJ_SAMOSTALNI_TRENING, s);
    posiljalac.posalji(zahtev);

    Odgovor odg = (Odgovor) primalac.primi();
    if (odg.getOdgovor() == null) {
        System.out.println("USPEH");
    } else {
        System.out.println("GRESKA");
        ((Exception) odg.getOdgovor()).printStackTrace();
    }
}

public void azurirajSamostalanTrening(SamostalanTrening s) {
    Zahtev zahtev = new Zahtev(Operacija.AZURIRAJ_SAMOSTALNI_TRENING, s);
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

public void obrisiSamostalanTrening(SamostalanTrening s) throws Exception {
    Zahtev zahtev = new Zahtev(Operacija.OBRISI_SAMOSTALNI_TRENING, s);
    posiljalac.posalji(zahtev);

    Odgovor odg = (Odgovor) primalac.primi();
    if (odg.getOdgovor() == null) {
        System.out.println("USPEH");
    } else {
        System.out.println("GRESKA");
        ((Exception) odg.getOdgovor()).printStackTrace();
        throw new Exception("Sistem ne može da obriše samostalan trening.");
    }
}

@SuppressWarnings("unchecked")
public List<SamostalanTrening> ucitajSamostalneTreninge() {
    Zahtev zahtev = new Zahtev(Operacija.UCITAJ_SAMOSTALNE_TRENINGE, null);
    List<SamostalanTrening> lista = new ArrayList<>();

    posiljalac.posalji(zahtev);

    Odgovor odg = (Odgovor) primalac.primi();
    lista = (List<SamostalanTrening>) odg.getOdgovor();

    return lista;
}
public void dodajTreningSaTrenerom(TreningSaTrenerom t) {
    Zahtev zahtev = new Zahtev(Operacija.DODAJ_TRENING_SA_TRENEROM, t);
    posiljalac.posalji(zahtev);

    Odgovor odg = (Odgovor) primalac.primi();
    if (odg.getOdgovor() == null) {
        System.out.println("USPEH");
    } else {
        System.out.println("GRESKA");
        ((Exception) odg.getOdgovor()).printStackTrace();
    }
}

public void azurirajTreningSaTrenerom(TreningSaTrenerom t) {
    Zahtev zahtev = new Zahtev(Operacija.AZURIRAJ_TRENING_SA_TRENEROM, t);
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

public void obrisiTreningSaTrenerom(TreningSaTrenerom t) throws Exception {
    Zahtev zahtev = new Zahtev(Operacija.OBRISI_TRENING_SA_TRENEROM, t);
    posiljalac.posalji(zahtev);

    Odgovor odg = (Odgovor) primalac.primi();
    if (odg.getOdgovor() == null) {
        System.out.println("USPEH");
    } else {
        System.out.println("GRESKA");
        ((Exception) odg.getOdgovor()).printStackTrace();
        throw new Exception("Sistem ne može da obriše trening sa trenerom.");
    }
}

@SuppressWarnings("unchecked")
public List<TreningSaTrenerom> ucitajTreningeSaTrenerom() {
    Zahtev zahtev = new Zahtev(Operacija.UCITAJ_TRENINZI_SA_TRENEROM, null);
    posiljalac.posalji(zahtev);

    Odgovor odg = (Odgovor) primalac.primi();
    return (List<TreningSaTrenerom>) odg.getOdgovor();
}

 

            @SuppressWarnings("unchecked")
            public List<PlanTreninga> ucitajPlanoveTreninga(String uslov) {
                Zahtev zahtev = new Zahtev(Operacija.UCITAJ_PLANOVE_TRENINGA, uslov);
                posiljalac.posalji(zahtev);

                Odgovor odg = (Odgovor) primalac.primi();
                return (List<PlanTreninga>) odg.getOdgovor();
            }

            public int dodajPlanTreninga(PlanTreninga p) throws Exception {
                Zahtev zahtev = new Zahtev(Operacija.DODAJ_PLAN_TRENINGA, p);
                posiljalac.posalji(zahtev);

                Odgovor odg = (Odgovor) primalac.primi();
                Object odgovor = odg.getOdgovor();

                if (odgovor instanceof Exception) {
                    ((Exception) odgovor).printStackTrace();
                    throw (Exception) odgovor;
                }

                // ocekujemo da server vrati int id
                return (int) odgovor;
            }

            public void azurirajPlanTreninga(PlanTreninga p) throws Exception {
                Zahtev zahtev = new Zahtev(Operacija.AZURIRAJ_PLAN_TRENINGA, p);
                posiljalac.posalji(zahtev);

                Odgovor odg = (Odgovor) primalac.primi();
                Object odgovor = odg.getOdgovor();

                if (odgovor == null) {
                    System.out.println("USPEH");
                    cordinator.Cordinator.getInstanca().osveziFormu();
                } else {
                    System.out.println("GRESKA");
                    ((Exception) odgovor).printStackTrace();
                    throw new Exception("Sistem ne može da ažurira plan treninga.");
                }
            }

            public void obrisiPlanTreninga(PlanTreninga p) throws Exception {
                Zahtev zahtev = new Zahtev(Operacija.OBRISI_PLAN_TRENINGA, p);
                posiljalac.posalji(zahtev);

                Odgovor odg = (Odgovor) primalac.primi();
                Object odgovor = odg.getOdgovor();

                if (odgovor == null) {
                    System.out.println("USPEH");
                } else {
                    System.out.println("GRESKA");
                    ((Exception) odgovor).printStackTrace();
                    throw new Exception("Sistem ne može da obriše plan treninga.");
                }
            }

}
