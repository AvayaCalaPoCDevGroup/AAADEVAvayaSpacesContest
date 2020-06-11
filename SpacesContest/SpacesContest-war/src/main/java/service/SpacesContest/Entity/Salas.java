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
@Table(name = "salas", catalog = "avayaspacescontest", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Salas.findAll", query = "SELECT s FROM Salas s")
    , @NamedQuery(name = "Salas.findById", query = "SELECT s FROM Salas s WHERE s.id = :id")
    , @NamedQuery(name = "Salas.findByIdsala", query = "SELECT s FROM Salas s WHERE s.idsala = :idsala")
    , @NamedQuery(name = "Salas.findByIdevento", query = "SELECT s FROM Salas s WHERE s.idevento = :idevento")
    , @NamedQuery(name = "Salas.findByOnline", query = "SELECT s FROM Salas s WHERE s.online = :online")
    , @NamedQuery(name = "Salas.findByNombresala", query = "SELECT s FROM Salas s WHERE s.nombresala = :nombresala")
    , @NamedQuery(name = "Salas.findByFechacreacion", query = "SELECT s FROM Salas s WHERE s.fechacreacion = :fechacreacion")
    , @NamedQuery(name = "Salas.findByFechaejecucion", query = "SELECT s FROM Salas s WHERE s.fechaejecucion = :fechaejecucion")
    , @NamedQuery(name = "Salas.findByFechaclausura", query = "SELECT s FROM Salas s WHERE s.fechaclausura = :fechaclausura")
    , @NamedQuery(name = "Salas.findByDuracionsala", query = "SELECT s FROM Salas s WHERE s.duracionsala = :duracionsala")
    , @NamedQuery(name = "Salas.findByParticipantes", query = "SELECT s FROM Salas s WHERE s.participantes = :participantes")
    , @NamedQuery(name = "Salas.findByPreguntas", query = "SELECT s FROM Salas s WHERE s.preguntas = :preguntas")
    , @NamedQuery(name = "Salas.findByRespuestas", query = "SELECT s FROM Salas s WHERE s.respuestas = :respuestas")
    , @NamedQuery(name = "Salas.findByCorrectas", query = "SELECT s FROM Salas s WHERE s.correctas = :correctas")
    , @NamedQuery(name = "Salas.findByIncorrectas", query = "SELECT s FROM Salas s WHERE s.incorrectas = :incorrectas")
    , @NamedQuery(name = "Salas.findByConcursos", query = "SELECT s FROM Salas s WHERE s.concursos = :concursos")
    , @NamedQuery(name = "Salas.findByArrayganadores", query = "SELECT s FROM Salas s WHERE s.arrayganadores = :arrayganadores")
    , @NamedQuery(name = "Salas.findByEstatus", query = "SELECT s FROM Salas s WHERE s.estatus = :estatus")
    , @NamedQuery(name = "Salas.findByCreadopor", query = "SELECT s FROM Salas s WHERE s.creadopor = :creadopor")
    , @NamedQuery(name = "Salas.findByIdspace", query = "SELECT s FROM Salas s WHERE s.idspace = :idspace")})
public class Salas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Size(max = 2147483647)
    @Column(name = "idsala")
    private String idsala;
    @Size(max = 2147483647)
    @Column(name = "idevento")
    private String idevento;
    @Column(name = "online")
    private Boolean online;
    @Size(max = 2147483647)
    @Column(name = "nombresala")
    private String nombresala;
    @Size(max = 2147483647)
    @Column(name = "fechacreacion")
    private String fechacreacion;
    @Size(max = 2147483647)
    @Column(name = "fechaejecucion")
    private String fechaejecucion;
    @Size(max = 2147483647)
    @Column(name = "fechaclausura")
    private String fechaclausura;
    @Size(max = 2147483647)
    @Column(name = "duracionsala")
    private String duracionsala;
    @Column(name = "participantes")
    private Integer participantes;
    @Column(name = "preguntas")
    private Integer preguntas;
    @Column(name = "respuestas")
    private Integer respuestas;
    @Column(name = "correctas")
    private Integer correctas;
    @Column(name = "incorrectas")
    private Integer incorrectas;
    @Column(name = "concursos")
    private Integer concursos;
    @Size(max = 2147483647)
    @Column(name = "arrayganadores")
    private String arrayganadores;
    @Size(max = 2147483647)
    @Column(name = "estatus")
    private String estatus;
    @Size(max = 2147483647)
    @Column(name = "creadopor")
    private String creadopor;
    @Size(max = 2147483647)
    @Column(name = "idspace")
    private String idspace;

    public Salas() {
    }

    public Salas(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdsala() {
        return idsala;
    }

    public void setIdsala(String idsala) {
        this.idsala = idsala;
    }

    public String getIdevento() {
        return idevento;
    }

    public void setIdevento(String idevento) {
        this.idevento = idevento;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public String getNombresala() {
        return nombresala;
    }

    public void setNombresala(String nombresala) {
        this.nombresala = nombresala;
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

    public String getFechaclausura() {
        return fechaclausura;
    }

    public void setFechaclausura(String fechaclausura) {
        this.fechaclausura = fechaclausura;
    }

    public String getDuracionsala() {
        return duracionsala;
    }

    public void setDuracionsala(String duracionsala) {
        this.duracionsala = duracionsala;
    }

    public Integer getParticipantes() {
        return participantes;
    }

    public void setParticipantes(Integer participantes) {
        this.participantes = participantes;
    }

    public Integer getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(Integer preguntas) {
        this.preguntas = preguntas;
    }

    public Integer getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(Integer respuestas) {
        this.respuestas = respuestas;
    }

    public Integer getCorrectas() {
        return correctas;
    }

    public void setCorrectas(Integer correctas) {
        this.correctas = correctas;
    }

    public Integer getIncorrectas() {
        return incorrectas;
    }

    public void setIncorrectas(Integer incorrectas) {
        this.incorrectas = incorrectas;
    }

    public Integer getConcursos() {
        return concursos;
    }

    public void setConcursos(Integer concursos) {
        this.concursos = concursos;
    }

    public String getArrayganadores() {
        return arrayganadores;
    }

    public void setArrayganadores(String arrayganadores) {
        this.arrayganadores = arrayganadores;
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

    public String getIdspace() {
        return idspace;
    }

    public void setIdspace(String idspace) {
        this.idspace = idspace;
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
        if (!(object instanceof Salas)) {
            return false;
        }
        Salas other = (Salas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "service.avayaspacescontest.entity.Salas[ id=" + id + " ]";
    }
    
}
