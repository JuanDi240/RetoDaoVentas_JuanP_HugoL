package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Empleado;
import util.ConexionBD;

public class EmpleadoDAO implements GenericDAO<Empleado> {

	@Override
	public boolean insertar(Empleado empleado) {
		String sql = "INSERT INTO cliente (puesto, salario) VALUES (?, ?);";
		
		

		try (Connection conn = ConexionBD.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql,               Statement.RETURN_GENERATED_KEYS)) {

			
			pstmt.setString(1, empleado.getPuesto());
			pstmt.setDouble(2, empleado.getSalario());

			int filas = pstmt.executeUpdate();

			if (filas > 0) {
				try (ResultSet rs = pstmt.getGeneratedKeys()) {
					if (rs.next()) {
						empleado.setId(rs.getInt(1)); // asigna el ID
						return true;
					}
				}
			}
			return false;

		} catch (SQLException e) {
			System.err.println("Error SQL al insertar '" + empleado.getId() + "': " + e.getMessage());
			return false;
		}
	}

	@Override
	public List<Empleado> obtenerTodos() {
		List<Empleado> empleados = new ArrayList<>();
	    String sql = "select id, direccion from cliente;";

			try (Connection conn = ConexionBD.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql);
					ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					empleados.add(mapearFila(rs));
					
				}

			} catch (SQLException e) {
				System.err.println("Error SQL al obtener todos los empleados: " + e.getMessage());
			}
			return empleados;
	}
	private Empleado mapearFila(ResultSet rs) throws SQLException {
		Empleado m = new Empleado();
		m.setId(rs.getInt("id"));
		m.setPuesto(rs.getString("puesto"));
		m.setSalario(rs.getDouble("salario"));
		return m;

	}

	@Override
	public Empleado obtenerPorId(int id) {
		String sql = "select id, puesto, salario from empleado where id = ?";

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
	public boolean actualizar(Empleado empleado) {
		String sql = "UPDATE cliente SET puesto=?, salario= ? WHERE id=?";

		try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, empleado.getPuesto());
			pstmt.setDouble(2, empleado.getSalario());
			pstmt.setInt(3, empleado.getId());
			int filas = pstmt.executeUpdate();
			return filas > 0; // false si el ID no existía en la BD

		} catch (SQLException e) {
			System.err.println("Error SQL al actualizar ID " + empleado.getId() + ": " + e.getMessage());
			return false;
		}// TODO Auto-generated method stub
	}

	@Override
	public boolean eliminar(int id) {
		String sql = "DELETE from empleado where id = ?";

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
