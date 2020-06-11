package service.SpacesContest.Entity;

import java.io.Serializable;
import javax.persistence.Basic;
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
@Table(name = "integrantesconcurso", catalog = "avayaspacescontest", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Integrantesconcurso.findAll", query = "SELECT i FROM Integrantesconcurso i")
    , @NamedQuery(name = "Integrantesconcurso.findById", query = "SELECT i FROM Integrantesconcurso i WHERE i.id = :id")
    , @NamedQuery(name = "Integrantesconcurso.findByIdconcurso", query = "SELECT i FROM Integrantesconcurso i WHERE i.idconcurso = :idconcurso")
    , @NamedQuery(name = "Integrantesconcurso.findByIdconcursoAndIdasistenciaAndEstatusAndIdEvento", query = "SELECT i FROM Integrantesconcurso i WHERE i.idconcurso = :idconcurso AND i.asistencia = :asistencia AND i.estatus = :estatus AND i.idevento = :idevento AND i.llaveconcurso = :llaveconcurso")
    , @NamedQuery(name = "Integrantesconcurso.findByIdintegrante", query = "SELECT i FROM Integrantesconcurso i WHERE i.idintegrante = :idintegrante")
    , @NamedQuery(name = "Integrantesconcurso.findByNombreintegrante", query = "SELECT i FROM Integrantesconcurso i WHERE i.nombreintegrante = :nombreintegrante")
    , @NamedQuery(name = "Integrantesconcurso.findByCorreo", query = "SELECT i FROM Integrantesconcurso i WHERE i.correo = :correo")
    , @NamedQuery(name = "Integrantesconcurso.findByCorreoAndIdconcurso", query = "SELECT i FROM Integrantesconcurso i WHERE i.correo = :correo AND i.idconcurso = :idconcurso AND i.llaveconcurso = :llaveconcurso")
    , @NamedQuery(name = "Integrantesconcurso.findByTelefono", query = "SELECT i FROM Integrantesconcurso i WHERE i.telefono = :telefono")
    , @NamedQuery(name = "Integrantesconcurso.findByEmpresa", query = "SELECT i FROM Integrantesconcurso i WHERE i.empresa = :empresa")
    , @NamedQuery(name = "Integrantesconcurso.findByFotourl", query = "SELECT i FROM Integrantesconcurso i WHERE i.fotourl = :fotourl")
    , @NamedQuery(name = "Integrantesconcurso.findByAciertos", query = "SELECT i FROM Integrantesconcurso i WHERE i.aciertos = :aciertos")
    , @NamedQuery(name = "Integrantesconcurso.findByErrores", query = "SELECT i FROM Integrantesconcurso i WHERE i.errores = :errores")
    , @NamedQuery(name = "Integrantesconcurso.findByAsistencia", query = "SELECT i FROM Integrantesconcurso i WHERE i.asistencia = :asistencia")
    , @NamedQuery(name = "Integrantesconcurso.findByFecharegistro", query = "SELECT i FROM Integrantesconcurso i WHERE i.fecharegistro = :fecharegistro")
    , @NamedQuery(name = "Integrantesconcurso.findByEstatus", query = "SELECT i FROM Integrantesconcurso i WHERE i.estatus = :estatus")
    , @NamedQuery(name = "Integrantesconcurso.findByCreadopor", query = "SELECT i FROM Integrantesconcurso i WHERE i.creadopor = :creadopor")
    , @NamedQuery(name = "Integrantesconcurso.findByTotalDeIntegrantesPorLlaveDelConcurso", query = "SELECT i FROM Integrantesconcurso i WHERE i.llaveconcurso = :llaveconcurso")
    , @NamedQuery(name = "Integrantesconcurso.findIntegrantesPorLlaveDelConcurso", query = "SELECT i FROM Integrantesconcurso i WHERE i.llaveconcurso = :llaveconcurso")
    , @NamedQuery(name = "Integrantesconcurso.findByTopicid", query = "SELECT i FROM Integrantesconcurso i WHERE i.topicid = :topicid")})
public class Integrantesconcurso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Size(max = 2147483647)
    @Column(name = "idconcurso")
    private String idconcurso;
    @Size(max = 2147483647)
    @Column(name = "idintegrante")
    private String idintegrante;
    @Size(max = 2147483647)
    @Column(name = "nombreintegrante")
    private String nombreintegrante;
    @Size(max = 2147483647)
    @Column(name = "correo")
    private String correo;
    @Size(max = 2147483647)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 2147483647)
    @Column(name = "empresa")
    private String empresa;
    @Size(max = 2147483647)
    @Column(name = "fotourl")
    private String fotourl;
    @Column(name = "aciertos")
    private Integer aciertos;
    @Column(name = "errores")
    private Integer errores;
    @Size(max = 2147483647)
    @Column(name = "asistencia")
    private String asistencia;
    @Size(max = 2147483647)
    @Column(name = "fecharegistro")
    private String fecharegistro;
    @Size(max = 2147483647)
    @Column(name = "estatus")
    private String estatus;
    @Size(max = 2147483647)
    @Column(name = "creadopor")
    private String creadopor;
    @Size(max = 2147483647)
    @Column(name = "topicid")
    private String topicid;
    @Size(max = 2147483647)
    @Column(name = "idevento")
    private String idevento;
    @Size(max = 2147483647)
    @Column(name = "llaveconcurso")
    private String llaveconcurso;

    public Integrantesconcurso() {
    }

    public Integrantesconcurso(String idconcurso, String idintegrante, String nombreintegrante, String correo, String telefono, String empresa, String fotourl, Integer aciertos, Integer errores, String asistencia, String fecharegistro, String estatus, String creadopor, String topicid, String idevento, String llaveconcurso) {
        this.idconcurso = idconcurso;
        this.idintegrante = idintegrante;
        this.nombreintegrante = nombreintegrante;
        this.correo = correo;
        this.telefono = telefono;
        this.empresa = empresa;
        this.fotourl = fotourl;
        this.aciertos = aciertos;
        this.errores = errores;
        this.asistencia = asistencia;
        this.fecharegistro = fecharegistro;
        this.estatus = estatus;
        this.creadopor = creadopor;
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

    public Integrantesconcurso(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getNombreintegrante() {
        return nombreintegrante;
    }

    public void setNombreintegrante(String nombreintegrante) {
        this.nombreintegrante = nombreintegrante;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getFotourl() {
        return fotourl;
    }

    public void setFotourl(String fotourl) {
        this.fotourl = fotourl;
    }

    public Integer getAciertos() {
        return aciertos;
    }

    public void setAciertos(Integer aciertos) {
        this.aciertos = aciertos;
    }

    public Integer getErrores() {
        return errores;
    }

    public void setErrores(Integer errores) {
        this.errores = errores;
    }

    public String getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(String asistencia) {
        this.asistencia = asistencia;
    }

    public String getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(String fecharegistro) {
        this.fecharegistro = fecharegistro;
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

    public String getTopicid() {
        return topicid;
    }

    public void setTopicid(String topicid) {
        this.topicid = topicid;
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
        if (!(object instanceof Integrantesconcurso)) {
            return false;
        }
        Integrantesconcurso other = (Integrantesconcurso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "service.avayaspacescontest.entity.Integrantesconcurso[ id=" + id + " ]";
    }

}