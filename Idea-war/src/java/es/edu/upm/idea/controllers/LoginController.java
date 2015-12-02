package es.edu.upm.idea.controllers;

import es.edu.upm.idea.entities.Usuario;
import es.edu.upm.idea.services.LoginFacade;

import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;

@Named("loginController")
@SessionScoped
public class LoginController implements Serializable {

    private Usuario current;
    private String username;
    private String password;
    private Date registerDate;
    
    @EJB
    private es.edu.upm.idea.services.LoginFacade ejbFacade;
    
    private DataModel items = null;


    
    public Usuario getCurrent() {
        return current;
    }

    public void setCurrent(Usuario current) {
        this.current = current;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public DataModel getItems() {
        return items;
    }

    public void setItems(DataModel items) {
        this.items = items;
    }

    public LoginFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(LoginFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public LoginController() {
    }

    public String loginUser(){
            //compare username and password entries
            current = getEjbFacade().login(getUsername(), getPassword());
            if(current != null){
                return "success";
            }
            return "fail";
    }
    
    public String logoutUser(){
            //compare username and password entries
            current = null;
            return "fail";
    }
}
