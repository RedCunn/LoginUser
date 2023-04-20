package es.daw.poo2.login.model;

/**
 *
 * @author melola
 */
public class User {
    // Atributos
    private String login;
    private Password password;
    
    // Constructores
    public User(String login){
        this.login = login;
        
        // si no se especifica la Password se crea una por defecto
        this.password = new Password();
    }
    
    public User(String login, Password password){
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public Password getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" + "login=" + login + ", password=" + password + '}';
    }
    
    
    
    
    
}