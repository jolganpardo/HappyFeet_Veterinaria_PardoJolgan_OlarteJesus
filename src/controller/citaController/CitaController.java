package controller.citaController;

import model.entities.Citas.Cita;
import model.entities.Veterinarios.Veterinario;
import service.CitaService;

import java.time.LocalDateTime;
import java.util.List;

public class CitaController {

    private final CitaService citaService;

    // Constructor principal: se pasa el service
    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    public Cita agregarCita(int mascotaId, LocalDateTime fechaHora, String motivo, int estadoId, int veterinarioId) {
        Cita cita = new Cita(null, mascotaId, fechaHora, motivo, estadoId, veterinarioId);
        try {
            return citaService.agregarCita(cita);
        } catch (Exception e) {
            System.out.println("Error al agregar cita: " + e.getMessage());
            return null;
        }
    }

    public Cita obtenerCitaPorId(int id) {
        return citaService.obtenerCitaPorId(id);
    }

    public List<Cita> obtenerTodasCitas() {
        return citaService.obtenerTodasLasCitas();
    }

    public void actualizarCita(Cita cita) {
        try {
            citaService.actualizarCita(cita);
        } catch (Exception e) {
            System.out.println("Error al actualizar cita: " + e.getMessage());
        }
    }

    public void cancelarCita(int id) {
        try {
            citaService.cancelarCita(id);
        } catch (Exception e) {
            System.out.println("Error al cancelar cita: " + e.getMessage());
        }
    }

    public List<Veterinario> obtenerVeterinariosDisponibles(LocalDateTime fecha) {
        return citaService.obtenerVeterinariosDisponibles(fecha);
    }

    public Veterinario obtenerVeterinarioDisponible(LocalDateTime fecha) {
        return citaService.obtenerVeterinarioDisponible(fecha);
    }

    public LocalDateTime obtenerProximaFechaLibre() {
        return citaService.obtenerProximaFechaLibre();
    }

    public List<Cita> obtenerCitasPorVeterinario(int vetId) {
        return citaService.obtenerCitasPorVeterinario(vetId);
    }
}
