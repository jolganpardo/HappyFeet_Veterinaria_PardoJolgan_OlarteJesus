package model.entities.Mascotas;

public class Razas {
    private Integer id;
    private String nombre;
    private Especies especie_id; // relación con Especie

    public Razas(Integer id, String nombre, Especies especie_id) {
        this.id = id;
        this.nombre = nombre;
        this.especie_id = especie_id;
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

    public Especies getEspecie_id() {
        return especie_id;
    }

    public void setEspecie_id(Especies especie_id) {
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
