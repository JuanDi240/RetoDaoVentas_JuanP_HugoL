package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Cliente;
import util.ConexionBD;

public class ClienteDAO implements GenericDAO<Cliente> {

	@Override
	public boolean insertar(Cliente objeto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Cliente> obtenerTodos() {
		List<Cliente> alumnos = new ArrayList<>();
	    String sql = "select id, direccion from cliente;";

			try (Connection conn = ConexionBD.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql);
					ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					alumnos.add(mapearFila(rs));
					
				}

			} catch (SQLException e) {
				System.err.println("Error SQL al obtener todos los clientes: " + e.getMessage());
			}
			return alumnos;
	}
	private Cliente mapearFila(ResultSet rs) throws SQLException {
		Cliente c = new Cliente();
		c.setId(rs.getInt("id"));
		c.setDireccion(rs.getString("direccion"));
		
		return c;

	}

	@Override
	public Cliente obtenerPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean actualizar(Cliente objeto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
