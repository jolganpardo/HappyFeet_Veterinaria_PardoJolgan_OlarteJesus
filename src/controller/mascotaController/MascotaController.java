package controller.mascotaController;

import model.entities.Mascotas.Mascota;
import service.MascotaService;

import java.util.List;
import java.util.Scanner;

public class MascotaController {

    private final MascotaService mascotaService;
    private final Scanner input;

    public MascotaController(MascotaService mascotaService, Scanner input) {
        this.mascotaService = mascotaService;
        this.input = input;
    }

    public void agregarMascota() {
        System.out.println("=== AGREGAR MASCOTA ===");

        System.out.print("Documento del dueño: ");
        String documento = input.nextLine().trim();

        System.out.print("Ingrese el ID de la especie: ");
        String especieStr = input.nextLine().trim();
        int especieId = Integer.parseInt(especieStr);

        System.out.print("Ingrese el ID de la raza: ");
        String razaStr = input.nextLine().trim();
        int razaId = Integer.parseInt(razaStr);

        System.out.print("Nombre de la mascota: ");
        String nombre = input.nextLine().trim();

        System.out.print("Fecha de nacimiento (yyyy-MM-dd): ");
        String fechaNacimiento = input.nextLine().trim();

        System.out.print("Sexo (Macho/Hembra): ");
        String sexo = input.nextLine().trim();

        System.out.print("URL de la foto (opcional): ");
        String urlFoto = input.nextLine().trim();

        System.out.print("Microchip (opcional): ");
        String microchip = input.nextLine().trim();

        try {
            Mascota mascota = mascotaService.agregarMascota(
                    documento, nombre, razaId, especieId,
                    fechaNacimiento, sexo, urlFoto, microchip
            );
            System.out.println("Mascota agregada con éxito. ID: " + mascota.getId());
            imprimirMascota(mascota);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void actualizarMascota() {
        System.out.println("=== ACTUALIZAR MASCOTA ===");
        System.out.print("Ingrese ID de la mascota: ");
        int id = Integer.parseInt(input.nextLine().trim());

        try {
            Mascota mascota = mascotaService.obtenerPorId(id);
            if (mascota == null) {
                System.out.println("Mascota no encontrada o INACTIVA");
                return;
            }

            System.out.print("Nuevo nombre (" + mascota.getNombre() + "): ");
            String nombre = input.nextLine().trim();

            System.out.print("Nuevo sexo (" + mascota.getSexo() + "): ");
            String sexo = input.nextLine().trim();

            System.out.print("Nuevo ID de dueño (" + mascota.getDueno_id() + "): ");
            String duenoStr = input.nextLine().trim();

            mascotaService.actualizarMascota(id, nombre, sexo, duenoStr);
            System.out.println("Mascota actualizada correctamente.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void cambiarEstadoMascota() {
        System.out.println("=== CAMBIAR ESTADO DE MASCOTA ===");
        System.out.print("Ingrese ID de la mascota: ");
        int id = Integer.parseInt(input.nextLine().trim());

        try {
            mascotaService.cambiarEstadoMascota(id);
            System.out.println("Mascota marcada como INACTIVA");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void listarMascotas() {
        System.out.println("=== LISTA DE MASCOTAS ===");
        try {
            List<Mascota> lista = mascotaService.obtenerTodos();
            if (lista.isEmpty()) {
                System.out.println("No hay mascotas registradas");
                return;
            }
            for (Mascota m : lista) imprimirMascota(m);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void buscarPorId() {
        System.out.print("Ingrese ID de la mascota: ");
        int id = Integer.parseInt(input.nextLine().trim());
        try {
            Mascota mascota = mascotaService.obtenerPorId(id);
            if (mascota != null) imprimirMascota(mascota);
            else System.out.println("Mascota no encontrada");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void obtenerPorDuenoDocumento() {
        System.out.print("Ingrese documento del dueño: ");
        String documento = input.nextLine().trim();

        try {
            List<Mascota> mascotas = mascotaService.obtenerPorDuenoDocumento(documento);
            if (mascotas.isEmpty()) {
                System.out.println("No se encontraron mascotas para este dueño");
                return;
            }
            for (Mascota m : mascotas) imprimirMascota(m);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void obtenerPorRazaId() {
        System.out.print("Ingrese ID de la raza: ");
        String idStr = input.nextLine().trim();

        try {
            List<Mascota> mascotas = mascotaService.obtenerPorRazaId(idStr);
            if (mascotas.isEmpty()) {
                System.out.println("No se encontraron mascotas para esta raza");
            } else {
                for (Mascota m : mascotas) imprimirMascota(m);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void obtenerPorEspecieId() {
        System.out.print("Ingrese ID de la especie: ");
        String idStr = input.nextLine().trim();

        try {
            List<Mascota> mascotas = mascotaService.obtenerPorEspecieId(idStr);
            if (mascotas.isEmpty()) {
                System.out.println("No se encontraron mascotas para esta especie");
            } else {
                for (Mascota m : mascotas) imprimirMascota(m);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void imprimirMascota(Mascota mascota) {
        if (mascota == null) {
            System.out.println("No se encontró la mascota.");
            return;
        }
        System.out.println("------ INFORMACIÓN DE LA MASCOTA ------");
        System.out.println("ID: " + mascota.getId());
        System.out.println("ID Dueño: " + mascota.getDueno_id());
        System.out.println("Nombre: " + mascota.getNombre());
        System.out.println("Fecha Nacimiento: " + mascota.getFecha_nacimiento());
        System.out.println("ID Especie: " + mascota.getEspecie_id());
        System.out.println("ID Raza: " + mascota.getRaza_id());
        System.out.println("Sexo: " + mascota.getSexo());
        System.out.println("URL Foto: " + (mascota.getUrl_foto() != null ? mascota.getUrl_foto() : "N/A"));
        System.out.println("Microchip: " + (mascota.getMicrochip() != null ? mascota.getMicrochip() : "N/A"));
        System.out.println("Estado: " + mascota.getEstado());
        System.out.println("--------------------------------------");
    }
}