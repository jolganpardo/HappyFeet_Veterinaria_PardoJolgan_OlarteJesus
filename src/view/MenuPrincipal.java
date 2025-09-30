package view;

import controller.citaController.CitaController;
import model.repository.CitasDAO.CitaDAO;
import model.repository.InventarioDAO.ProveedorDAO;
import model.repository.VeterinariosDAO.VeterinarioDAO;
import service.CitaService;
import view.cita.MenuCita;
import view.dueno.MenuDueno;
import view.factura.MenuFactura;
import view.inventario.MenuInventario;
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
                    CitaService citaService = new CitaService(new CitaDAO(), new VeterinarioDAO());
                    CitaController citaController = new CitaController(citaService);
                    MenuCita menuCita = new MenuCita(citaController);
                    menuCita.mostrarMenuCita();
                    break;

                case 4:
                    MenuVeterinario menuVeterinario = new MenuVeterinario(scanner, new VeterinarioDAO());
                    menuVeterinario.mostrarMenuVeterinario();
                    break;
                case 5:
                    MenuInventario menuInventario = new MenuInventario();
                    menuInventario.mostrarMenu();
                    break;
                case 6:
                    MenuFactura menuFactura = new MenuFactura();
                    menuFactura.mostrarMenuFactura();
                    break;
                case 7:
                    MenuProveedor menuProveedor = new MenuProveedor(scanner, new ProveedorDAO());
                    menuProveedor.mostrarMenu();
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
