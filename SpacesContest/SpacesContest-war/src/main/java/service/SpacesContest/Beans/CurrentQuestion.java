package service.SpacesContest.Beans;


/**
 *
 * @author umansilla
 */
public class CurrentQuestion {

    private final String idPregunta;
    private final String idConcurso;
    private final String idEvento;

    public CurrentQuestion(String idPregunta, String idConcurso, String idEvento) {
        this.idPregunta = idPregunta;
        this.idConcurso = idConcurso;
        this.idEvento = idEvento;
    }
    
    public String getIdPregunta() {
        return idPregunta;
    }


    public String getIdConcurso() {
        return idConcurso;
    }

    public String getIdEvento() {
        return idEvento;
    }

}
