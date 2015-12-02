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
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "Idea-warPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    public Usuario findWithEmail(String eMail) {
        
        Usuario u = null;
                
        List<Usuario> listaUsuarios = em.createQuery(
        "SELECT u FROM Usuario u WHERE u.correo = :eMail")
        .setParameter("eMail", eMail).getResultList();
        if (listaUsuarios != null && listaUsuarios.size()> 0){
            u = listaUsuarios.get(0);
        }
         return u;
    }
}
