package view;

import view.cita.MenuCita;
import view.dueno.MenuDueno;
import view.mascota.MenuMascota;
import view.proveedor.MenuProveedor;
import view.veterinario.MenuVeterinario;

import java.util.Scanner;

public class MenuPrincipal {

    private final Scanner scanner;

    public MenuPrincipal() {
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion;

        do {
            System.out.print("""
                    \n===== MENÚ PRINCIPAL - HAPPY FEET =====
                    1. Gestionar Dueños
                    2. Gestionar Mascotas
                    3. Gestionar Citas
                    4. Gestionar Veterinarios
                    5. Gestionar Inventario
                    6. Gestionar Facturación
                    7. Gestionar Proveedores
                    0. Salir
                    >>Ingrese su opcion:""");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    MenuDueno menuDueno = new MenuDueno();
                    menuDueno.mostrarMenuDueno();
                    break;
                case 2:
                    MenuMascota menuMascota = new MenuMascota();
                    menuMascota.mostrarMenuMascota();
                    break;
                case 3:
                    MenuCita menuCita = new MenuCita();
                    menuCita.mostrarMenuCita();
                    break;
                case 4:
                    MenuVeterinario menuVeterinario = new MenuVeterinario();
                    menuVeterinario.mostrarMenuVeterinario();
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    MenuProveedor menuProveedor = new MenuProveedor();
                    menuProveedor.mostrarMenuProveedor();
                    break;
                case 0:
                    System.out.println("Saliendo... Hasta pronto.");
                    break;
                default:
                    System.out.println("Opcion no valida.");

            }
        } while (opcion != 0);
    }
}
