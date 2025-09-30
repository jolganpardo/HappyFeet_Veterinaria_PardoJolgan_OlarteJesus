package model.entities.Historial_Medico;

import model.entities.Mascotas.Mascota;
import model.entities.Veterinarios.Veterinario;

import java.time.LocalDate;

public class HistorialMedico {
    private Integer id;
    private Integer mascota_id;
    private Integer veterinario_id;
    private LocalDate fecha_evento;
    private Integer evento_tipo_id;
    private String descripcion;
    private String diagnostico;
    private String tratamiento_recomendado;

    public HistorialMedico(Integer id, Integer mascota_id, Integer veterinario_id, LocalDate fecha_evento, Integer evento_tipo_id, String descripcion, String diagnostico, String tratamiento_recomendado) {
        this.id = id;
        this.mascota_id = mascota_id;
        this.veterinario_id = veterinario_id;
        this.fecha_evento = fecha_evento;
        this.evento_tipo_id = evento_tipo_id;
        this.descripcion = descripcion;
        this.diagnostico = diagnostico;
        this.tratamiento_recomendado = tratamiento_recomendado;
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

    public Integer getVeterinario_id() {
        return veterinario_id;
    }

    public void setVeterinario_id(Integer veterinario_id) {
        this.veterinario_id = veterinario_id;
    }

    public LocalDate getFecha_evento() {
        return fecha_evento;
    }

    public void setFecha_evento(LocalDate fecha_evento) {
        this.fecha_evento = fecha_evento;
    }

    public Integer getEvento_tipo_id() {
        return evento_tipo_id;
    }

    public void setEvento_tipo_id(Integer evento_tipo_id) {
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

    @Override
    public String toString() {
        return "HistorialMedico{" +
                "id=" + id +
                ", mascota_id=" + mascota_id +
                ", veterinario_id=" + veterinario_id +
                ", fecha_evento=" + fecha_evento +
                ", evento_tipo_id=" + evento_tipo_id +
                ", descripcion='" + descripcion + '\'' +
                ", diagnostico='" + diagnostico + '\'' +
                ", tratamiento_recomendado='" + tratamiento_recomendado + '\'' +
                '}';
    }
}
