package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Producto;
import util.ConexionBD;

public class ProductoDAO implements GenericDAO<Producto>{

	

	@Override
	public boolean insertar(Producto objeto) {
		String sql = "INSERT INTO producto(nombre, precio, stock) VALUES(?,?,?)";
		try (Connection con = ConexionBD.getConnection();
		PreparedStatement ps = con.prepareStatement(sql)) {
		ps.setString(1, objeto.getNombre());
		ps.setDouble(2, objeto.getPrecio());
		ps.setInt(3, objeto.getStock());
		return ps.executeUpdate() > 0;
		} catch (SQLException e) {
		System.out.println("Error insertando producto: " + e.getMessage());
		return false;
		}
	}

	@Override
	public List<Producto> obtenerTodos() {
		List<Producto> productos = new ArrayList<>();
	    String sql = "select id, nombre, precio, stock from producto;";

			try (Connection conn = ConexionBD.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql);
					ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					productos.add(mapearFila(rs));
					
				}

			} catch (SQLException e) {
				System.err.println("Error SQL al obtener todos los productos: " + e.getMessage());
			}
			return productos;
	}
	private Producto mapearFila(ResultSet rs) throws SQLException {
		Producto c = new Producto();
		c.setId(rs.getInt("id"));
		c.setNombre(rs.getString("nombre"));
		c.setPrecio(rs.getDouble("precio"));
		c.setStock(rs.getInt("stock"));
		
		return c;

	}

	@Override
	public Producto obtenerPorId(int id) {
		String sql = "SELECT * FROM producto WHERE id=?";
		try (Connection con = ConexionBD.getConnection();
		PreparedStatement ps = con.prepareStatement(sql)) {
		ps.setInt(1, id);
		try (ResultSet rs = ps.executeQuery()) {
		if (rs.next()) {
		return mapearFila(rs);
		}
		}
		} catch (SQLException e) {
		System.out.println("Error buscando producto por id: " + e.getMessage());
		}
		return null;
	}

	@Override
	public boolean actualizar(Producto objeto) {
		String sql = "UPDATE producto SET nombre=?, precio=?, stock=? WHERE id=?";

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, objeto.getNombre());
            ps.setDouble(2, objeto.getPrecio());
            ps.setInt(3, objeto.getStock());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error actualizando producto: " + e.getMessage());
            return false;
        }

	}

	@Override
	public boolean eliminar(int id) {
        String sql = "DELETE FROM producto WHERE id=?";

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error eliminando prodcuto: " + e.getMessage());
            return false;
        }

	}
	
}
