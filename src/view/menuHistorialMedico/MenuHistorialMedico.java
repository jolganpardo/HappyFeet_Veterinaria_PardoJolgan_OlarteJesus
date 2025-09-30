package view.menuHistorialMedico;

import controller.historialMedicoController.HistorialMedicoController;
import controller.mascotaController.MascotaController;
import model.repository.HistorialMedicoDAO.HistorialMedicoDAO;
import model.repository.HistorialMedicoDAO.IHistorialMedicoDAO;
import model.repository.MascotasDAO.IMascotasDAO;
import model.repository.VeterinariosDAO.IVeterinariosDAO;
import model.repository.VeterinariosDAO.VeterinarioDAO;
import service.HistorialMedicoService;
import service.MascotaService;

import java.util.Scanner;

public class MenuHistorialMedico {
    private IHistorialMedicoDAO iHistorialMedicoDAO;
    private IVeterinariosDAO iVeterinariosDAO;
    private HistorialMedicoService historialMedicoService;
    private HistorialMedicoController historialMedicoController;
    private Scanner input;

    public MenuHistorialMedico() {
        this.iHistorialMedicoDAO = new HistorialMedicoDAO();
        this.iVeterinariosDAO = new VeterinarioDAO();
        this.historialMedicoService = new HistorialMedicoService(iHistorialMedicoDAO, iVeterinariosDAO);
        this.historialMedicoController = new HistorialMedicoController(historialMedicoService);
        this.input = new Scanner(System.in);
    }

    public void mostrarMenuHistorialMedico() {
        int opcion;
        do {
            System.out.print("""
                    \n===== MENÚ HISTORIAL MÉDICO =====
                    1. Agregar historial médico.
                    2. Ver historial por ID.
                    3. Ver todos los historiales.
                    4. Ver historiales por mascota.
                    5. Actualizar historial médico.
                    6. Eliminar historial médico
                    0. Salir
                    >>>Seleccione una opción:\s 
                    """);

            opcion = input.nextInt();
            input.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1 -> historialMedicoController.agregarHistorial();
                case 2 -> historialMedicoController.obtenerHistorialPorId();
                case 3 -> historialMedicoController.obtenerTodos();
                case 4 -> historialMedicoController.obtenerPorMascota();
                case 5 -> historialMedicoController.actualizarHistorial();
                case 6 -> historialMedicoController.eliminarHistorial();
                case 0 -> System.out.println("Saliendo... del menu de Historial Medico..");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }
}
