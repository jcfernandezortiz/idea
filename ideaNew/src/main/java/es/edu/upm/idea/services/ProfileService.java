package es.edu.upm.idea.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import es.edu.upm.idea.entities.Profile;

@Stateless
public class ProfileService {
	
	@PersistenceContext
	protected EntityManager em;
	
	public void create(Profile profile){
		em.persist(profile);
	}
	
	public void update(Profile profile){
		em.merge(profile);
	}
	
	public Profile findById(Integer id){
		return em.find(Profile.class,id);
	}
	
	public List<Profile> findAll(){
		Query q = em.createQuery("SELECT p FROM Profile p");
		return q.getResultList();
	}

}
