/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.daw.poo2.login.model;

/**
 *
 * @author ivang
 */
public class WeakPasswordException extends Exception {
    private final String pwd;
    private static final String MESSAGE = "La contraseña %s no es fuerte: %s";

    public WeakPasswordException(String pwd, String causa) {
        super(String.format(MESSAGE, pwd, causa));
        this.pwd = pwd;
    }
    
    
    
}
