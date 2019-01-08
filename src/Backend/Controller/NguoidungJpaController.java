/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Controller;

import Backend.Controller.exceptions.IllegalOrphanException;
import Backend.Controller.exceptions.NonexistentEntityException;
import Backend.Model.Nguoidung;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Backend.Model.Noidung;
import java.util.ArrayList;
import java.util.Collection;
import Backend.Model.NguoidungDuan;
import Foundation.AlertMess;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author vukho
 */
public class NguoidungJpaController implements Serializable {

    public EntityManager getEntityManager() {
        return Persistence.createEntityManagerFactory("eProject2PU").createEntityManager();
    }

    public void create(Nguoidung nguoidung) {
        if (nguoidung.getNoidungCollection() == null) {
            nguoidung.setNoidungCollection(new ArrayList<Noidung>());
        }
        if (nguoidung.getNguoidungDuanCollection() == null) {
            nguoidung.setNguoidungDuanCollection(new ArrayList<NguoidungDuan>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Noidung> attachedNoidungCollection = new ArrayList<Noidung>();
            for (Noidung noidungCollectionNoidungToAttach : nguoidung.getNoidungCollection()) {
                noidungCollectionNoidungToAttach = em.getReference(noidungCollectionNoidungToAttach.getClass(), noidungCollectionNoidungToAttach.getIDNoiDung());
                attachedNoidungCollection.add(noidungCollectionNoidungToAttach);
            }
            nguoidung.setNoidungCollection(attachedNoidungCollection);
            Collection<NguoidungDuan> attachedNguoidungDuanCollection = new ArrayList<NguoidungDuan>();
            for (NguoidungDuan nguoidungDuanCollectionNguoidungDuanToAttach : nguoidung.getNguoidungDuanCollection()) {
                nguoidungDuanCollectionNguoidungDuanToAttach = em.getReference(nguoidungDuanCollectionNguoidungDuanToAttach.getClass(), nguoidungDuanCollectionNguoidungDuanToAttach.getNguoidungDuanPK());
                attachedNguoidungDuanCollection.add(nguoidungDuanCollectionNguoidungDuanToAttach);
            }
            nguoidung.setNguoidungDuanCollection(attachedNguoidungDuanCollection);
            em.persist(nguoidung);
            for (Noidung noidungCollectionNoidung : nguoidung.getNoidungCollection()) {
                noidungCollectionNoidung.getNguoidungCollection().add(nguoidung);
                noidungCollectionNoidung = em.merge(noidungCollectionNoidung);
            }
            for (NguoidungDuan nguoidungDuanCollectionNguoidungDuan : nguoidung.getNguoidungDuanCollection()) {
                Nguoidung oldNguoidungOfNguoidungDuanCollectionNguoidungDuan = nguoidungDuanCollectionNguoidungDuan.getNguoidung();
                nguoidungDuanCollectionNguoidungDuan.setNguoidung(nguoidung);
                nguoidungDuanCollectionNguoidungDuan = em.merge(nguoidungDuanCollectionNguoidungDuan);
                if (oldNguoidungOfNguoidungDuanCollectionNguoidungDuan != null) {
                    oldNguoidungOfNguoidungDuanCollectionNguoidungDuan.getNguoidungDuanCollection().remove(nguoidungDuanCollectionNguoidungDuan);
                    oldNguoidungOfNguoidungDuanCollectionNguoidungDuan = em.merge(oldNguoidungOfNguoidungDuanCollectionNguoidungDuan);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Nguoidung nguoidung) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Nguoidung persistentNguoidung = em.find(Nguoidung.class, nguoidung.getId());
            Collection<Noidung> noidungCollectionOld = persistentNguoidung.getNoidungCollection();
            Collection<Noidung> noidungCollectionNew = nguoidung.getNoidungCollection();
            Collection<NguoidungDuan> nguoidungDuanCollectionOld = persistentNguoidung.getNguoidungDuanCollection();
            Collection<NguoidungDuan> nguoidungDuanCollectionNew = nguoidung.getNguoidungDuanCollection();
            List<String> illegalOrphanMessages = null;
            for (NguoidungDuan nguoidungDuanCollectionOldNguoidungDuan : nguoidungDuanCollectionOld) {
                if (!nguoidungDuanCollectionNew.contains(nguoidungDuanCollectionOldNguoidungDuan)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain NguoidungDuan " + nguoidungDuanCollectionOldNguoidungDuan + " since its nguoidung field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Noidung> attachedNoidungCollectionNew = new ArrayList<Noidung>();
            for (Noidung noidungCollectionNewNoidungToAttach : noidungCollectionNew) {
                noidungCollectionNewNoidungToAttach = em.getReference(noidungCollectionNewNoidungToAttach.getClass(), noidungCollectionNewNoidungToAttach.getIDNoiDung());
                attachedNoidungCollectionNew.add(noidungCollectionNewNoidungToAttach);
            }
            noidungCollectionNew = attachedNoidungCollectionNew;
            nguoidung.setNoidungCollection(noidungCollectionNew);
            Collection<NguoidungDuan> attachedNguoidungDuanCollectionNew = new ArrayList<NguoidungDuan>();
            for (NguoidungDuan nguoidungDuanCollectionNewNguoidungDuanToAttach : nguoidungDuanCollectionNew) {
                nguoidungDuanCollectionNewNguoidungDuanToAttach = em.getReference(nguoidungDuanCollectionNewNguoidungDuanToAttach.getClass(), nguoidungDuanCollectionNewNguoidungDuanToAttach.getNguoidungDuanPK());
                attachedNguoidungDuanCollectionNew.add(nguoidungDuanCollectionNewNguoidungDuanToAttach);
            }
            nguoidungDuanCollectionNew = attachedNguoidungDuanCollectionNew;
            nguoidung.setNguoidungDuanCollection(nguoidungDuanCollectionNew);
            nguoidung = em.merge(nguoidung);
            for (Noidung noidungCollectionOldNoidung : noidungCollectionOld) {
                if (!noidungCollectionNew.contains(noidungCollectionOldNoidung)) {
                    noidungCollectionOldNoidung.getNguoidungCollection().remove(nguoidung);
                    noidungCollectionOldNoidung = em.merge(noidungCollectionOldNoidung);
                }
            }
            for (Noidung noidungCollectionNewNoidung : noidungCollectionNew) {
                if (!noidungCollectionOld.contains(noidungCollectionNewNoidung)) {
                    noidungCollectionNewNoidung.getNguoidungCollection().add(nguoidung);
                    noidungCollectionNewNoidung = em.merge(noidungCollectionNewNoidung);
                }
            }
            for (NguoidungDuan nguoidungDuanCollectionNewNguoidungDuan : nguoidungDuanCollectionNew) {
                if (!nguoidungDuanCollectionOld.contains(nguoidungDuanCollectionNewNguoidungDuan)) {
                    Nguoidung oldNguoidungOfNguoidungDuanCollectionNewNguoidungDuan = nguoidungDuanCollectionNewNguoidungDuan.getNguoidung();
                    nguoidungDuanCollectionNewNguoidungDuan.setNguoidung(nguoidung);
                    nguoidungDuanCollectionNewNguoidungDuan = em.merge(nguoidungDuanCollectionNewNguoidungDuan);
                    if (oldNguoidungOfNguoidungDuanCollectionNewNguoidungDuan != null && !oldNguoidungOfNguoidungDuanCollectionNewNguoidungDuan.equals(nguoidung)) {
                        oldNguoidungOfNguoidungDuanCollectionNewNguoidungDuan.getNguoidungDuanCollection().remove(nguoidungDuanCollectionNewNguoidungDuan);
                        oldNguoidungOfNguoidungDuanCollectionNewNguoidungDuan = em.merge(oldNguoidungOfNguoidungDuanCollectionNewNguoidungDuan);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = nguoidung.getId();
                if (findNguoidung(id) == null) {
                    throw new NonexistentEntityException("The nguoidung with id " + id + " no longer exists.");
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
            Nguoidung nguoidung;
            try {
                nguoidung = em.getReference(Nguoidung.class, id);
                nguoidung.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nguoidung with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<NguoidungDuan> nguoidungDuanCollectionOrphanCheck = nguoidung.getNguoidungDuanCollection();
            for (NguoidungDuan nguoidungDuanCollectionOrphanCheckNguoidungDuan : nguoidungDuanCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Nguoidung (" + nguoidung + ") cannot be destroyed since the NguoidungDuan " + nguoidungDuanCollectionOrphanCheckNguoidungDuan + " in its nguoidungDuanCollection field has a non-nullable nguoidung field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Noidung> noidungCollection = nguoidung.getNoidungCollection();
            for (Noidung noidungCollectionNoidung : noidungCollection) {
                noidungCollectionNoidung.getNguoidungCollection().remove(nguoidung);
                noidungCollectionNoidung = em.merge(noidungCollectionNoidung);
            }
            em.remove(nguoidung);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Nguoidung> findNguoidungEntities() {
        return findNguoidungEntities(true, -1, -1);
    }

    public List<Nguoidung> findNguoidungEntities(int maxResults, int firstResult) {
        return findNguoidungEntities(false, maxResults, firstResult);
    }

    private List<Nguoidung> findNguoidungEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Nguoidung.class));
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

    public Nguoidung findNguoidung(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Nguoidung.class, id);
        } finally {
            em.close();
        }
    }

    public List<Nguoidung> findNguoidungByEmail(String Email) {
        EntityManager em = getEntityManager();
        try {
            String hql = String.format("select a from %s a where a.email = :Email", Nguoidung.class.getName());
            return em.createQuery(hql).setParameter("Email", Email).getResultList();
        } catch (Exception e) {
            AlertMess alert = new AlertMess("Đã xảy ra lỗi khi truy cập cơ sở dữ liệu");
            alert.ShowMessError();
            System.out.println(e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

    public void EditStatusLogin(Nguoidung user, boolean status) {
        try {
            user.setTrangThaiDangNhap(status);
            edit(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int getNguoidungCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Nguoidung> rt = cq.from(Nguoidung.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
