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
@Table(name="venta")
public class Sale {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idventa")
	private int idSale;
	
	@Column(name = "fecha_inicio")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	
	@Column(name = "fecha_fin")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;
	
	@Column(name = "activo")
	private int state;
	
	@OneToMany(mappedBy="sale")
	private List<Bid> bids;
	
	@JoinColumn(name="ididea")
	@ManyToOne
	private Idea idea;
	
	@JoinColumn(name="idestado_subasta")
	@ManyToOne
	private AuctionState auctionState;

	public int getIdSale() {
		return idSale;
	}

	public void setIdSale(int idSale) {
		this.idSale = idSale;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public List<Bid> getBids() {
		return bids;
	}

	public void setBids(List<Bid> bids) {
		this.bids = bids;
	}

	public Idea getIdea() {
		return idea;
	}

	public void setIdea(Idea idea) {
		this.idea = idea;
	}

	public AuctionState getAuctionState() {
		return auctionState;
	}

	public void setAuctionState(AuctionState auctionState) {
		this.auctionState = auctionState;
	}

	
}
