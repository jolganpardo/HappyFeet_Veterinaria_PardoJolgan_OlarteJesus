package model.repository.CitasDAO;

import model.entities.Citas.CitaEstado;
import java.util.List;

public interface ICitaEstadoDAO {
    void agregarEstado(CitaEstado estado);
    CitaEstado obtenerPorId(Integer id);
    CitaEstado obtenerPorNombre(String nombre);
    List<CitaEstado> obtenerTodos();
    void actualizarEstado(CitaEstado estado);
    void eliminarEstado(Integer id);
}
