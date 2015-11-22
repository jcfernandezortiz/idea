package es.edu.upm.idea.controllers;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


@ManagedBean(name = "ideaBean")
@ViewScoped
public class IdeaBean {

	private String name;
	private String description;
	IdeaEntity idea;
	public IdeaBean(){
		idea = new IdeaEntity();
		this.name = idea.getName();
		this.description = idea.getDescription();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void save(){
		addMessage("Saved!!");
	}
	public void edit() {
		this.description ="nueva descripcion";
		addMessage("Edited!");
    }
     
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	
}
