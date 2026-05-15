package app;

import dao.ClienteDAO;
import modelo.Cliente;

public class MainGeneral {

	public static void main(String[] args) {
		//1.	Muestra todos los clientes, selecciona uno escribiendo el id y muestra sus datos y sus facturas.
		//2.	Muestra todos los empleados y seleccionando un id muestra las facturas que ha emitido.
		

		
		ClienteDAO cDAO= new ClienteDAO();
		
		for (Cliente c : cDAO.obtenerTodos()) {
			System.out.println(c);
		}
		

		

	}

}
