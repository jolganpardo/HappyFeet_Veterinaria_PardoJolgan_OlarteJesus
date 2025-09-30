package view.inventario;

import controller.inventarioController.InventarioController;
import model.entities.Inventario.Inventario;
import model.entities.Inventario.ProductoTipo;
import model.repository.InventarioDAO.IInventarioDAO;
import model.repository.InventarioDAO.InventarioDAO;
import model.repository.InventarioDAO.ProductoTipoDAO;
import model.repository.InventarioDAO.ProveedorDAO;
import service.InventarioService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MenuInventario {
    private final InventarioController controller;
    private final Scanner scanner;

    public MenuInventario() {
        IInventarioDAO inventarioDAO = new InventarioDAO();
        InventarioService inventarioService = new InventarioService(inventarioDAO);
        this.controller = new InventarioController(inventarioService);
        this.scanner = new Scanner(System.in);
    }


    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n===== MENÚ INVENTARIO =====");
            System.out.println("1. Agregar producto");
            System.out.println("2. Ver todos los productos");
            System.out.println("3. Buscar producto por ID");
            System.out.println("4. Actualizar producto");
            System.out.println("5. Eliminar producto");
            System.out.println("6. Ver productos con bajo stock");
            System.out.println("7. Buscar productos por proveedor");
            System.out.println("8. Buscar productos por tipo");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1 -> agregarProducto();
                case 2 -> verTodos();
                case 3 -> buscarPorId();
                case 4 -> actualizarProducto();
                case 5 -> eliminarProducto();
                case 6 -> productosBajoStock();
                case 7 -> buscarPorProveedor();
                case 8 -> buscarPorTipo();
                case 0 -> System.out.println("Saliendo del menú de Inventario...");
                default -> System.out.println("Opción no válida, intente nuevamente.");
            }
        } while (opcion != 0);
    }

    private void agregarProducto() {
        System.out.println("\n--- Agregar Producto ---");

        // Agregar Nombre
        String nombre = validarNombreProducto();

        // Tipo de producto
        ProductoTipoDAO tipoDAO = new ProductoTipoDAO();
        System.out.println("=== TIPOS DISPONIBLES ===");
        for (ProductoTipo t : tipoDAO.obtenerTodos()) {
            System.out.println(t.getId() + " - " + t.getNombre());
        }
        int tipoId = validarTipoProducto();

        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();

        System.out.print("Fabricante: ");
        String fabricante = scanner.nextLine();

        System.out.print("Lote: ");
        String lote = scanner.nextLine();

        int cantidad = validarCantidad("Cantidad en stock: ");
        int minimo = validarCantidad("Stock mínimo: ");

        LocalDate fechaVenc = validarFechaVencimiento();

        double precio = validarPrecio();

        int proveedorId = validarProveedor();


        Inventario inv = new Inventario(null, nombre, tipoId, descripcion, fabricante, lote,
                cantidad, minimo, fechaVenc, precio, proveedorId);

        controller.agregarInventario(inv);
    }

    private void verTodos() {
        System.out.println("\n--- Lista de Productos ---");
        List<Inventario> lista = controller.obtenerTodos();
        lista.forEach(System.out::println);
    }

    private void buscarPorId() {
        System.out.print("Ingrese ID del producto: ");
        int id = scanner.nextInt();
        controller.obtenerInventarioPorId(id);
    }

    private void actualizarProducto() {
        System.out.print("Ingrese ID del producto a actualizar: ");
        int id = scanner.nextInt(); scanner.nextLine();

        Inventario inv = controller.obtenerInventarioPorId(id);
        if (inv == null) return;

        System.out.println("Deje vacío para mantener el valor actual.");

        System.out.print("Nuevo nombre (" + inv.getNombre_producto() + "): ");
        String nombre = scanner.nextLine();
        if (!nombre.isEmpty()) inv.setNombre_producto(nombre);

        System.out.print("Nuevo stock (" + inv.getCantidad_stock() + "): ");
        String stockStr = scanner.nextLine();
        if (!stockStr.isEmpty()) inv.setCantidad_stock(Integer.parseInt(stockStr));

        System.out.print("Nuevo precio (" + inv.getPrecio_venta() + "): ");
        String precioStr = scanner.nextLine();
        if (!precioStr.isEmpty()) inv.setPrecio_venta(Double.parseDouble(precioStr));

        controller.actualizarInventario(inv);
    }

    private void eliminarProducto() {
        System.out.print("Ingrese ID del producto a eliminar: ");
        int id = scanner.nextInt();
        controller.eliminarInventario(id);
    }

    private void productosBajoStock() {
        System.out.println("\n--- Productos con Bajo Stock ---");
        List<Inventario> lista = controller.obtenerProductosBajoStock();
        lista.forEach(System.out::println);
    }

    private void buscarPorProveedor() {
        System.out.print("Ingrese ID del proveedor: ");
        int provId = scanner.nextInt();
        List<Inventario> lista = controller.obtenerPorProveedor(provId);
        lista.forEach(System.out::println);
    }

    private void buscarPorTipo() {
        System.out.print("Ingrese ID del tipo de producto: ");
        int tipoId = scanner.nextInt();
        List<Inventario> lista = controller.obtenerPorTipoProducto(tipoId);
        lista.forEach(System.out::println);
    }


    // Validar nombre no vacío
    private String validarNombreProducto() {
        String nombre;
        do {
            System.out.print("Nombre: ");
            nombre = scanner.nextLine().trim();
            if (nombre.isEmpty()) System.out.println("El nombre no puede estar vacío.");
        } while (nombre.isEmpty());
        return nombre;
    }

    // Validar ID de tipo
    private int validarTipoProducto() {
        int tipoId;
        ProductoTipoDAO tipoDAO = new ProductoTipoDAO();
        do {
            System.out.print("Tipo de producto (ID): ");
            tipoId = scanner.nextInt(); scanner.nextLine();
            if (tipoDAO.obtenerPorId(tipoId) == null) {
                System.out.println("Tipo de producto no existe.");
                tipoId = -1;
            }
        } while (tipoId == -1);
        return tipoId;
    }

    // Validar proveedor
    private int validarProveedor() {
        int proveedorId;
        ProveedorDAO provDAO = new ProveedorDAO();
        do {
            System.out.print("Proveedor (ID): ");
            proveedorId = scanner.nextInt(); scanner.nextLine();
            if (provDAO.obtenerPorId(proveedorId) == null) {
                System.out.println("Proveedor no existe.");
                proveedorId = -1;
            }
        } while (proveedorId == -1);
        return proveedorId;
    }

    // Validar cantidad o precio no negativo
    private int validarCantidad(String mensaje) {
        int cantidad;
        do {
            System.out.print(mensaje);
            cantidad = scanner.nextInt(); scanner.nextLine();
            if (cantidad < 0) System.out.println("No puede ser negativo.");
        } while (cantidad < 0);
        return cantidad;
    }

    // Validar fecha de vencimiento
    private LocalDate validarFechaVencimiento() {
        LocalDate fecha = null;
        while (true) {
            System.out.print("Fecha de vencimiento (YYYY-MM-DD o enter): ");
            String f = scanner.nextLine().trim();
            if (f.isEmpty()) break;
            try {
                fecha = LocalDate.parse(f);
                if (fecha.isBefore(LocalDate.now())) {
                    System.out.println("La fecha no puede ser anterior a hoy.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Formato inválido.");
            }
        }
        return fecha;
    }

    private double validarPrecio() {
        double precio = -1;
        while (precio < 0) {
            System.out.print("Precio de venta: ");
            String inputStr = scanner.nextLine().trim();
            try {
                precio = Double.parseDouble(inputStr);
                if (precio < 0) System.out.println("El precio no puede ser negativo.");
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número válido.");
            }
        }
        return precio;
    }


}
