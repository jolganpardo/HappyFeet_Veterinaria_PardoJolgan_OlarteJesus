package view.proveedor;

import controller.proveedorController.ProveedorController;
import model.entities.Inventario.Proveedor;
import model.repository.InventarioDAO.IProveedorDAO;

import java.util.List;
import java.util.Scanner;

public class MenuProveedor {
    private Scanner input;
    private IProveedorDAO proveedorDAO;
    private ProveedorController proveedorController;

    public void MenuProveedor() {
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
            Ingrese una opcion:
            """);
            opcion = proveedorController.leerEntero("Ingresa una opcion: ");

            switch (opcion) {
                case 1 -> proveedorController.agregarProveedor();
                case 2 -> proveedorController.actualizarProveedor();
                case 3 -> proveedorController.eliminarProveedor();
                case 4 -> proveedorController.buscarProveedorPorId();
                case 5 -> proveedorController.listarProveedores();
                case 0 -> System.out.println("Saliendo del menú de proveedores...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }
}
