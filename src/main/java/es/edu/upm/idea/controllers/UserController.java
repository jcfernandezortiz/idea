package es.edu.upm.idea.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import es.edu.upm.idea.entities.User;
import es.edu.upm.idea.services.UserService;

@ManagedBean
public class UserController {

	private User user;
	private List<User> users;
	
	@EJB
	private UserService userService;

	
	public UserController(){
		this.users = new ArrayList<User>();
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<User> getUsers() {
		return users;
	}

	@PostConstruct
	public void getAllUsers(){
		users=userService.findAll();	
	}
	

	

}
