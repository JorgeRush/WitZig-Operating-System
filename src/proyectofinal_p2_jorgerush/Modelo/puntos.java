/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal_p2_jorgerush.Modelo;

import java.util.LinkedList;

/**
 *
 * @author Jorge Rush
 */
public class puntos {

    LinkedList<Integer> PuntosX = new LinkedList();
    LinkedList<Integer> PuntosY = new LinkedList();

    public void GuardarPuntos(int x, int y) {
        PuntosX.add(x);
        PuntosY.add(y);

    }

    public LinkedList<Integer> listaX() {
        return PuntosX;
    }

    public LinkedList<Integer> listaY() {
        return PuntosY;
    }
    public void borrarPantalla(){
        while (PuntosX.isEmpty()==false) {
            PuntosX.remove();
        }
        while (PuntosY.isEmpty()==false) {
            PuntosY.remove();
        }
    }
}
