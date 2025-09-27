package model.repository.InventarioDAO;

import model.ConexionSingleton;
import model.entities.Inventario.Proveedor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAO implements IProveedorDAO {
    private final Connection con;

    public ProveedorDAO() {
        con = ConexionSingleton.getInstance().getConnection();
    }

    @Override
    public void insertar(Proveedor proveedor) {
        String sql = "INSERT INTO proveedor (nombre_empresa, contacto, telefono, email, direccion) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, proveedor.getNombre());
            pstmt.setString(2, proveedor.getContacto());
            pstmt.setString(3, proveedor.getTelefono());
            pstmt.setString(4, proveedor.getEmail());
            pstmt.setString(5, proveedor.getDireccion());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar proveedor", e);
        }
    }

    @Override
    public Proveedor obtenerPorId(Integer id) {
        Proveedor proveedor = null;
        String sql = "SELECT * FROM proveedor WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    proveedor = new Proveedor(
                            rs.getInt("id"),
                            rs.getString("nombre_empresa"),
                            rs.getString("contacto"),
                            rs.getString("telefono"),
                            rs.getString("email"),
                            rs.getString("direccion")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar proveedor con ID " + id, e);
        }
        return proveedor;
    }

    @Override
    public List<Proveedor> obtenerTodos() {
        List<Proveedor> proveedores = new ArrayList<>();
        String sql = "SELECT * FROM proveedor";
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                proveedores.add(new Proveedor(
                        rs.getInt("id"),
                        rs.getString("nombre_empresa"),
                        rs.getString("contacto"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getString("direccion")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar todos los proveedores", e);
        }
        return proveedores;
    }

    @Override
    public void actualizar(Proveedor proveedor) {
        String sql = "UPDATE proveedor SET nombre_empresa = ?, contacto = ?, telefono = ?, email = ?, direccion = ? WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, proveedor.getNombre());
            pstmt.setString(2, proveedor.getContacto());
            pstmt.setString(3, proveedor.getTelefono());
            pstmt.setString(4, proveedor.getEmail());
            pstmt.setString(5, proveedor.getDireccion());
            pstmt.setInt(6, proveedor.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar proveedor con ID " + proveedor.getId(), e);
        }
    }

    @Override
    public void eliminar(Integer id) {
        String sql = "DELETE FROM proveedor WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar proveedor con ID " + id, e);
        }
    }
}
