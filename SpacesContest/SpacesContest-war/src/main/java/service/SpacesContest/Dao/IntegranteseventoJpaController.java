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
import service.SpacesContest.Entity.Integrantesevento;

/**
 *
 * @author umansilla
 */
public class IntegranteseventoJpaController implements Serializable {


	private static final long serialVersionUID = 1L;

	public IntegranteseventoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Integrantesevento integrantesevento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(integrantesevento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Integrantesevento integrantesevento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            integrantesevento = em.merge(integrantesevento);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = integrantesevento.getId();
                if (findIntegrantesevento(id) == null) {
                    throw new NonexistentEntityException("The integrantesevento with id " + id + " no longer exists.");
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
            Integrantesevento integrantesevento;
            try {
                integrantesevento = em.getReference(Integrantesevento.class, id);
                integrantesevento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The integrantesevento with id " + id + " no longer exists.", enfe);
            }
            em.remove(integrantesevento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Integrantesevento> findIntegranteseventoEntities() {
        return findIntegranteseventoEntities(true, -1, -1);
    }

    public List<Integrantesevento> findIntegranteseventoEntities(int maxResults, int firstResult) {
        return findIntegranteseventoEntities(false, maxResults, firstResult);
    }

    private List<Integrantesevento> findIntegranteseventoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Integrantesevento.class));
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

    public Integrantesevento findIntegrantesevento(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Integrantesevento.class, id);
        } finally {
            em.close();
        }
    }

    public int getIntegranteseventoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Integrantesevento> rt = cq.from(Integrantesevento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Integrantesevento obtenerIntegranteEventoPorIdIntegrante(String idIntegrante) {
        EntityManager em = getEntityManager();
        TypedQuery<Integrantesevento> namedQuery = em.createNamedQuery("Integrantesevento.findByIdintegrante", Integrantesevento.class);
        namedQuery.setParameter("idintegrante", idIntegrante);
        List<Integrantesevento> result = namedQuery.getResultList();
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

    public Integrantesevento obtenerIntegranteEventoPorCorreoAvayaSpaces(String correoAvayaSpaces) {
        EntityManager em = getEntityManager();
        TypedQuery<Integrantesevento> namedQuery = em.createNamedQuery("Integrantesevento.findByCorreoavayaspaces", Integrantesevento.class);
        namedQuery.setParameter("correoavayaspaces", correoAvayaSpaces);
        List<Integrantesevento> result = namedQuery.getResultList();
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }
    public Integrantesevento obtenerIntegranteEventoPorCorreoAvayaSpacesAndIdEvento(String correoAvayaSpaces, String idEvento) {
        EntityManager em = getEntityManager();
        TypedQuery<Integrantesevento> namedQuery = em.createNamedQuery("Integrantesevento.findByCorreoavayaspacesAndIdEvento", Integrantesevento.class);
        namedQuery.setParameter("correoavayaspaces", correoAvayaSpaces);
        namedQuery.setParameter("idevento", idEvento);
        List<Integrantesevento> result = namedQuery.getResultList();
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }
    public Integrantesevento obtenerIntegranteEventoPorCorreoRegistroAndIdEvento(String correoRegistro, String idEvento) {
        EntityManager em = getEntityManager();
        TypedQuery<Integrantesevento> namedQuery = em.createNamedQuery("Integrantesevento.findByCorreoAndIdEvento", Integrantesevento.class);
        namedQuery.setParameter("correo", correoRegistro);
        namedQuery.setParameter("idevento", idEvento);
        List<Integrantesevento> result = namedQuery.getResultList();
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }
    
    public List<Integrantesevento> obtenerIntegrantesPorIdEvento(String idEvento) {
        EntityManager em = getEntityManager();
        TypedQuery<Integrantesevento> namedQuery = em.createNamedQuery("Integrantesevento.findByIdevento", Integrantesevento.class);
        namedQuery.setParameter("idevento", idEvento);
        List<Integrantesevento> result = namedQuery.getResultList();
        if (!result.isEmpty()) {
            return result;
        }
        return new ArrayList<>();
    }
    
    public Long obtenerIntegranteRegistradosAEventoPorIdEvento(String idEvento) {
        EntityManager em = getEntityManager();
        TypedQuery<Long> namedQuery = em.createNamedQuery("Integrantesevento.findByIdeventoTotalRgistrados", Long.class);
        namedQuery.setParameter("idevento", idEvento);
        return namedQuery.getSingleResult();
    }
    
    public List<Integrantesevento> obtenerIntegrantesEventoPorIdEvento(String idEvento) {
        EntityManager em = getEntityManager();
        TypedQuery<Integrantesevento> namedQuery = em.createNamedQuery("Integrantesevento.findByIdevento", Integrantesevento.class);
        namedQuery.setParameter("idevento", idEvento);
        List<Integrantesevento> result = namedQuery.getResultList();
        if (!result.isEmpty()) {
            return result;
        }
        return new ArrayList<>();
    }
}
