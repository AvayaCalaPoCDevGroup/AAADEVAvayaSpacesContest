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
import service.SpacesContest.Entity.Integrantessalas;


/**
 *
 * @author umansilla
 */
public class IntegrantessalasJpaController implements Serializable {

    public IntegrantessalasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Integrantessalas integrantessalas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(integrantessalas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Integrantessalas integrantessalas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            integrantessalas = em.merge(integrantessalas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = integrantessalas.getId();
                if (findIntegrantessalas(id) == null) {
                    throw new NonexistentEntityException("The integrantessalas with id " + id + " no longer exists.");
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
            Integrantessalas integrantessalas;
            try {
                integrantessalas = em.getReference(Integrantessalas.class, id);
                integrantessalas.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The integrantessalas with id " + id + " no longer exists.", enfe);
            }
            em.remove(integrantessalas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Integrantessalas> findIntegrantessalasEntities() {
        return findIntegrantessalasEntities(true, -1, -1);
    }

    public List<Integrantessalas> findIntegrantessalasEntities(int maxResults, int firstResult) {
        return findIntegrantessalasEntities(false, maxResults, firstResult);
    }

    private List<Integrantessalas> findIntegrantessalasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Integrantessalas.class));
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

    public Integrantessalas findIntegrantessalas(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Integrantessalas.class, id);
        } finally {
            em.close();
        }
    }

    public int getIntegrantessalasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Integrantessalas> rt = cq.from(Integrantessalas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Integrantessalas obtenerIntegranteByIdIntegranteAsistenciaTrueEstatusActivo(String idIntegrante) {
        EntityManager em = getEntityManager();
        TypedQuery<Integrantessalas> namedQuery = em.createNamedQuery("Integrantessalas.findByIdintegranteAndAsistenciaAndStatus", Integrantessalas.class);
        namedQuery.setParameter("idintegrante", idIntegrante);
        namedQuery.setParameter("asistencia", "TRUE");
        namedQuery.setParameter("estatus", "ACTIVO");
        List<Integrantessalas> result = namedQuery.getResultList();
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

    public Integrantessalas obtenerIntegranteByCorreoAvayaSpacecsAsistenciaTrueEstatusActivo(String correo) {
        EntityManager em = getEntityManager();
        TypedQuery<Integrantessalas> namedQuery = em.createNamedQuery("Integrantessalas.findByCorreoAndAsistenciaAndStatus", Integrantessalas.class);
        namedQuery.setParameter("correo", correo);
        namedQuery.setParameter("asistencia", "TRUE");
        namedQuery.setParameter("estatus", "ACTIVO");
        List<Integrantessalas> result = namedQuery.getResultList();
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }
}
