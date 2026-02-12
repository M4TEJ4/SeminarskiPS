/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cordinator;
  
import forme.LoginForma;
import java.util.HashMap;
import java.util.Map; 
import kontroleri.LoginController; 
 

/**
 *
 * @author student
 */
public class Cordinator {
    private static Cordinator instanca;  
    private Map<String, Object> parametri;
      
       
     private LoginController loginConroller;
 
     
    private Cordinator() {
         parametri = new HashMap<>();
    }
    public static Cordinator getInstanca() {
        if (instanca == null) {
            instanca = new Cordinator();
        }
        return instanca;
    }
    public void dodajParam(String s, Object o) {
        parametri.put(s, o);
    }

    public Object vratiParam(String s) {
        return parametri.get(s);
    }
   
     
    public void otvoriLoginFormu() {
        loginConroller = new LoginController(new LoginForma());
         dodajParam("aktivnaForma", "login");
        loginConroller.otvoriFormu();
    } 
    
 

 
}
