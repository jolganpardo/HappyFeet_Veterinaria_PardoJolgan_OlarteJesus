package model.repository.MascotasDAO;

import model.ConexionSingleton;
import model.entities.Mascotas.Especie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EspecieDAO implements IEspeciesDAO{
    private Connection con;
    public EspecieDAO() {con = ConexionSingleton.getInstance().getConnection();}

    @Override
    public void agregarEspecie(Especie especie) {
        String sql = "INSERT INTO especie (nombre) VALUES (?) ";
        try (PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setString(1,especie.getNombre());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al agregar especie.\n" + e.getMessage());
        }
    }

    @Override
    public Especie obtenerPorId(Integer id) {
        Especie especie = null;
        String sql = "SELECT * FROM especie WHERE id = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    especie = new Especie(rs.getInt("id"),
                            rs.getString("nombre"));
                }
            }
        } catch(SQLException e) {
            throw new RuntimeException("Error al consultar la especie con ID = " + id + "\n" + e.getMessage());
        }

        return especie;
    }

    @Override
    public List<Especie>  obtenerTodos() {
        List<Especie> lst = new ArrayList<>();
        String sql = "SELECT * FROM especie";

        try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {

            while(rs.next()) {
                Especie especie = new Especie(rs.getInt("id"),
                        rs.getString("nombre"));

                lst.add(especie);
            }
        } catch(SQLException e) {
            throw new RuntimeException("Error al consultar todas las especies.\n" + e.getMessage());
        }
        return lst;
    }

    @Override
    public void actualizarEspecie(Especie especie) {
        String sql = "UPDATE especie SET nombre = ? WHERE id = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setString(1, especie.getNombre());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar especie: " + especie.getNombre() + "\n" + e.getMessage());
        }
    }

    @Override
    public void eliminarEspecie(Integer id) {
        String sql = "DELETE FROM especie WHERE id = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar especie: " + id + "\n" + e.getMessage());
        }
    }
}
