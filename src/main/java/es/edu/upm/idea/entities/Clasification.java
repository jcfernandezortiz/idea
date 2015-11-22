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
@Table(name="Clasificacion")
public class Clasification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idclasificacion")
	private int idClasification;
	
	@Column(name="nombre", nullable=false)
	private String name;
	
	@Column(name="descripcion", nullable=false)
	private String description;

	@OneToMany(mappedBy="clasification")
	private List<IdeaClasification> ideasClasifications;
	
	public int getIdClasification() {
		return idClasification;
	}

	public void setIdClasification(int idClasification) {
		this.idClasification = idClasification;
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
	
}
