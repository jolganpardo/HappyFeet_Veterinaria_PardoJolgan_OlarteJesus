package controller.citaController;

import model.entities.Citas.Cita;
import model.entities.Citas.CitaEstado;
import model.entities.Veterinarios.Veterinario;
import model.repository.CitasDAO.CitaEstadoDAO;
import model.repository.VeterinariosDAO.IVeterinariosDAO;
import model.repository.VeterinariosDAO.VeterinarioDAO;
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

    public void imprimirCita(Cita cita) {
        if (cita == null) {
            System.out.println("No se encontró la cita.");
            return;
        }

        // DAO de veterinarios
        IVeterinariosDAO veterinarioDAO = new VeterinarioDAO();
        Veterinario vet = null;
        if (cita.getVeterinario_id() != null) {
            vet = veterinarioDAO.buscarPorId(cita.getVeterinario_id());
        }

        // DAO de estados
        CitaEstadoDAO estadoDAO = new CitaEstadoDAO();
        CitaEstado estado = null;
        if (cita.getEstado_id() != null) {
            estado = estadoDAO.obtenerPorId(cita.getEstado_id());
        }

        System.out.println("------ INFORMACION DE LA CITA ------");
        System.out.println("ID: " + cita.getId());
        System.out.println("Mascota ID: " + cita.getMascota_id());
        System.out.println("Fecha y Hora: " + cita.getFecha_hora());
        System.out.println("Motivo: " + cita.getMotivo());
        if (estado != null) {
            System.out.println("Estado: " + estado.getNombre());
        } else {
            System.out.println("Estado: No asignado");
        }
        System.out.println("Veterinario ID: " + cita.getVeterinario_id());
        if (vet != null) {
            System.out.println("Veterinario: " + vet.getNombre_completo());
        } else {
            System.out.println("Veterinario: No asignado");
        }
        System.out.println("----------------------------------");
    }
}
