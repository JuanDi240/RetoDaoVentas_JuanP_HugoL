package modelo;

public class Cliente extends Persona {
	
	private String direccion;
	

	public Cliente() {
		super();
	}


	public Cliente(String direccion) {
		super();
		this.direccion = direccion;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	@Override
	public String toString() {
		return "Cliente [direccion=" + direccion + ", id=" + id + ", dni=" + dni + ", nombre=" + nombre + "]";
	}
	
	

}
