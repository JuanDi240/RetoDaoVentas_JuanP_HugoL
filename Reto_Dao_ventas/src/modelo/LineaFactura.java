package modelo;

public class LineaFactura {
	int id;
	int id_factura;
	int id_producto;
	int cantidad;
	double precio_unitario;
	double importe;
	
	public LineaFactura() {
		super();
	}

	public LineaFactura(int id_factura, int id_producto, int cantidad, double precio_unitario, double importe) {
		super();
		this.id_factura = id_factura;
		this.id_producto = id_producto;
		this.cantidad = cantidad;
		this.precio_unitario = precio_unitario;
		this.importe = importe;
	}

	public LineaFactura(int id, int id_factura, int id_producto, int cantidad, double precio_unitario, double importe) {
		super();
		this.id = id;
		this.id_factura = id_factura;
		this.id_producto = id_producto;
		this.cantidad = cantidad;
		this.precio_unitario = precio_unitario;
		this.importe = importe;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_factura() {
		return id_factura;
	}

	public void setId_factura(int id_factura) {
		this.id_factura = id_factura;
	}

	public int getId_producto() {
		return id_producto;
	}

	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecio_unitario() {
		return precio_unitario;
	}

	public void setPrecio_unitario(double precio_unitario) {
		this.precio_unitario = precio_unitario;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	@Override
	public String toString() {
		return "LineaFactura [id=" + id + ", id_factura=" + id_factura + ", id_producto=" + id_producto + ", cantidad="
				+ cantidad + ", precio_unitario=" + precio_unitario + ", importe=" + importe + "]";
	}
	
	
	
}
