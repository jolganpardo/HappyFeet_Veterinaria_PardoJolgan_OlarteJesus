package service;

import model.entities.Duenos.Dueno;
import model.repository.duenosDAO.IDuenosDAO;

import java.util.List;

public class DuenoService {

    private final IDuenosDAO duenoDAO;

    public DuenoService(IDuenosDAO duenoDAO) {
        this.duenoDAO = duenoDAO;
    }

    public void agregarDueno(Dueno dueno) {
        duenoDAO.agregarDueno(dueno);
    }

    public void actualizarDueno(Dueno dueno) {
        duenoDAO.actualizarDuenos(dueno);
    }

    public void eliminarDueno(String documento) {
        duenoDAO.eliminarDueno(documento);
    }

    public Dueno buscarPorDocumento(String documento) {
        return duenoDAO.buscarPorDocumento(documento);
    }

    public List<Dueno> listarDuenos() {
        return duenoDAO.listarDuenos();
    }
}
