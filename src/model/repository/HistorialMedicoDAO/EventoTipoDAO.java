package model.repository.HistorialMedicoDAO;

import model.ConexionSingleton;
import model.entities.Historial_Medico.EventoTipos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventoTipoDAO implements IEventoTipoDAO {
    private final Connection con;

    public EventoTipoDAO() {
        con = ConexionSingleton.getInstance().getConnection();
    }

    @Override
    public void agregarEventoTipo(EventoTipos evento) {
        String sql = "INSERT INTO evento_tipo (nombre) VALUES (?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, evento.getNombre());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al agregar tipo de evento", e);
        }
    }

    @Override
    public EventoTipos obtenerPorId(Integer id) {
        EventoTipos evento = null;
        String sql = "SELECT * FROM evento_tipo WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    evento = new EventoTipos(
                            rs.getInt("id"),
                            rs.getString("nombre")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar tipo de evento con ID " + id, e);
        }
        return evento;
    }

    @Override
    public EventoTipos obtenerPorNombre(String nombre) {
        EventoTipos evento = null;
        String sql = "SELECT * FROM evento_tipo WHERE nombre = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    evento = new EventoTipos(
                            rs.getInt("id"),
                            rs.getString("nombre")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar tipo de evento con nombre " + nombre, e);
        }
        return evento;
    }

    @Override
    public List<EventoTipos> obtenerTodos() {
        List<EventoTipos> eventos = new ArrayList<>();
        String sql = "SELECT * FROM evento_tipo";
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                eventos.add(new EventoTipos(
                        rs.getInt("id"),
                        rs.getString("nombre")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar todos los tipos de evento", e);
        }
        return eventos;
    }

    @Override
    public void actualizarEventoTipo(EventoTipos evento) {
        String sql = "UPDATE evento_tipo SET nombre = ? WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, evento.getNombre());
            pstmt.setInt(2, evento.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar tipo de evento con ID " + evento.getId(), e);
        }
    }

    @Override
    public void eliminarEventoTipo(Integer id) {
        String sql = "DELETE FROM evento_tipo WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar tipo de evento con ID " + id, e);
        }
    }
}
