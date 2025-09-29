package controller.inventarioController;

import model.entities.Inventario.Inventario;
import model.repository.InventarioDAO.InventarioDAO;

import java.util.List;

public class InventarioController {
    private final InventarioDAO inventarioDAO;

    public InventarioController() {
        this.inventarioDAO = new InventarioDAO();
    }

    public void agregarInventario(Inventario inventario) {
        try {
            inventarioDAO.insertar(inventario);
            System.out.println("Producto agregado al inventario correctamente.");
        } catch (Exception e) {
            System.out.println("Error al agregar producto: " + e.getMessage());
        }
    }

    public Inventario obtenerInventarioPorId(int id) {
        Inventario inventario = inventarioDAO.obtenerPorId(id);
        if (inventario != null) {
            System.out.println("Producto encontrado: " + inventario);
        } else {
            System.out.println("No se encontró el producto con ID: " + id);
        }
        return inventario;
    }

    public List<Inventario> obtenerTodos() {
        List<Inventario> inventarios = inventarioDAO.obtenerTodos();
        if (inventarios.isEmpty()) {
            System.out.println("No hay productos en el inventario.");
        } else {
            inventarios.forEach(System.out::println);
        }
        return inventarios;
    }

    public void actualizarInventario(Inventario inventario) {
        try {
            inventarioDAO.actualizar(inventario);
            System.out.println("Producto actualizado correctamente.");
        } catch (Exception e) {
            System.out.println("Error al actualizar producto: " + e.getMessage());
        }
    }

    public void eliminarInventario(int id) {
        try {
            inventarioDAO.eliminar(id);
            System.out.println("Producto eliminado del inventario.");
        } catch (Exception e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
        }
    }

    public List<Inventario> obtenerPorTipoProducto(int tipoId) {
        return inventarioDAO.obtenerTodos().stream()
                .filter(inv -> inv.getProducto_tipo_id().equals(tipoId))
                .toList();
    }

    public List<Inventario> obtenerPorProveedor(int proveedorId) {
        return inventarioDAO.obtenerTodos().stream()
                .filter(inv -> inv.getProveedor_id().equals(proveedorId))
                .toList();
    }

    public List<Inventario> obtenerProductosBajoStock() {
        return inventarioDAO.obtenerTodos().stream()
                .filter(inv -> inv.getCantidad_stock() < inv.getStock_minimo())
                .toList();
    }
}
