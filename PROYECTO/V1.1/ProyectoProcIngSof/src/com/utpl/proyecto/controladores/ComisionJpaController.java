/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.utpl.proyecto.controladores;

import com.utpl.proyecto.Comision;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.utpl.proyecto.Personas;
import com.utpl.proyecto.controladores.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author cvcaraguay
 */
public class ComisionJpaController implements Serializable {

    public ComisionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Comision comision) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Personas idPersona2 = comision.getIdPersona2();
            if (idPersona2 != null) {
                idPersona2 = em.getReference(idPersona2.getClass(), idPersona2.getIdPersona());
                comision.setIdPersona2(idPersona2);
            }
            Personas idPersona3 = comision.getIdPersona3();
            if (idPersona3 != null) {
                idPersona3 = em.getReference(idPersona3.getClass(), idPersona3.getIdPersona());
                comision.setIdPersona3(idPersona3);
            }
            Personas idPersona4 = comision.getIdPersona4();
            if (idPersona4 != null) {
                idPersona4 = em.getReference(idPersona4.getClass(), idPersona4.getIdPersona());
                comision.setIdPersona4(idPersona4);
            }
            Personas idPersona1 = comision.getIdPersona1();
            if (idPersona1 != null) {
                idPersona1 = em.getReference(idPersona1.getClass(), idPersona1.getIdPersona());
                comision.setIdPersona1(idPersona1);
            }
            em.persist(comision);
            if (idPersona2 != null) {
                idPersona2.getComisionList().add(comision);
                idPersona2 = em.merge(idPersona2);
            }
            if (idPersona3 != null) {
                idPersona3.getComisionList().add(comision);
                idPersona3 = em.merge(idPersona3);
            }
            if (idPersona4 != null) {
                idPersona4.getComisionList().add(comision);
                idPersona4 = em.merge(idPersona4);
            }
            if (idPersona1 != null) {
                idPersona1.getComisionList().add(comision);
                idPersona1 = em.merge(idPersona1);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Comision comision) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Comision persistentComision = em.find(Comision.class, comision.getIdComision());
            Personas idPersona2Old = persistentComision.getIdPersona2();
            Personas idPersona2New = comision.getIdPersona2();
            Personas idPersona3Old = persistentComision.getIdPersona3();
            Personas idPersona3New = comision.getIdPersona3();
            Personas idPersona4Old = persistentComision.getIdPersona4();
            Personas idPersona4New = comision.getIdPersona4();
            Personas idPersona1Old = persistentComision.getIdPersona1();
            Personas idPersona1New = comision.getIdPersona1();
            if (idPersona2New != null) {
                idPersona2New = em.getReference(idPersona2New.getClass(), idPersona2New.getIdPersona());
                comision.setIdPersona2(idPersona2New);
            }
            if (idPersona3New != null) {
                idPersona3New = em.getReference(idPersona3New.getClass(), idPersona3New.getIdPersona());
                comision.setIdPersona3(idPersona3New);
            }
            if (idPersona4New != null) {
                idPersona4New = em.getReference(idPersona4New.getClass(), idPersona4New.getIdPersona());
                comision.setIdPersona4(idPersona4New);
            }
            if (idPersona1New != null) {
                idPersona1New = em.getReference(idPersona1New.getClass(), idPersona1New.getIdPersona());
                comision.setIdPersona1(idPersona1New);
            }
            comision = em.merge(comision);
            if (idPersona2Old != null && !idPersona2Old.equals(idPersona2New)) {
                idPersona2Old.getComisionList().remove(comision);
                idPersona2Old = em.merge(idPersona2Old);
            }
            if (idPersona2New != null && !idPersona2New.equals(idPersona2Old)) {
                idPersona2New.getComisionList().add(comision);
                idPersona2New = em.merge(idPersona2New);
            }
            if (idPersona3Old != null && !idPersona3Old.equals(idPersona3New)) {
                idPersona3Old.getComisionList().remove(comision);
                idPersona3Old = em.merge(idPersona3Old);
            }
            if (idPersona3New != null && !idPersona3New.equals(idPersona3Old)) {
                idPersona3New.getComisionList().add(comision);
                idPersona3New = em.merge(idPersona3New);
            }
            if (idPersona4Old != null && !idPersona4Old.equals(idPersona4New)) {
                idPersona4Old.getComisionList().remove(comision);
                idPersona4Old = em.merge(idPersona4Old);
            }
            if (idPersona4New != null && !idPersona4New.equals(idPersona4Old)) {
                idPersona4New.getComisionList().add(comision);
                idPersona4New = em.merge(idPersona4New);
            }
            if (idPersona1Old != null && !idPersona1Old.equals(idPersona1New)) {
                idPersona1Old.getComisionList().remove(comision);
                idPersona1Old = em.merge(idPersona1Old);
            }
            if (idPersona1New != null && !idPersona1New.equals(idPersona1Old)) {
                idPersona1New.getComisionList().add(comision);
                idPersona1New = em.merge(idPersona1New);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = comision.getIdComision();
                if (findComision(id) == null) {
                    throw new NonexistentEntityException("The comision with id " + id + " no longer exists.");
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
            Comision comision;
            try {
                comision = em.getReference(Comision.class, id);
                comision.getIdComision();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The comision with id " + id + " no longer exists.", enfe);
            }
            Personas idPersona2 = comision.getIdPersona2();
            if (idPersona2 != null) {
                idPersona2.getComisionList().remove(comision);
                idPersona2 = em.merge(idPersona2);
            }
            Personas idPersona3 = comision.getIdPersona3();
            if (idPersona3 != null) {
                idPersona3.getComisionList().remove(comision);
                idPersona3 = em.merge(idPersona3);
            }
            Personas idPersona4 = comision.getIdPersona4();
            if (idPersona4 != null) {
                idPersona4.getComisionList().remove(comision);
                idPersona4 = em.merge(idPersona4);
            }
            Personas idPersona1 = comision.getIdPersona1();
            if (idPersona1 != null) {
                idPersona1.getComisionList().remove(comision);
                idPersona1 = em.merge(idPersona1);
            }
            em.remove(comision);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Comision> findComisionEntities() {
        return findComisionEntities(true, -1, -1);
    }

    public List<Comision> findComisionEntities(int maxResults, int firstResult) {
        return findComisionEntities(false, maxResults, firstResult);
    }

    private List<Comision> findComisionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Comision.class));
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

    public Comision findComision(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Comision.class, id);
        } finally {
            em.close();
        }
    }

    public int getComisionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Comision> rt = cq.from(Comision.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
