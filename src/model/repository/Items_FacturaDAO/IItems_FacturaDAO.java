package model.repository.Items_FacturaDAO;

import model.entities.Items_Factura.Items_Factura;

import java.util.List;

public interface IItems_FacturaDAO {
    void insertar(Items_Factura item);
    Items_Factura obtenerPorId(Integer id);
    List<Items_Factura> obtenerTodos();
    List<Items_Factura> obtenerPorFacturaId(Integer facturaId);
    void actualizar(Items_Factura item);
    void eliminar(Integer id);
}
