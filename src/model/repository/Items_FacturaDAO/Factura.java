package model.repository.Items_FacturaDAO;

import model.entities.Items_Factura.Facturas;

import java.util.List;

public interface Factura {
    void insertar(Facturas factura);
    Facturas obtenerPorId(Integer id);
    List<Facturas> obtenerTodas();
    void actualizar(Facturas factura);
    void eliminar(Integer id);
}
