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
public class archivosTxt  {
    String nombreA;
 
    File archivo;
    String fechaModificado;
    String tipoA;
    String rutA;
    double tamañoA;

    public archivosTxt(String nombreA, File archivo, String fechaCreado, String tipoA, String rutA, double tamañoA) {
        this.nombreA = nombreA;
        this.archivo = archivo;
        this.fechaModificado = fechaCreado;
        this.tipoA = tipoA;
        this.rutA = rutA;
        this.tamañoA = tamañoA;
    }

    public String getNombreA() {
        return nombreA;
    }

    public void setNombreA(String nombreA) {
        this.nombreA = nombreA;
    }

    public File getArchivo() {
        return archivo;
    }

    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }

    public String getFechaModificado() {
        return fechaModificado;
    }

    public void setFechaModificado(String fechaModificado) {
        this.fechaModificado = fechaModificado;
    }

    public String getTipoA() {
        return tipoA;
    }

    public void setTipoA(String tipoA) {
        this.tipoA = tipoA;
    }

    public String getRutA() {
        return rutA;
    }

    public void setRutA(String rutA) {
        this.rutA = rutA;
    }

    public double getTamañoA() {
        return tamañoA;
    }

    public void setTamañoA(double tamañoA) {
        this.tamañoA = tamañoA;
    }


    
    

  
    
    
}
