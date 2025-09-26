package model.repository.Items_FacturaDAO;

import model.entities.Items_Factura.Servicios;

import java.util.List;

public interface IServiciosDAO {
    void insertar(Servicios servicio);
    Servicios obtenerPorId(Integer id);
    List<Servicios> obtenerTodos();
    void actualizar(Servicios servicio);
    void eliminar(Integer id);
}
