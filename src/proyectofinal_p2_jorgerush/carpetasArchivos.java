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
    ArrayList <archivosTxt> archivosCarpeta;
    String fechaMod;
    String tipo;
    int tamaño=0;

    public carpetasArchivos(String nombre, String fechaMod, String tipo) {
        this.nombre = nombre;
        archivosCarpeta=new ArrayList<>();
        this.fechaMod = fechaMod;
        this.tipo = tipo;
       
    }
    public void añadirArchivo(archivosTxt archivo){
        archivosCarpeta.add(archivo);
        
    }
    
    public void sumarTamaño(){
        
    }
    
    
}
