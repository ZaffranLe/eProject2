/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Controller;

import Backend.Controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Backend.Model.Duan;
import Backend.Model.Nhatky;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author vukho
 */
public class NhatkyJpaController implements Serializable {

    public EntityManager getEntityManager() {
        return Persistence.createEntityManagerFactory("eProject2PU").createEntityManager();
    }
    public void create(Nhatky nhatky) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Duan IDDuAn = nhatky.getIDDuAn();
            if (IDDuAn != null) {
                IDDuAn = em.getReference(IDDuAn.getClass(), IDDuAn.getIDDuAn());
                nhatky.setIDDuAn(IDDuAn);
            }
            em.persist(nhatky);
            if (IDDuAn != null) {
                IDDuAn.getNhatkyCollection().add(nhatky);
                IDDuAn = em.merge(IDDuAn);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Nhatky nhatky) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Nhatky persistentNhatky = em.find(Nhatky.class, nhatky.getId());
            Duan IDDuAnOld = persistentNhatky.getIDDuAn();
            Duan IDDuAnNew = nhatky.getIDDuAn();
            if (IDDuAnNew != null) {
                IDDuAnNew = em.getReference(IDDuAnNew.getClass(), IDDuAnNew.getIDDuAn());
                nhatky.setIDDuAn(IDDuAnNew);
            }
            nhatky = em.merge(nhatky);
            if (IDDuAnOld != null && !IDDuAnOld.equals(IDDuAnNew)) {
                IDDuAnOld.getNhatkyCollection().remove(nhatky);
                IDDuAnOld = em.merge(IDDuAnOld);
            }
            if (IDDuAnNew != null && !IDDuAnNew.equals(IDDuAnOld)) {
                IDDuAnNew.getNhatkyCollection().add(nhatky);
                IDDuAnNew = em.merge(IDDuAnNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = nhatky.getId();
                if (findNhatky(id) == null) {
                    throw new NonexistentEntityException("The nhatky with id " + id + " no longer exists.");
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
            Nhatky nhatky;
            try {
                nhatky = em.getReference(Nhatky.class, id);
                nhatky.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nhatky with id " + id + " no longer exists.", enfe);
            }
            Duan IDDuAn = nhatky.getIDDuAn();
            if (IDDuAn != null) {
                IDDuAn.getNhatkyCollection().remove(nhatky);
                IDDuAn = em.merge(IDDuAn);
            }
            em.remove(nhatky);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Nhatky> findNhatkyEntities() {
        return findNhatkyEntities(true, -1, -1);
    }

    public List<Nhatky> findNhatkyEntities(int maxResults, int firstResult) {
        return findNhatkyEntities(false, maxResults, firstResult);
    }

    private List<Nhatky> findNhatkyEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Nhatky.class));
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

    public Nhatky findNhatky(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Nhatky.class, id);
        } finally {
            em.close();
        }
    }

    public int getNhatkyCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Nhatky> rt = cq.from(Nhatky.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
        public List<Nhatky> getAllByProject(String idDuAn) {
        EntityManager em = getEntityManager();
        try {
            String jpql = String.format("Select a from Nhatky a   where a.iDDuAn.iDDuAn =:idDuAn");
            return em.createQuery(jpql).setParameter("idDuAn", idDuAn).getResultList();

        } catch (Exception e) {

            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            return null;
        } finally {
            em.close();
        }
    }
    
}
