package util;

import java.util.Scanner;

public class Validaciones {
    private final Scanner input;

    public Validaciones(Scanner input) {
        this.input = input;
    }

    public String leerTexto(String mensaje) {
        String valor;
        do {
            System.out.print(mensaje);
            valor = input.nextLine().trim();
            if (valor.isEmpty()) System.out.println("Este campo no puede estar vacío.");
        } while (valor.isEmpty());
        return valor;
    }

    public String validarEmail(String mensaje) {
        String email;
        while (true) {
            System.out.print(mensaje);
            email = input.nextLine().trim();
            if (email.isEmpty()) {
                System.out.println("El email no puede estar vacío.");
                continue;
            }
            if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
                System.out.println("Email inválido. Debe tener formato ejemplo@dominio.com");
                continue;
            }
            break;
        }
        return email;
    }

    public String validarTelefono(String mensaje) {
        String telefono;
        while (true) {
            System.out.print(mensaje);
            telefono = input.nextLine().trim();
            if (telefono.isEmpty()) {
                System.out.println("El teléfono no puede estar vacío.");
                continue;
            }
            if (!telefono.matches("\\d{7,15}")) {
                System.out.println("Teléfono inválido. Solo números, entre 7 y 15 dígitos.");
                continue;
            }
            break;
        }
        return telefono;
    }

    public int leerEnteroPositivo(String mensaje) {
        int valor;
        while (true) {
            System.out.print(mensaje);
            String line = input.nextLine();
            try {
                valor = Integer.parseInt(line);
                if (valor < 0) {
                    System.out.println("No puede ser negativo.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número válido.");
            }
        }
        return valor;
    }
}
