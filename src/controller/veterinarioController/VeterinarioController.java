package controller.veterinarioController;

import model.entities.Veterinarios.Veterinario;
import model.repository.VeterinariosDAO.IVeterinariosDAO;

import java.util.List;
import java.util.Scanner;

public class VeterinarioController {
    private final IVeterinariosDAO veterinarioDAO;
    private final Scanner input;

    public VeterinarioController(IVeterinariosDAO veterinarioDAO, Scanner input) {
        this.veterinarioDAO = veterinarioDAO;
        this.input = input;
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
        veterinarioDAO.insertar(v);
        System.out.println("Veterinario agregado con éxito.");
    }

    public void actualizarVeterinario() {
        System.out.print("ID del veterinario a actualizar: ");
        int id = input.nextInt();
        input.nextLine();

        Veterinario v = veterinarioDAO.buscarPorId(id);
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

            veterinarioDAO.actualizar(v);
            System.out.println("Veterinario actualizado con éxito.");
        } else {
            System.out.println("No existe veterinario con ese ID.");
        }
    }

    public void eliminarVeterinario() {
        System.out.print("ID del veterinario a eliminar: ");
        int id = input.nextInt();
        input.nextLine();
        veterinarioDAO.eliminar(id);
        System.out.println("🗑️ Veterinario eliminado con éxito.");
    }

    public void buscarPorId() {
        System.out.print("ID del veterinario: ");
        int id = input.nextInt();
        input.nextLine();

        Veterinario v = veterinarioDAO.buscarPorId(id);
        if (v != null) {
            System.out.println("🔎Veterinario encontrado: " + v);
        } else {
            System.out.println("No existe veterinario con ese ID.");
        }
    }

    public void listarVeterinarios() {
        List<Veterinario> lista = veterinarioDAO.listarTodos();
        System.out.println("\n📋 Lista de veterinarios:");
        for (Veterinario v : lista) {
            System.out.println(v);
        }
    }
}
