package model.entities.Items_Factura;

import model.entities.Inventario.Producto_Tipo;

public class Items_Factura {
    private Integer id;
    private Facturas factura_id;
    private Producto_Tipo producto_id;
    private String servicio_descripcion;
    private Integer cantidad;
    private Integer precio_unitario;
    private Integer subtotal;
    private Servicios servicios_id;

    public Items_Factura(Integer id, Facturas factura_id, Producto_Tipo producto_id, String servicio_descripcion, Integer cantidad, Integer precio_unitario, Integer subtotal, Servicios servicios_id) {
        this.id = id;
        this.factura_id = factura_id;
        this.producto_id = producto_id;
        this.servicio_descripcion = servicio_descripcion;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
        this.subtotal = subtotal;
        this.servicios_id = servicios_id;
    }

    public Integer getId() {
        return id;
    }

    public Facturas getFactura_id() {
        return factura_id;
    }

    public void setFactura_id(Facturas factura_id) {
        this.factura_id = factura_id;
    }

    public Producto_Tipo getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(Producto_Tipo producto_id) {
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

    public Servicios getServicios_id() {
        return servicios_id;
    }

    public void setServicios_id(Servicios servicios_id) {
        this.servicios_id = servicios_id;
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
                ", servicios_id=" + servicios_id +
                '}';
    }
}
