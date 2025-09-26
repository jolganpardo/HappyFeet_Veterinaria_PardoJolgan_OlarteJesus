package model.repository.MascotasDAO;

import model.ConexionSingleton;
import model.entities.Mascotas.Especies;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EspecieDAO implements IEspeciesDAO{
    private Connection con;
    public EspecieDAO() {con = ConexionSingleton.getInstance().getConnection();}

    @Override
    public void agregarEspecie(Especies especie) {
        String sql = "INSERT INTO especies (nombre) VALUES (?) ";
        try (PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setString(1,especie.getNombre());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al agregar especie.\n" + e.getMessage());
        }
    }

    @Override
    public Especies obtenerPorId(Integer id) {
        Especies especie = null;
        String sql = "SELECT * FROM especies WHERE id = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    especie = new Especies(rs.getInt("id"),
                            rs.getString("nombre"));
                }
            }
        } catch(SQLException e) {
            throw new RuntimeException("Error al consultar la especie con ID = " + id + "\n" + e.getMessage());
        }

        return especie;
    }

    @Override
    public List<Especies>  obtenerTodos() {
        List<Especies> lst = new ArrayList<>();
        String sql = "SELECT * FROM especies";

        try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {

            while(rs.next()) {
                Especies especie = new Especies(rs.getInt("id"),
                        rs.getString("nombre"));

                lst.add(especie);
            }
        } catch(SQLException e) {
            throw new RuntimeException("Error al consultar todas las especies.\n" + e.getMessage());
        }
        return lst;
    }

    @Override
    public void actualizarEspecie(Especies especie) {
        String sql = "UPDATE especies SET nombre = ? WHERE id = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setString(1, especie.getNombre());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar especie: " + especie.getNombre() + "\n" + e.getMessage());
        }
    }

    @Override
    public void eliminarEspecie(Integer id) {
        String sql = "DELETE FROM especies WHERE id = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar especie: " + id + "\n" + e.getMessage());
        }
    }
}
