package service.SpacesContest.Service.Impl;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.json.JSONObject;

import service.SpacesContest.Dao.ConcursosJpaController;
import service.SpacesContest.Dao.EventosJpaController;
import service.SpacesContest.Dao.IntegrantesconcursoJpaController;
import service.SpacesContest.Dao.IntegranteseventoJpaController;
import service.SpacesContest.Dao.IntegrantessalasJpaController;
import service.SpacesContest.Dao.PreguntasJpaController;
import service.SpacesContest.Dao.RespuestasJpaController;
import service.SpacesContest.Dao.SpeakersJpaController;
import service.SpacesContest.Dao.TituloseventoJpaController;
import service.SpacesContest.Entity.Concursos;
import service.SpacesContest.Entity.Eventos;
import service.SpacesContest.Entity.Integrantesconcurso;
import service.SpacesContest.Entity.Integrantesevento;
import service.SpacesContest.Entity.Integrantessalas;
import service.SpacesContest.Entity.Preguntas;
import service.SpacesContest.Entity.Respuestas;
import service.SpacesContest.Entity.Speakers;
import service.SpacesContest.Entity.Titulosevento;
import service.SpacesContest.Service.IAvayaSpacesContest;
import service.SpacesContest.Util.Constants;
import service.SpacesContest.Util.DateFormatedNow;


/**
 *
 * @author umansilla
 */
public class AvayaSpacesContestImpl implements IAvayaSpacesContest {

    private final EntityManagerFactory emf;

    public AvayaSpacesContestImpl() {
        this.emf = Persistence.createEntityManagerFactory(Constants.JPA_NAME);
    }

    /*
    CONCURSOS
     */
    @Override
    public Concursos obtenerConcursoPorIdConcurso(String idConcurso) {
        return new ConcursosJpaController(emf).obtenerConcursoPorIdConcurso(idConcurso);
    }
    
    @Override
    public Concursos obtenerConcursoPorIdConcursoFamilyChildren(String idConcurso){
        return new ConcursosJpaController(emf).obtenerConcursoPorIdConcursoAndFamilyChildren(idConcurso);
    }

    @Override
    public List<Concursos> obtenerConcursosTypeQuizesActivo() {
        return new ConcursosJpaController(emf).obtenerConcursoTypeQuizAndActivo();
    }

    @Override
    public List<Concursos> obtenerConcursosTypeQuizesActivo(String creadoPor) {
        return new ConcursosJpaController(emf).obtenerConcursoTypeQuizActivo(creadoPor);
    }

    @Override
    public void crearConcursoTypeQuizChildren(String idEvento, Concursos concurso, String creadoPor, String spaceId, String nuevaLlave, String nuevoUUIDConcurso) {
        new ConcursosJpaController(emf)
                .create(new Concursos(nuevoUUIDConcurso,
                        concurso.getIdsala(),
                        idEvento,
                        nuevaLlave,
                        true,
                        concurso.getNombreconcurso(),
                        new DateFormatedNow().getDateNow(),
                        new DateFormatedNow().getDateNow(),
                        "",
                        "",
                        0,
                        0,
                        0,
                        0,
                        0,
                        "[]",
                        "ACTIVO",
                        false,
                        creadoPor,
                        spaceId,
                        concurso.getType(),
                        concurso.getImageurl(),
                        concurso.getColor(),
                        concurso.getIsfavorite(),
                        concurso.getFontcolor(),
                        "CHILDREN")
                );
    }

    @Override
    public void crearConcursoTypeQuiz(JSONObject jsonObject, String uuid, String spaceId, String llave, Boolean fake, String creadoPor) {
        new ConcursosJpaController(emf).create(
                new Concursos(
                        uuid,
                        "00000000-0000-0000-0000-000000000000",
                        "00000000-0000-0000-0000-000000000000",
                        llave,
                        Boolean.FALSE,
                        jsonObject.getString("nombre"),
                        new DateFormatedNow().getDateNow(),
                        jsonObject.getString("fechaApertura"),
                        "EMPTY",
                        "EMPTY",
                        0,
                        jsonObject.getJSONArray("preguntas").length(),
                        0,
                        0,
                        0,
                        "[]",
                        "ACTIVO",
                        fake,
                        creadoPor,
                        spaceId,
                        "QUIZ",
                        jsonObject.getString("imageQuiz"),
                        jsonObject.getString("quizColor"),
                        jsonObject.getBoolean("isFavorite"),
                        jsonObject.getString("fontColor"),
                        "PARENT"));
    }

    @Override
    public void editarConcursoTypeChildrenToTerminated(String idConcurso, String llaveConcurso) throws Exception {
        new ConcursosJpaController(emf).editarConcursoSetEstatusTerminated(idConcurso, llaveConcurso);
    }

    @Override
    public void editarCocurdoIdSpacesEmpty(Long id) throws Exception {
        new ConcursosJpaController(emf).editarConcursoIdSpaceEmpty(id);
    }

    @Override
    public void editarConcursoToNewIdAvayaSpaceAndIdConcurso(Concursos concurso, String idSpace, String idEvento) throws Exception {
        new ConcursosJpaController(emf).edit(
                new Concursos(
                        concurso.getId(),
                        concurso.getIdconcurso(),
                        concurso.getIdsala(),
                        idEvento,
                        concurso.getLlave(),
                        concurso.getOnline(),
                        concurso.getNombreconcurso(),
                        concurso.getFechacreacion(),
                        concurso.getFechaejecucion(),
                        concurso.getFechaclausura(),
                        concurso.getDuracionconcurso(),
                        concurso.getParticipantes(),
                        concurso.getPreguntas(),
                        concurso.getRespuestas(),
                        concurso.getCorrectas(),
                        concurso.getIncorrectas(),
                        concurso.getArrayganadores(),
                        concurso.getEstatus(),
                        concurso.getFake(),
                        concurso.getCreadopor(),
                        idSpace,
                        concurso.getType(),
                        concurso.getImageurl(),
                        concurso.getColor(),
                        concurso.getIsfavorite(),
                        concurso.getFontcolor(),
                        concurso.getFamily())
        );
    }

    @Override
    public Concursos obtenerConcursoPorIdConcursoAndIdEventoAndFamilyChildren(String idConcurso, String idEvento) {
        return new ConcursosJpaController(emf).obtenerConcursoPorIdConcursoAndIdEventoAndFamilyAsChildren(idConcurso, idEvento);
    }

    @Override
    public Concursos obtenerConcursoPorIdConcursoFamilyParent(String idConcurso) {
        return new ConcursosJpaController(emf).obtenerConcursoPorIdConcursoAndFamilyAsParent(idConcurso);
    }
    
    @Override
    public void editarConcursoFamimlyParentToInactivoById(Long id) throws Exception{
        new ConcursosJpaController(emf).editarConcursoParentToInactiveById(id);
    }
    
    

    /*
    Integrantes Concurso
     */
    @Override
    public void crearIntegrante(Integrantesconcurso integrantesconcurso) {
        new IntegrantesconcursoJpaController(emf).create(integrantesconcurso);
    }

    @Override
    public Integrantesconcurso obtenerIntegranteConcrusoByIdIntegrante(String idIntegrante) {
        return new IntegrantesconcursoJpaController(emf).obtenerIntegranteByIdIntegrante(idIntegrante);
    }

    @Override
    public Integrantesconcurso obtenerIntegranteConcursoByIdIntegranteAndIdConcurso(String idIntegrante, String idConcurso) {
        return new IntegrantesconcursoJpaController(emf).obtenerIntegranteByIdIntegranteAndIdConcurso(idIntegrante, idConcurso);
    }

    @Override
    public Integrantesconcurso obtenerIntegranteConcursoPorEmailSpacesAndIdConcurso(String correoSpaces, String idConcurso, String llaveConcurso) {
        return new IntegrantesconcursoJpaController(emf).obtenerIntegranteByCorreoSpacesAndIdConcurso(correoSpaces, idConcurso, llaveConcurso);
    }

    @Override
    public List<Integrantesconcurso> obtenerIntegrantesPorIdConcursoAsistenciaTrueAndStatusActive(String idConcurso, String idEvento, String llaveConcurso) {
        return new IntegrantesconcursoJpaController(emf).obtenerIntegrantesPorIdConcursoAndAsistenciaTrueAndEstatusActive(idConcurso, idEvento, llaveConcurso);
    }
    
    @Override
    public int obtenerIntegrantesEnroladosPorLlaveDelConcurso(String llave){
        return new IntegrantesconcursoJpaController(emf).obtenerTodosLosIntegrantesEnroladosPorLlaveDelConcurso(llave);
    }

    @Override
    public List<Integrantesconcurso> obtenerIntegrantesEnrolladoPirLlaveDelConcurso(String llaveconcurso){
        return new IntegrantesconcursoJpaController(emf).obtenerIntegrantesEnroladosPorLlaveDelConcurso(llaveconcurso);
    }
    
    /*
    Integrantes Salas
     */
    @Override
    public Integrantessalas obtenerIntegranteSalaPorIdIntegrante(String idIntegrante) {
        return new IntegrantessalasJpaController(emf).obtenerIntegranteByIdIntegranteAsistenciaTrueEstatusActivo(idIntegrante);
    }

    @Override
    public Integrantessalas obtenerIntegranteSalaPorCorreoAvayaSpaces(String correo) {
        return new IntegrantessalasJpaController(emf).obtenerIntegranteByCorreoAvayaSpacecsAsistenciaTrueEstatusActivo(correo);
    }

    /*
    INTEGRANTES EVENTO
     */
    @Override
    public List<Integrantesevento> obtenerIntegrantesEventoPorIdEvento(String idEvento){
        return new IntegranteseventoJpaController(emf).obtenerIntegrantesEventoPorIdEvento(idEvento);
    }
    
    @Override
    public Integrantesevento obtenerIntegranteeventoPorIdIntegrante(String idIntegrante) {
        return new IntegranteseventoJpaController(emf).obtenerIntegranteEventoPorIdIntegrante(idIntegrante);
    }

    @Override
    public void crearIntegranteEvento(Integrantesevento integranteEvent) {
        new IntegranteseventoJpaController(emf).create(integranteEvent);
    }

    @Override
    public Integrantesevento obtenerIntegranteEventoPorCorreoAvayaSpaces(String correoAvayaSpaces) {
        return new IntegranteseventoJpaController(emf).obtenerIntegranteEventoPorCorreoAvayaSpaces(correoAvayaSpaces);
    }

    @Override
    public Integrantesevento obtenerIntegranteEventoPorCorreoAvayaSpacesAndIdEvento(String correoAvayaSapces, String IdEvento) {
        return new IntegranteseventoJpaController(emf).obtenerIntegranteEventoPorCorreoAvayaSpacesAndIdEvento(correoAvayaSapces, IdEvento);
    }
    
    @Override
    public Integrantesevento obtenerIntegranteEventoPorCorreoRegistroAndIdEvento(String correoResgistro, String idEvento) {
    	return new IntegranteseventoJpaController(emf).obtenerIntegranteEventoPorCorreoRegistroAndIdEvento(correoResgistro, idEvento);
    }
    
    @Override
    public List<Integrantesevento> obtenerIntegrantesPorIdEvento(String idEvento){
    	return new IntegranteseventoJpaController(emf).obtenerIntegrantesPorIdEvento(idEvento);
    }

    @Override
    public Long obtenerTodosLosResgistradosAEventoporIdEvento(String idEvento){
        return new IntegranteseventoJpaController(emf).obtenerIntegranteRegistradosAEventoPorIdEvento(idEvento);
    }
    
    /*
    PREGUNTAS
     */
    @Override
    public void crearPregunta(JSONObject jsonPregunta, String idConcurso, String llave, String creadoPor) {
        new PreguntasJpaController(emf).create(
                new Preguntas(
                        UUID.randomUUID().toString(),
                        idConcurso,
                        llave,
                        jsonPregunta.getString("preguta"),
                        jsonPregunta.getString("opcionA"),
                        jsonPregunta.getString("opcionB"),
                        jsonPregunta.getString("opcionC"),
                        jsonPregunta.getString("opcionD"),
                        Boolean.toString(jsonPregunta.getBoolean("checkBoxA")),
                        Boolean.toString(jsonPregunta.getBoolean("checkBoxB")),
                        Boolean.toString(jsonPregunta.getBoolean("checkBoxC")),
                        Boolean.toString(jsonPregunta.getBoolean("checkBoxD")),
                        new DateFormatedNow().getDateNow(),
                        "EMPTY",
                        Boolean.FALSE,
                        "ACTIVO",
                        creadoPor,
                        jsonPregunta.getInt("indexorder"),
                        Integer.parseInt(jsonPregunta.getString("points").replaceAll("pts", "")),
                        jsonPregunta.getString("image"),
                        jsonPregunta.getString("time"),
                        "00000000-0000-0000-0000-000000000000",
                        "PARENT"
                ));
    }

    @Override
    public void crearPreguntaChildren(Preguntas pregunta, String idEvento, String creadoPor, String nuevaLlave, String nuevoUUIDConcurso) {
        new PreguntasJpaController(emf)
                .create(new Preguntas(
                        UUID.randomUUID().toString(),
                        nuevoUUIDConcurso,
                        nuevaLlave,
                        pregunta.getPregunta(),
                        pregunta.getOpciona(),
                        pregunta.getOpcionb(),
                        pregunta.getOpcionc(),
                        pregunta.getOpciond(),
                        pregunta.getRespuestauno(),
                        pregunta.getRespuestados(),
                        pregunta.getRespuestatres(),
                        pregunta.getRespuestacuatro(),
                        new DateFormatedNow().getDateNow(),
                        new DateFormatedNow().getDateNow(),
                        pregunta.getFake(),
                        "ACTIVO",
                        creadoPor,
                        pregunta.getIndexorder(),
                        pregunta.getPoints(),
                        pregunta.getImageurl(),
                        pregunta.getTimepregunta(),
                        idEvento,
                        "CHILDREN"));
    }

    @Override
    public List<Preguntas> obtenerPreguntsPorIdConcursoAndFamilyParent(String idConcurso, String creadoPor) {
        return new PreguntasJpaController(emf).obtenerPreguntasPorIDConcursoAndFamilyParent(idConcurso, creadoPor);
    }

    @Override
    public List<Preguntas> obtenerPreguntsPorIdConcursoAndFamilyChildren(String idConcurso, String creadoPor, String llaveConcurso) {
        return new PreguntasJpaController(emf).obtenerPreguntasPorIDConcursoAndFamilyChildren(idConcurso, creadoPor, llaveConcurso);
    }
    
    @Override
    public List<Preguntas> obtenerPreguntsPorIdConcursoAndFamilyChildrenForClose(String idConcurso, String creadoPor, String llaveConcurso){
        return new PreguntasJpaController(emf).obtenerPreguntsPorIdConcursoAndFamilyChildrenForClose(idConcurso, creadoPor, llaveConcurso);
    }

    @Override
    public List<Preguntas> obtenerPreguntasPorIdConcursoAndIdEventoAndFamilyChildren(String idConcurso, String idEvento) {
        return new PreguntasJpaController(emf).obtenerPreguntaPorIdConcursoAndIdEventoAndFamilyChildren(idConcurso, idEvento);
    }

    @Override
    public Preguntas obtenerPreguntaPorIdPreguntaAndIdConcrusoAndFamilyAsChildren(String idPregunta, String idConcurso, String idEvento) {
        return new PreguntasJpaController(emf).obtenerPreguntaPorIdPreguntaAndIdConcursoAndFamilyAsChildren(idPregunta, idConcurso, idEvento);
    }

    @Override
    public Integer editarPreguntaEstatusTerminated(Long id) throws Exception {
        return new PreguntasJpaController(emf).editarEstatusPreguntaTerminated(id);
    }

    /*
    Respuestas
     */
    @Override
    public Respuestas obtenerRespuestasPorCorreoParticipanteIdPreguntaIdConcurso(String correoSpaces, String idPregunta, String idConcurso) {
        return new RespuestasJpaController(emf).obtenerRespuestaPorCorreoSpacesAndIdPreguntaAndIdCocnurso(correoSpaces, idPregunta, idConcurso);
    }

    @Override
    public Respuestas obtenerRespuestaPorIdPreguntaAndTopicId(String idPregunta, String topicId){
        return new RespuestasJpaController(emf).obtenerRespuestaPorIdPreguntaAndTopicId(idPregunta, topicId);
    }
    
    @Override
    public void crearRespuestaPorPorticipante(Respuestas respuestas) {
        new RespuestasJpaController(emf).create(respuestas);
    }

    @Override
    public List<Respuestas> obtenerRespuestasPorIdPreguntaAndIdIdConcursoAndIdEvento(String idPrgunta, String idConcurso, String idEvento) {
        return new RespuestasJpaController(emf).obtenerRespuestaPorIdPreguntaAndIdCocnursoAndIdEvento(idPrgunta, idConcurso, idEvento);
    }

    @Override
    public List<Respuestas> obtenerRespuestasPorIdConcursoAndIdEventoAndLlaveConcurso(String idConcurso, String idEvento, String llave) {
        return new RespuestasJpaController(emf).obtenerRespuestaPorIdConcurso(idConcurso, idEvento, llave);
    }
    @Override
    public Respuestas obtenerTipicIdPorIdEventoYCorreoSpaces(String idEvento, String correoSpaces){
        return new RespuestasJpaController(emf).obtenerTopicIdPorIdEventoYCorreoSpaces(idEvento, correoSpaces);
    }
    
    @Override
    public Respuestas obtenerCorreoSpacesByIdEventoAndTopicId(String idEvento, String topicId){
        return new RespuestasJpaController(emf).obtenerCorreoSpacesByIdEventoAndTopicId(idEvento, topicId);
    }

    @Override
    public void resetAciertosAndErrorsByIdColumn(Long id) throws Exception {
        new RespuestasJpaController(emf).resetAciertosAndErrorByIdColumn(id);
    }

    @Override
    public void updateAciertosAndErroresByRespuestasObject(Respuestas respuestas, int updateAcierto, int updateError, String updateBodyText, String llaveconcurso) {
        try {
            new RespuestasJpaController(emf)
                    .edit(new Respuestas(
                            respuestas.getId(),
                            respuestas.getIdrespuesta(),
                            respuestas.getIdpregunta(),
                            respuestas.getIdconcurso(),
                            respuestas.getIdintegrante(),
                            respuestas.getEmailintegrante(),
                            respuestas.getNombreintegrante(),
                            respuestas.getUrlimage(),
                            updateAcierto,
                            updateError,
                            updateBodyText,
                            new DateFormatedNow().getDateNow(),
                            respuestas.getEstatus(),
                            respuestas.getTopicid(),
                            respuestas.getIdevento(),
                            llaveconcurso));
        } catch (Exception ex) {
            System.out.println("Error al actualizar Aciertos y Errores: " + ex.toString());
        }
    }

    /*
    EVENTOS
     */
    @Override
    public Eventos ObtenerEventoPorIdEvento(String idEvento) {
        return new EventosJpaController(emf).obtenerEventoPorIdEvento(idEvento);
    }

    @Override
    public void CrearEventoParaQuiz(String uuid, String nombreEvento, String creadoPor, String idSpace, String joinurl) {
        new EventosJpaController(emf).create(
                new Eventos(
                        uuid,
                        true,
                        nombreEvento,
                        new DateFormatedNow().getDateNow(),
                        "",
                        "",
                        "",
                        0,
                        0, 0, 0, 0, 0, 0, "[]", "", "",
                        nombreEvento,
                        "[]",
                        "ACTIVO",
                        creadoPor,
                        idSpace,
                        joinurl
                )
        );
    }

    @Override
    public List<Eventos> obtenerEventosPorEstatusActivoAndCreadoPor(String creadoPor) {
        return new EventosJpaController(emf).obtenerEventosPorCreatedBy(creadoPor);
    }
    
    @Override
    public void borrarEvento(Long id) throws Exception{
        new EventosJpaController(emf).editarEventoAEstatusInactivo(id);
    }
    /*
    SPEAKERS
     */
    @Override
    public void editarSpeakersDelEvento(Eventos evento, String speakersArray) throws Exception {
        new EventosJpaController(emf).edit(new Eventos(
                evento.getId(),
                evento.getIdevento(),
                evento.getOnline(),
                evento.getNombreevento(),
                evento.getFechacreacion(),
                evento.getFechaejecucion(),
                evento.getFechaclausura(),
                evento.getDuracionevento(),
                evento.getParticipantes(),
                evento.getPreguntas(),
                evento.getRespuestas(),
                evento.getCorrectas(),
                evento.getIncorrectas(),
                evento.getSalas(),
                evento.getConcursos(),
                evento.getArrayganadores(),
                evento.getCiudadsede(),
                evento.getPaissede(),
                evento.getResumenevento(),
                speakersArray,
                evento.getEstatus(),
                evento.getCreadopor(),
                evento.getIdspace(),
                evento.getJoinurl()));
    }
    
    @Override
    public String crearSpeaker(String imageSpeaker, String nombreSpeaker, String posicionSpeaker, String companySpeaker, String descriptionSpeaker, String creadoPor) {
        String idSpeaker = UUID.randomUUID().toString();
        new SpeakersJpaController(emf).create(new Speakers(
                idSpeaker,
                nombreSpeaker,
                imageSpeaker,
                posicionSpeaker,
                companySpeaker,
                descriptionSpeaker,
                creadoPor, new DateFormatedNow().getDateNow()));
        return idSpeaker;
    }

    @Override
    public Speakers obtenerSpealerPorIdSpeakerAndCreadoPor(String idSpeaker, String creadoPor) {
        return new SpeakersJpaController(emf).obtenerSpeakersByIdspeakerAndCreadoPor(idSpeaker, creadoPor);
    }

    @Override
    public List<Speakers> obtenerSpeakersByCreadoPor(String creadoPor) {
        return new SpeakersJpaController(emf).obtenerSpeakersByCreadoPor(creadoPor);
    }

    @Override
    public void actualizarSpeaker(Speakers speaker, String imageSpeaker, String nombreSpeaker, String posicionSpeaker, String companySpeaker, String descriptionSpeaker) throws Exception {
        new SpeakersJpaController(emf).edit(new Speakers(
                speaker.getId(),
                speaker.getIdspeaker(),
                nombreSpeaker,
                imageSpeaker,
                posicionSpeaker,
                companySpeaker,
                descriptionSpeaker,
                speaker.getCreadopor(),
                new DateFormatedNow().getDateNow())
        );
    }

    /*
    Titulos Evento
     */
    @Override
    public Titulosevento obtenerTitulosEventoPorIdEventoAndCreadoPor(String idEvento, String creadoPor) {
        return new TituloseventoJpaController(emf).obtenerTitulosEventoPorIdEventoANDCreadoPor(idEvento, creadoPor);
    }

    @Override
    public void editarTituloPorIDTituloYIdEventoYCradoPor(Titulosevento titulosEvento, String subtitlesArray, String description) throws Exception {
        new TituloseventoJpaController(emf).edit(new Titulosevento(
                titulosEvento.getId(),
                titulosEvento.getIdevento(),
                subtitlesArray,
                description,
                titulosEvento.getCreadopor(),
                titulosEvento.getFechadecreacion()));
    }

    @Override
    public void crearTituloEvento(String idEvento, String subtitlesArray, String description, String creadoPor) {
        new TituloseventoJpaController(emf).create(new Titulosevento(
                idEvento,
                subtitlesArray,
                description,
                creadoPor,
                new DateFormatedNow().getDateNow()));
    }
}