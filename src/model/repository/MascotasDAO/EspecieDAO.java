package model.repository.MascotasDAO;

import model.ConexionSingleton;
import model.entities.Mascotas.Especie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EspecieDAO implements IEspeciesDAO {
    private Connection con;
    public EspecieDAO() {con = ConexionSingleton.getInstance().getConnection();}

    @Override
    public Especie agregarEspecie(Especie especie) {
        String sql = "INSERT INTO especie (nombre) VALUES (?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, especie.getNombre());
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    especie.setId(rs.getInt(1));
                }
            }
            System.out.println("Especie agregada con éxito. ID: " + especie.getId());
        } catch (SQLException e) {
            throw new RuntimeException("Error al agregar especie: " + e.getMessage());
        }
        return especie;
    }

    @Override
    public Especie obtenerPorId(Integer id) {
        Especie especie = null;
        String sql = "SELECT * FROM especie WHERE id = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    especie = new Especie(rs.getInt("id"),
                            rs.getString("nombre"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar la especie con ID = " + id + "\n" + e.getMessage());
        }

        return especie;
    }

    @Override
    public List<Especie> obtenerTodos() {
        List<Especie> lst = new ArrayList<>();
        String sql = "SELECT * FROM especie ORDER BY nombre";

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Especie especie = new Especie(rs.getInt("id"),
                        rs.getString("nombre"));
                lst.add(especie);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar todas las especies.\n" + e.getMessage());
        }
        return lst;
    }

    @Override
    public void actualizarEspecie(Especie especie) {
        String sql = "UPDATE especie SET nombre = ? WHERE id = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, especie.getNombre());
            pstmt.setInt(2, especie.getId());
            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas == 0) {
                throw new RuntimeException("No se encontró la especie con ID: " + especie.getId());
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar especie: " + especie.getNombre() + "\n" + e.getMessage());
        }
    }

    @Override
    public void eliminarEspecie(Integer id) {
        String sql = "DELETE FROM especie WHERE id = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas == 0) {
                throw new RuntimeException("No se encontró la especie con ID: " + id);
            }

        } catch (SQLException e) {
            if (e.getSQLState().equals("23000")) {
                throw new RuntimeException("No se puede eliminar la especie con ID=" + id +
                        " porque hay razas o mascotas asociadas.\n", e);
            }
            throw new RuntimeException("Error al eliminar especie: " + id + "\n" + e.getMessage());
        }
    }
}