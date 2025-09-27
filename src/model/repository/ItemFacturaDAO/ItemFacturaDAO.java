package model.repository.ItemFacturaDAO;

import model.ConexionSingleton;
import model.entities.Items_Factura.ItemFactura;
import model.entities.Items_Factura.Facturas;
import model.entities.Inventario.ProductoTipo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemFacturaDAO implements IItemFacturaDAO {
    private final Connection con;

    public ItemFacturaDAO() {
        con = ConexionSingleton.getInstance().getConnection();
    }

    @Override
    public void insertar(ItemFactura item) {
        String sql = "INSERT INTO items_factura (factura_id, producto_id, servicio_descripcion, cantidad, precio_unitario, subtotal) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, item.getFactura_id().getId());
            pstmt.setInt(2, item.getProducto_id() != null ? item.getProducto_id().getId() : null);
            pstmt.setString(3, item.getServicio_descripcion());
            pstmt.setInt(4, item.getCantidad());
            pstmt.setInt(5, item.getPrecio_unitario());
            pstmt.setInt(6, item.getSubtotal());
            pstmt.executeUpdate();

            try (ResultSet keys = pstmt.getGeneratedKeys()) {
                if (keys.next()) {
                    item.setId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar item de factura", e);
        }
    }

    @Override
    public ItemFactura obtenerPorId(Integer id) {
        ItemFactura item = null;
        String sql = "SELECT * FROM items_factura WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    item = new ItemFactura(
                            rs.getInt("id"),
                            new Facturas(rs.getInt("factura_id"), null, null, 0.0, null),
                            new ProductoTipo(rs.getInt("producto_id"), null),
                            rs.getString("servicio_descripcion"),
                            rs.getInt("cantidad"),
                            rs.getInt("precio_unitario"),
                            rs.getInt("subtotal")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar item de factura con ID " + id, e);
        }
        return item;
    }

    @Override
    public List<ItemFactura> obtenerPorFacturaId(Integer facturaId) {
        List<ItemFactura> items = new ArrayList<>();
        String sql = "SELECT * FROM items_factura WHERE factura_id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, facturaId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    ItemFactura item = new ItemFactura(
                            rs.getInt("id"),
                            new Facturas(rs.getInt("factura_id"), null, null, 0.0, null),
                            new ProductoTipo(rs.getInt("producto_id"), null),
                            rs.getString("servicio_descripcion"),
                            rs.getInt("cantidad"),
                            rs.getInt("precio_unitario"),
                            rs.getInt("subtotal")
                    );
                    items.add(item);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar items de factura con factura ID " + facturaId, e);
        }
        return items;
    }

    @Override
    public List<ItemFactura> obtenerTodos() {
        List<ItemFactura> items = new ArrayList<>();
        String sql = "SELECT * FROM items_factura";
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                ItemFactura item = new ItemFactura(
                        rs.getInt("id"),
                        new Facturas(rs.getInt("factura_id"), null, null, 0.0, null),
                        new ProductoTipo(rs.getInt("producto_id"), null),
                        rs.getString("servicio_descripcion"),
                        rs.getInt("cantidad"),
                        rs.getInt("precio_unitario"),
                        rs.getInt("subtotal")
                );
                items.add(item);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar todos los items de factura", e);
        }
        return items;
    }

    @Override
    public void actualizar(ItemFactura item) {
        String sql = "UPDATE items_factura SET factura_id = ?, producto_id = ?, servicio_descripcion = ?, cantidad = ?, precio_unitario = ?, subtotal = ? WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, item.getFactura_id().getId());
            pstmt.setInt(2, item.getProducto_id() != null ? item.getProducto_id().getId() : null);
            pstmt.setString(3, item.getServicio_descripcion());
            pstmt.setInt(4, item.getCantidad());
            pstmt.setInt(5, item.getPrecio_unitario());
            pstmt.setInt(6, item.getSubtotal());
            pstmt.setInt(7, item.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar item de factura con ID " + item.getId(), e);
        }
    }

    @Override
    public void eliminar(Integer id) {
        String sql = "DELETE FROM items_factura WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar item de factura con ID " + id, e);
        }
    }
}
