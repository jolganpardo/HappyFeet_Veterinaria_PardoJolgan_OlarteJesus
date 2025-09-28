package controller.proveedorController;

import model.entities.Inventario.Proveedor;
import model.repository.InventarioDAO.IProveedorDAO;

import java.util.List;
import java.util.Scanner;

public class ProveedorController {
    private final IProveedorDAO proveedorDAO;
    private final Scanner input;

    public ProveedorController(IProveedorDAO proveedorDAO, Scanner input) {
        this.proveedorDAO = proveedorDAO;
        this.input = input;
    }

    public void agregarProveedor() {
        try {
            System.out.print("Nombre de la empresa: ");
            String nombre = input.nextLine();

            System.out.print("Nombre del contacto: ");
            String contacto = input.nextLine();

            System.out.print("Teléfono: ");
            String telefono = input.nextLine();

            System.out.print("Email: ");
            String email = input.nextLine();

            System.out.print("Dirección: ");
            String direccion = input.nextLine();

            Proveedor proveedor = new Proveedor(null, nombre, contacto, telefono, email, direccion);
            proveedorDAO.insertar(proveedor);
            System.out.println("Proveedor agregado con éxito.");
        } catch (Exception e) {
            System.out.println("Error al agregar proveedor: " + e.getMessage());
        }
    }

    public void actualizarProveedor() {
        try {
            System.out.print("ID del proveedor a actualizar: ");
            int id = input.nextInt();
            input.nextLine(); // limpiar buffer

            Proveedor proveedor = proveedorDAO.obtenerPorId(id);
            if (proveedor == null) {
                System.out.println("No existe proveedor con ese ID.");
                return;
            }

            System.out.print("Nuevo nombre de la empresa (" + proveedor.getNombre() + "): ");
            String nombre = input.nextLine();
            if (!nombre.isEmpty()) proveedor.setNombre(nombre);

            System.out.print("Nuevo contacto (" + proveedor.getContacto() + "): ");
            String contacto = input.nextLine();
            if (!contacto.isEmpty()) proveedor.setContacto(contacto);

            System.out.print("Nuevo teléfono (" + proveedor.getTelefono() + "): ");
            String telefono = input.nextLine();
            if (!telefono.isEmpty()) proveedor.setTelefono(telefono);

            System.out.print("Nuevo email (" + proveedor.getEmail() + "): ");
            String email = input.nextLine();
            if (!email.isEmpty()) proveedor.setEmail(email);

            System.out.print("Nueva dirección (" + proveedor.getDireccion() + "): ");
            String direccion = input.nextLine();
            if (!direccion.isEmpty()) proveedor.setDireccion(direccion);

            proveedorDAO.actualizar(proveedor);
            System.out.println("Proveedor actualizado con éxito.");
        } catch (Exception e) {
            System.out.println("Error al actualizar proveedor: " + e.getMessage());
        }
    }

    public void eliminarProveedor() {
        try {
            System.out.print("ID del proveedor a eliminar: ");
            int id = input.nextInt();
            input.nextLine();
            proveedorDAO.eliminar(id);
            System.out.println("Proveedor eliminado con éxito.");
        } catch (Exception e) {
            System.out.println("Error al eliminar proveedor: " + e.getMessage());
        }
    }

    public void buscarProveedorPorId() {
        System.out.print("ID del proveedor: ");
        int id = input.nextInt();
        input.nextLine();
        Proveedor proveedor = proveedorDAO.obtenerPorId(id);
        if (proveedor != null) {
            System.out.println("Proveedor encontrado: " + proveedor);
        } else {
            System.out.println("No existe proveedor con ese ID.");
        }
    }

    public void listarProveedores() {
        List<Proveedor> proveedores = proveedorDAO.obtenerTodos();
        System.out.println("\n📋 Lista de proveedores:");
        for (Proveedor p : proveedores) {
            System.out.println(p);
        }
    }
}
