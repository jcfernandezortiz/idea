package es.edu.upm.idea.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="estado_idea")
public class IdeaState {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idestado_idea")
	private int idIdeaState;
	
	@Column(name = "descripcion")
	private String description;
	
	@OneToMany(mappedBy="ideaState")
	private List<Idea> ideas;

	public int getIdIdeaState() {
		return idIdeaState;
	}

	public void setIdIdeaState(int idIdeaState) {
		this.idIdeaState = idIdeaState;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Idea> getIdeas() {
		return ideas;
	}

	public void setIdeas(List<Idea> ideas) {
		this.ideas = ideas;
	}
	
}
