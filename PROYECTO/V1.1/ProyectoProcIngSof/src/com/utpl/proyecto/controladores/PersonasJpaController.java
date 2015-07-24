/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.utpl.proyecto.controladores;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.utpl.proyecto.Departamento;
import com.utpl.proyecto.Propuesta;
import java.util.ArrayList;
import java.util.List;
import com.utpl.proyecto.Comision;
import com.utpl.proyecto.Personas;
import com.utpl.proyecto.Proyecto;
import com.utpl.proyecto.controladores.exceptions.IllegalOrphanException;
import com.utpl.proyecto.controladores.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author cvcaraguay
 */
public class PersonasJpaController implements Serializable {

    public PersonasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Personas personas) {
        if (personas.getPropuestaList() == null) {
            personas.setPropuestaList(new ArrayList<Propuesta>());
        }
        if (personas.getComisionList() == null) {
            personas.setComisionList(new ArrayList<Comision>());
        }
        if (personas.getComisionList1() == null) {
            personas.setComisionList1(new ArrayList<Comision>());
        }
        if (personas.getComisionList2() == null) {
            personas.setComisionList2(new ArrayList<Comision>());
        }
        if (personas.getComisionList3() == null) {
            personas.setComisionList3(new ArrayList<Comision>());
        }
        if (personas.getProyectoList() == null) {
            personas.setProyectoList(new ArrayList<Proyecto>());
        }
        if (personas.getProyectoList1() == null) {
            personas.setProyectoList1(new ArrayList<Proyecto>());
        }
        if (personas.getProyectoList2() == null) {
            personas.setProyectoList2(new ArrayList<Proyecto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Departamento idDepartamento = personas.getIdDepartamento();
            if (idDepartamento != null) {
                idDepartamento = em.getReference(idDepartamento.getClass(), idDepartamento.getIdDepartamento());
                personas.setIdDepartamento(idDepartamento);
            }
            List<Propuesta> attachedPropuestaList = new ArrayList<Propuesta>();
            for (Propuesta propuestaListPropuestaToAttach : personas.getPropuestaList()) {
                propuestaListPropuestaToAttach = em.getReference(propuestaListPropuestaToAttach.getClass(), propuestaListPropuestaToAttach.getIdPropuesta());
                attachedPropuestaList.add(propuestaListPropuestaToAttach);
            }
            personas.setPropuestaList(attachedPropuestaList);
            List<Comision> attachedComisionList = new ArrayList<Comision>();
            for (Comision comisionListComisionToAttach : personas.getComisionList()) {
                comisionListComisionToAttach = em.getReference(comisionListComisionToAttach.getClass(), comisionListComisionToAttach.getIdComision());
                attachedComisionList.add(comisionListComisionToAttach);
            }
            personas.setComisionList(attachedComisionList);
            List<Comision> attachedComisionList1 = new ArrayList<Comision>();
            for (Comision comisionList1ComisionToAttach : personas.getComisionList1()) {
                comisionList1ComisionToAttach = em.getReference(comisionList1ComisionToAttach.getClass(), comisionList1ComisionToAttach.getIdComision());
                attachedComisionList1.add(comisionList1ComisionToAttach);
            }
            personas.setComisionList1(attachedComisionList1);
            List<Comision> attachedComisionList2 = new ArrayList<Comision>();
            for (Comision comisionList2ComisionToAttach : personas.getComisionList2()) {
                comisionList2ComisionToAttach = em.getReference(comisionList2ComisionToAttach.getClass(), comisionList2ComisionToAttach.getIdComision());
                attachedComisionList2.add(comisionList2ComisionToAttach);
            }
            personas.setComisionList2(attachedComisionList2);
            List<Comision> attachedComisionList3 = new ArrayList<Comision>();
            for (Comision comisionList3ComisionToAttach : personas.getComisionList3()) {
                comisionList3ComisionToAttach = em.getReference(comisionList3ComisionToAttach.getClass(), comisionList3ComisionToAttach.getIdComision());
                attachedComisionList3.add(comisionList3ComisionToAttach);
            }
            personas.setComisionList3(attachedComisionList3);
            List<Proyecto> attachedProyectoList = new ArrayList<Proyecto>();
            for (Proyecto proyectoListProyectoToAttach : personas.getProyectoList()) {
                proyectoListProyectoToAttach = em.getReference(proyectoListProyectoToAttach.getClass(), proyectoListProyectoToAttach.getIdProyecto());
                attachedProyectoList.add(proyectoListProyectoToAttach);
            }
            personas.setProyectoList(attachedProyectoList);
            List<Proyecto> attachedProyectoList1 = new ArrayList<Proyecto>();
            for (Proyecto proyectoList1ProyectoToAttach : personas.getProyectoList1()) {
                proyectoList1ProyectoToAttach = em.getReference(proyectoList1ProyectoToAttach.getClass(), proyectoList1ProyectoToAttach.getIdProyecto());
                attachedProyectoList1.add(proyectoList1ProyectoToAttach);
            }
            personas.setProyectoList1(attachedProyectoList1);
            List<Proyecto> attachedProyectoList2 = new ArrayList<Proyecto>();
            for (Proyecto proyectoList2ProyectoToAttach : personas.getProyectoList2()) {
                proyectoList2ProyectoToAttach = em.getReference(proyectoList2ProyectoToAttach.getClass(), proyectoList2ProyectoToAttach.getIdProyecto());
                attachedProyectoList2.add(proyectoList2ProyectoToAttach);
            }
            personas.setProyectoList2(attachedProyectoList2);
            em.persist(personas);
            if (idDepartamento != null) {
                idDepartamento.getPersonasList().add(personas);
                idDepartamento = em.merge(idDepartamento);
            }
            for (Propuesta propuestaListPropuesta : personas.getPropuestaList()) {
                Personas oldIdPersonaOfPropuestaListPropuesta = propuestaListPropuesta.getIdPersona();
                propuestaListPropuesta.setIdPersona(personas);
                propuestaListPropuesta = em.merge(propuestaListPropuesta);
                if (oldIdPersonaOfPropuestaListPropuesta != null) {
                    oldIdPersonaOfPropuestaListPropuesta.getPropuestaList().remove(propuestaListPropuesta);
                    oldIdPersonaOfPropuestaListPropuesta = em.merge(oldIdPersonaOfPropuestaListPropuesta);
                }
            }
            for (Comision comisionListComision : personas.getComisionList()) {
                Personas oldIdPersona2OfComisionListComision = comisionListComision.getIdPersona2();
                comisionListComision.setIdPersona2(personas);
                comisionListComision = em.merge(comisionListComision);
                if (oldIdPersona2OfComisionListComision != null) {
                    oldIdPersona2OfComisionListComision.getComisionList().remove(comisionListComision);
                    oldIdPersona2OfComisionListComision = em.merge(oldIdPersona2OfComisionListComision);
                }
            }
            for (Comision comisionList1Comision : personas.getComisionList1()) {
                Personas oldIdPersona3OfComisionList1Comision = comisionList1Comision.getIdPersona3();
                comisionList1Comision.setIdPersona3(personas);
                comisionList1Comision = em.merge(comisionList1Comision);
                if (oldIdPersona3OfComisionList1Comision != null) {
                    oldIdPersona3OfComisionList1Comision.getComisionList1().remove(comisionList1Comision);
                    oldIdPersona3OfComisionList1Comision = em.merge(oldIdPersona3OfComisionList1Comision);
                }
            }
            for (Comision comisionList2Comision : personas.getComisionList2()) {
                Personas oldIdPersona4OfComisionList2Comision = comisionList2Comision.getIdPersona4();
                comisionList2Comision.setIdPersona4(personas);
                comisionList2Comision = em.merge(comisionList2Comision);
                if (oldIdPersona4OfComisionList2Comision != null) {
                    oldIdPersona4OfComisionList2Comision.getComisionList2().remove(comisionList2Comision);
                    oldIdPersona4OfComisionList2Comision = em.merge(oldIdPersona4OfComisionList2Comision);
                }
            }
            for (Comision comisionList3Comision : personas.getComisionList3()) {
                Personas oldIdPersona1OfComisionList3Comision = comisionList3Comision.getIdPersona1();
                comisionList3Comision.setIdPersona1(personas);
                comisionList3Comision = em.merge(comisionList3Comision);
                if (oldIdPersona1OfComisionList3Comision != null) {
                    oldIdPersona1OfComisionList3Comision.getComisionList3().remove(comisionList3Comision);
                    oldIdPersona1OfComisionList3Comision = em.merge(oldIdPersona1OfComisionList3Comision);
                }
            }
            for (Proyecto proyectoListProyecto : personas.getProyectoList()) {
                Personas oldAux2OfProyectoListProyecto = proyectoListProyecto.getAux2();
                proyectoListProyecto.setAux2(personas);
                proyectoListProyecto = em.merge(proyectoListProyecto);
                if (oldAux2OfProyectoListProyecto != null) {
                    oldAux2OfProyectoListProyecto.getProyectoList().remove(proyectoListProyecto);
                    oldAux2OfProyectoListProyecto = em.merge(oldAux2OfProyectoListProyecto);
                }
            }
            for (Proyecto proyectoList1Proyecto : personas.getProyectoList1()) {
                Personas oldAux1OfProyectoList1Proyecto = proyectoList1Proyecto.getAux1();
                proyectoList1Proyecto.setAux1(personas);
                proyectoList1Proyecto = em.merge(proyectoList1Proyecto);
                if (oldAux1OfProyectoList1Proyecto != null) {
                    oldAux1OfProyectoList1Proyecto.getProyectoList1().remove(proyectoList1Proyecto);
                    oldAux1OfProyectoList1Proyecto = em.merge(oldAux1OfProyectoList1Proyecto);
                }
            }
            for (Proyecto proyectoList2Proyecto : personas.getProyectoList2()) {
                Personas oldDirectorOfProyectoList2Proyecto = proyectoList2Proyecto.getDirector();
                proyectoList2Proyecto.setDirector(personas);
                proyectoList2Proyecto = em.merge(proyectoList2Proyecto);
                if (oldDirectorOfProyectoList2Proyecto != null) {
                    oldDirectorOfProyectoList2Proyecto.getProyectoList2().remove(proyectoList2Proyecto);
                    oldDirectorOfProyectoList2Proyecto = em.merge(oldDirectorOfProyectoList2Proyecto);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Personas personas) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Personas persistentPersonas = em.find(Personas.class, personas.getIdPersona());
            Departamento idDepartamentoOld = persistentPersonas.getIdDepartamento();
            Departamento idDepartamentoNew = personas.getIdDepartamento();
            List<Propuesta> propuestaListOld = persistentPersonas.getPropuestaList();
            List<Propuesta> propuestaListNew = personas.getPropuestaList();
            List<Comision> comisionListOld = persistentPersonas.getComisionList();
            List<Comision> comisionListNew = personas.getComisionList();
            List<Comision> comisionList1Old = persistentPersonas.getComisionList1();
            List<Comision> comisionList1New = personas.getComisionList1();
            List<Comision> comisionList2Old = persistentPersonas.getComisionList2();
            List<Comision> comisionList2New = personas.getComisionList2();
            List<Comision> comisionList3Old = persistentPersonas.getComisionList3();
            List<Comision> comisionList3New = personas.getComisionList3();
            List<Proyecto> proyectoListOld = persistentPersonas.getProyectoList();
            List<Proyecto> proyectoListNew = personas.getProyectoList();
            List<Proyecto> proyectoList1Old = persistentPersonas.getProyectoList1();
            List<Proyecto> proyectoList1New = personas.getProyectoList1();
            List<Proyecto> proyectoList2Old = persistentPersonas.getProyectoList2();
            List<Proyecto> proyectoList2New = personas.getProyectoList2();
            List<String> illegalOrphanMessages = null;
            for (Propuesta propuestaListOldPropuesta : propuestaListOld) {
                if (!propuestaListNew.contains(propuestaListOldPropuesta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Propuesta " + propuestaListOldPropuesta + " since its idPersona field is not nullable.");
                }
            }
            for (Comision comisionListOldComision : comisionListOld) {
                if (!comisionListNew.contains(comisionListOldComision)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Comision " + comisionListOldComision + " since its idPersona2 field is not nullable.");
                }
            }
            for (Comision comisionList1OldComision : comisionList1Old) {
                if (!comisionList1New.contains(comisionList1OldComision)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Comision " + comisionList1OldComision + " since its idPersona3 field is not nullable.");
                }
            }
            for (Comision comisionList2OldComision : comisionList2Old) {
                if (!comisionList2New.contains(comisionList2OldComision)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Comision " + comisionList2OldComision + " since its idPersona4 field is not nullable.");
                }
            }
            for (Comision comisionList3OldComision : comisionList3Old) {
                if (!comisionList3New.contains(comisionList3OldComision)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Comision " + comisionList3OldComision + " since its idPersona1 field is not nullable.");
                }
            }
            for (Proyecto proyectoListOldProyecto : proyectoListOld) {
                if (!proyectoListNew.contains(proyectoListOldProyecto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Proyecto " + proyectoListOldProyecto + " since its aux2 field is not nullable.");
                }
            }
            for (Proyecto proyectoList1OldProyecto : proyectoList1Old) {
                if (!proyectoList1New.contains(proyectoList1OldProyecto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Proyecto " + proyectoList1OldProyecto + " since its aux1 field is not nullable.");
                }
            }
            for (Proyecto proyectoList2OldProyecto : proyectoList2Old) {
                if (!proyectoList2New.contains(proyectoList2OldProyecto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Proyecto " + proyectoList2OldProyecto + " since its director field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idDepartamentoNew != null) {
                idDepartamentoNew = em.getReference(idDepartamentoNew.getClass(), idDepartamentoNew.getIdDepartamento());
                personas.setIdDepartamento(idDepartamentoNew);
            }
            List<Propuesta> attachedPropuestaListNew = new ArrayList<Propuesta>();
            for (Propuesta propuestaListNewPropuestaToAttach : propuestaListNew) {
                propuestaListNewPropuestaToAttach = em.getReference(propuestaListNewPropuestaToAttach.getClass(), propuestaListNewPropuestaToAttach.getIdPropuesta());
                attachedPropuestaListNew.add(propuestaListNewPropuestaToAttach);
            }
            propuestaListNew = attachedPropuestaListNew;
            personas.setPropuestaList(propuestaListNew);
            List<Comision> attachedComisionListNew = new ArrayList<Comision>();
            for (Comision comisionListNewComisionToAttach : comisionListNew) {
                comisionListNewComisionToAttach = em.getReference(comisionListNewComisionToAttach.getClass(), comisionListNewComisionToAttach.getIdComision());
                attachedComisionListNew.add(comisionListNewComisionToAttach);
            }
            comisionListNew = attachedComisionListNew;
            personas.setComisionList(comisionListNew);
            List<Comision> attachedComisionList1New = new ArrayList<Comision>();
            for (Comision comisionList1NewComisionToAttach : comisionList1New) {
                comisionList1NewComisionToAttach = em.getReference(comisionList1NewComisionToAttach.getClass(), comisionList1NewComisionToAttach.getIdComision());
                attachedComisionList1New.add(comisionList1NewComisionToAttach);
            }
            comisionList1New = attachedComisionList1New;
            personas.setComisionList1(comisionList1New);
            List<Comision> attachedComisionList2New = new ArrayList<Comision>();
            for (Comision comisionList2NewComisionToAttach : comisionList2New) {
                comisionList2NewComisionToAttach = em.getReference(comisionList2NewComisionToAttach.getClass(), comisionList2NewComisionToAttach.getIdComision());
                attachedComisionList2New.add(comisionList2NewComisionToAttach);
            }
            comisionList2New = attachedComisionList2New;
            personas.setComisionList2(comisionList2New);
            List<Comision> attachedComisionList3New = new ArrayList<Comision>();
            for (Comision comisionList3NewComisionToAttach : comisionList3New) {
                comisionList3NewComisionToAttach = em.getReference(comisionList3NewComisionToAttach.getClass(), comisionList3NewComisionToAttach.getIdComision());
                attachedComisionList3New.add(comisionList3NewComisionToAttach);
            }
            comisionList3New = attachedComisionList3New;
            personas.setComisionList3(comisionList3New);
            List<Proyecto> attachedProyectoListNew = new ArrayList<Proyecto>();
            for (Proyecto proyectoListNewProyectoToAttach : proyectoListNew) {
                proyectoListNewProyectoToAttach = em.getReference(proyectoListNewProyectoToAttach.getClass(), proyectoListNewProyectoToAttach.getIdProyecto());
                attachedProyectoListNew.add(proyectoListNewProyectoToAttach);
            }
            proyectoListNew = attachedProyectoListNew;
            personas.setProyectoList(proyectoListNew);
            List<Proyecto> attachedProyectoList1New = new ArrayList<Proyecto>();
            for (Proyecto proyectoList1NewProyectoToAttach : proyectoList1New) {
                proyectoList1NewProyectoToAttach = em.getReference(proyectoList1NewProyectoToAttach.getClass(), proyectoList1NewProyectoToAttach.getIdProyecto());
                attachedProyectoList1New.add(proyectoList1NewProyectoToAttach);
            }
            proyectoList1New = attachedProyectoList1New;
            personas.setProyectoList1(proyectoList1New);
            List<Proyecto> attachedProyectoList2New = new ArrayList<Proyecto>();
            for (Proyecto proyectoList2NewProyectoToAttach : proyectoList2New) {
                proyectoList2NewProyectoToAttach = em.getReference(proyectoList2NewProyectoToAttach.getClass(), proyectoList2NewProyectoToAttach.getIdProyecto());
                attachedProyectoList2New.add(proyectoList2NewProyectoToAttach);
            }
            proyectoList2New = attachedProyectoList2New;
            personas.setProyectoList2(proyectoList2New);
            personas = em.merge(personas);
            if (idDepartamentoOld != null && !idDepartamentoOld.equals(idDepartamentoNew)) {
                idDepartamentoOld.getPersonasList().remove(personas);
                idDepartamentoOld = em.merge(idDepartamentoOld);
            }
            if (idDepartamentoNew != null && !idDepartamentoNew.equals(idDepartamentoOld)) {
                idDepartamentoNew.getPersonasList().add(personas);
                idDepartamentoNew = em.merge(idDepartamentoNew);
            }
            for (Propuesta propuestaListNewPropuesta : propuestaListNew) {
                if (!propuestaListOld.contains(propuestaListNewPropuesta)) {
                    Personas oldIdPersonaOfPropuestaListNewPropuesta = propuestaListNewPropuesta.getIdPersona();
                    propuestaListNewPropuesta.setIdPersona(personas);
                    propuestaListNewPropuesta = em.merge(propuestaListNewPropuesta);
                    if (oldIdPersonaOfPropuestaListNewPropuesta != null && !oldIdPersonaOfPropuestaListNewPropuesta.equals(personas)) {
                        oldIdPersonaOfPropuestaListNewPropuesta.getPropuestaList().remove(propuestaListNewPropuesta);
                        oldIdPersonaOfPropuestaListNewPropuesta = em.merge(oldIdPersonaOfPropuestaListNewPropuesta);
                    }
                }
            }
            for (Comision comisionListNewComision : comisionListNew) {
                if (!comisionListOld.contains(comisionListNewComision)) {
                    Personas oldIdPersona2OfComisionListNewComision = comisionListNewComision.getIdPersona2();
                    comisionListNewComision.setIdPersona2(personas);
                    comisionListNewComision = em.merge(comisionListNewComision);
                    if (oldIdPersona2OfComisionListNewComision != null && !oldIdPersona2OfComisionListNewComision.equals(personas)) {
                        oldIdPersona2OfComisionListNewComision.getComisionList().remove(comisionListNewComision);
                        oldIdPersona2OfComisionListNewComision = em.merge(oldIdPersona2OfComisionListNewComision);
                    }
                }
            }
            for (Comision comisionList1NewComision : comisionList1New) {
                if (!comisionList1Old.contains(comisionList1NewComision)) {
                    Personas oldIdPersona3OfComisionList1NewComision = comisionList1NewComision.getIdPersona3();
                    comisionList1NewComision.setIdPersona3(personas);
                    comisionList1NewComision = em.merge(comisionList1NewComision);
                    if (oldIdPersona3OfComisionList1NewComision != null && !oldIdPersona3OfComisionList1NewComision.equals(personas)) {
                        oldIdPersona3OfComisionList1NewComision.getComisionList1().remove(comisionList1NewComision);
                        oldIdPersona3OfComisionList1NewComision = em.merge(oldIdPersona3OfComisionList1NewComision);
                    }
                }
            }
            for (Comision comisionList2NewComision : comisionList2New) {
                if (!comisionList2Old.contains(comisionList2NewComision)) {
                    Personas oldIdPersona4OfComisionList2NewComision = comisionList2NewComision.getIdPersona4();
                    comisionList2NewComision.setIdPersona4(personas);
                    comisionList2NewComision = em.merge(comisionList2NewComision);
                    if (oldIdPersona4OfComisionList2NewComision != null && !oldIdPersona4OfComisionList2NewComision.equals(personas)) {
                        oldIdPersona4OfComisionList2NewComision.getComisionList2().remove(comisionList2NewComision);
                        oldIdPersona4OfComisionList2NewComision = em.merge(oldIdPersona4OfComisionList2NewComision);
                    }
                }
            }
            for (Comision comisionList3NewComision : comisionList3New) {
                if (!comisionList3Old.contains(comisionList3NewComision)) {
                    Personas oldIdPersona1OfComisionList3NewComision = comisionList3NewComision.getIdPersona1();
                    comisionList3NewComision.setIdPersona1(personas);
                    comisionList3NewComision = em.merge(comisionList3NewComision);
                    if (oldIdPersona1OfComisionList3NewComision != null && !oldIdPersona1OfComisionList3NewComision.equals(personas)) {
                        oldIdPersona1OfComisionList3NewComision.getComisionList3().remove(comisionList3NewComision);
                        oldIdPersona1OfComisionList3NewComision = em.merge(oldIdPersona1OfComisionList3NewComision);
                    }
                }
            }
            for (Proyecto proyectoListNewProyecto : proyectoListNew) {
                if (!proyectoListOld.contains(proyectoListNewProyecto)) {
                    Personas oldAux2OfProyectoListNewProyecto = proyectoListNewProyecto.getAux2();
                    proyectoListNewProyecto.setAux2(personas);
                    proyectoListNewProyecto = em.merge(proyectoListNewProyecto);
                    if (oldAux2OfProyectoListNewProyecto != null && !oldAux2OfProyectoListNewProyecto.equals(personas)) {
                        oldAux2OfProyectoListNewProyecto.getProyectoList().remove(proyectoListNewProyecto);
                        oldAux2OfProyectoListNewProyecto = em.merge(oldAux2OfProyectoListNewProyecto);
                    }
                }
            }
            for (Proyecto proyectoList1NewProyecto : proyectoList1New) {
                if (!proyectoList1Old.contains(proyectoList1NewProyecto)) {
                    Personas oldAux1OfProyectoList1NewProyecto = proyectoList1NewProyecto.getAux1();
                    proyectoList1NewProyecto.setAux1(personas);
                    proyectoList1NewProyecto = em.merge(proyectoList1NewProyecto);
                    if (oldAux1OfProyectoList1NewProyecto != null && !oldAux1OfProyectoList1NewProyecto.equals(personas)) {
                        oldAux1OfProyectoList1NewProyecto.getProyectoList1().remove(proyectoList1NewProyecto);
                        oldAux1OfProyectoList1NewProyecto = em.merge(oldAux1OfProyectoList1NewProyecto);
                    }
                }
            }
            for (Proyecto proyectoList2NewProyecto : proyectoList2New) {
                if (!proyectoList2Old.contains(proyectoList2NewProyecto)) {
                    Personas oldDirectorOfProyectoList2NewProyecto = proyectoList2NewProyecto.getDirector();
                    proyectoList2NewProyecto.setDirector(personas);
                    proyectoList2NewProyecto = em.merge(proyectoList2NewProyecto);
                    if (oldDirectorOfProyectoList2NewProyecto != null && !oldDirectorOfProyectoList2NewProyecto.equals(personas)) {
                        oldDirectorOfProyectoList2NewProyecto.getProyectoList2().remove(proyectoList2NewProyecto);
                        oldDirectorOfProyectoList2NewProyecto = em.merge(oldDirectorOfProyectoList2NewProyecto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = personas.getIdPersona();
                if (findPersonas(id) == null) {
                    throw new NonexistentEntityException("The personas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Personas personas;
            try {
                personas = em.getReference(Personas.class, id);
                personas.getIdPersona();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The personas with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Propuesta> propuestaListOrphanCheck = personas.getPropuestaList();
            for (Propuesta propuestaListOrphanCheckPropuesta : propuestaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Personas (" + personas + ") cannot be destroyed since the Propuesta " + propuestaListOrphanCheckPropuesta + " in its propuestaList field has a non-nullable idPersona field.");
            }
            List<Comision> comisionListOrphanCheck = personas.getComisionList();
            for (Comision comisionListOrphanCheckComision : comisionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Personas (" + personas + ") cannot be destroyed since the Comision " + comisionListOrphanCheckComision + " in its comisionList field has a non-nullable idPersona2 field.");
            }
            List<Comision> comisionList1OrphanCheck = personas.getComisionList1();
            for (Comision comisionList1OrphanCheckComision : comisionList1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Personas (" + personas + ") cannot be destroyed since the Comision " + comisionList1OrphanCheckComision + " in its comisionList1 field has a non-nullable idPersona3 field.");
            }
            List<Comision> comisionList2OrphanCheck = personas.getComisionList2();
            for (Comision comisionList2OrphanCheckComision : comisionList2OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Personas (" + personas + ") cannot be destroyed since the Comision " + comisionList2OrphanCheckComision + " in its comisionList2 field has a non-nullable idPersona4 field.");
            }
            List<Comision> comisionList3OrphanCheck = personas.getComisionList3();
            for (Comision comisionList3OrphanCheckComision : comisionList3OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Personas (" + personas + ") cannot be destroyed since the Comision " + comisionList3OrphanCheckComision + " in its comisionList3 field has a non-nullable idPersona1 field.");
            }
            List<Proyecto> proyectoListOrphanCheck = personas.getProyectoList();
            for (Proyecto proyectoListOrphanCheckProyecto : proyectoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Personas (" + personas + ") cannot be destroyed since the Proyecto " + proyectoListOrphanCheckProyecto + " in its proyectoList field has a non-nullable aux2 field.");
            }
            List<Proyecto> proyectoList1OrphanCheck = personas.getProyectoList1();
            for (Proyecto proyectoList1OrphanCheckProyecto : proyectoList1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Personas (" + personas + ") cannot be destroyed since the Proyecto " + proyectoList1OrphanCheckProyecto + " in its proyectoList1 field has a non-nullable aux1 field.");
            }
            List<Proyecto> proyectoList2OrphanCheck = personas.getProyectoList2();
            for (Proyecto proyectoList2OrphanCheckProyecto : proyectoList2OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Personas (" + personas + ") cannot be destroyed since the Proyecto " + proyectoList2OrphanCheckProyecto + " in its proyectoList2 field has a non-nullable director field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Departamento idDepartamento = personas.getIdDepartamento();
            if (idDepartamento != null) {
                idDepartamento.getPersonasList().remove(personas);
                idDepartamento = em.merge(idDepartamento);
            }
            em.remove(personas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Personas> findPersonasEntities() {
        return findPersonasEntities(true, -1, -1);
    }

    public List<Personas> findPersonasEntities(int maxResults, int firstResult) {
        return findPersonasEntities(false, maxResults, firstResult);
    }

    private List<Personas> findPersonasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Personas.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Personas login(int cedula, String clave) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Personas> qry;
            qry = em.createQuery("SELECT p FROM Personas p WHERE p.cedula = :cedula AND p.clave = :clave", Personas.class);
            qry.setParameter("cedula", cedula);
            qry.setParameter("clave", clave);
            return qry.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public Personas findPersonas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Personas.class, id);
        } finally {
            em.close();
        }
    }

    public int getPersonasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Personas> rt = cq.from(Personas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
