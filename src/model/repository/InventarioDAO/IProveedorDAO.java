package model.repository.InventarioDAO;

import model.entities.Inventario.Proveedores;

import java.util.List;

public interface IProveedorDAO {
    void insertar(Proveedores proveedor);
    Proveedores obtenerPorId(Integer id);
    List<Proveedores> obtenerTodos();
    void actualizar(Proveedores proveedor);
    void eliminar(Integer id);
}
