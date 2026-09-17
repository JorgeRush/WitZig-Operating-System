/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal_p2_jorgerush.SnakeClases;

/**
 *
 * @author Jorge Rush
 */
public class Caminante implements Runnable {

    PanelSnake panel;
    boolean estado=true;

    public Caminante(PanelSnake panel) {
        this.panel = panel;
    }

    @Override
    public void run() {
        while (estado) {
            panel.avanzar();
            panel.repaint();
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.getLogger(Caminante.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
    }
    public void parar(){
        this.estado=false;
    }

}
