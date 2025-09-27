package model.entities.Citas;

public class CitaEstado {
    private Integer id;
    private String nombre;

    public CitaEstado(Integer id, String nombre) {
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
        return "Cita_EstadosDAO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';

    }
}
