package model.dao;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class Mascota {
    // Declaracion de variables
    private Integer id;
    private Integer dueno_id;
    private String nombre;
    private Integer raza_id;
    private Date fecha_nacimiento;
    private String sexo;
    private String url_image;

    // Constructores
     public Mascota(Integer dueno_id, String nombre, Integer raza_id, Date fecha_Nacimiento, String sexo, String url_image) {
         this.dueno_id = dueno_id;
         this.nombre = nombre;
         this.raza_id = raza_id;
         this.fecha_nacimiento = fecha_Nacimiento;
         this.sexo = sexo;
         this.url_image = url_image;
     }

     public Mascota(Dueno dueno, String nombre, Raza raza, Date fecha_nacimiento, String sexo, String url_image) {
         this.dueno_id = dueno.getId();
         this.nombre = nombre;
         this.raza_id = raza.getId();
         this.fecha_nacimiento = fecha_nacimiento;
         this.sexo = sexo;
         this.url_image = url_image;
     }

    public Mascota(Integer id, Integer dueno_id, String nombre, Integer raza_id, Date fecha_Nacimiento, String sexo, String url_image) {
        this.id = id;
        this.dueno_id = dueno_id;
        this.nombre = nombre;
        this.raza_id = raza_id;
        this.fecha_nacimiento = fecha_Nacimiento;
        this.sexo = sexo;
        this.url_image = url_image;
    }

    //Getter calcular edad
    public void edad() {
        if (fecha_nacimiento == null) {
            System.out.println("No hay fecha de nacimiento para calcular edad");
            return;
        }

        LocalDate nacimiento = fecha_nacimiento.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        Period periodo = Period.between(nacimiento, LocalDate.now());

        int anio = periodo.getYears();
        int mes = periodo.getMonths();

        System.out.println(anio + " año(s) y " + mes + " mes(es)");
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public Integer getDueno_id() {
        return dueno_id;
    }

    public void setDueno_id(Integer dueno_id) {
        this.dueno_id = dueno_id;
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

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }
}
