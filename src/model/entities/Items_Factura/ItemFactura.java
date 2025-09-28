package model.entities.Items_Factura;

import model.entities.Inventario.ProductoTipo;

public class ItemFactura {
    private Integer id;
    private Integer factura_id;
    private Integer producto_id;
    private String servicio_descripcion;
    private Integer cantidad;
    private Integer precio_unitario;
    private Integer subtotal;

    public ItemFactura(Integer id, Integer factura_id, Integer producto_id, String servicio_descripcion, Integer cantidad, Integer precio_unitario, Integer subtotal) {
        this.id = id;
        this.factura_id = factura_id;
        this.producto_id = producto_id;
        this.servicio_descripcion = servicio_descripcion;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
        this.subtotal = subtotal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {this.id = id;}

    public Integer getFactura_id() {
        return factura_id;
    }

    public void setFactura_id(Integer factura_id) {
        this.factura_id = factura_id;
    }

    public Integer getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(Integer producto_id) {
        this.producto_id = producto_id;
    }

    public String getServicio_descripcion() {
        return servicio_descripcion;
    }

    public void setServicio_descripcion(String servicio_descripcion) {
        this.servicio_descripcion = servicio_descripcion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(Integer precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public Integer getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Integer subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "Items_Factura{" +
                "id=" + id +
                ", factura_id=" + factura_id +
                ", producto_id=" + producto_id +
                ", servicio_descripcion='" + servicio_descripcion + '\'' +
                ", cantidad=" + cantidad +
                ", precio_unitario=" + precio_unitario +
                ", subtotal=" + subtotal +
                '}';
    }
}
