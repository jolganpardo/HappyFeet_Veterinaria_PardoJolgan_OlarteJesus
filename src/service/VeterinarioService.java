package service;

import model.entities.Veterinarios.Veterinario;
import model.repository.VeterinariosDAO.IVeterinariosDAO;

import java.util.List;

public class VeterinarioService {
    private final IVeterinariosDAO veterinarioDAO;

    public VeterinarioService(IVeterinariosDAO veterinarioDAO) {
        this.veterinarioDAO = veterinarioDAO;
    }

    public Integer agregarVeterinario(Veterinario veterinario) {
        return veterinarioDAO.insertar(veterinario);
    }

    public void actualizarVeterinario(Veterinario veterinario) {
        veterinarioDAO.actualizar(veterinario);
    }

    public void eliminarVeterinario(Integer id) {
        veterinarioDAO.eliminar(id);
    }

    public Veterinario buscarVeterinarioPorId(Integer id) {
        return veterinarioDAO.buscarPorId(id);
    }

    public List<Veterinario> obtenerTodosLosVeterinarios() {
        return veterinarioDAO.listarTodos();
    }
}