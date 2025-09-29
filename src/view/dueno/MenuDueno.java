package view.dueno;

import controller.duenoController.DuenoController;
import model.entities.Duenos.Dueno;
import model.repository.duenosDAO.DuenoDAO;
import model.repository.duenosDAO.IDuenosDAO;

import java.util.Scanner;

public class MenuDueno {
    private IDuenosDAO duenoDAO; // Usamos la interfaz
    private DuenoController duenoController;
    private Scanner input;

    public MenuDueno() {
        this.duenoDAO = new DuenoDAO();
        this.input = new Scanner(System.in);
        this.duenoController = new DuenoController(duenoDAO, input);
    }

    public void mostrarMenuDueno() {
        int opcion;
        do {
            System.out.print("""
                \n=== MENU DUEÑO ===
                1. Insertar nuevo dueño.
                2. Buscar dueño por ID.
                3. Listar todos los dueños.
                4. Actualizar dueño.
                5. Eliminar dueño.
                0. Salir del menú de dueño.
                Selecciona una opción:
                """);
            opcion = input.nextInt();
            input.nextLine();

            switch (opcion) {
                case 1:
                    agregarDueno();
                    break;
                case 2:
                    buscarPorId();
                    break;
                case 3:
                    listarDuenos();
                    break;
                case 4:
                    actualizarDueno();
                    break;
                case 5:
                    eliminarDueno();
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
        duenoDAO.agregarDueno(nuevo);
        System.out.println("Dueño insertado con éxito.");
    }

    public void buscarPorId() {
        System.out.print("ID del dueño: ");
        int id = input.nextInt();
        input.nextLine();
        Dueno dueno = duenoDAO.buscarPorId(id);
        if (dueno != null) {
            System.out.println("🔎 Dueño encontrado: " + dueno);
        } else {
            System.out.println("No existe dueño con ese ID.");
        }
    }

    public void listarDuenos() {
        System.out.println("\n📋 Lista de dueños:");
        for (Dueno d : duenoDAO.listarDuenos()) {
            System.out.println(d);
        }
    }

    public void actualizarDueno() {
        System.out.print("ID del dueño a actualizar: ");
        int id = input.nextInt();
        input.nextLine();
        Dueno dueno = duenoDAO.buscarPorId(id);
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

            duenoDAO.actualizarDuenos(dueno);
            System.out.println("Dueño actualizado con éxito.");
        } else {
            System.out.println("No existe dueño con ese ID.");
        }
    }

    public void eliminarDueno() {
        System.out.print("ID del dueño a eliminar: ");
        int id = input.nextInt();
        input.nextLine();
        Dueno dueno = duenoDAO.buscarPorId(id);
        if (dueno != null) {
            duenoDAO.eliminarDueno(id);
            System.out.println("🗑️Dueño eliminado con éxito.");
        } else {
            System.out.println("No existe dueño con ese ID.");
        }
    }
}
