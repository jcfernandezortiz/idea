package es.edu.upm.idea.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import es.edu.upm.idea.entities.AuctionState;


@Stateless
public class AuctionStateService {
	
	@PersistenceContext
	protected EntityManager em;
	
	public void create(AuctionState auctionState){
		em.persist(auctionState);
	}
	
	public void update(AuctionState auctionState){
		em.merge(auctionState);
	}
	
	public AuctionState findById(Integer id){
		return em.find(AuctionState.class,id);
	}
	
	public List<AuctionState> findAll(){
		Query q = em.createQuery("SELECT a FROM AuctionState a");
		return q.getResultList();
	}	
	
}
