/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal_p2_jorgerush;

import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author Jorge Rush
 */
public class archivosTxt {
    String nombre;
    String contenido;
    File archivo;
    String fechaCreado;
    String tipo;
    int tamaño;

    public archivosTxt(String nombre, String contenido,String fechaCreado, String tipo,int tamaño) {
        this.nombre = nombre;
        archivo=new File(nombre);
        this.fechaCreado = fechaCreado;
        this.tipo=tipo;
        this.tamaño = tamaño;
    }
    public void guardarArchivo(File archivoT){
        archivo=archivoT;
        guardarContenido();
    }
    public void guardarContenido(){
        try( FileWriter guardarContenido= new FileWriter(archivo)){
            guardarContenido.write(contenido);
        }catch (Exception E){
            
        }
         
    }
    
    
}
