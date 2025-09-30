package service;

import model.entities.Historial_Medico.HistorialMedico;
import model.entities.Veterinarios.Veterinario;
import model.repository.HistorialMedicoDAO.IHistorialMedicoDAO;
import model.repository.VeterinariosDAO.IVeterinariosDAO;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class HistorialMedicoService {

    private final IHistorialMedicoDAO historialDAO;
    private final IVeterinariosDAO veterinarioDAO;

    public HistorialMedicoService(IHistorialMedicoDAO historialDAO, IVeterinariosDAO veterinarioDAO) {
        this.historialDAO = historialDAO;
        this.veterinarioDAO = veterinarioDAO;
    }

    public void agregarHistorial(HistorialMedico historial) throws Exception {
        // --- Veterinario ---
        if (historial.getVeterinario_id() == null) {
            List<Veterinario> veterinarios = veterinarioDAO.listarTodos();
            if (veterinarios.isEmpty()) {
                throw new Exception("No hay veterinarios registrados en el sistema.");
            }
            int randomIndex = ThreadLocalRandom.current().nextInt(veterinarios.size());
            historial.setVeterinario_id(veterinarios.get(randomIndex).getId());
        }

        Veterinario vet = veterinarioDAO.buscarPorId(historial.getVeterinario_id());
        if (vet == null) {
            throw new Exception("El veterinario con ID " + historial.getVeterinario_id() + " no existe.");
        }

        // --- Fecha del evento ---
        if (historial.getFecha_evento() == null) {
            LocalDate startDate = LocalDate.of(2025, 1, 1);
            LocalDate endDate = LocalDate.now(); // hasta hoy
            long start = startDate.toEpochDay();
            long end = endDate.toEpochDay();
            long randomDay = ThreadLocalRandom.current().nextLong(start, end + 1);
            historial.setFecha_evento(LocalDate.ofEpochDay(randomDay));
        }

        // --- Evento tipo ---
        if (historial.getEvento_tipo_id() == null) {
            int randomTipo = ThreadLocalRandom.current().nextInt(1, 4); // ajusta el rango según tu tabla
            historial.setEvento_tipo_id(randomTipo);
        }

        // --- Guardar en BD ---
        historialDAO.agregarHistorial(historial);
    }


    public HistorialMedico obtenerHistorialPorId(Integer id) {
        return historialDAO.obtenerPorId(id);
    }

    public List<HistorialMedico> obtenerTodosLosHistoriales() {
        return historialDAO.obtenerTodos();
    }

    public List<HistorialMedico> obtenerHistorialPorMascota(Integer mascotaId) {
        return historialDAO.obtenerPorMascotaId(mascotaId);
    }

    public void actualizarHistorial(HistorialMedico historial) throws Exception {
        if (historial.getVeterinario_id() != null) {
            Veterinario vet = veterinarioDAO.buscarPorId(historial.getVeterinario_id());
            if (vet == null) {
                throw new Exception("El veterinario con ID " + historial.getVeterinario_id() + " no existe.");
            }
        }
        historialDAO.actualizarHistorial(historial);
    }

    public void eliminarHistorial(Integer id) {
        historialDAO.eliminarHistorial(id);
    }
}
