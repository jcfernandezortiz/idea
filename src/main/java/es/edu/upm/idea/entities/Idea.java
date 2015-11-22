package es.edu.upm.idea.entities;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="idea")
public class Idea {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ididea")	
	private int idIdea;
	
	@Column(name = "titulo")
	private String title;
	
	@Column(name = "descripcion")
	private String description;
	
	@Column(name = "fecha_registro")
	@Temporal(TemporalType.TIMESTAMP)
	private Date registerDate;
	
	@Column(name = "activo")
	private int state;
	
	@OneToMany(mappedBy="idea")
	private List<Comment> comments;
	
	@OneToMany(mappedBy="idea")
	private List<IdeaClasification> ideaClasifications;
	
	@OneToMany(mappedBy="idea")
	private List<Sale> sales;	
	
	@JoinColumn(name="idestado_idea")
	@ManyToOne
	private IdeaState ideaState;
	
	@JoinColumn(name="idusuario")
	@ManyToOne
	private User user;

	public int getIdIdea() {
		return idIdea;
	}

	public void setIdIdea(int idIdea) {
		this.idIdea = idIdea;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public IdeaState getIdeaState() {
		return ideaState;
	}

	public void setIdeaState(IdeaState ideaState) {
		this.ideaState = ideaState;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Sale> getSales() {
		return sales;
	}

	public void setSales(List<Sale> sales) {
		this.sales = sales;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<IdeaClasification> getIdeaClasifications() {
		return ideaClasifications;
	}

	public void setIdeaClasifications(List<IdeaClasification> ideaClasifications) {
		this.ideaClasifications = ideaClasifications;
	}

}
