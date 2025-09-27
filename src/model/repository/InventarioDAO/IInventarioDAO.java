package model.repository.InventarioDAO;

import model.entities.Inventario.Inventario;
import java.util.List;

public interface IInventarioDAO {
    void insertar(Inventario inventario);
    Inventario obtenerPorId(Integer id);
    List<Inventario> obtenerTodos();
    void actualizar(Inventario inventario);
    void eliminar(Integer id);
}
