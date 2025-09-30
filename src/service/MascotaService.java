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
    private final IEspeciesDAO especieDAO;
    private final IRazasDAO razaDAO;

    public MascotaService(IMascotasDAO mascotaDAO) {
        this.mascotaDAO = mascotaDAO;
        this.duenosDAO = new DuenoDAO();
        this.especieDAO = new EspecieDAO();
        this.razaDAO = new RazaDAO();
    }

    public Mascota agregarMascota(String documento, String nombre, int razaId, int especieId,
                                  String fechaNacimiento, String sexo, String urlFoto, String microchip) {

        // Validaciones
        String docValidado = validarDocumentoDueno(documento);
        validarEspecie(especieId);
        validarRaza(razaId, especieId);
        String nombreValidado = validarTexto(nombre, "Nombre", 1, 100);
        LocalDate fechaValidada = validarFecha(fechaNacimiento);
        String sexoValidado = validarSexo(sexo);
        String microchipValidado = validarMicrochip(microchip);

        // Crear mascota
        Mascota mascota = new Mascota(
                null,
                null,
                nombreValidado,
                especieId,
                razaId,
                fechaValidada,
                sexoValidado,
                urlFoto.isEmpty() ? null : urlFoto,
                microchipValidado.isEmpty() ? null : microchipValidado,
                "ACTIVA"
        );

        // Guardar en base de datos
        int idGenerado = mascotaDAO.agregarMascota(mascota, docValidado);
        mascota.setId(idGenerado);
        return mascota;
    }

    public void actualizarMascota(int id, String nombre, String sexo, String duenoStr) {
        Mascota mascota = mascotaDAO.obtenerPorId(id);
        if (mascota == null || "INACTIVA".equalsIgnoreCase(mascota.getEstado())) {
            throw new RuntimeException("Mascota no encontrada o INACTIVA");
        }

        // Actualizar nombre si se proporciona
        if (!nombre.isEmpty()) {
            String nombreValidado = validarTexto(nombre, "Nombre", 1, 100);
            mascota.setNombre(nombreValidado);
        }

        // Actualizar sexo si se proporciona
        if (!sexo.isEmpty()) {
            String sexoValidado = validarSexo(sexo);
            mascota.setSexo(sexoValidado);
        }

        // Actualizar dueño si se proporciona
        if (!duenoStr.isEmpty()) {
            try {
                int nuevoDuenoId = Integer.parseInt(duenoStr);
                Dueno dueno = duenosDAO.buscarPorId(nuevoDuenoId);
                if (dueno == null) {
                    throw new RuntimeException("No existe un dueño con ID: " + nuevoDuenoId);
                }
                mascota.setDuenos_id(nuevoDuenoId);
            } catch (NumberFormatException e) {
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
        try {
            int id = Integer.parseInt(idStr);
            Raza raza = razaDAO.obtenerPorId(id);
            if (raza == null) {
                throw new RuntimeException("No existe una raza con ID: " + id);
            }
            return mascotaDAO.obtenerPorRazaId(id);
        } catch (NumberFormatException e) {
            throw new RuntimeException("ID de raza inválido");
        }
    }

    public List<Mascota> obtenerPorEspecieId(String idStr) {
        try {
            int id = Integer.parseInt(idStr);
            Especie especie = especieDAO.obtenerPorId(id);
            if (especie == null) {
                throw new RuntimeException("No existe una especie con ID: " + id);
            }
            return mascotaDAO.obtenerPorEspecieId(id);
        } catch (NumberFormatException e) {
            throw new RuntimeException("ID de especie inválido");
        }
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

    // ===== MÉTODOS DE VALIDACIÓN =====

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
        Especie especie = especieDAO.obtenerPorId(especieId);
        if (especie == null) {
            throw new RuntimeException("No existe una especie con ID: " + especieId);
        }
    }

    private void validarRaza(int razaId, int especieId) {
        Raza raza = razaDAO.obtenerPorId(razaId);
        if (raza == null) {
            throw new RuntimeException("No existe una raza con ID: " + razaId);
        }

        if (raza.getEspecie_id() != especieId) {
            throw new RuntimeException("La raza seleccionada no corresponde a la especie indicada.");
        }
    }

    private String validarTexto(String texto, String campo, int min, int max) {
        if (texto == null || texto.trim().isEmpty()) {
            throw new RuntimeException(campo + " no puede estar vacío.");
        }
        if (texto.length() < min || texto.length() > max) {
            throw new RuntimeException(campo + " inválido. Longitud permitida: " + min + "-" + max);
        }
        return texto.trim();
    }

    private LocalDate validarFecha(String fechaStr) {
        try {
            LocalDate fecha = LocalDate.parse(fechaStr);
            if (fecha.isAfter(LocalDate.now())) {
                throw new RuntimeException("La fecha no puede ser futura.");
            }
            if (fecha.isBefore(LocalDate.now().minusYears(50))) {
                throw new RuntimeException("La fecha es demasiado antigua. Verifique el formato.");
            }
            return fecha;
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Formato de fecha inválido. Use yyyy-MM-dd.");
        }
    }

    private String validarSexo(String sexo) {
        if (sexo == null || sexo.trim().isEmpty()) {
            throw new RuntimeException("Sexo no puede estar vacío.");
        }
        if (!sexo.equalsIgnoreCase("Macho") && !sexo.equalsIgnoreCase("Hembra")) {
            throw new RuntimeException("Sexo inválido. Debe ser 'Macho' o 'Hembra'.");
        }
        return sexo.substring(0, 1).toUpperCase() + sexo.substring(1).toLowerCase();
    }

    private String validarMicrochip(String microchip) {
        if (microchip == null) {
            return "";
        }
        microchip = microchip.trim();
        if (!microchip.isEmpty() && !microchip.matches("[A-Za-z0-9]{5,20}")) {
            throw new RuntimeException("Microchip inválido (5-20 caracteres alfanuméricos).");
        }
        return microchip;
    }
}