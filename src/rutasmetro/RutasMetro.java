/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rutasmetro;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

/**
 *
 * @author root
 */
public class RutasMetro {
    ArrayList<Edge> adj[];
    Estacion estaciones[];
    public RutasMetro() throws IOException{
        CargarDatos cd= new CargarDatos();
        estaciones = cd.cargarEstaciones("nodos.csv");
        Arista aristas[]= cd.cargarAristas("aristas.csv");
        armarGrafo(aristas);
    }
    private void armarGrafo(Arista aristas[]){
        adj = new ArrayList[estaciones.length];
        for (int i = 0; i < adj.length; i++) {
            adj[i]= new ArrayList<>();
        }
        int a, b;
        for (int i = 1; i < estaciones.length; i++) {
            if(estaciones[i-1].getLinea() == estaciones[i].getLinea()){
                a= estaciones[i-1].getId();
                b = estaciones[i].getId();
                adj[a].add(new Edge(b,1, 4));
                adj[b].add(new Edge(a,1, 4));
            }
        }
        for(int i = 0; i < aristas.length; i++) {
            Arista ar= aristas[i];
            adj[ar.getDe()].add(new Edge(ar.getA(), 1,ar.getTiempo()));
        }
        //checar que todo sea bidireccional
        for (int i = 0; i < adj.length; i++) {
            for (int j = 0; j < adj[i].size(); j++) {
                Edge ed= adj[i].get(j);
                if(!hasEdge(ed.destino, i)){
                    System.out.println("Error!******** "+ ed.destino+" "+ i);
                }
            }
        }
    }
    public boolean hasEdge(int de,int a){
        ArrayList<Edge> lis= adj[de];
        for (int i = 0; i < lis.size(); i++) {
            if(lis.get(i).destino== a)return true;
        }
        return false;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        /*CargarDatos cd= new CargarDatos();
        Estacion est[] = cd.cargarEstaciones("nodos.csv");
        Arista aristas[]= cd.cargarAristas("aristas.csv");
        for (int i = 0; i < est.length; i++) {
            System.out.println(est[i]);
        }
        for (int i = 0; i < aristas.length; i++) {
            System.out.println(aristas[i]);
        }*/
        RutasMetro rt=new RutasMetro();
        System.out.println("Lineas del metro disponibles: " + rt.getLineas());
        System.out.println("Delegaciones: " +rt.getDelegaciones());
    }
    public ArrayList<Estacion> getEstacionPorLinea(int a){
        ArrayList<Estacion> est= new ArrayList<>();
        for (int i = 0; i < estaciones.length; i++) {
            if(estaciones[i].getLinea() == a){
                est.add(estaciones[i]);
            }
        }
        return est;
    }
    public ArrayList<Estacion> getEstacionPorDelegacion(String  delegacion){
        ArrayList<Estacion> est= new ArrayList<>();
        for (int i = 0; i < estaciones.length; i++) {
            if(estaciones[i].getDelegacion().contains(delegacion)){
                est.add(estaciones[i]);
            }
        }        
        return est;
    }
    public ArrayList<String> getDelegaciones(){
        HashSet<String> delegaciones = new HashSet<>();
        for (int i = 0; i < estaciones.length; i++) {
            delegaciones.add(estaciones[i].getDelegacion());
        }
        ArrayList<String> r = new ArrayList<>();
        for (Iterator<String> iterator = delegaciones.iterator(); iterator.hasNext();) {
            String next = iterator.next();
            r.add(next);
        }
        return r;
    }
    public ArrayList<Integer> getLineas(){
        ArrayList<Integer> r=new ArrayList<>();
        boolean lineas[]= new boolean[13];
        Arrays.fill(lineas, false);
        for (int i = 0; i < estaciones.length; i++) {
            lineas[estaciones[i].getLinea()]= true;
        }
        for (int i = 0; i < lineas.length; i++) {
            if(lineas[i])r.add(i);
        }
        return r;
    }
    
}
