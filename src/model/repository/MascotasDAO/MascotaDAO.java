package model.repository.MascotasDAO;

import model.ConexionSingleton;
import model.entities.Duenos.Dueno;
import model.entities.Mascotas.Mascota;
import model.entities.Mascotas.Raza;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MascotaDAO implements IMascotasDAO {
    private Connection con;
    public MascotaDAO() {con = ConexionSingleton.getInstance().getConnection();}

    @Override
    public void agregarMascota(Mascota mascota) {
        String sql = "INSERT INTO mascota (dueno_id, nombre, raza_id, fecha_nacimiento, sexo, url_foto, estado)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, mascota.getDueno_id());
            pstmt.setString(2, mascota.getNombre());
            pstmt.setInt(3, mascota.getRaza_id());
            pstmt.setDate(4, java.sql.Date.valueOf(mascota.getFecha_nacimiento()));
            pstmt.setString(5, mascota.getSexo());
            pstmt.setString(6, mascota.getUrl_image());
            pstmt.setString(7, mascota.getEstado());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al agregar mascota.\n" + e.getMessage());
        }
    }

    @Override
    public Mascota obtenerPorId(Integer id) {
        Mascota mascota = null;

        String sql = "SELECT * FROM mascota WHERE id = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    mascota = new Mascota(
                            rs.getInt("id"),
                            rs.getInt("dueno_id"),
                            rs.getString("nombre"),
                            rs.getInt("raza_id"),
                            rs.getDate("fecha_nacimiento") != null ?
                                    rs.getDate("fecha_nacimiento").toLocalDate() : null,
                            rs.getString("sexo"),
                            rs.getString("url_foto"),
                            rs.getString("microchip"),
                            rs.getString("estado")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar la mascota con ID = " + id, e);
        }

        return mascota;
    }


    @Override
    public List<Mascota> obtenerTodos() {
        List<Mascota> lst = new ArrayList<>();
        String sql = "SELECT * FROM mascota";

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Mascota mascota = new Mascota(
                        rs.getInt("id"),
                        rs.getInt("dueno_id"),
                        rs.getString("nombre"),
                        rs.getInt("raza_id"),
                        rs.getDate("fecha_nacimiento") != null ?
                                rs.getDate("fecha_nacimiento").toLocalDate() : null,
                        rs.getString("sexo"),
                        rs.getString("url_foto"),
                        rs.getString("microchip"),
                        rs.getString("estado")
                );
                lst.add(mascota);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar todas las mascotas", e);
        }

        return lst;
    }


    @Override
    public void actualizarMascota(Mascota mascota) {
        String sql = "UPDATE mascota SET dueno_id = ?, nombre = ?, raza_id = ?, fecha_nacimiento = ?, sexo = ?, url_foto = ?, estado = ? WHERE id = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, mascota.getDueno_id());
            pstmt.setString(2, mascota.getNombre());
            pstmt.setInt(3, mascota.getRaza_id());
            pstmt.setDate(4, java.sql.Date.valueOf(mascota.getFecha_nacimiento()));
            pstmt.setString(5, mascota.getSexo());
            pstmt.setString(6, mascota.getUrl_image());
            pstmt.setString(7, mascota.getEstado());
            pstmt.setInt(8, mascota.getId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar mascota con ID " + mascota.getId(), e);
        }
    }

    @Override
    public void cambiarEstadoMascota(Integer id) {
        String sql = "UPDATE mascota SET estado = 'INACTIVA' where id = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al cambiar estado de mascota con ID = " + "\n" + e);
        }
    }

    @Override
    public List<Mascota> obtenerPorDuenoId(Integer duenoId) {
        List<Mascota> lst = new ArrayList<>();
        String sql = "SELECT * FROM mascota WHERE dueno_id = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, duenoId);
            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    Mascota mascota = new Mascota(rs.getInt("id"),
                            rs.getInt("dueno_id"),
                            rs.getString("nombre"),
                            rs.getInt("raza_id"),
                            rs.getDate("fecha_nacimiento").toLocalDate(),
                            rs.getString("sexo"),
                            rs.getString("url_foto"),
                            rs.getString("microchip"),
                            rs.getString("estado"));
                    lst.add(mascota);

                    }
            }
        } catch(SQLException e) {
            throw new RuntimeException("Error al consultar las mascotas del dueño " + duenoId + "\n" + e);
        }
        return lst;
    }

    @Override
    public List<Mascota> obtenerPorRazaId(Integer razaId) {
        List<Mascota> lst = new ArrayList<>();
        String sql = "SELECT * FROM mascota WHERE raza_id = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, razaId);
            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    Mascota mascota = new Mascota(rs.getInt("id"),
                            rs.getInt("dueno_id"),
                            rs.getString("nombre"),
                            rs.getInt("raza_id"),
                            rs.getDate("fecha_nacimiento").toLocalDate(),
                            rs.getString("sexo"),
                            rs.getString("url_foto"),
                            rs.getString("microchip"),
                            rs.getString("estado"));
                    lst.add(mascota);

                }
            }
        } catch(SQLException e) {
            throw new RuntimeException("Error al consultar las mascotas de la raza ID " + razaId + "\n" + e);
        }
        return lst;
    }
}
