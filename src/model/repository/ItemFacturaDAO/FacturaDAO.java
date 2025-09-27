package model.repository.ItemFacturaDAO;

import model.ConexionSingleton;
import model.entities.Duenos.Dueno;
import model.entities.Items_Factura.Facturas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacturaDAO implements IFacturaDAO {
    private final Connection con;

    public FacturaDAO() {
        con = ConexionSingleton.getInstance().getConnection();
    }

    @Override
    public void insertar(Facturas factura) {
        String sql = "INSERT INTO factura (dueno_id, fecha_emision, total, metodo_pago) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, factura.getDueno_id().getId());
            pstmt.setTimestamp(2, Timestamp.valueOf(factura.getFecha_emision()));
            pstmt.setDouble(3, factura.getTotal());
            pstmt.setString(4, factura.getMetodo_pago());
            pstmt.executeUpdate();

            try (ResultSet keys = pstmt.getGeneratedKeys()) {
                if (keys.next()) {
                    factura.setId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar factura", e);
        }
    }

    @Override
    public Facturas obtenerPorId(Integer id) {
        Facturas factura = null;
        String sql = "SELECT * FROM factura WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    factura = new Facturas(
                            rs.getInt("id"),
                            new Dueno(rs.getInt("dueno_id"), null, null, null, null, null),
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
    public List<Facturas> obtenerTodas() {
        List<Facturas> facturas = new ArrayList<>();
        String sql = "SELECT * FROM factura";
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Facturas factura = new Facturas(
                        rs.getInt("id"),
                        new Dueno(rs.getInt("dueno_id"), null, null, null, null, null),
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
    public void actualizar(Facturas factura) {
        String sql = "UPDATE factura SET dueno_id = ?, fecha_emision = ?, total = ?, metodo_pago = ? WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, factura.getDueno_id().getId());
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
