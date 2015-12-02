/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.edu.upm.idea.controllers;

import es.edu.upm.idea.controllers.util.JsfUtil;
import es.edu.upm.idea.entities.Usuario;
import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mfreire
 */
@Named("loginController")
@SessionScoped
public class LoginController implements Serializable{
    private Usuario current;
    private String email;
    private String password;
    
    
    @EJB
    private es.edu.upm.idea.services.UsuarioFacade ejbFacade;

    public LoginController() {
    }
    
    
    public String login(){
        Usuario usuario = ejbFacade.findWithEmail(email);
        if (usuario != null){
            if(usuario.getPassword().equals(password))
            {
                //Autenticación exitosa
                System.out.println("Autenticacion Exitosa");
                current = usuario;
                //JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("LoginDeleted"));
                return "success";
            } else{
                System.out.println("Usuario o password incorrecto");
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("LoginFail"));
                return "fail";
            }
        } else{
            //Fallo autenticacion
            System.out.println("Usuario o password incorrecto");
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("LoginFail"));
            return "fail";
        }
    }
    
    public String logOff(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
        return "out";
    }
    
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Usuario getCurrent() {
        return current;
    }

    public void setCurrent(Usuario current) {
        this.current = current;
    }    
}
