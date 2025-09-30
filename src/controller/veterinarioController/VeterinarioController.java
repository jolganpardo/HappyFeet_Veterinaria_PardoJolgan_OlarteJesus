package controller.veterinarioController;

import model.entities.Veterinarios.Veterinario;
import service.VeterinarioService;
import util.Validaciones;

import java.util.List;
import java.util.Scanner;

public class VeterinarioController {
    private final VeterinarioService veterinarioService;
    private final Validaciones validador;

    public VeterinarioController(VeterinarioService veterinarioService, Scanner input) {
        this.veterinarioService = veterinarioService;
        this.validador = new Validaciones(input);
    }

    public void agregarVeterinario() {
        String nombre = validador.leerTexto("Nombre completo: ");
        String especialidad = validador.leerTexto("Especialidad: ");
        String telefono = validador.validarTelefono("Teléfono: ");
        String email = validador.validarEmail("Email: ");

        Veterinario veterinario = new Veterinario(null, nombre, especialidad, telefono, email);
        try {
            Integer idGenerado = veterinarioService.agregarVeterinario(veterinario);
            veterinario.setId(idGenerado);
            System.out.println("Veterinario agregado con éxito. ID: " + idGenerado);
            imprimirVeterinario(veterinario);
        } catch (Exception e) {
            System.out.println("Error al agregar veterinario: " + e.getMessage());
        }
    }

    public void actualizarVeterinario() {
        int id = validador.leerEnteroPositivo("ID del veterinario a actualizar: ");
        Veterinario veterinario = veterinarioService.buscarVeterinarioPorId(id);
        if (veterinario == null) {
            System.out.println("No existe veterinario con ese ID.");
            return;
        }

        System.out.println("Deje vacío para mantener el valor actual.");
        String nombre = validador.leerTexto("Nuevo nombre (" + veterinario.getNombre_completo() + "): ");
        if (!nombre.isEmpty()) veterinario.setNombre_completo(nombre);

        String especialidad = validador.leerTexto("Nueva especialidad (" + veterinario.getEspecialidad() + "): ");
        if (!especialidad.isEmpty()) veterinario.setEspecialidad(especialidad);

        String telefono = validador.validarTelefonoOpcional("Nuevo teléfono (" + veterinario.getTelefono() + "): ");
        if (!telefono.isEmpty()) veterinario.setTelefono(telefono);

        String email = validador.validarEmailOpcional("Nuevo email (" + veterinario.getEmail() + "): ");
        if (!email.isEmpty()) veterinario.setEmail(email);

        try {
            veterinarioService.actualizarVeterinario(veterinario);
            System.out.println("Veterinario actualizado con éxito.");
        } catch (Exception e) {
            System.out.println("Error al actualizar veterinario: " + e.getMessage());
        }
    }

    public void eliminarVeterinario() {
        int id = validador.leerEnteroPositivo("ID del veterinario a eliminar: ");
        try {
            veterinarioService.eliminarVeterinario(id);
            System.out.println("Veterinario eliminado con éxito.");
        } catch (Exception e) {
            System.out.println("Error al eliminar veterinario: " + e.getMessage());
        }
    }

    public void buscarVeterinarioPorId() {
        int id = validador.leerEnteroPositivo("ID del veterinario: ");
        Veterinario veterinario = veterinarioService.buscarVeterinarioPorId(id);
        imprimirVeterinario(veterinario);
    }

    public void listarVeterinarios() {
        List<Veterinario> veterinarios = veterinarioService.obtenerTodosLosVeterinarios();
        if (veterinarios.isEmpty()) {
            System.out.println("No hay veterinarios registrados.");
        } else {
            System.out.println("\n=== LISTA DE VETERINARIOS ===");
            for (Veterinario v : veterinarios) {
                imprimirVeterinario(v);
            }
        }
    }

    public void imprimirVeterinario(Veterinario veterinario) {
        if (veterinario == null) {
            System.out.println("No se encontró el veterinario.");
            return;
        }
        System.out.println("------ INFORMACION DEL VETERINARIO ------");
        System.out.println("ID: " + veterinario.getId());
        System.out.println("Nombre completo: " + veterinario.getNombre_completo());
        System.out.println("Especialidad: " + veterinario.getEspecialidad());
        System.out.println("Telefono: " + veterinario.getTelefono());
        System.out.println("Email: " + veterinario.getEmail());
        System.out.println("------------------------------------------");
    }
}