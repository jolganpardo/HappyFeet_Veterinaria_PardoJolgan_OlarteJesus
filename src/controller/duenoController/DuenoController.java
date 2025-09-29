package controller.duenoController;

import model.entities.Duenos.Dueno;
import model.repository.duenosDAO.DuenoDAO;
import model.repository.duenosDAO.IDuenosDAO;

import java.util.Scanner;

public class DuenoController {
    private IDuenosDAO iduenoDAO;
    private Scanner input;

    public DuenoController(IDuenosDAO iduenoDAO, Scanner input) {
        this.iduenoDAO = iduenoDAO;
        this.input = input;
    }


}
