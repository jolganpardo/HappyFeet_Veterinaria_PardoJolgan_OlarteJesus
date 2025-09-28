package controller.duenoController;

import model.entities.Duenos.Dueno;
import model.repository.duenosDAO.DuenoDAO;
import model.repository.duenosDAO.IDuenosDAO;

import java.util.Scanner;

public class DuenoController {
    private IDuenosDAO iduenoDAO;
    private Scanner input;

    public DuenoController(IDuenosDAO iduenoDAO, Scanner input) {
        this.iduenoDAO = iduenoDAO;
        this.input = input;
    }

    public void agregarDueno() {
        System.out.print("Nombre completo: ");
        String nombre = input.nextLine();
        System.out.print("Documento identidad: ");
        String doc = input.nextLine();
        System.out.print("Dirección: ");
        String dir = input.nextLine();
        System.out.print("Teléfono: ");
        String tel = input.nextLine();
        System.out.print("Email: ");
        String email = input.nextLine();

        Dueno nuevo = new Dueno(null, nombre, doc, dir, tel, email);
        iduenoDAO.agregarDueno(nuevo);
        System.out.println("Dueño insertado con éxito.");
    }

    public void buscarPorId() {
        System.out.print("ID del dueño: ");
        int id = input.nextInt();
        input.nextLine();
        Dueno dueno = iduenoDAO.buscarPorId(id);
        if (dueno != null) {
            System.out.println("🔎 Dueño encontrado: " + dueno);
        } else {
            System.out.println("No existe dueño con ese ID.");
        }
    }

    public void listarDuenos() {
        System.out.println("\n📋 Lista de dueños:");
        for (Dueno d : iduenoDAO.listarDuenos()) {
            System.out.println(d);
        }
    }

    public void actualizarDueno() {
        System.out.print("ID del dueño a actualizar: ");
        int id = input.nextInt();
        input.nextLine();
        Dueno dueno = iduenoDAO.buscarPorId(id);
        if (dueno != null) {
            System.out.print("Nuevo nombre completo (" + dueno.getNombre_completo() + "): ");
            String nuevoNombre = input.nextLine();
            if (!nuevoNombre.isEmpty()) dueno.setNombre_completo(nuevoNombre);

            System.out.print("Nueva dirección (" + dueno.getDireccion() + "): ");
            String nuevaDir = input.nextLine();
            if (!nuevaDir.isEmpty()) dueno.setDireccion(nuevaDir);

            System.out.print("Nuevo teléfono (" + dueno.getTelefono() + "): ");
            String nuevoTel = input.nextLine();
            if (!nuevoTel.isEmpty()) dueno.setTelefono(nuevoTel);

            System.out.print("Nuevo email (" + dueno.getEmail() + "): ");
            String nuevoEmail = input.nextLine();
            if (!nuevoEmail.isEmpty()) dueno.setEmail(nuevoEmail);

            iduenoDAO.actualizarDuenos(dueno);
            System.out.println("Dueño actualizado con éxito.");
        } else {
            System.out.println("No existe dueño con ese ID.");
        }
    }

    public void eliminarDueno() {
        System.out.print("ID del dueño a eliminar: ");
        int id = input.nextInt();
        input.nextLine();
        Dueno dueno = iduenoDAO.buscarPorId(id);
        if (dueno != null) {
            iduenoDAO.eliminarDueno(id);
            System.out.println("🗑️Dueño eliminado con éxito.");
        } else {
            System.out.println("No existe dueño con ese ID.");
        }
    }
}
