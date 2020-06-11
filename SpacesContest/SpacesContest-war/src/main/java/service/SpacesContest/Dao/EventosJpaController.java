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
import service.SpacesContest.Entity.Eventos;


/**
 *
 * @author umansilla
 */
public class EventosJpaController implements Serializable {

    public EventosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Eventos eventos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(eventos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Eventos eventos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            eventos = em.merge(eventos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = eventos.getId();
                if (findEventos(id) == null) {
                    throw new NonexistentEntityException("The eventos with id " + id + " no longer exists.");
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
            Eventos eventos;
            try {
                eventos = em.getReference(Eventos.class, id);
                eventos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The eventos with id " + id + " no longer exists.", enfe);
            }
            em.remove(eventos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Eventos> findEventosEntities() {
        return findEventosEntities(true, -1, -1);
    }

    public List<Eventos> findEventosEntities(int maxResults, int firstResult) {
        return findEventosEntities(false, maxResults, firstResult);
    }

    private List<Eventos> findEventosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Eventos.class));
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

    public Eventos findEventos(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Eventos.class, id);
        } finally {
            em.close();
        }
    }

    public int getEventosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Eventos> rt = cq.from(Eventos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Eventos obtenerEventoPorIdEvento(String idEvento) {
        EntityManager em = getEntityManager();
        TypedQuery<Eventos> namedQuery = em.createNamedQuery("Eventos.findByIdevento", Eventos.class);
        namedQuery.setParameter("idevento", idEvento);
        List<Eventos> result = namedQuery.getResultList();
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

    public List<Eventos> obtenerEventosPorCreatedBy(String creadoPor) {
        EntityManager em = getEntityManager();
        TypedQuery<Eventos> namedQuery = em.createNamedQuery("Eventos.findByEstatusActivoAndcreadoPor", Eventos.class);
        namedQuery.setParameter("estatus", "ACTIVO");
        namedQuery.setParameter("creadopor", creadoPor);
        List<Eventos> result = namedQuery.getResultList();
        if (!result.isEmpty()) {
            return result;
        }
        return new ArrayList<>();
    }
    
    
      public Integer editarEventoAEstatusInactivo(Long id) throws Exception {
        //updateEstatusTerminated
        int statement;
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            statement = em.createNamedQuery("Eventos.updateEventoToEstatusInactivo", Eventos.class)
                    .setParameter("id", id)
                    .executeUpdate();
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return statement;
    }

}
