package modelo;

public class Empleado extends Persona {
	private int id;
	private String puesto;
	private double salario;
	public Empleado() {
		super();
	}
	public Empleado(String puesto, double salario) {
		super();
		this.puesto = puesto;
		this.salario = salario;
	}
	public Empleado(int id, String puesto, double salario) {
		super();
		this.id = id;
		this.puesto = puesto;
		this.salario = salario;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPuesto() {
		return puesto;
	}
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	@Override
	public String toString() {
		return "Empleado [id=" + id + ", puesto=" + puesto + ", salario=" + salario + "]";
	}
	
}
