/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Trener;
import operacije.login.LoginOperacija;

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
}
