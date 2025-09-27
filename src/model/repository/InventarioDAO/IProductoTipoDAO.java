package model.repository.InventarioDAO;

import model.entities.Inventario.ProductoTipo;
import java.util.List;

public interface IProductoTipoDAO {
    void insertar(ProductoTipo tipo);
    ProductoTipo obtenerPorId(Integer id);
    ProductoTipo obtenerPorNombre(String nombre);
    List<ProductoTipo> obtenerTodos();
    void actualizar(ProductoTipo tipo);
    void eliminar(Integer id);
}
