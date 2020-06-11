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
@Table(name = "preguntas", catalog = "avayaspacescontest", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Preguntas.findAll", query = "SELECT p FROM Preguntas p")
    , @NamedQuery(name = "Preguntas.findById", query = "SELECT p FROM Preguntas p WHERE p.id = :id")
    , @NamedQuery(name = "Preguntas.findByIdpregunta", query = "SELECT p FROM Preguntas p WHERE p.idpregunta = :idpregunta")
    , @NamedQuery(name = "Preguntas.findByIdpreguntaAndIdconcursoAndFamilyChildrenAndIdEvento", query = "SELECT p FROM Preguntas p WHERE p.idpregunta = :idpregunta AND p.idconcurso = :idconcurso AND p.familypregunta = :familypregunta AND p.idevento = :idevento")
    , @NamedQuery(name = "Preguntas.findByIdconcurso", query = "SELECT p FROM Preguntas p WHERE p.idconcurso = :idconcurso")
    , @NamedQuery(name = "Preguntas.findByIdconcursoAndCreadoPorAndLlave", query = "SELECT p FROM Preguntas p WHERE p.idconcurso = :idconcurso AND p.creadopor = :creadopor AND p.llave = :llave AND p.familypregunta = :familypregunta")
    , @NamedQuery(name = "Preguntas.findByIdconcursoAndIdEventoAndFamily", query = "SELECT p FROM Preguntas p WHERE p.idconcurso = :idconcurso AND p.idevento = :idevento AND p.familypregunta = :familypregunta ORDER BY p.indexorder")
    , @NamedQuery(name = "Preguntas.findByLlave", query = "SELECT p FROM Preguntas p WHERE p.llave = :llave")
    , @NamedQuery(name = "Preguntas.findByPregunta", query = "SELECT p FROM Preguntas p WHERE p.pregunta = :pregunta")
    , @NamedQuery(name = "Preguntas.findByOpciona", query = "SELECT p FROM Preguntas p WHERE p.opciona = :opciona")
    , @NamedQuery(name = "Preguntas.findByOpcionb", query = "SELECT p FROM Preguntas p WHERE p.opcionb = :opcionb")
    , @NamedQuery(name = "Preguntas.findByOpcionc", query = "SELECT p FROM Preguntas p WHERE p.opcionc = :opcionc")
    , @NamedQuery(name = "Preguntas.findByOpciond", query = "SELECT p FROM Preguntas p WHERE p.opciond = :opciond")
    , @NamedQuery(name = "Preguntas.findByRespuestauno", query = "SELECT p FROM Preguntas p WHERE p.respuestauno = :respuestauno")
    , @NamedQuery(name = "Preguntas.findByRespuestados", query = "SELECT p FROM Preguntas p WHERE p.respuestados = :respuestados")
    , @NamedQuery(name = "Preguntas.findByRespuestatres", query = "SELECT p FROM Preguntas p WHERE p.respuestatres = :respuestatres")
    , @NamedQuery(name = "Preguntas.findByRespuestacuatro", query = "SELECT p FROM Preguntas p WHERE p.respuestacuatro = :respuestacuatro")
    , @NamedQuery(name = "Preguntas.findByFechacreacion", query = "SELECT p FROM Preguntas p WHERE p.fechacreacion = :fechacreacion")
    , @NamedQuery(name = "Preguntas.findByFechaejecucion", query = "SELECT p FROM Preguntas p WHERE p.fechaejecucion = :fechaejecucion")
    , @NamedQuery(name = "Preguntas.findByFake", query = "SELECT p FROM Preguntas p WHERE p.fake = :fake")
    , @NamedQuery(name = "Preguntas.findByEstatus", query = "SELECT p FROM Preguntas p WHERE p.estatus = :estatus")
    , @NamedQuery(name = "Preguntas.findByCreadopor", query = "SELECT p FROM Preguntas p WHERE p.creadopor = :creadopor")
    , @NamedQuery(name = "Preguntas.findByIndexorder", query = "SELECT p FROM Preguntas p WHERE p.indexorder = :indexorder")
    , @NamedQuery(name = "Preguntas.findByIdConcursoAndCreadoPor", query = "SELECT p FROM Preguntas p WHERE p.idconcurso = :idconcurso AND p.creadopor = :creadopor AND p.familypregunta = :familypregunta ORDER BY p.indexorder")
    , @NamedQuery(name = "Preguntas.updateEstatusTerminated", query = "UPDATE Preguntas p SET p.estatus = 'TERMINATED' WHERE p.id = :id")
    , @NamedQuery(name = "Preguntas.findByPoints", query = "SELECT p FROM Preguntas p WHERE p.points = :points")})
public class Preguntas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Size(max = 2147483647)
    @Column(name = "idpregunta")
    private String idpregunta;
    @Size(max = 2147483647)
    @Column(name = "idconcurso")
    private String idconcurso;
    @Size(max = 2147483647)
    @Column(name = "llave")
    private String llave;
    @Size(max = 2147483647)
    @Column(name = "pregunta")
    private String pregunta;
    @Size(max = 2147483647)
    @Column(name = "opciona")
    private String opciona;
    @Size(max = 2147483647)
    @Column(name = "opcionb")
    private String opcionb;
    @Size(max = 2147483647)
    @Column(name = "opcionc")
    private String opcionc;
    @Size(max = 2147483647)
    @Column(name = "opciond")
    private String opciond;
    @Size(max = 2147483647)
    @Column(name = "respuestauno")
    private String respuestauno;
    @Size(max = 2147483647)
    @Column(name = "respuestados")
    private String respuestados;
    @Size(max = 2147483647)
    @Column(name = "respuestatres")
    private String respuestatres;
    @Size(max = 2147483647)
    @Column(name = "respuestacuatro")
    private String respuestacuatro;
    @Size(max = 2147483647)
    @Column(name = "fechacreacion")
    private String fechacreacion;
    @Size(max = 2147483647)
    @Column(name = "fechaejecucion")
    private String fechaejecucion;
    @Column(name = "fake")
    private Boolean fake;
    @Size(max = 2147483647)
    @Column(name = "estatus")
    private String estatus;
    @Size(max = 2147483647)
    @Column(name = "creadopor")
    private String creadopor;
    @Column(name = "indexorder")
    private Integer indexorder;
    @Column(name = "points")
    private Integer points;
    @Size(max = 2147483647)
    @Column(name = "imageurl")
    private String imageurl;
    @Size(max = 2147483647)
    @Column(name = "timepregunta")
    private String timepregunta;
    @Size(max = 2147483647)
    @Column(name = "idevento")
    private String idevento;
    @Size(max = 2147483647)
    @Column(name = "familypregunta")
    private String familypregunta;

    public Preguntas() {
    }

    public Preguntas(String idpregunta, String idconcurso, String llave, String pregunta, String opciona, String opcionb, String opcionc, String opciond, String respuestauno, String respuestados, String respuestatres, String respuestacuatro, String fechacreacion, String fechaejecucion, Boolean fake, String estatus, String creadopor, Integer indexorder, Integer points, String imageurl, String timepregunta, String idevento, String familypregunta) {
        this.idpregunta = idpregunta;
        this.idconcurso = idconcurso;
        this.llave = llave;
        this.pregunta = pregunta;
        this.opciona = opciona;
        this.opcionb = opcionb;
        this.opcionc = opcionc;
        this.opciond = opciond;
        this.respuestauno = respuestauno;
        this.respuestados = respuestados;
        this.respuestatres = respuestatres;
        this.respuestacuatro = respuestacuatro;
        this.fechacreacion = fechacreacion;
        this.fechaejecucion = fechaejecucion;
        this.fake = fake;
        this.estatus = estatus;
        this.creadopor = creadopor;
        this.indexorder = indexorder;
        this.points = points;
        this.imageurl = imageurl;
        this.timepregunta = timepregunta;
        this.idevento = idevento;
        this.familypregunta = familypregunta;
    }

    public String getTimepregunta() {
        return timepregunta;
    }

    public void setTimepregunta(String timepregunta) {
        this.timepregunta = timepregunta;
    }

    public String getIdevento() {
        return idevento;
    }

    public void setIdevento(String idevento) {
        this.idevento = idevento;
    }

    public String getFamily() {
        return familypregunta;
    }

    public void setFamily(String familypregunta) {
        this.familypregunta = familypregunta;
    }

    public String getTime() {
        return timepregunta;
    }

    public void setTime(String timepregunta) {
        this.timepregunta = timepregunta;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public Preguntas(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getLlave() {
        return llave;
    }

    public void setLlave(String llave) {
        this.llave = llave;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getOpciona() {
        return opciona;
    }

    public void setOpciona(String opciona) {
        this.opciona = opciona;
    }

    public String getOpcionb() {
        return opcionb;
    }

    public void setOpcionb(String opcionb) {
        this.opcionb = opcionb;
    }

    public String getOpcionc() {
        return opcionc;
    }

    public void setOpcionc(String opcionc) {
        this.opcionc = opcionc;
    }

    public String getOpciond() {
        return opciond;
    }

    public void setOpciond(String opciond) {
        this.opciond = opciond;
    }

    public String getRespuestauno() {
        return respuestauno;
    }

    public void setRespuestauno(String respuestauno) {
        this.respuestauno = respuestauno;
    }

    public String getRespuestados() {
        return respuestados;
    }

    public void setRespuestados(String respuestados) {
        this.respuestados = respuestados;
    }

    public String getRespuestatres() {
        return respuestatres;
    }

    public void setRespuestatres(String respuestatres) {
        this.respuestatres = respuestatres;
    }

    public String getRespuestacuatro() {
        return respuestacuatro;
    }

    public void setRespuestacuatro(String respuestacuatro) {
        this.respuestacuatro = respuestacuatro;
    }

    public String getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(String fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public String getFechaejecucion() {
        return fechaejecucion;
    }

    public void setFechaejecucion(String fechaejecucion) {
        this.fechaejecucion = fechaejecucion;
    }

    public Boolean getFake() {
        return fake;
    }

    public void setFake(Boolean fake) {
        this.fake = fake;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getCreadopor() {
        return creadopor;
    }

    public void setCreadopor(String creadopor) {
        this.creadopor = creadopor;
    }

    public Integer getIndexorder() {
        return indexorder;
    }

    public void setIndexorder(Integer indexorder) {
        this.indexorder = indexorder;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
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
        if (!(object instanceof Preguntas)) {
            return false;
        }
        Preguntas other = (Preguntas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "service.avayaspacescontest.entity.Preguntas[ id=" + id + " ]";
    }

}
