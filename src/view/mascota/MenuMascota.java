package view.mascota;

import controller.mascotaController.MascotaController;
import model.repository.MascotasDAO.IMascotasDAO;
import model.repository.MascotasDAO.MascotaDAO;

import java.util.Scanner;

public class MenuMascota {
    private IMascotasDAO mascotasDAO;
    private MascotaController mascotaController;
    private Scanner input;

    public MenuMascota() {
        this.mascotasDAO = new MascotaDAO();
        this.input = new Scanner(System.in);
        this.mascotaController = new MascotaController(mascotasDAO, input);
    }

    public void mostrarMenuMascota() {
        int opcion;
        do {
            System.out.print("""
                    \n === MENU MASCOTA ===
                    1. Agregar Mascota.
                    2. Mostrar mascota por ID.
                    3. Mostrar todas las mascotas.
                    4. Actualizar mascota.
                    5. Cambiar estado de la mascota (Activa, Inactiva).
                    6. Mascota por ID del dueño.
                    7. Mostrar mascota por raza.
                    >>> Ingrese la opcion:""");
            opcion = input.nextInt();
            input.nextLine();

            switch (opcion){
                case 1:
                    mascotaController.agregarMascota();
                    break;
                case 2:
                    mascotaController.buscarPorId();
                    break;
                case 3:
                    mascotaController.listarMascotas();
                    break;
                case 4:
                    mascotaController.actualizarMascota();
                    break;
                case 5:
                    mascotaController.cambiarEstadoMascota();
                    break;
                case 6:
                    mascotaController.obtenerPorDuenoId();
                    break;
                case 7:
                    mascotaController.obtenerPorRazaId();
                    break;
                case 0:
                    System.out.println("Saliendo... del menu de mascotas..");
                    System.out.println("Volviendo al menu principal");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        }while (opcion != 0);
    }
}
