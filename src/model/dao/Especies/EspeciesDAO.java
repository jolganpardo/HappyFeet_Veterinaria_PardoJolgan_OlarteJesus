package model.dao.Especies;

public class EspeciesDAO {
    private Integer id;
    private String nombre;

    public EspeciesDAO(String nombre) {
        this.nombre = nombre;
    }

    public EspeciesDAO(Integer id, String nombre) {
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
