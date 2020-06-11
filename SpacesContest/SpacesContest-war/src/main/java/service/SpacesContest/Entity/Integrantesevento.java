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
@Table(name = "integrantesevento", catalog = "avayaspacescontest", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Integrantesevento.findAll", query = "SELECT i FROM Integrantesevento i")
    , @NamedQuery(name = "Integrantesevento.findById", query = "SELECT i FROM Integrantesevento i WHERE i.id = :id")
    , @NamedQuery(name = "Integrantesevento.findByIdintegrante", query = "SELECT i FROM Integrantesevento i WHERE i.idintegrante = :idintegrante")
    , @NamedQuery(name = "Integrantesevento.findByIdevento", query = "SELECT i FROM Integrantesevento i WHERE i.idevento = :idevento")
    , @NamedQuery(name = "Integrantesevento.findByIdeventoTotalRgistrados", query = "SELECT COUNT(i) FROM Integrantesevento i WHERE i.idevento = :idevento")
    , @NamedQuery(name = "Integrantesevento.findByIdcertificado", query = "SELECT i FROM Integrantesevento i WHERE i.idcertificado = :idcertificado")
    , @NamedQuery(name = "Integrantesevento.findByIdavayaspaces", query = "SELECT i FROM Integrantesevento i WHERE i.idavayaspaces = :idavayaspaces")
    , @NamedQuery(name = "Integrantesevento.findByNombreintegrante", query = "SELECT i FROM Integrantesevento i WHERE i.nombreintegrante = :nombreintegrante")
    , @NamedQuery(name = "Integrantesevento.findByApelllidos", query = "SELECT i FROM Integrantesevento i WHERE i.apelllidos = :apelllidos")
    , @NamedQuery(name = "Integrantesevento.findByCorreo", query = "SELECT i FROM Integrantesevento i WHERE i.correo = :correo")
    , @NamedQuery(name = "Integrantesevento.findByCorreoAndIdEvento", query = "SELECT i FROM Integrantesevento i WHERE i.correo = :correo AND i.idevento = :idevento")
    , @NamedQuery(name = "Integrantesevento.findByCorreoavayaspaces", query = "SELECT i FROM Integrantesevento i WHERE i.correoavayaspaces = :correoavayaspaces")
    , @NamedQuery(name = "Integrantesevento.findByCorreoavayaspacesAndIdEvento", query = "SELECT i FROM Integrantesevento i WHERE i.correoavayaspaces = :correoavayaspaces AND i.idevento = :idevento")
    , @NamedQuery(name = "Integrantesevento.findByRole", query = "SELECT i FROM Integrantesevento i WHERE i.role = :role")
    , @NamedQuery(name = "Integrantesevento.findByPictureurl", query = "SELECT i FROM Integrantesevento i WHERE i.pictureurl = :pictureurl")
    , @NamedQuery(name = "Integrantesevento.findByEmpresa", query = "SELECT i FROM Integrantesevento i WHERE i.empresa = :empresa")
    , @NamedQuery(name = "Integrantesevento.findByFechacreacion", query = "SELECT i FROM Integrantesevento i WHERE i.fechacreacion = :fechacreacion")
    , @NamedQuery(name = "Integrantesevento.findByPuesto", query = "SELECT i FROM Integrantesevento i WHERE i.puesto = :puesto")
    , @NamedQuery(name = "Integrantesevento.findByPais", query = "SELECT i FROM Integrantesevento i WHERE i.pais = :pais")
    , @NamedQuery(name = "Integrantesevento.findByCiudad", query = "SELECT i FROM Integrantesevento i WHERE i.ciudad = :ciudad")
    , @NamedQuery(name = "Integrantesevento.findByEstado", query = "SELECT i FROM Integrantesevento i WHERE i.estado = :estado")
    , @NamedQuery(name = "Integrantesevento.findByCp", query = "SELECT i FROM Integrantesevento i WHERE i.cp = :cp")
    , @NamedQuery(name = "Integrantesevento.findByTelefonooficina", query = "SELECT i FROM Integrantesevento i WHERE i.telefonooficina = :telefonooficina")
    , @NamedQuery(name = "Integrantesevento.findByTelefonomovil", query = "SELECT i FROM Integrantesevento i WHERE i.telefonomovil = :telefonomovil")
    , @NamedQuery(name = "Integrantesevento.findByFacebook", query = "SELECT i FROM Integrantesevento i WHERE i.facebook = :facebook")
    , @NamedQuery(name = "Integrantesevento.findByTwitter", query = "SELECT i FROM Integrantesevento i WHERE i.twitter = :twitter")
    , @NamedQuery(name = "Integrantesevento.findByCompanysfunction", query = "SELECT i FROM Integrantesevento i WHERE i.companysfunction = :companysfunction")
    , @NamedQuery(name = "Integrantesevento.findByCompanysemployees", query = "SELECT i FROM Integrantesevento i WHERE i.companysemployees = :companysemployees")
    , @NamedQuery(name = "Integrantesevento.findByCompanysjoblevel", query = "SELECT i FROM Integrantesevento i WHERE i.companysjoblevel = :companysjoblevel")
    , @NamedQuery(name = "Integrantesevento.findByCompanysjobfuntion", query = "SELECT i FROM Integrantesevento i WHERE i.companysjobfuntion = :companysjobfuntion")
    , @NamedQuery(name = "Integrantesevento.findByCompanysitbudget", query = "SELECT i FROM Integrantesevento i WHERE i.companysitbudget = :companysitbudget")
    , @NamedQuery(name = "Integrantesevento.findByCompanyspurchaserole", query = "SELECT i FROM Integrantesevento i WHERE i.companyspurchaserole = :companyspurchaserole")
    , @NamedQuery(name = "Integrantesevento.findByAttendedyears", query = "SELECT i FROM Integrantesevento i WHERE i.attendedyears = :attendedyears")
    , @NamedQuery(name = "Integrantesevento.findByCheckboxtechnologies", query = "SELECT i FROM Integrantesevento i WHERE i.checkboxtechnologies = :checkboxtechnologies")
    , @NamedQuery(name = "Integrantesevento.findByCheckboxoem", query = "SELECT i FROM Integrantesevento i WHERE i.checkboxoem = :checkboxoem")
    , @NamedQuery(name = "Integrantesevento.findByCheckboxavayadevconect", query = "SELECT i FROM Integrantesevento i WHERE i.checkboxavayadevconect = :checkboxavayadevconect")
    , @NamedQuery(name = "Integrantesevento.findByAciertos", query = "SELECT i FROM Integrantesevento i WHERE i.aciertos = :aciertos")
    , @NamedQuery(name = "Integrantesevento.findByErrores", query = "SELECT i FROM Integrantesevento i WHERE i.errores = :errores")
    , @NamedQuery(name = "Integrantesevento.findByAsistencia", query = "SELECT i FROM Integrantesevento i WHERE i.asistencia = :asistencia")
    , @NamedQuery(name = "Integrantesevento.findByEstatus", query = "SELECT i FROM Integrantesevento i WHERE i.estatus = :estatus")})
public class Integrantesevento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Size(max = 2147483647)
    @Column(name = "idintegrante")
    private String idintegrante;
    @Size(max = 2147483647)
    @Column(name = "idevento")
    private String idevento;
    @Size(max = 2147483647)
    @Column(name = "idcertificado")
    private String idcertificado;
    @Size(max = 2147483647)
    @Column(name = "idavayaspaces")
    private String idavayaspaces;
    @Size(max = 2147483647)
    @Column(name = "nombreintegrante")
    private String nombreintegrante;
    @Size(max = 2147483647)
    @Column(name = "apelllidos")
    private String apelllidos;
    @Size(max = 2147483647)
    @Column(name = "correo")
    private String correo;
    @Size(max = 2147483647)
    @Column(name = "correoavayaspaces")
    private String correoavayaspaces;
    @Size(max = 2147483647)
    @Column(name = "role")
    private String role;
    @Size(max = 2147483647)
    @Column(name = "pictureurl")
    private String pictureurl;
    @Size(max = 2147483647)
    @Column(name = "empresa")
    private String empresa;
    @Size(max = 2147483647)
    @Column(name = "fechacreacion")
    private String fechacreacion;
    @Size(max = 2147483647)
    @Column(name = "puesto")
    private String puesto;
    @Size(max = 2147483647)
    @Column(name = "pais")
    private String pais;
    @Size(max = 2147483647)
    @Column(name = "ciudad")
    private String ciudad;
    @Size(max = 2147483647)
    @Column(name = "estado")
    private String estado;
    @Size(max = 2147483647)
    @Column(name = "cp")
    private String cp;
    @Size(max = 2147483647)
    @Column(name = "telefonooficina")
    private String telefonooficina;
    @Size(max = 2147483647)
    @Column(name = "telefonomovil")
    private String telefonomovil;
    @Size(max = 2147483647)
    @Column(name = "facebook")
    private String facebook;
    @Size(max = 2147483647)
    @Column(name = "twitter")
    private String twitter;
    @Size(max = 2147483647)
    @Column(name = "companysfunction")
    private String companysfunction;
    @Size(max = 2147483647)
    @Column(name = "companysemployees")
    private String companysemployees;
    @Size(max = 2147483647)
    @Column(name = "companysjoblevel")
    private String companysjoblevel;
    @Size(max = 2147483647)
    @Column(name = "companysjobfuntion")
    private String companysjobfuntion;
    @Size(max = 2147483647)
    @Column(name = "companysitbudget")
    private String companysitbudget;
    @Size(max = 2147483647)
    @Column(name = "companyspurchaserole")
    private String companyspurchaserole;
    @Size(max = 2147483647)
    @Column(name = "attendedyears")
    private String attendedyears;
    @Column(name = "checkboxtechnologies")
    private Boolean checkboxtechnologies;
    @Column(name = "checkboxoem")
    private Boolean checkboxoem;
    @Column(name = "checkboxavayadevconect")
    private Boolean checkboxavayadevconect;
    @Column(name = "aciertos")
    private Integer aciertos;
    @Column(name = "errores")
    private Integer errores;
    @Size(max = 2147483647)
    @Column(name = "asistencia")
    private String asistencia;
    @Size(max = 2147483647)
    @Column(name = "estatus")
    private String estatus;

    public Integrantesevento() {
    }

    
    
    public Integrantesevento(String idintegrante, String idevento, String idcertificado, String idavayaspaces,
			String nombreintegrante, String apelllidos, String correo, String correoavayaspaces, String role,
			String pictureurl, String empresa, String fechacreacion, String puesto, String pais, String ciudad,
			String estado, String cp, String telefonooficina, String telefonomovil, String facebook, String twitter,
			String companysfunction, String companysemployees, String companysjoblevel, String companysjobfuntion,
			String companysitbudget, String companyspurchaserole, String attendedyears, Boolean checkboxtechnologies,
			Boolean checkboxoem, Boolean checkboxavayadevconect, Integer aciertos, Integer errores, String asistencia,
			String estatus) {
		this.idintegrante = idintegrante;
		this.idevento = idevento;
		this.idcertificado = idcertificado;
		this.idavayaspaces = idavayaspaces;
		this.nombreintegrante = nombreintegrante;
		this.apelllidos = apelllidos;
		this.correo = correo;
		this.correoavayaspaces = correoavayaspaces;
		this.role = role;
		this.pictureurl = pictureurl;
		this.empresa = empresa;
		this.fechacreacion = fechacreacion;
		this.puesto = puesto;
		this.pais = pais;
		this.ciudad = ciudad;
		this.estado = estado;
		this.cp = cp;
		this.telefonooficina = telefonooficina;
		this.telefonomovil = telefonomovil;
		this.facebook = facebook;
		this.twitter = twitter;
		this.companysfunction = companysfunction;
		this.companysemployees = companysemployees;
		this.companysjoblevel = companysjoblevel;
		this.companysjobfuntion = companysjobfuntion;
		this.companysitbudget = companysitbudget;
		this.companyspurchaserole = companyspurchaserole;
		this.attendedyears = attendedyears;
		this.checkboxtechnologies = checkboxtechnologies;
		this.checkboxoem = checkboxoem;
		this.checkboxavayadevconect = checkboxavayadevconect;
		this.aciertos = aciertos;
		this.errores = errores;
		this.asistencia = asistencia;
		this.estatus = estatus;
	}



	public Integrantesevento(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdintegrante() {
        return idintegrante;
    }

    public void setIdintegrante(String idintegrante) {
        this.idintegrante = idintegrante;
    }

    public String getIdevento() {
        return idevento;
    }

    public void setIdevento(String idevento) {
        this.idevento = idevento;
    }

    public String getIdcertificado() {
        return idcertificado;
    }

    public void setIdcertificado(String idcertificado) {
        this.idcertificado = idcertificado;
    }

    public String getIdavayaspaces() {
        return idavayaspaces;
    }

    public void setIdavayaspaces(String idavayaspaces) {
        this.idavayaspaces = idavayaspaces;
    }

    public String getNombreintegrante() {
        return nombreintegrante;
    }

    public void setNombreintegrante(String nombreintegrante) {
        this.nombreintegrante = nombreintegrante;
    }

    public String getApelllidos() {
        return apelllidos;
    }

    public void setApelllidos(String apelllidos) {
        this.apelllidos = apelllidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCorreoavayaspaces() {
        return correoavayaspaces;
    }

    public void setCorreoavayaspaces(String correoavayaspaces) {
        this.correoavayaspaces = correoavayaspaces;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPictureurl() {
        return pictureurl;
    }

    public void setPictureurl(String pictureurl) {
        this.pictureurl = pictureurl;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(String fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getTelefonooficina() {
        return telefonooficina;
    }

    public void setTelefonooficina(String telefonooficina) {
        this.telefonooficina = telefonooficina;
    }

    public String getTelefonomovil() {
        return telefonomovil;
    }

    public void setTelefonomovil(String telefonomovil) {
        this.telefonomovil = telefonomovil;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getCompanysfunction() {
        return companysfunction;
    }

    public void setCompanysfunction(String companysfunction) {
        this.companysfunction = companysfunction;
    }

    public String getCompanysemployees() {
        return companysemployees;
    }

    public void setCompanysemployees(String companysemployees) {
        this.companysemployees = companysemployees;
    }

    public String getCompanysjoblevel() {
        return companysjoblevel;
    }

    public void setCompanysjoblevel(String companysjoblevel) {
        this.companysjoblevel = companysjoblevel;
    }

    public String getCompanysjobfuntion() {
        return companysjobfuntion;
    }

    public void setCompanysjobfuntion(String companysjobfuntion) {
        this.companysjobfuntion = companysjobfuntion;
    }

    public String getCompanysitbudget() {
        return companysitbudget;
    }

    public void setCompanysitbudget(String companysitbudget) {
        this.companysitbudget = companysitbudget;
    }

    public String getCompanyspurchaserole() {
        return companyspurchaserole;
    }

    public void setCompanyspurchaserole(String companyspurchaserole) {
        this.companyspurchaserole = companyspurchaserole;
    }

    public String getAttendedyears() {
        return attendedyears;
    }

    public void setAttendedyears(String attendedyears) {
        this.attendedyears = attendedyears;
    }

    public Boolean getCheckboxtechnologies() {
        return checkboxtechnologies;
    }

    public void setCheckboxtechnologies(Boolean checkboxtechnologies) {
        this.checkboxtechnologies = checkboxtechnologies;
    }

    public Boolean getCheckboxoem() {
        return checkboxoem;
    }

    public void setCheckboxoem(Boolean checkboxoem) {
        this.checkboxoem = checkboxoem;
    }

    public Boolean getCheckboxavayadevconect() {
        return checkboxavayadevconect;
    }

    public void setCheckboxavayadevconect(Boolean checkboxavayadevconect) {
        this.checkboxavayadevconect = checkboxavayadevconect;
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
        if (!(object instanceof Integrantesevento)) {
            return false;
        }
        Integrantesevento other = (Integrantesevento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "service.avayaspacescontest.entity.Integrantesevento[ id=" + id + " ]";
    }
    
}
