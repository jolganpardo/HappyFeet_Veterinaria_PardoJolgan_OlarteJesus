package controller.historialMedicoController;

import model.entities.Historial_Medico.HistorialMedico;
import model.repository.HistorialMedicoDAO.HistorialMedicoDAO;
import model.repository.VeterinariosDAO.VeterinarioDAO;
import service.HistorialMedicoService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class HistorialMedicoController {
    private final HistorialMedicoService service;
    private final Scanner scanner;

    public HistorialMedicoController(HistorialMedicoService service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    public void agregarHistorial() {
        try {
            System.out.print("ID de la mascota: ");
            int mascotaId = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            System.out.print("Descripción: ");
            String desc = scanner.nextLine();

            System.out.print("Diagnóstico: ");
            String diag = scanner.nextLine();

            System.out.print("Tratamiento recomendado: ");
            String trat = scanner.nextLine();

            // Ahora veterinario_id, fecha_evento y evento_tipo se generan automáticamente
            HistorialMedico historial = new HistorialMedico(
                    null, // ID autogenerado
                    mascotaId,
                    null, // veterinario automático
                    null, // fecha automática
                    null, // tipo evento automático
                    desc,
                    diag,
                    trat
            );

            service.agregarHistorial(historial);
            System.out.println("Historial agregado con éxito.");
        } catch (Exception e) {
            System.out.println("Error al agregar historial: " + e.getMessage());
        }
    }

    public void obtenerHistorialPorId() {
        System.out.print("Ingrese el ID del historial: ");
        int id = scanner.nextInt();
        HistorialMedico historial = service.obtenerHistorialPorId(id);
        if (historial != null) {
            System.out.println(historial);
        } else {
            System.out.println("No se encontró historial con ese ID.");
        }
    }

    public void obtenerTodos() {
        List<HistorialMedico> historiales = service.obtenerTodosLosHistoriales();
        historiales.forEach(System.out::println);
    }

    public void obtenerPorMascota() {
        System.out.print("Ingrese el ID de la mascota: ");
        int id = scanner.nextInt();
        List<HistorialMedico> historiales = service.obtenerHistorialPorMascota(id);
        if (historiales.isEmpty()) {
            System.out.println("No hay historiales para esta mascota.");
        } else {
            historiales.forEach(System.out::println);
        }
    }

    public void actualizarHistorial() {
        try {
            System.out.print("Ingrese el ID del historial a actualizar: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            HistorialMedico historial = service.obtenerHistorialPorId(id);
            if (historial == null) {
                System.out.println("Historial no encontrado.");
                return;
            }

            System.out.print("Nueva descripción: ");
            historial.setDescripcion(scanner.nextLine());
            System.out.print("Nuevo diagnóstico: ");
            historial.setDiagnostico(scanner.nextLine());
            System.out.print("Nuevo tratamiento recomendado: ");
            historial.setTratamiento_recomendado(scanner.nextLine());

            service.actualizarHistorial(historial);
            System.out.println("Historial actualizado.");
        } catch (Exception e) {
            System.out.println("Error al actualizar: " + e.getMessage());
        }
    }

    public void eliminarHistorial() {
        System.out.print("Ingrese el ID del historial a eliminar: ");
        int id = scanner.nextInt();
        service.eliminarHistorial(id);
        System.out.println("Historial eliminado.");
    }
}
