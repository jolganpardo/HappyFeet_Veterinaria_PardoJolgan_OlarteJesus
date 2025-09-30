package model.entities.Mascotas;

import model.entities.Duenos.Dueno;
import java.util.UUID;

import java.time.LocalDate;

public class Mascota {
    private Integer id;
    private Integer duenos_id;
    private String nombre;
    private Integer raza_id;
    private Integer especie_id;
    private LocalDate fecha_nacimiento;
    private String sexo; // "Macho" o "Hembra"
    private String url_foto;
    private String microchip;
    private String estado; // "Activo" o "Inactivo"

    public Mascota(Integer id, Integer duenos_id, String nombre, Integer raza_id, Integer especie_id, LocalDate fecha_nacimiento, String sexo, String url_foto, String microchip, String estado) {
        this.id = id;
        this.duenos_id = duenos_id;
        this.nombre = nombre;
        this.raza_id = raza_id;
        this.especie_id = especie_id;
        this.fecha_nacimiento = fecha_nacimiento;
        this.sexo = sexo;
        this.url_foto = url_foto;
        this.microchip = microchip;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {this.id = id;}

    public Integer getDuenos_id() {
        return duenos_id;
    }

    public void setDuenos_id(Integer duenos_id) {
        this.duenos_id = duenos_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getRaza_id() {
        return raza_id;
    }

    public void setRaza_id(Integer raza_id) {
        this.raza_id = raza_id;
    }

    public Integer getEspecie_id() {return especie_id;}

    public void setEspecie_id(Integer especie_id) {this.especie_id = especie_id;}

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

    public Integer getDueno_id() {return  duenos_id;}

    public String getUrl_image() {return  url_foto;}

    public String getEstado() {return estado;}

    public void setEstado(String estado) {this.estado = estado;}

    public String getMicrochip() {
        if (microchip == null || microchip.isBlank()) {
            // Generar microchp random en caso de que el usuario no ingrese nada
            return "M-" + UUID.randomUUID().toString().substring(0, 8);
        }
        return microchip;
    }


    public void setMicrochip(String microchip) {
        this.microchip = microchip;
    }


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
                ", microchip='" + microchip + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
