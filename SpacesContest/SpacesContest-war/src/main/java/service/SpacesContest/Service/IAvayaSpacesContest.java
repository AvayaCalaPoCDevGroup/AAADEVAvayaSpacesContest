package service.SpacesContest.Service;

import java.util.List;

import org.json.JSONObject;

import service.SpacesContest.Entity.Concursos;
import service.SpacesContest.Entity.Eventos;
import service.SpacesContest.Entity.Integrantesconcurso;
import service.SpacesContest.Entity.Integrantesevento;
import service.SpacesContest.Entity.Integrantessalas;
import service.SpacesContest.Entity.Preguntas;
import service.SpacesContest.Entity.Respuestas;
import service.SpacesContest.Entity.Speakers;
import service.SpacesContest.Entity.Titulosevento;


/**
 *
 * @author umansilla
 */
public interface IAvayaSpacesContest {
    
    //CONCURSOS
    List<Concursos> obtenerConcursosTypeQuizesActivo();
    List<Concursos> obtenerConcursosTypeQuizesActivo(String creadoPor);
    Concursos obtenerConcursoPorIdConcurso(String idConcurso);
    Concursos obtenerConcursoPorIdConcursoFamilyChildren(String idConcurso);
    Concursos obtenerConcursoPorIdConcursoAndIdEventoAndFamilyChildren(String idConcurso, String idEvento);
    Concursos obtenerConcursoPorIdConcursoFamilyParent(String idConcurso);
    void crearConcursoTypeQuiz(JSONObject jsonObject, String uuid, String spaceId, String llave, Boolean fake,String creadoPor);
    void crearConcursoTypeQuizChildren(String idEvento, Concursos concurso, String creadoPor, String spaceId, String nuevaLlave, String nuevoUUIDConcurso);
    void editarConcursoTypeChildrenToTerminated(String idConcurso, String llaveConcurso) throws Exception;
    void editarCocurdoIdSpacesEmpty(Long id) throws Exception;
    void editarConcursoToNewIdAvayaSpaceAndIdConcurso(Concursos concurso, String idSpace, String idEvento) throws Exception;
    void editarConcursoFamimlyParentToInactivoById(Long id) throws Exception;
    //INTEGRANTES CONCURSO
    Integrantesconcurso obtenerIntegranteConcrusoByIdIntegrante(String idIntegrante);
    Integrantesconcurso obtenerIntegranteConcursoByIdIntegranteAndIdConcurso(String idIntegrante, String idConcurso);
    void crearIntegrante(Integrantesconcurso integrantesconcurso);
    Integrantesconcurso obtenerIntegranteConcursoPorEmailSpacesAndIdConcurso(String correoSpaces, String idConcurso, String llaveConcurso);
    List<Integrantesconcurso> obtenerIntegrantesPorIdConcursoAsistenciaTrueAndStatusActive(String idConcurso, String idEvento, String llaveConcurso);
    List<Integrantesconcurso> obtenerIntegrantesEnrolladoPirLlaveDelConcurso(String llaveconcurso);
    int obtenerIntegrantesEnroladosPorLlaveDelConcurso(String llave);
    //INTEGRANTES SALA
    Integrantessalas obtenerIntegranteSalaPorIdIntegrante(String idIntegrante);
    Integrantessalas obtenerIntegranteSalaPorCorreoAvayaSpaces(String correo);
    //INTEGRANTES EVENTO
    public List<Integrantesevento> obtenerIntegrantesEventoPorIdEvento(String idEvento);
    Integrantesevento obtenerIntegranteeventoPorIdIntegrante(String idIntegrante);
    void crearIntegranteEvento(Integrantesevento integranteEvento);
    Integrantesevento obtenerIntegranteEventoPorCorreoAvayaSpaces(String correoAvayaSapces);
    Integrantesevento obtenerIntegranteEventoPorCorreoAvayaSpacesAndIdEvento(String correoAvayaSapces, String IdEvento);
    Integrantesevento obtenerIntegranteEventoPorCorreoRegistroAndIdEvento(String correoResgistro, String idEvento);
    List<Integrantesevento> obtenerIntegrantesPorIdEvento(String idEvento);
    Long obtenerTodosLosResgistradosAEventoporIdEvento(String idEvento);
    //PREGUNTAS
    void crearPregunta(JSONObject jsonPregunta, String idConcurso, String llave, String creadoPor);
    void crearPreguntaChildren(Preguntas pregunta, String idEvento, String creadoPor, String nuevaLlave, String nuevoUUIDConcurso);
    List<Preguntas> obtenerPreguntsPorIdConcursoAndFamilyParent(String idConcurso, String creadoPor);
    List<Preguntas> obtenerPreguntsPorIdConcursoAndFamilyChildren(String idConcurso, String creadoPor, String llaveConcurso);
    List<Preguntas> obtenerPreguntasPorIdConcursoAndIdEventoAndFamilyChildren(String idConcurso, String idEvento);
    List<Preguntas> obtenerPreguntsPorIdConcursoAndFamilyChildrenForClose(String idConcurso, String creadoPor, String llaveConcurso);
    Preguntas obtenerPreguntaPorIdPreguntaAndIdConcrusoAndFamilyAsChildren(String idPregunta, String idConcurso, String idEvento);
    Integer editarPreguntaEstatusTerminated(Long id) throws Exception;
    //RESPUESTAS
    List<Respuestas> obtenerRespuestasPorIdPreguntaAndIdIdConcursoAndIdEvento(String idPrgunta, String idConcurso, String idEvento);
    List<Respuestas> obtenerRespuestasPorIdConcursoAndIdEventoAndLlaveConcurso(String idConcurso, String idEvento, String llave);
    Respuestas obtenerRespuestasPorCorreoParticipanteIdPreguntaIdConcurso(String correoSpaces, String idPregunta, String idConcurso);
    Respuestas obtenerRespuestaPorIdPreguntaAndTopicId(String idPregunta, String topicId);
    Respuestas obtenerTipicIdPorIdEventoYCorreoSpaces(String idEvento, String correoSpaces);
    Respuestas obtenerCorreoSpacesByIdEventoAndTopicId(String idEvento, String topicId);
    void crearRespuestaPorPorticipante(Respuestas respuesta);
    void resetAciertosAndErrorsByIdColumn(Long id) throws Exception;
    void updateAciertosAndErroresByRespuestasObject(Respuestas respuestas, int updateAcierto, int updateError, String updateBodyText, String llaveConcurso);
    //EVENTOS
    Eventos ObtenerEventoPorIdEvento(String idEvento);
    List<Eventos> obtenerEventosPorEstatusActivoAndCreadoPor(String creadoPor);
    void CrearEventoParaQuiz(String uuid, String nombreEvento, String creadoPor, String idSpace, String joinurl);
    void borrarEvento(Long id)  throws Exception;
    //SPEAKERS
    void editarSpeakersDelEvento(Eventos evento, String speakersArray) throws Exception;
    String crearSpeaker(String imageSpeaker, String nombreSpeaker, String posicionSpeaker, String companySpeaker, String descriptionSpeaker, String creadoPor);
    Speakers obtenerSpealerPorIdSpeakerAndCreadoPor(String idSpeaker, String creadoPor);
    List<Speakers> obtenerSpeakersByCreadoPor(String creadoPor);
    void actualizarSpeaker(Speakers speaker, String imageSpeaker, String nombreSpeaker, String posicionSpeaker, String companySpeaker, String descriptionSpeaker) throws Exception;
    //TITULOS
    Titulosevento obtenerTitulosEventoPorIdEventoAndCreadoPor(String idEvento, String creadoPor);
    void editarTituloPorIDTituloYIdEventoYCradoPor(Titulosevento titulosEvento, String subtitlesArray, String description) throws Exception;
    void crearTituloEvento(String idEvento, String subtitlesArray, String description, String creadoPor);
}
