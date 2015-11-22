package es.edu.upm.idea.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import es.edu.upm.idea.entities.Comment;


@Stateless
public class CommentService {
	
	@PersistenceContext
	protected EntityManager em;
	
	public void create(Comment comment){
		em.persist(comment);
	}
	
	public void update(Comment comment){
		em.merge(comment);
	}
	
	public Comment findById(Integer id){
		return em.find(Comment.class,id);
	}
	
	public List<Comment> findAll(){
		Query q = em.createQuery("SELECT c FROM Comment c");
		return q.getResultList();
	}
	
}
