package model.repository.duenosDAO;

import model.ConexionSingleton;
import model.entities.Duenos.Dueno;
import model.entities.Mascotas.Mascotas;
import model.entities.Mascotas.Razas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DuenoDAO implements IDuenosDAO {
    private Connection con;
    public DuenoDAO() {con = ConexionSingleton.getInstance().getConnection();}

    @Override
    public void agregarDueno(Dueno dueno) {
        String sql  = "INSERT INTO duenos (nombre_completo, documento_identidad, direccion, telefono, email) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, dueno.getNombre_completo());
            pstmt.setString(2, dueno.getDocumento_identidad());
            pstmt.setString(3, dueno.getDireccion());
            pstmt.setString(4, dueno.getTelefono());
            pstmt.setString(5, dueno.getEmail());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al agregar dueño.\n" + e.getMessage());
        }

    }

    @Override
    public Dueno buscarPorId(int id) {
        Dueno dueno = null;

        String sql = "SELECT * FROM duenos WHERE id = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    dueno = new Dueno(rs.getInt("id"),
                            rs.getString("nombre_completo"),
                            rs.getString("documento_identidad"),
                            rs.getString("direccion"),
                            rs.getString("telefono"),
                            rs.getString("email"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar el dueño con ID = " + id + "\n" + e.getMessage());
        }
        return dueno;
    }

    @Override
    public List<Dueno> listarDuenos() {
        List<Dueno> lst = new ArrayList<>();
        String sql = "SELECT * FROM duenos";

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Dueno dueno = new Dueno(rs.getInt("id"),
                        rs.getString("nombre_completo"),
                        rs.getString("documento_identidad"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("email"));
                lst.add(dueno);
            }
        } catch(SQLException e) {
            throw new RuntimeException("Error al consultar todos los dueños" + "\n" + e.getMessage());
        }
        return lst;
    }

    @Override
    public void actualizarDuenos(Dueno dueno) {
        String sql = "UPDATE duenos SET nombre_completo = ?, documento_identidad = ?, direccion = ?, telefono = ?, email = ? WHERE id = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, dueno.getNombre_completo());
            pstmt.setString(2, dueno.getDocumento_identidad());
            pstmt.setString(3, dueno.getDireccion());
            pstmt.setString(4, dueno.getTelefono());
            pstmt.setString(5, dueno.getEmail());
            pstmt.setInt(6, dueno.getId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar dueño con ID " + dueno.getId() + "\n" + e.getMessage());
        }
    }

    @Override
    public void eliminarDueno(int id) {
        String sql = "DELETE FROM duenos WHERE id = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            if (e.getSQLState().equals("23000")) {
                throw new RuntimeException("No se puede eliminar el dueño con ID=" + id +
                        " porque hay mascotas asociadas.\n", e);
            }
            throw new RuntimeException("Error al eliminar el dueño con ID " + id, e);
        }
    }
}
