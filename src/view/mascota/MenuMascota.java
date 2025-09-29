package view.mascota;

import controller.mascotaController.MascotaController;
import model.entities.Mascotas.Mascota;
import model.repository.MascotasDAO.IMascotasDAO;
import model.repository.MascotasDAO.MascotaDAO;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MenuMascota {
    private IMascotasDAO mascotasDAO;
    private MascotaController mascotaController;
    private Scanner input;

    public MenuMascota() {
        this.mascotasDAO = new MascotaDAO();
        this.input = new Scanner(System.in);
        this.mascotaController = new MascotaController(mascotasDAO, input);
    }

    public void mostrarMenuMascota() {
        int opcion;
        do {
            System.out.print("""
                    \n === MENU MASCOTA ===
                    1. Agregar Mascota.
                    2. Mostrar mascota por ID.
                    3. Mostrar todas las mascotas.
                    4. Actualizar mascota.
                    5. Cambiar estado de la mascota (Activa, Inactiva).
                    6. Mascota por ID del dueño.
                    7. Mostrar mascota por raza.
                    >>> Ingrese la opcion:""");
            opcion = input.nextInt();
            input.nextLine();

            switch (opcion){
                case 1:
                    agregarMascota();
                    break;
                case 2:
                    buscarPorId();
                    break;
                case 3:
                    listarMascotas();
                    break;
                case 4:
                    actualizarMascota();
                    break;
                case 5:
                    cambiarEstadoMascota();
                    break;
                case 6:
                    obtenerPorDuenoId();
                    break;
                case 7:
                    obtenerPorRazaId();
                    break;
                case 0:
                    System.out.println("Saliendo... del menu de mascotas..");
                    System.out.println("Volviendo al menu principal");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        }while (opcion != 0);
    }
    public void agregarMascota() {
        System.out.println("=== AGREGAR MASCOTA ===");

        System.out.print("ID del dueño: ");
        int duenoId = input.nextInt();
        input.nextLine();

        System.out.print("Nombre de la mascota: ");
        String nombre = input.nextLine();

        System.out.print("ID de la raza: (ej: 1=Perro, 2=Gato, 3=Ave, 4=Conejo) ");
        int razaId = input.nextInt();
        input.nextLine();

        System.out.print("Fecha de nacimiento (yyyy-MM-dd): ");
        String fechaStr = input.nextLine();
        LocalDate fecha = LocalDate.parse(fechaStr);

        System.out.print("Sexo (Macho/Hembra): ");
        String sexo = input.nextLine();

        System.out.print("URL de la foto (opcional): ");
        String urlFoto = input.nextLine();

        Mascota mascota = new Mascota(null, duenoId, nombre, razaId, fecha, sexo, urlFoto, null, "ACTIVA");
        mascotasDAO.agregarMascota(mascota);

        System.out.println("Mascota agregada con éxito.");
    }

    public void actualizarMascota() {
        System.out.println("=== ACTUALIZAR MASCOTA ===");

        System.out.print("ID de la mascota a actualizar: ");
        int id = input.nextInt();
        input.nextLine();

        Mascota mascota = mascotasDAO.obtenerPorId(id);
        if (mascota == null) {
            System.out.println("No se encontró la mascota con ID " + id);
            return;
        }

        System.out.print("Nuevo nombre (" + mascota.getNombre() + "): ");
        String nombre = input.nextLine();
        if (!nombre.isEmpty()) mascota.setNombre(nombre);

        System.out.print("Nueva raza ID (" + mascota.getRaza_id() + "): ");
        String razaStr = input.nextLine();
        if (!razaStr.isEmpty()) mascota.setRaza_id(Integer.parseInt(razaStr));

        System.out.print("Nueva fecha de nacimiento (" + mascota.getFecha_nacimiento() + "): ");
        String fechaStr = input.nextLine();
        if (!fechaStr.isEmpty()) mascota.setFecha_nacimiento(LocalDate.parse(fechaStr));

        System.out.print("Nuevo sexo (" + mascota.getSexo() + "): ");
        String sexo = input.nextLine();
        if (!sexo.isEmpty()) mascota.setSexo(sexo);

        System.out.print("Nueva URL foto (" + mascota.getUrl_foto() + "): ");
        String urlFoto = input.nextLine();
        if (!urlFoto.isEmpty()) mascota.setUrl_foto(urlFoto);

        mascotasDAO.actualizarMascota(mascota);
        System.out.println("Mascota actualizada correctamente.");
    }

    public void cambiarEstadoMascota() {
        System.out.println("=== CAMBIAR ESTADO DE MASCOTA (INACTIVA) ===");
        System.out.print("ID de la mascota: ");
        int id = input.nextInt();
        input.nextLine();

        mascotasDAO.cambiarEstadoMascota(id);
        System.out.println("Mascota marcada como INACTIVA.");
    }

    public void listarMascotas() {
        System.out.println("=== LISTA DE MASCOTAS ===");
        List<Mascota> lista = mascotasDAO.obtenerTodos();
        if (lista.isEmpty()) {
            System.out.println("No hay mascotas registradas.");
            return;
        }
        for (Mascota m : lista) {
            System.out.println(m);
        }
    }

    public void buscarPorId() {
        System.out.print("Ingrese ID de la mascota: ");
        int id = input.nextInt();
        input.nextLine();

        Mascota mascota = mascotasDAO.obtenerPorId(id);
        if (mascota != null) {
            System.out.println(mascota);
        } else {
            System.out.println("Mascota no encontrada.");
        }
    }

    public void obtenerPorDuenoId() {
        System.out.print("Ingrese el ID del dueño: ");
        int duenoId = input.nextInt();
        input.nextLine();

        List<Mascota> mascotas = mascotasDAO.obtenerPorDuenoId(duenoId);

        if (mascotas.isEmpty()) {
            System.out.println("No se encontraron mascotas para el dueño con ID " + duenoId);
        } else {
            System.out.println("Mascotas del dueño ID " + duenoId + ":");
            for (Mascota m : mascotas) {
                System.out.println(m);
            }
        }
    }

    public void obtenerPorRazaId() {
        System.out.print("Ingrese el ID de la raza: ");
        int razaId = input.nextInt();
        input.nextLine();

        List<Mascota> mascotas = mascotasDAO.obtenerPorRazaId(razaId);

        if (mascotas.isEmpty()) {
            System.out.println("No se encontraron mascotas para la raza con ID " + razaId);
        } else {
            System.out.println("Mascotas de la raza ID " + razaId + ":");
            for (Mascota m : mascotas) {
                System.out.println(m);
            }
        }
    }
}
