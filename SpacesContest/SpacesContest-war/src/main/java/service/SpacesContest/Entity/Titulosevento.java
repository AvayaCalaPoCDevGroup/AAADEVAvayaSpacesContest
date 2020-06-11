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
@Table(name = "titulosevento", catalog = "avayaspacescontest", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Titulosevento.findAll", query = "SELECT t FROM Titulosevento t")
    , @NamedQuery(name = "Titulosevento.findById", query = "SELECT t FROM Titulosevento t WHERE t.id = :id")
    , @NamedQuery(name = "Titulosevento.findByIdevento", query = "SELECT t FROM Titulosevento t WHERE t.idevento = :idevento")
    , @NamedQuery(name = "Titulosevento.findByIdeventoAndCreadoPor", query = "SELECT t FROM Titulosevento t WHERE t.idevento = :idevento AND t.creadopor = :creadopor")
    , @NamedQuery(name = "Titulosevento.findByTitulos", query = "SELECT t FROM Titulosevento t WHERE t.titulos = :titulos")
    , @NamedQuery(name = "Titulosevento.findByDescripcion", query = "SELECT t FROM Titulosevento t WHERE t.descripcion = :descripcion")
    , @NamedQuery(name = "Titulosevento.findByCreadopor", query = "SELECT t FROM Titulosevento t WHERE t.creadopor = :creadopor")
    , @NamedQuery(name = "Titulosevento.findByFechadecreacion", query = "SELECT t FROM Titulosevento t WHERE t.fechadecreacion = :fechadecreacion")})
public class Titulosevento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Size(max = 2147483647)
    @Column(name = "idevento")
    private String idevento;
    @Size(max = 2147483647)
    @Column(name = "titulos")
    private String titulos;
    @Size(max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 2147483647)
    @Column(name = "creadopor")
    private String creadopor;
    @Size(max = 2147483647)
    @Column(name = "fechadecreacion")
    private String fechadecreacion;

    public Titulosevento() {
    }

    public Titulosevento(String idevento, String titulos, String descripcion, String creadopor, String fechadecreacion) {
        this.idevento = idevento;
        this.titulos = titulos;
        this.descripcion = descripcion;
        this.creadopor = creadopor;
        this.fechadecreacion = fechadecreacion;
    }

    
    
    public Titulosevento(Long id, String idevento, String titulos, String descripcion, String creadopor, String fechadecreacion) {
        this.id = id;
        this.idevento = idevento;
        this.titulos = titulos;
        this.descripcion = descripcion;
        this.creadopor = creadopor;
        this.fechadecreacion = fechadecreacion;
    }
    
    

    public Titulosevento(Long id) {
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

    public String getTitulos() {
        return titulos;
    }

    public void setTitulos(String titulos) {
        this.titulos = titulos;
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
        if (!(object instanceof Titulosevento)) {
            return false;
        }
        Titulosevento other = (Titulosevento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "service.avayaspacescontest.entity.Titulosevento[ id=" + id + " ]";
    }
    
}
