package model.entities.Mascotas;

import java.time.LocalDate;

public class Adopcion {
    private Integer id;
    private Integer mascota_id;
    private String estado;
    private LocalDate fecha_inicio;
    private LocalDate fecha_fin;

    // Constructor para insertar nueva adopción sin id
    public Adopcion(Integer mascota_id, String estado, LocalDate fecha_inicio, LocalDate fecha_fin) {
        this.mascota_id = mascota_id;
        this.estado = estado;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
    }

    // Constructor completo
    public Adopcion(Integer id, Integer mascota_id, String estado, LocalDate fecha_inicio, LocalDate fecha_fin) {
        this.id = id;
        this.mascota_id = mascota_id;
        this.estado = estado;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
    }

    public Integer getId() { return id; }

    public Integer getMascotaId() { return mascota_id; }

    public void setMascotaId(Integer mascota_id) { this.mascota_id = mascota_id; }

    public String getEstado() { return estado; }

    public void setEstado(String estado) { this.estado = estado; }

    public LocalDate getFechaInicio() { return fecha_inicio; }

    public void setFechaInicio(LocalDate fecha_inicio) { this.fecha_inicio = fecha_inicio; }

    public LocalDate getFechaFin() { return fecha_fin; }

    public void setFechaFin(LocalDate fecha_fin) { this.fecha_fin = fecha_fin; }

    @Override
    public String toString() {
        return "Adopcion{" +
                "id=" + id +
                ", mascota_id=" + mascota_id +
                ", estado='" + estado + '\'' +
                ", fecha_inicio=" + fecha_inicio +
                ", fecha_fin=" + fecha_fin +
                '}';
    }
}
