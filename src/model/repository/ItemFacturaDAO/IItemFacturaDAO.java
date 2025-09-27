package model.repository.ItemFacturaDAO;

import model.entities.Items_Factura.ItemFactura;
import java.util.List;

public interface IItemFacturaDAO {
    void insertar(ItemFactura item);
    ItemFactura obtenerPorId(Integer id);
    List<ItemFactura> obtenerPorFacturaId(Integer facturaId);
    List<ItemFactura> obtenerTodos();
    void actualizar(ItemFactura item);
    void eliminar(Integer id);
}
