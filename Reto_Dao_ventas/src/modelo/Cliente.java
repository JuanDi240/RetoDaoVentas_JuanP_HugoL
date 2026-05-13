package modelo;

public class Cliente extends Persona {
	private int id;
	private String direccion;
	public Cliente() {
		super();
	}
	public Cliente(String direccion) {
		super();
		this.direccion = direccion;
	}
	public Cliente(int id, String direccion) {
		super();
		this.id = id;
		this.direccion = direccion;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", direccion=" + direccion + "]";
	}
	

}
