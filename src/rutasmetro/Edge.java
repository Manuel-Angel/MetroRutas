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
public class Edge {
    public int destino;
    public int tipo;
    public double tiempo;

    public Edge(int destino,int tipo, double tiempo) {
        this.destino=destino;
        this.tipo = tipo;
        this.tiempo = tiempo;
    }
    public String toString(){
        return "{" + destino+", "+tipo+", "+ tiempo+"}";
    }
}
