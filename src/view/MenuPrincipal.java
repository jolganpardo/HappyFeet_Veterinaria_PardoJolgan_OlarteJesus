package view;

import java.util.Scanner;

public class MenuPrincipal {

    private final Scanner scanner;

    public MenuPrincipal() {
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion;

        do {
            System.out.printf("""
                    \n===== MENÚ PRINCIPAL - HAPPY FEET =====
                    1. Gestionar Dueños
                    2. Gestionar Mascotas"
                    3. Gestionar Citas
                    4. Gestionar Veterinarios
                    5. Gestionar Inventario
                    6. Gestionar Facturación
                    7. Gestionar Proveedores
                    0. Salir
                    >>Ingrese su opcion: 
                    """);

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 0:
                    System.out.println("Saliendo... Hasta pronto.");
                    break;
                default:
                    System.out.println("Opcion no valida.");

            }
        } while (opcion != 0);
    }
}
