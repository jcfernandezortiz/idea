package es.edu.upm.idea.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import es.edu.upm.idea.entities.User;

@Stateless
public class UserService {
		
		@PersistenceContext
		protected EntityManager em;
		
		public void create(User user){
			em.persist(user);
		}
		
		public void update(User user){
			em.merge(user);
		}
		
		public User findById(Integer id){
			return em.find(User.class,id);
		}
		
		public List<User> findAll(){
			Query q = em.createQuery("SELECT u FROM User u");
			return q.getResultList();
		}
}
