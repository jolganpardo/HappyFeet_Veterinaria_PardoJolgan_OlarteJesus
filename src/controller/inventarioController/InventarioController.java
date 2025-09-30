package controller.inventarioController;

import model.entities.Inventario.Inventario;
import service.InventarioService;

import java.util.List;

public class InventarioController {

    private final InventarioService inventarioService;

    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    public void agregarInventario(Inventario inventario) {
        try {
            inventarioService.agregarInventario(inventario);
            System.out.println("Producto agregado al inventario correctamente.");
        } catch (Exception e) {
            System.out.println("Error al agregar producto: " + e.getMessage());
        }
    }

    public Inventario obtenerInventarioPorId(int id) {
        Inventario inventario = inventarioService.obtenerPorId(id);
        if (inventario != null) {
            System.out.println("Producto encontrado: " + inventario);
        } else {
            System.out.println("No se encontró el producto con ID: " + id);
        }
        return inventario;
    }

    public List<Inventario> obtenerTodos() {
        return inventarioService.obtenerTodos();
    }

    public void actualizarInventario(Inventario inventario) {
        try {
            inventarioService.actualizarInventario(inventario);
            System.out.println("Producto actualizado correctamente.");
        } catch (Exception e) {
            System.out.println("Error al actualizar producto: " + e.getMessage());
        }
    }

    public void eliminarInventario(int id) {
        try {
            inventarioService.eliminarInventario(id);
            System.out.println("Producto eliminado del inventario.");
        } catch (Exception e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
        }
    }

    public List<Inventario> obtenerPorTipoProducto(int tipoId) {
        return inventarioService.obtenerPorTipoProducto(tipoId);
    }

    public List<Inventario> obtenerPorProveedor(int proveedorId) {
        return inventarioService.obtenerPorProveedor(proveedorId);
    }

    public List<Inventario> obtenerProductosBajoStock() {
        return inventarioService.obtenerProductosBajoStock();
    }
}
