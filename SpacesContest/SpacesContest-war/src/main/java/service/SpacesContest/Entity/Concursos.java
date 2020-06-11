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
@Table(name = "concursos", catalog = "avayaspacescontest", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Concursos.findAll", query = "SELECT c FROM Concursos c")
    , @NamedQuery(name = "Concursos.findById", query = "SELECT c FROM Concursos c WHERE c.id = :id")
    , @NamedQuery(name = "Concursos.findByIdconcurso", query = "SELECT c FROM Concursos c WHERE c.idconcurso = :idconcurso")
    , @NamedQuery(name = "Concursos.findByIdconcursoAndFamilyChildren", query = "SELECT c FROM Concursos c WHERE c.idconcurso = :idconcurso AND c.familyconcurso = :familyconcurso")
    , @NamedQuery(name = "Concursos.findByIdconcursoAndFamily", query = "SELECT c FROM Concursos c WHERE c.idconcurso = :idconcurso AND c.familyconcurso = :familyconcurso")
    , @NamedQuery(name = "Concursos.findByIdconcursoAndIdEventoAndFamilyChildren", query = "SELECT c FROM Concursos c WHERE c.idconcurso = :idconcurso AND c.idevento = :idevento AND c.familyconcurso = :familyconcurso")
    , @NamedQuery(name = "Concursos.findByIdconcursoFamilyParent", query = "SELECT c FROM Concursos c WHERE c.idconcurso = :idconcurso AND c.familyconcurso = :familyconcurso")
    , @NamedQuery(name = "Concursos.updateConcursoByIdConcurso", query = "Update Concursos c SET c.estatus = 'TERMINATED' WHERE c.idconcurso = :idconcurso AND c.familyconcurso = :familyconcurso AND c.llave = :llave")
    , @NamedQuery(name = "Concursos.updateConcursoByIdEmptyidSpace", query = "Update Concursos c SET c.idavayaspaces = '' WHERE c.id = :id")
    , @NamedQuery(name = "Concursos.updateConcursoParentToInactiveById", query = "Update Concursos c SET c.estatus = 'INACTIVO' WHERE c.id = :id")
    , @NamedQuery(name = "Concursos.findByIdsala", query = "SELECT c FROM Concursos c WHERE c.idsala = :idsala")
    , @NamedQuery(name = "Concursos.findByIdevento", query = "SELECT c FROM Concursos c WHERE c.idevento = :idevento")
    , @NamedQuery(name = "Concursos.findByLlave", query = "SELECT c FROM Concursos c WHERE c.llave = :llave")
    , @NamedQuery(name = "Concursos.findByOnline", query = "SELECT c FROM Concursos c WHERE c.online = :online")
    , @NamedQuery(name = "Concursos.findByNombreconcurso", query = "SELECT c FROM Concursos c WHERE c.nombreconcurso = :nombreconcurso")
    , @NamedQuery(name = "Concursos.findByFechacreacion", query = "SELECT c FROM Concursos c WHERE c.fechacreacion = :fechacreacion")
    , @NamedQuery(name = "Concursos.findByFechaejecucion", query = "SELECT c FROM Concursos c WHERE c.fechaejecucion = :fechaejecucion")
    , @NamedQuery(name = "Concursos.findByFechaclausura", query = "SELECT c FROM Concursos c WHERE c.fechaclausura = :fechaclausura")
    , @NamedQuery(name = "Concursos.findByDuracionconcurso", query = "SELECT c FROM Concursos c WHERE c.duracionconcurso = :duracionconcurso")
    , @NamedQuery(name = "Concursos.findByParticipantes", query = "SELECT c FROM Concursos c WHERE c.participantes = :participantes")
    , @NamedQuery(name = "Concursos.findByPreguntas", query = "SELECT c FROM Concursos c WHERE c.preguntas = :preguntas")
    , @NamedQuery(name = "Concursos.findByRespuestas", query = "SELECT c FROM Concursos c WHERE c.respuestas = :respuestas")
    , @NamedQuery(name = "Concursos.findByCorrectas", query = "SELECT c FROM Concursos c WHERE c.correctas = :correctas")
    , @NamedQuery(name = "Concursos.findByIncorrectas", query = "SELECT c FROM Concursos c WHERE c.incorrectas = :incorrectas")
    , @NamedQuery(name = "Concursos.findByArrayganadores", query = "SELECT c FROM Concursos c WHERE c.arrayganadores = :arrayganadores")
    , @NamedQuery(name = "Concursos.findByEstatus", query = "SELECT c FROM Concursos c WHERE c.estatus = :estatus")
    , @NamedQuery(name = "Concursos.findByFake", query = "SELECT c FROM Concursos c WHERE c.fake = :fake")
    , @NamedQuery(name = "Concursos.findByCreadopor", query = "SELECT c FROM Concursos c WHERE c.creadopor = :creadopor")
    , @NamedQuery(name = "Concursos.findByIdavayaspaces", query = "SELECT c FROM Concursos c WHERE c.idavayaspaces = :idavayaspaces")
    , @NamedQuery(name = "Concursos.findByTypeAndEstatus", query = "SELECT c FROM Concursos c WHERE c.type = :type AND c.estatus = :estatus")
    , @NamedQuery(name = "Concursos.findByTypeAndEstatusActivoANDTerinated", query = "SELECT c FROM Concursos c WHERE c.creadopor = :creadopor AND c.type = :type AND c.familyconcurso = :familyconcurso ORDER BY c.isfavorite DESC")
    , @NamedQuery(name = "Concursos.findByTypeAndEstatusActivo", query = "SELECT c FROM Concursos c WHERE c.creadopor = :creadopor AND c.type = :type AND c.familyconcurso = :familyconcurso AND c.estatus = :estatus ORDER BY c.isfavorite DESC")
    , @NamedQuery(name = "Concursos.findByType", query = "SELECT c FROM Concursos c WHERE c.type = :type")})
public class Concursos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Size(max = 2147483647)
    @Column(name = "idconcurso")
    private String idconcurso;
    @Size(max = 2147483647)
    @Column(name = "idsala")
    private String idsala;
    @Size(max = 2147483647)
    @Column(name = "idevento")
    private String idevento;
    @Size(max = 2147483647)
    @Column(name = "llave")
    private String llave;
    @Column(name = "online")
    private Boolean online;
    @Size(max = 2147483647)
    @Column(name = "nombreconcurso")
    private String nombreconcurso;
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
    @Column(name = "duracionconcurso")
    private String duracionconcurso;
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
    @Size(max = 2147483647)
    @Column(name = "arrayganadores")
    private String arrayganadores;
    @Size(max = 2147483647)
    @Column(name = "estatus")
    private String estatus;
    @Column(name = "fake")
    private Boolean fake;
    @Size(max = 2147483647)
    @Column(name = "creadopor")
    private String creadopor;
    @Size(max = 2147483647)
    @Column(name = "idavayaspaces")
    private String idavayaspaces;
    @Size(max = 2147483647)
    @Column(name = "type")
    private String type;
    @Size(max = 2147483647)
    @Column(name = "imageurl")
    private String imageurl;
    @Size(max = 2147483647)
    @Column(name = "color")
    private String color;
    @Column(name = "isfavorite")
    private Boolean isfavorite;
    @Size(max = 2147483647)
    @Column(name = "fontcolor")
    private String fontcolor;
    @Size(max = 2147483647)
    @Column(name = "familyconcurso")
    private String familyconcurso;

    public Concursos() {
    }

    public Concursos(String idconcurso, String idsala, String idevento, String llave, Boolean online, String nombreconcurso, String fechacreacion, String fechaejecucion, String fechaclausura, String duracionconcurso, Integer participantes, Integer preguntas, Integer respuestas, Integer correctas, Integer incorrectas, String arrayganadores, String estatus, Boolean fake, String creadopor, String idavayaspaces, String type, String imageurl, String color, Boolean isfavorite, String fontcolor, String familyconcurso) {
        this.idconcurso = idconcurso;
        this.idsala = idsala;
        this.idevento = idevento;
        this.llave = llave;
        this.online = online;
        this.nombreconcurso = nombreconcurso;
        this.fechacreacion = fechacreacion;
        this.fechaejecucion = fechaejecucion;
        this.fechaclausura = fechaclausura;
        this.duracionconcurso = duracionconcurso;
        this.participantes = participantes;
        this.preguntas = preguntas;
        this.respuestas = respuestas;
        this.correctas = correctas;
        this.incorrectas = incorrectas;
        this.arrayganadores = arrayganadores;
        this.estatus = estatus;
        this.fake = fake;
        this.creadopor = creadopor;
        this.idavayaspaces = idavayaspaces;
        this.type = type;
        this.imageurl = imageurl;
        this.color = color;
        this.isfavorite = isfavorite;
        this.fontcolor = fontcolor;
        this.familyconcurso = familyconcurso;
    }

    
    
    public Concursos(Long id, String idconcurso, String idsala, String idevento, String llave, Boolean online, String nombreconcurso, String fechacreacion, String fechaejecucion, String fechaclausura, String duracionconcurso, Integer participantes, Integer preguntas, Integer respuestas, Integer correctas, Integer incorrectas, String arrayganadores, String estatus, Boolean fake, String creadopor, String idavayaspaces, String type, String imageurl, String color, Boolean isfavorite, String fontcolor, String familyconcurso) {
        this.id = id;
        this.idconcurso = idconcurso;
        this.idsala = idsala;
        this.idevento = idevento;
        this.llave = llave;
        this.online = online;
        this.nombreconcurso = nombreconcurso;
        this.fechacreacion = fechacreacion;
        this.fechaejecucion = fechaejecucion;
        this.fechaclausura = fechaclausura;
        this.duracionconcurso = duracionconcurso;
        this.participantes = participantes;
        this.preguntas = preguntas;
        this.respuestas = respuestas;
        this.correctas = correctas;
        this.incorrectas = incorrectas;
        this.arrayganadores = arrayganadores;
        this.estatus = estatus;
        this.fake = fake;
        this.creadopor = creadopor;
        this.idavayaspaces = idavayaspaces;
        this.type = type;
        this.imageurl = imageurl;
        this.color = color;
        this.isfavorite = isfavorite;
        this.fontcolor = fontcolor;
        this.familyconcurso = familyconcurso;
    }

   
    public String getFamily() {
        return familyconcurso;
    }

    public void setFamily(String familyconcurso) {
        this.familyconcurso = familyconcurso;
    }
    
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Boolean getIsfavorite() {
        return isfavorite;
    }

    public void setIsfavorite(Boolean isfavorite) {
        this.isfavorite = isfavorite;
    }

    public String getFontcolor() {
        return fontcolor;
    }

    public void setFontcolor(String fontcolor) {
        this.fontcolor = fontcolor;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public Concursos(Long id) {
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

    public String getLlave() {
        return llave;
    }

    public void setLlave(String llave) {
        this.llave = llave;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public String getNombreconcurso() {
        return nombreconcurso;
    }

    public void setNombreconcurso(String nombreconcurso) {
        this.nombreconcurso = nombreconcurso;
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

    public String getDuracionconcurso() {
        return duracionconcurso;
    }

    public void setDuracionconcurso(String duracionconcurso) {
        this.duracionconcurso = duracionconcurso;
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

    public Boolean getFake() {
        return fake;
    }

    public void setFake(Boolean fake) {
        this.fake = fake;
    }

    public String getCreadopor() {
        return creadopor;
    }

    public void setCreadopor(String creadopor) {
        this.creadopor = creadopor;
    }

    public String getIdavayaspaces() {
        return idavayaspaces;
    }

    public void setIdavayaspaces(String idavayaspaces) {
        this.idavayaspaces = idavayaspaces;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        if (!(object instanceof Concursos)) {
            return false;
        }
        Concursos other = (Concursos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "service.avayaspacescontest.entity.Concursos[ id=" + id + " ]";
    }

}
