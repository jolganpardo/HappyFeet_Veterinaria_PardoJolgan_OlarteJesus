package controller.inventarioController;

import model.entities.Inventario.Inventario;
import model.repository.InventarioDAO.ProductoTipoDAO;
import model.repository.InventarioDAO.ProveedorDAO;
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
            imprimirInventario(inventario);
        } catch (Exception e) {
            System.out.println("Error al agregar producto: " + e.getMessage());
        }
    }

    public Inventario obtenerInventarioPorId(int id) {
        Inventario inventario = inventarioService.obtenerPorId(id);
        if (inventario != null) {
            imprimirInventario(inventario);
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
        List<Inventario> porTipo = inventarioService.obtenerPorTipoProducto(tipoId);
        if (porTipo.isEmpty()) {
            System.out.println("No se encontraron productos de este tipo.");
        } else {
            for (Inventario inv : porTipo) {
                imprimirInventario(inv);
            }
        }
        return porTipo;
    }

    public List<Inventario> obtenerPorProveedor(int proveedorId) {
        List<Inventario> porProveedor = inventarioService.obtenerPorProveedor(proveedorId);
        if (porProveedor.isEmpty()) {
            System.out.println("No se encontraron productos de este proveedor.");
        } else {
            for (Inventario inv : porProveedor) {
                imprimirInventario(inv);
            }
        }
        return porProveedor;
    }

    public List<Inventario> obtenerProductosBajoStock() {
        List<Inventario> bajos = inventarioService.obtenerProductosBajoStock();
        if (bajos.isEmpty()) {
            System.out.println("No hay productos bajo stock mínimo.");
        } else {
            for (Inventario inv : bajos) {
                imprimirInventario(inv);
            }
        }
        return bajos;
    }

    public void imprimirInventario(Inventario inv) {
        if (inv == null) {
            System.out.println("No se encontró el producto.");
            return;
        }

        // Obtener nombre del tipo
        ProductoTipoDAO tipoDAO = new ProductoTipoDAO();
        String tipoNombre = "Desconocido";
        if (inv.getProducto_tipo_id() != null) {
            tipoNombre = tipoDAO.obtenerPorId(inv.getProducto_tipo_id()).getNombre();
        }

        // Obtener nombre del proveedor
        ProveedorDAO provDAO = new ProveedorDAO();
        String proveedorNombre = "Desconocido";
        if (inv.getProveedor_id() != null) {
            proveedorNombre = provDAO.obtenerPorId(inv.getProveedor_id()).getNombre();
        }

        System.out.println("------ PRODUCTO ------");
        System.out.println("ID: " + inv.getId());
        System.out.println("Nombre: " + inv.getNombre_producto());
        System.out.println("Tipo: " + tipoNombre);
        System.out.println("Descripción: " + inv.getDescripcion());
        System.out.println("Fabricante: " + inv.getFabricante());
        System.out.println("Lote: " + inv.getLote());
        System.out.println("Stock: " + inv.getCantidad_stock());
        System.out.println("Stock mínimo: " + inv.getStock_minimo());
        System.out.println("Fecha de vencimiento: " + inv.getFecha_vencimiento());
        System.out.println("Precio venta: " + inv.getPrecio_venta());
        System.out.println("Proveedor: " + proveedorNombre);
        System.out.println("---------------------");
    }

}
