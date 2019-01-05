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
import Backend.Model.Nguoidung;
import Backend.Model.Duan;
import Backend.Model.NguoidungDuan;
import Backend.Model.NguoidungDuanPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author vukho
 */
public class NguoidungDuanJpaController implements Serializable {

    public NguoidungDuanJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(NguoidungDuan nguoidungDuan) throws PreexistingEntityException, Exception {
        if (nguoidungDuan.getNguoidungDuanPK() == null) {
            nguoidungDuan.setNguoidungDuanPK(new NguoidungDuanPK());
        }
        nguoidungDuan.getNguoidungDuanPK().setIDDuAn(nguoidungDuan.getDuan().getIDDuAn());
        nguoidungDuan.getNguoidungDuanPK().setIDNguoiDung(nguoidungDuan.getNguoidung().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Nguoidung nguoidung = nguoidungDuan.getNguoidung();
            if (nguoidung != null) {
                nguoidung = em.getReference(nguoidung.getClass(), nguoidung.getId());
                nguoidungDuan.setNguoidung(nguoidung);
            }
            Duan duan = nguoidungDuan.getDuan();
            if (duan != null) {
                duan = em.getReference(duan.getClass(), duan.getIDDuAn());
                nguoidungDuan.setDuan(duan);
            }
            em.persist(nguoidungDuan);
            if (nguoidung != null) {
                nguoidung.getNguoidungDuanCollection().add(nguoidungDuan);
                nguoidung = em.merge(nguoidung);
            }
            if (duan != null) {
                duan.getNguoidungDuanCollection().add(nguoidungDuan);
                duan = em.merge(duan);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findNguoidungDuan(nguoidungDuan.getNguoidungDuanPK()) != null) {
                throw new PreexistingEntityException("NguoidungDuan " + nguoidungDuan + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(NguoidungDuan nguoidungDuan) throws NonexistentEntityException, Exception {
        nguoidungDuan.getNguoidungDuanPK().setIDDuAn(nguoidungDuan.getDuan().getIDDuAn());
        nguoidungDuan.getNguoidungDuanPK().setIDNguoiDung(nguoidungDuan.getNguoidung().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            NguoidungDuan persistentNguoidungDuan = em.find(NguoidungDuan.class, nguoidungDuan.getNguoidungDuanPK());
            Nguoidung nguoidungOld = persistentNguoidungDuan.getNguoidung();
            Nguoidung nguoidungNew = nguoidungDuan.getNguoidung();
            Duan duanOld = persistentNguoidungDuan.getDuan();
            Duan duanNew = nguoidungDuan.getDuan();
            if (nguoidungNew != null) {
                nguoidungNew = em.getReference(nguoidungNew.getClass(), nguoidungNew.getId());
                nguoidungDuan.setNguoidung(nguoidungNew);
            }
            if (duanNew != null) {
                duanNew = em.getReference(duanNew.getClass(), duanNew.getIDDuAn());
                nguoidungDuan.setDuan(duanNew);
            }
            nguoidungDuan = em.merge(nguoidungDuan);
            if (nguoidungOld != null && !nguoidungOld.equals(nguoidungNew)) {
                nguoidungOld.getNguoidungDuanCollection().remove(nguoidungDuan);
                nguoidungOld = em.merge(nguoidungOld);
            }
            if (nguoidungNew != null && !nguoidungNew.equals(nguoidungOld)) {
                nguoidungNew.getNguoidungDuanCollection().add(nguoidungDuan);
                nguoidungNew = em.merge(nguoidungNew);
            }
            if (duanOld != null && !duanOld.equals(duanNew)) {
                duanOld.getNguoidungDuanCollection().remove(nguoidungDuan);
                duanOld = em.merge(duanOld);
            }
            if (duanNew != null && !duanNew.equals(duanOld)) {
                duanNew.getNguoidungDuanCollection().add(nguoidungDuan);
                duanNew = em.merge(duanNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                NguoidungDuanPK id = nguoidungDuan.getNguoidungDuanPK();
                if (findNguoidungDuan(id) == null) {
                    throw new NonexistentEntityException("The nguoidungDuan with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(NguoidungDuanPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            NguoidungDuan nguoidungDuan;
            try {
                nguoidungDuan = em.getReference(NguoidungDuan.class, id);
                nguoidungDuan.getNguoidungDuanPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nguoidungDuan with id " + id + " no longer exists.", enfe);
            }
            Nguoidung nguoidung = nguoidungDuan.getNguoidung();
            if (nguoidung != null) {
                nguoidung.getNguoidungDuanCollection().remove(nguoidungDuan);
                nguoidung = em.merge(nguoidung);
            }
            Duan duan = nguoidungDuan.getDuan();
            if (duan != null) {
                duan.getNguoidungDuanCollection().remove(nguoidungDuan);
                duan = em.merge(duan);
            }
            em.remove(nguoidungDuan);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<NguoidungDuan> findNguoidungDuanEntities() {
        return findNguoidungDuanEntities(true, -1, -1);
    }

    public List<NguoidungDuan> findNguoidungDuanEntities(int maxResults, int firstResult) {
        return findNguoidungDuanEntities(false, maxResults, firstResult);
    }

    private List<NguoidungDuan> findNguoidungDuanEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(NguoidungDuan.class));
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

    public NguoidungDuan findNguoidungDuan(NguoidungDuanPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(NguoidungDuan.class, id);
        } finally {
            em.close();
        }
    }

    public int getNguoidungDuanCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<NguoidungDuan> rt = cq.from(NguoidungDuan.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
