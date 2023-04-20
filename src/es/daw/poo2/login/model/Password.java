package es.daw.poo2.login.model;

import java.util.Random;

/**
 *
 * @author Iv�n, Cons, Alejandro, Daniel
 * @version 1.0
 * @since 30-03-23
 * 
 */
public class Password {
    
    public static final int LONGITUD = 6; 
    private String pwd;
    private static final char[] especiales = {'$','&','*'};
    private static final char[] vocalesUp = {'A','E','I','O','U'};    
    private static final String PATTERN = "DAW";
    
    public Password(){
        this.pwd = generarPwd();
    }
    
    public Password(String pwd) throws WeakPasswordException{
        if (esFuerte(pwd)){
            this.pwd = pwd;
        }
        
    }

    public String getPwd() {
        return pwd;
    }

    private boolean esFuerte(String pwd) throws WeakPasswordException{
      
        boolean ok_longitud = false;
        boolean ok_Vocal = false;
        boolean ok_Especial = false;
        boolean ok_DAW = false;
        StringBuilder causa = new StringBuilder();
        
        if (pwd.length() >= LONGITUD) ok_longitud = true;
      
        int i=0;
        char primer = pwd.charAt(0);
        while (!ok_Vocal && i < vocalesUp.length){
            if ( primer == vocalesUp[i]) ok_Vocal = true;
            i++;
        }
        
        i = 0;
        char ulti = pwd.charAt(pwd.length()-1);
        while (!ok_Especial && i < especiales.length){
            if ( ulti == especiales[i] )ok_Especial = true;
            i++;
        }
            
        int pos = pwd.indexOf("DAW");
        if (pos > 0) ok_DAW = true;

        System.out.println("AVISOS:");
        if (!ok_longitud) 
            causa.append("La constraseña debe tener una longitud m�nima de ").append(LONGITUD).append("\n");
        if (!ok_Vocal) 
            causa.append("La constraseña debe empezar por una vocal en mayúsculas.\n");
        if (!ok_Especial) 
            causa.append("La constraseña debe acabar por uno de estos caracteres  $  &  * \n");
        if (!ok_DAW) 
            causa.append("La constraseña debe contener el texto DAW\n");
        
        if(!ok_DAW || !ok_Especial || !ok_Vocal || !ok_longitud)
            throw new WeakPasswordException(pwd, causa.toString());
        
        return true;
    }
  
    private String generarContraseña(){
        String defaultPwd ="ADAWcualquiercosa&";
        
        return defaultPwd;
    }
    
    private String generarPwd(){
        
        String pwd = "";
        
        Random r = new Random();
        pwd += vocalesUp[r.nextInt(5)]; 
        pwd += r.nextInt(10);
        pwd += "DAW";
        pwd += especiales[r.nextInt(3)];
        
        return pwd;
    }

    @Override
    public String toString() {
        return "Password{" + "pwd=" + pwd + '}';
    }
    
    
}