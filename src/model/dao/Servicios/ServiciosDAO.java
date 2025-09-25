package model.dao.Servicios;

public class ServiciosDAO {
    private Integer id;
    private String nombre;
    private Integer precio;

    public ServiciosDAO(String nombre, Integer precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public ServiciosDAO(Integer id, String nombre, Integer precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "ServiciosDAO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                '}';
    }
}
