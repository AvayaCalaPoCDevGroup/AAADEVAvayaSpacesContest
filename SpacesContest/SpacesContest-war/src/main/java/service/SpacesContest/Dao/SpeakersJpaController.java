package service.SpacesContest.Dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import service.SpacesContest.Dao.Exceptions.NonexistentEntityException;
import service.SpacesContest.Entity.Speakers;


/**
 *
 * @author umansilla
 */
public class SpeakersJpaController implements Serializable {

    public SpeakersJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Speakers speakers) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(speakers);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Speakers speakers) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            speakers = em.merge(speakers);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = speakers.getId();
                if (findSpeakers(id) == null) {
                    throw new NonexistentEntityException("The speakers with id " + id + " no longer exists.");
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
            Speakers speakers;
            try {
                speakers = em.getReference(Speakers.class, id);
                speakers.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The speakers with id " + id + " no longer exists.", enfe);
            }
            em.remove(speakers);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Speakers> findSpeakersEntities() {
        return findSpeakersEntities(true, -1, -1);
    }

    public List<Speakers> findSpeakersEntities(int maxResults, int firstResult) {
        return findSpeakersEntities(false, maxResults, firstResult);
    }

    private List<Speakers> findSpeakersEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Speakers.class));
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

    public Speakers findSpeakers(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Speakers.class, id);
        } finally {
            em.close();
        }
    }

    public int getSpeakersCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Speakers> rt = cq.from(Speakers.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Speakers> obtenerSpeakersByCreadoPor(String creadoPor) {
        EntityManager em = getEntityManager();
        TypedQuery<Speakers> namedQuery = em.createNamedQuery("Speakers.findByCreadopor", Speakers.class);
        namedQuery.setParameter("creadopor", creadoPor);
        List<Speakers> result = namedQuery.getResultList();
        if (!result.isEmpty()) {
            return result;
        }
        return new ArrayList<>();
    }

    public Speakers obtenerSpeakersByIdspeakerAndCreadoPor(String idSpeaker, String creadoPor) {
        EntityManager em = getEntityManager();
        TypedQuery<Speakers> namedQuery = em.createNamedQuery("Speakers.findByIdspeakerAndCreadoPor", Speakers.class);
        namedQuery.setParameter("idspeaker", idSpeaker);
        namedQuery.setParameter("creadopor", creadoPor);
        List<Speakers> result = namedQuery.getResultList();
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }
    
}
