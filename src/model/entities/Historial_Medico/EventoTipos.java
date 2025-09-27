package model.entities.Historial_Medico;

public class EventoTipos {
    private Integer id;
    private String nombre;

    public EventoTipos(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Evento_TiposDAO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
