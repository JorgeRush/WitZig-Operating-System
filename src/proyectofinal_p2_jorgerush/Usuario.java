/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal_p2_jorgerush;

import java.awt.Color;
import java.awt.Font;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.Icon;

/**
 *
 * @author Jorge Rush
 */
public class Usuario implements Serializable {
    //Configuracion de Pantalla de Inicio
    //Crear Carpetas, Eliminar Archivos, Ver Contenido de carpetas (explorador de archivos)
    //Color de elementos de la interfaz, o la fuente de texto en el editor.
    //Tipo de usuario

    String nombreUsuario;
    String contraseñaUsuario;
    String tipoUsuario;
    boolean Administrador;
    Color colorFondo;
    Color colorBarra;
    Color colorNavigator;
    Font fontGeneral;
    Color colorFont;
    Icon fondoImagen;
    String rutaArchivo;
    int contador = 0;
    boolean visible;
    Color barraNE;
    Color fondoNE;
    Color fondoOP;

    ArrayList<archivosTxt> listaArchivos;
    ArrayList<carpetasArchivos> listaCarpetas;

    public Usuario(String nombre, String contraseña, String tipoUsuario) {
        this.nombreUsuario = nombre;
        this.contraseñaUsuario = contraseña;
        this.tipoUsuario = tipoUsuario;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public void cambiarVisible(boolean cambio) {
        visible = cambio;

    }

    public boolean verificarVisible() {
        return visible;
    }

    public int getContador() {
        return contador;
    }

    public ArrayList<archivosTxt> getListaArchivos() {
        return listaArchivos;
    }

    public void setListaArchivos(ArrayList<archivosTxt> listaArchivos) {
        this.listaArchivos = listaArchivos;
    }

    public ArrayList<carpetasArchivos> getListaCarpetas() {
        return listaCarpetas;
    }

    public void setListaCarpetas(ArrayList<carpetasArchivos> listaCarpetas) {
        this.listaCarpetas = listaCarpetas;
    }

    public Usuario(Color barraNE,Color fondoNE, Color fondoOP, boolean visible, String nombreUsuario, String contraseñaUsuario, String tipoUsuario, Color colorFondo, Color colorBarra, Color colorNavigator, Font fontGeneral, Icon fondoImagen, ArrayList<archivosTxt> listaArchivos, ArrayList<carpetasArchivos> listaCarpetas, String rutaArchivo, Color colorF) {
        this.nombreUsuario = nombreUsuario;
        this.contraseñaUsuario = contraseñaUsuario;
        this.tipoUsuario = tipoUsuario;
        this.visible = visible;
        this.barraNE = barraNE;
        this.fondoNE=fondoNE;
        this.fondoOP=fondoOP;

        if (tipoUsuario.equals("Administrador")) {
            Administrador = true;
        } else {
            Administrador = false;
        }
        this.colorFondo = colorFondo;
        this.colorBarra = colorBarra;
        this.colorNavigator = colorNavigator;
        this.fontGeneral = fontGeneral;
        this.fondoImagen = fondoImagen;
        this.listaArchivos = listaArchivos;
        this.listaCarpetas = listaCarpetas;
        this.rutaArchivo = rutaArchivo;
        this.colorFont = colorF;
    }

    public Color getBarraNE() {
        return barraNE;
    }

    public void setBarraNE(Color barraNE) {
        this.barraNE = barraNE;
    }

    public Color getFondoNE() {
        return fondoNE;
    }

    public void setFondoNE(Color fondoNE) {
        this.fondoNE = fondoNE;
    }

    public Color getFondoOP() {
        return fondoOP;
    }

    public void setFondoOP(Color fondoOP) {
        this.fondoOP = fondoOP;
    }

    public Color getColorFont() {
        return colorFont;
    }

    public void setColorFont(Color colorFont) {
        this.colorFont = colorFont;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public ArrayList<archivosTxt> retornarArchivos() {
        return listaArchivos;
    }

    public ArrayList<carpetasArchivos> retornarCarpetas() {
        return listaCarpetas;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContraseñaUsuario() {
        return contraseñaUsuario;
    }

    public void setContraseñaUsuario(String contraseñaUsuario) {
        this.contraseñaUsuario = contraseñaUsuario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public boolean isAdministrador() {
        return Administrador;
    }

    public void setAdministrador(boolean Administrador) {
        this.Administrador = Administrador;
    }

    public Color getColorFondo() {
        return colorFondo;
    }

    public void setColorFondo(Color colorFondo) {
        this.colorFondo = colorFondo;
    }

    public Color getColorBarra() {
        return colorBarra;
    }

    public void setColorBarra(Color colorBarra) {
        this.colorBarra = colorBarra;
    }

    public Color getColorNavigator() {
        return colorNavigator;
    }

    public void setColorNavigator(Color colorNavigator) {
        this.colorNavigator = colorNavigator;
    }

    public Font getFontGeneral() {
        return fontGeneral;
    }

    public void setFontGeneral(Font fontGeneral) {
        this.fontGeneral = fontGeneral;
    }

    public Icon getFondoImagen() {
        return fondoImagen;
    }

    public void setFondoImagen(Icon fondoImagen) {
        this.fondoImagen = fondoImagen;
    }

    @Override
    public String toString() {
        return nombreUsuario;
    }

}
