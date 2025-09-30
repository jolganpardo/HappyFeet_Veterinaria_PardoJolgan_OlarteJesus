package controller.proveedorController;

import model.entities.Inventario.Proveedor;
import service.ProveedorService;
import util.Validaciones;

import java.util.List;
import java.util.Scanner;

public class ProveedorController {
    private final ProveedorService proveedorService;
    private final Validaciones validador;

    public ProveedorController(ProveedorService proveedorService, Scanner input) {
        this.proveedorService = proveedorService;
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
            proveedorService.agregarProveedor(proveedor);
            System.out.println("Proveedor agregado con éxito.");
        } catch (Exception e) {
            System.out.println("Error al agregar proveedor: " + e.getMessage());
        }
    }

    public void actualizarProveedor() {
        int id = validador.leerEnteroPositivo("ID del proveedor a actualizar: ");
        Proveedor proveedor = proveedorService.obtenerProveedorPorId(id);
        if (proveedor == null) {
            System.out.println("No existe proveedor con ese ID.");
            return;
        }

        System.out.println("Deje vacío para mantener el valor actual.");
        String nombre = validador.leerTexto("Nuevo nombre de la empresa (" + proveedor.getNombre() + "): ");
        if (!nombre.isEmpty()) proveedor.setNombre(nombre);

        String contacto = validador.leerTexto("Nuevo contacto (" + proveedor.getContacto() + "): ");
        if (!contacto.isEmpty()) proveedor.setContacto(contacto);

        String telefono = validador.validarTelefonoOpcional("Nuevo teléfono (" + proveedor.getTelefono() + "): ");
        if (!telefono.isEmpty()) proveedor.setTelefono(telefono);

        String email = validador.validarEmailOpcional("Nuevo email (" + proveedor.getEmail() + "): ");
        if (!email.isEmpty()) proveedor.setEmail(email);

        String direccion = validador.leerTexto("Nueva dirección (" + proveedor.getDireccion() + "): ");
        if (!direccion.isEmpty()) proveedor.setDireccion(direccion);

        try {
            proveedorService.actualizarProveedor(proveedor);
            System.out.println("Proveedor actualizado con éxito.");
        } catch (Exception e) {
            System.out.println("Error al actualizar proveedor: " + e.getMessage());
        }
    }

    public void eliminarProveedor() {
        int id = validador.leerEnteroPositivo("ID del proveedor a eliminar: ");
        try {
            proveedorService.eliminarProveedor(id);
            System.out.println("Proveedor eliminado con éxito.");
        } catch (Exception e) {
            System.out.println("Error al eliminar proveedor: " + e.getMessage());
        }
    }

    public void buscarProveedorPorId() {
        int id = validador.leerEnteroPositivo("ID del proveedor: ");
        Proveedor proveedor = proveedorService.obtenerProveedorPorId(id);
        imprimirProveedor(proveedor);
    }

    public void listarProveedores() {
        List<Proveedor> proveedores = proveedorService.obtenerTodosLosProveedores();
        if (proveedores.isEmpty()) {
            System.out.println("No hay proveedores registrados.");
        } else {
            System.out.println("\n=== LISTA DE PROVEEDORES ===");
            for (Proveedor p : proveedores) {
                imprimirProveedor(p);
            }
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