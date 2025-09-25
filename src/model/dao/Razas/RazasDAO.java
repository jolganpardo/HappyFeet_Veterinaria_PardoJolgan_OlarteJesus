package model.dao.Razas;

import model.dao.Especies.EspeciesDAO;

public class RazasDAO {
    private int id;
    private String nombre;
    private EspeciesDAO especie_id; // relación con Especie

    public RazasDAO(String nombre, EspeciesDAO especiesDAO) {
        this.nombre = nombre;
        this.especie_id = especiesDAO;
    }

    public RazasDAO(int id, String nombre, EspeciesDAO especie_id) {
        this.id = id;
        this.nombre = nombre;
        this.especie_id = especie_id;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public EspeciesDAO getEspecie_id() {
        return especie_id;
    }

    public void setEspecie_id(EspeciesDAO especie_id) {
        this.especie_id = especie_id;
    }
}
