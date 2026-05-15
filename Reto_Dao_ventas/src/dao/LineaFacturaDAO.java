package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.LineaFactura;
import util.ConexionBD;

public class LineaFacturaDAO implements GenericDAO<LineaFactura> {

	@Override
	public boolean insertar(LineaFactura lineafactura) {
		String sql = "INSERT INTO lineafactura(id_factura, id_producto, cantidad, precio_unitario, importe) VALUES(?,?,?,?,?)";

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, lineafactura.getId_factura());
            ps.setInt(2, lineafactura.getId_producto());
            ps.setInt(3, lineafactura.getCantidad());
            ps.setDouble(4, lineafactura.getPrecio_unitario());
            ps.setDouble(5, lineafactura.getImporte());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error insertando lineafacturas: " + e.getMessage());
            return false;
        }
	}

	@Override
	public List<LineaFactura> obtenerTodos() {
		List<LineaFactura> lista = new ArrayList<>();
        String sql = "SELECT * FROM lineafactura";

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapear(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error obteniendo lineafacturas: " + e.getMessage());
        }

        return lista;
	}

	@Override
	public LineaFactura obtenerPorId(int id) {
		String sql = "SELECT * FROM lineafactura WHERE id=?";

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error buscando lineafactura por id: " + e.getMessage());
        }
        return null;
	}

	@Override
	public boolean actualizar(LineaFactura lineafactura) {
		String sql = "UPDATE lineafactura SET id_factura=?, id_producto=?, cantidad=?, precio_unitario=?, importe=? WHERE id=?";

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

        	 ps.setInt(1, lineafactura.getId_factura());
             ps.setInt(2, lineafactura.getId_producto());
             ps.setInt(3, lineafactura.getCantidad());
             ps.setDouble(4, lineafactura.getPrecio_unitario());
             ps.setDouble(5, lineafactura.getImporte());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error actualizando factura: " + e.getMessage());
            return false;
        }
	}

	@Override
	public boolean eliminar(int id) {
		String sql = "DELETE FROM lineafactura WHERE id=?";

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error eliminando lineafactura: " + e.getMessage());
            return false;
        }
	}
	
	private LineaFactura mapear(ResultSet rs) throws SQLException {
		LineaFactura lf = new LineaFactura();
		lf.setId_factura(rs.getInt("id_factura"));
		lf.setId_producto(rs.getInt("id_producto"));
		lf.setCantidad(rs.getInt("cantidad"));
		lf.setPrecio_unitario(rs.getDouble("precio_unitario"));
		lf.setImporte(rs.getDouble("importe"));
		
		return lf;
}
	
}
