package controller.citaController;

import model.entities.Citas.Cita;
import model.entities.Duenos.Dueno;
import model.repository.CitasDAO.ICitaDAO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class CitaController {
    private final ICitaDAO citaDAO;
    private final Scanner input;

    public CitaController(ICitaDAO citaDAO, Scanner input) {
        this.citaDAO = citaDAO;
        this.input = input;
    }

    public void agregarCita() {
        try {
            System.out.print("ID de la mascota: ");
            Integer mascotaId = input.nextInt();
            input.nextLine();

            System.out.print("Fecha y hora (yyyy-MM-dd HH:mm): ");
            String fechaStr = input.nextLine();
            LocalDateTime fechaHora = LocalDateTime.parse(fechaStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

            System.out.print("Motivo: ");
            String motivo = input.nextLine();

            System.out.print("ID del estado (ej: 1=Programada, 2=En Proceso, 3=Finalizada 4=Cancelada, 5=No Asistió): ");
            Integer estadoId = input.nextInt();
            input.nextLine();

            System.out.print("ID del veterinario: ");
            Integer veterinarioId = input.nextInt();

            Cita nueva = new Cita(null, mascotaId, fechaHora, motivo, estadoId, veterinarioId);
            citaDAO.agregarCita(nueva);
            System.out.println("Cita agregada con éxito.");
        } catch (Exception e) {
            System.out.println("Error al agregar cita: " + e.getMessage());
        }
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
}
