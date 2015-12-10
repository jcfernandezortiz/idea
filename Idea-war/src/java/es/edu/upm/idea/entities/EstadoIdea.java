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
@Table(name = "estado_idea")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoIdea.findAll", query = "SELECT e FROM EstadoIdea e"),
    @NamedQuery(name = "EstadoIdea.findByIdestadoIdea", query = "SELECT e FROM EstadoIdea e WHERE e.idestadoIdea = :idestadoIdea")})
public class EstadoIdea implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idestado_idea")
    private Integer idestadoIdea;
    @Lob
    @Size(max = 65535)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "idestadoIdea")
    private List<Idea> ideaList;

    public EstadoIdea() {
    }

    public EstadoIdea(Integer idestadoIdea) {
        this.idestadoIdea = idestadoIdea;
    }

    public Integer getIdestadoIdea() {
        return idestadoIdea;
    }

    public void setIdestadoIdea(Integer idestadoIdea) {
        this.idestadoIdea = idestadoIdea;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<Idea> getIdeaList() {
        return ideaList;
    }

    public void setIdeaList(List<Idea> ideaList) {
        this.ideaList = ideaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idestadoIdea != null ? idestadoIdea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoIdea)) {
            return false;
        }
        EstadoIdea other = (EstadoIdea) object;
        if ((this.idestadoIdea == null && other.idestadoIdea != null) || (this.idestadoIdea != null && !this.idestadoIdea.equals(other.idestadoIdea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descripcion;
    }
    
}
