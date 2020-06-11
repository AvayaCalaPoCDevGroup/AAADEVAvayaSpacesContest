package service.SpacesContest.Beans;


/**
 *
 * @author umansilla
 */
public class ImportQuiz {

    private String question;
    private String opcionA;
    private String opcionB;
    private String opcionC;
    private String opcionD;
    private Boolean respuestaA;
    private Boolean respuestaB;
    private Boolean respuestaC;
    private Boolean respuestaD;
    private String image;

    public ImportQuiz() {
    }

    public ImportQuiz(String question, String opcionA, String opcionB, String opcionC, String opcionD, Boolean respuestaA, Boolean respuestaB, Boolean respuestaC, Boolean respuestaD, String image) {
        this.question = question;
        this.opcionA = opcionA;
        this.opcionB = opcionB;
        this.opcionC = opcionC;
        this.opcionD = opcionD;
        this.respuestaA = respuestaA;
        this.respuestaB = respuestaB;
        this.respuestaC = respuestaC;
        this.respuestaD = respuestaD;
        this.image = image;
    }

    
    
    public ImportQuiz(String question, String opcionA, String opcionB, String opcionC, String opcionD, Boolean respuestaA, Boolean respuestaB, Boolean respuestaC, Boolean respuestaD) {
        this.question = question;
        this.opcionA = opcionA;
        this.opcionB = opcionB;
        this.opcionC = opcionC;
        this.opcionD = opcionD;
        this.respuestaA = respuestaA;
        this.respuestaB = respuestaB;
        this.respuestaC = respuestaC;
        this.respuestaD = respuestaD;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOpcionA() {
        return opcionA;
    }

    public void setOpcionA(String opcionA) {
        this.opcionA = opcionA;
    }

    public String getOpcionB() {
        return opcionB;
    }

    public void setOpcionB(String opcionB) {
        this.opcionB = opcionB;
    }

    public String getOpcionC() {
        return opcionC;
    }

    public void setOpcionC(String opcionC) {
        this.opcionC = opcionC;
    }

    public String getOpcionD() {
        return opcionD;
    }

    public void setOpcionD(String opcionD) {
        this.opcionD = opcionD;
    }

    public Boolean getRespuestaA() {
        return respuestaA;
    }

    public void setRespuestaA(Boolean respuestaA) {
        this.respuestaA = respuestaA;
    }

    public Boolean getRespuestaB() {
        return respuestaB;
    }

    public void setRespuestaB(Boolean respuestaB) {
        this.respuestaB = respuestaB;
    }

    public Boolean getRespuestaC() {
        return respuestaC;
    }

    public void setRespuestaC(Boolean respuestaC) {
        this.respuestaC = respuestaC;
    }

    public Boolean getRespuestaD() {
        return respuestaD;
    }

    public void setRespuestaD(Boolean respuestaD) {
        this.respuestaD = respuestaD;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
