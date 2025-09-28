package model.entities.Items_Factura;

import model.entities.Duenos.Dueno;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Facturas {
    private Integer id;
    private Integer dueno_id;
    private LocalDateTime fecha_emision;
    private Double total;
    private String metodo_pago;

    public Facturas(Integer id, Integer dueno_id, LocalDateTime fecha_emision, Double total, String metodo_pago) {
        this.id = id;
        this.dueno_id = dueno_id;
        this.fecha_emision = fecha_emision;
        this.total = total;
        this.metodo_pago = metodo_pago;

    }

    public Integer getId() {
        return id;
    }

    public Integer getDueno_id() {
        return dueno_id;
    }

    public void setDueno_id(Integer dueno_id) {
        this.dueno_id = dueno_id;
    }

    public LocalDateTime getFecha_emision() {
        return fecha_emision;
    }

    public void setFecha_emision(LocalDateTime fecha_emision) {
        this.fecha_emision = fecha_emision;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getMetodo_pago() {return metodo_pago;}

    public void setMetodo_pago(String metodo_pago) {this.metodo_pago = metodo_pago;}

    @Override
    public String toString() {
        return "FacturasDAO{" +
                "id=" + id +
                ", dueno_id=" + dueno_id +
                ", fecha_emision=" + fecha_emision +
                ", total=" + total +
                ", metodo_pago='" + metodo_pago + '\'' +
                '}';
    }
}
