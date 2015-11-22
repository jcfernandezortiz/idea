package es.edu.upm.idea.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="idea_x_clasificacion")
public class IdeaClasification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ididea_clasificacion")
	private int idIdeaClasification;
	
	@Column(name="fecha_registro")
	@Temporal(TemporalType.TIMESTAMP)
	private Date registerDate;
	
	@JoinColumn(name="idclasificacion")
	@ManyToOne
	private Clasification clasification;
	
	@JoinColumn(name="ididea")
	@ManyToOne
	private Idea idea;

	public int getIdIdeaClasification() {
		return idIdeaClasification;
	}

	public void setIdIdeaClasification(int idIdeaClasification) {
		this.idIdeaClasification = idIdeaClasification;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Clasification getClasification() {
		return clasification;
	}

	public void setClasification(Clasification clasification) {
		this.clasification = clasification;
	}

	public Idea getIdea() {
		return idea;
	}

	public void setIdea(Idea idea) {
		this.idea = idea;
	}

}
