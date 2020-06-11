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
@Table(name = "speakers", catalog = "avayaspacescontest", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Speakers.findAll", query = "SELECT s FROM Speakers s")
    , @NamedQuery(name = "Speakers.findById", query = "SELECT s FROM Speakers s WHERE s.id = :id")
    , @NamedQuery(name = "Speakers.findByIdspeaker", query = "SELECT s FROM Speakers s WHERE s.idspeaker = :idspeaker")
    , @NamedQuery(name = "Speakers.findByIdspeakerAndCreadoPor", query = "SELECT s FROM Speakers s WHERE s.idspeaker = :idspeaker AND s.creadopor = :creadopor")
    , @NamedQuery(name = "Speakers.findByNombrespeaker", query = "SELECT s FROM Speakers s WHERE s.nombrespeaker = :nombrespeaker")
    , @NamedQuery(name = "Speakers.findByFoto", query = "SELECT s FROM Speakers s WHERE s.foto = :foto")
    , @NamedQuery(name = "Speakers.findByTitulo", query = "SELECT s FROM Speakers s WHERE s.titulo = :titulo")
    , @NamedQuery(name = "Speakers.findByEmpresa", query = "SELECT s FROM Speakers s WHERE s.empresa = :empresa")
    , @NamedQuery(name = "Speakers.findByDescripcion", query = "SELECT s FROM Speakers s WHERE s.descripcion = :descripcion")
    , @NamedQuery(name = "Speakers.findByCreadopor", query = "SELECT s FROM Speakers s WHERE s.creadopor = :creadopor")
    , @NamedQuery(name = "Speakers.findByFechadecreacion", query = "SELECT s FROM Speakers s WHERE s.fechadecreacion = :fechadecreacion")})
public class Speakers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Size(max = 2147483647)
    @Column(name = "idspeaker")
    private String idspeaker;
    @Size(max = 2147483647)
    @Column(name = "nombrespeaker")
    private String nombrespeaker;
    @Size(max = 2147483647)
    @Column(name = "foto")
    private String foto;
    @Size(max = 2147483647)
    @Column(name = "titulo")
    private String titulo;
    @Size(max = 2147483647)
    @Column(name = "empresa")
    private String empresa;
    @Size(max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 2147483647)
    @Column(name = "creadopor")
    private String creadopor;
    @Size(max = 2147483647)
    @Column(name = "fechadecreacion")
    private String fechadecreacion;

    public Speakers(Long id, String idspeaker, String nombrespeaker, String foto, String titulo, String empresa, String descripcion, String creadopor, String fechadecreacion) {
        this.id = id;
        this.idspeaker = idspeaker;
        this.nombrespeaker = nombrespeaker;
        this.foto = foto;
        this.titulo = titulo;
        this.empresa = empresa;
        this.descripcion = descripcion;
        this.creadopor = creadopor;
        this.fechadecreacion = fechadecreacion;
    }

    public Speakers(String idspeaker, String nombrespeaker, String foto, String titulo, String empresa, String descripcion, String creadopor, String fechadecreacion) {
        this.idspeaker = idspeaker;
        this.nombrespeaker = nombrespeaker;
        this.foto = foto;
        this.titulo = titulo;
        this.empresa = empresa;
        this.descripcion = descripcion;
        this.creadopor = creadopor;
        this.fechadecreacion = fechadecreacion;
    }

    public Speakers() {
    }

    public Speakers(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdspeaker() {
        return idspeaker;
    }

    public void setIdspeaker(String idspeaker) {
        this.idspeaker = idspeaker;
    }

    public String getNombrespeaker() {
        return nombrespeaker;
    }

    public void setNombrespeaker(String nombrespeaker) {
        this.nombrespeaker = nombrespeaker;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCreadopor() {
        return creadopor;
    }

    public void setCreadopor(String creadopor) {
        this.creadopor = creadopor;
    }

    public String getFechadecreacion() {
        return fechadecreacion;
    }

    public void setFechadecreacion(String fechadecreacion) {
        this.fechadecreacion = fechadecreacion;
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
        if (!(object instanceof Speakers)) {
            return false;
        }
        Speakers other = (Speakers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "service.avayaspacescontest.entity.Speakers[ id=" + id + " ]";
    }

}
