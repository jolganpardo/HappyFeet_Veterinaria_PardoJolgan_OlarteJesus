package model.dao.Facturas;

import model.dao.Duenos.DuenosDAO;

import java.time.LocalDate;

public class FacturasDAO {
    private Integer id;
    private DuenosDAO dueno_id;
    private LocalDate fecha_emision;
    private Integer total;

    public FacturasDAO(DuenosDAO dueno_id, LocalDate fecha_emision, Integer total) {
        this.dueno_id = dueno_id;
        this.fecha_emision = fecha_emision;
        this.total = total;
    }

    public FacturasDAO(Integer id, DuenosDAO dueno_id, LocalDate fecha_emision, Integer total) {
        this.id = id;
        this.dueno_id = dueno_id;
        this.fecha_emision = fecha_emision;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public DuenosDAO getDueno_id() {
        return dueno_id;
    }

    public void setDueno_id(DuenosDAO dueno_id) {
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
