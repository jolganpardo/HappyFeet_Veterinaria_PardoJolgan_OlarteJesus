package service;

import model.entities.Items_Factura.Factura;
import model.repository.ItemFacturaDAO.IFacturaDAO;

import java.util.List;

public class FacturaService {
    private final IFacturaDAO facturaDAO;

    public FacturaService(IFacturaDAO facturaDAO) {
        this.facturaDAO = facturaDAO;
    }

    public void agregarFactura(Factura f) {
        facturaDAO.insertar(f);
    }

    public void actualizarFactura(Factura f) {
        facturaDAO.actualizar(f);
    }

    public void eliminarFactura(int id) {
        facturaDAO.eliminar(id);
    }

    public Factura buscarPorId(int id) {
        return facturaDAO.obtenerPorId(id);
    }

    public List<Factura> listarFacturas() {
        return facturaDAO.obtenerTodas();
    }
}
