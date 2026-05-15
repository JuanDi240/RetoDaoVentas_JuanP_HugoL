package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import modelo.Factura;
import util.ConexionBD;

public class FacturaDAO implements GenericDAO<Factura>{

	@Override
	public boolean insertar(Factura factura) {
		String sql = "INSERT INTO factura(fecha, id_cliente, id_empleado, subtotal, iva, total) VALUES(?,?,?,?,?,?)";

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setObject(1, factura.getFecha());
            ps.setInt(2, factura.getId_cliente());
            ps.setInt(3, factura.getId_empleado());
            ps.setDouble(4, factura.getSubtotal());
            ps.setDouble(5, factura.getIva());
            ps.setDouble(6, factura.getTotal());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error insertando factura: " + e.getMessage());
            return false;
        }

	}

	@Override
	public List<Factura> obtenerTodos() {
		List<Factura> lista = new ArrayList<>();
        String sql = "SELECT * FROM factura";

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapear(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error obteniendo facturas: " + e.getMessage());
        }

        return lista;

	}

	@Override
	public Factura obtenerPorId(int id) {
		String sql = "SELECT * FROM factura WHERE id=?";

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error buscando factura por id: " + e.getMessage());
        }
        return null;

	}

	@Override
	public boolean actualizar(Factura objeto) {
		String sql = "UPDATE factura SET fecha=?, id_cliente=?, id_empleado=?, subtotal=?, iva=?, total=? WHERE id=?";

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setObject(1, objeto.getFecha());
            ps.setInt(2, objeto.getId_cliente());
            ps.setInt(3, objeto.getId_empleado());
            ps.setDouble(4, objeto.getSubtotal());
            ps.setDouble(5, objeto.getIva());
            ps.setDouble(6, objeto.getTotal());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error actualizando factura: " + e.getMessage());
            return false;
        }

	}

	@Override
	public boolean eliminar(int id) {
        String sql = "DELETE FROM factura WHERE id=?";

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error eliminando factura: " + e.getMessage());
            return false;
        }

	}
	
	private Factura mapear(ResultSet rs) throws SQLException {
			Factura f = new Factura();
			f.setFecha(rs.getObject("fecha", LocalDate.class));
			f.setId_cliente(rs.getInt("id_cliente"));
			f.setId_empleado(rs.getInt("id_empleado"));
			f.setSubtotal(rs.getDouble("id_subtotal"));
			f.setIva(rs.getDouble("iva"));
			f.setTotal(rs.getDouble("total"));
			
			return f;
    }
	
}
