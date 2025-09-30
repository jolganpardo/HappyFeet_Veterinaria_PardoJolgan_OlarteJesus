package view.veterinario;

import controller.veterinarioController.VeterinarioController;
import model.repository.VeterinariosDAO.IVeterinariosDAO;
import service.VeterinarioService;
import util.Validaciones;

import java.util.Scanner;

public class MenuVeterinario {
    private Scanner input;
    private VeterinarioController veterinarioController;

    public MenuVeterinario(Scanner input, IVeterinariosDAO veterinarioDAO) {
        this.input = input;
        VeterinarioService veterinarioService = new VeterinarioService(veterinarioDAO);
        this.veterinarioController = new VeterinarioController(veterinarioService, input);
    }

    public void mostrarMenuVeterinario() {
        int opcion;
        do {
            System.out.print("""
                \n=== MENU VETERINARIO ===
                1. Agregar un veterinario.
                2. Actualizar datos de un veterinario por ID.
                3. Eliminar un veterinario por ID.
                4. Buscar veterinario por ID.
                5. Listar todos los veterinarios.
                0. Salir del menú de veterinario.
                Ingrese una opción:\s""");
            opcion = Validaciones.leerEntero(input);

            switch (opcion) {
                case 1:
                    veterinarioController.agregarVeterinario();
                    break;
                case 2:
                    veterinarioController.actualizarVeterinario();
                    break;
                case 3:
                    veterinarioController.eliminarVeterinario();
                    break;
                case 4:
                    veterinarioController.buscarVeterinarioPorId();
                    break;
                case 5:
                    veterinarioController.listarVeterinarios();
                    break;
                case 0:
                    System.out.println("Saliendo del menú de veterinarios...");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (opcion != 0);
    }
}