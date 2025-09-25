package model.entities.Inventario;

import java.time.LocalDate;

public class Inventario {
    private Integer id;
    private String nombre_producto;
    private Producto_Tipo producto_tipo_id;
    private String descripcion;
    private String fabricante;
    private String lote;
    private Integer cantidad_stock;
    private Integer stock_minimo;
    private LocalDate fecha_vencimiento;
    private Integer precio_venta;
    private Proveedores proveedores_id;
    private LocalDate fecha_ultima_compra;

    public Inventario(Integer id, String nombre_producto, Producto_Tipo producto_tipo_id, String descripcion, String fabricante, String lote, Integer cantidad_stock, Integer stock_minimo, LocalDate fecha_vencimiento, Integer precio_venta, Proveedores proveedores_id, LocalDate fecha_ultima_compra) {
        this.id = id;
        this.nombre_producto = nombre_producto;
        this.producto_tipo_id = producto_tipo_id;
        this.descripcion = descripcion;
        this.fabricante = fabricante;
        this.lote = lote;
        this.cantidad_stock = cantidad_stock;
        this.stock_minimo = stock_minimo;
        this.fecha_vencimiento = fecha_vencimiento;
        this.precio_venta = precio_venta;
        this.proveedores_id = proveedores_id;
        this.fecha_ultima_compra = fecha_ultima_compra;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public Producto_Tipo getProducto_tipo_id() {
        return producto_tipo_id;
    }

    public void setProducto_tipo_id(Producto_Tipo producto_tipo_id) {
        this.producto_tipo_id = producto_tipo_id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public Integer getCantidad_stock() {
        return cantidad_stock;
    }

    public void setCantidad_stock(Integer cantidad_stock) {
        this.cantidad_stock = cantidad_stock;
    }

    public Integer getStock_minimo() {
        return stock_minimo;
    }

    public void setStock_minimo(Integer stock_minimo) {
        this.stock_minimo = stock_minimo;
    }

    public LocalDate getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(LocalDate fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public Integer getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(Integer precio_venta) {
        this.precio_venta = precio_venta;
    }

    public Proveedores getProveedores_id() {
        return proveedores_id;
    }

    public void setProveedores_id(Proveedores proveedores_id) {
        this.proveedores_id = proveedores_id;
    }

    public LocalDate getFecha_ultima_compra() {
        return fecha_ultima_compra;
    }

    public void setFecha_ultima_compra(LocalDate fecha_ultima_compra) {
        this.fecha_ultima_compra = fecha_ultima_compra;
    }

    @Override
    public String toString() {
        return "Inventario{" +
                "id=" + id +
                ", nombre_producto='" + nombre_producto + '\'' +
                ", producto_tipo_id=" + producto_tipo_id +
                ", descripcion='" + descripcion + '\'' +
                ", fabricante='" + fabricante + '\'' +
                ", lote='" + lote + '\'' +
                ", cantidad_stock=" + cantidad_stock +
                ", stock_minimo=" + stock_minimo +
                ", fecha_vencimiento=" + fecha_vencimiento +
                ", precio_venta=" + precio_venta +
                ", proveedores_id=" + proveedores_id +
                ", fecha_ultima_compra=" + fecha_ultima_compra +
                '}';
    }
}
