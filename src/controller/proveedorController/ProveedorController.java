package controller.proveedorController;

import model.entities.Inventario.Proveedor;
import model.repository.InventarioDAO.IProveedorDAO;

import java.util.List;
import java.util.Scanner;

public class ProveedorController {
    private final IProveedorDAO proveedorDAO;
    private final Scanner input;

    public ProveedorController(IProveedorDAO proveedorDAO, Scanner input) {
        this.proveedorDAO = proveedorDAO;
        this.input = input;
    }


}
