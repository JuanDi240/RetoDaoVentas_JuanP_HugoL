package serie;

import java.io.Serializable;

public class Persona implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected String dni;
	protected String nombre;
	public Persona(int id, String dni, String nombre) {
		super();
		
		this.dni = dni;
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		return "Persona [dni=" + dni + ", nombre=" + nombre + "]";
	}
	
	
	

}
