/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.edu.upm.idea.services;

import es.edu.upm.idea.entities.Clasificacion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mfreire
 */
@Stateless
public class ClasificacionFacade extends AbstractFacade<Clasificacion> {

    @PersistenceContext(unitName = "Idea-warPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClasificacionFacade() {
        super(Clasificacion.class);
    }
    
}
