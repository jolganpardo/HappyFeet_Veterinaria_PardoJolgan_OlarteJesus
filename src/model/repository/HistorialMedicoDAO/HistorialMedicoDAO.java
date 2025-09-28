package model.repository.HistorialMedicoDAO;

import model.ConexionSingleton;
import model.entities.Historial_Medico.HistorialMedico;
import model.entities.Historial_Medico.EventoTipos;
import model.entities.Mascotas.Mascota;
import model.entities.Veterinarios.Veterinario;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HistorialMedicoDAO implements IHistorialMedicoDAO {
    private final Connection con;

    public HistorialMedicoDAO() {
        con = ConexionSingleton.getInstance().getConnection();
    }

    @Override
    public void agregarHistorial(HistorialMedico historial) {
        String sql = "INSERT INTO historial_medico (mascota_id, veterinario_id, fecha_evento, evento_tipo_id, descripcion, diagnostico, tratamiento_recomendado) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, historial.getMascota_id());
            pstmt.setInt(2, historial.getVeterinario_id());
            pstmt.setDate(3, Date.valueOf(historial.getFecha_evento()));
            pstmt.setInt(4, historial.getEvento_tipo_id());
            pstmt.setString(5, historial.getDescripcion());
            pstmt.setString(6, historial.getDiagnostico());
            pstmt.setString(7, historial.getTratamiento_recomendado());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al agregar historial médico", e);
        }
    }

    @Override
    public HistorialMedico obtenerPorId(Integer id) {
        HistorialMedico historial = null;
        String sql = "SELECT * FROM historial_medico WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    historial = new HistorialMedico(
                            rs.getInt("id"),
                            rs.getInt("mascota_id"),
                            rs.getInt("veterinario_id"),
                            rs.getDate("fecha_evento").toLocalDate(),
                            rs.getInt("evento_tipo_id"),
                            rs.getString("descripcion"),
                            rs.getString("diagnostico"),
                            rs.getString("tratamiento_recomendado")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar historial médico con ID " + id, e);
        }
        return historial;
    }


    @Override
    public List<HistorialMedico> obtenerTodos() {
        List<HistorialMedico> historiales = new ArrayList<>();
        String sql = "SELECT * FROM historial_medico";
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                HistorialMedico historial = new HistorialMedico(
                        rs.getInt("id"),
                        rs.getInt("mascota_id"),
                        rs.getInt("veterinario_id"),
                        rs.getDate("fecha_evento").toLocalDate(),
                        rs.getInt("evento_tipo_id"),
                        rs.getString("descripcion"),
                        rs.getString("diagnostico"),
                        rs.getString("tratamiento_recomendado")
                );
                historiales.add(historial);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar todos los historiales médicos", e);
        }
        return historiales;
    }

    @Override
    public List<HistorialMedico> obtenerPorMascotaId(Integer mascotaId) {
        List<HistorialMedico> historiales = new ArrayList<>();
        String sql = "SELECT * FROM historial_medico WHERE mascota_id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, mascotaId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    HistorialMedico historial = new HistorialMedico(
                            rs.getInt("id"),
                            rs.getInt("mascota_id"),
                            rs.getInt("veterinario_id"),
                            rs.getDate("fecha_evento").toLocalDate(),
                            rs.getInt("evento_tipo_id"),
                            rs.getString("descripcion"),
                            rs.getString("diagnostico"),
                            rs.getString("tratamiento_recomendado")
                    );
                    historiales.add(historial);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar historiales médicos de mascota " + mascotaId, e);
        }
        return historiales;
    }

    @Override
    public void actualizarHistorial(HistorialMedico historial) {
        String sql = "UPDATE historial_medico SET mascota_id = ?, veterinario_id = ?, fecha_evento = ?, evento_tipo_id = ?, descripcion = ?, diagnostico = ?, tratamiento_recomendado = ? WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, historial.getMascota_id());
            pstmt.setInt(2, historial.getVeterinario_id());
            pstmt.setDate(3, Date.valueOf(historial.getFecha_evento()));
            pstmt.setInt(4, historial.getEvento_tipo_id());
            pstmt.setString(5, historial.getDescripcion());
            pstmt.setString(6, historial.getDiagnostico());
            pstmt.setString(7, historial.getTratamiento_recomendado());
            pstmt.setInt(8, historial.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar historial médico con ID " + historial.getId(), e);
        }
    }

    @Override
    public void eliminarHistorial(Integer id) {
        String sql = "DELETE FROM historial_medico WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar historial médico con ID " + id, e);
        }
    }
}
