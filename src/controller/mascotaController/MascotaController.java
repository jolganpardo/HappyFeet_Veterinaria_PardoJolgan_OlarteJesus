package controller.mascotaController;

import model.entities.Duenos.Dueno;
import model.entities.Mascotas.Especie;
import model.entities.Mascotas.Mascota;
import model.entities.Mascotas.Raza;
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

        // Mostrar especies disponibles
        List<Especie> especies = mascotaService.listarEspecies();
        if (especies.isEmpty()) {
            System.out.println("No hay especies registradas.");
            return;
        }

        System.out.println("=== ESPECIES DISPONIBLES ===");
        for (Especie especie : especies) {
            System.out.println(especie.getId() + " - " + especie.getNombre());
        }
        System.out.print("Seleccione el ID de la especie (o 0 para añadir nueva): ");
        int especieId;
        try {
            especieId = Integer.parseInt(input.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido");
            return;
        }

        // Agregar nueva especie si se selecciona 0
        if (especieId == 0) {
            System.out.print("Ingrese el nombre de la nueva especie: ");
            String nuevaEspecie = input.nextLine().trim();
            if (nuevaEspecie.isEmpty()) {
                System.out.println("Nombre de especie no puede estar vacío");
                return;
            }
            try {
                mascotaService.agregarEspecie(nuevaEspecie);
                especies = mascotaService.listarEspecies();
                especieId = especies.stream()
                        .filter(e -> e.getNombre().equalsIgnoreCase(nuevaEspecie))
                        .findFirst()
                        .map(Especie::getId)
                        .orElse(0);
                System.out.println("Especie añadida con ID: " + especieId);
            } catch (Exception e) {
                System.out.println("Error al agregar especie: " + e.getMessage());
                return;
            }
        }

        // Mostrar y seleccionar raza
        List<Raza> razas = mascotaService.listarRazasPorEspecie(especieId);
        if (razas.isEmpty()) {
            System.out.println("No hay razas registradas para esta especie.");
        } else {
            System.out.println("=== RAZAS DISPONIBLES ===");
            for (Raza raza : razas) {
                System.out.println(raza.getId() + " - " + raza.getNombre());
            }
        }
        System.out.print("Seleccione el ID de la raza (o 0 para añadir nueva): ");
        int razaId;
        try {
            razaId = Integer.parseInt(input.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido");
            return;
        }

        // Agregar nueva raza si se selecciona 0
        if (razaId == 0) {
            System.out.print("Ingrese el nombre de la nueva raza: ");
            String nuevaRaza = input.nextLine().trim();
            if (nuevaRaza.isEmpty()) {
                System.out.println("Nombre de raza no puede estar vacío");
                return;
            }
            try {
                mascotaService.agregarRaza(nuevaRaza, especieId);
                razas = mascotaService.listarRazasPorEspecie(especieId);
                razaId = razas.stream()
                        .filter(r -> r.getNombre().equalsIgnoreCase(nuevaRaza))
                        .findFirst()
                        .map(Raza::getId)
                        .orElse(0);
                System.out.println("Raza añadida con ID: " + razaId);
            } catch (Exception e) {
                System.out.println("Error al agregar raza: " + e.getMessage());
                return;
            }
        }

        System.out.print("Nombre de la mascota: ");
        String nombre = input.nextLine().trim();

        System.out.print("Fecha de nacimiento (yyyy-MM-dd): ");
        String fechaNacimiento = input.nextLine().trim();

        System.out.print("Sexo (Macho/Hembra): ");
        String sexo = input.nextLine().trim();

        System.out.print("URL de la foto (opcional): ");
        String urlFoto = input.nextLine().trim();

        System.out.print("Microchip (opcional, si lo deja vacío se generará automáticamente): ");
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
        System.out.println("=== ACTUALIZAR DUEÑO DE LA MASCOTA ===");
        System.out.print("Ingrese ID de la mascota: ");
        int id;
        try {
            id = Integer.parseInt(input.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Error: ID inválido");
            return;
        }

        try {
            Mascota mascota = mascotaService.obtenerPorId(id);
            if (mascota == null || "INACTIVA".equalsIgnoreCase(mascota.getEstado())) {
                System.out.println("Mascota no encontrada o INACTIVA");
                return;
            }

            // Listar dueños disponibles
            List<Dueno> duenos = mascotaService.listarDuenos();
            if (duenos.isEmpty()) {
                System.out.println("No hay dueños registrados.");
                return;
            }

            System.out.println("=== DUEÑOS DISPONIBLES ===");
            for (Dueno dueno : duenos) {
                System.out.println(dueno.getId() + " - " + dueno.getNombre_completo());
            }

            System.out.print("Seleccione el ID del nuevo dueño: ");
            String duenoStr = input.nextLine().trim();
            int nuevoDuenoId;
            try {
                nuevoDuenoId = Integer.parseInt(duenoStr);
            } catch (NumberFormatException e) {
                System.out.println("Error: ID de dueño inválido");
                return;
            }

            if (duenos.stream().noneMatch(d -> d.getId() == nuevoDuenoId)) {
                System.out.println("ID de dueño no existe");
                return;
            }

            // Actualizar dueño de la mascota
            mascotaService.actualizarMascota(id, "", "", String.valueOf(nuevoDuenoId));
            System.out.println("Dueño de la mascota actualizado correctamente.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public void cambiarEstadoMascota() {
        System.out.println("=== CAMBIAR ESTADO DE MASCOTA (INACTIVA) ===");
        System.out.print("Ingrese ID de la mascota: ");
        int id;
        try {
            id = Integer.parseInt(input.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Error: ID inválido");
            return;
        }

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
        int id;
        try {
            id = Integer.parseInt(input.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Error: ID inválido");
            return;
        }

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
        // Mostrar razas disponibles
        List<Raza> razas = mascotaService.listarRazas();
        if (razas.isEmpty()) {
            System.out.println("No hay razas registradas");
            return;
        }

        System.out.println("=== LISTA DE RAZAS DISPONIBLES ===");
        for (Raza r : razas) {
            System.out.println(r.getId() + " - " + r.getNombre());
        }

        System.out.print("Ingrese ID de la raza: ");
        String idStr = input.nextLine().trim();

        try {
            List<Mascota> mascotas = mascotaService.obtenerPorRazaId(idStr);
            if (mascotas.isEmpty()) {
                System.out.println("No se encontraron mascotas para esta raza");
            } else {
                System.out.println("=== Mascotas de la raza ID " + idStr + " ===");
                for (Mascota m : mascotas) imprimirMascota(m);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void obtenerPorEspecieId() {
        // Mostrar especies disponibles
        List<Especie> especies = mascotaService.listarEspecies();
        if (especies.isEmpty()) {
            System.out.println("No hay especies registradas");
            return;
        }

        System.out.println("=== LISTA DE ESPECIES DISPONIBLES ===");
        for (Especie e : especies) {
            System.out.println(e.getId() + " - " + e.getNombre());
        }

        System.out.print("Ingrese ID de la especie: ");
        String idStr = input.nextLine().trim();

        try {
            List<Mascota> mascotas = mascotaService.obtenerPorEspecieId(idStr);
            if (mascotas.isEmpty()) {
                System.out.println("No se encontraron mascotas para esta especie");
            } else {
                System.out.println("=== Mascotas de la especie ID " + idStr + " ===");
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