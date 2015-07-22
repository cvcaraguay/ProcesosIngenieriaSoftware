/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utpl.proyecto;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cvcaraguay
 */
@Entity
@Table(name = "proyecto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proyecto.findAll", query = "SELECT p FROM Proyecto p"),
    @NamedQuery(name = "Proyecto.findByIdProyecto", query = "SELECT p FROM Proyecto p WHERE p.idProyecto = :idProyecto")})
public class Proyecto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_proyecto")
    private Integer idProyecto;
    @JoinColumn(name = "director", referencedColumnName = "id_persona")
    @ManyToOne(optional = false)
    private Personas director;
    @JoinColumn(name = "aux1", referencedColumnName = "id_persona")
    @ManyToOne(optional = false)
    private Personas aux1;
    @JoinColumn(name = "aux2", referencedColumnName = "id_persona")
    @ManyToOne(optional = false)
    private Personas aux2;
    @JoinColumn(name = "id_propuesta", referencedColumnName = "id_propuesta")
    @ManyToOne(optional = false)
    private Propuesta idPropuesta;

    public Proyecto() {
    }

    public Proyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Personas getDirector() {
        return director;
    }

    public void setDirector(Personas director) {
        this.director = director;
    }

    public Personas getAux1() {
        return aux1;
    }

    public void setAux1(Personas aux1) {
        this.aux1 = aux1;
    }

    public Personas getAux2() {
        return aux2;
    }

    public void setAux2(Personas aux2) {
        this.aux2 = aux2;
    }

    public Propuesta getIdPropuesta() {
        return idPropuesta;
    }

    public void setIdPropuesta(Propuesta idPropuesta) {
        this.idPropuesta = idPropuesta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProyecto != null ? idProyecto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proyecto)) {
            return false;
        }
        Proyecto other = (Proyecto) object;
        if ((this.idProyecto == null && other.idProyecto != null) || (this.idProyecto != null && !this.idProyecto.equals(other.idProyecto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utpl.proyecto.Proyecto[ idProyecto=" + idProyecto + " ]";
    }
    
}
