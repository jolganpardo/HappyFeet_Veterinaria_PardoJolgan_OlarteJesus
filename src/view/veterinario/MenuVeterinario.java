package view.veterinario;

import controller.veterinarioController.VeterinarioController;
import model.repository.VeterinariosDAO.IVeterinariosDAO;
import model.repository.VeterinariosDAO.VeterinarioDAO;

import java.util.Scanner;

public class MenuVeterinario {
    private Scanner input;
    private IVeterinariosDAO veterinariosDAO;
    private VeterinarioController veterinarioController;

    public MenuVeterinario() {
        this.input = new Scanner(System.in);
        this.veterinariosDAO = new VeterinarioDAO();
        this.veterinarioController = new VeterinarioController(veterinariosDAO, input);
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
                Ingrese una opción:
                """);
            opcion = input.nextInt();
            input.nextLine();

            switch (opcion) {
                case 1 -> veterinarioController.agregarVeterinario();
                case 2 -> veterinarioController.actualizarVeterinario();
                case 3 -> veterinarioController.eliminarVeterinario();
                case 4 -> veterinarioController.buscarPorId();
                case 5 -> veterinarioController.listarVeterinarios();
                case 0 -> System.out.println("Saliendo del menú de veterinarios...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }
}
