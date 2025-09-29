package controller.citaController;

import model.entities.Citas.Cita;
import model.entities.Duenos.Dueno;
import model.repository.CitasDAO.ICitaDAO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class CitaController {
    private final ICitaDAO citaDAO;
    private final Scanner input;

    public CitaController(ICitaDAO citaDAO, Scanner input) {
        this.citaDAO = citaDAO;
        this.input = input;
    }


}
