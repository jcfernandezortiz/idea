package es.edu.upm.idea.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import es.edu.upm.idea.entities.IdeaState;

@Stateless
public class IdeaStateService {

	@PersistenceContext
	protected EntityManager em;
	
	public void create(IdeaState ideaState){
		em.persist(ideaState);
	}
	
	public void update(IdeaState ideaState){
		em.merge(ideaState);
	}
	
	public IdeaState findById(Integer id){
		return em.find(IdeaState.class,id);
	}
	
	public List<IdeaState> findAll(){
		Query q = em.createQuery("SELECT i FROM IdeaState i");
		return q.getResultList();
	}
	
}
