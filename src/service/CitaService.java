package service;

import model.entities.Citas.Cita;
import model.entities.Veterinarios.Veterinario;
import model.repository.CitasDAO.CitaDAO;
import model.repository.CitasDAO.ICitaDAO;
import model.repository.VeterinariosDAO.IVeterinariosDAO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class CitaService {

    private final ICitaDAO citaDAO;
    private final IVeterinariosDAO veterinarioDAO;

    public CitaService(ICitaDAO citaDAO, IVeterinariosDAO veterinarioDAO) {
        this.citaDAO = citaDAO;
        this.veterinarioDAO = veterinarioDAO;
    }

    public Cita agregarCita(Cita cita) throws Exception {
        int idGenerado = citaDAO.agregarCita(cita);
        cita.setId(idGenerado);
        return cita;
    }

    public Cita obtenerCitaPorId(int id) {
        return citaDAO.obtenerPorId(id);
    }

    public List<Cita> obtenerTodasLasCitas() {
        return citaDAO.obtenerTodos();
    }

    public void actualizarCita(Cita cita) {
        citaDAO.actualizarCita(cita);
    }

    public void cancelarCita(int id) {
        citaDAO.cancelarCita(id);
    }

    public List<Veterinario> obtenerVeterinariosDisponibles(LocalDateTime fecha) {
        List<Veterinario> todos = veterinarioDAO.listarTodos();
        return todos.stream()
                .filter(v -> obtenerCitasPorVeterinario(v.getId()).stream()
                        .noneMatch(c -> c.getFecha_hora().equals(fecha)))
                .toList();
    }

    public Veterinario obtenerVeterinarioDisponible(LocalDateTime fecha) {
        List<Veterinario> libres = obtenerVeterinariosDisponibles(fecha);
        if (libres.isEmpty()) return null;
        int indiceRandom = ThreadLocalRandom.current().nextInt(libres.size());
        return libres.get(indiceRandom);
    }

    public LocalDateTime obtenerProximaFechaLibre() {
        LocalDateTime fechaBase = LocalDateTime.now().plusDays(1);
        int intentosMaximos = 30;

        for (int i = 0; i < intentosMaximos; i++) {
            int horaRandom = ThreadLocalRandom.current().nextInt(6, 21);
            LocalDateTime posibleFecha = fechaBase.plusDays(i).withHour(horaRandom)
                    .withMinute(0).withSecond(0).withNano(0);

            if (!obtenerVeterinariosDisponibles(posibleFecha).isEmpty()) {
                return posibleFecha;
            }
        }
        return null;
    }

    public List<Cita> obtenerCitasPorVeterinario(int veterinarioId) {
        return citaDAO.obtenerPorVeterinarioId(veterinarioId);
    }
}
