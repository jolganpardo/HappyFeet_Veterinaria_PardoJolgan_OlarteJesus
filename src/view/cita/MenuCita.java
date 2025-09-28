package view.cita;

import controller.citaController.CitaController;
import model.repository.CitasDAO.CitaDAO;
import model.repository.CitasDAO.ICitaDAO;

import java.util.Scanner;

public class MenuCita {
    private ICitaDAO citaDAO;
    private CitaController citaController;
    private Scanner input;

    public MenuCita() {
        this.citaDAO = new CitaDAO();
        this.input = new Scanner(System.in);
        this.citaController = new CitaController(citaDAO, input);
    }

    public void mostrarMenuCita(){
        int opcion;
        do {
            System.out.print("""
                \n=== MENU CITA ===
                1. Ingresar una cita.
                2. Obtener cita por ID.
                3. Mostrar todas las citas.
                4. Actualizar una cita.
                5. Cancelar una cita por el ID
                0. Salir del menu de citas.
                Selecciona una opción:
                """);
            opcion = input.nextInt();
            input.nextLine();

            switch (opcion){
                case 1:
                    citaController.agregarCita();
                    break;
                case 2:
                    citaController.buscarPorId();
                    break;
                case 3:
                    citaController.listarCitas();
                    break;
                case 4:
                    citaController.actualizarCita();
                    break;
                case 5:
                    citaController.cancelarCita();
                    break;
                case 0:
                    System.out.println("Saliendo... del menu de citas..");
                    System.out.println("Volviendo al menu principal");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        }while (opcion != 0);
        input.close();
    }
}
