package es.edu.upm.idea.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import es.edu.upm.idea.entities.IdeaClasification;


@Stateless
public class IdeaClasificationService {

	@PersistenceContext
	protected EntityManager em;
	
	public void create(IdeaClasification ideaClassification){
		em.persist(ideaClassification);
	}
	
	public void update(IdeaClasification ideaClassification){
		em.merge(ideaClassification);
	}
	
	public IdeaClasification findById(Integer id){
		return em.find(IdeaClasification.class,id);
	}
	
	@SuppressWarnings("unchecked")
	public List<IdeaClasification> findAll(){
		Query q = em.createQuery("SELECT i FROM IdeaClasification i");
		return q.getResultList();
	}	
}
