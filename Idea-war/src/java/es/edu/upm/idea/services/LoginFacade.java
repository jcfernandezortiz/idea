/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.edu.upm.idea.services;

import es.edu.upm.idea.entities.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mfreire
 */
@Stateless
public class LoginFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "Idea-warPU")
    private EntityManager em;
    private Usuario user;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LoginFacade() {
        super(Usuario.class);
    }
    
    public Usuario login(String username, String password){
        try{
            user = (Usuario)em.createNamedQuery("Usuario.login").setParameter("correo",username).setParameter("password",password).getSingleResult();
            return user;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
}
