package controller.facturaController;

import model.entities.Duenos.Dueno;
import model.entities.Items_Factura.Factura;
import service.DuenoService;
import service.FacturaService;
import util.Validaciones;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class FacturaController {
    private final FacturaService facturaService;
    private final DuenoService duenoService;
    private final Validaciones validador;

    public FacturaController(FacturaService facturaService, DuenoService duenoService, Scanner input) {
        this.facturaService = facturaService;
        this.duenoService = duenoService;
        this.validador = new Validaciones(input);
    }

    public void agregarFactura() {
        String documentoDueno = validador.leerTexto("Documento del dueño: ");
        Dueno dueno = duenoService.buscarPorDocumento(documentoDueno);

        if (dueno == null) {
            System.out.println("No se encontró dueño con ese documento. No se puede crear la factura.");
            return;
        }

        LocalDateTime fechaEmision = fechaActual();
        double total = leerDouble("Total de la factura: ", 0, Double.MAX_VALUE);
        String metodoPago = leerMetodoPago();

        Factura f = new Factura(null, dueno.getId(), fechaActual(), total, metodoPago);
        f = facturaService.agregarFactura(f);
        imprimirFactura(f);
    }

    public void actualizarFactura() {
        int id = validador.leerEnteroPositivo("ID de la factura a actualizar: ");
        Factura f = facturaService.buscarPorId(id);

        if (f == null) {
            System.out.println("No existe factura con ese ID.");
            return;
        }

        System.out.println("Deje vacío para mantener el valor actual.");

        String documentoDueno = leerLineaOpcional("Nuevo documento del dueño (" + f.getDueno_id() + "): ");
        if (!documentoDueno.isEmpty()) {
            Dueno dueno = duenoService.buscarPorDocumento(documentoDueno);
            if (dueno != null) f.setDueno_id(dueno.getId());
            else System.out.println("No se encontró dueño con ese documento. Se mantiene el anterior.");
        }

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
        if (f != null) imprimirFactura(f);
        else System.out.println("No existe factura con ese ID.");
    }

    public void listarFacturas() {
        List<Factura> lista = facturaService.listarFacturas();
        System.out.println("\n===== LISTA DE FACTURAS =====:");
        for (Factura f : lista) {
            imprimirFactura(f);
        }
    }

    public void imprimirFactura(Factura factura) {
        if (factura == null) {
            System.out.println("No se encontró la factura.");
            return;
        }

        Dueno dueno = duenoService.buscarPorId(factura.getDueno_id());
        String nombreDueno = (dueno != null) ? dueno.getNombre_completo() : "Desconocido";
        String documentoDueno = (dueno != null) ? dueno.getDocumento_identidad() : "Desconocido";

        System.out.println("------ INFORMACION DE LA FACTURA ------");
        System.out.println("ID: " + factura.getId());
        System.out.println("Dueño ID: " + factura.getDueno_id());
        System.out.println("Dueño: " + nombreDueno);
        System.out.println("Documento: " + documentoDueno);
        System.out.println("Fecha Emision: " + factura.getFecha_emision().toLocalDate());
        System.out.println("Total: " + factura.getTotal());
        System.out.println("Metodo de Pago: " + factura.getMetodo_pago());
        System.out.println("----------------------------------");
    }

    // Validaciones específicas
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

    private LocalDateTime fechaActual() {
        return LocalDateTime.now();
    }
}
