package controller.mascotaController;

import model.entities.Mascotas.Mascota;
import model.repository.MascotasDAO.IMascotasDAO;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MascotaController {
    private IMascotasDAO imascotasDAO;
    private Scanner input;

    public MascotaController(IMascotasDAO imascotasDAO, Scanner input) {
        this.imascotasDAO = imascotasDAO;
        this.input = input;
    }


}
