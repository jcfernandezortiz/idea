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
@Table(name="estado_subasta")
public class AuctionState {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idestado_subasta")
	private int idAuctionState;
	
	@Column(name = "descripcion")
	private String description;
	
	@OneToMany(mappedBy="auctionState")
	private List<Sale> sales;

	public int getIdAuctionState() {
		return idAuctionState;
	}

	public void setIdAuctionState(int idAuctionState) {
		this.idAuctionState = idAuctionState;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Sale> getSales() {
		return sales;
	}

	public void setSales(List<Sale> sales) {
		this.sales = sales;
	}

}
