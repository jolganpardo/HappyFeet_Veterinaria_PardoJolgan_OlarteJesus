package model.dao.Historial_Medico;

import model.dao.Evento_Tipos.Evento_TiposDAO;
import model.dao.Mascotas.MascotasDAO;

import java.time.LocalDate;

public class Historial_MedicoDAO {
    private Integer id;
    private MascotasDAO mascota_id;
    private LocalDate fecha_evento;
    private Evento_TiposDAO evento_tipo_id;
    private String descripcion;
    private String diagnostico;
    private String tratamiento_recomendado;

    public Historial_MedicoDAO(MascotasDAO mascota_id, LocalDate fecha_evento, Evento_TiposDAO evento_tipo_id, String descripcion, String diagnostico, String tratamiento_recomendado) {
        this.mascota_id = mascota_id;
        this.fecha_evento = fecha_evento;
        this.evento_tipo_id = evento_tipo_id;
        this.descripcion = descripcion;
        this.diagnostico = diagnostico;
        this.tratamiento_recomendado = tratamiento_recomendado;
    }

    public Historial_MedicoDAO(Integer id, MascotasDAO mascota_id, LocalDate fecha_evento, Evento_TiposDAO evento_tipo_id, String descripcion, String diagnostico, String tratamiento_recomendado) {
        this.id = id;
        this.mascota_id = mascota_id;
        this.fecha_evento = fecha_evento;
        this.evento_tipo_id = evento_tipo_id;
        this.descripcion = descripcion;
        this.diagnostico = diagnostico;
        this.tratamiento_recomendado = tratamiento_recomendado;
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

    public LocalDate getFecha_evento() {
        return fecha_evento;
    }

    public void setFecha_evento(LocalDate fecha_evento) {
        this.fecha_evento = fecha_evento;
    }

    public Evento_TiposDAO getEvento_tipo_id() {
        return evento_tipo_id;
    }

    public void setEvento_tipo_id(Evento_TiposDAO evento_tipo_id) {
        this.evento_tipo_id = evento_tipo_id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTratamiento_recomendado() {
        return tratamiento_recomendado;
    }

    public void setTratamiento_recomendado(String tratamiento_recomendado) {
        this.tratamiento_recomendado = tratamiento_recomendado;
    }
}
