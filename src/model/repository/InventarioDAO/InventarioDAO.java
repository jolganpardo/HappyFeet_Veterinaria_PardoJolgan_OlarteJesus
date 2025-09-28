package model.repository.InventarioDAO;

import model.ConexionSingleton;
import model.entities.Inventario.Inventario;
import model.entities.Inventario.ProductoTipo;
import model.entities.Inventario.Proveedor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventarioDAO implements IInventarioDAO {
    private final Connection con;

    public InventarioDAO() {
        con = ConexionSingleton.getInstance().getConnection();
    }

    @Override
    public void insertar(Inventario inventario) {
        String sql = "INSERT INTO inventario (nombre_producto, producto_tipo_id, descripcion," +
                " fabricante, lote, cantidad_stock, stock_minimo, fecha_vencimiento, precio_venta," +
                " proveedor_id, fecha_ultima_compra) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, inventario.getNombre_producto());
            pstmt.setInt(2, inventario.getProducto_tipo_id());
            pstmt.setString(3, inventario.getDescripcion());
            pstmt.setString(4, inventario.getFabricante());
            pstmt.setString(5, inventario.getLote());
            pstmt.setInt(6, inventario.getCantidad_stock());
            pstmt.setInt(7, inventario.getStock_minimo());
            pstmt.setDate(8, inventario.getFecha_vencimiento() != null ? Date.valueOf(inventario.getFecha_vencimiento()) : null);
            pstmt.setDouble(9, inventario.getPrecio_venta());
            pstmt.setInt(10, inventario.getProveedor_id());
            pstmt.setDate(11, inventario.getFecha_ultima_compra() != null ? Date.valueOf(inventario.getFecha_ultima_compra()) : null);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar producto en inventario", e);
        }
    }

    @Override
    public Inventario obtenerPorId(Integer id) {
        Inventario inv = null;
        String sql = "SELECT * FROM inventario WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    inv = new Inventario(
                            rs.getInt("id"),
                            rs.getString("nombre_producto"),
                            rs.getInt("producto_tipo_id"),
                            rs.getString("descripcion"),
                            rs.getString("fabricante"),
                            rs.getString("lote"),
                            rs.getInt("cantidad_stock"),
                            rs.getInt("stock_minimo"),
                            rs.getDate("fecha_vencimiento") != null ? rs.getDate("fecha_vencimiento").toLocalDate() : null,
                            rs.getDouble("precio_venta"),
                            rs.getInt("proveedor_id"),
                            rs.getDate("fecha_ultima_compra") != null ? rs.getDate("fecha_ultima_compra").toLocalDate() : null);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar inventario con ID " + id, e);
        }
        return inv;
    }

    @Override
    public List<Inventario> obtenerTodos() {
        List<Inventario> inventarios = new ArrayList<>();
        String sql = "SELECT * FROM inventario";

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Inventario inventario = new Inventario(
                        rs.getInt("id"),
                        rs.getString("nombre_producto"),
                        rs.getInt("producto_tipo_id"),
                        rs.getString("descripcion"),
                        rs.getString("fabricante"),
                        rs.getString("lote"),
                        rs.getInt("cantidad_stock"),
                        rs.getInt("stock_minimo"),
                        rs.getDate("fecha_vencimiento") != null ? rs.getDate("fecha_vencimiento").toLocalDate() : null,
                        rs.getDouble("precio_venta"),
                        rs.getInt("proveedor_id"),
                        rs.getDate("fecha_ultima_compra") != null ? rs.getDate("fecha_ultima_compra").toLocalDate() : null);
                inventarios.add(inventario);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar todos los productos del inventario", e);
        }

        return inventarios;
    }


    @Override
    public void actualizar(Inventario inventario) {
        String sql = "UPDATE inventario SET nombre_producto = ?, producto_tipo_id = ?, descripcion = ?," +
                " fabricante = ?, lote = ?, cantidad_stock = ?, stock_minimo = ?, fecha_vencimiento = ?," +
                " precio_venta = ?, proveedor_id = ?, fecha_ultima_compra = ? WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, inventario.getNombre_producto());
            pstmt.setInt(2, inventario.getProducto_tipo_id());
            pstmt.setString(3, inventario.getDescripcion());
            pstmt.setString(4, inventario.getFabricante());
            pstmt.setString(5, inventario.getLote());
            pstmt.setInt(6, inventario.getCantidad_stock());
            pstmt.setInt(7, inventario.getStock_minimo());
            pstmt.setDate(8, inventario.getFecha_vencimiento() != null ? Date.valueOf(inventario.getFecha_vencimiento()) : null);
            pstmt.setDouble(9, inventario.getPrecio_venta());
            pstmt.setInt(10, inventario.getProveedor_id());
            pstmt.setDate(11, inventario.getFecha_ultima_compra() != null ? Date.valueOf(inventario.getFecha_ultima_compra()) : null);
            pstmt.setInt(12, inventario.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar inventario con ID " + inventario.getId(), e);
        }
    }

    @Override
    public void eliminar(Integer id) {
        String sql = "DELETE FROM inventario WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar inventario con ID " + id, e);
        }
    }
}
