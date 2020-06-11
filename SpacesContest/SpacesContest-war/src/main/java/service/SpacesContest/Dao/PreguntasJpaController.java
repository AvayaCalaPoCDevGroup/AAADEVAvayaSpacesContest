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
import service.SpacesContest.Entity.Preguntas;


/**
 *
 * @author umansilla
 */
public class PreguntasJpaController implements Serializable {

    public PreguntasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Preguntas preguntas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(preguntas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Preguntas preguntas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            preguntas = em.merge(preguntas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = preguntas.getId();
                if (findPreguntas(id) == null) {
                    throw new NonexistentEntityException("The preguntas with id " + id + " no longer exists.");
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
            Preguntas preguntas;
            try {
                preguntas = em.getReference(Preguntas.class, id);
                preguntas.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The preguntas with id " + id + " no longer exists.", enfe);
            }
            em.remove(preguntas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Preguntas> findPreguntasEntities() {
        return findPreguntasEntities(true, -1, -1);
    }

    public List<Preguntas> findPreguntasEntities(int maxResults, int firstResult) {
        return findPreguntasEntities(false, maxResults, firstResult);
    }

    private List<Preguntas> findPreguntasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Preguntas.class));
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

    public Preguntas findPreguntas(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Preguntas.class, id);
        } finally {
            em.close();
        }
    }

    public int getPreguntasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Preguntas> rt = cq.from(Preguntas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Preguntas> obtenerPreguntasPorIDConcursoAndFamilyParent(String idConcurso, String creadoPor) {
        EntityManager em = getEntityManager();
        TypedQuery<Preguntas> namedQuery = em.createNamedQuery("Preguntas.findByIdConcursoAndCreadoPor", Preguntas.class);
        namedQuery.setParameter("idconcurso", idConcurso);
        namedQuery.setParameter("creadopor", creadoPor);
        namedQuery.setParameter("familypregunta", "PARENT");
        List<Preguntas> result = namedQuery.getResultList();
        if (!result.isEmpty()) {
            return result;
        }
        return new ArrayList<>();
    }

    public List<Preguntas> obtenerPreguntasPorIDConcursoAndFamilyChildren(String idConcurso, String creadoPor, String llaveConcurso) {
        EntityManager em = getEntityManager();
        TypedQuery<Preguntas> namedQuery = em.createNamedQuery("Preguntas.findByIdConcursoAndCreadoPor", Preguntas.class);
        namedQuery.setParameter("idconcurso", idConcurso);
        namedQuery.setParameter("creadopor", creadoPor);
        namedQuery.setParameter("llave", llaveConcurso);
        namedQuery.setParameter("familypregunta", "CHILDREN");
        List<Preguntas> result = namedQuery.getResultList();
        if (!result.isEmpty()) {
            return result;
        }
        return new ArrayList<>();
    }

    public Preguntas obtenerPreguntaPorIdPreguntaAndIdConcursoAndFamilyAsChildren(String idPregunta, String idConcurso, String idEvento) {
        EntityManager em = getEntityManager();
        TypedQuery<Preguntas> namedQuery = em.createNamedQuery("Preguntas.findByIdpreguntaAndIdconcursoAndFamilyChildrenAndIdEvento", Preguntas.class);
        namedQuery.setParameter("idpregunta", idPregunta);
        namedQuery.setParameter("idconcurso", idConcurso);
        namedQuery.setParameter("idevento", idEvento);
        namedQuery.setParameter("familypregunta", "CHILDREN");

        List<Preguntas> result = namedQuery.getResultList();
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

    public List<Preguntas> obtenerPreguntaPorIdConcursoAndIdEventoAndFamilyChildren(String idConcurso, String idEvento) {
        EntityManager em = getEntityManager();
        TypedQuery<Preguntas> namedQuery = em.createNamedQuery("Preguntas.findByIdconcursoAndIdEventoAndFamily", Preguntas.class);
        namedQuery.setParameter("idconcurso", idConcurso);
        namedQuery.setParameter("idevento", idEvento);
        namedQuery.setParameter("familypregunta", "CHILDREN");

        List<Preguntas> result = namedQuery.getResultList();
        if (!result.isEmpty()) {
            return result;
        }
        return new ArrayList<>();
    }

    public List<Preguntas> obtenerPreguntsPorIdConcursoAndFamilyChildrenForClose(String idConcurso, String creadoPor, String llave) {
        EntityManager em = getEntityManager();
        TypedQuery<Preguntas> namedQuery = em.createNamedQuery("Preguntas.findByIdconcursoAndCreadoPorAndLlave", Preguntas.class);
        namedQuery.setParameter("idconcurso", idConcurso);
        namedQuery.setParameter("llave", llave);
        namedQuery.setParameter("creadopor", creadoPor);
        namedQuery.setParameter("familypregunta", "CHILDREN");

        List<Preguntas> result = namedQuery.getResultList();
        if (!result.isEmpty()) {
            return result;
        }
        return new ArrayList<>();
    }

    public Integer editarEstatusPreguntaTerminated(Long id) throws Exception {
        //updateEstatusTerminated
        int statement;
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            statement = em.createNamedQuery("Preguntas.updateEstatusTerminated", Preguntas.class)
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