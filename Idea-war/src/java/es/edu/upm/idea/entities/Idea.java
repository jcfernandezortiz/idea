/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.edu.upm.idea.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mfreire
 */
@Entity
@Table(name = "idea")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Idea.findAll", query = "SELECT i FROM Idea i"),
    @NamedQuery(name = "Idea.findByIdidea", query = "SELECT i FROM Idea i WHERE i.ididea = :ididea"),
    @NamedQuery(name = "Idea.findByFechaRegistro", query = "SELECT i FROM Idea i WHERE i.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "Idea.findByActivo", query = "SELECT i FROM Idea i WHERE i.activo = :activo")})
public class Idea implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ididea")
    private Integer ididea;
    @Lob
    @Size(max = 65535)
    @Column(name = "titulo")
    private String titulo;
    @Lob
    @Size(max = 65535)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "activo")
    private Short activo;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne
    private Usuario idusuario;
    @JoinColumn(name = "idestado_idea", referencedColumnName = "idestado_idea")
    @ManyToOne
    private EstadoIdea idestadoIdea;
    @OneToMany(mappedBy = "ididea")
    private List<Venta> ventaList;
    @OneToMany(mappedBy = "ididea")
    private List<Comentario> comentarioList;
    
    @ManyToMany
    @JoinTable(name="idea_x_clasificacion", joinColumns={@JoinColumn(name="ididea")}, inverseJoinColumns = {@JoinColumn(name="idclasificacion")})
    private List<Clasificacion> clasificacionList;
    

    public Idea() {
    }

    public Idea(Integer ididea) {
        this.ididea = ididea;
    }

    public Integer getIdidea() {
        return ididea;
    }

    public List<Clasificacion> getClasificacionList() {
        return clasificacionList;
    }

    public void setClasificacionList(List<Clasificacion> clasificacionList) {
        this.clasificacionList = clasificacionList;
    }

    public void setIdidea(Integer ididea) {
        this.ididea = ididea;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Short getActivo() {
        return activo;
    }

    public void setActivo(Short activo) {
        this.activo = activo;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    public EstadoIdea getIdestadoIdea() {
        return idestadoIdea;
    }

    public void setIdestadoIdea(EstadoIdea idestadoIdea) {
        this.idestadoIdea = idestadoIdea;
    }

    @XmlTransient
    public List<Venta> getVentaList() {
        return ventaList;
    }

    public void setVentaList(List<Venta> ventaList) {
        this.ventaList = ventaList;
    }

    @XmlTransient
    public List<Comentario> getComentarioList() {
        return comentarioList;
    }

    public void setComentarioList(List<Comentario> comentarioList) {
        this.comentarioList = comentarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ididea != null ? ididea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Idea)) {
            return false;
        }
        Idea other = (Idea) object;
        if ((this.ididea == null && other.ididea != null) || (this.ididea != null && !this.ididea.equals(other.ididea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.edu.upm.idea.entities.Idea[ ididea=" + ididea + " ]";
    }
    
}
