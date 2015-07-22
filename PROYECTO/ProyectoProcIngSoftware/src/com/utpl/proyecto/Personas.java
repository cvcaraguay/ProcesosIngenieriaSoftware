/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utpl.proyecto;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author cvcaraguay
 */
@Entity
@Table(name = "personas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personas.findAll", query = "SELECT p FROM Personas p"),
    @NamedQuery(name = "Personas.findByIdPersona", query = "SELECT p FROM Personas p WHERE p.idPersona = :idPersona"),
    @NamedQuery(name = "Personas.findByCedula", query = "SELECT p FROM Personas p WHERE p.cedula = :cedula"),
    @NamedQuery(name = "Personas.findByRol", query = "SELECT p FROM Personas p WHERE p.rol = :rol"),
    @NamedQuery(name = "Personas.findByNombre", query = "SELECT p FROM Personas p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Personas.findByApellido", query = "SELECT p FROM Personas p WHERE p.apellido = :apellido"),
    @NamedQuery(name = "Personas.findByCorreo", query = "SELECT p FROM Personas p WHERE p.correo = :correo"),
    @NamedQuery(name = "Personas.findByNumeroCreditos", query = "SELECT p FROM Personas p WHERE p.numeroCreditos = :numeroCreditos"),
    @NamedQuery(name = "Personas.findByNivelGp", query = "SELECT p FROM Personas p WHERE p.nivelGp = :nivelGp")})
public class Personas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_persona")
    private Integer idPersona;
    @Basic(optional = false)
    @Column(name = "cedula")
    private int cedula;
    @Basic(optional = false)
    @Column(name = "rol")
    private String rol;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "apellido")
    private String apellido;
    @Basic(optional = false)
    @Column(name = "correo")
    private String correo;
    @Basic(optional = false)
    @Column(name = "numero_creditos")
    private int numeroCreditos;
    @Basic(optional = false)
    @Column(name = "nivel_gp")
    private double nivelGp;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPersona")
    private List<Propuesta> propuestaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPersona2")
    private List<Comision> comisionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPersona3")
    private List<Comision> comisionList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPersona4")
    private List<Comision> comisionList2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPersona1")
    private List<Comision> comisionList3;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "director")
    private List<Proyecto> proyectoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aux1")
    private List<Proyecto> proyectoList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aux2")
    private List<Proyecto> proyectoList2;
    @JoinColumn(name = "id_departamento", referencedColumnName = "id_departamento")
    @ManyToOne(optional = false)
    private Departamento idDepartamento;

    public Personas() {
    }

    public Personas(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public Personas(Integer idPersona, int cedula, String rol, String nombre, String apellido, String correo, int numeroCreditos, double nivelGp) {
        this.idPersona = idPersona;
        this.cedula = cedula;
        this.rol = rol;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.numeroCreditos = numeroCreditos;
        this.nivelGp = nivelGp;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getNumeroCreditos() {
        return numeroCreditos;
    }

    public void setNumeroCreditos(int numeroCreditos) {
        this.numeroCreditos = numeroCreditos;
    }

    public double getNivelGp() {
        return nivelGp;
    }

    public void setNivelGp(double nivelGp) {
        this.nivelGp = nivelGp;
    }

    @XmlTransient
    public List<Propuesta> getPropuestaList() {
        return propuestaList;
    }

    public void setPropuestaList(List<Propuesta> propuestaList) {
        this.propuestaList = propuestaList;
    }

    @XmlTransient
    public List<Comision> getComisionList() {
        return comisionList;
    }

    public void setComisionList(List<Comision> comisionList) {
        this.comisionList = comisionList;
    }

    @XmlTransient
    public List<Comision> getComisionList1() {
        return comisionList1;
    }

    public void setComisionList1(List<Comision> comisionList1) {
        this.comisionList1 = comisionList1;
    }

    @XmlTransient
    public List<Comision> getComisionList2() {
        return comisionList2;
    }

    public void setComisionList2(List<Comision> comisionList2) {
        this.comisionList2 = comisionList2;
    }

    @XmlTransient
    public List<Comision> getComisionList3() {
        return comisionList3;
    }

    public void setComisionList3(List<Comision> comisionList3) {
        this.comisionList3 = comisionList3;
    }

    @XmlTransient
    public List<Proyecto> getProyectoList() {
        return proyectoList;
    }

    public void setProyectoList(List<Proyecto> proyectoList) {
        this.proyectoList = proyectoList;
    }

    @XmlTransient
    public List<Proyecto> getProyectoList1() {
        return proyectoList1;
    }

    public void setProyectoList1(List<Proyecto> proyectoList1) {
        this.proyectoList1 = proyectoList1;
    }

    @XmlTransient
    public List<Proyecto> getProyectoList2() {
        return proyectoList2;
    }

    public void setProyectoList2(List<Proyecto> proyectoList2) {
        this.proyectoList2 = proyectoList2;
    }

    public Departamento getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Departamento idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPersona != null ? idPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personas)) {
            return false;
        }
        Personas other = (Personas) object;
        if ((this.idPersona == null && other.idPersona != null) || (this.idPersona != null && !this.idPersona.equals(other.idPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utpl.proyecto.Personas[ idPersona=" + idPersona + " ]";
    }
    
}
