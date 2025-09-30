package controller.citaController;

import model.entities.Citas.Cita;
import model.repository.CitasDAO.CitaDAO;
import model.repository.CitasDAO.ICitaDAO;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

public class CitaController {
    private final ICitaDAO citaDAO;

    public CitaController() {
        this.citaDAO = new CitaDAO();
    }

    public void agregarCita(Integer mascotaId, LocalDateTime fechaHora, String motivo,
                            Integer estadoId, Integer veterinarioId) {
        try {
            if (mascotaId == null || fechaHora == null || motivo == null || motivo.isBlank()) {
                throw new IllegalArgumentException("Datos inválidos: mascota, fecha y motivo son obligatorios.");
            }
            Cita cita = new Cita(null, mascotaId, fechaHora, motivo, estadoId, veterinarioId);
            citaDAO.agregarCita(cita);
            System.out.println("Cita agregada con éxito.");
        } catch (Exception e) {
            System.out.println("Error al agregar cita: " + e.getMessage());
        }
    }

    public Cita obtenerCitaPorId(Integer id) {
        try {
            if (id == null || id <= 0) throw new IllegalArgumentException("ID inválido.");
            return citaDAO.obtenerPorId(id);
        } catch (Exception e) {
            System.out.println("Error al obtener la cita: " + e.getMessage());
            return null;
        }
    }

    public List<Cita> obtenerTodasLasCitas() {
        try {
            return citaDAO.obtenerTodos();
        } catch (Exception e) {
            System.out.println("Error al listar las citas: " + e.getMessage());
            return List.of();
        }
    }

    public void actualizarCita(Cita cita) {
        try {
            if (cita.getId() == null || cita.getId() <= 0) {
                throw new IllegalArgumentException("El ID de la cita es obligatorio para actualizar.");
            }
            citaDAO.actualizarCita(cita);
            System.out.println("Cita actualizada con éxito.");
        } catch (Exception e) {
            System.out.println("Error al actualizar cita: " + e.getMessage());
        }
    }

    public void cancelarCita(Integer id) {
        try {
            if (id == null || id <= 0) throw new IllegalArgumentException("ID inválido.");
            citaDAO.cancelarCita(id);
            System.out.println("Cita cancelada con éxito.");
        } catch (Exception e) {
            System.out.println("Error al cancelar la cita: " + e.getMessage());
        }
    }

    public List<Cita> obtenerCitasPorMascota(Integer mascotaId) {
        try {
            return citaDAO.obtenerPorMascotaId(mascotaId);
        } catch (Exception e) {
            System.out.println("Error al obtener citas de la mascota: " + e.getMessage());
            return List.of();
        }
    }

    public List<Cita> obtenerCitasPorVeterinario(Integer veterinarioId) {
        try {
            return citaDAO.obtenerPorVeterinarioId(veterinarioId);
        } catch (Exception e) {
            System.out.println("Error al obtener citas del veterinario: " + e.getMessage());
            return List.of();
        }
    }
}
