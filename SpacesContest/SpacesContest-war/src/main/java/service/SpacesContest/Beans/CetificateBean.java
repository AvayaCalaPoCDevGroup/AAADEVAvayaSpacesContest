package service.SpacesContest.Beans;

import java.io.Serializable;

public class CetificateBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String nombre;
	private String apellido;
	private String empresa;
	private String fechaEvento;
	private String email;
	
	public CetificateBean() {
	}
	
	public CetificateBean(String nombre, String apellido, String empresa, String fechaEvento, String email) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.empresa = empresa;
		this.fechaEvento = fechaEvento;
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getFechaEvento() {
		return fechaEvento;
	}
	public void setFechaEvento(String fechaEvento) {
		this.fechaEvento = fechaEvento;
	}
	
	
}
