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
import service.SpacesContest.Entity.Concursos;


/**
 *
 * @author umansilla
 */
public class ConcursosJpaController implements Serializable {

    public ConcursosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Concursos concursos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(concursos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Concursos concursos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            concursos = em.merge(concursos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = concursos.getId();
                if (findConcursos(id) == null) {
                    throw new NonexistentEntityException("The concursos with id " + id + " no longer exists.");
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
            Concursos concursos;
            try {
                concursos = em.getReference(Concursos.class, id);
                concursos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The concursos with id " + id + " no longer exists.", enfe);
            }
            em.remove(concursos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Concursos> findConcursosEntities() {
        return findConcursosEntities(true, -1, -1);
    }

    public List<Concursos> findConcursosEntities(int maxResults, int firstResult) {
        return findConcursosEntities(false, maxResults, firstResult);
    }

    private List<Concursos> findConcursosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Concursos.class));
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

    public Concursos findConcursos(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Concursos.class, id);
        } finally {
            em.close();
        }
    }

    public int getConcursosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Concursos> rt = cq.from(Concursos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Concursos obtenerConcursoPorIdConcurso(String idConcurso) {
        EntityManager em = getEntityManager();
        TypedQuery<Concursos> namedQuery = em.createNamedQuery("Concursos.findByIdconcursoAndFamily", Concursos.class);
        namedQuery.setParameter("idconcurso", idConcurso);
        namedQuery.setParameter("familyconcurso", "PARENT");
        List<Concursos> result = namedQuery.getResultList();
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

    public Concursos obtenerConcursoPorIdConcursoAndIdEventoAndFamilyAsChildren(String idConcurso, String idEvento) {
        EntityManager em = getEntityManager();
        TypedQuery<Concursos> namedQuery = em.createNamedQuery("Concursos.findByIdconcursoAndIdEventoAndFamilyChildren", Concursos.class);
        namedQuery.setParameter("idconcurso", idConcurso);
        namedQuery.setParameter("idevento", idEvento);
        namedQuery.setParameter("familyconcurso", "CHILDREN");
        List<Concursos> result = namedQuery.getResultList();
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

    public Concursos obtenerConcursoPorIdConcursoAndFamilyAsParent(String idConcurso) {
        EntityManager em = getEntityManager();
        TypedQuery<Concursos> namedQuery = em.createNamedQuery("Concursos.findByIdconcursoFamilyParent", Concursos.class);
        namedQuery.setParameter("idconcurso", idConcurso);
        namedQuery.setParameter("familyconcurso", "PARENT");
        List<Concursos> result = namedQuery.getResultList();
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

    public List<Concursos> obtenerConcursoTypeQuizAndActivo() {
        EntityManager em = getEntityManager();
        TypedQuery<Concursos> namedQuery = em.createNamedQuery("Concursos.findByTypeAndEstatus", Concursos.class);
        namedQuery.setParameter("type", "QUIZ");
        namedQuery.setParameter("estatus", "ACTIVO");
        List<Concursos> result = namedQuery.getResultList();
        if (!result.isEmpty()) {
            return result;
        }
        return new ArrayList<>();
    }

    public List<Concursos> obtenerConcursoTypeQuiz() {
        EntityManager em = getEntityManager();
        TypedQuery<Concursos> namedQuery = em.createNamedQuery("Concursos.findByTypeAndEstatus", Concursos.class);
        namedQuery.setParameter("type", "QUIZ");
        namedQuery.setParameter("estatus", "ACTIVO");
        List<Concursos> result = namedQuery.getResultList();
        if (!result.isEmpty()) {
            return result;
        }
        return new ArrayList<>();
    }

    public List<Concursos> obtenerConcursoTypeQuizActivo(String creadoPor) {
        EntityManager em = getEntityManager();
        TypedQuery<Concursos> namedQuery = em.createNamedQuery("Concursos.findByTypeAndEstatusActivo", Concursos.class);
        namedQuery.setParameter("type", "QUIZ");
        namedQuery.setParameter("creadopor", creadoPor);
        namedQuery.setParameter("familyconcurso", "PARENT");
        namedQuery.setParameter("estatus", "ACTIVO");
        List<Concursos> result = namedQuery.getResultList();
        if (!result.isEmpty()) {
            return result;
        }
        return new ArrayList<>();
    }

    public Concursos obtenerConcursoPorIdConcursoAndFamilyChildren(String idConcurso) {
        EntityManager em = getEntityManager();
        TypedQuery<Concursos> namedQuery = em.createNamedQuery("Concursos.findByIdconcursoAndFamilyChildren", Concursos.class);
        namedQuery.setParameter("idconcurso", idConcurso);
        namedQuery.setParameter("familyconcurso", "CHILDREN");
        List<Concursos> result = namedQuery.getResultList();
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

    public Integer editarConcursoSetEstatusTerminated(String idConcurso, String llaveConcurso) throws Exception {
        //updateEstatusTerminated
        int statement;
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            statement = em.createNamedQuery("Concursos.updateConcursoByIdConcurso", Concursos.class)
                    .setParameter("idconcurso", idConcurso)
                    .setParameter("llave", llaveConcurso)
                    .setParameter("familyconcurso", "CHILDREN")
                    .executeUpdate();
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return statement;
    }

    public Integer editarConcursoIdSpaceEmpty(Long id) throws Exception {
        //updateEstatusTerminated
        int statement;
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            statement = em.createNamedQuery("Concursos.updateConcursoByIdEmptyidSpace", Concursos.class)
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

    public Integer editarConcursoParentToInactiveById(Long id) throws Exception {
        //updateEstatusTerminated
        int statement;
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            statement = em.createNamedQuery("Concursos.updateConcursoParentToInactiveById", Concursos.class)
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
