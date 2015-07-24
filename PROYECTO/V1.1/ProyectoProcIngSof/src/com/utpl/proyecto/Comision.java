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
@Table(name = "comision")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comision.findAll", query = "SELECT c FROM Comision c"),
    @NamedQuery(name = "Comision.findByIdComision", query = "SELECT c FROM Comision c WHERE c.idComision = :idComision")})
public class Comision implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_comision")
    private Integer idComision;
    @JoinColumn(name = "id_persona2", referencedColumnName = "id_persona")
    @ManyToOne(optional = false)
    private Personas idPersona2;
    @JoinColumn(name = "id_persona3", referencedColumnName = "id_persona")
    @ManyToOne(optional = false)
    private Personas idPersona3;
    @JoinColumn(name = "id_persona4", referencedColumnName = "id_persona")
    @ManyToOne(optional = false)
    private Personas idPersona4;
    @JoinColumn(name = "id_persona1", referencedColumnName = "id_persona")
    @ManyToOne(optional = false)
    private Personas idPersona1;

    public Comision() {
    }

    public Comision(Integer idComision) {
        this.idComision = idComision;
    }

    public Integer getIdComision() {
        return idComision;
    }

    public void setIdComision(Integer idComision) {
        this.idComision = idComision;
    }

    public Personas getIdPersona2() {
        return idPersona2;
    }

    public void setIdPersona2(Personas idPersona2) {
        this.idPersona2 = idPersona2;
    }

    public Personas getIdPersona3() {
        return idPersona3;
    }

    public void setIdPersona3(Personas idPersona3) {
        this.idPersona3 = idPersona3;
    }

    public Personas getIdPersona4() {
        return idPersona4;
    }

    public void setIdPersona4(Personas idPersona4) {
        this.idPersona4 = idPersona4;
    }

    public Personas getIdPersona1() {
        return idPersona1;
    }

    public void setIdPersona1(Personas idPersona1) {
        this.idPersona1 = idPersona1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idComision != null ? idComision.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comision)) {
            return false;
        }
        Comision other = (Comision) object;
        if ((this.idComision == null && other.idComision != null) || (this.idComision != null && !this.idComision.equals(other.idComision))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utpl.proyecto.Comision[ idComision=" + idComision + " ]";
    }
    
}
