package model.dao;

public class Raza {
    //Declaracion de variables
    private Integer id;
    private Integer especie_id;
    private String nombre;

    //Constructores
    public Raza(Integer especie_id, String nombre) {
        this.especie_id = especie_id;
        this.nombre = nombre;
    }

    public Raza(Especie especie, String nombre) {
        this.especie_id = especie.getId();
        this.nombre = nombre;
    }

    public Raza(Integer id, Integer especie_id, String nombre) {
        this.id = id;
        this.especie_id = especie_id;
        this.nombre = nombre;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public Integer getEspecie_id() {
        return especie_id;
    }

    public void setEspecie_id(Integer especie_id) {
        this.especie_id = especie_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
