package model.repository.VeterinariosDAO;

import model.ConexionSingleton;
import model.entities.Veterinarios.Veterinario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeterinarioDAO implements IVeterinariosDAO {
    private final Connection con;

    public VeterinarioDAO() {
        con = ConexionSingleton.getInstance().getConnection();
    }

    @Override
    public void insertar(Veterinario veterinario) {
        String sql = "INSERT INTO veterinario (nombre_completo, especialidad, telefono, email) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, veterinario.getNombre_completo());
            pstmt.setString(2, veterinario.getEspecialidad());
            pstmt.setString(3, veterinario.getTelefono());
            pstmt.setString(4, veterinario.getEmail());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar veterinario", e);
        }
    }

    @Override
    public void actualizar(Veterinario veterinario) {
        String sql = "UPDATE veterinario SET nombre_completo = ?, especialidad = ?, telefono = ?, email = ? WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, veterinario.getNombre_completo());
            pstmt.setString(2, veterinario.getEspecialidad());
            pstmt.setString(3, veterinario.getTelefono());
            pstmt.setString(4, veterinario.getEmail());
            pstmt.setInt(5, veterinario.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar veterinario con ID " + veterinario.getId(), e);
        }
    }

    @Override
    public void eliminar(Integer id) {
        String sql = "DELETE FROM veterinario WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar veterinario con ID " + id, e);
        }
    }

    @Override
    public Veterinario buscarPorId(Integer id) {
        Veterinario veterinario = null;
        String sql = "SELECT * FROM veterinario WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    veterinario = new Veterinario(
                            rs.getInt("id"),
                            rs.getString("nombre_completo"),
                            rs.getString("especialidad"),
                            rs.getString("telefono"),
                            rs.getString("email")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar veterinario con ID " + id, e);
        }
        return veterinario;
    }

    @Override
    public List<Veterinario> listarTodos() {
        List<Veterinario> veterinarios = new ArrayList<>();
        String sql = "SELECT * FROM veterinario";
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Veterinario veterinario = new Veterinario(
                        rs.getInt("id"),
                        rs.getString("nombre_completo"),
                        rs.getString("especialidad"),
                        rs.getString("telefono"),
                        rs.getString("email")
                );
                veterinarios.add(veterinario);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar veterinarios", e);
        }
        return veterinarios;
    }
}
