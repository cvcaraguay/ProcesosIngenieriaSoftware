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
import com.utpl.proyecto.controladores.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author cvcaraguay
 */
public class ProyectoJpaController implements Serializable {

    public ProyectoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Proyecto proyecto) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Personas aux2 = proyecto.getAux2();
            if (aux2 != null) {
                aux2 = em.getReference(aux2.getClass(), aux2.getIdPersona());
                proyecto.setAux2(aux2);
            }
            Personas aux1 = proyecto.getAux1();
            if (aux1 != null) {
                aux1 = em.getReference(aux1.getClass(), aux1.getIdPersona());
                proyecto.setAux1(aux1);
            }
            Personas director = proyecto.getDirector();
            if (director != null) {
                director = em.getReference(director.getClass(), director.getIdPersona());
                proyecto.setDirector(director);
            }
            Propuesta idPropuesta = proyecto.getIdPropuesta();
            if (idPropuesta != null) {
                idPropuesta = em.getReference(idPropuesta.getClass(), idPropuesta.getIdPropuesta());
                proyecto.setIdPropuesta(idPropuesta);
            }
            em.persist(proyecto);
            if (aux2 != null) {
                aux2.getProyectoList().add(proyecto);
                aux2 = em.merge(aux2);
            }
            if (aux1 != null) {
                aux1.getProyectoList().add(proyecto);
                aux1 = em.merge(aux1);
            }
            if (director != null) {
                director.getProyectoList().add(proyecto);
                director = em.merge(director);
            }
            if (idPropuesta != null) {
                idPropuesta.getProyectoList().add(proyecto);
                idPropuesta = em.merge(idPropuesta);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Proyecto proyecto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proyecto persistentProyecto = em.find(Proyecto.class, proyecto.getIdProyecto());
            Personas aux2Old = persistentProyecto.getAux2();
            Personas aux2New = proyecto.getAux2();
            Personas aux1Old = persistentProyecto.getAux1();
            Personas aux1New = proyecto.getAux1();
            Personas directorOld = persistentProyecto.getDirector();
            Personas directorNew = proyecto.getDirector();
            Propuesta idPropuestaOld = persistentProyecto.getIdPropuesta();
            Propuesta idPropuestaNew = proyecto.getIdPropuesta();
            if (aux2New != null) {
                aux2New = em.getReference(aux2New.getClass(), aux2New.getIdPersona());
                proyecto.setAux2(aux2New);
            }
            if (aux1New != null) {
                aux1New = em.getReference(aux1New.getClass(), aux1New.getIdPersona());
                proyecto.setAux1(aux1New);
            }
            if (directorNew != null) {
                directorNew = em.getReference(directorNew.getClass(), directorNew.getIdPersona());
                proyecto.setDirector(directorNew);
            }
            if (idPropuestaNew != null) {
                idPropuestaNew = em.getReference(idPropuestaNew.getClass(), idPropuestaNew.getIdPropuesta());
                proyecto.setIdPropuesta(idPropuestaNew);
            }
            proyecto = em.merge(proyecto);
            if (aux2Old != null && !aux2Old.equals(aux2New)) {
                aux2Old.getProyectoList().remove(proyecto);
                aux2Old = em.merge(aux2Old);
            }
            if (aux2New != null && !aux2New.equals(aux2Old)) {
                aux2New.getProyectoList().add(proyecto);
                aux2New = em.merge(aux2New);
            }
            if (aux1Old != null && !aux1Old.equals(aux1New)) {
                aux1Old.getProyectoList().remove(proyecto);
                aux1Old = em.merge(aux1Old);
            }
            if (aux1New != null && !aux1New.equals(aux1Old)) {
                aux1New.getProyectoList().add(proyecto);
                aux1New = em.merge(aux1New);
            }
            if (directorOld != null && !directorOld.equals(directorNew)) {
                directorOld.getProyectoList().remove(proyecto);
                directorOld = em.merge(directorOld);
            }
            if (directorNew != null && !directorNew.equals(directorOld)) {
                directorNew.getProyectoList().add(proyecto);
                directorNew = em.merge(directorNew);
            }
            if (idPropuestaOld != null && !idPropuestaOld.equals(idPropuestaNew)) {
                idPropuestaOld.getProyectoList().remove(proyecto);
                idPropuestaOld = em.merge(idPropuestaOld);
            }
            if (idPropuestaNew != null && !idPropuestaNew.equals(idPropuestaOld)) {
                idPropuestaNew.getProyectoList().add(proyecto);
                idPropuestaNew = em.merge(idPropuestaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = proyecto.getIdProyecto();
                if (findProyecto(id) == null) {
                    throw new NonexistentEntityException("The proyecto with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proyecto proyecto;
            try {
                proyecto = em.getReference(Proyecto.class, id);
                proyecto.getIdProyecto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proyecto with id " + id + " no longer exists.", enfe);
            }
            Personas aux2 = proyecto.getAux2();
            if (aux2 != null) {
                aux2.getProyectoList().remove(proyecto);
                aux2 = em.merge(aux2);
            }
            Personas aux1 = proyecto.getAux1();
            if (aux1 != null) {
                aux1.getProyectoList().remove(proyecto);
                aux1 = em.merge(aux1);
            }
            Personas director = proyecto.getDirector();
            if (director != null) {
                director.getProyectoList().remove(proyecto);
                director = em.merge(director);
            }
            Propuesta idPropuesta = proyecto.getIdPropuesta();
            if (idPropuesta != null) {
                idPropuesta.getProyectoList().remove(proyecto);
                idPropuesta = em.merge(idPropuesta);
            }
            em.remove(proyecto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Proyecto> findProyectoEntities() {
        return findProyectoEntities(true, -1, -1);
    }

    public List<Proyecto> findProyectoEntities(int maxResults, int firstResult) {
        return findProyectoEntities(false, maxResults, firstResult);
    }

    private List<Proyecto> findProyectoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Proyecto.class));
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

    public Proyecto findProyecto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Proyecto.class, id);
        } finally {
            em.close();
        }
    }

    public int getProyectoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Proyecto> rt = cq.from(Proyecto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
