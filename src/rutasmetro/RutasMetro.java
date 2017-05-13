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
import java.util.PriorityQueue;

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
            adj[ar.getDe()].add(new Edge(ar.getA(), ar.getTipo(),ar.getTiempo()));
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
        RutasMetro rt=new RutasMetro();
        System.out.println("Lineas del metro disponibles: " + rt.getLineas());
        System.out.println("Delegaciones: " +rt.getDelegaciones());
        ArrayList<Arista> ruta= rt.calcularRuta(0, 113);
        System.out.println(ruta);
        double tiempo=0;
        for (int i = 0; i < ruta.size(); i++) {
            int de, a;
            Arista ar= ruta.get(i);
            de=ar.getDe();
            a=ar.getA();
            tiempo+= ar.getTiempo();
            System.out.print("de "+ rt.estaciones[de].getName());
            System.out.print(" (linea "+ rt.estaciones[de].getStringLinea()+")");
            System.out.print(" a "+ rt.estaciones[a].getName());
            System.out.print(" (linea " +rt.estaciones[a].getStringLinea()+")");
            if(ar.getTipo()==2){
                System.out.println(" (cambio de linea)");
            }else System.out.println("");
        }
        System.out.println("Tiempo: " +tiempo);
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
    public ArrayList<Arista> calcularRuta(int a, int b){
        ArrayList<Integer> rutas= new ArrayList<Integer>();
        PriorityQueue<NodeRoute> edges= new PriorityQueue();
        NodeRoute[] nodos = new NodeRoute[estaciones.length];
        
        double dist[] = new double[estaciones.length];
        Arrays.fill(dist, Integer.MAX_VALUE);
        nodos[a]= new NodeRoute(-1,0, a);
        dist[a] = 0;
        edges.add(nodos[a]);
        while(!edges.isEmpty()){
            NodeRoute ed= edges.poll();
            ArrayList<Edge> aristas= adj[ed.nodeId];
            //System.out.println("act "+ ed.nodeId + ", "+ aristas.size()+": " + aristas.toString());
            if(ed.w > dist[ed.nodeId])continue;
            for (int i = 0; i < aristas.size(); i++) {
                Edge u = aristas.get(i);
                if(dist[ed.nodeId] + u.tiempo < dist[u.destino]){
                    dist[u.destino] = dist[ed.nodeId] + u.tiempo;
                    //System.out.print("de " + ed.nodeId + " " +estaciones[ed.nodeId].getName());
                    //System.out.println(" a " + ed.nodeId + " " + estaciones[u.destino].getName());
                    nodos[u.destino] = new NodeRoute(ed.nodeId, dist[u.destino], u.destino);
                    edges.add(nodos[u.destino]);
                }
            }
        }
        rutas.add(b);
        int act= b;
        while(act!=a){
            //System.out.println("Nodo "+ act + " " + nodos[act]);
            act= nodos[act].from;
            rutas.add(act);
        }
        ArrayList<Arista> resp= new ArrayList<>();
        for (int i = rutas.size()-1; i >=1; i--) {
            int destino = rutas.get(i-1);
            ArrayList<Edge> ed= adj[rutas.get(i)];
            for (int j = 0; j < ed.size(); j++) {
                Edge edgeAc = ed.get(j);
                if(edgeAc.destino == destino){
                    resp.add(new Arista(rutas.get(i),destino,edgeAc.tipo,edgeAc.tiempo));
                }
            }
        }
        return resp;
    }
    class NodeRoute implements Comparable<NodeRoute>{
        int from;
        double w;
        int nodeId;
        public NodeRoute(int f, double w, int nodeId){
            from = f;
            this.w= w;
            this.nodeId=nodeId;
        }
        public NodeRoute(int f, int nodeId){
            this(f,Integer.MAX_VALUE, nodeId);
        }
        @Override
        public int compareTo(NodeRoute t) {
            if(this.w == t.w)return 0;
            if(this.w < t.w)return -1;
            return 1;
        }
        public String toString(){
            return "{"+nodeId+","+w+"}";
        }
    }
}

