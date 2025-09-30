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

    // Validar entero positivo
    public int leerEntero(String mensaje) {
        int valor;
        while (true) {
            System.out.print(mensaje);
            String line = input.nextLine();
            try {
                valor = Integer.parseInt(line);
                if (valor < 0) {
                    System.out.println("No puede ser negativo.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número válido.");
            }
        }
        return valor;
    }

    // Validar texto no vacío
    private String leerTexto(String mensaje) {
        String valor;
        do {
            System.out.print(mensaje);
            valor = input.nextLine().trim();
            if (valor.isEmpty()) System.out.println("Este campo no puede estar vacío.");
        } while (valor.isEmpty());
        return valor;
    }

    // Agregar proveedor
    public void agregarProveedor() {
        String nombre = leerTexto("Nombre de la empresa: ");
        String contacto = leerTexto("Nombre del contacto: ");
        String telefono = validarTelefono("Teléfono: ");
        String email = validarEmail("Email: ");
        String direccion = leerTexto("Dirección: ");

        Proveedor proveedor = new Proveedor(null, nombre, contacto, telefono, email, direccion);
        try {
            proveedorDAO.insertar(proveedor);
            System.out.println("Proveedor agregado con éxito.");
        } catch (Exception e) {
            System.out.println("Error al agregar proveedor: " + e.getMessage());
        }
    }

    // Actualizar proveedor
    public void actualizarProveedor() {
        int id = leerEntero("ID del proveedor a actualizar: ");
        Proveedor proveedor = proveedorDAO.obtenerPorId(id);
        if (proveedor == null) {
            System.out.println("No existe proveedor con ese ID.");
            return;
        }

        System.out.println("Deje vacío para mantener el valor actual.");
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

        try {
            proveedorDAO.actualizar(proveedor);
            System.out.println("Proveedor actualizado con éxito.");
        } catch (Exception e) {
            System.out.println("Error al actualizar proveedor: " + e.getMessage());
        }
    }

    // Eliminar proveedor
    public void eliminarProveedor() {
        int id = leerEntero("ID del proveedor a eliminar: ");
        try {
            proveedorDAO.eliminar(id);
            System.out.println("Proveedor eliminado con éxito.");
        } catch (Exception e) {
            System.out.println("Error al eliminar proveedor: " + e.getMessage());
        }
    }

    // Buscar proveedor por ID
    public void buscarProveedorPorId() {
        int id = leerEntero("ID del proveedor: ");
        Proveedor proveedor = proveedorDAO.obtenerPorId(id);
        imprimirProveedor(proveedor);
    }

    // Listar todos
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

    public String validarEmail(String mensaje) {
        String email;
        while (true) {
            System.out.print(mensaje);
            email = input.nextLine().trim();
            if (email.isEmpty()) {
                System.out.println("El email no puede estar vacío.");
                continue;
            }
            // Validación simple de formato
            if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
                System.out.println("Email inválido. Debe tener formato ejemplo@dominio.com");
                continue;
            }
            break;
        }
        return email;
    }

    public String validarTelefono(String mensaje) {
        String telefono;
        while (true) {
            System.out.print(mensaje);
            telefono = input.nextLine().trim();
            if (telefono.isEmpty()) {
                System.out.println("El teléfono no puede estar vacío.");
                continue;
            }
            // Solo permite 10 caracteres del numero celular
            if (!telefono.matches("\\d{10}")) {
                System.out.println("Teléfono inválido. Solo números. Debe contener 10 digitos.");
                continue;
            }
            break;
        }
        return telefono;
    }


}
