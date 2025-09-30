package model.repository.CitasDAO;

import model.ConexionSingleton;
import model.entities.Citas.Cita;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitaDAO implements ICitaDAO {
    private final Connection con;

    public CitaDAO() {
        con = ConexionSingleton.getInstance().getConnection();
    }

    @Override
    public int agregarCita(Cita cita) {
        String sql = "INSERT INTO cita (mascota_id, fecha_hora, motivo, estado_id, veterinario_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, cita.getMascota_id());
            pstmt.setTimestamp(2, Timestamp.valueOf(cita.getFecha_hora()));
            pstmt.setString(3, cita.getMotivo());

            if (cita.getEstado_id() != null) {
                pstmt.setInt(4, cita.getEstado_id());
            } else {
                pstmt.setNull(4, Types.INTEGER);
            }

            if (cita.getVeterinario_id() != null) {
                pstmt.setInt(5, cita.getVeterinario_id());
            } else {
                pstmt.setNull(5, Types.INTEGER);
            }

            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int idGenerado = rs.getInt(1);
                    return idGenerado;
                } else {
                    throw new SQLException("No se pudo obtener el ID generado de la cita.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al agregar cita: " + e.getMessage(), e);
        }
    }


    @Override
    public Cita obtenerPorId(Integer id) {
        Cita cita = null;
        String sql = "SELECT * FROM cita WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    cita = new Cita(
                            rs.getInt("id"),
                            rs.getInt("mascota_id"),
                            rs.getTimestamp("fecha_hora").toLocalDateTime(),
                            rs.getString("motivo"),
                            rs.getInt("estado_id"),
                            rs.getInt("veterinario_id")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar la cita con ID = " + id, e);
        }
        return cita;
    }

    @Override
    public List<Cita> obtenerTodos() {
        List<Cita> citas = new ArrayList<>();
        String sql = "SELECT * FROM cita";
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Cita cita = new Cita(
                        rs.getInt("id"),
                        rs.getInt("mascota_id"),
                        rs.getTimestamp("fecha_hora").toLocalDateTime(),
                        rs.getString("motivo"),
                        rs.getInt("estado_id"),
                        rs.getInt("veterinario_id")
                );
                citas.add(cita);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar todas las citas", e);
        }
        return citas;
    }

    @Override
    public void actualizarCita(Cita cita) {
        String sql = "UPDATE cita SET mascota_id = ?, fecha_hora = ?, motivo = ?, estado_id = ?, veterinario_id = ? WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, cita.getMascota_id());
            pstmt.setTimestamp(2, Timestamp.valueOf(cita.getFecha_hora()));
            pstmt.setString(3, cita.getMotivo());

            if (cita.getEstado_id() != null) {
                pstmt.setInt(4, cita.getEstado_id());
            } else {
                pstmt.setNull(4, Types.INTEGER);
            }

            if (cita.getVeterinario_id() != null) {
                pstmt.setInt(5, cita.getVeterinario_id());
            } else {
                pstmt.setNull(5, Types.INTEGER);
            }

            pstmt.setInt(6, cita.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar la cita con ID " + cita.getId(), e);
        }
    }

    @Override
    public void cancelarCita(Integer id) {
        String sql = "UPDATE cita SET estado_id = (SELECT id FROM cita_estado WHERE nombre = 'Cancelada' LIMIT 1) WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al cancelar la cita con ID = " + id, e);
        }
    }

    @Override
    public List<Cita> obtenerPorVeterinarioId(Integer veterinarioId) {
        List<Cita> citas = new ArrayList<>();
        String sql = """
        SELECT c.id, c.mascota_id, c.fecha_hora, c.motivo, c.estado_id, c.veterinario_id,
               v.nombre_completo AS nombre_veterinario
        FROM cita c
        JOIN veterinario v ON c.veterinario_id = v.id
        WHERE c.veterinario_id = ?
    """;

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, veterinarioId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Cita cita = new Cita(
                            rs.getInt("id"),
                            rs.getInt("mascota_id"),
                            rs.getTimestamp("fecha_hora").toLocalDateTime(),
                            rs.getString("motivo"),
                            rs.getInt("estado_id"),
                            rs.getInt("veterinario_id")
                    );

                    citas.add(cita);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar las citas del veterinario con ID " + veterinarioId, e);
        }

        return citas;
    }


}
