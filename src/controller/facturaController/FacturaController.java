package controller.facturaController;

import model.entities.Items_Factura.Factura;
import service.FacturaService;
import util.Validaciones;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class FacturaController {
    private final FacturaService facturaService;
    private final Validaciones validador;

    public FacturaController(FacturaService facturaService, Scanner input) {
        this.facturaService = facturaService;
        this.validador = new Validaciones(input);
    }

    // ===== Operaciones =====

    public void agregarFactura() {
        int duenoId = validador.leerEnteroPositivo("ID del dueño: ");
        LocalDateTime fechaEmision = leerFecha("Fecha de emisión (YYYY-MM-DD): ");
        double total = leerDouble("Total de la factura: ", 0, Double.MAX_VALUE);
        String metodoPago = leerMetodoPago();

        Factura f = new Factura(null, duenoId, fechaEmision, total, metodoPago);
        facturaService.agregarFactura(f);
        System.out.println("Factura agregada con éxito.");
    }

    public void actualizarFactura() {
        int id = validador.leerEnteroPositivo("ID de la factura a actualizar: ");
        Factura f = facturaService.buscarPorId(id);

        if (f == null) {
            System.out.println("No existe factura con ese ID.");
            return;
        }

        System.out.println("Deje vacío para mantener el valor actual.");

        String duenoStr = leerLineaOpcional("Nuevo dueño ID (" + f.getDueno_id() + "): ");
        if (!duenoStr.isEmpty()) f.setDueno_id(Integer.parseInt(duenoStr));

        String fechaStr = leerLineaOpcional("Nueva fecha (" + f.getFecha_emision().toLocalDate() + "): ");
        if (!fechaStr.isEmpty()) {
            try {
                LocalDate fecha = LocalDate.parse(fechaStr);
                f.setFecha_emision(fecha.atStartOfDay());
            } catch (DateTimeParseException e) {
                System.out.println("Fecha inválida. No se actualizará.");
            }
        }

        String totalStr = leerLineaOpcional("Nuevo total (" + f.getTotal() + "): ");
        if (!totalStr.isEmpty()) {
            try {
                double total = Double.parseDouble(totalStr);
                if (total >= 0) f.setTotal(total);
                else System.out.println("Total inválido, debe ser positivo.");
            } catch (NumberFormatException e) {
                System.out.println("Total inválido. No se actualizará.");
            }
        }

        String metodoPago = leerMetodoPagoOpcional("Nuevo método de pago (" + f.getMetodo_pago() + "): ");
        if (!metodoPago.isEmpty()) f.setMetodo_pago(metodoPago);

        facturaService.actualizarFactura(f);
        System.out.println("Factura actualizada con éxito.");
    }

    public void eliminarFactura() {
        int id = validador.leerEnteroPositivo("ID de la factura a eliminar: ");
        facturaService.eliminarFactura(id);
        System.out.println("Factura eliminada con éxito.");
    }

    public void buscarFacturaPorId() {
        int id = validador.leerEnteroPositivo("ID de la factura: ");
        Factura f = facturaService.buscarPorId(id);
        if (f != null) System.out.println("Factura encontrada: " + f);
        else System.out.println("No existe factura con ese ID.");
    }

    public void listarFacturas() {
        List<Factura> lista = facturaService.listarFacturas();
        System.out.println("\nLista de facturas:");
        for (Factura f : lista) {
            System.out.println(f);
        }
    }

    // ===== Métodos privados de validación =====
    private LocalDateTime leerFecha(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String valor = validador.leerTexto("");
            try {
                LocalDate fecha = LocalDate.parse(valor);
                return fecha.atStartOfDay();
            } catch (DateTimeParseException e) {
                System.out.println("Fecha inválida. Use el formato YYYY-MM-DD.");
            }
        }
    }

    private double leerDouble(String mensaje, double min, double max) {
        while (true) {
            System.out.print(mensaje);
            String line = new Scanner(System.in).nextLine().trim();
            try {
                double valor = Double.parseDouble(line);
                if (valor < min || valor > max) throw new NumberFormatException();
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Número inválido. Debe ser entre " + min + " y " + max + ".");
            }
        }
    }

    private String leerMetodoPago() {
        while (true) {
            System.out.print("""
                    \nMétodos de pago disponibles:
                    1. Efectivo
                    2. Tarjeta Débito
                    3. Tarjeta Crédito
                    4. Transferencia
                    5. Cheque
                    >>> Ingrese método de pago (1-5): 
                    """);
            String opcion = new Scanner(System.in).nextLine().trim();
            switch (opcion) {
                case "1": return "Efectivo";
                case "2": return "Tarjeta Débito";
                case "3": return "Tarjeta Crédito";
                case "4": return "Transferencia";
                case "5": return "Cheque";
                default: System.out.println("Opción inválida. Debe ser un número del 1 al 5.");
            }
        }
    }

    private String leerMetodoPagoOpcional(String mensaje) {
        System.out.print(mensaje + " (1-5 o vacío para mantener): ");
        String opcion = new Scanner(System.in).nextLine().trim();
        switch (opcion) {
            case "1": return "Efectivo";
            case "2": return "Tarjeta Débito";
            case "3": return "Tarjeta Crédito";
            case "4": return "Transferencia";
            case "5": return "Cheque";
            case "": return "";
            default:
                System.out.println("Opción inválida. No se actualizará.");
                return "";
        }
    }

    private String leerLineaOpcional(String mensaje) {
        System.out.print(mensaje);
        return new Scanner(System.in).nextLine().trim();
    }
}
