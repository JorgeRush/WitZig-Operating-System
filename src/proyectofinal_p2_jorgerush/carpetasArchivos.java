/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal_p2_jorgerush;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Jorge Rush
 */
public class carpetasArchivos {
    String nombre;
    File ubicacionCarpeta;
    ArrayList <archivosTxt> archivosCarpeta;
    String fechaMod;
    String tipo;
    String ruta;
    double tamaño=0;

    public carpetasArchivos(String nombre,File ubi, String fechaMod, String tipo,double tamaño,String ruta) {
        this.nombre = nombre;
        this.ubicacionCarpeta=ubi;
        archivosCarpeta=new ArrayList<>();
        this.fechaMod = fechaMod;
        this.tipo = tipo;
        this.tamaño=tamaño;
        this.ruta=ruta;
       
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaMod() {
        return fechaMod;
    }

    public void setFechaMod(String fechaMod) {
        this.fechaMod = fechaMod;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getTamaño() {
        return tamaño;
    }

    public void setTamaño(double tamaño) {
        this.tamaño = tamaño;
    }
    
    
    public void añadirArchivo(archivosTxt archivo){
        archivosCarpeta.add(archivo);
        
    }
    
   
    
    
}
