package modelo;

import serie.Persona;

public class Cliente extends Persona {
	private static final long serialVersionUID = 1L;
	private String direccion;
	public Cliente(String dni, String nombre) {
		super(dni, nombre);
	}
	public Cliente(String dni, String nombre, String direccion) {
		super(dni, nombre);
		this.direccion = direccion;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Cliente [direccion=" + direccion + ", dni=" + dni + ", nombre=" + nombre + "]";
	}
	
	
	
	

}
