package view.cita;

import controller.citaController.CitaController;
import model.repository.CitasDAO.CitaDAO;
import model.repository.CitasDAO.ICitaDAO;

import java.util.Scanner;

public class MenuCita {
    private final ICitaDAO citaDAO;
    private final CitaController citaController;
    private final Scanner input;

    public MenuCita() {
        this.citaDAO = new CitaDAO();
        this.input = new Scanner(System.in);
        this.citaController = new CitaController(citaDAO, input);
    }

    public void mostrarMenuCita() {
        int opcion;
        do {
            System.out.print("""
                \n=== MENU CITA ===
                1. Ingresar una cita
                2. Obtener cita por ID
                3. Mostrar todas las citas
                4. Actualizar una cita
                5. Cancelar una cita por el ID
                6. Listar citas por mascota
                7. Listar citas por veterinario
                0. Salir del menú de citas
                Selecciona una opción: 
                """);
            opcion = input.nextInt();
            input.nextLine();

            switch (opcion) {
                case 1 -> citaController.agregarCita();
                case 2 -> citaController.buscarPorId();
                case 3 -> citaController.listarCitas();
                case 4 -> citaController.actualizarCita();
                case 5 -> citaController.cancelarCita();
                case 6 -> citaController.listarPorMascota();
                case 7 -> citaController.listarPorVeterinario();
                case 0 -> {
                    System.out.println("Saliendo del menú de citas...");
                    System.out.println("↩Volviendo al menú principal.");
                }
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }
}
