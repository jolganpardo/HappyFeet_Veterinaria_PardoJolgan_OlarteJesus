package view.dueno;

import controller.duenoController.DuenoController;
import model.repository.duenosDAO.DuenoDAO;
import model.repository.duenosDAO.IDuenosDAO;

import java.util.Scanner;

public class MenuDueno {
    private IDuenosDAO duenoDAO; // Usamos la interfaz
    private DuenoController duenoController;
    private Scanner input;

    public MenuDueno() {
        this.duenoDAO = new DuenoDAO();
        this.input = new Scanner(System.in);
        this.duenoController = new DuenoController(duenoDAO, input);
    }

    public void mostrarMenuDueno() {
        int opcion;
        do {
            System.out.print("""
                \n=== MENU DUEÑO ===
                1. Insertar nuevo dueño.
                2. Buscar dueño por ID.
                3. Listar todos los dueños.
                4. Actualizar dueño.
                5. Eliminar dueño.
                0. Salir del menú de dueño.
                Selecciona una opción:
                """);
            opcion = input.nextInt();
            input.nextLine();

            switch (opcion) {
                case 1:
                    duenoController.agregarDueno();
                    break;
                case 2:
                    duenoController.buscarPorId();
                    break;
                case 3:
                    duenoController.listarDuenos();
                    break;
                case 4:
                    duenoController.actualizarDueno();
                    break;
                case 5:
                    duenoController.eliminarDueno();
                    break;
                case 0:
                    System.out.println("Saliendo... del menu de dueños..");
                    System.out.println("Volviendo al menu principal");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        } while (opcion != 0);
    }
}
