package app;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import javax.swing.text.DateFormatter;

import dao.ClienteDAO;
import dao.EmpleadoDAO;
import dao.FacturaDAO;
import dao.LineaFacturaDAO;
import dao.ProductoDAO;
import modelo.Cliente;
import modelo.Empleado;
import modelo.Factura;
import modelo.LineaFactura;
import modelo.Producto;
import serie.Persona;

public class MainGeneral {

	public static void main(String[] args) {
		//1.	Muestra todos los clientes, selecciona uno escribiendo el id y muestra sus datos y sus facturas.
		
		Scanner sc = new Scanner(System.in).useLocale(Locale.US);
		
		ClienteDAO cD = new ClienteDAO();
		FacturaDAO fD = new FacturaDAO();
		LineaFacturaDAO lfD = new LineaFacturaDAO();
		EmpleadoDAO eD = new EmpleadoDAO();
		
		for (Cliente c : cD.obtenerTodos()) {
			System.out.println(c);
			
		}
		
		System.out.println("Introduce el Id del cliente para ver sus datos y facturas: ");
		int id=sc.nextInt();
		System.out.println(cD.obtenerPorId(id));
		
		for (Factura f : fD.obtenerTodos()) {
			if (f.getId_cliente()==id) {
				System.out.println(f);
			}
		}
		
		System.out.println();
		
		//2.	Muestra todos los empleados y seleccionando un id muestra las facturas que ha emitido.
		
		for (Empleado e : eD.obtenerTodos()) {
			System.out.println(e);
			
		}
		System.out.println("Introduce el Id del empleado para ver sus datos y facturas: ");
		int id2=sc.nextInt();
		System.out.println(eD.obtenerPorId(id2));
		
		for (Factura f : fD.obtenerTodos()) {
			if (f.getId_empleado()==id2) {
				System.out.println(f);
			}
		}
		
		System.out.println();
		
		//3.	Muestra una factura por id junto con todas sus líneas de factura.
		
		System.out.println("Introduce el Id de una factura para ver sus datos y sus lineas: ");
		int id3=sc.nextInt();
		System.out.println(fD.obtenerPorId(id3));
		
		for (LineaFactura lfa : lfD.obtenerTodos()) {
			if (lfa.getId_factura()==id3) {
				System.out.println(lfa);
			}
		}
		
		System.out.println();
		
		//4.	Muestra todas las facturas de un mes, pide el número del mes y calcula el total facturado ese mes.
		
		System.out.println("Introduce el Mes de una factura para ver sus datos");
		int fecha=sc.nextInt();
		double sumaTotal=0;
		
		for (Factura f : fD.obtenerTodos()) {
			if (f.getFecha().getMonthValue()==fecha) {
				System.out.println(f);
				sumaTotal+=f.getTotal();
			}
		}
		System.out.println("Suma Total de las facturas: " + sumaTotal);
		
		System.out.println();
		
		//5.	Muestra todas las facturas de una fecha concreta, por ejemplo 05-05-2026
		
		System.out.println("Introduce la fecha de una factura para ver sus datos (ej:)");
		//String texto=sc.next();
		LocalDate fecha2 = LocalDate.parse("2026-05-05");
		
		
		for (Factura f : fD.obtenerTodos()) {
			if (f.getFecha().isEqual(fecha2)) {
				System.out.println(f);
			}
		}
		
		//6. 	Añade un cliente: 12345678Z, Pepe Carrera, Plaza Mozart 3
		
		System.out.println("EJE 6");
		
		System.out.println();
		
		//7. 	Añadir nuevo producto: pide los datos por teclado e inserta un producto nuevo si no existe un producto con ese nombre.
		
		Producto p1 = new Producto();
		System.out.print("Introduce el nombre: ");
		p1.setNombre(sc.next());
		
		System.out.print(" Introduce el precio: ");
		p1.setPrecio(sc.nextDouble());
		
		System.out.print("Introduce el stock: ");
		p1.setStock(sc.nextInt());
		boolean existe=false;
		
		ProductoDAO pD = new ProductoDAO();
		for (Producto p : pD.obtenerTodos()) {
			if (p.getNombre().equals(p1.getNombre())) {
				existe=true;
			}
		}
		
		if (existe!=true) {
			pD.insertar(p1);
			System.out.println("Producto " + p1.getNombre() + " añadido!");
		}
		
		
		
		
		
		

	sc.close();
	}
}
