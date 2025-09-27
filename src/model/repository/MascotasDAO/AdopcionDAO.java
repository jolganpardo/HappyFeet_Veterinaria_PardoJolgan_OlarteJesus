package model.repository.MascotasDAO;

import model.ConexionSingleton;
import model.entities.Mascotas.Adopcion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdopcionDAO implements IAdopcionDAO {
    private Connection con;

    public AdopcionDAO() {
        con = ConexionSingleton.getInstance().getConnection();
    }

    @Override
    public void agregarAdopcion(Adopcion adopcion) {
        String sql = "INSERT INTO adopcion (mascota_id, estado, fecha_inicio, fecha_fin) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, adopcion.getMascotaId());
            pstmt.setString(2, adopcion.getEstado());
            pstmt.setDate(3, Date.valueOf(adopcion.getFechaInicio()));
            if (adopcion.getFechaFin() != null) {
                pstmt.setDate(4, Date.valueOf(adopcion.getFechaFin()));
            } else {
                pstmt.setNull(4, Types.DATE);
            }
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al agregar adopción.\n" + e.getMessage());
        }
    }

    @Override
    public Adopcion obtenerPorId(Integer id) {
        Adopcion adopcion = null;
        String sql = "SELECT * FROM adopcion WHERE id = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    adopcion = new Adopcion(
                            rs.getInt("id"),
                            rs.getInt("mascota_id"),
                            rs.getString("estado"),
                            rs.getDate("fecha_inicio").toLocalDate(),
                            rs.getDate("fecha_fin") != null ? rs.getDate("fecha_fin").toLocalDate() : null
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar la adopción con ID = " + id + "\n" + e.getMessage());
        }

        return adopcion;
    }

    @Override
    public List<Adopcion> obtenerTodos() {
        List<Adopcion> lst = new ArrayList<>();
        String sql = "SELECT * FROM adopcion";

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Adopcion adopcion = new Adopcion(
                        rs.getInt("id"),
                        rs.getInt("mascota_id"),
                        rs.getString("estado"),
                        rs.getDate("fecha_inicio").toLocalDate(),
                        rs.getDate("fecha_fin") != null ? rs.getDate("fecha_fin").toLocalDate() : null
                );
                lst.add(adopcion);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar todas las adopciones.\n" + e.getMessage());
        }

        return lst;
    }

    @Override
    public void actualizarAdopcion(Adopcion adopcion) {
        String sql = "UPDATE adopcion SET mascota_id = ?, estado = ?, fecha_inicio = ?, fecha_fin = ? WHERE id = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, adopcion.getMascotaId());
            pstmt.setString(2, adopcion.getEstado());
            pstmt.setDate(3, Date.valueOf(adopcion.getFechaInicio()));
            if (adopcion.getFechaFin() != null) {
                pstmt.setDate(4, Date.valueOf(adopcion.getFechaFin()));
            } else {
                pstmt.setNull(4, Types.DATE);
            }
            pstmt.setInt(5, adopcion.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar adopción con ID = " + adopcion.getId() + "\n" + e.getMessage());
        }
    }

    @Override
    public void eliminarAdopcion(Integer id) {
        String sql = "DELETE FROM adopcion WHERE id = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar adopción con ID = " + id + "\n" + e.getMessage());
        }
    }
}
