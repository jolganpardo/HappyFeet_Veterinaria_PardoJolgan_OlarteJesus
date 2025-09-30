package view.cita;

import controller.citaController.CitaController;
import model.entities.Citas.Cita;
import model.entities.Citas.CitaEstado;
import model.entities.Veterinarios.Veterinario;
import model.repository.CitasDAO.CitaDAO;
import model.repository.CitasDAO.CitaEstadoDAO;
import model.repository.CitasDAO.ICitaDAO;
import model.repository.VeterinariosDAO.IVeterinariosDAO;
import model.repository.VeterinariosDAO.VeterinarioDAO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class MenuCita {
    private ICitaDAO citaDAO;
    private CitaController controller;
    private Scanner input;

    public MenuCita() {
        this.citaDAO = new CitaDAO();
        this.input = new Scanner(System.in);
        this.controller = new CitaController(new VeterinarioDAO());
    }


    public void mostrarMenuCita(){
        int opcion;
        do {
            System.out.print("""
                \n=== MENU CITA ===
                1. Ingresar una cita.
                2. Obtener cita por ID.
                3. Mostrar todas las citas.
                4. Actualizar una cita.
                5. Cancelar una cita por el ID.
                6. Mostrar citas por veterinario ID.
                0. Salir del menu de citas.
                Selecciona una opción:
                """);
            opcion = input.nextInt();
            input.nextLine();

            switch (opcion){
                case 1:
                    agregarCita();
                    break;
                case 2:
                    buscarPorId();
                    break;
                case 3:
                    listarCitas();
                    break;
                case 4:
                    actualizarCita();
                    break;
                case 5:
                    cancelarCita();
                    break;
                case 6:
                    listarPorVeterinario();
                    break;
                case 0:
                    System.out.println("Saliendo... del menu de citas..");
                    System.out.println("Volviendo al menu principal");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        }while (opcion != 0);
    }

    public void agregarCita() {
        System.out.println("\n--- Agregar Cita ---");

        int mascotaId = leerEntero("Ingrese ID de la mascota: ");
        System.out.print("Ingrese motivo: ");
        String motivo = input.nextLine();

        CitaEstadoDAO estadoDAO = new CitaEstadoDAO();
        List<CitaEstado> estados = estadoDAO.obtenerTodos();

        System.out.println("Seleccione el estado de la cita:");
        for (int i = 0; i < estados.size(); i++) {
            System.out.println((i + 1) + ". " + estados.get(i).getNombre());
        }
        System.out.print("Ingrese número (Enter para 'Programada'): ");
        String entrada = input.nextLine();
        CitaEstado estadoSeleccionado = entrada.isBlank() ?
                estadoDAO.obtenerPorNombre("Programada") :
                estados.get(Integer.parseInt(entrada) - 1);

        LocalDateTime fechaHora = controller.obtenerProximaFechaLibre();
        if (fechaHora == null) {
            System.out.println("No hay fechas disponibles.");
            return;
        }

        Veterinario veterinario = controller.obtenerVeterinarioDisponible(fechaHora);
        if (veterinario == null) {
            System.out.println("No hay veterinarios disponibles.");
            return;
        }

        // Guardar cita y obtener el objeto completo con ID
        Cita citaCreada = controller.agregarCita(
                mascotaId,
                fechaHora,
                motivo,
                estadoSeleccionado.getId(),
                veterinario.getId()
        );

        imprimirCita(citaCreada);
    }

    public void buscarPorId() {
        System.out.print("ID de la cita: ");
        int id = input.nextInt();
        input.nextLine();
        Cita cita = citaDAO.obtenerPorId(id);
        if (cita != null) {
            imprimirCita(cita);
        } else {
            System.out.println("No existe cita con ese ID.");
        }
    }

    public void listarCitas() {
        List<Cita> citas = citaDAO.obtenerTodos();
        System.out.println("\nLista de citas:");
        for (Cita c : citas) {
            imprimirCita(c);
        }
    }

    public void actualizarCita() {
        System.out.print("ID de la cita a actualizar: ");
        int id = leerEntero();
        Cita cita = citaDAO.obtenerPorId(id);

        if (cita == null) {
            System.out.println("No existe cita con ese ID.");
            return;
        }

        // --- Motivo ---
        System.out.print("Nuevo motivo (" + cita.getMotivo() + "): ");
        String nuevoMotivo = input.nextLine();
        if (!nuevoMotivo.isEmpty()) cita.setMotivo(nuevoMotivo);

        // --- Fecha y hora ---
        System.out.print("Nueva fecha y hora (yyyy-MM-dd HH:mm) (" + cita.getFecha_hora() + "): ");
        String nuevaFecha = input.nextLine();
        LocalDateTime fechaFinal = cita.getFecha_hora();
        if (!nuevaFecha.isEmpty()) {
            try {
                fechaFinal = LocalDateTime.parse(nuevaFecha, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                cita.setFecha_hora(fechaFinal);
            } catch (DateTimeParseException e) {
                System.out.println("Formato inválido, se mantiene la fecha actual.");
            }
        }

        // --- Estado ---
        CitaEstadoDAO estadoDAO = new CitaEstadoDAO();
        List<CitaEstado> estados = estadoDAO.obtenerTodos();
        System.out.println("Seleccione el estado de la cita:");
        for (int i = 0; i < estados.size(); i++) {
            System.out.println((i + 1) + ". " + estados.get(i).getNombre());
        }
        System.out.print("Ingrese número (Enter para mantener actual): ");
        String estadoEntrada = input.nextLine();
        if (!estadoEntrada.isBlank()) {
            try {
                int opcion = Integer.parseInt(estadoEntrada);
                if (opcion >= 1 && opcion <= estados.size()) {
                    cita.setEstado_id(estados.get(opcion - 1).getId());
                } else {
                    System.out.println("Opción inválida, se mantiene el estado actual.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida, se mantiene el estado actual.");
            }
        }

        // --- Veterinario ---
        List<Veterinario> vetsDisponibles = controller.obtenerVeterinariosDisponibles(fechaFinal);
        if (!vetsDisponibles.isEmpty()) {
            System.out.println("Veterinarios disponibles para esa fecha:");
            for (int i = 0; i < vetsDisponibles.size(); i++) {
                System.out.println((i + 1) + ". " + vetsDisponibles.get(i).getNombre_completo());
            }
            System.out.print("Seleccione veterinario (Enter para mantener actual): ");
            String vetEntrada = input.nextLine();
            if (!vetEntrada.isBlank()) {
                try {
                    int opcion = Integer.parseInt(vetEntrada);
                    if (opcion >= 1 && opcion <= vetsDisponibles.size()) {
                        cita.setVeterinario_id(vetsDisponibles.get(opcion - 1).getId());
                    } else {
                        System.out.println("Opción inválida, se mantiene el veterinario actual.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida, se mantiene el veterinario actual.");
                }
            }
        } else {
            System.out.println("No hay veterinarios disponibles para la fecha seleccionada. Se mantiene el actual.");
        }

        // --- Guardar cambios ---
        citaDAO.actualizarCita(cita);
        System.out.println("Cita actualizada con éxito.");
        imprimirCita(cita);
    }


    public void cancelarCita() {
        System.out.print("ID de la cita a cancelar: ");
        int id = input.nextInt();
        input.nextLine();
        Cita cita = citaDAO.obtenerPorId(id);
        if (cita != null) {
            citaDAO.cancelarCita(id);
            System.out.println("Cita cancelada con éxito.");
        } else {
            System.out.println("No existe cita con ese ID.");
        }
    }

    public void listarPorVeterinario() {
        // Mostrar todos los veterinarios para que el usuario escoja
        IVeterinariosDAO veterinarioDAO = new VeterinarioDAO();
        List<Veterinario> veterinarios = veterinarioDAO.listarTodos();

        System.out.println("=== VETERINARIOS DISPONIBLES ===");
        for (Veterinario v : veterinarios) {
            System.out.println(v.getId() + " - " + v.getNombre_completo());
        }

        System.out.print("Ingrese el ID del veterinario para listar sus citas: ");
        int vetId = input.nextInt();
        input.nextLine();

        // Obtener citas del veterinario seleccionado
        List<Cita> citas = citaDAO.obtenerPorVeterinarioId(vetId);

        if (citas.isEmpty()) {
            System.out.println("No hay citas para este veterinario.");
        } else {
            System.out.println("\nCitas del veterinario:");
            for (Cita c : citas) {
                imprimirCita(c);
            }
        }
    }


    private int leerEntero() {
        while (true) {
            try {
                return Integer.parseInt(input.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida, ingrese un número: ");
            }
        }
    }

    private int leerEntero(String mensaje) {
        System.out.print(mensaje);
        return leerEntero();
    }

    public void imprimirCita(Cita cita) {
        if (cita == null) {
            System.out.println("No se encontró la cita.");
            return;
        }

        // DAO de veterinarios
        IVeterinariosDAO veterinarioDAO = new VeterinarioDAO();
        Veterinario vet = null;
        if (cita.getVeterinario_id() != null) {
            vet = veterinarioDAO.buscarPorId(cita.getVeterinario_id());
        }

        // DAO de estados
        CitaEstadoDAO estadoDAO = new CitaEstadoDAO();
        CitaEstado estado = null;
        if (cita.getEstado_id() != null) {
            estado = estadoDAO.obtenerPorId(cita.getEstado_id());
        }

        System.out.println("------ INFORMACION DE LA CITA ------");
        System.out.println("ID: " + cita.getId());
        System.out.println("Mascota ID: " + cita.getMascota_id());
        System.out.println("Fecha y Hora: " + cita.getFecha_hora());
        System.out.println("Motivo: " + cita.getMotivo());
        if (estado != null) {
            System.out.println("Estado: " + estado.getNombre());
        } else {
            System.out.println("Estado: No asignado");
        }
        System.out.println("Veterinario ID: " + cita.getVeterinario_id());
        if (vet != null) {
            System.out.println("Veterinario: " + vet.getNombre_completo());
        } else {
            System.out.println("Veterinario: No asignado");
        }
        System.out.println("----------------------------------");
    }


}