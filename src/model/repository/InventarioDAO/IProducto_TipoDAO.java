package model.repository.InventarioDAO;

import model.entities.Inventario.Producto_Tipo;

import java.util.List;

public interface IProducto_TipoDAO {
    void insertar(Producto_Tipo productoTipo);
    Producto_Tipo obtenerPorId(Integer id);
    List<Producto_Tipo> obtenerTodos();
    void actualizar(Producto_Tipo productoTipo);
    void eliminar(Integer id);
}
