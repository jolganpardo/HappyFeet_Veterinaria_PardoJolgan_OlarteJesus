package service;

import model.entities.Duenos.Dueno;
import model.entities.Mascotas.*;
import model.repository.MascotasDAO.*;
import model.repository.duenosDAO.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class MascotaService {

    private final IMascotasDAO mascotaDAO;
    private final IDuenosDAO duenosDAO;
    private final EspecieDAO especieDAO;
    private final RazaDAO razaDAO;

    public MascotaService(IMascotasDAO mascotaDAO) {
        this.mascotaDAO = mascotaDAO;
        this.duenosDAO = new DuenoDAO();
        this.especieDAO = new EspecieDAO();
        this.razaDAO = new RazaDAO();
    }

    public Mascota agregarMascota(String documento, String nombre, int razaId, int especieId,
                                  String fechaNacimiento, String sexo, String urlFoto, String microchip) {

        String docValidado = validarDocumentoDueno(documento);
        validarEspecie(especieId);
        validarRaza(razaId, especieId);
        String nombreValidado = validarTexto(nombre, "Nombre", 1, 100);
        LocalDate fechaValidada = validarFecha(fechaNacimiento);
        String sexoValidado = validarSexo(sexo);
        String microchipValidado = validarMicrochip(microchip);

        Mascota mascota = new Mascota(
                null,
                null,
                nombreValidado,
                razaId,
                especieId,
                fechaValidada,
                sexoValidado,
                urlFoto.isEmpty() ? null : urlFoto,
                microchipValidado.isEmpty() ? null : microchipValidado,
                "ACTIVA"
        );

        int idGenerado = mascotaDAO.agregarMascota(mascota, docValidado);
        mascota.setId(idGenerado);
        return mascota;
    }

    public void actualizarMascota(int id, String nombre, String sexo, String duenoStr) {
        Mascota mascota = mascotaDAO.obtenerPorId(id);
        if (mascota == null || "INACTIVA".equalsIgnoreCase(mascota.getEstado())) {
            throw new RuntimeException("Mascota no encontrada o INACTIVA");
        }

        if (!nombre.isEmpty()) {
            String nombreValidado = validarTexto(nombre, "Nombre", 1, 100);
            mascota.setNombre(nombreValidado);
        }

        if (!sexo.isEmpty()) {
            String sexoValidado = validarSexo(sexo);
            mascota.setSexo(sexoValidado);
        }

        if (!duenoStr.isEmpty()) {
            int nuevoDuenoId = Integer.parseInt(duenoStr);
            List<Dueno> duenos = duenosDAO.listarDuenos();
            if (duenos.stream().anyMatch(d -> d.getId() == nuevoDuenoId)) {
                mascota.setDuenos_id(nuevoDuenoId);
            } else {
                throw new RuntimeException("ID de dueño inválido");
            }
        }

        mascotaDAO.actualizarMascota(mascota);
    }

    public void cambiarEstadoMascota(int id) {
        Mascota mascota = mascotaDAO.obtenerPorId(id);
        if (mascota == null) {
            throw new RuntimeException("Mascota no encontrada");
        }
        if ("INACTIVA".equalsIgnoreCase(mascota.getEstado())) {
            throw new RuntimeException("La mascota ya está INACTIVA");
        }
        mascotaDAO.cambiarEstadoMascota(id);
    }

    public List<Mascota> obtenerTodos() {
        return mascotaDAO.obtenerTodos();
    }

    public Mascota obtenerPorId(int id) {
        return mascotaDAO.obtenerPorId(id);
    }

    public List<Mascota> obtenerPorDuenoDocumento(String documentoStr) {
        if (!documentoStr.matches("\\d+")) {
            throw new RuntimeException("Documento inválido");
        }
        int documento = Integer.parseInt(documentoStr);
        return mascotaDAO.obtenerPorDuenoDocumento(documento);
    }

    public List<Mascota> obtenerPorRazaId(String idStr) {
        int id = Integer.parseInt(idStr);
        List<Raza> razas = razaDAO.obtenerTodos();
        if (razas.isEmpty()) {
            throw new RuntimeException("No hay razas registradas");
        }
        return mascotaDAO.obtenerPorRazaId(id);
    }

    public List<Mascota> obtenerPorEspecieId(String idStr) {
        int id = Integer.parseInt(idStr);
        List<Especie> especies = especieDAO.obtenerTodos();
        if (especies.isEmpty()) {
            throw new RuntimeException("No hay especies registradas");
        }
        return mascotaDAO.obtenerPorEspecieId(id);
    }

    public List<Especie> listarEspecies() {
        return especieDAO.obtenerTodos();
    }

    public List<Raza> listarRazasPorEspecie(int especieId) {
        return razaDAO.obtenerPorEspecieId(especieId);
    }

    public List<Raza> listarRazas() {
        return razaDAO.obtenerTodos();
    }

    private String validarDocumentoDueno(String doc) {
        if (!doc.matches("\\d{6,20}")) {
            throw new RuntimeException("Documento inválido. Debe contener entre 6 y 20 dígitos.");
        }

        Dueno dueno = duenosDAO.buscarPorDocumento(doc);
        if (dueno == null) {
            throw new RuntimeException("No existe un dueño con ese documento.");
        }
        return doc;
    }

    private void validarEspecie(int especieId) {
        List<Especie> especies = especieDAO.obtenerTodos();
        if (especies.isEmpty()) {
            throw new RuntimeException("No hay especies registradas.");
        }

        if (especies.stream().noneMatch(e -> e.getId() == especieId)) {
            throw new RuntimeException("ID de especie no existe.");
        }
    }

    private void validarRaza(int razaId, int especieId) {
        List<Raza> razas = razaDAO.obtenerPorEspecieId(especieId);
        if (razas.isEmpty()) {
            throw new RuntimeException("No hay razas registradas para esta especie.");
        }

        if (razas.stream().noneMatch(r -> r.getId() == razaId)) {
            throw new RuntimeException("ID de raza no existe.");
        }
    }

    private String validarTexto(String texto, String campo, int min, int max) {
        if (texto.length() < min || texto.length() > max) {
            throw new RuntimeException(campo + " inválido. Longitud permitida: " + min + "-" + max);
        }
        return texto;
    }

    private LocalDate validarFecha(String fechaStr) {
        try {
            LocalDate fecha = LocalDate.parse(fechaStr);
            if (fecha.isAfter(LocalDate.now())) {
                throw new RuntimeException("La fecha no puede ser futura.");
            }
            return fecha;
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Formato de fecha inválido. Use yyyy-MM-dd.");
        }
    }

    private String validarSexo(String sexo) {
        if (!sexo.equalsIgnoreCase("Macho") && !sexo.equalsIgnoreCase("Hembra")) {
            throw new RuntimeException("Sexo inválido. Debe ser 'Macho' o 'Hembra'.");
        }
        return sexo.substring(0, 1).toUpperCase() + sexo.substring(1).toLowerCase();
    }

    private String validarMicrochip(String microchip) {
        if (!microchip.isEmpty() && !microchip.matches("[A-Za-z0-9]{5,20}")) {
            throw new RuntimeException("Microchip inválido (5-20 caracteres alfanuméricos).");
        }
        return microchip;
    }
}