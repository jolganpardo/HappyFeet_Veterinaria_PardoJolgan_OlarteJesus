package model.repository.CitasDAO;

import model.ConexionSingleton;
import model.entities.Citas.CitaEstado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitaEstadoDAO implements ICitaEstadoDAO {
    private final Connection con;

    public CitaEstadoDAO() {
        con = ConexionSingleton.getInstance().getConnection();
    }

    @Override
    public void agregarEstado(CitaEstado estado) {
        String sql = "INSERT INTO cita_estado (nombre) VALUES (?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, estado.getNombre());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al agregar estado de cita: " + e.getMessage(), e);
        }
    }

    @Override
    public CitaEstado obtenerPorId(Integer id) {
        CitaEstado estado = null;
        String sql = "SELECT * FROM cita_estado WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    estado = new CitaEstado(
                            rs.getInt("id"),
                            rs.getString("nombre")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar estado de cita con ID " + id, e);
        }
        return estado;
    }

    @Override
    public CitaEstado obtenerPorNombre(String nombre) {
        CitaEstado estado = null;
        String sql = "SELECT * FROM cita_estado WHERE nombre = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    estado = new CitaEstado(
                            rs.getInt("id"),
                            rs.getString("nombre")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar estado de cita con nombre " + nombre, e);
        }
        return estado;
    }

    @Override
    public List<CitaEstado> obtenerTodos() {
        List<CitaEstado> estados = new ArrayList<>();
        String sql = "SELECT * FROM cita_estado";
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                estados.add(new CitaEstado(
                        rs.getInt("id"),
                        rs.getString("nombre")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar todos los estados de cita", e);
        }
        return estados;
    }

    @Override
    public void actualizarEstado(CitaEstado estado) {
        String sql = "UPDATE cita_estado SET nombre = ? WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, estado.getNombre());
            pstmt.setInt(2, estado.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar estado de cita con ID " + estado.getId(), e);
        }
    }

    @Override
    public void eliminarEstado(Integer id) {
        String sql = "DELETE FROM cita_estado WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar estado de cita con ID " + id, e);
        }
    }
}
