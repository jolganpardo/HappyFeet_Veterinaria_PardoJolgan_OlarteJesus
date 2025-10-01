package controller.duenoController;

import model.entities.Duenos.Dueno;
import service.DuenoService;
import util.Validaciones;

import java.util.Scanner;

public class DuenoController {

    private final DuenoService duenoService;
    private final Validaciones validador;

    public DuenoController(DuenoService duenoService, Scanner input) {
        this.duenoService = duenoService;
        this.validador = new Validaciones(input);
    }

    public void agregarDueno() {
        String nombre = validador.leerTexto("Nombre completo: ");
        String documento = validador.leerTexto("Documento de identidad: ");
        String direccion = validador.leerTexto("Dirección: ");
        String telefono = validador.validarTelefono("Teléfono: ");
        String email = validador.validarEmail("Email: ");

        Dueno nuevo = new Dueno(null, nombre, documento, direccion, telefono, email);
        duenoService.agregarDueno(nuevo);
    }

    public void actualizarDueno() {
        String documento = validador.leerTexto("Documento del dueño a actualizar: ");
        Dueno dueno = duenoService.buscarPorDocumento(documento);
        if (dueno == null) {
            System.out.println("No existe dueño con ese documento.");
            return;
        }

        System.out.println("Deje vacío para mantener el valor actual.");
        String nuevoNombre = validador.leerTextoOpcional("Nuevo nombre completo (" + dueno.getNombre_completo() + "): ");
        if (!nuevoNombre.isEmpty()) dueno.setNombre_completo(nuevoNombre);

        String nuevaDireccion = validador.leerTextoOpcional("Nueva dirección (" + dueno.getDireccion() + "): ");
        if (!nuevaDireccion.isEmpty()) dueno.setDireccion(nuevaDireccion);

        String nuevoTelefono = validador.validarTelefonoOpcional("Nuevo teléfono (" + dueno.getTelefono() + "): ");
        if (!nuevoTelefono.isEmpty()) dueno.setTelefono(nuevoTelefono);

        String nuevoEmail = validador.validarEmailOpcional("Nuevo email (" + dueno.getEmail() + "): ");
        if (!nuevoEmail.isEmpty()) dueno.setEmail(nuevoEmail);

        duenoService.actualizarDueno(dueno);
        System.out.println("Dueño actualizado con éxito.");
    }

    public void eliminarDueno() {
        String documento = validador.leerTexto("Documento del dueño a eliminar: ");
        Dueno dueno = duenoService.buscarPorDocumento(documento);
        if (dueno != null) {
            duenoService.eliminarDueno(documento);
            System.out.println("Dueño eliminado con éxito.");
        } else {
            System.out.println("No existe dueño con ese documento.");
        }
    }

    public void buscarPorDocumento() {
        String documento = validador.leerTexto("Documento del dueño: ");
        Dueno dueno = duenoService.buscarPorDocumento(documento);
        imprimirDueno(dueno);
    }

    public void listarDuenos() {
        System.out.println("\nLista de dueños:");
        for (Dueno d : duenoService.listarDuenos()) {
            imprimirDueno(d);
        }
    }

    public void imprimirDueno(Dueno dueno) {
        if (dueno == null) {
            System.out.println("No se encontró el dueño.");
            return;
        }

        System.out.println("------ INFORMACION DEL DUEÑO ------");
        System.out.println("ID: " + dueno.getId());
        System.out.println("Nombre completo: " + dueno.getNombre_completo());
        System.out.println("Documento de identidad: " + dueno.getDocumento_identidad());
        System.out.println("Dirección: " + dueno.getDireccion());
        System.out.println("Teléfono: " + dueno.getTelefono());
        System.out.println("Email: " + dueno.getEmail());
        System.out.println("----------------------------------");
    }
}
