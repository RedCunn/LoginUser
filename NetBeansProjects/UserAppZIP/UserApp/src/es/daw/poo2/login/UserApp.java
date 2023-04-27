package es.daw.poo2.login;

import es.daw.poo2.login.model.Password;
import es.daw.poo2.login.model.User;
import es.daw.poo2.login.model.WeakPasswordException;
import java.util.Scanner;


/**
 *
 * @author Iv�n, Cons, Dani, Alejandro
 */
public class UserApp {

    /**pos
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // ---------- FASE 1 DE PRUEBAS: VALIDAR EL COMPORTAMIENTO DE LA CLASE PASSWORD ---------
       
        
        // --------------- MEN� ----------------
        System.out.println("*** CREACI�N DE CUENTAS DE USUARIO *****");
        System.out.println("1. Introduce el login: ");
        Scanner sc = new Scanner(System.in);
        String login = sc.nextLine();
        
        System.out.println("2. Introduce la contrase�a: ");
        System.out.println("En caso de querer que se genere una contrase�a autom�tica pulsa intro...");
        String pwd = sc.nextLine();
        
        User user = null;
        if (pwd.equals("")){
            user = new User(login);
        }else{
            Password p;
            try {
                p = new Password(pwd);
                user = new User(login,p);
            } catch (WeakPasswordException ex) {
                ex.getMessage();
            }
            
           
        }
        
        if(user != null)
            System.out.println("INFO DE LA CUENTA DE USUARIO GENERADA EN EL SISTEMA: "+user);
    }
    
}