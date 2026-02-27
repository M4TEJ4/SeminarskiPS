/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import controller.Controller;
import domen.Klijent;
import domen.PlanTreninga;
import domen.SamostalanTrening;
import domen.Trener;
import domen.TreningSaTrenerom;
import domen.Vezba;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
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

    private final Socket socket;
    private final Posiljalac posiljalac;
    private final Primalac primalac;
    private boolean kraj = false;

    public ObradaKlijentskihZahteva(Socket socket) {
        this.socket = socket;
        this.posiljalac = new Posiljalac(socket);
        this.primalac = new Primalac(socket);
    }

    @Override
    public void run() {
        while (!kraj) {
            try {
                Zahtev zahtev = (Zahtev) primalac.primi();
                Odgovor odgovor = new Odgovor();

                switch (zahtev.getOperacija()) {

                    case LOGIN: {
                        Trener t = (Trener) zahtev.getParametar();
                        t = Controller.getInstance().login(t);
                        odgovor.setOdgovor(t);
                        break;
                    }

                    case UCITAJ_KLIJENTE: {
                        List<Klijent> klijenti = Controller.getInstance().ucitajKlijente();
                        odgovor.setOdgovor(klijenti);
                        break;
                    }

                    case DODAJ_KLIJENTA: {
                        Klijent noviKlijent = (Klijent) zahtev.getParametar();
                        Controller.getInstance().dodajKlijenta(noviKlijent);
                        odgovor.setOdgovor(null);
                        break;
                    }

                    case AZURIRAJ_KLIJENTA: {
                        Klijent izmenjeniKlijent = (Klijent) zahtev.getParametar();
                        Controller.getInstance().azurirajKlijenta(izmenjeniKlijent);
                        odgovor.setOdgovor(null);
                        break;
                    }

                    case OBRISI_KLIJENTA: {
                        try {
                            Klijent klijentZaBrisanje = (Klijent) zahtev.getParametar();
                            Controller.getInstance().obrisiKlijenta(klijentZaBrisanje);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    }

                    case UCITAJ_VEZBE: {
                        List<Vezba> vezbe = Controller.getInstance().ucitajVezbe();
                        odgovor.setOdgovor(vezbe);
                        break;
                    }

                    case DODAJ_VEZBU: {
                        Vezba novaVezba = (Vezba) zahtev.getParametar();
                        Controller.getInstance().dodajVezbu(novaVezba);
                        odgovor.setOdgovor(null);
                        break;
                    }

                    case AZURIRAJ_VEZBU: {
                        Vezba izmenjenaVezba = (Vezba) zahtev.getParametar();
                        Controller.getInstance().azurirajVezbu(izmenjenaVezba);
                        odgovor.setOdgovor(null);
                        break;
                    }

                    case OBRISI_VEZBU: {
                        try {
                            Vezba vezbaZaBrisanje = (Vezba) zahtev.getParametar();
                            Controller.getInstance().obrisiVezbu(vezbaZaBrisanje);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    }

                    case UCITAJ_SAMOSTALNE_TRENINGE: {
                        List<SamostalanTrening> treninzi = Controller.getInstance().ucitajSamostalneTreninge();
                        odgovor.setOdgovor(treninzi);
                        break;
                    }

                    case DODAJ_SAMOSTALNI_TRENING: {
                        SamostalanTrening novi = (SamostalanTrening) zahtev.getParametar();
                        Controller.getInstance().dodajSamostalanTrening(novi);
                        odgovor.setOdgovor(null);
                        break;
                    }

                    case AZURIRAJ_SAMOSTALNI_TRENING: {
                        SamostalanTrening izmenjen = (SamostalanTrening) zahtev.getParametar();
                        Controller.getInstance().azurirajSamostalanTrening(izmenjen);
                        odgovor.setOdgovor(null);
                        break;
                    }

                    case OBRISI_SAMOSTALNI_TRENING: {
                        try {
                            SamostalanTrening zaBrisanje = (SamostalanTrening) zahtev.getParametar();
                            Controller.getInstance().obrisiSamostalanTrening(zaBrisanje);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    }

                    case UCITAJ_TRENINZI_SA_TRENEROM: {
                        List<TreningSaTrenerom> listaTST = Controller.getInstance().ucitajTreningeSaTrenerom();
                        odgovor.setOdgovor(listaTST);
                        break;
                    }

                    case DODAJ_TRENING_SA_TRENEROM: {
                        TreningSaTrenerom noviTST = (TreningSaTrenerom) zahtev.getParametar();
                        Controller.getInstance().dodajTreningSaTrenerom(noviTST);
                        odgovor.setOdgovor(null);
                        break;
                    }

                    case AZURIRAJ_TRENING_SA_TRENEROM: {
                        TreningSaTrenerom izmenjenTST = (TreningSaTrenerom) zahtev.getParametar();
                        Controller.getInstance().azurirajTreningSaTrenerom(izmenjenTST);
                        odgovor.setOdgovor(null);
                        break;
                    }

                    case OBRISI_TRENING_SA_TRENEROM: {
                        try {
                            TreningSaTrenerom zaBrisanjeTST = (TreningSaTrenerom) zahtev.getParametar();
                            Controller.getInstance().obrisiTreningSaTrenerom(zaBrisanjeTST);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    }

                    case UCITAJ_PLANOVE_TRENINGA: {
                        String uslov = (String) zahtev.getParametar();
                        List<PlanTreninga> planovi = Controller.getInstance().ucitajPlanoveTreninga(uslov);
                        odgovor.setOdgovor(planovi);
                        break;
                    }

                    case DODAJ_PLAN_TRENINGA: {
                        PlanTreninga noviPlan = (PlanTreninga) zahtev.getParametar();
                        int noviId = Controller.getInstance().dodajPlanTreninga(noviPlan);
                        odgovor.setOdgovor(noviId);
                        break;
                    }

                    case AZURIRAJ_PLAN_TRENINGA: {
                        PlanTreninga izmenjenPlan = (PlanTreninga) zahtev.getParametar();
                        Controller.getInstance().azurirajPlanTreninga(izmenjenPlan);
                        odgovor.setOdgovor(null);
                        break;
                    }

                    case OBRISI_PLAN_TRENINGA: {
                        try {
                            PlanTreninga planZaBrisanje = (PlanTreninga) zahtev.getParametar();
                            Controller.getInstance().obrisiPlanTreninga(planZaBrisanje);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    }

                    case UCITAJ_TRENERE: {
                        List<Trener> treneri = Controller.getInstance().ucitajTrenere();
                        odgovor.setOdgovor(treneri);
                        break;
                    }

                    case DODAJ_TRENERA: {
                        Trener noviTrener = (Trener) zahtev.getParametar();
                        Controller.getInstance().dodajTrenera(noviTrener);
                        odgovor.setOdgovor(null);
                        break;
                    }

                    case AZURIRAJ_TRENERA: {
                        Trener izmenjenTrener = (Trener) zahtev.getParametar();
                        Controller.getInstance().azurirajTrenera(izmenjenTrener);
                        odgovor.setOdgovor(null);
                        break;
                    }

                    case OBRISI_TRENERA: {
                        try {
                            Trener trenerZaBrisanje = (Trener) zahtev.getParametar();
                            Controller.getInstance().obrisiTrenera(trenerZaBrisanje);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    }

                    default: {
                        System.out.println("GRESKA, TA OPERACIJA NE POSTOJI");
                        odgovor.setOdgovor(new Exception("Nepoznata operacija."));
                        break;
                    }
                }

                posiljalac.posalji(odgovor);

            } catch (SocketException se) {
                // Kad server gasi konekciju ili klijent prekine -> ne logujemo kao gresku
                if (!kraj) {
                    Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, se);
                }
                break;
            } catch (Exception ex) {
                if (!kraj) {
                    Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void prekini() {
        kraj = true;
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        interrupt();
    }
}