/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Controller;

import Backend.Controller.exceptions.IllegalOrphanException;
import Backend.Controller.exceptions.NonexistentEntityException;
import Backend.Controller.exceptions.PreexistingEntityException;
import Backend.Model.Duan;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Backend.Model.NguoidungDuan;
import java.util.ArrayList;
import java.util.Collection;
import Backend.Model.Nhatky;
import Backend.Model.Noidung;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author vukho
 */
public class DuanJpaController implements Serializable {

    public EntityManager getEntityManager() {
        return Persistence.createEntityManagerFactory("eProject2PU").createEntityManager();
    }

    public void create(Duan duan) throws PreexistingEntityException, Exception {
        if (duan.getNguoidungDuanCollection() == null) {
            duan.setNguoidungDuanCollection(new ArrayList<NguoidungDuan>());
        }
        if (duan.getNhatkyCollection() == null) {
            duan.setNhatkyCollection(new ArrayList<Nhatky>());
        }
        if (duan.getNoidungCollection() == null) {
            duan.setNoidungCollection(new ArrayList<Noidung>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<NguoidungDuan> attachedNguoidungDuanCollection = new ArrayList<NguoidungDuan>();
            for (NguoidungDuan nguoidungDuanCollectionNguoidungDuanToAttach : duan.getNguoidungDuanCollection()) {
                nguoidungDuanCollectionNguoidungDuanToAttach = em.getReference(nguoidungDuanCollectionNguoidungDuanToAttach.getClass(), nguoidungDuanCollectionNguoidungDuanToAttach.getNguoidungDuanPK());
                attachedNguoidungDuanCollection.add(nguoidungDuanCollectionNguoidungDuanToAttach);
            }
            duan.setNguoidungDuanCollection(attachedNguoidungDuanCollection);
            Collection<Nhatky> attachedNhatkyCollection = new ArrayList<Nhatky>();
            for (Nhatky nhatkyCollectionNhatkyToAttach : duan.getNhatkyCollection()) {
                nhatkyCollectionNhatkyToAttach = em.getReference(nhatkyCollectionNhatkyToAttach.getClass(), nhatkyCollectionNhatkyToAttach.getId());
                attachedNhatkyCollection.add(nhatkyCollectionNhatkyToAttach);
            }
            duan.setNhatkyCollection(attachedNhatkyCollection);
            Collection<Noidung> attachedNoidungCollection = new ArrayList<Noidung>();
            for (Noidung noidungCollectionNoidungToAttach : duan.getNoidungCollection()) {
                noidungCollectionNoidungToAttach = em.getReference(noidungCollectionNoidungToAttach.getClass(), noidungCollectionNoidungToAttach.getIDNoiDung());
                attachedNoidungCollection.add(noidungCollectionNoidungToAttach);
            }
            duan.setNoidungCollection(attachedNoidungCollection);
            em.persist(duan);
            for (NguoidungDuan nguoidungDuanCollectionNguoidungDuan : duan.getNguoidungDuanCollection()) {
                Duan oldDuanOfNguoidungDuanCollectionNguoidungDuan = nguoidungDuanCollectionNguoidungDuan.getDuan();
                nguoidungDuanCollectionNguoidungDuan.setDuan(duan);
                nguoidungDuanCollectionNguoidungDuan = em.merge(nguoidungDuanCollectionNguoidungDuan);
                if (oldDuanOfNguoidungDuanCollectionNguoidungDuan != null) {
                    oldDuanOfNguoidungDuanCollectionNguoidungDuan.getNguoidungDuanCollection().remove(nguoidungDuanCollectionNguoidungDuan);
                    oldDuanOfNguoidungDuanCollectionNguoidungDuan = em.merge(oldDuanOfNguoidungDuanCollectionNguoidungDuan);
                }
            }
            for (Nhatky nhatkyCollectionNhatky : duan.getNhatkyCollection()) {
                Duan oldIDDuAnOfNhatkyCollectionNhatky = nhatkyCollectionNhatky.getIDDuAn();
                nhatkyCollectionNhatky.setIDDuAn(duan);
                nhatkyCollectionNhatky = em.merge(nhatkyCollectionNhatky);
                if (oldIDDuAnOfNhatkyCollectionNhatky != null) {
                    oldIDDuAnOfNhatkyCollectionNhatky.getNhatkyCollection().remove(nhatkyCollectionNhatky);
                    oldIDDuAnOfNhatkyCollectionNhatky = em.merge(oldIDDuAnOfNhatkyCollectionNhatky);
                }
            }
            for (Noidung noidungCollectionNoidung : duan.getNoidungCollection()) {
                Duan oldIDDuAnOfNoidungCollectionNoidung = noidungCollectionNoidung.getIDDuAn();
                noidungCollectionNoidung.setIDDuAn(duan);
                noidungCollectionNoidung = em.merge(noidungCollectionNoidung);
                if (oldIDDuAnOfNoidungCollectionNoidung != null) {
                    oldIDDuAnOfNoidungCollectionNoidung.getNoidungCollection().remove(noidungCollectionNoidung);
                    oldIDDuAnOfNoidungCollectionNoidung = em.merge(oldIDDuAnOfNoidungCollectionNoidung);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDuan(duan.getIDDuAn()) != null) {
                throw new PreexistingEntityException("Duan " + duan + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Duan duan) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Duan persistentDuan = em.find(Duan.class, duan.getIDDuAn());
            Collection<NguoidungDuan> nguoidungDuanCollectionOld = persistentDuan.getNguoidungDuanCollection();
            Collection<NguoidungDuan> nguoidungDuanCollectionNew = duan.getNguoidungDuanCollection();
            Collection<Nhatky> nhatkyCollectionOld = persistentDuan.getNhatkyCollection();
            Collection<Nhatky> nhatkyCollectionNew = duan.getNhatkyCollection();
            Collection<Noidung> noidungCollectionOld = persistentDuan.getNoidungCollection();
            Collection<Noidung> noidungCollectionNew = duan.getNoidungCollection();
            List<String> illegalOrphanMessages = null;
            for (NguoidungDuan nguoidungDuanCollectionOldNguoidungDuan : nguoidungDuanCollectionOld) {
                if (!nguoidungDuanCollectionNew.contains(nguoidungDuanCollectionOldNguoidungDuan)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain NguoidungDuan " + nguoidungDuanCollectionOldNguoidungDuan + " since its duan field is not nullable.");
                }
            }
            for (Nhatky nhatkyCollectionOldNhatky : nhatkyCollectionOld) {
                if (!nhatkyCollectionNew.contains(nhatkyCollectionOldNhatky)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Nhatky " + nhatkyCollectionOldNhatky + " since its IDDuAn field is not nullable.");
                }
            }
            for (Noidung noidungCollectionOldNoidung : noidungCollectionOld) {
                if (!noidungCollectionNew.contains(noidungCollectionOldNoidung)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Noidung " + noidungCollectionOldNoidung + " since its IDDuAn field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<NguoidungDuan> attachedNguoidungDuanCollectionNew = new ArrayList<NguoidungDuan>();
            for (NguoidungDuan nguoidungDuanCollectionNewNguoidungDuanToAttach : nguoidungDuanCollectionNew) {
                nguoidungDuanCollectionNewNguoidungDuanToAttach = em.getReference(nguoidungDuanCollectionNewNguoidungDuanToAttach.getClass(), nguoidungDuanCollectionNewNguoidungDuanToAttach.getNguoidungDuanPK());
                attachedNguoidungDuanCollectionNew.add(nguoidungDuanCollectionNewNguoidungDuanToAttach);
            }
            nguoidungDuanCollectionNew = attachedNguoidungDuanCollectionNew;
            duan.setNguoidungDuanCollection(nguoidungDuanCollectionNew);
            Collection<Nhatky> attachedNhatkyCollectionNew = new ArrayList<Nhatky>();
            for (Nhatky nhatkyCollectionNewNhatkyToAttach : nhatkyCollectionNew) {
                nhatkyCollectionNewNhatkyToAttach = em.getReference(nhatkyCollectionNewNhatkyToAttach.getClass(), nhatkyCollectionNewNhatkyToAttach.getId());
                attachedNhatkyCollectionNew.add(nhatkyCollectionNewNhatkyToAttach);
            }
            nhatkyCollectionNew = attachedNhatkyCollectionNew;
            duan.setNhatkyCollection(nhatkyCollectionNew);
            Collection<Noidung> attachedNoidungCollectionNew = new ArrayList<Noidung>();
            for (Noidung noidungCollectionNewNoidungToAttach : noidungCollectionNew) {
                noidungCollectionNewNoidungToAttach = em.getReference(noidungCollectionNewNoidungToAttach.getClass(), noidungCollectionNewNoidungToAttach.getIDNoiDung());
                attachedNoidungCollectionNew.add(noidungCollectionNewNoidungToAttach);
            }
            noidungCollectionNew = attachedNoidungCollectionNew;
            duan.setNoidungCollection(noidungCollectionNew);
            duan = em.merge(duan);
            for (NguoidungDuan nguoidungDuanCollectionNewNguoidungDuan : nguoidungDuanCollectionNew) {
                if (!nguoidungDuanCollectionOld.contains(nguoidungDuanCollectionNewNguoidungDuan)) {
                    Duan oldDuanOfNguoidungDuanCollectionNewNguoidungDuan = nguoidungDuanCollectionNewNguoidungDuan.getDuan();
                    nguoidungDuanCollectionNewNguoidungDuan.setDuan(duan);
                    nguoidungDuanCollectionNewNguoidungDuan = em.merge(nguoidungDuanCollectionNewNguoidungDuan);
                    if (oldDuanOfNguoidungDuanCollectionNewNguoidungDuan != null && !oldDuanOfNguoidungDuanCollectionNewNguoidungDuan.equals(duan)) {
                        oldDuanOfNguoidungDuanCollectionNewNguoidungDuan.getNguoidungDuanCollection().remove(nguoidungDuanCollectionNewNguoidungDuan);
                        oldDuanOfNguoidungDuanCollectionNewNguoidungDuan = em.merge(oldDuanOfNguoidungDuanCollectionNewNguoidungDuan);
                    }
                }
            }
            for (Nhatky nhatkyCollectionNewNhatky : nhatkyCollectionNew) {
                if (!nhatkyCollectionOld.contains(nhatkyCollectionNewNhatky)) {
                    Duan oldIDDuAnOfNhatkyCollectionNewNhatky = nhatkyCollectionNewNhatky.getIDDuAn();
                    nhatkyCollectionNewNhatky.setIDDuAn(duan);
                    nhatkyCollectionNewNhatky = em.merge(nhatkyCollectionNewNhatky);
                    if (oldIDDuAnOfNhatkyCollectionNewNhatky != null && !oldIDDuAnOfNhatkyCollectionNewNhatky.equals(duan)) {
                        oldIDDuAnOfNhatkyCollectionNewNhatky.getNhatkyCollection().remove(nhatkyCollectionNewNhatky);
                        oldIDDuAnOfNhatkyCollectionNewNhatky = em.merge(oldIDDuAnOfNhatkyCollectionNewNhatky);
                    }
                }
            }
            for (Noidung noidungCollectionNewNoidung : noidungCollectionNew) {
                if (!noidungCollectionOld.contains(noidungCollectionNewNoidung)) {
                    Duan oldIDDuAnOfNoidungCollectionNewNoidung = noidungCollectionNewNoidung.getIDDuAn();
                    noidungCollectionNewNoidung.setIDDuAn(duan);
                    noidungCollectionNewNoidung = em.merge(noidungCollectionNewNoidung);
                    if (oldIDDuAnOfNoidungCollectionNewNoidung != null && !oldIDDuAnOfNoidungCollectionNewNoidung.equals(duan)) {
                        oldIDDuAnOfNoidungCollectionNewNoidung.getNoidungCollection().remove(noidungCollectionNewNoidung);
                        oldIDDuAnOfNoidungCollectionNewNoidung = em.merge(oldIDDuAnOfNoidungCollectionNewNoidung);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = duan.getIDDuAn();
                if (findDuan(id) == null) {
                    throw new NonexistentEntityException("The duan with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Duan duan;
            try {
                duan = em.getReference(Duan.class, id);
                duan.getIDDuAn();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The duan with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<NguoidungDuan> nguoidungDuanCollectionOrphanCheck = duan.getNguoidungDuanCollection();
            for (NguoidungDuan nguoidungDuanCollectionOrphanCheckNguoidungDuan : nguoidungDuanCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Duan (" + duan + ") cannot be destroyed since the NguoidungDuan " + nguoidungDuanCollectionOrphanCheckNguoidungDuan + " in its nguoidungDuanCollection field has a non-nullable duan field.");
            }
            Collection<Nhatky> nhatkyCollectionOrphanCheck = duan.getNhatkyCollection();
            for (Nhatky nhatkyCollectionOrphanCheckNhatky : nhatkyCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Duan (" + duan + ") cannot be destroyed since the Nhatky " + nhatkyCollectionOrphanCheckNhatky + " in its nhatkyCollection field has a non-nullable IDDuAn field.");
            }
            Collection<Noidung> noidungCollectionOrphanCheck = duan.getNoidungCollection();
            for (Noidung noidungCollectionOrphanCheckNoidung : noidungCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Duan (" + duan + ") cannot be destroyed since the Noidung " + noidungCollectionOrphanCheckNoidung + " in its noidungCollection field has a non-nullable IDDuAn field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(duan);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Duan> findDuanEntities() {
        return findDuanEntities(true, -1, -1);
    }

    public List<Duan> findDuanEntities(int maxResults, int firstResult) {
        return findDuanEntities(false, maxResults, firstResult);
    }

    private List<Duan> findDuanEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Duan.class));
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

    public Duan findDuan(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Duan.class, id);
        } finally {
            em.close();
        }
    }

    public int getDuanCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Duan> rt = cq.from(Duan.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
