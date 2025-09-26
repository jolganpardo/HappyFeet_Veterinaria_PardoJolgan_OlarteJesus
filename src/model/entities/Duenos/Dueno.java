package model.entities.Duenos;

public class Dueno {
    //Declaracion de variables
    private Integer id;
    private String nombre_completo;
    private String documento_identidad;
    private String direccion;
    private String telefono;
    private String email;

    public Dueno(Integer id, String nombre_completo, String documento_identidad, String direccion, String telefono, String email) {
        this.id = id;
        this.nombre_completo = nombre_completo;
        this.documento_identidad = documento_identidad;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public String getNombre_completo() {
        return nombre_completo;
    }

    public void setNombre_completo(String nombre_completo) {
        this.nombre_completo = nombre_completo;
    }

    public String getDocumento_identidad() {
        return documento_identidad;
    }

    public void setDocumento_identidad(String documento_identidad) {
        this.documento_identidad = documento_identidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Dueno{" +
                "id=" + id +
                ", nombre_completo='" + nombre_completo + '\'' +
                ", documento_identidad='" + documento_identidad + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
