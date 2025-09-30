package view.factura;

import controller.facturaController.FacturaController;
import model.repository.ItemFacturaDAO.FacturaDAO;
import model.repository.ItemFacturaDAO.IFacturaDAO;

import java.util.Scanner;

public class MenuFactura {
    private final IFacturaDAO iFacturaDAO;
    private final FacturaController facturaController;
    private final Scanner input;

    public MenuFactura() {
        this.iFacturaDAO = new FacturaDAO();
        this.input = new Scanner(System.in);
        this.facturaController = new FacturaController(iFacturaDAO, input);
    }

    public void mostrarMenuFactura() {
        int opcion;
        do {
            System.out.print("""
                    \n=== MENU Factura ===
                    1. Agregar una factura.
                    2. Actualizar una Factura.
                    3. Eliminar una Factura.
                    4. Listar todas las facturas.
                    0. Salir del menú de dueño.
                    >>>Selecciona una opción:\s
                    """);
            opcion = input.nextInt();
            input.nextLine();

            switch (opcion) {
                case 1:
                    facturaController.agregarFactura();
                    break;
                case 2:
                    facturaController.actualizarFactura();
                    break;
                case 3:
                    facturaController.eliminarFactura();
                    break;
                case 4:
                    facturaController.listarFacturas();
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