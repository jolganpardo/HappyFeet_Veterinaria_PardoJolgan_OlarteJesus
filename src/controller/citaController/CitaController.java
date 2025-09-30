package controller.citaController;

import model.entities.Citas.Cita;
import model.entities.Duenos.Dueno;
import model.repository.CitasDAO.CitaDAO;
import model.repository.CitasDAO.ICitaDAO;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import model.entities.Veterinarios.Veterinario;
import model.repository.VeterinariosDAO.VeterinarioDAO;
import model.repository.VeterinariosDAO.IVeterinariosDAO;

public class CitaController {
    private final ICitaDAO citaDAO;
    private final IVeterinariosDAO veterinarioDAO;

    public CitaController(IVeterinariosDAO veterinarioDAO) {
        this.veterinarioDAO = veterinarioDAO;
        this.citaDAO = new CitaDAO();
    }

    public LocalDateTime obtenerProximaFechaLibre() {
        LocalDateTime fechaBase = LocalDateTime.now().plusDays(1); // empieza al día siguiente
        int intentosMaximos = 30; // buscar hasta 30 días adelante

        for (int i = 0; i < intentosMaximos; i++) {
            // Generar hora aleatoria entre 6 y 20
            int horaRandom = ThreadLocalRandom.current().nextInt(6, 21);
            LocalDateTime posibleFecha = fechaBase.plusDays(i).withHour(horaRandom).withMinute(0).withSecond(0).withNano(0);

            // Revisar si hay veterinarios disponibles en esa fecha
            List<Veterinario> vetsLibres = obtenerVeterinariosDisponibles(posibleFecha);
            if (!vetsLibres.isEmpty()) {
                return posibleFecha; // retornar la primera fecha y hora libre
            }
        }

        return null; // No hay fecha disponible
    }

    // Devuelve la lista de veterinarios libres en una fecha
    public List<Veterinario> obtenerVeterinariosDisponibles(LocalDateTime fecha) {
        List<Veterinario> todos = veterinarioDAO.listarTodos();
        return todos.stream()
                .filter(v -> obtenerCitasPorVeterinario(v.getId()).stream()
                        .noneMatch(c -> c.getFecha_hora().equals(fecha)))
                .toList();
    }

    // Devuelve un solo veterinario libre
    public Veterinario obtenerVeterinarioDisponible(LocalDateTime fecha) {
        List<Veterinario> libres = obtenerVeterinariosDisponibles(fecha);
        if (libres.isEmpty()) {
            return null;
        }
        // Elegir un veterinario al azar
        int indiceRandom = ThreadLocalRandom.current().nextInt(libres.size());
        return libres.get(indiceRandom);
    }


    public Cita agregarCita(Integer mascotaId, LocalDateTime fechaHora, String motivo,
                            Integer estadoId, Integer veterinarioId) {
        try {
            if (mascotaId == null || fechaHora == null || motivo == null || motivo.isBlank()) {
                throw new IllegalArgumentException("Datos inválidos: mascota, fecha y motivo son obligatorios.");
            }

            Cita cita = new Cita(null, mascotaId, fechaHora, motivo, estadoId, veterinarioId);

            // Guardar y obtener el ID generado
            int idGenerado = citaDAO.agregarCita(cita);
            cita.setId(idGenerado);


            return cita;

        } catch (Exception e) {
            System.out.println("Error al agregar cita: " + e.getMessage());
            e.printStackTrace();
            return null;
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

    public List<Cita> obtenerCitasPorVeterinario(Integer veterinarioId) {
        try {
            return citaDAO.obtenerPorVeterinarioId(veterinarioId);
        } catch (Exception e) {
            System.out.println("Error al obtener citas del veterinario: " + e.getMessage());
            return List.of();
        }
    }
}