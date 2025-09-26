package model.entities.Mascotas;

public class Raza {
    private Integer id;
    private Especie especie_id; // relación con Especie
    private String nombre;

    public Raza(Integer id, Especie especie_id, String nombre) {
        this.id = id;
        this.especie_id = especie_id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Especie getEspecie_id() {
        return especie_id;
    }

    public void setEspecie_id(Especie especie_id) {
        this.especie_id = especie_id;
    }

    @Override
    public String toString() {
        return "Razas{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", especie_id=" + especie_id +
                '}';
    }
}
