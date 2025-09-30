package controller.proveedorController;

import model.entities.Inventario.Proveedor;
import model.repository.InventarioDAO.IProveedorDAO;
import util.Validaciones;

import java.util.List;
import java.util.Scanner;

public class ProveedorController {
    private final IProveedorDAO proveedorDAO;
    private final Validaciones validador;

    public ProveedorController(IProveedorDAO proveedorDAO, Scanner input) {
        this.proveedorDAO = proveedorDAO;
        this.validador = new Validaciones(input);
    }

    public void agregarProveedor() {
        String nombre = validador.leerTexto("Nombre de la empresa: ");
        String contacto = validador.leerTexto("Nombre del contacto: ");
        String telefono = validador.validarTelefono("Teléfono: ");
        String email = validador.validarEmail("Email: ");
        String direccion = validador.leerTexto("Dirección: ");

        Proveedor proveedor = new Proveedor(null, nombre, contacto, telefono, email, direccion);
        try {
            proveedorDAO.insertar(proveedor);
            System.out.println("Proveedor agregado con éxito.");
        } catch (Exception e) {
            System.out.println("Error al agregar proveedor: " + e.getMessage());
        }
    }

    public void actualizarProveedor() {
        int id = validador.leerEnteroPositivo("ID del proveedor a actualizar: ");
        Proveedor proveedor = proveedorDAO.obtenerPorId(id);
        if (proveedor == null) {
            System.out.println("No existe proveedor con ese ID.");
            return;
        }

        System.out.println("Deje vacío para mantener el valor actual.");
        String nombre = validador.leerTexto("Nuevo nombre de la empresa (" + proveedor.getNombre() + "): ");
        if (!nombre.isEmpty()) proveedor.setNombre(nombre);

        String contacto = validador.leerTexto("Nuevo contacto (" + proveedor.getContacto() + "): ");
        if (!contacto.isEmpty()) proveedor.setContacto(contacto);

        String telefono = validador.validarTelefono("Nuevo teléfono (" + proveedor.getTelefono() + "): ");
        if (!telefono.isEmpty()) proveedor.setTelefono(telefono);

        String email = validador.validarEmail("Nuevo email (" + proveedor.getEmail() + "): ");
        if (!email.isEmpty()) proveedor.setEmail(email);

        String direccion = validador.leerTexto("Nueva dirección (" + proveedor.getDireccion() + "): ");
        if (!direccion.isEmpty()) proveedor.setDireccion(direccion);

        try {
            proveedorDAO.actualizar(proveedor);
            System.out.println("Proveedor actualizado con éxito.");
        } catch (Exception e) {
            System.out.println("Error al actualizar proveedor: " + e.getMessage());
        }
    }

    public void eliminarProveedor() {
        int id = validador.leerEnteroPositivo("ID del proveedor a eliminar: ");
        try {
            proveedorDAO.eliminar(id);
            System.out.println("Proveedor eliminado con éxito.");
        } catch (Exception e) {
            System.out.println("Error al eliminar proveedor: " + e.getMessage());
        }
    }

    public void buscarProveedorPorId() {
        int id = validador.leerEnteroPositivo("ID del proveedor: ");
        Proveedor proveedor = proveedorDAO.obtenerPorId(id);
        imprimirProveedor(proveedor);
    }

    public void listarProveedores() {
        List<Proveedor> proveedores = proveedorDAO.obtenerTodos();
        if (proveedores.isEmpty()) {
            System.out.println("No hay proveedores registrados.");
        } else {
            System.out.println("\n=== LISTA DE PROVEEDORES ===");
            proveedores.forEach(this::imprimirProveedor);
        }
    }

    public void imprimirProveedor(Proveedor p) {
        if (p == null) {
            System.out.println("No se encontró el proveedor.");
            return;
        }
        System.out.println("------ PROVEEDOR ------");
        System.out.println("ID: " + p.getId());
        System.out.println("Empresa: " + p.getNombre());
        System.out.println("Contacto: " + p.getContacto());
        System.out.println("Teléfono: " + p.getTelefono());
        System.out.println("Email: " + p.getEmail());
        System.out.println("Dirección: " + p.getDireccion());
        System.out.println("------------------------");
    }
}
