package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Cliente;
import util.ConexionBD;

public class ClienteDAO implements GenericDAO<Cliente> {

	@Override
	public boolean insertar(Cliente cliente) {
		String sql = "INSERT INTO cliente (direccion) VALUES (?);";
				
				

		try (Connection conn = ConexionBD.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql,               Statement.RETURN_GENERATED_KEYS)) {

			
			pstmt.setString(1, cliente.getDireccion());
			

			int filas = pstmt.executeUpdate();

			if (filas > 0) {
				try (ResultSet rs = pstmt.getGeneratedKeys()) {
					if (rs.next()) {
						cliente.setId(rs.getInt(1)); // asigna el ID
						return true;
					}
				}
			}
			return false;

		} catch (SQLException e) {
			System.err.println("Error SQL al insertar '" + cliente.getId() + "': " + e.getMessage());
			return false;
		}
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
		String sql = "select id, direccion from cliente where id = ?";

		try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, id);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return mapearFila(rs);
				}
			}

		} catch (SQLException e) {
			System.err.println("Error SQL al buscar ID " + id + ": " + e.getMessage());
		}
		return null; // no encontrado
	}

	@Override
	public boolean actualizar(Cliente cliente) {
		String sql = "UPDATE cliente SET direccion=? WHERE id=?";

			try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

				pstmt.setString(1, cliente.getDireccion());
				pstmt.setInt(2, cliente.getId());
				
				int filas = pstmt.executeUpdate();
				return filas > 0; // false si el ID no existía en la BD

			} catch (SQLException e) {
				System.err.println("Error SQL al actualizar ID " + cliente.getId() + ": " + e.getMessage());
				return false;
			}// TODO Auto-generated method stub
	}

	@Override
	public boolean eliminar(int id) {
		String sql = "DELETE from cliente where id = ?";

		try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, id );
			
			
			int filas = pstmt.executeUpdate();
			return filas > 0; // false si el ID no existía en la BD

		} catch (SQLException e) {
			System.err.println("Eliminando" + e.getMessage());
			return false;
		}// TODO Auto-generated method stub
	}

}
