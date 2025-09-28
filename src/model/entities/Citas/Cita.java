package model.entities.Citas;

import model.entities.Mascotas.Mascota;
import model.entities.Veterinarios.Veterinario;

import java.time.LocalDateTime;

public class Cita {
    private Integer id;
    private Integer mascota_id;
    private LocalDateTime fecha_hora;
    private String motivo;
    private Integer estado_id;
    private Integer veterianrio_id;

    public Cita(Integer id, Integer mascota_id, LocalDateTime fecha_hora, String motivo, Integer estado_id, Integer veterianrio_id) {
        this.id = id;
        this.mascota_id = mascota_id;
        this.fecha_hora = fecha_hora;
        this.motivo = motivo;
        this.estado_id = estado_id;
        this.veterianrio_id = veterianrio_id;
    }

    public Integer getId() {
        return id;
    }

    public Integer getMascota_id() {
        return mascota_id;
    }

    public void setMascota_id(Integer mascota_id) {
        this.mascota_id = mascota_id;
    }

    public LocalDateTime getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(LocalDateTime fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Integer getEstado_id() {
        return estado_id;
    }

    public void setEstado_id(Integer estado_id) {
        this.estado_id = estado_id;
    }

    public Integer getVeterianrio_id() {
        return veterianrio_id;
    }

    public void setVeterianrio_id(Integer veterianrio_id) {
        this.veterianrio_id = veterianrio_id;
    }

    @Override
    public String toString() {
        return "CitasDAO{" +
                "id=" + id +
                ", mascota_id=" + mascota_id +
                ", fecha_hora=" + fecha_hora +
                ", motivo='" + motivo + '\'' +
                ", estado_id=" + estado_id +
                ", veterianrio_id=" + veterianrio_id +
                '}';
    }
}
