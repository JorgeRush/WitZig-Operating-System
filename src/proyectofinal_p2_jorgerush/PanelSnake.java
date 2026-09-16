/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal_p2_jorgerush;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Jorge Rush
 */
public class PanelSnake extends JPanel {

    Color colorSnake = (new Color(72, 207, 81));
    Color colorComida = new Color(247, 73, 67);

    int tamMax, tam, can, res;
    ArrayList<int[]> snake = new ArrayList<>();
    int[] comida = new int[2];
    String direccion = "de";
    String direccionProxima = "de";
    Thread hilo;
    Caminante camino;

    public PanelSnake(int tammax, int can) {
        this.tamMax = tammax;
        this.can = can;
        this.tam = tammax / can;
        this.res = tammax % can;
        int[] a = {can / 2 - 1, can / 2 - 1};
        int[] b = {can / 2, can / 2 - 1};
        snake.add(a);
        snake.add(b);
        generarComida();

//        comida[0] = 25;
//        comida[1] = 14;
        camino = new Caminante(this);
        hilo = new Thread(camino);
        hilo.start();
    }

    @Override
    public void paint(Graphics pintor) {
        super.paint(pintor);

        pintor.setColor(colorSnake);

//        for (int i = 0; i < snake.size(); i++) {
//             pintor.fillRect(res/2+snake.get(i)[0]*tam,res/2+snake.get(i)[1]*tam,tam-1,tam-1);
//    
//        }
        for (int[] par : snake) {
            pintor.fillRect(res / 2 + par[0] * tam, res / 2 + par[1] * tam, tam - 1, tam - 1);

        }
        pintor.setColor(colorComida);
        pintor.fillRect(res / 2 + comida[0] * tam, res / 2 + comida[1] * tam, tam - 1, tam - 1);

    }
    boolean juegoTerminado = false;

    public void reiniciarJuego() {
        juegoTerminado = false;

        snake.clear();

        int[] a = {can / 2 - 1, can / 2 - 1};
        int[] b = {can / 2, can / 2 - 1};

        snake.add(a);
        snake.add(b);

        direccion = "de";
        direccionProxima = "de";

        generarComida();

        repaint();
    }

    public void avanzar() {

        igualarDir();
        int[] ultimo = snake.get(snake.size() - 1);
        int agregarX = 0;
        int agregarY = 0;
        switch (direccion) {
            case "de":
                agregarX = 1;

                break;
            case "iz":
                agregarX = -1;

                break;
            case "ar":
                agregarY = -1;

                break;
            case "ab":
                agregarY = 1;

                break;
            default:
                throw new AssertionError();
        }
        int[] nuevo = {Math.floorMod(ultimo[0] + agregarX, can), Math.floorMod(ultimo[1] + agregarY, can)};
        boolean existe = false;
        for (int i = 0; i < snake.size(); i++) {
            if (nuevo[0] == snake.get(i)[0] && nuevo[1] == snake.get(i)[1]) {
                existe = true;
                break;
            }
        }
        if (juegoTerminado == true) {
            return;
        }
        if (existe) {
            juegoTerminado = true;
            JOptionPane.showMessageDialog(this,"    GAME OVER    ");
            int opcion = JOptionPane.showConfirmDialog(this, "¿Deseas Jugar de nuevo?", "\n   Game Over",
                    JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                reiniciarJuego();

            }
            return;

        } else {
            if (nuevo[0] == comida[0] && nuevo[1] == comida[1]) {
                snake.add(nuevo);
                generarComida();
            } else {
                snake.add(nuevo);
                snake.remove(0);
            }
        }

    }

    public void generarComida() {
        boolean existe = false;
        int a = (int) (Math.random() * can);
        int b = (int) (Math.random() * can);
        for (int[] par : snake) {
            if (par[0] == a && par[1] == b) {
                existe = true;
                generarComida();
                break;
            }
        }
        if (!existe) {
            this.comida[0] = a;
            this.comida[1] = b;
        }
    }

    public void cambiarDireccion(String dir) {
        if ((this.direccion.equals("de") || this.direccion.equals("iz")) && (dir.equals("ar") || dir.equals("ab"))) {
            this.direccionProxima = dir;
        }
        if ((this.direccion.equals("ar") || this.direccion.equals("ab")) && (dir.equals("iz") || dir.equals("de"))) {
            this.direccionProxima = dir;
        }

    }

    public void igualarDir() {
        this.direccion = this.direccionProxima;
    }
}
