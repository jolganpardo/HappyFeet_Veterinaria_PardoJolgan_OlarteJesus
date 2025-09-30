package controller.historialMedicoController;

import model.entities.Historial_Medico.HistorialMedico;
import model.entities.Mascotas.Mascota;
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
            imprimirHistorialMedico(historial);
        } catch (Exception e) {
            System.out.println("Error al agregar historial: " + e.getMessage());
        }
    }

    public void obtenerHistorialPorId() {
        System.out.print("Ingrese el ID del historial: ");
        int id = scanner.nextInt();
        HistorialMedico historial = service.obtenerHistorialPorId(id);
        if (historial != null) {
            imprimirHistorialMedico(historial);
        } else {
            System.out.println("No se encontró historial con ese ID.");
        }
    }

    public void obtenerTodos() {
        List<HistorialMedico> historiales = service.obtenerTodosLosHistoriales();
        if (historiales.isEmpty()) {
            System.out.println("No hay historiales registrados.");
        } else {
            for (HistorialMedico historial : historiales) {
                imprimirHistorialMedico(historial);
            }
        }
    }


    public void obtenerPorMascota() {
        System.out.print("Ingrese el ID de la mascota: ");
        int id = scanner.nextInt();
        List<HistorialMedico> historiales = service.obtenerHistorialPorMascota(id);
        if (historiales.isEmpty()) {
            System.out.println("No hay historiales para esta mascota.");
        } else {
            historiales.forEach(this::imprimirHistorialMedico);
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

    private void imprimirHistorialMedico(HistorialMedico historial) {
        if (historial == null) {
            System.out.println("No se encontró la mascota.");
            return;
        }
        System.out.println("------ INFORMACIÓN DE HISTORIAL MEDICO ------");
        System.out.println("ID: " + historial.getId());
        System.out.println("ID Mascota: " + historial.getMascota_id());
        System.out.println("Veterinario ID: " + historial.getVeterinario_id());
        System.out.println("Fecha Evento: " + historial.getFecha_evento());
        System.out.println("Evento Tipo ID: " + historial.getEvento_tipo_id());
        System.out.println("Descripcion: " + historial.getDescripcion());
        System.out.println("Diagnostico: " + historial.getDiagnostico());
        System.out.println("Tratamiento Recomendado: " + historial.getTratamiento_recomendado());
        System.out.println("--------------------------------------");
    }
}
