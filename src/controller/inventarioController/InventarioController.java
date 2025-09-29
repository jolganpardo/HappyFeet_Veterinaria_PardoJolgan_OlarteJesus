package controller.inventarioController;

import model.entities.Inventario.Inventario;
import model.repository.InventarioDAO.InventarioDAO;

import java.util.List;

public class InventarioController {
    private final InventarioDAO inventarioDAO;

    public InventarioController() {
        this.inventarioDAO = new InventarioDAO();
    }

    // Crear
    public void agregarInventario(Inventario inventario) {
        try {
            inventarioDAO.insertar(inventario);
            System.out.println("✅ Producto agregado al inventario correctamente.");
        } catch (Exception e) {
            System.out.println("❌ Error al agregar producto: " + e.getMessage());
        }
    }

    // Leer por ID
    public Inventario obtenerInventarioPorId(int id) {
        Inventario inventario = inventarioDAO.obtenerPorId(id);
        if (inventario != null) {
            System.out.println("Producto encontrado: " + inventario);
        } else {
            System.out.println("No se encontró el producto con ID: " + id);
        }
        return inventario;
    }

    // Leer todos
    public List<Inventario> obtenerTodos() {
        List<Inventario> inventarios = inventarioDAO.obtenerTodos();
        if (inventarios.isEmpty()) {
            System.out.println("⚠️ No hay productos en el inventario.");
        } else {
            inventarios.forEach(System.out::println);
        }
        return inventarios;
    }

    // Actualizar
    public void actualizarInventario(Inventario inventario) {
        try {
            inventarioDAO.actualizar(inventario);
            System.out.println("Producto actualizado correctamente.");
        } catch (Exception e) {
            System.out.println("Error al actualizar producto: " + e.getMessage());
        }
    }

    // Eliminar
    public void eliminarInventario(int id) {
        try {
            inventarioDAO.eliminar(id);
            System.out.println("Producto eliminado del inventario.");
        } catch (Exception e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
        }
    }

    // === MÉTODOS EXTRA ÚTILES ===

    // Buscar productos por tipo
    public List<Inventario> obtenerPorTipoProducto(int tipoId) {
        return inventarioDAO.obtenerTodos().stream()
                .filter(inv -> inv.getProducto_tipo_id().equals(tipoId))
                .toList();
    }

    // Buscar productos por proveedor
    public List<Inventario> obtenerPorProveedor(int proveedorId) {
        return inventarioDAO.obtenerTodos().stream()
                .filter(inv -> inv.getProveedor_id().equals(proveedorId))
                .toList();
    }

    // Mostrar productos por debajo del stock mínimo
    public List<Inventario> obtenerProductosBajoStock() {
        return inventarioDAO.obtenerTodos().stream()
                .filter(inv -> inv.getCantidad_stock() < inv.getStock_minimo())
                .toList();
    }
}
