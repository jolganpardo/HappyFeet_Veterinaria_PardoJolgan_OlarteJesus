package controller.veterinarioController;

import model.entities.Veterinarios.Veterinario;
import model.repository.VeterinariosDAO.IVeterinariosDAO;

import java.util.List;
import java.util.Scanner;

public class VeterinarioController {
    private final IVeterinariosDAO veterinarioDAO;
    private final Scanner input;

    public VeterinarioController(IVeterinariosDAO veterinarioDAO, Scanner input) {
        this.veterinarioDAO = veterinarioDAO;
        this.input = input;
    }


}
