/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rutasmetro;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class CargarDatos {
    
    public BufferedReader cargarArchivo(String name) throws FileNotFoundException{
        FileInputStream file= new FileInputStream(name);
        BufferedReader bf= new BufferedReader(new InputStreamReader(file));
        return bf;
    }
    public Estacion[] cargarEstaciones(String name) throws IOException{
        BufferedReader bf= cargarArchivo(name);
        ArrayList<Estacion> est= new ArrayList<>();
        int id,linea;
        while(bf.ready()){
            String tokens[] = bf.readLine().split(";");
            id= Integer.parseInt(tokens[0]);
            linea=Integer.parseInt(tokens[1]);
            Estacion e= new Estacion(id, linea, tokens[2],tokens[3]);
            est.add(e);
        }
        return est.toArray(new Estacion[est.size()]);
    }
    public Arista[] cargarAristas(String name) throws IOException{
        BufferedReader bf= cargarArchivo(name);
        ArrayList<Arista> aristas= new ArrayList<>();
        int de,a, tipo;
        double tiempo;
        while(bf.ready()){
            String tokens[] = bf.readLine().split(";");
            de= Integer.parseInt(tokens[0]);
            a=Integer.parseInt(tokens[1]);
            tipo=Integer.parseInt(tokens[2]);
            tiempo = Double.parseDouble(tokens[3]);
            Arista arista= new Arista(de,a,tipo, tiempo);
            aristas.add(arista);
        }
        return aristas.toArray(new Arista[aristas.size()]);
    }
}
