package controller.mascotaController;

import model.entities.Duenos.Dueno;
import model.entities.Mascotas.*;
import model.repository.MascotasDAO.*;
import model.repository.duenosDAO.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class MascotaController {

    private final IMascotasDAO mascotaDAO;
    private final Scanner input;

    public MascotaController(IMascotasDAO mascotaDAO, Scanner input) {
        this.mascotaDAO = mascotaDAO;
        this.input = input;
    }

    public void agregarMascota() {
        System.out.println("=== AGREGAR MASCOTA ===");

        String documento = validarDocumentoDueno();
        if (documento == null) return;

        int especieId = validarEspecie();
        if (especieId <= 0) return;

        int razaId = validarRaza(especieId);
        if (razaId <= 0) return;

        String nombre = leerTexto("Nombre de la mascota", 1, 100);
        if (nombre == null) return;

        LocalDate fechaNacimiento = validarFecha("Fecha de nacimiento (yyyy-MM-dd)");
        if (fechaNacimiento == null) return;

        String sexo = validarSexo();
        if (sexo == null) return;

        String urlFoto = leerOpcional("URL de la foto (opcional)");
        String microchip = validarMicrochip();

        Mascota mascota = new Mascota(
                null,
                null,
                nombre,
                razaId,
                especieId,
                fechaNacimiento,
                sexo,
                urlFoto.isEmpty() ? null : urlFoto,
                microchip.isEmpty() ? null : microchip,
                "ACTIVA"
        );

        try {
            int idGenerado = mascotaDAO.agregarMascota(mascota, documento);
            mascota.setId(idGenerado);
            System.out.println("Mascota agregada con éxito. ID: " + idGenerado);
            imprimirMascota(mascota);
        } catch (Exception e) {
            System.out.println("Error al agregar mascota: " + e.getMessage());
        }
    }

    // Continuación de tu clase MascotaController

    public void actualizarMascota() {
        System.out.println("=== ACTUALIZAR MASCOTA ===");
        System.out.print("Ingrese ID de la mascota: ");
        int id = Integer.parseInt(input.nextLine().trim());
        try {
            Mascota mascota = mascotaDAO.obtenerPorId(id);
            if (mascota == null || "INACTIVA".equalsIgnoreCase(mascota.getEstado())) {
                System.out.println("Mascota no encontrada o INACTIVA");
                return;
            }

            System.out.print("Nuevo nombre (" + mascota.getNombre() + "): ");
            String nombre = input.nextLine().trim();
            if (!nombre.isEmpty()) mascota.setNombre(nombre);

            System.out.print("Nuevo sexo (" + mascota.getSexo() + "): ");
            String sexo = input.nextLine().trim();
            if (!sexo.isEmpty()) mascota.setSexo(sexo);

            IDuenosDAO duenosDAO = new DuenoDAO();
            List<Dueno> duenos = duenosDAO.listarDuenos();
            System.out.print("Nuevo ID de dueño (" + mascota.getDueno_id() + "): ");
            String duenoStr = input.nextLine().trim();
            if (!duenoStr.isEmpty()) {
                int nuevoDuenoId = Integer.parseInt(duenoStr);
                if (duenos.stream().anyMatch(d -> d.getId() == nuevoDuenoId)) {
                    mascota.setDuenos_id(nuevoDuenoId);
                } else {
                    System.out.println("ID de dueño inválido, se mantiene el actual.");
                }
            }

            mascotaDAO.actualizarMascota(mascota);
            System.out.println("Mascota actualizada correctamente.");
        } catch (Exception e) {
            System.out.println("Error al actualizar mascota: " + e.getMessage());
        }
    }

    public void cambiarEstadoMascota() {
        System.out.println("=== CAMBIAR ESTADO DE MASCOTA ===");
        System.out.print("Ingrese ID de la mascota: ");
        int id = Integer.parseInt(input.nextLine().trim());
        try {
            Mascota mascota = mascotaDAO.obtenerPorId(id);
            if (mascota == null) {
                System.out.println("Mascota no encontrada");
                return;
            }
            if ("INACTIVA".equalsIgnoreCase(mascota.getEstado())) {
                System.out.println("La mascota ya está INACTIVA");
                return;
            }
            mascotaDAO.cambiarEstadoMascota(id);
            System.out.println("Mascota marcada como INACTIVA");
        } catch (Exception e) {
            System.out.println("Error al cambiar estado: " + e.getMessage());
        }
    }

    public void listarMascotas() {
        System.out.println("=== LISTA DE MASCOTAS ===");
        try {
            List<Mascota> lista = mascotaDAO.obtenerTodos();
            if (lista.isEmpty()) {
                System.out.println("No hay mascotas registradas");
                return;
            }
            for (Mascota m : lista) imprimirMascota(m);
        } catch (Exception e) {
            System.out.println("Error al listar mascotas: " + e.getMessage());
        }
    }

    public void buscarPorId() {
        System.out.print("Ingrese ID de la mascota: ");
        int id = Integer.parseInt(input.nextLine().trim());
        try {
            Mascota mascota = mascotaDAO.obtenerPorId(id);
            if (mascota != null) imprimirMascota(mascota);
            else System.out.println("Mascota no encontrada");
        } catch (Exception e) {
            System.out.println("Error al buscar mascota: " + e.getMessage());
        }
    }

    public void obtenerPorDuenoDocumento() {
        System.out.print("Ingrese documento del dueño: ");
        String docStr = input.nextLine().trim();
        if (!docStr.matches("\\d+")) {
            System.out.println("Documento inválido");
            return;
        }
        int documento = Integer.parseInt(docStr);
        try {
            List<Mascota> mascotas = mascotaDAO.obtenerPorDuenoDocumento(documento);
            if (mascotas.isEmpty()) {
                System.out.println("No se encontraron mascotas para este dueño");
                return;
            }
            for (Mascota m : mascotas) imprimirMascota(m);
        } catch (Exception e) {
            System.out.println("Error al buscar mascotas: " + e.getMessage());
        }
    }

    public void obtenerPorRazaId() {
        RazaDAO razaDAO = new RazaDAO();
        try {
            List<Raza> razas = razaDAO.obtenerTodos();
            if (razas.isEmpty()) {
                System.out.println("No hay razas registradas");
                return;
            }
            for (Raza r : razas) System.out.println(r.getId() + " - " + r.getNombre());
            System.out.print("Ingrese ID de la raza: ");
            int id = Integer.parseInt(input.nextLine().trim());
            List<Mascota> mascotas = mascotaDAO.obtenerPorRazaId(id);
            if (mascotas.isEmpty()) System.out.println("No se encontraron mascotas para esta raza");
            else for (Mascota m : mascotas) imprimirMascota(m);
        } catch (Exception e) {
            System.out.println("Error al buscar mascotas: " + e.getMessage());
        }
    }

    public void obtenerPorEspecieId() {
        EspecieDAO especieDAO = new EspecieDAO();
        try {
            List<Especie> especies = especieDAO.obtenerTodos();
            if (especies.isEmpty()) {
                System.out.println("No hay especies registradas");
                return;
            }
            for (Especie e : especies) System.out.println(e.getId() + " - " + e.getNombre());
            System.out.print("Ingrese ID de la especie: ");
            int id = Integer.parseInt(input.nextLine().trim());
            List<Mascota> mascotas = mascotaDAO.obtenerPorEspecieId(id);
            if (mascotas.isEmpty()) System.out.println("No se encontraron mascotas para esta especie");
            else for (Mascota m : mascotas) imprimirMascota(m);
        } catch (Exception e) {
            System.out.println("Error al buscar mascotas: " + e.getMessage());
        }
    }


    private String validarDocumentoDueno() {
        System.out.print("Documento del dueño: ");
        String doc = input.nextLine().trim();

        if (!doc.matches("\\d{6,20}")) {
            System.out.println("Documento inválido. Debe contener entre 6 y 20 dígitos.");
            return null;
        }

        IDuenosDAO duenosDAO = new DuenoDAO();
        try {
            Dueno dueno = duenosDAO.buscarPorDocumento(doc);
            if (dueno == null) {
                System.out.println("No existe un dueño con ese documento.");
                return null;
            }
            return doc;
        } catch (Exception e) {
            System.out.println("Error al validar documento: " + e.getMessage());
            return null;
        }
    }

    private int validarEspecie() {
        EspecieDAO dao = new EspecieDAO();
        try {
            List<Especie> especies = dao.obtenerTodos();
            if (especies.isEmpty()) {
                System.out.println("No hay especies registradas.");
                return -1;
            }

            System.out.println("=== ESPECIES DISPONIBLES ===");
            for (Especie e : especies) {
                System.out.println(e.getId() + " - " + e.getNombre());
            }

            System.out.print("Ingrese el ID de la especie: ");
            int id = Integer.parseInt(input.nextLine().trim());
            if (especies.stream().anyMatch(e -> e.getId() == id)) {
                return id;
            } else {
                System.out.println("ID de especie no existe.");
                return -1;
            }
        } catch (Exception e) {
            System.out.println("Error al listar especies: " + e.getMessage());
            return -1;
        }
    }

    private int validarRaza(int especieId) {
        RazaDAO dao = new RazaDAO();
        try {
            List<Raza> razas = dao.obtenerPorEspecieId(especieId);
            if (razas.isEmpty()) {
                System.out.println("No hay razas registradas para esta especie.");
                return -1;
            }

            System.out.println("=== RAZAS DISPONIBLES ===");
            for (Raza r : razas) {
                System.out.println(r.getId() + " - " + r.getNombre());
            }

            System.out.print("Ingrese el ID de la raza: ");
            int id = Integer.parseInt(input.nextLine().trim());
            if (razas.stream().anyMatch(r -> r.getId() == id)) {
                return id;
            } else {
                System.out.println("ID de raza no existe.");
                return -1;
            }
        } catch (Exception e) {
            System.out.println("Error al listar razas: " + e.getMessage());
            return -1;
        }
    }

    private String leerTexto(String campo, int min, int max) {
        System.out.print(campo + ": ");
        String texto = input.nextLine().trim();
        if (texto.length() < min || texto.length() > max) {
            System.out.println(campo + " inválido. Longitud permitida: " + min + "-" + max);
            return null;
        }
        return texto;
    }


    private LocalDate validarFecha(String mensaje) {
        System.out.print(mensaje + ": ");
        try {
            LocalDate fecha = LocalDate.parse(input.nextLine().trim());
            if (fecha.isAfter(LocalDate.now())) {
                System.out.println("La fecha no puede ser futura.");
                return null;
            }
            return fecha;
        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha inválido. Use yyyy-MM-dd.");
            return null;
        }
    }

    private String validarSexo() {
        System.out.print("Sexo (Macho/Hembra): ");
        String sexo = input.nextLine().trim();
        if (!sexo.equalsIgnoreCase("Macho") && !sexo.equalsIgnoreCase("Hembra")) {
            System.out.println("Sexo inválido. Debe ser 'Macho' o 'Hembra'.");
            return null;
        }
        return sexo.substring(0, 1).toUpperCase() + sexo.substring(1).toLowerCase();
    }

    private String leerOpcional(String mensaje) {
        System.out.print(mensaje + ": ");
        return input.nextLine().trim();
    }

    private String validarMicrochip() {
        System.out.print("Microchip (opcional): ");
        String microchip = input.nextLine().trim();
        if (!microchip.isEmpty() && !microchip.matches("[A-Za-z0-9]{5,20}")) {
            System.out.println("Microchip inválido (5-20 caracteres alfanuméricos).");
            return "";
        }
        return microchip;
    }

    public void imprimirMascota(Mascota mascota) {
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
