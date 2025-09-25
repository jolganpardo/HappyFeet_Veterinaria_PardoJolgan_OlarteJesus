package model.entities.Items_Factura;

import model.entities.Duenos.Dueno;

import java.time.LocalDate;

public class Facturas {
    private Integer id;
    private Dueno dueno_id;
    private LocalDate fecha_emision;
    private Integer total;

    public Facturas(Integer id, Dueno dueno_id, LocalDate fecha_emision, Integer total) {
        this.id = id;
        this.dueno_id = dueno_id;
        this.fecha_emision = fecha_emision;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public Dueno getDueno_id() {
        return dueno_id;
    }

    public void setDueno_id(Dueno dueno_id) {
        this.dueno_id = dueno_id;
    }

    public LocalDate getFecha_emision() {
        return fecha_emision;
    }

    public void setFecha_emision(LocalDate fecha_emision) {
        this.fecha_emision = fecha_emision;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "FacturasDAO{" +
                "id=" + id +
                ", dueno_id=" + dueno_id +
                ", fecha_emision=" + fecha_emision +
                ", total=" + total +
                '}';
    }
}
