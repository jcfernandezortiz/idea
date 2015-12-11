/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.edu.upm.idea.services;

import es.edu.upm.idea.entities.Idea;
import es.edu.upm.idea.entities.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author mfreire
 */
@Stateless
public class IdeaFacade extends AbstractFacade<Idea> {

    @PersistenceContext(unitName = "Idea-warPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IdeaFacade() {
        super(Idea.class);
    }
    
    public int count1(Usuario user) {
        
        Query query = em.createQuery("SELECT count(i) FROM Idea i WHERE i.idusuario = :usuario");
        query.setParameter("usuario", user);
        query.getSingleResult();

        return ((Long) query.getSingleResult()).intValue();
        
    }
    
    
    
    
    public List<Idea> findRange1(int[] range, Usuario user) {
        Query query = em.createQuery("SELECT i FROM Idea i WHERE i.idusuario = :usuario");
        query.setParameter("usuario", user);
        query.setMaxResults(range[1] - range[0] + 1);
        query.setFirstResult(range[0]);
        return query.getResultList();
        
    }
    
}
