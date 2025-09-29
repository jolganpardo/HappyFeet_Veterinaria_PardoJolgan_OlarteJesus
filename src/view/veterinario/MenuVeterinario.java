package view.veterinario;

import controller.veterinarioController.VeterinarioController;
import model.entities.Veterinarios.Veterinario;
import model.repository.VeterinariosDAO.IVeterinariosDAO;
import model.repository.VeterinariosDAO.VeterinarioDAO;

import java.util.List;
import java.util.Scanner;

public class MenuVeterinario {
    private Scanner input;
    private IVeterinariosDAO veterinariosDAO;
    private VeterinarioController veterinarioController;

    public MenuVeterinario() {
        this.input = new Scanner(System.in);
        this.veterinariosDAO = new VeterinarioDAO();
    }

    public void mostrarMenuVeterinario() {
        int opcion;
        do {
            System.out.print("""
                \n=== MENU VETERINARIO ===
                1. Agregar un veterinario.
                2. Actualizar datos de un veterinario por ID.
                3. Eliminar un veterinario por ID.
                4. Buscar veterinario por ID.
                5. Listar todos los veterinarios.
                0. Salir del menú de veterinario.
                Ingrese una opción:
                """);
            opcion = input.nextInt();
            input.nextLine();

            switch (opcion) {
                case 1 -> agregarVeterinario();
                case 2 -> actualizarVeterinario();
                case 3 -> eliminarVeterinario();
                case 4 -> buscarPorId();
                case 5 -> listarVeterinarios();
                case 0 -> System.out.println("Saliendo del menú de veterinarios...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    public void agregarVeterinario() {
        System.out.print("Nombre completo: ");
        String nombre = input.nextLine();

        System.out.print("Especialidad: ");
        String especialidad = input.nextLine();

        System.out.print("Teléfono: ");
        String telefono = input.nextLine();

        System.out.print("Email: ");
        String email = input.nextLine();

        Veterinario v = new Veterinario(null, nombre, especialidad, telefono, email);
        veterinariosDAO.insertar(v);
        System.out.println("Veterinario agregado con éxito.");
    }

    public void actualizarVeterinario() {
        System.out.print("ID del veterinario a actualizar: ");
        int id = input.nextInt();
        input.nextLine();

        Veterinario v = veterinariosDAO.buscarPorId(id);
        if (v != null) {
            System.out.print("Nuevo nombre (" + v.getNombre_completo() + "): ");
            String nombre = input.nextLine();
            if (!nombre.isEmpty()) v.setNombre_completo(nombre);

            System.out.print("Nueva especialidad (" + v.getEspecialidad() + "): ");
            String especialidad = input.nextLine();
            if (!especialidad.isEmpty()) v.setEspecialidad(especialidad);

            System.out.print("Nuevo teléfono (" + v.getTelefono() + "): ");
            String telefono = input.nextLine();
            if (!telefono.isEmpty()) v.setTelefono(telefono);

            System.out.print("Nuevo email (" + v.getEmail() + "): ");
            String email = input.nextLine();
            if (!email.isEmpty()) v.setEmail(email);

            veterinariosDAO.actualizar(v);
            System.out.println("Veterinario actualizado con éxito.");
        } else {
            System.out.println("No existe veterinario con ese ID.");
        }
    }

    public void eliminarVeterinario() {
        System.out.print("ID del veterinario a eliminar: ");
        int id = input.nextInt();
        input.nextLine();
        veterinariosDAO.eliminar(id);
        System.out.println("🗑️ Veterinario eliminado con éxito.");
    }

    public void buscarPorId() {
        System.out.print("ID del veterinario: ");
        int id = input.nextInt();
        input.nextLine();

        Veterinario v = veterinariosDAO.buscarPorId(id);
        if (v != null) {
            System.out.println("🔎Veterinario encontrado: " + v);
        } else {
            System.out.println("No existe veterinario con ese ID.");
        }
    }

    public void listarVeterinarios() {
        List<Veterinario> lista = veterinariosDAO.listarTodos();
        System.out.println("\n📋 Lista de veterinarios:");
        for (Veterinario v : lista) {
            System.out.println(v);
        }
    }
}
