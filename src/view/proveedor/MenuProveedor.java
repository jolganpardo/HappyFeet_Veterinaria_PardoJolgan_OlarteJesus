package view.proveedor;

import controller.proveedorController.ProveedorController;
import model.repository.InventarioDAO.IProveedorDAO;
import service.ProveedorService;
import util.Validaciones;

import java.util.Scanner;

public class MenuProveedor {
    private Scanner input;
    private ProveedorController proveedorController;

    public MenuProveedor(Scanner input, IProveedorDAO proveedorDAO) {
        this.input = input;
        ProveedorService proveedorService = new ProveedorService(proveedorDAO);
        this.proveedorController = new ProveedorController(proveedorService, input);
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.print("""
            \n=== MENU PROVEEDOR ===
            1. Agregar proveedor
            2. Actualizar proveedor por ID
            3. Eliminar proveedor por ID
            4. Buscar proveedor por ID
            5. Listar todos los proveedores
            0. Salir
            Ingrese una opcion:\s""");
            opcion = Validaciones.leerEntero(input);

            switch (opcion) {
                case 1:
                    proveedorController.agregarProveedor();
                    break;
                case 2:
                    proveedorController.actualizarProveedor();
                    break;
                case 3:
                    proveedorController.eliminarProveedor();
                    break;
                case 4:
                    proveedorController.buscarProveedorPorId();
                    break;
                case 5:
                    proveedorController.listarProveedores();
                    break;
                case 0:
                    System.out.println("Saliendo del menú de proveedores...");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (opcion != 0);
    }
}