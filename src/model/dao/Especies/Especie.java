package model.dao.Especies;

public class Especie {
    //Declaracion de variables
    private Integer id;
    private String nombre;

    //Constructor

    public Especie(String nombre) {
        this.nombre = nombre;
    }

    public Especie(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    //Getters and Setters
    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
