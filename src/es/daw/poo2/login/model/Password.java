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
    
    // -------------------
    // Atributos
    // -------------------
    
    /**
     * Atributo est�tico que indica la LONGITUD m�nima que tiene que tener la contrase�a
     */
    public static final int LONGITUD = 6; 
    
    /**
     * Atributo que almacena la contrase�a
     */
    private String pwd;
    
    /**
     * Array est�tico que contiene los caracteres especiales obligatorios requeridos para la contrase�a
     */
    private static final char[] especiales = {'$','&','*'};
    
    /**
     * Array est�tico que contiene las vocales (m�nimo una ser� obligatoria)
     */
    private static final char[] vocalesUp = {'A','E','I','O','U'};    
    
    /**
     * String con el texto que debe aparecer obligatoriamente en alg�n lugar de la contrase�a
     */
    private static final String PATTERN = "DAW";
    
    // -------------------
    // Constructores
    // -------------------
    
    /**
     * Constructor b�sico que genera autom�ticamente la contrase�a cumpliendo los requisitos
     */
    public Password(){
        // En este caso el programa generar� autom�ticamente la contrase�a
        this.pwd = generarPwd();
    }
    
    /**
     * Constructor que recibe una contrase�a definida por el usuario, comprueba si cumple los requisitos y asigna la contrase�a
     * @param pwd contrase�a definida por el usuario
     * @throws es.daw.poo2.login.model.WeakPasswordException Excepcion que indica que la contrase�a no cumple con los siguientes requisitos:  
     */
    public Password(String pwd) throws WeakPasswordException{
        // Habr� que validar que dicha contrase�a es fuerte. 
        // Si no es fuerte el usuario recibir� un aviso y el programa terminar�.
        
        if (esFuerte(pwd)){
            this.pwd = pwd;
        }
        
    }
    
    // Getters & Setters
    /**
     * M�todo que devuleve la contrase�a del usuario
     * @return la contrase�a del usuario
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * M�todo que comprueba que la contrase�a es fuerte atendiendo a los requisitos: \nque contenga la cadena 'DAW', que tenga al menos 6 caracteres, que empieze por una vocal may�scula y que acabe con un caracter especial ( $ * o &amp;)
     * @param pwd Contrase�a definida por el usuario
     * @return Devuelve un true si se ha podido generar la contrase�a. False si no.
     * @throws WeakPasswordException Excepcion lanzada en caso de que la contrase�a no cumpla alguno de los requisitos.
     * @see #especiales
     * @see #PATTERN
     * @see #vocalesUp
     */
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
            causa.append("La constrase�a debe tener una longitud m�nima de ").append(LONGITUD).append("\n");
        if (!ok_Vocal) 
            causa.append("La constrase�a debe empezar por una vocal en may�sculas.\n");
        if (!ok_Especial) 
            causa.append("La constrase�a debe acabar por uno de estos caracteres  $  &  * \n");
        if (!ok_DAW) 
            causa.append("La constrase�a debe contener el texto DAW\n");
        
        if(!ok_DAW || !ok_Especial || !ok_Vocal || !ok_longitud)
            throw new WeakPasswordException(pwd, causa.toString());
        
        return true;
    }
    
    /**
     * M�todo que devuelve una contrase�a por defecto cumpliendo los requisitos
     * @return la contrase�a fija.
     * @deprecated Este m�todo genera una misma contrase�a siempre. Usar mejor generarPwd() que genera una aleatoria.
     * @see #generarPwd() 
     */
    private String generarContraseña(){
        String defaultPwd ="ADAWcualquiercosa&";
        
        return defaultPwd;
    }
    
    //private void generarPwd(){
    /**
     * M�todo que genera una contrase�a aleatoria cumpliendo los requisitos especificados en caso de que el usuario no ponga ninguna
     * @return Contrase�a generada autom�ticamente
     * @see #esFuerte(java.lang.String)
     * 
     */
    private String generarPwd(){
        
        String pwd = "";
        
        Random r = new Random();
        // 1. empezar con una vocal en may�sculas
        pwd += vocalesUp[r.nextInt(5)]; //empezar por vocal may
        // 2. Para rellenar meto 1 n�mero aleatorio (m�s f�cil que meter una letra aleatoria)
        pwd += r.nextInt(10);
        // 3. Meto el texto DAW
        pwd += "DAW";
        // 4. Acaba en un car�cter especial
        pwd += especiales[r.nextInt(3)]; //acabar con car�cter especial
        
        return pwd;
    }

    /**
     * M�todo override de toString que indica la contrase�a
     * @return La contrase�a en formato texto
     */
    @Override
    public String toString() {
        return "Password{" + "pwd=" + pwd + '}';
    }
    
    
}