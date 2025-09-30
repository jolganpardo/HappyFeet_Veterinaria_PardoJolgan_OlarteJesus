package view.cita;

import controller.citaController.CitaController;
import model.entities.Citas.Cita;
import model.repository.CitasDAO.CitaDAO;
import model.repository.CitasDAO.ICitaDAO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class MenuCita {
    private ICitaDAO citaDAO;
    private CitaController controller;
    private Scanner input;

    public MenuCita() {
        this.citaDAO = new CitaDAO();
        this.input = new Scanner(System.in);
        this.controller = new CitaController();
    }


    public void mostrarMenuCita(){
        int opcion;
        do {
            System.out.print("""
                \n=== MENU CITA ===
                1. Ingresar una cita.
                2. Obtener cita por ID.
                3. Mostrar todas las citas.
                4. Actualizar una cita.
                5. Cancelar una cita por el ID
                0. Salir del menu de citas.
                Selecciona una opción:
                """);
            opcion = input.nextInt();
            input.nextLine();

            switch (opcion){
                case 1:
                    agregarCita();
                    break;
                case 2:
                    buscarPorId();
                    break;
                case 3:
                    listarCitas();
                    break;
                case 4:
                    actualizarCita();
                    break;
                case 5:
                    cancelarCita();
                    break;
                case 0:
                    System.out.println("Saliendo... del menu de citas..");
                    System.out.println("Volviendo al menu principal");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        }while (opcion != 0);
    }

    public void agregarCita() {
        System.out.println("\n--- Agregar Cita ---");
        int mascotaId = leerEntero("Ingrese ID de la mascota: ");
        LocalDateTime fechaHora = leerFecha("Ingrese fecha y hora (yyyy-MM-dd HH:mm): ");
        System.out.print("Ingrese motivo: ");
        String motivo = input.nextLine();
        int estadoId = leerEntero("Ingrese ID de estado (opcional, 0 si no aplica): ");
        int veterinarioId = leerEntero("Ingrese ID de veterinario (opcional, 0 si no aplica): ");

        controller.agregarCita(mascotaId,
                fechaHora,
                motivo,
                estadoId != 0 ? estadoId : null,
                veterinarioId != 0 ? veterinarioId : null
        );
    }

    public void buscarPorId() {
        System.out.print("ID de la cita: ");
        int id = input.nextInt();
        input.nextLine();
        Cita cita = citaDAO.obtenerPorId(id);
        if (cita != null) {
            System.out.println("🔎Cita encontrada: " + cita);
        } else {
            System.out.println("No existe cita con ese ID.");
        }
    }

    public void listarCitas() {
        List<Cita> citas = citaDAO.obtenerTodos();
        System.out.println("\n📋 Lista de citas:");
        for (Cita c : citas) {
            System.out.println(c);
        }
    }

    public void actualizarCita() {
        System.out.print("ID de la cita a actualizar: ");
        int id = input.nextInt();
        input.nextLine();
        Cita cita = citaDAO.obtenerPorId(id);
        if (cita != null) {
            System.out.print("Nuevo motivo (" + cita.getMotivo() + "): ");
            String nuevoMotivo = input.nextLine();
            if (!nuevoMotivo.isEmpty()) cita.setMotivo(nuevoMotivo);

            System.out.print("Nueva fecha y hora (yyyy-MM-dd HH:mm) (" + cita.getFecha_hora() + "): ");
            String nuevaFecha = input.nextLine();
            if (!nuevaFecha.isEmpty()) {
                cita.setFecha_hora(LocalDateTime.parse(nuevaFecha, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            }

            System.out.print("Nuevo estado ID (" + cita.getEstado_id() + "): ");
            String nuevoEstado = input.nextLine();
            if (!nuevoEstado.isEmpty()) cita.setEstado_id(Integer.parseInt(nuevoEstado));

            citaDAO.actualizarCita(cita);
            System.out.println("Cita actualizada con éxito.");
        } else {
            System.out.println("No existe cita con ese ID.");
        }
    }

    public void cancelarCita() {
        System.out.print("ID de la cita a cancelar: ");
        int id = input.nextInt();
        input.nextLine();
        Cita cita = citaDAO.obtenerPorId(id);
        if (cita != null) {
            citaDAO.cancelarCita(id);
            System.out.println("🛑Cita cancelada con éxito.");
        } else {
            System.out.println("No existe cita con ese ID.");
        }
    }

    public void listarPorMascota() {
        System.out.print("ID de la mascota: ");
        int mascotaId = input.nextInt();
        input.nextLine();
        List<Cita> citas = citaDAO.obtenerPorMascotaId(mascotaId);
        System.out.println("\n📋 Citas de la mascota:");
        for (Cita c : citas) {
            System.out.println(c);
        }
    }

    public void listarPorVeterinario() {
        System.out.print("ID del veterinario: ");
        int vetId = input.nextInt();
        input.nextLine();
        List<Cita> citas = citaDAO.obtenerPorVeterinarioId(vetId);
        System.out.println("\n📋 Citas del veterinario:");
        for (Cita c : citas) {
            System.out.println(c);
        }
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

    private LocalDateTime leerFecha(String mensaje) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        while (true) {
            System.out.print(mensaje);
            String entrada = input.nextLine();
            try {
                return LocalDateTime.parse(entrada, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Formato inválido. Use: yyyy-MM-dd HH:mm");
            }
        }
    }
}