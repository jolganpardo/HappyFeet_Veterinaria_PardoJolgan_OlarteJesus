package model.dao.Evento_Tipos;

public class Evento_TiposDAO {
    private Integer id;
    private String nombre;

    public Evento_TiposDAO(String nombre) {
        this.nombre = nombre;
    }

    public Evento_TiposDAO(Integer id, String nombre) {
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
