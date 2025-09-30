package model.entities.Veterinarios;

public class Veterinario {
    private Integer id;
    private String nombre_completo;
    private String especialidad;
    private String telefono;
    private String email;

    public Veterinario(Integer id, String nombre_completo, String especialidad, String telefono, String email) {
        this.id = id;
        this.nombre_completo = nombre_completo;
        this.especialidad = especialidad;
        this.telefono = telefono;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {this.id=id;}

    public String getNombre_completo() {
        return nombre_completo;
    }

    public void setNombre_completo(String nombre_completo) {
        this.nombre_completo = nombre_completo;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
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
        return "Veterinarios{" +
                "id=" + id +
                ", nombre_completo='" + nombre_completo + '\'' +
                ", especialidad='" + especialidad + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
