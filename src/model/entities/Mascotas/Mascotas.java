package model.entities.Mascotas;

import model.entities.Duenos.Dueno;

import java.sql.Date;
import java.time.LocalDate;

public class Mascotas {
    private Integer id;
    private Dueno duenos_id;
    private String nombre;
    private Razas raza_id;
    private LocalDate fecha_nacimiento;
    private String sexo; // "Macho" o "Hembra"
    private String url_foto;
    private String estado; // "Activo" o "Inactivo"

    public Mascotas(Integer id, Dueno duenos_id, String nombre, Razas raza_id, LocalDate fecha_nacimiento, String sexo, String url_foto, String estado) {
        this.id = id;
        this.duenos_id = duenos_id;
        this.nombre = nombre;
        this.raza_id = raza_id;
        this.fecha_nacimiento = fecha_nacimiento;
        this.sexo = sexo;
        this.url_foto = url_foto;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public Dueno getDuenos_id() {
        return duenos_id;
    }

    public void setDuenos_id(Dueno duenos_id) {
        this.duenos_id = duenos_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Razas getRaza_id() {
        return raza_id;
    }

    public void setRaza_id(Razas raza_id) {
        this.raza_id = raza_id;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getUrl_foto() {
        return url_foto;
    }

    public void setUrl_foto(String url_foto) {
        this.url_foto = url_foto;
    }

    public Integer getDueno_id() {return  duenos_id.getId();}

    public String getUrl_image() {return  url_foto;}

    public String getEstado() {return estado;}

    public void setEstado(String estado) {this.estado = estado;}

    @Override
    public String toString() {
        return "MascotasDAO{" +
                "id=" + id +
                ", duenos_id=" + duenos_id +
                ", nombre='" + nombre + '\'' +
                ", raza_id=" + raza_id +
                ", fecha_nacimiento=" + fecha_nacimiento +
                ", sexo='" + sexo + '\'' +
                ", url_foto='" + url_foto + '\'' +
                '}';
    }
}
