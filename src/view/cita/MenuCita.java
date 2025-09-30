package view.cita;

import controller.citaController.CitaController;
import model.entities.Citas.Cita;
import model.entities.Veterinarios.Veterinario;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class MenuCita {

    private final CitaController controller;
    private final Scanner input;

    public MenuCita(CitaController controller) {
        this.controller = controller;
        this.input = new Scanner(System.in);
    }

    public void mostrarMenuCita() {
        int opcion;
        do {
            System.out.print("""
                    \n=== MENU CITA ===
                    1. Ingresar una cita.
                    2. Obtener cita por ID.
                    3. Mostrar todas las citas.
                    0. Volver al menu principal.
                    Selecciona una opción: 
                    """);
            opcion = leerEntero();

            switch (opcion) {
                case 1 -> agregarCita();
                case 2 -> buscarPorId();
                case 3 -> listarCitas();
                case 0 -> System.out.println("Saliendo del menú de citas...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void agregarCita() {
        System.out.println("\n--- Agregar Cita ---");
        int mascotaId = leerEntero("Ingrese ID de la mascota: ");
        System.out.print("Ingrese motivo: ");
        String motivo = input.nextLine();

        LocalDateTime fechaHora = controller.obtenerProximaFechaLibre();
        if (fechaHora == null) {
            System.out.println("No hay fechas disponibles.");
            return;
        }

        Veterinario vet = controller.obtenerVeterinarioDisponible(fechaHora);
        if (vet == null) {
            System.out.println("No hay veterinarios disponibles.");
            return;
        }

        Cita citaCreada = controller.agregarCita(mascotaId, fechaHora, motivo, 1, vet.getId());
        if (citaCreada != null) imprimirCita(citaCreada);
    }

    private void buscarPorId() {
        int id = leerEntero("Ingrese ID de la cita: ");
        Cita cita = controller.obtenerCitaPorId(id);
        if (cita != null) imprimirCita(cita);
        else System.out.println("No existe cita con ese ID.");
    }

    private void listarCitas() {
        List<Cita> citas = controller.obtenerTodasCitas();
        if (citas.isEmpty()) {
            System.out.println("No hay citas registradas.");
            return;
        }
        for (Cita c : citas) imprimirCita(c);
    }

    private void imprimirCita(Cita cita) {
        System.out.println("------ INFORMACION DE LA CITA ------");
        System.out.println("ID: " + cita.getId());
        System.out.println("Mascota ID: " + cita.getMascota_id());
        System.out.println("Fecha y Hora: " + cita.getFecha_hora());
        System.out.println("Motivo: " + cita.getMotivo());
        System.out.println("Estado ID: " + cita.getEstado_id());
        System.out.println("Veterinario ID: " + cita.getVeterinario_id());
        System.out.println("----------------------------------");
    }

    private int leerEntero() {
        while (true) {
            try {
                return Integer.parseInt(input.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida, ingrese un número: ");
            }
        }
    }

    private int leerEntero(String mensaje) {
        System.out.print(mensaje);
        return leerEntero();
    }
}
