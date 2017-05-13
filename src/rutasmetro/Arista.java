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
public class Arista {
    private int de;
    private int a;
    private int tipo;
    private double tiempo;

    public Arista(int de, int a, int tipo, double tiempo) {
        this.de = de;
        this.a = a;
        this.tipo = tipo;
        this.tiempo = tiempo;
    }

    /**
     * @return the de
     */
    public int getDe() {
        return de;
    }

    /**
     * @param de the de to set
     */
    public void setDe(int de) {
        this.de = de;
    }

    /**
     * @return the a
     */
    public int getA() {
        return a;
    }

    /**
     * @param a the a to set
     */
    public void setA(int a) {
        this.a = a;
    }

    /**
     * @return the tipo
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the tiempo
     */
    public double getTiempo() {
        return tiempo;
    }

    /**
     * @param tiempo the tiempo to set
     */
    public void setTiempo(double tiempo) {
        this.tiempo = tiempo;
    }

    @Override
    public String toString() {
        return "Arista{" + "de=" + de + ", a=" + a + ", tipo=" + tipo + ", tiempo=" + tiempo + '}';
    }
    
}
