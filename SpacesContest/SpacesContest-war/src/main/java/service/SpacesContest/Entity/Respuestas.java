package service.SpacesContest.Entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author umansilla
 */
@Entity
@Table(name = "respuestas", catalog = "avayaspacescontest", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Respuestas.findAll", query = "SELECT r FROM Respuestas r")
    , @NamedQuery(name = "Respuestas.findById", query = "SELECT r FROM Respuestas r WHERE r.id = :id")
    , @NamedQuery(name = "Respuestas.findByIdrespuesta", query = "SELECT r FROM Respuestas r WHERE r.idrespuesta = :idrespuesta")
    , @NamedQuery(name = "Respuestas.findByIdpregunta", query = "SELECT r FROM Respuestas r WHERE r.idpregunta = :idpregunta")
    , @NamedQuery(name = "Respuestas.findByIdpreguntaAndTopicID", query = "SELECT r FROM Respuestas r WHERE r.idpregunta = :idpregunta AND r.topicid = :topicid")
    , @NamedQuery(name = "Respuestas.findByIdconcurso", query = "SELECT r FROM Respuestas r WHERE r.idconcurso = :idconcurso ORDER BY r.fechacreacion")
    , @NamedQuery(name = "Respuestas.findByIdconcursoAndIdEventoAndLlave", query = "SELECT r FROM Respuestas r WHERE r.idconcurso = :idconcurso AND r.idevento = :idevento AND r.llaveconcurso = :llaveconcurso ORDER BY r.fechacreacion")
    , @NamedQuery(name = "Respuestas.findByIdintegrante", query = "SELECT r FROM Respuestas r WHERE r.idintegrante = :idintegrante")
    , @NamedQuery(name = "Respuestas.findByEmailintegrante", query = "SELECT r FROM Respuestas r WHERE r.emailintegrante = :emailintegrante")
    , @NamedQuery(name = "Respuestas.findByEmailintegranteAndIdpreguntaAndIdconcurso", query = "SELECT r FROM Respuestas r WHERE r.emailintegrante = :emailintegrante AND r.idpregunta = :idpregunta AND r.idconcurso = :idconcurso")
    , @NamedQuery(name = "Respuestas.findByIdpreguntaAndIdconcursoAndIdEvento", query = "SELECT r FROM Respuestas r WHERE r.idpregunta = :idpregunta AND r.idconcurso = :idconcurso AND r.idevento = :idevento")
    , @NamedQuery(name = "Respuestas.findByNombreintegrante", query = "SELECT r FROM Respuestas r WHERE r.nombreintegrante = :nombreintegrante")
    , @NamedQuery(name = "Respuestas.findByUrlimage", query = "SELECT r FROM Respuestas r WHERE r.urlimage = :urlimage")
    , @NamedQuery(name = "Respuestas.findByAcierto", query = "SELECT r FROM Respuestas r WHERE r.acierto = :acierto")
    , @NamedQuery(name = "Respuestas.findByError", query = "SELECT r FROM Respuestas r WHERE r.error = :error")
    , @NamedQuery(name = "Respuestas.resetAciertosAndErrores", query = "Update Respuestas r SET r.acierto = 0, r.error = 0 WHERE r.id = :id")
    , @NamedQuery(name = "Respuestas.findByBodytext", query = "SELECT r FROM Respuestas r WHERE r.bodytext = :bodytext")
    , @NamedQuery(name = "Respuestas.findByFechacreacion", query = "SELECT r FROM Respuestas r WHERE r.fechacreacion = :fechacreacion")
    , @NamedQuery(name = "Respuestas.findByIdEventoAndTopicId", query = "SELECT  r FROM Respuestas r WHERE r.idevento = :idevento AND r.topicid = :topicid")
    , @NamedQuery(name = "Respuestas.findByIdEventoAndCorreoSpaces", query = "SELECT  r FROM Respuestas r WHERE r.idevento = :idevento AND r.emailintegrante = :emailintegrante")
    , @NamedQuery(name = "Respuestas.findByEstatus", query = "SELECT r FROM Respuestas r WHERE r.estatus = :estatus")})
public class Respuestas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Size(max = 2147483647)
    @Column(name = "idrespuesta")
    private String idrespuesta;
    @Size(max = 2147483647)
    @Column(name = "idpregunta")
    private String idpregunta;
    @Size(max = 2147483647)
    @Column(name = "idconcurso")
    private String idconcurso;
    @Size(max = 2147483647)
    @Column(name = "idintegrante")
    private String idintegrante;
    @Size(max = 2147483647)
    @Column(name = "emailintegrante")
    private String emailintegrante;
    @Size(max = 2147483647)
    @Column(name = "nombreintegrante")
    private String nombreintegrante;
    @Size(max = 2147483647)
    @Column(name = "urlimage")
    private String urlimage;
    @Column(name = "acierto")
    private Integer acierto;
    @Column(name = "error")
    private Integer error;
    @Size(max = 2147483647)
    @Column(name = "bodytext")
    private String bodytext;
    @Size(max = 2147483647)
    @Column(name = "fechacreacion")
    private String fechacreacion;
    @Size(max = 2147483647)
    @Column(name = "estatus")
    private String estatus;
    @Size(max = 2147483647)
    @Column(name = "topicid")
    private String topicid;
    @Size(max = 2147483647)
    @Column(name = "idevento")
    private String idevento;
    @Size(max = 2147483647)
    @Column(name = "llaveconcurso")
    private String llaveconcurso;

    public Respuestas() {
    }

    public Respuestas(Long id, String idrespuesta, String idpregunta, String idconcurso, String idintegrante, String emailintegrante, String nombreintegrante, String urlimage, Integer acierto, Integer error, String bodytext, String fechacreacion, String estatus, String topicid, String idevento, String llaveconcurso) {
        this.id = id;
        this.idrespuesta = idrespuesta;
        this.idpregunta = idpregunta;
        this.idconcurso = idconcurso;
        this.idintegrante = idintegrante;
        this.emailintegrante = emailintegrante;
        this.nombreintegrante = nombreintegrante;
        this.urlimage = urlimage;
        this.acierto = acierto;
        this.error = error;
        this.bodytext = bodytext;
        this.fechacreacion = fechacreacion;
        this.estatus = estatus;
        this.topicid = topicid;
        this.idevento = idevento;
        this.llaveconcurso = llaveconcurso;
    }

    public Respuestas(String idrespuesta, String idpregunta, String idconcurso, String idintegrante, String emailintegrante, String nombreintegrante, String urlimage, Integer acierto, Integer error, String bodytext, String fechacreacion, String estatus, String topicid, String idevento, String llaveconcurso) {
        this.idrespuesta = idrespuesta;
        this.idpregunta = idpregunta;
        this.idconcurso = idconcurso;
        this.idintegrante = idintegrante;
        this.emailintegrante = emailintegrante;
        this.nombreintegrante = nombreintegrante;
        this.urlimage = urlimage;
        this.acierto = acierto;
        this.error = error;
        this.bodytext = bodytext;
        this.fechacreacion = fechacreacion;
        this.estatus = estatus;
        this.topicid = topicid;
        this.idevento = idevento;
        this.llaveconcurso = llaveconcurso;
    }

    public String getLlaveconcurso() {
        return llaveconcurso;
    }

    public void setLlaveconcurso(String llaveconcurso) {
        this.llaveconcurso = llaveconcurso;
    }

    public String getIdevento() {
        return idevento;
    }

    public void setIdevento(String idevento) {
        this.idevento = idevento;
    }

    public String getTopicid() {
        return topicid;
    }

    public void setTopicid(String topicid) {
        this.topicid = topicid;
    }

    public Respuestas(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdrespuesta() {
        return idrespuesta;
    }

    public void setIdrespuesta(String idrespuesta) {
        this.idrespuesta = idrespuesta;
    }

    public String getIdpregunta() {
        return idpregunta;
    }

    public void setIdpregunta(String idpregunta) {
        this.idpregunta = idpregunta;
    }

    public String getIdconcurso() {
        return idconcurso;
    }

    public void setIdconcurso(String idconcurso) {
        this.idconcurso = idconcurso;
    }

    public String getIdintegrante() {
        return idintegrante;
    }

    public void setIdintegrante(String idintegrante) {
        this.idintegrante = idintegrante;
    }

    public String getEmailintegrante() {
        return emailintegrante;
    }

    public void setEmailintegrante(String emailintegrante) {
        this.emailintegrante = emailintegrante;
    }

    public String getNombreintegrante() {
        return nombreintegrante;
    }

    public void setNombreintegrante(String nombreintegrante) {
        this.nombreintegrante = nombreintegrante;
    }

    public String getUrlimage() {
        return urlimage;
    }

    public void setUrlimage(String urlimage) {
        this.urlimage = urlimage;
    }

    public Integer getAcierto() {
        return acierto;
    }

    public void setAcierto(Integer acierto) {
        this.acierto = acierto;
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public String getBodytext() {
        return bodytext;
    }

    public void setBodytext(String bodytext) {
        this.bodytext = bodytext;
    }

    public String getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(String fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Respuestas)) {
            return false;
        }
        Respuestas other = (Respuestas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "service.avayaspacescontest.entity.Respuestas[ id=" + id + " ]";
    }

}