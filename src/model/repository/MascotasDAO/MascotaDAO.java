package model.repository.MascotasDAO;

import model.ConexionSingleton;
import model.entities.Mascotas.Mascota;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MascotaDAO implements IMascotasDAO {
    private Connection con;
    public MascotaDAO() {con = ConexionSingleton.getInstance().getConnection();}

    @Override
    public int agregarMascota(Mascota mascota, String documentoDueno) {
        // Primero obtener el dueno_id
        String selectDuenoSql = "SELECT id FROM dueno WHERE documento_identidad = ?";
        int duenoId = 0;

        try (PreparedStatement selectPstmt = con.prepareStatement(selectDuenoSql)) {
            selectPstmt.setString(1, documentoDueno);
            try (ResultSet rs = selectPstmt.executeQuery()) {
                if (rs.next()) {
                    duenoId = rs.getInt("id");
                    mascota.setDuenos_id(duenoId); // Asignar inmediatamente
                } else {
                    throw new SQLException("No se encontró un dueño con documento: " + documentoDueno);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar dueño con documento " + documentoDueno, e);
        }

        // Ahora insertar la mascota con el dueno_id conocido
        String sql = """
    INSERT INTO mascota (
        dueno_id, nombre, especie_id, raza_id, fecha_nacimiento, sexo, url_foto, estado
    )
    VALUES (?, ?, ?, ?, ?, ?, ?, ?)
    """;

        try (PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, duenoId);
            pstmt.setString(2, mascota.getNombre());
            pstmt.setInt(3, mascota.getEspecie_id());
            pstmt.setInt(4, mascota.getRaza_id());
            pstmt.setDate(5, java.sql.Date.valueOf(mascota.getFecha_nacimiento()));
            pstmt.setString(6, mascota.getSexo());
            if (mascota.getUrl_image() != null) {
                pstmt.setString(7, mascota.getUrl_image());
            } else {
                pstmt.setNull(7, Types.VARCHAR);
            }
            pstmt.setString(8, mascota.getEstado());
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int idGenerado = rs.getInt(1);

                    String microchip = mascota.getMicrochip();
                    if (microchip == null || microchip.isBlank()) {
                        microchip = "M-" + idGenerado;
                        mascota.setMicrochip(microchip);
                    }

                    String updateSql = "UPDATE mascota SET microchip = ? WHERE id = ?";
                    try (PreparedStatement updatePstmt = con.prepareStatement(updateSql)) {
                        updatePstmt.setString(1, microchip);
                        updatePstmt.setInt(2, idGenerado);
                        updatePstmt.executeUpdate();
                    }

                    return idGenerado;
                } else {
                    throw new SQLException("No se pudo obtener el ID generado para la mascota");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al agregar mascota", e);
        }
    }



    @Override
    public void agregarMascota(Mascota mascota) {

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
                            rs.getInt("especie_id"),
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
                        rs.getInt("especie_id"),
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
    public List<Mascota> obtenerPorDuenoDocumento(Integer duenoDocumento) {
        List<Mascota> lst = new ArrayList<>();
        String sql = """
            SELECT M.* 
            FROM mascota M
            JOIN dueno D ON M.dueno_id = D.id
            WHERE D.documento_identidad = ?
            """;

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, duenoDocumento);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Mascota mascota = new Mascota(
                            rs.getInt("id"),
                            rs.getInt("dueno_id"),
                            rs.getString("nombre"),
                            rs.getInt("raza_id"),
                            rs.getInt("especie_id"),
                            rs.getDate("fecha_nacimiento").toLocalDate(),
                            rs.getString("sexo"),
                            rs.getString("url_foto"),
                            rs.getString("microchip"),
                            rs.getString("estado")
                    );
                    lst.add(mascota);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar las mascotas del dueño con documento " + duenoDocumento, e);
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
                    Mascota mascota = new Mascota(
                            rs.getInt("id"),
                            rs.getInt("dueno_id"),
                            rs.getString("nombre"),
                            rs.getInt("raza_id"),
                            rs.getInt("especie_id"),
                            rs.getDate("fecha_nacimiento").toLocalDate(),
                            rs.getString("sexo"),
                            rs.getString("url_foto"),
                            rs.getString("microchip"),
                            rs.getString("estado")
                    );
                    lst.add(mascota);

                }
            }
        } catch(SQLException e) {
            throw new RuntimeException("Error al consultar las mascotas de la raza ID " + razaId + "\n" + e);
        }
        return lst;
    }

    @Override
    public List<Mascota> obtenerPorEspecieId(Integer especieId) {
        List<Mascota> mascotas = new ArrayList<>();
        String sql = "SELECT * FROM mascota WHERE especie_id = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, especieId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Mascota mascota = new Mascota(
                            rs.getInt("id"),
                            rs.getInt("dueno_id"),
                            rs.getString("nombre"),
                            rs.getInt("raza_id"),
                            rs.getInt("especie_id"),
                            rs.getDate("fecha_nacimiento").toLocalDate(),
                            rs.getString("sexo"),
                            rs.getString("url_foto"),
                            rs.getString("microchip"),
                            rs.getString("estado")
                    );
                    mascotas.add(mascota);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener mascotas por especie con ID = " + especieId + "\n" + e.getMessage());
        }

        return mascotas;
    }

}
