package es.daw.poo2.login.model;

import java.util.Random;

/**
 *
 * @author Iván, Cons, Alejandro, Daniel
 * @version 1.1
 * @since 30-03-23
 * 
 */
public class Password {
    
    // -------------------
    // Atributos
    // -------------------
    
    /**
     * Atributo estático que indica la LONGITUD mínima que tiene que tener la contraseña
     */
    public static final int LONGITUD = 6; 
    
    /**
     * Atributo que almacena la contraseña
     */
    private String pwd;
    
    /**
     * Array estático que contiene los caracteres especiales obligatorios requeridos para la contraseña
     */
    private static final char[] especiales = {'$','&','*'};
    
    /**
     * Array estático que contiene las vocales (mínimo una será obligatoria)
     */
    private static final char[] vocalesUp = {'A','E','I','O','U'};    
    
    /**
     * String con el texto que debe aparecer obligatoriamente en algún lugar de la contraseña
     */
    private static final String PATTERN = "DAW";
    
    // -------------------
    // Constructores
    // -------------------
    
    /**
     * Constructor básico que genera automáticamente la contraseña cumpliendo los requisitos
     */
    public Password(){
        // En este caso el programa generará automáticamente la contraseña
        this.pwd = generarPwd();
    }
    
    /**
     * Constructor que recibe una contraseña definida por el usuario, comprueba si cumple los requisitos y asigna la contraseña
     * @param pwd contraseña definida por el usuario
     * @throws es.daw.poo2.login.model.WeakPasswordException Excepcion que indica que la contraseña no cumple con los siguientes requisitos:  
     */
    public Password(String pwd) throws WeakPasswordException{
        // Habrá que validar que dicha contraseña es fuerte. 
        // Si no es fuerte el usuario recibirá un aviso y el programa terminará.
        
        if (esFuerte(pwd)){
            this.pwd = pwd;
        }
        
    }
    
    // Getters & Setters
    /**
     * Método que devuleve la contraseña del usuario
     * @return la contraseña del usuario
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * Método que comprueba que la contraseña es fuerte atendiendo a los requisitos: \nque contenga la cadena 'DAW', que tenga al menos 6 caracteres, que empieze por una vocal mayúscula y que acabe con un caracter especial ( $ * o &amp;)
     * @param pwd Contraseña definida por el usuario
     * @return Devuelve un true si se ha podido generar la contraseña. False si no.
     * @throws WeakPasswordException Excepcion lanzada en caso de que la contraseña no cumpla alguno de los requisitos.
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
            causa.append("La constraseña debe tener una longitud mínima de ").append(LONGITUD).append("\n");
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
    
    /**
     * Método que devuelve una contraseña por defecto cumpliendo los requisitos
     * @return la contraseña fija.
     * @deprecated Este método genera una misma contraseña siempre. Usar mejor generarPwd() que genera una aleatoria.
     * @see #generarPwd() 
     */
    private String generarContraseña(){
        String defaultPwd ="ADAWcualquiercosa&";
        
        return defaultPwd;
    }
    
    //private void generarPwd(){
    /**
     * Método que genera una contraseña aleatoria cumpliendo los requisitos especificados en caso de que el usuario no ponga ninguna
     * @return Contraseña generada automáticamente
     * @see #esFuerte(java.lang.String)
     * 
     */
    private String generarPwd(){
        
        String pwd = "";
        
        Random r = new Random();
        // 1. empezar con una vocal en mayúsculas
        pwd += vocalesUp[r.nextInt(5)]; //empezar por vocal may
        // 2. Para rellenar meto 1 número aleatorio (más fácil que meter una letra aleatoria)
        pwd += r.nextInt(10);
        // 3. Meto el texto DAW
        pwd += "DAW";
        // 4. Acaba en un carácter especial
        pwd += especiales[r.nextInt(3)]; //acabar con carácter especial
        
        return pwd;
    }

    /**
     * Método override de toString que indica la contraseña
     * @return La contraseña en formato texto
     */
    @Override
    public String toString() {
        return "Password{" + "pwd=" + pwd + '}';
    }
    
    
}