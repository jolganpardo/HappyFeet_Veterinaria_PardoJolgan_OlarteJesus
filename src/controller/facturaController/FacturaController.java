package controller.facturaController;

import model.entities.Items_Factura.Factura;
import model.repository.ItemFacturaDAO.IFacturaDAO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class FacturaController {
    private IFacturaDAO iFacturaDAO;
    private Scanner input;

    public FacturaController(IFacturaDAO iFacturaDAO, Scanner input) {
        this.iFacturaDAO = iFacturaDAO;
        this.input = input;
    }

    public void agregarFactura() {
        System.out.print("ID del dueño: ");
        int duenoId = input.nextInt();
        input.nextLine();

        System.out.print("Fecha de emisión (YYYY-MM-DD): ");
        String fechaStr = input.nextLine();
        LocalDate fecha = LocalDate.parse(fechaStr);
        LocalDateTime fechaEmision = fecha.atStartOfDay();

        System.out.print("Total de la factura: ");
        double total = input.nextDouble();
        input.nextLine();

        System.out.print("""
                \n Metodos de pago disponibles:
                1. Efectivo.
                2. Tarjeta Débito.
                3. Tarjeta Crédito.
                4. Transferencia
                5. Cheque.
                >>>Ingrese método de pago (1-5):
                """);

        int opcionMetodoPago;
        try {
            opcionMetodoPago = Integer.parseInt(input.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Debe ingresar un número del 1 al 5.");
            return;
        }

        String metodoPago;
        switch (opcionMetodoPago) {
            case 1 -> metodoPago = "Efectivo";
            case 2 -> metodoPago = "Tarjeta Débito";
            case 3 -> metodoPago = "Tarjeta Crédito";
            case 4 -> metodoPago = "Transferencia";
            case 5 -> metodoPago = "Cheque";
            default -> {
                System.out.println("Opción inválida. Debe ser un número entre 1 y 5.");
                return;
            }
        }

        Factura factura = new Factura(null, duenoId, fechaEmision, total, metodoPago);
        iFacturaDAO.insertar(factura);
        System.out.println("Factura agregada con éxito.");
    }

    public void actualizarFactura() {
        System.out.print("ID de la factura a actualizar: ");
        int id = input.nextInt();
        input.nextLine();

        Factura f = iFacturaDAO.obtenerPorId(id);
        if (f != null) {
            System.out.print("Nuevo dueño ID (" + f.getDueno_id() + "): ");
            String duenoStr = input.nextLine();
            if (!duenoStr.isEmpty()) f.setDueno_id(Integer.parseInt(duenoStr));

            System.out.print("Nueva fecha (" + f.getFecha_emision() + "): ");
            String fechaStr = input.nextLine();
            if (!fechaStr.isEmpty()) {
                f.setFecha_emision(LocalDateTime.parse(fechaStr));
            }

            System.out.print("Nuevo total (" + f.getTotal() + "): ");
            String totalStr = input.nextLine();
            if (!totalStr.isEmpty()) f.setTotal(Double.parseDouble(totalStr));

            System.out.print("Nuevo método de pago (" + f.getMetodo_pago() + "): ");
            String metodoPago = input.nextLine();
            if (!metodoPago.isEmpty()) f.setMetodo_pago(metodoPago);

            iFacturaDAO.actualizar(f);
            System.out.println("Factura actualizada con éxito.");
        } else {
            System.out.println("No existe factura con ese ID.");
        }
    }


    public void eliminarFactura() {
        System.out.print("ID de la factura a eliminar: ");
        int id = input.nextInt();
        input.nextLine();
        iFacturaDAO.eliminar(id);
        System.out.println("🗑️Factura eliminada con éxito.");
    }

    public void buscarFacturaPorId() {
        System.out.print("ID de la factura: ");
        int id = input.nextInt();
        input.nextLine();

        Factura f = iFacturaDAO.obtenerPorId(id);
        if (f != null) {
            System.out.println("🔎Factura encontrada: " + f);
        } else {
            System.out.println("No existe factura con ese ID.");
        }
    }

    public void listarFacturas() {
        List<Factura> lista = iFacturaDAO.obtenerTodas();
        System.out.println("\n📋 Lista de facturas:");
        for (Factura f : lista) {
            System.out.println(f);
        }
    }

}
