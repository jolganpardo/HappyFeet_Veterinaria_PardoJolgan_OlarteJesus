package model.repository.InventarioDAO;

import model.ConexionSingleton;
import model.entities.Inventario.ProductoTipo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoTipoDAO implements IProductoTipoDAO {
    private final Connection con;

    public ProductoTipoDAO() {
        con = ConexionSingleton.getInstance().getConnection();
    }

    @Override
    public void insertar(ProductoTipo tipo) {
        String sql = "INSERT INTO producto_tipo (nombre) VALUES (?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, tipo.getNombre());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar tipo de producto", e);
        }
    }

    @Override
    public ProductoTipo obtenerPorId(Integer id) {
        ProductoTipo tipo = null;
        String sql = "SELECT * FROM producto_tipo WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    tipo = new ProductoTipo(rs.getInt("id"), rs.getString("nombre"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar tipo de producto con ID " + id, e);
        }
        return tipo;
    }

    @Override
    public ProductoTipo obtenerPorNombre(String nombre) {
        ProductoTipo tipo = null;
        String sql = "SELECT * FROM producto_tipo WHERE nombre = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    tipo = new ProductoTipo(rs.getInt("id"), rs.getString("nombre"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar tipo de producto con nombre " + nombre, e);
        }
        return tipo;
    }

    @Override
    public List<ProductoTipo> obtenerTodos() {
        List<ProductoTipo> tipos = new ArrayList<>();
        String sql = "SELECT * FROM producto_tipo";
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                tipos.add(new ProductoTipo(rs.getInt("id"), rs.getString("nombre")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar todos los tipos de producto", e);
        }
        return tipos;
    }

    @Override
    public void actualizar(ProductoTipo tipo) {
        String sql = "UPDATE producto_tipo SET nombre = ? WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, tipo.getNombre());
            pstmt.setInt(2, tipo.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar tipo de producto con ID " + tipo.getId(), e);
        }
    }

    @Override
    public void eliminar(Integer id) {
        String sql = "DELETE FROM producto_tipo WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar tipo de producto con ID " + id, e);
        }
    }
}
