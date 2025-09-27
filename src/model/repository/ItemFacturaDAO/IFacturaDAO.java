package model.repository.ItemFacturaDAO;

import model.entities.Items_Factura.Facturas;
import java.util.List;

public interface IFacturaDAO {
    void insertar(Facturas factura);
    Facturas obtenerPorId(Integer id);
    List<Facturas> obtenerTodas();
    void actualizar(Facturas factura);
    void eliminar(Integer id);
}
