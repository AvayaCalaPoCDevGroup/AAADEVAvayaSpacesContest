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
import service.SpacesContest.Entity.Integrantesconcurso;


/**
 *
 * @author umansilla
 */
public class IntegrantesconcursoJpaController implements Serializable {

    public IntegrantesconcursoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Integrantesconcurso integrantesconcurso) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(integrantesconcurso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Integrantesconcurso integrantesconcurso) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            integrantesconcurso = em.merge(integrantesconcurso);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = integrantesconcurso.getId();
                if (findIntegrantesconcurso(id) == null) {
                    throw new NonexistentEntityException("The integrantesconcurso with id " + id + " no longer exists.");
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
            Integrantesconcurso integrantesconcurso;
            try {
                integrantesconcurso = em.getReference(Integrantesconcurso.class, id);
                integrantesconcurso.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The integrantesconcurso with id " + id + " no longer exists.", enfe);
            }
            em.remove(integrantesconcurso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Integrantesconcurso> findIntegrantesconcursoEntities() {
        return findIntegrantesconcursoEntities(true, -1, -1);
    }

    public List<Integrantesconcurso> findIntegrantesconcursoEntities(int maxResults, int firstResult) {
        return findIntegrantesconcursoEntities(false, maxResults, firstResult);
    }

    private List<Integrantesconcurso> findIntegrantesconcursoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Integrantesconcurso.class));
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

    public Integrantesconcurso findIntegrantesconcurso(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Integrantesconcurso.class, id);
        } finally {
            em.close();
        }
    }

    public int getIntegrantesconcursoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Integrantesconcurso> rt = cq.from(Integrantesconcurso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Integrantesconcurso obtenerIntegranteByIdIntegrante(String idIntegrante) {
        EntityManager em = getEntityManager();
        TypedQuery<Integrantesconcurso> namedQuery = em.createNamedQuery("Integrantesconcurso.findByIdintegrante", Integrantesconcurso.class);
        namedQuery.setParameter("idintegrante", idIntegrante);
        List<Integrantesconcurso> result = namedQuery.getResultList();
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

    public Integrantesconcurso obtenerIntegranteByIdIntegranteAndIdConcurso(String idIntegrante, String idConcurso) {
        EntityManager em = getEntityManager();
        TypedQuery<Integrantesconcurso> namedQuery = em.createNamedQuery("Integrantesconcurso.findByIdintegranteAndIdconcurso", Integrantesconcurso.class);
        namedQuery.setParameter("idintegrante", idIntegrante);
        namedQuery.setParameter("idconcurso", idConcurso);
        List<Integrantesconcurso> result = namedQuery.getResultList();
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

    public Integrantesconcurso obtenerIntegranteByCorreoSpacesAndIdConcurso(String correoSpaces, String idConcurso, String llaveConcurso) {
        EntityManager em = getEntityManager();
        TypedQuery<Integrantesconcurso> namedQuery = em.createNamedQuery("Integrantesconcurso.findByCorreoAndIdconcurso", Integrantesconcurso.class);
        namedQuery.setParameter("correo", correoSpaces);
        namedQuery.setParameter("idconcurso", idConcurso);
        namedQuery.setParameter("llaveconcurso", llaveConcurso);
        List<Integrantesconcurso> result = namedQuery.getResultList();
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

    public List<Integrantesconcurso> obtenerIntegrantesPorIdConcursoAndAsistenciaTrueAndEstatusActive(String idConcurso, String idEvento, String llaveConcurso) {
        EntityManager em = getEntityManager();
        TypedQuery<Integrantesconcurso> namedQuery = em.createNamedQuery("Integrantesconcurso.findByIdconcursoAndIdasistenciaAndEstatusAndIdEvento", Integrantesconcurso.class);
        namedQuery.setParameter("idconcurso", idConcurso);
        namedQuery.setParameter("idevento", idEvento);
        namedQuery.setParameter("llaveconcurso", llaveConcurso);
        namedQuery.setParameter("asistencia", "TRUE");
        namedQuery.setParameter("estatus", "ACTIVO");
        List<Integrantesconcurso> result = namedQuery.getResultList();
        if (!result.isEmpty()) {
            return result;
        }
        return new ArrayList<>();
    }

    public int obtenerTodosLosIntegrantesEnroladosPorLlaveDelConcurso(String llave) {
        EntityManager em = getEntityManager();
        TypedQuery<Integrantesconcurso> namedQuery = em.createNamedQuery("Integrantesconcurso.findByTotalDeIntegrantesPorLlaveDelConcurso", Integrantesconcurso.class);
        namedQuery.setParameter("llaveconcurso", llave);
        List<Integrantesconcurso> result = namedQuery.getResultList();
        if (!result.isEmpty()) {
            return result.size();
        }
        return 0;
    }

    public List<Integrantesconcurso> obtenerIntegrantesEnroladosPorLlaveDelConcurso(String llaveconcurso) {
        EntityManager em = getEntityManager();
        TypedQuery<Integrantesconcurso> namedQuery = em.createNamedQuery("Integrantesconcurso.findIntegrantesPorLlaveDelConcurso", Integrantesconcurso.class);
        namedQuery.setParameter("llaveconcurso", llaveconcurso);
        List<Integrantesconcurso> result = namedQuery.getResultList();
        if (!result.isEmpty()) {
            return result;
        }
        return new ArrayList<>();
    }

}
