package model.repository.MascotasDAO;

import model.ConexionSingleton;
import model.entities.Mascotas.Raza;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RazaDAO implements IRazasDAO {
    private Connection con;
    public RazaDAO() { con = ConexionSingleton.getInstance().getConnection();}

    @Override
    public Raza agregarRaza(Raza raza) {
        String sql = "INSERT INTO raza (especie_id, nombre) VALUES (?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, raza.getEspecie_id());
            pstmt.setString(2, raza.getNombre());
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    raza.setId(rs.getInt(1)); // ✅ Esto es obligatorio
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al agregar raza: " + e.getMessage());
        }
        return raza;
    }


    @Override
    public void actualizarRaza(Raza raza) {
        String sql = "UPDATE raza SET nombre = ?, especie_id = ? WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, raza.getNombre());
            pstmt.setInt(2, raza.getEspecie_id());
            pstmt.setInt(3, raza.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar raza ID=" + raza.getId(), e);
        }
    }


    @Override
    public void eliminarRaza(int id) {
        String sql = "DELETE FROM raza WHERE id = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            if (e.getSQLState().equals("23000")) {
                throw new RuntimeException("No se puede eliminar la raza con ID=" + id +
                        " porque hay mascotas asociadas.\n", e);
            }
            throw new RuntimeException("Error al eliminar la raza con ID " + id, e);
        }
    }

    @Override
    public Raza obtenerPorId(int id) {
        Raza raza = null;

        String sql = "SELECT * FROM raza WHERE id = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    raza = new Raza(rs.getInt("id"),
                            rs.getInt("especie_id"),
                            rs.getString("nombre"));

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar la raza con ID = " + id + "\n" + e);
        }
        return raza;
    }

    @Override
    public List<Raza> obtenerTodos() {
        List<Raza> lst = new ArrayList<>();
        String sql = "SELECT * FROM raza";

        try (Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Raza raza = new Raza(rs.getInt("id"),
                        rs.getInt("especie_id"),
                        rs.getString("nombre"));
                lst.add(raza);

            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar todas las razas. \n" + e);
        }
        return lst;
    }

    @Override
    public List<Raza> obtenerPorEspecieId(Integer especieId) {
        List<Raza> lst = new ArrayList<>();
        String sql = "SELECT * FROM raza WHERE especie_id = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, especieId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Raza raza = new Raza(rs.getInt("id"),
                            rs.getInt("especie_id"),
                            rs.getString("nombre"));
                    lst.add(raza);

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar todas las razas. \n" + e);
        }
        return lst;
    }
}
