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
@Table(name = "eventos", catalog = "avayaspacescontest", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Eventos.findAll", query = "SELECT e FROM Eventos e")
    , @NamedQuery(name = "Eventos.findById", query = "SELECT e FROM Eventos e WHERE e.id = :id")
    , @NamedQuery(name = "Eventos.findByIdevento", query = "SELECT e FROM Eventos e WHERE e.idevento = :idevento")
    , @NamedQuery(name = "Eventos.updateEventoToEstatusInactivo", query = "Update Eventos e SET e.estatus  = 'INACTIVO' WHERE e.id = :id")
    , @NamedQuery(name = "Eventos.findByOnline", query = "SELECT e FROM Eventos e WHERE e.online = :online")
    , @NamedQuery(name = "Eventos.findByNombreevento", query = "SELECT e FROM Eventos e WHERE e.nombreevento = :nombreevento")
    , @NamedQuery(name = "Eventos.findByFechacreacion", query = "SELECT e FROM Eventos e WHERE e.fechacreacion = :fechacreacion")
    , @NamedQuery(name = "Eventos.findByFechaejecucion", query = "SELECT e FROM Eventos e WHERE e.fechaejecucion = :fechaejecucion")
    , @NamedQuery(name = "Eventos.findByFechaclausura", query = "SELECT e FROM Eventos e WHERE e.fechaclausura = :fechaclausura")
    , @NamedQuery(name = "Eventos.findByDuracionevento", query = "SELECT e FROM Eventos e WHERE e.duracionevento = :duracionevento")
    , @NamedQuery(name = "Eventos.findByParticipantes", query = "SELECT e FROM Eventos e WHERE e.participantes = :participantes")
    , @NamedQuery(name = "Eventos.findByPreguntas", query = "SELECT e FROM Eventos e WHERE e.preguntas = :preguntas")
    , @NamedQuery(name = "Eventos.findByRespuestas", query = "SELECT e FROM Eventos e WHERE e.respuestas = :respuestas")
    , @NamedQuery(name = "Eventos.findByCorrectas", query = "SELECT e FROM Eventos e WHERE e.correctas = :correctas")
    , @NamedQuery(name = "Eventos.findByIncorrectas", query = "SELECT e FROM Eventos e WHERE e.incorrectas = :incorrectas")
    , @NamedQuery(name = "Eventos.findBySalas", query = "SELECT e FROM Eventos e WHERE e.salas = :salas")
    , @NamedQuery(name = "Eventos.findByConcursos", query = "SELECT e FROM Eventos e WHERE e.concursos = :concursos")
    , @NamedQuery(name = "Eventos.findByArrayganadores", query = "SELECT e FROM Eventos e WHERE e.arrayganadores = :arrayganadores")
    , @NamedQuery(name = "Eventos.findByCiudadsede", query = "SELECT e FROM Eventos e WHERE e.ciudadsede = :ciudadsede")
    , @NamedQuery(name = "Eventos.findByPaissede", query = "SELECT e FROM Eventos e WHERE e.paissede = :paissede")
    , @NamedQuery(name = "Eventos.findByResumenevento", query = "SELECT e FROM Eventos e WHERE e.resumenevento = :resumenevento")
    , @NamedQuery(name = "Eventos.findByArrayidspeakers", query = "SELECT e FROM Eventos e WHERE e.arrayidspeakers = :arrayidspeakers")
    , @NamedQuery(name = "Eventos.findByEstatus", query = "SELECT e FROM Eventos e WHERE e.estatus = :estatus")
    , @NamedQuery(name = "Eventos.findByEstatusActivoAndcreadoPor", query = "SELECT e FROM Eventos e WHERE e.estatus = :estatus AND e.creadopor = :creadopor")
    , @NamedQuery(name = "Eventos.findByCreadopor", query = "SELECT e FROM Eventos e WHERE e.creadopor = :creadopor")})
public class Eventos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Size(max = 2147483647)
    @Column(name = "idevento")
    private String idevento;
    @Column(name = "online")
    private Boolean online;
    @Size(max = 2147483647)
    @Column(name = "nombreevento")
    private String nombreevento;
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
    @Column(name = "duracionevento")
    private String duracionevento;
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
    @Column(name = "salas")
    private Integer salas;
    @Column(name = "concursos")
    private Integer concursos;
    @Size(max = 2147483647)
    @Column(name = "arrayganadores")
    private String arrayganadores;
    @Size(max = 2147483647)
    @Column(name = "ciudadsede")
    private String ciudadsede;
    @Size(max = 2147483647)
    @Column(name = "paissede")
    private String paissede;
    @Size(max = 2147483647)
    @Column(name = "resumenevento")
    private String resumenevento;
    @Size(max = 2147483647)
    @Column(name = "arrayidspeakers")
    private String arrayidspeakers;
    @Size(max = 2147483647)
    @Column(name = "estatus")
    private String estatus;
    @Size(max = 2147483647)
    @Column(name = "creadopor")
    private String creadopor;
    @Size(max = 2147483647)
    @Column(name = "idspace")
    private String idspace;
    @Size(max = 2147483647)
    @Column(name = "joinurl")
    private String joinurl;

    public Eventos() {
    }
    
    

    public Eventos(Long id, String idevento, Boolean online, String nombreevento, String fechacreacion,
			String fechaejecucion, String fechaclausura, String duracionevento, Integer participantes,
			Integer preguntas, Integer respuestas, Integer correctas, Integer incorrectas, Integer salas,
			Integer concursos, String arrayganadores, String ciudadsede, String paissede, String resumenevento,
			String arrayidspeakers, String estatus, String creadopor, String idspace, String joinurl) {
		super();
		this.id = id;
		this.idevento = idevento;
		this.online = online;
		this.nombreevento = nombreevento;
		this.fechacreacion = fechacreacion;
		this.fechaejecucion = fechaejecucion;
		this.fechaclausura = fechaclausura;
		this.duracionevento = duracionevento;
		this.participantes = participantes;
		this.preguntas = preguntas;
		this.respuestas = respuestas;
		this.correctas = correctas;
		this.incorrectas = incorrectas;
		this.salas = salas;
		this.concursos = concursos;
		this.arrayganadores = arrayganadores;
		this.ciudadsede = ciudadsede;
		this.paissede = paissede;
		this.resumenevento = resumenevento;
		this.arrayidspeakers = arrayidspeakers;
		this.estatus = estatus;
		this.creadopor = creadopor;
		this.idspace = idspace;
		this.joinurl = joinurl;
	}



	public Eventos(String idevento, Boolean online, String nombreevento, String fechacreacion, String fechaejecucion, String fechaclausura, String duracionevento, Integer participantes, Integer preguntas, Integer respuestas, Integer correctas, Integer incorrectas, Integer salas, Integer concursos, String arrayganadores, String ciudadsede, String paissede, String resumenevento, String arrayidspeakers, String estatus, String creadopor, String idspace, String joinurl) {
        this.idevento = idevento;
        this.online = online;
        this.nombreevento = nombreevento;
        this.fechacreacion = fechacreacion;
        this.fechaejecucion = fechaejecucion;
        this.fechaclausura = fechaclausura;
        this.duracionevento = duracionevento;
        this.participantes = participantes;
        this.preguntas = preguntas;
        this.respuestas = respuestas;
        this.correctas = correctas;
        this.incorrectas = incorrectas;
        this.salas = salas;
        this.concursos = concursos;
        this.arrayganadores = arrayganadores;
        this.ciudadsede = ciudadsede;
        this.paissede = paissede;
        this.resumenevento = resumenevento;
        this.arrayidspeakers = arrayidspeakers;
        this.estatus = estatus;
        this.creadopor = creadopor;
        this.idspace = idspace;
        this.joinurl = joinurl;
    }

    public String getJoinurl() {
        return joinurl;
    }

    public void setJoinurl(String joinurl) {
        this.joinurl = joinurl;
    }

    public String getIdspace() {
        return idspace;
    }

    public void setIdspace(String idspace) {
        this.idspace = idspace;
    }

    public Eventos(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getNombreevento() {
        return nombreevento;
    }

    public void setNombreevento(String nombreevento) {
        this.nombreevento = nombreevento;
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

    public String getDuracionevento() {
        return duracionevento;
    }

    public void setDuracionevento(String duracionevento) {
        this.duracionevento = duracionevento;
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

    public Integer getSalas() {
        return salas;
    }

    public void setSalas(Integer salas) {
        this.salas = salas;
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

    public String getCiudadsede() {
        return ciudadsede;
    }

    public void setCiudadsede(String ciudadsede) {
        this.ciudadsede = ciudadsede;
    }

    public String getPaissede() {
        return paissede;
    }

    public void setPaissede(String paissede) {
        this.paissede = paissede;
    }

    public String getResumenevento() {
        return resumenevento;
    }

    public void setResumenevento(String resumenevento) {
        this.resumenevento = resumenevento;
    }

    public String getArrayidspeakers() {
        return arrayidspeakers;
    }

    public void setArrayidspeakers(String arrayidspeakers) {
        this.arrayidspeakers = arrayidspeakers;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Eventos)) {
            return false;
        }
        Eventos other = (Eventos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "service.avayaspacescontest.entity.Eventos[ id=" + id + " ]";
    }

}
