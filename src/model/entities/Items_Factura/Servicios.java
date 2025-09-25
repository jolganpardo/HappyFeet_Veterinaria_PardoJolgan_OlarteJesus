package model.entities.Items_Factura;

public class Servicios {
    private Integer id;
    private String nombre;
    private Integer precio;

    public Servicios(Integer id, String nombre, Integer precio) {
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
        return "Servicios{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                '}';
    }
}
