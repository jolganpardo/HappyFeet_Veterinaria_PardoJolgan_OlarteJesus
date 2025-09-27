package model.entities.Historial_Medico;

import model.entities.Mascotas.Mascota;
import model.entities.Veterinarios.Veterinario;

import java.time.LocalDate;

public class HistorialMedico {
    private Integer id;
    private Mascota mascota_id;
    private Veterinario veterinario_id;
    private LocalDate fecha_evento;
    private EventoTipos evento_tipo_id;
    private String descripcion;
    private String diagnostico;
    private String tratamiento_recomendado;

    public HistorialMedico(Integer id, Mascota mascota_id, Veterinario veterinario_id, LocalDate fecha_evento, EventoTipos evento_tipo_id, String descripcion, String diagnostico, String tratamiento_recomendado) {
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

    public Mascota getMascota_id() {
        return mascota_id;
    }

    public void setMascota_id(Mascota mascota_id) {
        this.mascota_id = mascota_id;
    }

    public Veterinario getVeterinario_id() {return veterinario_id;}

    public void setVeterinario_id(Veterinario veterinario_id) {this.veterinario_id = veterinario_id;}

    public LocalDate getFecha_evento() {
        return fecha_evento;
    }

    public void setFecha_evento(LocalDate fecha_evento) {
        this.fecha_evento = fecha_evento;
    }

    public EventoTipos getEvento_tipo_id() {
        return evento_tipo_id;
    }

    public void setEvento_tipo_id(EventoTipos evento_tipo_id) {
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
        return "Historial_MedicoDAO{" +
                "id=" + id +
                ", mascota_id=" + mascota_id +
                ", fecha_evento=" + fecha_evento +
                ", evento_tipo_id=" + evento_tipo_id +
                ", descripcion='" + descripcion + '\'' +
                ", diagnostico='" + diagnostico + '\'' +
                ", tratamiento_recomendado='" + tratamiento_recomendado + '\'' +
                '}';
    }
}
