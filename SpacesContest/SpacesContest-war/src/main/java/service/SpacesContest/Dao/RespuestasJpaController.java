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
import service.SpacesContest.Entity.Respuestas;

/**
 *
 * @author umansilla
 */
public class RespuestasJpaController implements Serializable {

	public RespuestasJpaController(EntityManagerFactory emf) {
		this.emf = emf;
	}

	private EntityManagerFactory emf = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(Respuestas respuestas) {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(respuestas);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void edit(Respuestas respuestas) throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			respuestas = em.merge(respuestas);
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = respuestas.getId();
				if (findRespuestas(id) == null) {
					throw new NonexistentEntityException("The respuestas with id " + id + " no longer exists.");
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
			Respuestas respuestas;
			try {
				respuestas = em.getReference(Respuestas.class, id);
				respuestas.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The respuestas with id " + id + " no longer exists.", enfe);
			}
			em.remove(respuestas);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public List<Respuestas> findRespuestasEntities() {
		return findRespuestasEntities(true, -1, -1);
	}

	public List<Respuestas> findRespuestasEntities(int maxResults, int firstResult) {
		return findRespuestasEntities(false, maxResults, firstResult);
	}

	private List<Respuestas> findRespuestasEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Respuestas.class));
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

	public Respuestas findRespuestas(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Respuestas.class, id);
		} finally {
			em.close();
		}
	}

	public int getRespuestasCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Respuestas> rt = cq.from(Respuestas.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

	public Respuestas obtenerRespuestaPorCorreoSpacesAndIdPreguntaAndIdCocnurso(String correoSpaces, String idPregunta,
			String idConcurso) {
		EntityManager em = getEntityManager();
		TypedQuery<Respuestas> namedQuery = em
				.createNamedQuery("Respuestas.findByEmailintegranteAndIdpreguntaAndIdconcurso", Respuestas.class);
		namedQuery.setParameter("emailintegrante", correoSpaces);
		namedQuery.setParameter("idpregunta", idPregunta);
		namedQuery.setParameter("idconcurso", idConcurso);
		List<Respuestas> result = namedQuery.getResultList();
		if (!result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}

	public List<Respuestas> obtenerRespuestaPorIdPreguntaAndIdCocnursoAndIdEvento(String idPregunta, String idConcurso,
			String idEvento) {
		EntityManager em = getEntityManager();
		TypedQuery<Respuestas> namedQuery = em.createNamedQuery("Respuestas.findByIdpreguntaAndIdconcursoAndIdEvento",
				Respuestas.class);
		namedQuery.setParameter("idpregunta", idPregunta);
		namedQuery.setParameter("idconcurso", idConcurso);
		namedQuery.setParameter("idevento", idEvento);
		List<Respuestas> result = namedQuery.getResultList();
		if (!result.isEmpty()) {
			return result;
		}
		return new ArrayList<>();
	}

	public List<Respuestas> obtenerRespuestaPorIdConcurso(String idConcurso, String idEvento, String llaveConcurso) {
		EntityManager em = getEntityManager();
		TypedQuery<Respuestas> namedQuery = em.createNamedQuery("Respuestas.findByIdconcursoAndIdEventoAndLlave",
				Respuestas.class);
		namedQuery.setParameter("idconcurso", idConcurso);
		namedQuery.setParameter("idevento", idEvento);
		namedQuery.setParameter("llaveconcurso", llaveConcurso);
		List<Respuestas> result = namedQuery.getResultList();
		if (!result.isEmpty()) {
			return result;
		}
		return new ArrayList<>();
	}

	public void resetAciertosAndErrorByIdColumn(Long id) throws Exception {
		// RESET ACIERTOS U ERRORES TO 0;
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.createNamedQuery("Respuestas.resetAciertosAndErrores", Respuestas.class).setParameter("id", id)
					.executeUpdate();
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public Respuestas obtenerRespuestaPorIdPreguntaAndTopicId(String idPregunta, String topicId) {
		EntityManager em = getEntityManager();
		TypedQuery<Respuestas> namedQuery = em.createNamedQuery("Respuestas.findByIdpreguntaAndTopicID",
				Respuestas.class);
		namedQuery.setParameter("idpregunta", idPregunta);
		namedQuery.setParameter("topicid", topicId);
		List<Respuestas> result = namedQuery.getResultList();
		if (!result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}
	
	  public Respuestas obtenerTopicIdPorIdEventoYCorreoSpaces(String idEvento, String correoSpaces) {
	        EntityManager em = getEntityManager();
	        TypedQuery<Respuestas> namedQuery = em.createNamedQuery("Respuestas.findByIdEventoAndCorreoSpaces", Respuestas.class);
	        namedQuery.setParameter("idevento", idEvento);
	        namedQuery.setParameter("emailintegrante", correoSpaces);
	        List<Respuestas> result = namedQuery.getResultList();
	        if (!result.isEmpty()) {
	            return result.get(0);
	        }
	        return null;
	    }

	    public Respuestas obtenerCorreoSpacesByIdEventoAndTopicId(String idEvento, String topicId) {
	        EntityManager em = getEntityManager();
	        TypedQuery<Respuestas> namedQuery = em.createNamedQuery("Respuestas.findByIdEventoAndTopicId", Respuestas.class);
	        namedQuery.setParameter("idevento", idEvento);
	        namedQuery.setParameter("topicid", topicId);
	        List<Respuestas> result = namedQuery.getResultList();
	        if (!result.isEmpty()) {
	            return result.get(0);
	        }
	        return null;
	    }
}
