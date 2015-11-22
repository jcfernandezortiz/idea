package es.edu.upm.idea.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import es.edu.upm.idea.entities.Idea;

@Stateless
public class IdeaService {

	@PersistenceContext
	protected EntityManager em;
	
	public void create(Idea idea){
		em.persist(idea);
	}
	
	public void update(Idea idea){
		em.merge(idea);
	}
	
	public Idea findById(Integer id){
		return em.find(Idea.class,id);
	}
	
	public List<Idea> findAll(){
		Query q = em.createQuery("SELECT i FROM Idea i");
		return q.getResultList();
	}
}
