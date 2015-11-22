package es.edu.upm.idea.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import es.edu.upm.idea.entities.Bid;


@Stateless
public class BidService {

	@PersistenceContext
	protected EntityManager em;
	
	public void create(Bid bid){
		em.persist(bid);
	}

	public void update(Bid bid){
		em.merge(bid);
	}
	
	public Bid findById(Integer id){
		return em.find(Bid.class,id);
	}
	
	public List<Bid> findAll(){
		Query q = em.createQuery("SELECT b FROM Bid b");
		return q.getResultList();
	}
	
}
