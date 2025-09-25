package model.dao.Producto_Tipos;

public class Producto_TipoDAO {
    private Integer id;
    private String nombre;

    public Producto_TipoDAO(String nombre) {
        this.nombre = nombre;
    }

    public Producto_TipoDAO(Integer id, String nombre) {
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
        return "Producto_TipoDAO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
