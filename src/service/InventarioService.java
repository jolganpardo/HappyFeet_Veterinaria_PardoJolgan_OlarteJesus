package service;

import model.entities.Inventario.Inventario;
import model.repository.InventarioDAO.IInventarioDAO;

import java.util.List;
import java.util.stream.Collectors;

public class InventarioService {

    private final IInventarioDAO inventarioDAO;

    public InventarioService(IInventarioDAO inventarioDAO) {
        this.inventarioDAO = inventarioDAO;
    }

    public void agregarInventario(Inventario inventario) {
        inventarioDAO.insertar(inventario);
    }

    public Inventario obtenerPorId(int id) {
        return inventarioDAO.obtenerPorId(id);
    }

    public List<Inventario> obtenerTodos() {
        return inventarioDAO.obtenerTodos();
    }

    public void actualizarInventario(Inventario inventario) {
        inventarioDAO.actualizar(inventario);
    }

    public void eliminarInventario(int id) {
        inventarioDAO.eliminar(id);
    }

    public List<Inventario> obtenerPorTipoProducto(int tipoId) {
        return inventarioDAO.obtenerTodos().stream()
                .filter(inv -> inv.getProducto_tipo_id().equals(tipoId))
                .collect(Collectors.toList());
    }

    public List<Inventario> obtenerPorProveedor(int proveedorId) {
        return inventarioDAO.obtenerTodos().stream()
                .filter(inv -> inv.getProveedor_id().equals(proveedorId))
                .collect(Collectors.toList());
    }

    public List<Inventario> obtenerProductosBajoStock() {
        return inventarioDAO.obtenerTodos().stream()
                .filter(inv -> inv.getCantidad_stock() < inv.getStock_minimo())
                .collect(Collectors.toList());
    }
}
