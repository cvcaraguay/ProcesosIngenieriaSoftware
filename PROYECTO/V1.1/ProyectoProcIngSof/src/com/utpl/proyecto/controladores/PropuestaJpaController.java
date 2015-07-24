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
import com.utpl.proyecto.Personas;
import com.utpl.proyecto.Propuesta;
import com.utpl.proyecto.Proyecto;
import com.utpl.proyecto.controladores.exceptions.IllegalOrphanException;
import com.utpl.proyecto.controladores.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author cvcaraguay
 */
public class PropuestaJpaController implements Serializable {

    public PropuestaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Propuesta propuesta) {
        if (propuesta.getProyectoList() == null) {
            propuesta.setProyectoList(new ArrayList<Proyecto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Personas idPersona = propuesta.getIdPersona();
            if (idPersona != null) {
                idPersona = em.getReference(idPersona.getClass(), idPersona.getIdPersona());
                propuesta.setIdPersona(idPersona);
            }
            List<Proyecto> attachedProyectoList = new ArrayList<Proyecto>();
            for (Proyecto proyectoListProyectoToAttach : propuesta.getProyectoList()) {
                proyectoListProyectoToAttach = em.getReference(proyectoListProyectoToAttach.getClass(), proyectoListProyectoToAttach.getIdProyecto());
                attachedProyectoList.add(proyectoListProyectoToAttach);
            }
            propuesta.setProyectoList(attachedProyectoList);
            em.persist(propuesta);
            if (idPersona != null) {
                idPersona.getPropuestaList().add(propuesta);
                idPersona = em.merge(idPersona);
            }
            for (Proyecto proyectoListProyecto : propuesta.getProyectoList()) {
                Propuesta oldIdPropuestaOfProyectoListProyecto = proyectoListProyecto.getIdPropuesta();
                proyectoListProyecto.setIdPropuesta(propuesta);
                proyectoListProyecto = em.merge(proyectoListProyecto);
                if (oldIdPropuestaOfProyectoListProyecto != null) {
                    oldIdPropuestaOfProyectoListProyecto.getProyectoList().remove(proyectoListProyecto);
                    oldIdPropuestaOfProyectoListProyecto = em.merge(oldIdPropuestaOfProyectoListProyecto);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Propuesta propuesta) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Propuesta persistentPropuesta = em.find(Propuesta.class, propuesta.getIdPropuesta());
            Personas idPersonaOld = persistentPropuesta.getIdPersona();
            Personas idPersonaNew = propuesta.getIdPersona();
            List<Proyecto> proyectoListOld = persistentPropuesta.getProyectoList();
            List<Proyecto> proyectoListNew = propuesta.getProyectoList();
            List<String> illegalOrphanMessages = null;
            /*for (Proyecto proyectoListOldProyecto : proyectoListOld) {
                if (!proyectoListNew.contains(proyectoListOldProyecto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Proyecto " + proyectoListOldProyecto + " since its idPropuesta field is not nullable.");
                }
            }*/
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idPersonaNew != null) {
                idPersonaNew = em.getReference(idPersonaNew.getClass(), idPersonaNew.getIdPersona());
                propuesta.setIdPersona(idPersonaNew);
            }
            List<Proyecto> attachedProyectoListNew = new ArrayList<Proyecto>();
            /*for (Proyecto proyectoListNewProyectoToAttach : proyectoListNew) {
                proyectoListNewProyectoToAttach = em.getReference(proyectoListNewProyectoToAttach.getClass(), proyectoListNewProyectoToAttach.getIdProyecto());
                attachedProyectoListNew.add(proyectoListNewProyectoToAttach);
            }*/
            proyectoListNew = attachedProyectoListNew;
            propuesta.setProyectoList(proyectoListNew);
            propuesta = em.merge(propuesta);
            if (idPersonaOld != null && !idPersonaOld.equals(idPersonaNew)) {
                idPersonaOld.getPropuestaList().remove(propuesta);
                idPersonaOld = em.merge(idPersonaOld);
            }
            if (idPersonaNew != null && !idPersonaNew.equals(idPersonaOld)) {
                idPersonaNew.getPropuestaList().add(propuesta);
                idPersonaNew = em.merge(idPersonaNew);
            }
            for (Proyecto proyectoListNewProyecto : proyectoListNew) {
                if (!proyectoListOld.contains(proyectoListNewProyecto)) {
                    Propuesta oldIdPropuestaOfProyectoListNewProyecto = proyectoListNewProyecto.getIdPropuesta();
                    proyectoListNewProyecto.setIdPropuesta(propuesta);
                    proyectoListNewProyecto = em.merge(proyectoListNewProyecto);
                    if (oldIdPropuestaOfProyectoListNewProyecto != null && !oldIdPropuestaOfProyectoListNewProyecto.equals(propuesta)) {
                        oldIdPropuestaOfProyectoListNewProyecto.getProyectoList().remove(proyectoListNewProyecto);
                        oldIdPropuestaOfProyectoListNewProyecto = em.merge(oldIdPropuestaOfProyectoListNewProyecto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = propuesta.getIdPropuesta();
                if (findPropuesta(id) == null) {
                    throw new NonexistentEntityException("The propuesta with id " + id + " no longer exists.");
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
            Propuesta propuesta;
            try {
                propuesta = em.getReference(Propuesta.class, id);
                propuesta.getIdPropuesta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The propuesta with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Proyecto> proyectoListOrphanCheck = propuesta.getProyectoList();
            for (Proyecto proyectoListOrphanCheckProyecto : proyectoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Propuesta (" + propuesta + ") cannot be destroyed since the Proyecto " + proyectoListOrphanCheckProyecto + " in its proyectoList field has a non-nullable idPropuesta field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Personas idPersona = propuesta.getIdPersona();
            if (idPersona != null) {
                idPersona.getPropuestaList().remove(propuesta);
                idPersona = em.merge(idPersona);
            }
            em.remove(propuesta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Propuesta> findPropuestaEntities() {
        return findPropuestaEntities(true, -1, -1);
    }

    public List<Propuesta> findPropuestaEntities(int maxResults, int firstResult) {
        return findPropuestaEntities(false, maxResults, firstResult);
    }

    private List<Propuesta> findPropuestaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Propuesta.class));
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

    public Propuesta findPropuesta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Propuesta.class, id);
        } finally {
            em.close();
        }
    }

    public List<Propuesta> findPropuestaPorIdUsuario(int idPersona) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Propuesta> qry;
            qry = em.createQuery("SELECT p FROM Propuesta p WHERE p.idPersona.idPersona = :idPersona", Propuesta.class);
            qry.setParameter("idPersona", idPersona);
            return qry.getResultList();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    public List<Propuesta> findPropuestaPendientes() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Propuesta> qry;
            qry = em.createNamedQuery("Propuesta.findByEstado", Propuesta.class);
            qry.setParameter("estado", 0);
            
            return qry.getResultList();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public int getPropuestaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Propuesta> rt = cq.from(Propuesta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
