package service;

import model.entities.Inventario.Proveedor;
import model.repository.InventarioDAO.IProveedorDAO;

import java.util.List;

public class ProveedorService {
    private final IProveedorDAO proveedorDAO;

    public ProveedorService(IProveedorDAO proveedorDAO) {
        this.proveedorDAO = proveedorDAO;
    }

    public void agregarProveedor(Proveedor proveedor) throws Exception {
        proveedorDAO.insertar(proveedor);
    }

    public void actualizarProveedor(Proveedor proveedor) throws Exception {
        proveedorDAO.actualizar(proveedor);
    }

    public void eliminarProveedor(int id) throws Exception {
        proveedorDAO.eliminar(id);
    }

    public Proveedor obtenerProveedorPorId(int id) {
        return proveedorDAO.obtenerPorId(id);
    }

    public List<Proveedor> obtenerTodosLosProveedores() {
        return proveedorDAO.obtenerTodos();
    }
}