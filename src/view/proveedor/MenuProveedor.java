package view.proveedor;

import controller.proveedorController.ProveedorController;
import model.repository.InventarioDAO.IProveedorDAO;
import model.repository.InventarioDAO.ProveedorDAO;

import java.util.Scanner;

public class MenuProveedor {
    private Scanner input;
    private IProveedorDAO proveedorDAO;
    private ProveedorController proveedorController;

    public MenuProveedor() {
        this.input = new Scanner(System.in);
        this.proveedorDAO = new ProveedorDAO();
        this.proveedorController = new ProveedorController(proveedorDAO, input);
    }

    public void mostrarMenuProveedor() {
        int opcion;
        do {
            System.out.print("""
                \n=== MENU PROVEEDOR ===
                1. Agregar proveedor
                2. Actualizar proveedor
                3. Eliminar proveedor
                4. Buscar proveedor por ID
                5. Listar todos los proveedores
                0. Salir del menu de proveedores
                Ingrese una opcion:
                """);
            opcion = input.nextInt();
            input.nextLine();

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
                    System.out.println("Saliendo del menu de proveedores...");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        } while (opcion != 0);
    }
}
