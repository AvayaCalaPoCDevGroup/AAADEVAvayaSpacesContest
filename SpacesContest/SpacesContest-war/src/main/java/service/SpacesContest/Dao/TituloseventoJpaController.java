package service.SpacesContest.Dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import service.SpacesContest.Dao.Exceptions.NonexistentEntityException;
import service.SpacesContest.Entity.Titulosevento;

/**
 *
 * @author umansilla
 */
public class TituloseventoJpaController implements Serializable {

    public TituloseventoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Titulosevento titulosevento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(titulosevento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Titulosevento titulosevento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            titulosevento = em.merge(titulosevento);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = titulosevento.getId();
                if (findTitulosevento(id) == null) {
                    throw new NonexistentEntityException("The titulosevento with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Titulosevento titulosevento;
            try {
                titulosevento = em.getReference(Titulosevento.class, id);
                titulosevento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The titulosevento with id " + id + " no longer exists.", enfe);
            }
            em.remove(titulosevento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Titulosevento> findTituloseventoEntities() {
        return findTituloseventoEntities(true, -1, -1);
    }

    public List<Titulosevento> findTituloseventoEntities(int maxResults, int firstResult) {
        return findTituloseventoEntities(false, maxResults, firstResult);
    }

    private List<Titulosevento> findTituloseventoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Titulosevento.class));
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

    public Titulosevento findTitulosevento(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Titulosevento.class, id);
        } finally {
            em.close();
        }
    }

    public int getTituloseventoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Titulosevento> rt = cq.from(Titulosevento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Titulosevento obtenerTitulosEventoPorIdEventoANDCreadoPor(String idEvento, String creadoPor) {
        EntityManager em = getEntityManager();
        TypedQuery<Titulosevento> namedQuery = em.createNamedQuery("Titulosevento.findByIdeventoAndCreadoPor", Titulosevento.class);
        namedQuery.setParameter("idevento", idEvento);
        namedQuery.setParameter("creadopor", creadoPor);
        List<Titulosevento> result = namedQuery.getResultList();
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

}
