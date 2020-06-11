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
@Table(name = "integrantessalas", catalog = "avayaspacescontest", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Integrantessalas.findAll", query = "SELECT i FROM Integrantessalas i")
    , @NamedQuery(name = "Integrantessalas.findById", query = "SELECT i FROM Integrantessalas i WHERE i.id = :id")
    , @NamedQuery(name = "Integrantessalas.findByIdsala", query = "SELECT i FROM Integrantessalas i WHERE i.idsala = :idsala")
    , @NamedQuery(name = "Integrantessalas.findByIdintegrante", query = "SELECT i FROM Integrantessalas i WHERE i.idintegrante = :idintegrante")
    , @NamedQuery(name = "Integrantessalas.findByNombreintegrante", query = "SELECT i FROM Integrantessalas i WHERE i.nombreintegrante = :nombreintegrante")
    , @NamedQuery(name = "Integrantessalas.findByCorreo", query = "SELECT i FROM Integrantessalas i WHERE i.correo = :correo")
    , @NamedQuery(name = "Integrantessalas.findByTelefono", query = "SELECT i FROM Integrantessalas i WHERE i.telefono = :telefono")
    , @NamedQuery(name = "Integrantessalas.findByEmpresa", query = "SELECT i FROM Integrantessalas i WHERE i.empresa = :empresa")
    , @NamedQuery(name = "Integrantessalas.findByFotourl", query = "SELECT i FROM Integrantessalas i WHERE i.fotourl = :fotourl")
    , @NamedQuery(name = "Integrantessalas.findByAciertos", query = "SELECT i FROM Integrantessalas i WHERE i.aciertos = :aciertos")
    , @NamedQuery(name = "Integrantessalas.findByErrores", query = "SELECT i FROM Integrantessalas i WHERE i.errores = :errores")
    , @NamedQuery(name = "Integrantessalas.findByAsistencia", query = "SELECT i FROM Integrantessalas i WHERE i.asistencia = :asistencia")
    , @NamedQuery(name = "Integrantessalas.findByFecharegistro", query = "SELECT i FROM Integrantessalas i WHERE i.fecharegistro = :fecharegistro")
    , @NamedQuery(name = "Integrantessalas.findByEstatus", query = "SELECT i FROM Integrantessalas i WHERE i.estatus = :estatus")
    , @NamedQuery(name = "Integrantessalas.findByIdintegranteAndAsistenciaAndStatus", query = "SELECT i FROM Integrantessalas i WHERE i.estatus = :estatus AND i.idintegrante = :idintegrante AND i.asistencia = :asistencia")
    , @NamedQuery(name = "Integrantessalas.findByCorreoAndAsistenciaAndStatus", query = "SELECT i FROM Integrantessalas i WHERE i.estatus = :estatus AND i.correo = :correo AND i.asistencia = :asistencia")
    , @NamedQuery(name = "Integrantessalas.findByCreadopor", query = "SELECT i FROM Integrantessalas i WHERE i.creadopor = :creadopor")})
public class Integrantessalas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Size(max = 2147483647)
    @Column(name = "idsala")
    private String idsala;
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

    public Integrantessalas() {
    }

    public Integrantessalas(Long id) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Integrantessalas)) {
            return false;
        }
        Integrantessalas other = (Integrantessalas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "service.avayaspacescontest.entity.Integrantessalas[ id=" + id + " ]";
    }
    
}