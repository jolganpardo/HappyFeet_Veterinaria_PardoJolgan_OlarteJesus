package model.repository.InventarioDAO;

import model.entities.Inventario.Proveedor;
import java.util.List;

public interface IProveedorDAO {
    void insertar(Proveedor proveedor);
    Proveedor obtenerPorId(Integer id);
    List<Proveedor> obtenerTodos();
    void actualizar(Proveedor proveedor);
    void eliminar(Integer id);
}
