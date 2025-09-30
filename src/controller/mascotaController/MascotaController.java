package controller.mascotaController;

import model.entities.Duenos.Dueno;
import model.entities.Mascotas.Especie;
import model.entities.Mascotas.Mascota;
import model.entities.Mascotas.Raza;
import model.repository.MascotasDAO.*;
import model.repository.duenosDAO.DuenoDAO;
import model.repository.duenosDAO.IDuenosDAO;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class MascotaController {
    private IMascotasDAO imascotasDAO;
    private Scanner input;

    public MascotaController(IMascotasDAO imascotasDAO, Scanner input) {
        this.imascotasDAO = imascotasDAO;
        this.input = input;
    }

    public void agregarMascota() {
        System.out.println("=== AGREGAR MASCOTA ===");

        System.out.print("Documento del dueño: ");
        String documentoDueno = input.nextLine();

        // Mostrar especies
        EspecieDAO especieDAO = new EspecieDAO();
        System.out.println("=== ESPECIES DISPONIBLES ===");
        for (Especie especie : especieDAO.obtenerTodos()) {
            System.out.println(especie.getId() + " - " + especie.getNombre());
        }
        System.out.print("Seleccione el ID de la especie (o 0 para añadir nueva): ");
        int especieId = input.nextInt();
        input.nextLine();

        if (especieId == 0) {
            System.out.print("Ingrese el nombre de la nueva especie: ");
            String nuevaEspecie = input.nextLine();
            Especie especie = new Especie(null, nuevaEspecie);
            especieDAO.agregarEspecie(especie);

            // Recuperar el ID recien insertado
            especieId = especieDAO.obtenerTodos()
                    .stream()
                    .filter(e -> e.getNombre().equalsIgnoreCase(nuevaEspecie))
                    .findFirst()
                    .get()
                    .getId();

            System.out.println("Especie añadida con ID: " + especieId);
        }

        // Mostrar razas filtradas
        RazaDAO razaDAO = new RazaDAO();
        System.out.println("=== RAZAS DISPONIBLES ===");
        for (Raza raza : razaDAO.obtenerPorEspecieId(especieId)) {
            System.out.println(raza.getId() + " - " + raza.getNombre());
        }
        System.out.print("Seleccione el ID de la raza (o 0 para añadir nueva): ");
        int razaId = input.nextInt();
        input.nextLine();

        if (razaId == 0) {
            System.out.print("Ingrese el nombre de la nueva raza: ");
            String nuevaRaza = input.nextLine();
            Raza raza = new Raza(null, especieId, nuevaRaza);
            razaDAO.agregarRaza(raza);

            // Recuperar el ID recién insertado
            razaId = razaDAO.obtenerPorEspecieId(especieId)
                    .stream()
                    .filter(r -> r.getNombre().equalsIgnoreCase(nuevaRaza))
                    .findFirst()
                    .get()
                    .getId();

            System.out.println("Raza añadida con ID: " + razaId);
        }

        // Resto del flujo
        System.out.print("Nombre de la mascota: ");
        String nombre = input.nextLine();

        System.out.print("Fecha de nacimiento (yyyy-MM-dd): ");
        LocalDate fecha = LocalDate.parse(input.nextLine());

        System.out.print("Sexo (Macho/Hembra): ");
        String sexo = input.nextLine();

        System.out.print("URL de la foto (opcional): ");
        String urlFoto = input.nextLine();

        System.out.print("Número de microchip (opcional, si lo deja vacío se generará automáticamente): ");
        String microchip = input.nextLine();

        Mascota mascota = new Mascota(
                null,
                null,
                nombre,
                razaId,
                especieId,
                fecha,
                sexo,
                urlFoto.isEmpty() ? null : urlFoto,
                microchip.isEmpty() ? null : microchip,
                "ACTIVA"
        );

        // Capturar el ID generado
        int idGenerado = imascotasDAO.agregarMascota(mascota, documentoDueno);
        mascota.setId(idGenerado); // Asignar el ID a la mascota

        System.out.println("✓ Mascota agregada con éxito. ID: " + idGenerado);
        imprimirMascota(mascota);

    }


    public void actualizarMascota() {
        System.out.println("=== ACTUALIZAR MASCOTA (ADOPCIÓN) ===");

        System.out.print("ID de la mascota a actualizar: ");
        int id = input.nextInt();
        input.nextLine();

        Mascota mascota = imascotasDAO.obtenerPorId(id);
        if (mascota == null) {
            System.out.println("No se encontró la mascota con ID " + id);
            return;
        }

        // --- Nombre ---
        System.out.print("Nuevo nombre (" + mascota.getNombre() + "): ");
        String nombre = input.nextLine();
        if (!nombre.isEmpty()) {
            mascota.setNombre(nombre);
        }

        IDuenosDAO duenosDAO = new DuenoDAO();  // Usa la implementación concreta
        List<Dueno> duenos = duenosDAO.listarDuenos();

        System.out.println("=== LISTA DE DUEÑOS DISPONIBLES ===");
        for (Dueno d : duenos) {
            System.out.println(d.getId() + " - " + d.getNombre_completo());
        }


        // --- Cambio de dueño ---
        System.out.print("Nuevo ID de dueño (" + mascota.getDueno_id() + "): ");
        String duenoStr = input.nextLine();
        if (!duenoStr.isEmpty()) {
            try {
                int nuevoDuenoId = Integer.parseInt(duenoStr);
                mascota.setDuenos_id(nuevoDuenoId);
            } catch (NumberFormatException e) {
                System.out.println("ID inválido, se mantiene el actual.");
            }
        }

        // Guardar cambios
        imascotasDAO.actualizarMascota(mascota);
        System.out.println("Mascota actualizada.");
    }


    public void cambiarEstadoMascota() {
        System.out.println("=== CAMBIAR ESTADO DE MASCOTA (INACTIVA) ===");
        System.out.print("ID de la mascota: ");
        int id = input.nextInt();
        input.nextLine();

        imascotasDAO.cambiarEstadoMascota(id);
        System.out.println("Mascota marcada como INACTIVA.");
    }

    public void listarMascotas() {
        System.out.println("=== LISTA DE MASCOTAS ===");
        List<Mascota> lista = imascotasDAO.obtenerTodos();
        if (lista.isEmpty()) {
            System.out.println("No hay mascotas registradas.");
            return;
        }
        for (Mascota m : lista) {
            imprimirMascota(m);
        }
    }

    public void buscarPorId() {
        System.out.print("Ingrese ID de la mascota: ");
        int id = input.nextInt();
        input.nextLine();

        Mascota mascota = imascotasDAO.obtenerPorId(id);
        if (mascota != null) {
            imprimirMascota(mascota);
        } else {
            System.out.println("Mascota no encontrada.");
        }
    }

    public void obtenerPorDuenoDocumento() {
        System.out.print("Ingrese el documento de identificación del dueño: ");
        int documento = input.nextInt();
        input.nextLine();

        List<Mascota> mascotas = imascotasDAO.obtenerPorDuenoDocumento(documento);

        if (mascotas.isEmpty()) {
            System.out.println("No se encontraron mascotas para el dueño con documento " + documento);
        } else {
            System.out.println("Mascotas del dueño con documento " + documento + ":");
            for (Mascota m : mascotas) {
                imprimirMascota(m);
            }
        }
    }

    public void obtenerPorRazaId() {
        // Usamos la implementación concreta del DAO
        RazaDAO razaDAO = new RazaDAO();
        List<Raza> razas = razaDAO.obtenerTodos();

        if (razas.isEmpty()) {
            System.out.println("No hay razas registradas.");
            return;
        }

        System.out.println("=== LISTA DE RAZAS DISPONIBLES ===");
        for (Raza r : razas) {
            System.out.println(r.getId() + " - " + r.getNombre());
        }

        // Preguntar al usuario
        System.out.print("Ingrese el ID de la raza: ");
        int razaId = input.nextInt();
        input.nextLine();

        List<Mascota> mascotas = imascotasDAO.obtenerPorRazaId(razaId);

        if (mascotas.isEmpty()) {
            System.out.println("No se encontraron mascotas para la raza con ID " + razaId);
        } else {
            System.out.println("=== Mascotas de la raza ID " + razaId + " ===");
            for (Mascota m : mascotas) {
                imprimirMascota(m);
            }
        }
    }

    public void obtenerPorEspecieId() {
        // Usamos la implementación concreta del DAO
        EspecieDAO especieDAO = new EspecieDAO();
        List<Especie> especies = especieDAO.obtenerTodos();

        if (especies.isEmpty()) {
            System.out.println("No hay especies registradas.");
            return;
        }

        System.out.println("=== LISTA DE ESPECIES DISPONIBLES ===");
        for (Especie e : especies) {
            System.out.println(e.getId() + " - " + e.getNombre());
        }

        // Preguntar al usuario
        System.out.print("Ingrese el ID de la especie: ");
        int especieId = input.nextInt();
        input.nextLine();

        List<Mascota> mascotas = imascotasDAO.obtenerPorEspecieId(especieId);

        if (mascotas.isEmpty()) {
            System.out.println("No se encontraron mascotas para la especie con ID " + especieId);
        } else {
            System.out.println("=== Mascotas de la especie ID " + especieId + " ===");
            for (Mascota m : mascotas) {
                imprimirMascota(m);
            }
        }
    }

    public void imprimirMascota(Mascota mascota) {
        if (mascota == null) {
            System.out.println("No se encontró la Mascota.");
            return;
        }

        System.out.println("------ INFORMACION DE LA MASCOTA ------");
        System.out.println("ID: " + mascota.getId());
        System.out.println("ID Dueño: " + mascota.getDueno_id());
        System.out.println("Nombre: " + mascota.getNombre());
        System.out.println("Fecha Nacimiento: " + mascota.getFecha_nacimiento());
        System.out.println("ID Especie: " + mascota.getEspecie_id());
        System.out.println("Sexo: " + mascota.getSexo());
        System.out.println("URL Foto: " + mascota.getUrl_foto());
        System.out.println("Microchip: " + mascota.getMicrochip());
        System.out.println("Estado: " + mascota.getEstado());
        System.out.println("----------------------------------");
    }



}
