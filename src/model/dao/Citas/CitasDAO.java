package model.dao.Citas;

import model.dao.Cita_Estados.Cita_EstadosDAO;
import model.dao.Mascotas.MascotasDAO;
import model.dao.Veterinarios.VeterinariosDAO;

import java.time.LocalDate;

public class CitasDAO {
    private Integer id;
    private MascotasDAO mascota_id;
    private LocalDate fecha_hora;
    private String motivo;
    private Cita_EstadosDAO estado_id;
    private VeterinariosDAO veterianrio_id;

    public CitasDAO(MascotasDAO mascota_id, LocalDate fecha_hora, String motivo, Cita_EstadosDAO estado_id, VeterinariosDAO veterianrio_id) {
        this.mascota_id = mascota_id;
        this.fecha_hora = fecha_hora;
        this.motivo = motivo;
        this.estado_id = estado_id;
        this.veterianrio_id = veterianrio_id;
    }

    public CitasDAO(Integer id, MascotasDAO mascota_id, LocalDate fecha_hora, String motivo, Cita_EstadosDAO estado_id, VeterinariosDAO veterianrio_id) {
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

    public MascotasDAO getMascota_id() {
        return mascota_id;
    }

    public void setMascota_id(MascotasDAO mascota_id) {
        this.mascota_id = mascota_id;
    }

    public LocalDate getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(LocalDate fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Cita_EstadosDAO getEstado_id() {
        return estado_id;
    }

    public void setEstado_id(Cita_EstadosDAO estado_id) {
        this.estado_id = estado_id;
    }

    public VeterinariosDAO getVeterianrio_id() {
        return veterianrio_id;
    }

    public void setVeterianrio_id(VeterinariosDAO veterianrio_id) {
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
