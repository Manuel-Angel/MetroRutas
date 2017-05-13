/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rutasmetro;

/**
 *
 * @author root
 */
public class Estacion {
    private int id;
    private int linea;
    private String name;
    private String delegacion;

    public Estacion(int id, int linea, String name, String delegacion) {
        this.id = id;
        this.linea = linea;
        this.name = name;
        this.delegacion = delegacion;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the linea
     */
    public int getLinea() {
        return linea;
    }
    public String getStringLinea(){
        if(linea==10)return "A";
        if(linea==11)return "B";
        return ""+linea;
    }
    /**
     * @param linea the linea to set
     */
    public void setLinea(int linea) {
        this.linea = linea;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the delegacion
     */
    public String getDelegacion() {
        return delegacion;
    }

    /**
     * @param delegacion the delegacion to set
     */
    public void setDelegacion(String delegacion) {
        this.delegacion = delegacion;
    }

    @Override
    public String toString() {
        return "Estacion{" + "id=" + id + ", linea=" + linea + ", name=" + name + ", delegacion=" + delegacion + '}';
    }
    public Estacion copy(){
        return new Estacion(id, linea, name, delegacion);
    }
}
