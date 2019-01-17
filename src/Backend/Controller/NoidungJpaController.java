/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Controller;

import Backend.Controller.exceptions.NonexistentEntityException;
import Backend.Controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Backend.Model.Duan;
import Backend.Model.Nguoidung;
import Backend.Model.Noidung;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author vukho
 */
public class NoidungJpaController implements Serializable {

    public EntityManager getEntityManager() {
        return Persistence.createEntityManagerFactory("eProject2PU").createEntityManager();
    }

    public void create(Noidung noidung) throws PreexistingEntityException, Exception {
        if (noidung.getNguoidungCollection() == null) {
            noidung.setNguoidungCollection(new ArrayList<Nguoidung>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Duan IDDuAn = noidung.getIDDuAn();
            if (IDDuAn != null) {
                IDDuAn = em.getReference(IDDuAn.getClass(), IDDuAn.getIDDuAn());
                noidung.setIDDuAn(IDDuAn);
            }
            Collection<Nguoidung> attachedNguoidungCollection = new ArrayList<Nguoidung>();
            for (Nguoidung nguoidungCollectionNguoidungToAttach : noidung.getNguoidungCollection()) {
                nguoidungCollectionNguoidungToAttach = em.getReference(nguoidungCollectionNguoidungToAttach.getClass(), nguoidungCollectionNguoidungToAttach.getId());
                attachedNguoidungCollection.add(nguoidungCollectionNguoidungToAttach);
            }
            noidung.setNguoidungCollection(attachedNguoidungCollection);
            em.persist(noidung);
            if (IDDuAn != null) {
                IDDuAn.getNoidungCollection().add(noidung);
                IDDuAn = em.merge(IDDuAn);
            }
            for (Nguoidung nguoidungCollectionNguoidung : noidung.getNguoidungCollection()) {
                nguoidungCollectionNguoidung.getNoidungCollection().add(noidung);
                nguoidungCollectionNguoidung = em.merge(nguoidungCollectionNguoidung);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findNoidung(noidung.getIDNoiDung()) != null) {
                throw new PreexistingEntityException("Noidung " + noidung + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Noidung noidung) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Noidung persistentNoidung = em.find(Noidung.class, noidung.getIDNoiDung());
            Duan IDDuAnOld = persistentNoidung.getIDDuAn();
            Duan IDDuAnNew = noidung.getIDDuAn();
            Collection<Nguoidung> nguoidungCollectionOld = persistentNoidung.getNguoidungCollection();
            Collection<Nguoidung> nguoidungCollectionNew = noidung.getNguoidungCollection();
            if (IDDuAnNew != null) {
                IDDuAnNew = em.getReference(IDDuAnNew.getClass(), IDDuAnNew.getIDDuAn());
                noidung.setIDDuAn(IDDuAnNew);
            }
            Collection<Nguoidung> attachedNguoidungCollectionNew = new ArrayList<Nguoidung>();
            for (Nguoidung nguoidungCollectionNewNguoidungToAttach : nguoidungCollectionNew) {
                nguoidungCollectionNewNguoidungToAttach = em.getReference(nguoidungCollectionNewNguoidungToAttach.getClass(), nguoidungCollectionNewNguoidungToAttach.getId());
                attachedNguoidungCollectionNew.add(nguoidungCollectionNewNguoidungToAttach);
            }
            nguoidungCollectionNew = attachedNguoidungCollectionNew;
            noidung.setNguoidungCollection(nguoidungCollectionNew);
            noidung = em.merge(noidung);
            if (IDDuAnOld != null && !IDDuAnOld.equals(IDDuAnNew)) {
                IDDuAnOld.getNoidungCollection().remove(noidung);
                IDDuAnOld = em.merge(IDDuAnOld);
            }
            if (IDDuAnNew != null && !IDDuAnNew.equals(IDDuAnOld)) {
                IDDuAnNew.getNoidungCollection().add(noidung);
                IDDuAnNew = em.merge(IDDuAnNew);
            }
            for (Nguoidung nguoidungCollectionOldNguoidung : nguoidungCollectionOld) {
                if (!nguoidungCollectionNew.contains(nguoidungCollectionOldNguoidung)) {
                    nguoidungCollectionOldNguoidung.getNoidungCollection().remove(noidung);
                    nguoidungCollectionOldNguoidung = em.merge(nguoidungCollectionOldNguoidung);
                }
            }
            for (Nguoidung nguoidungCollectionNewNguoidung : nguoidungCollectionNew) {
                if (!nguoidungCollectionOld.contains(nguoidungCollectionNewNguoidung)) {
                    nguoidungCollectionNewNguoidung.getNoidungCollection().add(noidung);
                    nguoidungCollectionNewNguoidung = em.merge(nguoidungCollectionNewNguoidung);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = noidung.getIDNoiDung();
                if (findNoidung(id) == null) {
                    throw new NonexistentEntityException("The noidung with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Noidung noidung;
            try {
                noidung = em.getReference(Noidung.class, id);
                noidung.getIDNoiDung();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The noidung with id " + id + " no longer exists.", enfe);
            }
            Duan IDDuAn = noidung.getIDDuAn();
            if (IDDuAn != null) {
                IDDuAn.getNoidungCollection().remove(noidung);
                IDDuAn = em.merge(IDDuAn);
            }
            Collection<Nguoidung> nguoidungCollection = noidung.getNguoidungCollection();
            for (Nguoidung nguoidungCollectionNguoidung : nguoidungCollection) {
                nguoidungCollectionNguoidung.getNoidungCollection().remove(noidung);
                nguoidungCollectionNguoidung = em.merge(nguoidungCollectionNguoidung);
            }
            em.remove(noidung);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Noidung> findNoidungEntities() {
        return findNoidungEntities(true, -1, -1);
    }

    public List<Noidung> findNoidungEntities(int maxResults, int firstResult) {
        return findNoidungEntities(false, maxResults, firstResult);
    }

    private List<Noidung> findNoidungEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Noidung.class));
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

    public Noidung findNoidung(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Noidung.class, id);
        } finally {
            em.close();
        }
    }

    public int getNoidungCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Noidung> rt = cq.from(Noidung.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    /**
     * Lấy ra các task trong dự án theo trạng thái
     *
     * @param idDuAn id của dự án được chọn
     * @param trangThaiTask trạng thái của task
     * @return danh sách các task có trạng thái và thuộc dự án đã chọn
     */
    public List<Noidung> getAllByProject(String idDuAn) {
        EntityManager em = getEntityManager();
        try {
            String jpql = String.format("Select a from Noidung a   where a.iDDuAn.iDDuAn =:idDuAn");
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
