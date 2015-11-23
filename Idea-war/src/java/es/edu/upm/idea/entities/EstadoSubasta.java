/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.edu.upm.idea.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mfreire
 */
@Entity
@Table(name = "estado_subasta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoSubasta.findAll", query = "SELECT e FROM EstadoSubasta e"),
    @NamedQuery(name = "EstadoSubasta.findByIdestadoSubasta", query = "SELECT e FROM EstadoSubasta e WHERE e.idestadoSubasta = :idestadoSubasta")})
public class EstadoSubasta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idestado_subasta")
    private Integer idestadoSubasta;
    @Lob
    @Size(max = 65535)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "idestadoSubasta")
    private List<Venta> ventaList;

    public EstadoSubasta() {
    }

    public EstadoSubasta(Integer idestadoSubasta) {
        this.idestadoSubasta = idestadoSubasta;
    }

    public Integer getIdestadoSubasta() {
        return idestadoSubasta;
    }

    public void setIdestadoSubasta(Integer idestadoSubasta) {
        this.idestadoSubasta = idestadoSubasta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<Venta> getVentaList() {
        return ventaList;
    }

    public void setVentaList(List<Venta> ventaList) {
        this.ventaList = ventaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idestadoSubasta != null ? idestadoSubasta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoSubasta)) {
            return false;
        }
        EstadoSubasta other = (EstadoSubasta) object;
        if ((this.idestadoSubasta == null && other.idestadoSubasta != null) || (this.idestadoSubasta != null && !this.idestadoSubasta.equals(other.idestadoSubasta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.edu.upm.idea.entities.EstadoSubasta[ idestadoSubasta=" + idestadoSubasta + " ]";
    }
    
}
