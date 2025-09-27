package test;

import model.entities.Duenos.Dueno;
import model.repository.duenosDAO.DuenoDAO;

import java.util.Scanner;

public class MainMenuDueno {
    public static void main(String[] args) {
        DuenoDAO duenoDAO = new DuenoDAO();
        Scanner sc = new Scanner(System.in);

        int opcion;
        do {
            System.out.println("\n=== MENÚ DUEÑOS ===");
            System.out.println("1. Insertar nuevo dueño");
            System.out.println("2. Buscar dueño por ID");
            System.out.println("3. Listar todos los dueños");
            System.out.println("4. Actualizar dueño");
            System.out.println("5. Eliminar dueño");
            System.out.println("0. Salir");
            System.out.print("👉 Selecciona una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1: // Insertar
                    System.out.print("Nombre completo: ");
                    String nombre = sc.nextLine();
                    System.out.print("Documento identidad: ");
                    String doc = sc.nextLine();
                    System.out.print("Dirección: ");
                    String dir = sc.nextLine();
                    System.out.print("Teléfono: ");
                    String tel = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();

                    Dueno nuevo = new Dueno(null, nombre, doc, dir, tel, email);
                    duenoDAO.agregarDueno(nuevo);
                    System.out.println("✅ Dueño insertado con éxito.");
                    break;

                case 2: // Buscar por ID
                    System.out.print("ID del dueño: ");
                    int idBuscar = sc.nextInt();
                    Dueno encontrado = duenoDAO.buscarPorId(idBuscar);
                    if (encontrado != null) {
                        System.out.println("🔎 Dueño encontrado: " + encontrado);
                    } else {
                        System.out.println("⚠️ No existe dueño con ese ID.");
                    }
                    break;

                case 3: // Listar todos
                    System.out.println("\n📋 Lista de dueños:");
                    for (Dueno d : duenoDAO.listarDuenos()) {
                        System.out.println(d);
                    }
                    break;

                case 4: // Actualizar
                    System.out.print("ID del dueño a actualizar: ");
                    int idActualizar = sc.nextInt();
                    sc.nextLine();
                    Dueno duenoActualizar = duenoDAO.buscarPorId(idActualizar);
                    if (duenoActualizar != null) {
                        System.out.print("Nuevo nombre completo (" + duenoActualizar.getNombre_completo() + "): ");
                        String nuevoNombre = sc.nextLine();
                        if (!nuevoNombre.isEmpty()) duenoActualizar.setNombre_completo(nuevoNombre);

                        System.out.print("Nueva dirección (" + duenoActualizar.getDireccion() + "): ");
                        String nuevaDir = sc.nextLine();
                        if (!nuevaDir.isEmpty()) duenoActualizar.setDireccion(nuevaDir);

                        System.out.print("Nuevo teléfono (" + duenoActualizar.getTelefono() + "): ");
                        String nuevoTel = sc.nextLine();
                        if (!nuevoTel.isEmpty()) duenoActualizar.setTelefono(nuevoTel);

                        System.out.print("Nuevo email (" + duenoActualizar.getEmail() + "): ");
                        String nuevoEmail = sc.nextLine();
                        if (!nuevoEmail.isEmpty()) duenoActualizar.setEmail(nuevoEmail);

                        duenoDAO.actualizarDuenos(duenoActualizar);
                        System.out.println("✏️ Dueño actualizado con éxito.");
                    } else {
                        System.out.println("⚠️ No existe dueño con ese ID.");
                    }
                    break;

                case 5: // Eliminar
                    System.out.print("ID del dueño a eliminar: ");
                    int idEliminar = sc.nextInt();
                    Dueno duenoEliminar = duenoDAO.buscarPorId(idEliminar);
                    if (duenoEliminar != null) {
                        duenoDAO.eliminarDueno(idEliminar);
                        System.out.println("🗑️ Dueño eliminado con éxito.");
                    } else {
                        System.out.println("⚠️ No existe dueño con ese ID.");
                    }
                    break;

                case 0:
                    System.out.println("👋 Saliendo del programa...");
                    break;

                default:
                    System.out.println("❌ Opción inválida, intenta de nuevo.");
            }
        } while (opcion != 0);

        sc.close();
    }
}
