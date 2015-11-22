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
@Table(name="oferta")
public class Bid {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idoferta")
	private int idBid;
	
	@Column(name = "monto")
	private double mount;
	
	@Column(name = "fecha_registro")
	@Temporal(TemporalType.TIMESTAMP)
	private Date registerDate;
	
	@Column(name = "activo")
	private int state;
	
	@JoinColumn(name="idusuario")
	@ManyToOne
	private User user;
	
	@JoinColumn(name="idventa")
	@ManyToOne
	private Sale sale;

	public int getIdBid() {
		return idBid;
	}

	public void setIdBid(int idBid) {
		this.idBid = idBid;
	}

	public double getMount() {
		return mount;
	}

	public void setMount(double mount) {
		this.mount = mount;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	
	
}
