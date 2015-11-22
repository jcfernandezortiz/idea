package es.edu.upm.idea.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import es.edu.upm.idea.entities.Clasification;

@Stateless
public class ClasificationService {

	@PersistenceContext
	protected EntityManager em;
	
	public void create(Clasification classification){
		em.persist(classification);
	}
	
	public void update(Clasification classification){
		em.merge(classification);
	}
	
	public Clasification findById(Integer id){
		return em.find(Clasification.class,id);
	}
	
	public List<Clasification> findAll(){
		Query q = em.createQuery("SELECT c FROM Clasification c");
		return q.getResultList();
	}
	
}
