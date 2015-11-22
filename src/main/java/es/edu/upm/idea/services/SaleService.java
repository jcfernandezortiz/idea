package es.edu.upm.idea.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import es.edu.upm.idea.entities.Sale;

@Stateless
public class SaleService {

	@PersistenceContext
	protected EntityManager em;
	
	public void create(Sale sale){
		em.persist(sale);
	}
	
	public void update(Sale sale){
		em.merge(sale);
	}
	
	public Sale findById(Integer id){
		return em.find(Sale.class,id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Sale> findAll(){
		Query q = em.createQuery("SELECT s FROM Sale s");
		return q.getResultList();
	}
	
	
}
