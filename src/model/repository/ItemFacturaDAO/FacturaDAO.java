package model.repository.ItemFacturaDAO;

import model.ConexionSingleton;
import model.entities.Items_Factura.Factura;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacturaDAO implements IFacturaDAO {
    private final Connection con;

    public FacturaDAO() {
        con = ConexionSingleton.getInstance().getConnection();
    }

    @Override
    public Factura insertar(Factura f) {
        String sql = "INSERT INTO factura (dueno_id, fecha_emision, total, metodo_pago) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, f.getDueno_id());
            ps.setTimestamp(2, Timestamp.valueOf(f.getFecha_emision()));
            ps.setDouble(3, f.getTotal());
            ps.setString(4, f.getMetodo_pago());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                f.setId(rs.getInt(1)); // asigna el ID generado al objeto
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar factura", e);
        }
        return f;
    }

    @Override
    public Factura obtenerPorId(Integer id) {
        Factura factura = null;
        String sql = "SELECT * FROM factura WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    factura = new Factura(
                            rs.getInt("id"),
                            rs.getInt("dueno_id"),
                            rs.getTimestamp("fecha_emision").toLocalDateTime(),
                            rs.getDouble("total"),
                            rs.getString("metodo_pago")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar factura con ID " + id, e);
        }
        return factura;
    }


    @Override
    public List<Factura> obtenerTodas() {
        List<Factura> facturas = new ArrayList<>();
        String sql = "SELECT * FROM factura";
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Factura factura = new Factura(
                        rs.getInt("id"),
                        rs.getInt("dueno_id"),
                        rs.getTimestamp("fecha_emision").toLocalDateTime(),
                        rs.getDouble("total"),
                        rs.getString("metodo_pago")
                );
                facturas.add(factura);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar todas las facturas", e);
        }
        return facturas;
    }


    @Override
    public void actualizar(Factura factura) {
        String sql = "UPDATE factura SET dueno_id = ?, fecha_emision = ?, total = ?, metodo_pago = ? WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, factura.getDueno_id());
            pstmt.setTimestamp(2, Timestamp.valueOf(factura.getFecha_emision()));
            pstmt.setDouble(3, factura.getTotal());
            pstmt.setString(4, factura.getMetodo_pago());
            pstmt.setInt(5, factura.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar factura con ID " + factura.getId(), e);
        }
    }

    @Override
    public void eliminar(Integer id) {
        String sql = "DELETE FROM factura WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar factura con ID " + id, e);
        }
    }
}
