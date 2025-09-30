package view.factura;

import controller.facturaController.FacturaController;
import model.repository.ItemFacturaDAO.FacturaDAO;
import model.repository.ItemFacturaDAO.IFacturaDAO;
import model.repository.duenosDAO.DuenoDAO;
import service.DuenoService;
import service.FacturaService;

import java.util.Scanner;

public class MenuFactura {
    private final Scanner input = new Scanner(System.in);
    private final IFacturaDAO facturaDAO = new FacturaDAO();
    private final FacturaService facturaService = new FacturaService(facturaDAO);
    private final DuenoService duenoService = new DuenoService(new DuenoDAO());
    private final FacturaController facturaController = new FacturaController(facturaService, duenoService, input);

    public void mostrarMenuFactura() {
        int opcion;
        do {
            System.out.print("""
                    \n=== MENU Factura ===
                    1. Agregar una factura.
                    2. Actualizar una factura.
                    3. Eliminar una factura.
                    4. Buscar factura por ID.
                    5. Listar todas las facturas.
                    0. Salir del menú de facturas.
                    Selecciona una opción: 
                    """);
            opcion = input.nextInt();
            input.nextLine();

            switch (opcion) {
                case 1 -> facturaController.agregarFactura();
                case 2 -> facturaController.actualizarFactura();
                case 3 -> facturaController.eliminarFactura();
                case 4 -> facturaController.buscarFacturaPorId();
                case 5 -> facturaController.listarFacturas();
                case 0 -> System.out.println("Saliendo del menú de facturas...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }
}
