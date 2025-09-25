package model.entities.Mascotas;

public class Especies {
    private Integer id;
    private String nombre;

    public Especies(Integer id, String nombre) {
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
        return "EspeciesDAO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
