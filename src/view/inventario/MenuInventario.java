package view.inventario;

import controller.inventarioController.InventarioController;
import model.entities.Inventario.Inventario;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MenuInventario {
    private final InventarioController controller;
    private final Scanner scanner;

    public MenuInventario() {
        this.controller = new InventarioController();
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

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Tipo de producto (ID): 1=Medicamento, 2=Vacuna, 3=Insumo Médico, 4=Alimento, 5=Otro");
        int tipoId = scanner.nextInt(); scanner.nextLine();

        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();

        System.out.print("Fabricante: ");
        String fabricante = scanner.nextLine();

        System.out.print("Lote: ");
        String lote = scanner.nextLine();

        System.out.print("Cantidad en stock: ");
        int cantidad = scanner.nextInt();

        System.out.print("Stock mínimo: ");
        int minimo = scanner.nextInt(); scanner.nextLine();

        System.out.print("Fecha de vencimiento (YYYY-MM-DD o enter): ");
        String fechaStr = scanner.nextLine();
        LocalDate fechaVenc = fechaStr.isEmpty() ? null : LocalDate.parse(fechaStr);

        System.out.print("Precio de venta: ");
        double precio = scanner.nextDouble();

        System.out.print("Proveedor (ID): ");
        int proveedorId = scanner.nextInt(); scanner.nextLine();


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
}
