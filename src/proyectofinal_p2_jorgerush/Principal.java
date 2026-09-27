/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyectofinal_p2_jorgerush;

import proyectofinal_p2_jorgerush.SnakeClases.PanelFondo;
import proyectofinal_p2_jorgerush.SnakeClases.PanelSnake;
import java.util.Random;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SpinnerListModel;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import proyectofinal_p2_jorgerush.Modelo.puntos;

/**
 *
 * @author Jorge Rush
 */
public class Principal extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Principal.class.getName());

    /**
     * Creates new form Principal
     */
    PanelSnake panelSnake;
    puntos Pintado = null;
    boolean admin = false;

    public Principal() {
        this.setUndecorated(true);
        try {
            com.formdev.flatlaf.FlatLightLaf.setup();
        } catch (Exception e) {
            e.printStackTrace();
        }
       

        initComponents();

        XO();
        fondoIniciar.requestFocusInWindow();

        cargarUsuariosGuardados();
        cargarContenidoArbol();
        configurarIconosJTree();
        BarraNavegacion.setVisible(false);
        BarraTareas.setVisible(false);
        FondoPantalla.setVisible(false);

        Pintado = new puntos();

        panelSnake = new PanelSnake(500, 20);
        Snake.add(panelSnake);
        panelSnake.setBounds(10, 10, 500, 500);
        panelSnake.setOpaque(false);
        PanelFondo fondo = new PanelFondo(500, 20);
        Snake.add(fondo);
        fondo.setBounds(10, 10, 500, 500);

        AñadirBoton1.setVisible(false);
        usuario.setBorder(new com.formdev.flatlaf.ui.FlatLineBorder(new java.awt.Insets(1, 1, 1, 1), new java.awt.Color(206, 212, 218), 1, 25));
        contraseña.setBorder(new com.formdev.flatlaf.ui.FlatLineBorder(new java.awt.Insets(1, 1, 1, 1), new java.awt.Color(206, 212, 218), 1, 25));
        BarraTareas.putClientProperty("FlatLaf.style", "arc: 25; background: #FFFFFF");
        BarraTareas.setBorder(new com.formdev.flatlaf.ui.FlatLineBorder(new java.awt.Insets(1, 1, 1, 1), new java.awt.Color(206, 212, 218), 1, 25));

//        fondoIniciar.putClientProperty("FlatLaf.style", "arc: 25; background: #FFFFFF");
//        fondoIniciar.setBorder(new com.formdev.flatlaf.ui.FlatLineBorder(new java.awt.Insets(1, 1, 1, 1), new java.awt.Color(206, 212, 218), 1, 25));
//        
        willyCelebra.setVisible(false);
        BorrarBotonArchivos.setVisible(false);
        regresar.setVisible(false);
        tablaArchivos.setVisible(false);

        exploradorPc2.setVisible(false);
        exploradorArchivos.setVisible(false);
        panelColoresElementos.setVisible(false);
        exploradorPc.setVisible(false);
        panelPantalla.setVisible(false);
        panelFuentes.setVisible(false);
        eleccionPantalla.setVisible(false);
        eleccionExternos.setVisible(false);
        Imagen.setVisible(false);

        Color fondoTransparente = new Color(255, 255, 255, 200);
        BarraTareas.setBackground(fondoTransparente);

        String[] nombresFuentes = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getAvailableFontFamilyNames();

        SpinnerListModel modeloFuentes = new SpinnerListModel(nombresFuentes);
        spinnerFuente.setModel(modeloFuentes);
        spinnerFuente1.setModel(modeloFuentes);

        this.setExtendedState(this.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);

        FondoPantalla.setMaximumSize(FondoPantalla.getSize());
        exploradorArchivos.setTitle("Explorador de Archivos | Para actualizar la tabla, dele click a los espacios vacios de ambos lados.");
        XO.setTitle("Tic Tac Toe");
        personalizarPantalla.setTitle("Personalizar Pantala");
        Calculadora.setTitle("Calculadora");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")

    public void fechaHora() {

        Timer timer = new Timer(500, e -> {
            if (tablaArchivos.isVisible() == true && tablaCarpetas.isVisible() == false) {
                AñadirBoton1.setVisible(true);
                AñadirBoton.setVisible(false);
            } else if (tablaCarpetas.isVisible() == true && tablaArchivos.isVisible() == false) {
                AñadirBoton1.setVisible(false);
                AñadirBoton.setVisible(true);
            }
            String fecha1 = LocalDate.now().toString();
            String hora = String.format("%tr", LocalTime.now());
            fecha.setText(fecha1 + "   " + hora);
        });

        timer.start();

    }
    public void cerrarVentanas(){
        personalizarPantalla.dispose();
        crearTexto.dispose();
        exploradorArchivos.dispose();
        Calculadora.dispose();
        XO.dispose();
        Snake.dispose();
        Paint.dispose();
        AdminCuentas.dispose();
        Informacion.dispose();
        entrada.dispose();
    }

    public void cambiarColorFontPI(Color color) {
        BarraNavegacion.setForeground(color);
        Personalizar.setForeground(color);

    }

    public void cambiarFontPantallaInicio(Font fuente, Color color) {
        ModificarPantalla.setFont(fuente);
//        fecha.setFont(fuente);
//        user.setFont(fuente);
//        bienvenida.setFont(fuente);
//        mostrarU.setFont(fuente);
        Personalizar.setFont(fuente);
        Fuentes.setFont(fuente);
        Fuentes.setForeground(color);

//        Plain.setFont(fuente);
//        Plain.setForeground(color);
//
//        Bold.setFont(fuente);
//        Bold.setForeground(color);
//
//        BoldItalic.setFont(fuente);
//        BoldItalic.setForeground(color);
//
//        Italic.setFont(fuente);
//        Italic.setForeground(color);
        confirmarFuente.setFont(fuente);
        confirmarFuente.setForeground(color);

        jButton1.setFont(fuente);
        jButton1.setForeground(color);

        aspectosExternos.setFont(fuente);
        aspectosExternos.setForeground(color);

        fondoPm.setFont(fuente);
        fondoPm.setForeground(color);

        seleccionColor3.setFont(fuente);
        seleccionColor3.setForeground(color);

        lago.setFont(fuente);
        lago.setForeground(color);

        nevada.setFont(fuente);
        nevada.setForeground(color);

        tarde.setFont(fuente);
        tarde.setForeground(color);

        rancho.setFont(fuente);
        rancho.setForeground(color);

        elegirFondom.setFont(fuente);
        elegirFondom.setForeground(color);

        wDefecto.setFont(fuente);
        wDefecto.setForeground(color);

        Pantalla.setFont(fuente);
        Pantalla.setForeground(color);
//
//        Plain1.setFont(fuente);
//        Plain1.setForeground(color);
//
//        Bold1.setFont(fuente);
//        Bold1.setForeground(color);
//
//        Italic1.setFont(fuente);
//        Italic1.setForeground(color);
//
//        BoldItalic1.setFont(fuente);
//        BoldItalic1.setForeground(color);

        jButton2.setFont(fuente);
        jButton2.setForeground(color);

        jButton3.setFont(fuente);
        jButton3.setForeground(color);

        jButton9.setFont(fuente);
        jButton9.setForeground(color);

        BorrarBoton.setFont(fuente);
        BorrarBoton.setForeground(color);

        regresar.setFont(fuente);
        regresar.setForeground(color);

        BorrarBotonArchivos.setFont(fuente);
        BorrarBotonArchivos.setForeground(color);

        AñadirBoton.setFont(fuente);
        AñadirBoton.setForeground(color);

        AñadirBoton1.setFont(fuente);
        AñadirBoton1.setForeground(color);

        resultado.setFont(fuente);
        resultado.setForeground(color);

        siete.setFont(fuente);
        siete.setForeground(color);

        ocho.setFont(fuente);
        ocho.setForeground(color);

        nueve.setFont(fuente);
        nueve.setForeground(color);

        cero.setFont(fuente);
        cero.setForeground(color);

        menos.setFont(fuente);
        menos.setForeground(color);

        cuatro.setFont(fuente);
        cuatro.setForeground(color);

        cinco.setFont(fuente);
        cinco.setForeground(color);

        seis.setFont(fuente);
        seis.setForeground(color);

        dos.setFont(fuente);
        dos.setForeground(color);

        punto.setFont(fuente);
        punto.setForeground(color);

        uno.setFont(fuente);
        uno.setForeground(color);

        tres.setFont(fuente);
        tres.setForeground(color);

        limpiar.setFont(fuente);
        limpiar.setForeground(color);

        dividir.setFont(fuente);
        dividir.setForeground(color);

        multiplicar1.setFont(fuente);
        multiplicar1.setForeground(color);

        mas.setFont(fuente);
        mas.setForeground(color);

        jButton4.setFont(fuente);
        jButton4.setForeground(color);

        jButton6.setFont(fuente);
        jButton6.setForeground(color);

        seleccionarRuta.setFont(fuente);
        seleccionarRuta.setForeground(color);

        jButton7.setFont(fuente);
        jButton7.setForeground(color);

        RJuego.setFont(fuente);
        RJuego.setForeground(color);

        jButton5.setFont(fuente);
        jButton5.setForeground(color);

        cambiarTamaño.setFont(fuente);
        cambiarTamaño.setForeground(color);

        jButton8.setFont(fuente);
        jButton8.setForeground(color);

        jButton11.setFont(fuente);
        jButton11.setForeground(color);

        jButton12.setFont(fuente);
        jButton12.setForeground(color);

//        jButton10.setFont(fuente);
//        jButton10.setForeground(color);
        calculadora1.setFont(fuente);
        calculadora1.setForeground(color);

        calculadora.setFont(fuente);
        calculadora.setForeground(color);

        explorarArchivos.setFont(fuente);
        explorarArchivos.setForeground(color);

        WitZig.setFont(fuente);
        WitZig.setForeground(color);

        editorTexto1.setFont(fuente);
        editorTexto1.setForeground(color);

        TicTacToe.setFont(fuente);
        TicTacToe.setForeground(color);

        SnakeBoton.setFont(fuente);
        SnakeBoton.setForeground(color);
        tituloConfiguracion2.setFont(fuente);
        tituloConfiguracion2.setForeground(color);

        estiloFuente.setFont(fuente);
        estiloFuente.setForeground(color);

        tituloConfiguracion4.setFont(fuente);
        tituloConfiguracion4.setForeground(color);

        tituloConfiguracion3.setFont(fuente);
        tituloConfiguracion3.setForeground(color);

        jLabel11.setFont(fuente);
        jLabel11.setForeground(color);

        jLabel5.setFont(fuente);
        jLabel5.setForeground(color);

        jLabel4.setFont(fuente);
        jLabel4.setForeground(color);

        jLabel10.setFont(fuente);
        jLabel10.setForeground(color);

        tituloConfiguracion1.setFont(fuente);
        tituloConfiguracion1.setForeground(color);

        tituloConfiguracion5.setFont(fuente);
        tituloConfiguracion5.setForeground(color);

        tituloConfiguracion6.setFont(fuente);
        tituloConfiguracion6.setForeground(color);

        estiloFuente1.setFont(fuente);
        estiloFuente1.setForeground(color);

        tituloConfiguracion.setFont(fuente);
        tituloConfiguracion.setForeground(color);

        jLabel1.setFont(fuente);
        jLabel1.setForeground(color);

        pos1.setFont(fuente);
        pos1.setForeground(color);

        pos2.setFont(fuente);
        pos2.setForeground(color);

        pos3.setFont(fuente);
        pos3.setForeground(color);

        pos6.setFont(fuente);
        pos6.setForeground(color);

        pos5.setFont(fuente);
        pos5.setForeground(color);

        pos4.setFont(fuente);
        pos4.setForeground(color);

        pos7.setFont(fuente);
        pos7.setForeground(color);

        pos8.setFont(fuente);
        pos8.setForeground(color);

        pos9.setFont(fuente);
        pos9.setForeground(color);

        turnoMostrar.setFont(fuente);
        turnoMostrar.setForeground(color);

        jLabel6.setFont(fuente);
        jLabel6.setForeground(color);

        puntajeO.setFont(fuente);
        puntajeO.setForeground(color);

        jLabel8.setFont(fuente);
        jLabel8.setForeground(color);

        puntajeX.setFont(fuente);
        puntajeX.setForeground(color);

        willyCelebra.setFont(fuente);
        willyCelebra.setForeground(color);

        mensaje.setFont(fuente);
        mensaje.setForeground(color);

        Wally.setFont(fuente);
        Wally.setForeground(color);

        jLabel2.setFont(fuente);
        jLabel2.setForeground(color);

        ubicacionRutan.setFont(fuente);
        ubicacionRutan.setForeground(color);

        jLabel3.setFont(fuente);
        jLabel3.setForeground(color);

        jLabel7.setFont(fuente);
        jLabel7.setForeground(color);

        jLabel9.setFont(fuente);
        jLabel9.setForeground(color);

        jLabel12.setFont(fuente);
        jLabel12.setForeground(color);

        bienvenida.setFont(fuente);
        bienvenida.setForeground(color);

        jLabel22.setFont(fuente);
        jLabel22.setForeground(color);

        bienvenida1.setFont(fuente);
        bienvenida1.setForeground(color);

        jLabel16.setFont(fuente);
        jLabel16.setForeground(color);

        jLabel17.setFont(fuente);
        jLabel17.setForeground(color);

        jLabel18.setFont(fuente);
        jLabel18.setForeground(color);

        jLabel23.setFont(fuente);
        jLabel23.setForeground(color);

        jLabel24.setFont(fuente);
        jLabel24.setForeground(color);

        jLabel25.setFont(fuente);
        jLabel25.setForeground(color);

        rolTipo.setFont(fuente);
        rolTipo.setForeground(color);

        fecha.setFont(fuente);
        fecha.setForeground(color);

        mostrarU.setFont(fuente);
        mostrarU.setForeground(color);

        jLabel21.setFont(fuente);
        jLabel21.setForeground(color);

        jLabel19.setFont(fuente);
        jLabel19.setForeground(color);

        jLabel20.setFont(fuente);
        jLabel20.setForeground(color);

        jLabel27.setFont(fuente);
        jLabel27.setForeground(color);

        jLabel28.setFont(fuente);
        jLabel28.setForeground(color);

        jLabel26.setFont(fuente);
        jLabel26.setForeground(color);

//        Logo.setFont(fuente);
//        Logo.setForeground(color);
        
        
        jLabel13.setFont(fuente);
        jLabel13.setForeground(color);

//        jLabel14.setFont(fuente);
//        jLabel14.setForeground(color);
//
//        jLabel15.setFont(fuente);
//        jLabel15.setForeground(color);
        fondoImagen.setFont(fuente);
        fondoImagen.setForeground(color);

        ejemploFuente.setFont(fuente);
        ejemploFuente.setForeground(color);

        ejemploFuente1.setFont(fuente);
        ejemploFuente1.setForeground(color);

        ruta.setFont(fuente);
        ruta.setForeground(color);

        pantalla.setFont(fuente);
        pantalla.setForeground(color);

        nombreCarpeta.setFont(fuente);
        nombreCarpeta.setForeground(color);

        nombreCarpeta1.setFont(fuente);
        nombreCarpeta1.setForeground(color);

        contraUs.setFont(fuente);
        contraUs.setForeground(color);

        nombreUs.setFont(fuente);
        nombreUs.setForeground(color);

        nuevoU.setFont(fuente);
        nuevoU.setForeground(color);

        ncontraU.setFont(fuente);
        ncontraU.setForeground(color);

//        usuario.setFont(fuente);
//        usuario.setForeground(color);
//
//        contraseña.setFont(fuente);
//        contraseña.setForeground(color);
        Personalizar.setFont(fuente);
        Personalizar.setForeground(color);

        opcionesEditor.setFont(fuente);
        opcionesEditor.setForeground(color);

        archivo.setFont(fuente);
        archivo.setForeground(color);

        guardarArchivo.setFont(fuente);
        guardarArchivo.setForeground(color);

        abrirArchivo.setFont(fuente);
        abrirArchivo.setForeground(color);

        personalizarEditor.setFont(fuente);
        personalizarEditor.setForeground(color);

        jMenu2.setFont(fuente);
        jMenu2.setForeground(color);

        personalizarEditor2.setFont(fuente);
        personalizarEditor2.setForeground(color);

        Eliminar.setFont(fuente);
        Eliminar.setForeground(color);

        Abrir.setFont(fuente);
        Abrir.setForeground(color);

        EliminarArchivos.setFont(fuente);
        EliminarArchivos.setForeground(color);

        AbrirArchivos.setFont(fuente);
        AbrirArchivos.setForeground(color);

        modU.setFont(fuente);
        modU.setForeground(color);

        elimU.setFont(fuente);
        elimU.setForeground(color);

        BarraNavegacion.setFont(fuente);
        BarraNavegacion.setForeground(color);

        LogoWitZig.setFont(fuente);
        LogoWitZig.setForeground(color);

        ApagarS.setFont(fuente);
        ApagarS.setForeground(color);

        ModificarPantalla.setFont(fuente);
        ModificarPantalla.setForeground(color);

        LogIn.setFont(fuente);
        LogIn.setForeground(color);

        LogOut.setFont(fuente);
        LogOut.setForeground(color);

        verUsuarios.setFont(fuente);
        verUsuarios.setForeground(color);

        crearUsuarios.setFont(fuente);
        crearUsuarios.setForeground(color);
        tablaExplorador.setFont(fuente);
        tablaExplorador.setForeground(color);

        tablaArchivosTxt.setFont(fuente);
        tablaArchivosTxt.setForeground(color);

        arbolUsuarios.setFont(fuente);
        arbolUsuarios.setForeground(color);

        tablaUsuarios.setFont(fuente);
        tablaUsuarios.setForeground(color);

        jTabbedPane2.setFont(fuente);
        jTabbedPane2.setForeground(color);

        exploradorPc.setFont(fuente);
        exploradorPc.setForeground(color);

        exploradorPc2.setFont(fuente);
        exploradorPc2.setForeground(color);

        barraProgreso.setFont(fuente);
//        barraProgreso.setForeground(color);
    }

    public void cambiarFondo(int i) {

        if (i == 1) {
            Icon icono = new ImageIcon(getClass().getResource("/fondosPantalla/F1.png"));

            fondoImagen.setIcon(icono);
            FondoPantallaOg.setComponentZOrder(BarraTareas, 0);
            FondoPantallaOg.repaint();
        } else if (i == 2) {
            Icon icono = new ImageIcon(getClass().getResource("/fondosPantalla/F2.png"));
            fondoImagen.setIcon(icono);
            FondoPantallaOg.setComponentZOrder(BarraTareas, 0);
            FondoPantallaOg.repaint();
        } else if (i == 3) {
            Icon icono = new ImageIcon(getClass().getResource("/fondosPantalla/F3.png"));
            fondoImagen.setIcon(icono);
            FondoPantallaOg.setComponentZOrder(BarraTareas, 0);
            FondoPantallaOg.repaint();
        } else if (i == 4) {
            Icon icono = new ImageIcon(getClass().getResource("/fondosPantalla/F4.png"));
            fondoImagen.setIcon(icono);
            FondoPantallaOg.setComponentZOrder(BarraTareas, 0);
            FondoPantallaOg.repaint();

        } else if (i == 5) {
            Icon icono = new ImageIcon(getClass().getResource("/fondosPantalla/FW1.png"));
//            Icon icono = new ImageIcon(getClass().getResource("/fondosPantalla/WD6.png"));
            fondoImagen.setIcon(icono);
            FondoPantallaOg.setComponentZOrder(BarraTareas, 0);
            FondoPantallaOg.repaint();
//            Color fondoTransparente = new Color(30, 30, 30, 180);
//            BarraTareas.setBackground(fondoTransparente);

        } else if (i == 6) {
            Icon icono = new ImageIcon(getClass().getResource("/fondosPantalla/FW2.png"));
            fondoImagen.setIcon(icono);
            FondoPantallaOg.setComponentZOrder(BarraTareas, 0);
            FondoPantallaOg.repaint();
        } else if (i == 7) {
            Icon icono = new ImageIcon(getClass().getResource("/fondosPantalla/FW3.png"));
            fondoImagen.setIcon(icono);
            FondoPantallaOg.setComponentZOrder(BarraTareas, 0);
            FondoPantallaOg.repaint();
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        PopUpMenu = new javax.swing.JPopupMenu();
        Personalizar = new javax.swing.JMenuItem();
        personalizarPantalla = new javax.swing.JDialog();
        editarColoresPantalla = new javax.swing.JPanel();
        Fuentes = new javax.swing.JButton();
        panelFuentes = new javax.swing.JPanel();
        tituloConfiguracion2 = new javax.swing.JLabel();
        estiloFuente = new javax.swing.JLabel();
        tituloConfiguracion4 = new javax.swing.JLabel();
        spinnerFuente = new javax.swing.JSpinner();
        jScrollPane1 = new javax.swing.JScrollPane();
        ejemploFuente = new javax.swing.JTextArea();
        tamañoFuente = new javax.swing.JSpinner();
        Plain = new javax.swing.JButton();
        Bold = new javax.swing.JButton();
        BoldItalic = new javax.swing.JButton();
        Italic = new javax.swing.JButton();
        tituloConfiguracion3 = new javax.swing.JLabel();
        confirmarFuente = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        panelPantalla = new javax.swing.JPanel();
        aspectosExternos = new javax.swing.JButton();
        fondoPm = new javax.swing.JButton();
        eleccionExternos = new javax.swing.JPanel();
        ColorSolido2 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        seleccionColor3 = new javax.swing.JButton();
        barraN1 = new javax.swing.JRadioButton();
        barraT1 = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        eleccionPantalla = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        seleccionFondo = new javax.swing.JComboBox<>();
        Imagen = new javax.swing.JPanel();
        ColorSolido1 = new javax.swing.JPanel();
        elegirFondom = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jScrollPane6.getHorizontalScrollBar().setUnitIncrement(25);
        jPanel13 = new javax.swing.JPanel();
        tarde = new javax.swing.JButton();
        rancho = new javax.swing.JButton();
        nevada = new javax.swing.JButton();
        lago = new javax.swing.JButton();
        wDefecto = new javax.swing.JButton();
        wDefecto1 = new javax.swing.JButton();
        wDefecto2 = new javax.swing.JButton();
        Pantalla = new javax.swing.JButton();
        fondo = new javax.swing.JPanel();
        tituloConfiguracion1 = new javax.swing.JLabel();
        crearTexto = new javax.swing.JDialog();
        fondoGen = new javax.swing.JPanel();
        fondoOpc = new javax.swing.JPanel();
        tituloConfiguracion5 = new javax.swing.JLabel();
        spinnerFuente1 = new javax.swing.JSpinner();
        tamañoFuente1 = new javax.swing.JSpinner();
        tituloConfiguracion6 = new javax.swing.JLabel();
        estiloFuente1 = new javax.swing.JLabel();
        Plain1 = new javax.swing.JButton();
        Bold1 = new javax.swing.JButton();
        Italic1 = new javax.swing.JButton();
        BoldItalic1 = new javax.swing.JButton();
        tituloConfiguracion = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        panelColoresElementos = new javax.swing.JPanel();
        BarraN2 = new javax.swing.JCheckBox();
        fondoG = new javax.swing.JCheckBox();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        fondoOp = new javax.swing.JCheckBox();
        jButton9 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        ejemploFuente1 = new javax.swing.JTextArea();
        exploradorPc = new javax.swing.JFileChooser();
        opcionesEditor = new javax.swing.JMenuBar();
        archivo = new javax.swing.JMenu();
        guardarArchivo = new javax.swing.JMenuItem();
        abrirArchivo = new javax.swing.JMenuItem();
        personalizarEditor = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        PopUp = new javax.swing.JPopupMenu();
        personalizarEditor2 = new javax.swing.JMenuItem();
        exploradorArchivos = new javax.swing.JDialog();
        tablaCarpetas = new javax.swing.JScrollPane();
        tablaExplorador = new javax.swing.JTable();
        barraSuperior = new javax.swing.JPanel();
        BorrarBoton = new javax.swing.JButton();
        regresar = new javax.swing.JButton();
        BorrarBotonArchivos = new javax.swing.JButton();
        ruta = new javax.swing.JTextField();
        AñadirBoton = new javax.swing.JButton();
        AñadirBoton1 = new javax.swing.JButton();
        exploradorPc2 = new javax.swing.JFileChooser();
        tablaArchivos = new javax.swing.JScrollPane();
        tablaArchivosTxt = new javax.swing.JTable();
        OpcionesCarpetas = new javax.swing.JPopupMenu();
        Eliminar = new javax.swing.JMenuItem();
        Abrir = new javax.swing.JMenuItem();
        OpcionesArchivos = new javax.swing.JPopupMenu();
        EliminarArchivos = new javax.swing.JMenuItem();
        AbrirArchivos = new javax.swing.JMenuItem();
        Calculadora = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        pantalla = new javax.swing.JTextArea();
        resultado = new javax.swing.JButton();
        siete = new javax.swing.JButton();
        ocho = new javax.swing.JButton();
        nueve = new javax.swing.JButton();
        cero = new javax.swing.JButton();
        menos = new javax.swing.JButton();
        cuatro = new javax.swing.JButton();
        cinco = new javax.swing.JButton();
        seis = new javax.swing.JButton();
        dos = new javax.swing.JButton();
        punto = new javax.swing.JButton();
        uno = new javax.swing.JButton();
        tres = new javax.swing.JButton();
        limpiar = new javax.swing.JButton();
        dividir = new javax.swing.JButton();
        multiplicar1 = new javax.swing.JButton();
        mas = new javax.swing.JButton();
        XO = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        pos1 = new javax.swing.JLabel();
        pos2 = new javax.swing.JLabel();
        pos3 = new javax.swing.JLabel();
        pos6 = new javax.swing.JLabel();
        pos5 = new javax.swing.JLabel();
        pos4 = new javax.swing.JLabel();
        pos7 = new javax.swing.JLabel();
        pos8 = new javax.swing.JLabel();
        pos9 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        turnoMostrar = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        puntajeO = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        puntajeX = new javax.swing.JLabel();
        willyCelebra = new javax.swing.JLabel();
        BarraProgreso = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        barraProgreso = new javax.swing.JProgressBar();
        mensaje = new javax.swing.JLabel();
        Wally = new javax.swing.JLabel();
        CrearCarpeta = new javax.swing.JDialog();
        crearCarpeta = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        nombreCarpeta = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        ubicacionRutan = new javax.swing.JLabel();
        seleccionarRuta = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        mostrarCarpeta = new javax.swing.JDialog();
        crearCarpeta1 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        nombreCarpeta1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        Snake = new javax.swing.JDialog();
        RJuego = new javax.swing.JButton();
        Paint = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        tamañoPincel = new javax.swing.JSpinner();
        jButton5 = new javax.swing.JButton();
        cambiarTamaño = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        panel1 = new javax.swing.JPanel();
        AdminCuentas = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        bienvenida = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        bienvenida1 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        arbolUsuarios = new javax.swing.JTree();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        tipo = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        contraUs = new javax.swing.JTextField();
        nombreUs = new javax.swing.JTextField();
        jButton11 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        Informacion = new javax.swing.JDialog();
        jPanel10 = new javax.swing.JPanel();
        rolTipo = new javax.swing.JLabel();
        fecha = new javax.swing.JLabel();
        mostrarU = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        user = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaUsuarios = new javax.swing.JTable();
        ModificarUsuarios = new javax.swing.JPopupMenu();
        modU = new javax.swing.JMenuItem();
        elimU = new javax.swing.JMenuItem();
        ModificarUsuario = new javax.swing.JDialog();
        jPanel8 = new javax.swing.JPanel();
        nuevoU = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        ncontraU = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        entrada = new javax.swing.JDialog();
        jPanel14 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        FondoPantallaOg = new javax.swing.JPanel();
        fondoIniciar = new javax.swing.JPanel();
        jButton10 = new javax.swing.JButton();
        usuario = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        contraseña = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        BarraTareas = new javax.swing.JPanel();
        contenedor = new javax.swing.JPanel();
        calculadora1 = new javax.swing.JButton();
        calculadora = new javax.swing.JButton();
        explorarArchivos = new javax.swing.JButton();
        WitZig = new javax.swing.JButton();
        editorTexto1 = new javax.swing.JButton();
        TicTacToe = new javax.swing.JButton();
        SnakeBoton = new javax.swing.JButton();
        FondoPantalla = new javax.swing.JPanel();
        fondoImagen = new javax.swing.JLabel();
        BarraNavegacion = new javax.swing.JMenuBar();
        LogoWitZig = new javax.swing.JMenu();
        ApagarS = new javax.swing.JMenuItem();
        ModificarPantalla = new javax.swing.JMenu();
        LogIn = new javax.swing.JMenu();
        LogOut = new javax.swing.JMenu();
        verUsuarios = new javax.swing.JMenu();
        crearUsuarios = new javax.swing.JMenu();

        Personalizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/P1.png"))); // NOI18N
        Personalizar.setText("Personalizar");
        Personalizar.addActionListener(this::PersonalizarActionPerformed);
        PopUpMenu.add(Personalizar);

        editarColoresPantalla.setBackground(new java.awt.Color(255, 255, 255));
        editarColoresPantalla.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Fuentes.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Fuentes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/PF1.png"))); // NOI18N
        Fuentes.setText("Fuentes");
        Fuentes.setBorder(null);
        Fuentes.setFocusPainted(false);
        Fuentes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FuentesMouseClicked(evt);
            }
        });
        Fuentes.addActionListener(this::FuentesActionPerformed);
        editarColoresPantalla.add(Fuentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, 100, 30));

        panelFuentes.setBackground(new java.awt.Color(255, 255, 255));
        panelFuentes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tituloConfiguracion2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tituloConfiguracion2.setText("Ejemplar:");
        panelFuentes.add(tituloConfiguracion2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));

        estiloFuente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        estiloFuente.setText("Estilo de Fuente:");
        panelFuentes.add(estiloFuente, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        tituloConfiguracion4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tituloConfiguracion4.setText("Tamaño:");
        panelFuentes.add(tituloConfiguracion4, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, -1, 40));

        spinnerFuente.addChangeListener(this::spinnerFuenteStateChanged);
        panelFuentes.add(spinnerFuente, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 160, -1));

        ejemploFuente.setColumns(20);
        ejemploFuente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ejemploFuente.setRows(5);
        ejemploFuente.setText("¿Sabias que \"WitZig\" arcaicamente significa \"Ingenioso\" en Aleman?");
        jScrollPane1.setViewportView(ejemploFuente);

        panelFuentes.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 480, 110));

        tamañoFuente.setModel(new javax.swing.SpinnerNumberModel(15, 1, 22, 1));
        tamañoFuente.addChangeListener(this::tamañoFuenteStateChanged);
        panelFuentes.add(tamañoFuente, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, 50, -1));

        Plain.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Plain.setText("T");
        Plain.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PlainMouseClicked(evt);
            }
        });
        panelFuentes.add(Plain, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, -1, 20));

        Bold.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Bold.setText("N");
        Bold.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BoldMouseClicked(evt);
            }
        });
        panelFuentes.add(Bold, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, -1, 20));

        BoldItalic.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        BoldItalic.setText("M");
        BoldItalic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BoldItalicMouseClicked(evt);
            }
        });
        panelFuentes.add(BoldItalic, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 70, -1, 20));

        Italic.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        Italic.setText("K");
        Italic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ItalicMouseClicked(evt);
            }
        });
        panelFuentes.add(Italic, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, -1, 20));

        tituloConfiguracion3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tituloConfiguracion3.setText("Tipo de Fuente:");
        panelFuentes.add(tituloConfiguracion3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        confirmarFuente.setText("Seleccionar Como Fuente Predeterminada");
        confirmarFuente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                confirmarFuenteMouseClicked(evt);
            }
        });
        panelFuentes.add(confirmarFuente, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 260, -1, -1));

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/PX3.png"))); // NOI18N
        jButton1.setContentAreaFilled(false);
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(this::jButton1ActionPerformed);
        panelFuentes.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, 40, 40));

        editarColoresPantalla.add(panelFuentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 530, 290));

        panelPantalla.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        aspectosExternos.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        aspectosExternos.setText("Aspectos Externos");
        aspectosExternos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aspectosExternosMouseClicked(evt);
            }
        });
        aspectosExternos.addActionListener(this::aspectosExternosActionPerformed);
        panelPantalla.add(aspectosExternos, new org.netbeans.lib.awtextra.AbsoluteConstraints(292, 10, 160, 30));

        fondoPm.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        fondoPm.setText("Fondo de Pantalla");
        fondoPm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fondoPmMouseClicked(evt);
            }
        });
        fondoPm.addActionListener(this::fondoPmActionPerformed);
        panelPantalla.add(fondoPm, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 180, 30));

        eleccionExternos.setBackground(new java.awt.Color(204, 255, 153));

        ColorSolido2.setBackground(new java.awt.Color(255, 255, 255));
        ColorSolido2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        seleccionColor3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/PX3.png"))); // NOI18N
        seleccionColor3.setText("Elegir");
        seleccionColor3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionColor3MouseClicked(evt);
            }
        });
        jPanel4.add(seleccionColor3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 120, 30));

        barraN1.setText("Barra de Navegacion");
        barraN1.setFocusPainted(false);
        jPanel4.add(barraN1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        barraT1.setText("Barra de Tareas");
        barraT1.setFocusPainted(false);
        jPanel4.add(barraT1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Seleccione Elemento/s:");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jTabbedPane2.addTab("Seleccionar Color", jPanel4);

        ColorSolido2.add(jTabbedPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 530, 190));

        javax.swing.GroupLayout eleccionExternosLayout = new javax.swing.GroupLayout(eleccionExternos);
        eleccionExternos.setLayout(eleccionExternosLayout);
        eleccionExternosLayout.setHorizontalGroup(
            eleccionExternosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ColorSolido2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        eleccionExternosLayout.setVerticalGroup(
            eleccionExternosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ColorSolido2, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
        );

        panelPantalla.add(eleccionExternos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 530, 240));

        eleccionPantalla.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("   ");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Personalizar el Fondo de Pantalla:");

        seleccionFondo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Imagen", "Color Solido" }));
        seleccionFondo.addActionListener(this::seleccionFondoActionPerformed);

        Imagen.setBackground(new java.awt.Color(255, 255, 255));
        Imagen.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ColorSolido1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        elegirFondom.setText("Confirmar");
        elegirFondom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                elegirFondomMouseClicked(evt);
            }
        });
        elegirFondom.addActionListener(this::elegirFondomActionPerformed);
        ColorSolido1.add(elegirFondom, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, -1, -1));

        jLabel10.setText("Seleccionar una Imagen del Equipo:");
        ColorSolido1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, -1, -1));

        jLabel29.setText("Seleccione un Fondo de Pantalla Predeterminado:");
        ColorSolido1.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        jButton13.setText("Seleccionar...");
        jButton13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton13MouseClicked(evt);
            }
        });
        ColorSolido1.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 160, -1, -1));

        jScrollPane6.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane6.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        tarde.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fondosPantalla/FT2.png"))); // NOI18N
        tarde.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tardeMouseClicked(evt);
            }
        });

        rancho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fondosPantalla/FT3.png"))); // NOI18N
        rancho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ranchoMouseClicked(evt);
            }
        });

        nevada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fondosPantalla/FT1.png"))); // NOI18N
        nevada.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nevadaMouseClicked(evt);
            }
        });

        lago.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fondosPantalla/FT4.png"))); // NOI18N
        lago.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lagoMouseClicked(evt);
            }
        });
        lago.addActionListener(this::lagoActionPerformed);

        wDefecto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fondosPantalla/FWI1.png"))); // NOI18N
        wDefecto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                wDefectoMouseClicked(evt);
            }
        });

        wDefecto1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fondosPantalla/FWI3.png"))); // NOI18N
        wDefecto1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                wDefecto1MouseClicked(evt);
            }
        });

        wDefecto2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fondosPantalla/FWI2.png"))); // NOI18N
        wDefecto2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                wDefecto2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nevada, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(tarde, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(rancho, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(lago, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(wDefecto, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(wDefecto2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(wDefecto1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(wDefecto, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(wDefecto2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nevada, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tarde, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rancho, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lago, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(wDefecto1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane6.setViewportView(jPanel13);

        ColorSolido1.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 490, 110));

        Imagen.add(ColorSolido1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 540, 200));

        javax.swing.GroupLayout eleccionPantallaLayout = new javax.swing.GroupLayout(eleccionPantalla);
        eleccionPantalla.setLayout(eleccionPantallaLayout);
        eleccionPantallaLayout.setHorizontalGroup(
            eleccionPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eleccionPantallaLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel4)
                .addGap(16, 16, 16)
                .addComponent(seleccionFondo, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(155, Short.MAX_VALUE))
            .addGroup(eleccionPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Imagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(eleccionPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(eleccionPantallaLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel5)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        eleccionPantallaLayout.setVerticalGroup(
            eleccionPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eleccionPantallaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(eleccionPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(seleccionFondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(212, Short.MAX_VALUE))
            .addGroup(eleccionPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, eleccionPantallaLayout.createSequentialGroup()
                    .addGap(0, 34, Short.MAX_VALUE)
                    .addComponent(Imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(eleccionPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(eleccionPantallaLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel5)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        panelPantalla.add(eleccionPantalla, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 530, 240));

        editarColoresPantalla.add(panelPantalla, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 530, 290));

        Pantalla.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Pantalla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/PM1.png"))); // NOI18N
        Pantalla.setText("Pantalla");
        Pantalla.setBorder(null);
        Pantalla.setFocusPainted(false);
        Pantalla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PantallaMouseClicked(evt);
            }
        });
        Pantalla.addActionListener(this::PantallaActionPerformed);
        editarColoresPantalla.add(Pantalla, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 110, 30));

        fondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        editarColoresPantalla.add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 530, 290));

        tituloConfiguracion1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        tituloConfiguracion1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/P2.png"))); // NOI18N
        tituloConfiguracion1.setText("Personalización de Pantalla");
        tituloConfiguracion1.setOpaque(true);
        editarColoresPantalla.add(tituloConfiguracion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, -1, -1));

        personalizarPantalla.getContentPane().add(editarColoresPantalla, java.awt.BorderLayout.CENTER);

        crearTexto.setPreferredSize(new java.awt.Dimension(820, 700));
        crearTexto.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                crearTextoWindowOpened(evt);
            }
        });

        fondoGen.setBackground(new java.awt.Color(234, 230, 230));
        fondoGen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fondoGenMouseClicked(evt);
            }
        });
        fondoGen.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fondoOpc.setBackground(new java.awt.Color(255, 255, 255));
        fondoOpc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        tituloConfiguracion5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tituloConfiguracion5.setText("Tipo de Fuente:");

        spinnerFuente1.addChangeListener(this::spinnerFuente1StateChanged);

        tamañoFuente1.setModel(new javax.swing.SpinnerNumberModel(10, 1, null, 1));
        tamañoFuente1.addChangeListener(this::tamañoFuente1StateChanged);

        tituloConfiguracion6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tituloConfiguracion6.setText("Tamaño:");

        estiloFuente1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        estiloFuente1.setText("Estilo de Fuente:");

        Plain1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Plain1.setText("T");
        Plain1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Plain1MouseClicked(evt);
            }
        });

        Bold1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Bold1.setText("N");
        Bold1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Bold1MouseClicked(evt);
            }
        });

        Italic1.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        Italic1.setText("K");
        Italic1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Italic1MouseClicked(evt);
            }
        });

        BoldItalic1.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        BoldItalic1.setText("M");
        BoldItalic1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BoldItalic1MouseClicked(evt);
            }
        });

        tituloConfiguracion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tituloConfiguracion.setText("Color:");

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/PX3.png"))); // NOI18N
        jButton2.setContentAreaFilled(false);
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(this::jButton2ActionPerformed);

        panelColoresElementos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BarraN2.setText("Barra de Navegacion");
        panelColoresElementos.add(BarraN2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        fondoG.setText("Fondo General");
        panelColoresElementos.add(fondoG, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        jButton3.setBackground(new java.awt.Color(204, 204, 204));
        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/PX3.png"))); // NOI18N
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        jButton3.addActionListener(this::jButton3ActionPerformed);
        panelColoresElementos.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 30, 40, 40));

        jLabel1.setText("Seleccione un Elemento/s:");
        panelColoresElementos.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 6, -1, -1));

        fondoOp.setText("Fondo de Opciones");
        panelColoresElementos.add(fondoOp, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        jButton9.setBackground(new java.awt.Color(255, 153, 153));
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("X");
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton9MouseClicked(evt);
            }
        });
        jButton9.addActionListener(this::jButton9ActionPerformed);
        panelColoresElementos.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, -1, -1));

        javax.swing.GroupLayout fondoOpcLayout = new javax.swing.GroupLayout(fondoOpc);
        fondoOpc.setLayout(fondoOpcLayout);
        fondoOpcLayout.setHorizontalGroup(
            fondoOpcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondoOpcLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(fondoOpcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(fondoOpcLayout.createSequentialGroup()
                        .addComponent(tituloConfiguracion5)
                        .addGap(16, 16, 16)
                        .addComponent(spinnerFuente1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(fondoOpcLayout.createSequentialGroup()
                        .addComponent(estiloFuente1)
                        .addGap(10, 10, 10)
                        .addComponent(Plain1)
                        .addGap(3, 3, 3)
                        .addComponent(Bold1)
                        .addGap(3, 3, 3)
                        .addComponent(Italic1)
                        .addGap(3, 3, 3)
                        .addComponent(BoldItalic1)))
                .addGap(20, 20, 20)
                .addGroup(fondoOpcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(fondoOpcLayout.createSequentialGroup()
                        .addComponent(tituloConfiguracion6)
                        .addGap(41, 41, 41)
                        .addComponent(tamañoFuente1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(fondoOpcLayout.createSequentialGroup()
                        .addComponent(tituloConfiguracion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)))
                .addGap(18, 18, 18)
                .addComponent(panelColoresElementos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        fondoOpcLayout.setVerticalGroup(
            fondoOpcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondoOpcLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fondoOpcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelColoresElementos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(fondoOpcLayout.createSequentialGroup()
                        .addGroup(fondoOpcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tituloConfiguracion6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(fondoOpcLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(fondoOpcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tituloConfiguracion5)
                                    .addComponent(spinnerFuente1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tamañoFuente1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(fondoOpcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(fondoOpcLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(fondoOpcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(estiloFuente1)
                                    .addComponent(Plain1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Bold1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Italic1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(BoldItalic1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(fondoOpcLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(tituloConfiguracion))
                            .addGroup(fondoOpcLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        fondoGen.add(fondoOpc, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 110));

        ejemploFuente1.setColumns(20);
        ejemploFuente1.setRows(5);
        jScrollPane2.setViewportView(ejemploFuente1);

        fondoGen.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, 600, 530));

        opcionesEditor.setOpaque(true);

        archivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/Ed2.png"))); // NOI18N
        archivo.setText("Archivo");

        guardarArchivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/Ed3.png"))); // NOI18N
        guardarArchivo.setText("Guardar");
        guardarArchivo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                guardarArchivoMouseClicked(evt);
            }
        });
        guardarArchivo.addActionListener(this::guardarArchivoActionPerformed);
        archivo.add(guardarArchivo);

        abrirArchivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/Ed4.png"))); // NOI18N
        abrirArchivo.setText("Abrir");
        abrirArchivo.addActionListener(this::abrirArchivoActionPerformed);
        archivo.add(abrirArchivo);

        opcionesEditor.add(archivo);

        personalizarEditor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/P3.png"))); // NOI18N
        personalizarEditor.setText("Personalizar Elementos");
        personalizarEditor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                personalizarEditorMouseClicked(evt);
            }
        });
        opcionesEditor.add(personalizarEditor);

        jMenu2.setText("                                                                                                                                                                                                ");
        opcionesEditor.add(jMenu2);

        crearTexto.setJMenuBar(opcionesEditor);

        javax.swing.GroupLayout crearTextoLayout = new javax.swing.GroupLayout(crearTexto.getContentPane());
        crearTexto.getContentPane().setLayout(crearTextoLayout);
        crearTextoLayout.setHorizontalGroup(
            crearTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 820, Short.MAX_VALUE)
            .addGroup(crearTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(fondoGen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(crearTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(crearTextoLayout.createSequentialGroup()
                    .addGap(0, 93, Short.MAX_VALUE)
                    .addComponent(exploradorPc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 93, Short.MAX_VALUE)))
        );
        crearTextoLayout.setVerticalGroup(
            crearTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 677, Short.MAX_VALUE)
            .addGroup(crearTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(fondoGen, javax.swing.GroupLayout.DEFAULT_SIZE, 677, Short.MAX_VALUE))
            .addGroup(crearTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(crearTextoLayout.createSequentialGroup()
                    .addGap(0, 165, Short.MAX_VALUE)
                    .addComponent(exploradorPc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 165, Short.MAX_VALUE)))
        );

        personalizarEditor2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/P1.png"))); // NOI18N
        personalizarEditor2.setText("Personalizar Elementos");
        personalizarEditor2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                personalizarEditor2MouseClicked(evt);
            }
        });
        personalizarEditor2.addActionListener(this::personalizarEditor2ActionPerformed);
        PopUp.add(personalizarEditor2);

        exploradorArchivos.setMinimumSize(new java.awt.Dimension(700, 415));
        exploradorArchivos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exploradorArchivosMouseClicked(evt);
            }
        });
        exploradorArchivos.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                exploradorArchivosWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                exploradorArchivosWindowOpened(evt);
            }
        });
        exploradorArchivos.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaExplorador.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaExplorador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaExploradorMouseClicked(evt);
            }
        });
        tablaCarpetas.setViewportView(tablaExplorador);

        exploradorArchivos.getContentPane().add(tablaCarpetas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 650, 314));

        barraSuperior.setBackground(new java.awt.Color(255, 255, 255));
        barraSuperior.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BorrarBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/B1.png"))); // NOI18N
        BorrarBoton.setContentAreaFilled(false);
        BorrarBoton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BorrarBotonMouseClicked(evt);
            }
        });
        BorrarBoton.addActionListener(this::BorrarBotonActionPerformed);
        barraSuperior.add(BorrarBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 0, 40, 40));

        regresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/R1.png"))); // NOI18N
        regresar.setBorder(null);
        regresar.setContentAreaFilled(false);
        regresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                regresarMouseClicked(evt);
            }
        });
        barraSuperior.add(regresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 30, 40));

        BorrarBotonArchivos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/B1.png"))); // NOI18N
        BorrarBotonArchivos.setContentAreaFilled(false);
        BorrarBotonArchivos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BorrarBotonArchivosMouseClicked(evt);
            }
        });
        BorrarBotonArchivos.addActionListener(this::BorrarBotonArchivosActionPerformed);
        barraSuperior.add(BorrarBotonArchivos, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 0, 40, 40));

        ruta.setEditable(false);
        ruta.setText(" ");
        ruta.addActionListener(this::rutaActionPerformed);
        barraSuperior.add(ruta, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 540, 20));

        AñadirBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/C1.png"))); // NOI18N
        AñadirBoton.setContentAreaFilled(false);
        AñadirBoton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AñadirBotonMouseClicked(evt);
            }
        });
        AñadirBoton.addActionListener(this::AñadirBotonActionPerformed);
        barraSuperior.add(AñadirBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, 40, 20));

        AñadirBoton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/C1.png"))); // NOI18N
        AñadirBoton1.setContentAreaFilled(false);
        AñadirBoton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AñadirBoton1MouseClicked(evt);
            }
        });
        AñadirBoton1.addActionListener(this::AñadirBoton1ActionPerformed);
        barraSuperior.add(AñadirBoton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, 40, 20));

        exploradorArchivos.getContentPane().add(barraSuperior, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 6, 650, 37));

        exploradorPc2.addActionListener(this::exploradorPc2ActionPerformed);
        exploradorArchivos.getContentPane().add(exploradorPc2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 490, 280));

        tablaArchivosTxt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaArchivosTxt.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tablaArchivosTxtAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tablaArchivosTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaArchivosTxtMouseClicked(evt);
            }
        });
        tablaArchivos.setViewportView(tablaArchivosTxt);

        exploradorArchivos.getContentPane().add(tablaArchivos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 650, 314));

        Eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/B2.png"))); // NOI18N
        Eliminar.setText("Eliminar");
        Eliminar.addActionListener(this::EliminarActionPerformed);
        OpcionesCarpetas.add(Eliminar);

        Abrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/C3.png"))); // NOI18N
        Abrir.setText("Abrir");
        Abrir.addActionListener(this::AbrirActionPerformed);
        OpcionesCarpetas.add(Abrir);

        EliminarArchivos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/B2.png"))); // NOI18N
        EliminarArchivos.setText("Eliminar Archivo");
        EliminarArchivos.addActionListener(this::EliminarArchivosActionPerformed);
        OpcionesArchivos.add(EliminarArchivos);

        AbrirArchivos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/A1.png"))); // NOI18N
        AbrirArchivos.setText("Abrir archivo");
        AbrirArchivos.addActionListener(this::AbrirArchivosActionPerformed);
        OpcionesArchivos.add(AbrirArchivos);

        jPanel1.setBackground(new java.awt.Color(235, 235, 235));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pantalla.setEditable(false);
        pantalla.setColumns(20);
        pantalla.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        pantalla.setRows(5);
        pantalla.setCaretColor(new java.awt.Color(255, 255, 255));
        jScrollPane3.setViewportView(pantalla);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 350, 100));

        resultado.setFont(new java.awt.Font("Courier New", 0, 24)); // NOI18N
        resultado.setForeground(new java.awt.Color(57, 57, 57));
        resultado.setText("=");
        resultado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                resultadoMouseClicked(evt);
            }
        });
        jPanel1.add(resultado, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 340, 80, 150));

        siete.setFont(new java.awt.Font("Courier New", 0, 24)); // NOI18N
        siete.setForeground(new java.awt.Color(57, 57, 57));
        siete.setText("7");
        siete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sieteMouseClicked(evt);
            }
        });
        jPanel1.add(siete, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 70, 60));

        ocho.setFont(new java.awt.Font("Courier New", 0, 24)); // NOI18N
        ocho.setForeground(new java.awt.Color(57, 57, 57));
        ocho.setText("8");
        ocho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ochoMouseClicked(evt);
            }
        });
        jPanel1.add(ocho, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, 70, 60));

        nueve.setFont(new java.awt.Font("Courier New", 0, 24)); // NOI18N
        nueve.setForeground(new java.awt.Color(57, 57, 57));
        nueve.setText("9");
        nueve.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nueveMouseClicked(evt);
            }
        });
        jPanel1.add(nueve, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 200, 70, 60));

        cero.setFont(new java.awt.Font("Courier New", 0, 24)); // NOI18N
        cero.setForeground(new java.awt.Color(57, 57, 57));
        cero.setText("0");
        cero.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ceroMouseClicked(evt);
            }
        });
        cero.addActionListener(this::ceroActionPerformed);
        jPanel1.add(cero, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 170, 60));

        menos.setFont(new java.awt.Font("Courier New", 0, 24)); // NOI18N
        menos.setForeground(new java.awt.Color(57, 57, 57));
        menos.setText("-");
        menos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menosMouseClicked(evt);
            }
        });
        jPanel1.add(menos, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 270, 80, 50));

        cuatro.setFont(new java.awt.Font("Courier New", 0, 24)); // NOI18N
        cuatro.setForeground(new java.awt.Color(57, 57, 57));
        cuatro.setText("4");
        cuatro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cuatroMouseClicked(evt);
            }
        });
        jPanel1.add(cuatro, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 70, 60));

        cinco.setFont(new java.awt.Font("Courier New", 0, 24)); // NOI18N
        cinco.setForeground(new java.awt.Color(57, 57, 57));
        cinco.setText("5");
        cinco.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cincoMouseClicked(evt);
            }
        });
        jPanel1.add(cinco, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 280, 70, 60));

        seis.setFont(new java.awt.Font("Courier New", 0, 24)); // NOI18N
        seis.setForeground(new java.awt.Color(57, 57, 57));
        seis.setText("6");
        seis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seisMouseClicked(evt);
            }
        });
        jPanel1.add(seis, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 280, 70, 60));

        dos.setFont(new java.awt.Font("Courier New", 0, 24)); // NOI18N
        dos.setForeground(new java.awt.Color(57, 57, 57));
        dos.setText("2");
        dos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dosMouseClicked(evt);
            }
        });
        jPanel1.add(dos, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 350, 70, 60));

        punto.setFont(new java.awt.Font("Courier New", 0, 24)); // NOI18N
        punto.setForeground(new java.awt.Color(57, 57, 57));
        punto.setText(".");
        punto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                puntoMouseClicked(evt);
            }
        });
        jPanel1.add(punto, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 430, 70, 60));

        uno.setFont(new java.awt.Font("Courier New", 0, 24)); // NOI18N
        uno.setForeground(new java.awt.Color(57, 57, 57));
        uno.setText("1");
        uno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                unoMouseClicked(evt);
            }
        });
        jPanel1.add(uno, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 70, 60));

        tres.setFont(new java.awt.Font("Courier New", 0, 24)); // NOI18N
        tres.setForeground(new java.awt.Color(57, 57, 57));
        tres.setText("3");
        tres.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tresMouseClicked(evt);
            }
        });
        jPanel1.add(tres, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 350, 70, 60));

        limpiar.setFont(new java.awt.Font("Courier New", 0, 24)); // NOI18N
        limpiar.setForeground(new java.awt.Color(57, 57, 57));
        limpiar.setText("C");
        limpiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                limpiarMouseClicked(evt);
            }
        });
        jPanel1.add(limpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 70, 50));

        dividir.setFont(new java.awt.Font("Courier New", 0, 24)); // NOI18N
        dividir.setForeground(new java.awt.Color(57, 57, 57));
        dividir.setText("÷");
        dividir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dividirMouseClicked(evt);
            }
        });
        jPanel1.add(dividir, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 70, 50));

        multiplicar1.setFont(new java.awt.Font("Courier New", 0, 24)); // NOI18N
        multiplicar1.setForeground(new java.awt.Color(57, 57, 57));
        multiplicar1.setText("x");
        multiplicar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                multiplicar1MouseClicked(evt);
            }
        });
        jPanel1.add(multiplicar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, 70, 50));

        mas.setFont(new java.awt.Font("Courier New", 0, 24)); // NOI18N
        mas.setForeground(new java.awt.Color(57, 57, 57));
        mas.setText("+");
        mas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                masMouseClicked(evt);
            }
        });
        jPanel1.add(mas, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 200, 80, 50));

        javax.swing.GroupLayout CalculadoraLayout = new javax.swing.GroupLayout(Calculadora.getContentPane());
        Calculadora.getContentPane().setLayout(CalculadoraLayout);
        CalculadoraLayout.setHorizontalGroup(
            CalculadoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        CalculadoraLayout.setVerticalGroup(
            CalculadoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CalculadoraLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(222, 222, 222));

        pos1.setBackground(new java.awt.Color(255, 255, 255));
        pos1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        pos1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pos1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/Default.png"))); // NOI18N
        pos1.setOpaque(true);
        pos1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pos1MousePressed(evt);
            }
        });

        pos2.setBackground(new java.awt.Color(255, 255, 255));
        pos2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        pos2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pos2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/Default.png"))); // NOI18N
        pos2.setOpaque(true);
        pos2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pos2MousePressed(evt);
            }
        });

        pos3.setBackground(new java.awt.Color(255, 255, 255));
        pos3.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        pos3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pos3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/Default.png"))); // NOI18N
        pos3.setOpaque(true);
        pos3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pos3MousePressed(evt);
            }
        });

        pos6.setBackground(new java.awt.Color(255, 255, 255));
        pos6.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        pos6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pos6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/Default.png"))); // NOI18N
        pos6.setOpaque(true);
        pos6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pos6MousePressed(evt);
            }
        });

        pos5.setBackground(new java.awt.Color(255, 255, 255));
        pos5.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        pos5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pos5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/Default.png"))); // NOI18N
        pos5.setOpaque(true);
        pos5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pos5MousePressed(evt);
            }
        });

        pos4.setBackground(new java.awt.Color(255, 255, 255));
        pos4.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        pos4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pos4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/Default.png"))); // NOI18N
        pos4.setOpaque(true);
        pos4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pos4MousePressed(evt);
            }
        });

        pos7.setBackground(new java.awt.Color(255, 255, 255));
        pos7.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        pos7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pos7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/Default.png"))); // NOI18N
        pos7.setOpaque(true);
        pos7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pos7MousePressed(evt);
            }
        });

        pos8.setBackground(new java.awt.Color(255, 255, 255));
        pos8.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        pos8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pos8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/Default.png"))); // NOI18N
        pos8.setOpaque(true);
        pos8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pos8MousePressed(evt);
            }
        });

        pos9.setBackground(new java.awt.Color(255, 255, 255));
        pos9.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        pos9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pos9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/Default.png"))); // NOI18N
        pos9.setOpaque(true);
        pos9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pos9MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(pos7, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pos8, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pos9, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(pos1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(pos2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(pos3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(pos4, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(pos5, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(pos6, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pos3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pos1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pos2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pos6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pos4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pos5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pos9, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pos7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pos8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, -1, -1));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/R3.png"))); // NOI18N
        jButton4.setBorder(null);
        jButton4.setContentAreaFilled(false);
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });
        jButton4.addActionListener(this::jButton4ActionPerformed);
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 300, 50, -1));

        turnoMostrar.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        turnoMostrar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        turnoMostrar.setText("        ");
        jPanel2.add(turnoMostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 160, -1));

        jLabel6.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/O2.png"))); // NOI18N
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 290, -1, 40));

        puntajeO.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        puntajeO.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        puntajeO.setText("0");
        jPanel2.add(puntajeO, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 300, 20, 30));

        jLabel8.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/X2.png"))); // NOI18N
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 290, -1, 40));

        puntajeX.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        puntajeX.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        puntajeX.setText("0");
        jPanel2.add(puntajeX, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 300, 30, 30));

        willyCelebra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/Wally1.png"))); // NOI18N
        jPanel2.add(willyCelebra, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, -1, -1));

        javax.swing.GroupLayout XOLayout = new javax.swing.GroupLayout(XO.getContentPane());
        XO.getContentPane().setLayout(XOLayout);
        XOLayout.setHorizontalGroup(
            XOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(XOLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
                .addContainerGap())
        );
        XOLayout.setVerticalGroup(
            XOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(XOLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        BarraProgreso.setBackground(new java.awt.Color(213, 212, 212));
        BarraProgreso.setMinimumSize(new java.awt.Dimension(480, 200));
        BarraProgreso.setModal(true);
        BarraProgreso.setResizable(false);
        BarraProgreso.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        barraProgreso.setBackground(new java.awt.Color(255, 255, 255));
        barraProgreso.setFont(new java.awt.Font("Dubai Light", 0, 18)); // NOI18N
        barraProgreso.setForeground(new java.awt.Color(102, 102, 102));
        jPanel5.add(barraProgreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 400, 30));

        mensaje.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        mensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mensaje.setText("  Rusia tiene la superficie de Pluton.");
        mensaje.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel5.add(mensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 340, 80));

        Wally.setForeground(new java.awt.Color(242, 242, 242));
        Wally.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Wally/W9.png"))); // NOI18N
        Wally.setText("A                  ");
        Wally.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel5.add(Wally, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 100, 80));

        BarraProgreso.getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 470, 170));

        CrearCarpeta.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                CrearCarpetaWindowClosed(evt);
            }
        });
        CrearCarpeta.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        crearCarpeta.setBackground(new java.awt.Color(229, 228, 228));

        jButton6.setText("Crear");
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton6MouseClicked(evt);
            }
        });
        jButton6.addActionListener(this::jButton6ActionPerformed);

        jLabel2.setText("Ingrese el nombre de la Carpeta:");

        ubicacionRutan.setText("Seleccione la ruta de la Carpeta:");

        seleccionarRuta.setText("Seleccionar...");
        seleccionarRuta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarRutaMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/C1.png"))); // NOI18N
        jLabel3.setText("Crear Carpeta");

        javax.swing.GroupLayout crearCarpetaLayout = new javax.swing.GroupLayout(crearCarpeta);
        crearCarpeta.setLayout(crearCarpetaLayout);
        crearCarpetaLayout.setHorizontalGroup(
            crearCarpetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crearCarpetaLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(crearCarpetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ubicacionRutan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(crearCarpetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nombreCarpeta)
                    .addComponent(seleccionarRuta))
                .addContainerGap(130, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crearCarpetaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton6)
                .addGap(148, 148, 148))
            .addGroup(crearCarpetaLayout.createSequentialGroup()
                .addGap(166, 166, 166)
                .addComponent(jLabel3)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        crearCarpetaLayout.setVerticalGroup(
            crearCarpetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crearCarpetaLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(crearCarpetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombreCarpeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(crearCarpetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ubicacionRutan)
                    .addComponent(seleccionarRuta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jButton6)
                .addGap(20, 20, 20))
        );

        CrearCarpeta.getContentPane().add(crearCarpeta, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 437, -1));

        mostrarCarpeta.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        crearCarpeta1.setBackground(new java.awt.Color(229, 228, 228));

        jButton7.setText("Crear");
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton7MouseClicked(evt);
            }
        });
        jButton7.addActionListener(this::jButton7ActionPerformed);

        jLabel7.setText("Ingrese el nombre de la Carpeta:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/C1.png"))); // NOI18N
        jLabel9.setText("Crear Carpeta");

        javax.swing.GroupLayout crearCarpeta1Layout = new javax.swing.GroupLayout(crearCarpeta1);
        crearCarpeta1.setLayout(crearCarpeta1Layout);
        crearCarpeta1Layout.setHorizontalGroup(
            crearCarpeta1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crearCarpeta1Layout.createSequentialGroup()
                .addGap(166, 166, 166)
                .addComponent(jLabel9)
                .addGap(0, 158, Short.MAX_VALUE))
            .addGroup(crearCarpeta1Layout.createSequentialGroup()
                .addGroup(crearCarpeta1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(crearCarpeta1Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nombreCarpeta1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(crearCarpeta1Layout.createSequentialGroup()
                        .addGap(182, 182, 182)
                        .addComponent(jButton7)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        crearCarpeta1Layout.setVerticalGroup(
            crearCarpeta1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crearCarpeta1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addGroup(crearCarpeta1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombreCarpeta1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(37, 37, 37)
                .addComponent(jButton7)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        mostrarCarpeta.getContentPane().add(crearCarpeta1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 437, -1));

        Snake.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SnakeKeyPressed(evt);
            }
        });

        RJuego.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/R3.png"))); // NOI18N
        RJuego.setContentAreaFilled(false);
        RJuego.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RJuegoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout SnakeLayout = new javax.swing.GroupLayout(Snake.getContentPane());
        Snake.getContentPane().setLayout(SnakeLayout);
        SnakeLayout.setHorizontalGroup(
            SnakeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SnakeLayout.createSequentialGroup()
                .addContainerGap(237, Short.MAX_VALUE)
                .addComponent(RJuego)
                .addGap(232, 232, 232))
        );
        SnakeLayout.setVerticalGroup(
            SnakeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SnakeLayout.createSequentialGroup()
                .addContainerGap(511, Short.MAX_VALUE)
                .addComponent(RJuego, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Paint.setBackground(new java.awt.Color(204, 204, 204));
        Paint.setMinimumSize(new java.awt.Dimension(730, 560));
        Paint.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                PaintWindowOpened(evt);
            }
        });
        Paint.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(234, 234, 234));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tamañoPincel.setModel(new javax.swing.SpinnerNumberModel(15, 0, null, 1));
        tamañoPincel.setValue(10);
        jPanel6.add(tamañoPincel, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 60, 30));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/L1.png"))); // NOI18N
        jButton5.setContentAreaFilled(false);
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });
        jPanel6.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 6, -1, 40));

        cambiarTamaño.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/T1.png"))); // NOI18N
        cambiarTamaño.setContentAreaFilled(false);
        cambiarTamaño.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cambiarTamañoMouseClicked(evt);
            }
        });
        jPanel6.add(cambiarTamaño, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, 49, 50));

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/PX3.png"))); // NOI18N
        jButton8.setContentAreaFilled(false);
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton8MouseClicked(evt);
            }
        });
        jButton8.addActionListener(this::jButton8ActionPerformed);
        jPanel6.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, -1, 50));

        jLabel12.setText("  Tamaño");
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel6.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 50));

        Paint.getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 450, 50));

        panel1.setBackground(new java.awt.Color(255, 255, 255));
        panel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panel1MouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                panel1MouseMoved(evt);
            }
        });
        panel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        Paint.getContentPane().add(panel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 680, 470));

        AdminCuentas.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        bienvenida.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        bienvenida.setText("Crear Usuario");
        bienvenida.setOpaque(true);

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/W12.png"))); // NOI18N

        bienvenida1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        bienvenida1.setText("Crear Usuario");
        bienvenida1.setOpaque(true);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel22)
                .addGap(32, 32, 32)
                .addComponent(bienvenida, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addComponent(bienvenida1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel22))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bienvenida)
                    .addComponent(bienvenida1))
                .addGap(19, 19, 19))
        );

        AdminCuentas.getContentPane().add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, 60));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setMaximumSize(new java.awt.Dimension(750, 409));
        jPanel9.setMinimumSize(new java.awt.Dimension(750, 409));
        jPanel9.setPreferredSize(new java.awt.Dimension(750, 409));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jScrollPane4.setViewportView(arbolUsuarios);

        jPanel9.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, 345, 330));

        jLabel16.setBackground(new java.awt.Color(225, 225, 225));
        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("Tipo de Usuario:");
        jLabel16.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel9.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 100, -1));

        jLabel17.setBackground(new java.awt.Color(225, 225, 225));
        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setText("Nombre:");
        jPanel9.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 60, -1));

        tipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Invitado", "Administrador" }));
        jPanel9.add(tipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, 100, -1));

        jLabel18.setBackground(new java.awt.Color(225, 225, 225));
        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setText("Contraseña:");
        jPanel9.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 80, -1));
        jPanel9.add(contraUs, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 100, -1));
        jPanel9.add(nombreUs, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 100, -1));

        jButton11.setText("Crear");
        jButton11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton11MouseClicked(evt);
            }
        });
        jPanel9.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 210, -1, -1));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        );

        jPanel9.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 2, 380));

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/UT.png"))); // NOI18N
        jPanel9.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, 40));

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/NU.png"))); // NOI18N
        jPanel9.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 30, 40));

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/CU.png"))); // NOI18N
        jPanel9.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 30, 40));

        AdminCuentas.getContentPane().add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 75, 750, 409));

        Informacion.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                InformacionWindowActivated(evt);
            }
        });
        Informacion.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rolTipo.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        rolTipo.setText("Designacion: Admin");
        rolTipo.setOpaque(true);
        jPanel10.add(rolTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, 140, -1));

        fecha.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        fecha.setText("horario");
        fecha.setOpaque(true);
        jPanel10.add(fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 20, 190, 20));

        mostrarU.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        mostrarU.setText("Usuario:");
        jPanel10.add(mostrarU, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/W13.png"))); // NOI18N
        jPanel10.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 70, 60));

        user.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        user.setText("user");
        user.setOpaque(true);
        jPanel10.add(user, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 110, -1));

        Informacion.getContentPane().add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, 60));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setMaximumSize(new java.awt.Dimension(750, 410));
        jPanel11.setMinimumSize(new java.awt.Dimension(750, 410));
        jPanel11.setPreferredSize(new java.awt.Dimension(750, 410));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        tablaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Usuario", "Tipo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaUsuariosMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tablaUsuarios);

        jPanel11.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 720, 280));

        Informacion.getContentPane().add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 750, 410));

        modU.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/P1.png"))); // NOI18N
        modU.setText("Modificar Usuario");
        modU.addActionListener(this::modUActionPerformed);
        ModificarUsuarios.add(modU);

        elimU.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/B2.png"))); // NOI18N
        elimU.setText("Eliminar Usuario");
        elimU.addActionListener(this::elimUActionPerformed);
        ModificarUsuarios.add(elimU);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setText("Nuevo Usuario:");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setText("Nueva Contraseña:");

        jButton12.setText("Confirmar");
        jButton12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton12MouseClicked(evt);
            }
        });

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/NU.png"))); // NOI18N

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/CU.png"))); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel19))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel20)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nuevoU, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ncontraU, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jButton12)
                        .addGap(146, 146, 146))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(nuevoU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel19))
                    .addComponent(jLabel27))
                .addGap(26, 26, 26)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel20)
                        .addComponent(ncontraU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel28))
                .addGap(31, 31, 31)
                .addComponent(jButton12)
                .addGap(49, 49, 49))
        );

        javax.swing.GroupLayout ModificarUsuarioLayout = new javax.swing.GroupLayout(ModificarUsuario.getContentPane());
        ModificarUsuario.getContentPane().setLayout(ModificarUsuarioLayout);
        ModificarUsuarioLayout.setHorizontalGroup(
            ModificarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        ModificarUsuarioLayout.setVerticalGroup(
            ModificarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        entrada.setMinimumSize(new java.awt.Dimension(243, 73));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setMaximumSize(new java.awt.Dimension(243, 73));
        jPanel14.setMinimumSize(new java.awt.Dimension(243, 73));

        jLabel26.setBackground(new java.awt.Color(255, 255, 255));
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/LogoDef.png"))); // NOI18N

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout entradaLayout = new javax.swing.GroupLayout(entrada.getContentPane());
        entrada.getContentPane().setLayout(entradaLayout);
        entradaLayout.setHorizontalGroup(
            entradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        entradaLayout.setVerticalGroup(
            entradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        FondoPantallaOg.setBackground(new java.awt.Color(255, 255, 255));
        FondoPantallaOg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FondoPantallaOgMouseClicked(evt);
            }
        });
        FondoPantallaOg.setLayout(new java.awt.GridBagLayout());

        fondoIniciar.setBackground(new java.awt.Color(255, 255, 255));

        jButton10.setFont(new java.awt.Font("Microsoft JhengHei UI", 1, 15)); // NOI18N
        jButton10.setText("Log In");
        jButton10.setBorderPainted(false);
        jButton10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton10MouseClicked(evt);
            }
        });

        usuario.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        usuario.setForeground(new java.awt.Color(204, 204, 204));
        usuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        usuario.setText("Ingrese Usuario");
        usuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usuarioMouseClicked(evt);
            }
        });

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/US2.png"))); // NOI18N
        jLabel13.setText("jLabel13");

        contraseña.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        contraseña.setForeground(new java.awt.Color(204, 204, 204));
        contraseña.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        contraseña.setText("Ingrese Contraseña");
        contraseña.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                contraseñaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                contraseñaMouseEntered(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Microsoft JhengHei", 1, 15)); // NOI18N
        jLabel14.setText("Usuario");

        jLabel15.setFont(new java.awt.Font("Microsoft JhengHei", 1, 15)); // NOI18N
        jLabel15.setText("Contraseña");

        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/AP1.png"))); // NOI18N
        jButton14.setContentAreaFilled(false);
        jButton14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton14MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout fondoIniciarLayout = new javax.swing.GroupLayout(fondoIniciar);
        fondoIniciar.setLayout(fondoIniciarLayout);
        fondoIniciarLayout.setHorizontalGroup(
            fondoIniciarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondoIniciarLayout.createSequentialGroup()
                .addGroup(fondoIniciarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(fondoIniciarLayout.createSequentialGroup()
                        .addGap(351, 351, 351)
                        .addComponent(jLabel15))
                    .addGroup(fondoIniciarLayout.createSequentialGroup()
                        .addGap(362, 362, 362)
                        .addComponent(jLabel14))
                    .addGroup(fondoIniciarLayout.createSequentialGroup()
                        .addGap(361, 361, 361)
                        .addComponent(jButton10))
                    .addGroup(fondoIniciarLayout.createSequentialGroup()
                        .addGap(382, 382, 382)
                        .addComponent(jButton14))
                    .addGroup(fondoIniciarLayout.createSequentialGroup()
                        .addGap(278, 278, 278)
                        .addGroup(fondoIniciarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(contraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(317, Short.MAX_VALUE))
        );
        fondoIniciarLayout.setVerticalGroup(
            fondoIniciarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondoIniciarLayout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel15)
                .addGap(4, 4, 4)
                .addComponent(contraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jButton10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton14)
                .addGap(80, 80, 80))
        );

        FondoPantallaOg.add(fondoIniciar, new java.awt.GridBagConstraints());

        BarraTareas.setBackground(new java.awt.Color(225, 225, 225));
        BarraTareas.setMaximumSize(new java.awt.Dimension(4, 60));
        BarraTareas.setMinimumSize(new java.awt.Dimension(4, 60));
        BarraTareas.setOpaque(false);
        BarraTareas.setPreferredSize(new java.awt.Dimension(4, 60));
        BarraTareas.setLayout(new java.awt.GridBagLayout());

        contenedor.setOpaque(false);
        contenedor.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 16, 0));

        calculadora1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/PA1.png"))); // NOI18N
        calculadora1.setBorder(null);
        calculadora1.setContentAreaFilled(false);
        calculadora1.setFocusPainted(false);
        calculadora1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                calculadora1MouseClicked(evt);
            }
        });
        calculadora1.addActionListener(this::calculadora1ActionPerformed);
        contenedor.add(calculadora1);

        calculadora.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/CA1.png"))); // NOI18N
        calculadora.setBorder(null);
        calculadora.setContentAreaFilled(false);
        calculadora.setFocusPainted(false);
        calculadora.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                calculadoraMouseClicked(evt);
            }
        });
        calculadora.addActionListener(this::calculadoraActionPerformed);
        contenedor.add(calculadora);

        explorarArchivos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/E.png"))); // NOI18N
        explorarArchivos.setBorder(null);
        explorarArchivos.setContentAreaFilled(false);
        explorarArchivos.setFocusPainted(false);
        explorarArchivos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                explorarArchivosMouseClicked(evt);
            }
        });
        explorarArchivos.addActionListener(this::explorarArchivosActionPerformed);
        contenedor.add(explorarArchivos);

        WitZig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/WDF2.png"))); // NOI18N
        WitZig.setBorder(null);
        WitZig.setBorderPainted(false);
        WitZig.setContentAreaFilled(false);
        WitZig.setFocusPainted(false);
        WitZig.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                WitZigMouseClicked(evt);
            }
        });
        WitZig.addActionListener(this::WitZigActionPerformed);
        contenedor.add(WitZig);

        editorTexto1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/Ed.png"))); // NOI18N
        editorTexto1.setBorder(null);
        editorTexto1.setContentAreaFilled(false);
        editorTexto1.setFocusPainted(false);
        editorTexto1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editorTexto1MouseClicked(evt);
            }
        });
        editorTexto1.addActionListener(this::editorTexto1ActionPerformed);
        contenedor.add(editorTexto1);

        TicTacToe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/XO2.png"))); // NOI18N
        TicTacToe.setBorder(null);
        TicTacToe.setContentAreaFilled(false);
        TicTacToe.setFocusPainted(false);
        TicTacToe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TicTacToeMouseClicked(evt);
            }
        });
        TicTacToe.addActionListener(this::TicTacToeActionPerformed);
        contenedor.add(TicTacToe);

        SnakeBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/S1.png"))); // NOI18N
        SnakeBoton.setBorder(null);
        SnakeBoton.setContentAreaFilled(false);
        SnakeBoton.setFocusPainted(false);
        SnakeBoton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SnakeBotonMouseClicked(evt);
            }
        });
        SnakeBoton.addActionListener(this::SnakeBotonActionPerformed);
        contenedor.add(SnakeBoton);

        BarraTareas.add(contenedor, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_END;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 300, 10, 300);
        FondoPantallaOg.add(BarraTareas, gridBagConstraints);

        FondoPantalla.setBackground(new java.awt.Color(204, 204, 204));
        FondoPantalla.setLayout(new java.awt.GridBagLayout());

        fondoImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fondosPantalla/F1.png"))); // NOI18N
        fondoImagen.setMaximumSize(null);
        fondoImagen.setMinimumSize(null);
        fondoImagen.setPreferredSize(null);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 28;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        FondoPantalla.add(fondoImagen, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        FondoPantallaOg.add(FondoPantalla, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(FondoPantallaOg, gridBagConstraints);

        BarraNavegacion.setBackground(new java.awt.Color(242, 242, 242));
        BarraNavegacion.setBorder(null);
        BarraNavegacion.setBorderPainted(false);
        BarraNavegacion.setOpaque(true);

        LogoWitZig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/WDF.png"))); // NOI18N
        LogoWitZig.setMargin(new java.awt.Insets(0, 0, 0, 0));
        LogoWitZig.setMaximumSize(new java.awt.Dimension(51, 32));
        LogoWitZig.setMinimumSize(new java.awt.Dimension(51, 32));
        LogoWitZig.setPreferredSize(new java.awt.Dimension(51, 32));

        ApagarS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/AP1.png"))); // NOI18N
        ApagarS.setText("Apagar");
        ApagarS.addActionListener(this::ApagarSActionPerformed);
        LogoWitZig.add(ApagarS);

        BarraNavegacion.add(LogoWitZig);

        ModificarPantalla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/P4.png"))); // NOI18N
        ModificarPantalla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ModificarPantallaMouseClicked(evt);
            }
        });
        ModificarPantalla.addActionListener(this::ModificarPantallaActionPerformed);
        BarraNavegacion.add(ModificarPantalla);

        LogIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/Log1.png"))); // NOI18N
        LogIn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LogInMouseClicked(evt);
            }
        });
        BarraNavegacion.add(LogIn);

        LogOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/Log2.png"))); // NOI18N
        LogOut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LogOutMouseClicked(evt);
            }
        });
        BarraNavegacion.add(LogOut);

        verUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/M1.png"))); // NOI18N
        verUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                verUsuariosMouseClicked(evt);
            }
        });
        BarraNavegacion.add(verUsuarios);

        crearUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoTipos/M2.png"))); // NOI18N
        crearUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                crearUsuariosMouseClicked(evt);
            }
        });
        BarraNavegacion.add(crearUsuarios);

        setJMenuBar(BarraNavegacion);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void ocultarFondo(boolean mostrar) {
        fondoImagen.setVisible(mostrar);
    }
    private void explorarArchivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_explorarArchivosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_explorarArchivosActionPerformed
    public void aparecerElementos(boolean mostrar) {
        FondoPantalla.setVisible(mostrar);
    }
    private void ModificarPantallaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarPantallaActionPerformed


    }//GEN-LAST:event_ModificarPantallaActionPerformed

    private void ModificarPantallaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ModificarPantallaMouseClicked
        personalizarPantalla.pack();
        personalizarPantalla.setLocationRelativeTo(null);
        personalizarPantalla.setVisible(true);

    }//GEN-LAST:event_ModificarPantallaMouseClicked

    private void PersonalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PersonalizarActionPerformed
        personalizarPantalla.pack();
        personalizarPantalla.setLocationRelativeTo(null);
        personalizarPantalla.setVisible(true);
    }//GEN-LAST:event_PersonalizarActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked

    }//GEN-LAST:event_formMouseClicked

    private void FondoPantallaOgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FondoPantallaOgMouseClicked
        int boton = evt.getButton();

        if (boton == 1) {
            //selecciona
        } else if (boton == 3) {
            int x = evt.getX();
            int y = evt.getY();
            PopUpMenu.show(FondoPantallaOg, x, y);
        }
    }//GEN-LAST:event_FondoPantallaOgMouseClicked

    private void editorTexto1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editorTexto1MouseClicked
        crearTexto.pack();
        crearTexto.setLocationRelativeTo(this);

        crearTexto.setVisible(true);
    }//GEN-LAST:event_editorTexto1MouseClicked

    private void editorTexto1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editorTexto1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editorTexto1ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        fechaHora();
    }//GEN-LAST:event_formWindowOpened

    private void WitZigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WitZigActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_WitZigActionPerformed

    private void explorarArchivosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_explorarArchivosMouseClicked
        exploradorArchivos.pack();
        exploradorArchivos.setLocationRelativeTo(this);
        exploradorArchivos.setVisible(true);
        tablaExplorador.clearSelection();
        tablaArchivosTxt.clearSelection();
        tablaArchivos.setVisible(false);
        tablaCarpetas.setVisible(true);
    }//GEN-LAST:event_explorarArchivosMouseClicked

    private void FuentesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FuentesMouseClicked
        panelPantalla.setVisible(false);
        panelFuentes.setVisible(true);
    }//GEN-LAST:event_FuentesMouseClicked

    private void FuentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FuentesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FuentesActionPerformed

    private void spinnerFuenteStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinnerFuenteStateChanged

        String fuente = (String) spinnerFuente.getValue();
        Font fuenteActual = ejemploFuente.getFont();
        int estilo = fuenteActual.getStyle();
        int tamaño = fuenteActual.getSize();
        Font fuenteEjemplo = new Font(fuente, estilo, tamaño);
        ejemploFuente.setFont(fuenteEjemplo);
        Color colorEleccion = ejemploFuente.getForeground();
        ejemploFuente.setForeground(colorEleccion);
    }//GEN-LAST:event_spinnerFuenteStateChanged

    private void tamañoFuenteStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tamañoFuenteStateChanged
        Font fuenteActual = ejemploFuente.getFont();
        String nombreActual = fuenteActual.getName();
        int estilo = fuenteActual.getStyle();
        int tamaño = (int) tamañoFuente.getValue();
        Font fuenteEjemplo = new Font(nombreActual, estilo, tamaño);
        ejemploFuente.setFont(fuenteEjemplo);
        Color colorEleccion = ejemploFuente.getForeground();
        ejemploFuente.setForeground(colorEleccion);
    }//GEN-LAST:event_tamañoFuenteStateChanged
    int estiloFuenteN = 0;
    private void PlainMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PlainMouseClicked
        estiloFuenteN = 0;
        Font fuenteActual = ejemploFuente.getFont();
        String nombreActual = fuenteActual.getName();
        int tamaño = fuenteActual.getSize();
        Font fuenteEjemplo = new Font(nombreActual, estiloFuenteN, tamaño);
        Color colorEleccion = ejemploFuente.getForeground();
        ejemploFuente.setFont(fuenteEjemplo);
        ejemploFuente.setForeground(colorEleccion);
    }//GEN-LAST:event_PlainMouseClicked

    private void BoldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BoldMouseClicked
        estiloFuenteN = 1;
        Font fuenteActual = ejemploFuente.getFont();
        String nombreActual = fuenteActual.getName();
        int tamaño = fuenteActual.getSize();
        Font fuenteEjemplo = new Font(nombreActual, estiloFuenteN, tamaño);
        ejemploFuente.setFont(fuenteEjemplo);
        Color colorEleccion = ejemploFuente.getForeground();
        ejemploFuente.setForeground(colorEleccion);
    }//GEN-LAST:event_BoldMouseClicked

    private void BoldItalicMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BoldItalicMouseClicked
        estiloFuenteN = 3;
        Font fuenteActual = ejemploFuente.getFont();
        String nombreActual = fuenteActual.getName();
        int tamaño = fuenteActual.getSize();
        Font fuenteEjemplo = new Font(nombreActual, estiloFuenteN, tamaño);
        Color colorEleccion = ejemploFuente.getForeground();
        ejemploFuente.setFont(fuenteEjemplo);
        ejemploFuente.setForeground(colorEleccion);
    }//GEN-LAST:event_BoldItalicMouseClicked

    private void ItalicMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ItalicMouseClicked
        estiloFuenteN = 2;
        Font fuenteActual = ejemploFuente.getFont();
        String nombreActual = fuenteActual.getName();
        int tamaño = fuenteActual.getSize();
        Font fuenteEjemplo = new Font(nombreActual, estiloFuenteN, tamaño);
        Color colorEleccion = ejemploFuente.getForeground();
        ejemploFuente.setFont(fuenteEjemplo);
        ejemploFuente.setForeground(colorEleccion);
    }//GEN-LAST:event_ItalicMouseClicked

    Font fontGeneral;
    Color colorFont;
    private void confirmarFuenteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmarFuenteMouseClicked
        try {
            String fuente = (String) spinnerFuente.getValue();

            int estilo = estiloFuenteN;
            int tamaño = (int) tamañoFuente.getValue();
            Font fuenteConf = new Font(fuente, estilo, tamaño);
            cargarBarra();
            Color colorElegido = ejemploFuente.getForeground();
            cambiarFontPantallaInicio(fuenteConf, colorElegido);

//            cambiarColorFontPI(colorElegido);
            fontGeneral = fuenteConf;
            colorFont = colorElegido;
            JOptionPane.showMessageDialog(this, "Cambia realizado");
//            cambiarEstiloGlobal(fuenteConf, colorElegido);

        } catch (Exception E) {
            Font fuenteActual = ejemploFuente.getFont();
            Color colorElegido = ejemploFuente.getForeground();
            cambiarFontPantallaInicio(fuenteActual, colorElegido);
            Color colorDef = ejemploFuente.getForeground();
            cambiarColorFontPI(colorDef);

        }

    }//GEN-LAST:event_confirmarFuenteMouseClicked

    private void aspectosExternosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aspectosExternosMouseClicked
        eleccionPantalla.setVisible(false);
        eleccionExternos.setVisible(true);
    }//GEN-LAST:event_aspectosExternosMouseClicked

    private void aspectosExternosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aspectosExternosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_aspectosExternosActionPerformed

    private void fondoPmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fondoPmMouseClicked
        eleccionExternos.setVisible(false);
        eleccionPantalla.setVisible(true);
    }//GEN-LAST:event_fondoPmMouseClicked

    private void fondoPmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fondoPmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fondoPmActionPerformed
    public Color aplicarTransparencia(Color nuevoColor, int alpha) {
        return new Color(nuevoColor.getRed(), nuevoColor.getGreen(), nuevoColor.getBlue(), alpha);
    }
    private void seleccionColor3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionColor3MouseClicked
        Color seleccionado1 = JColorChooser.showDialog(this, "Seleccione un color", Color.LIGHT_GRAY);

        if (seleccionado1 != null) {
            boolean verf = false;

            if (barraT1.isSelected()) {
                BarraTareas.setBackground(seleccionado1);
                BarraTareas.setBorder(new com.formdev.flatlaf.ui.FlatLineBorder(new java.awt.Insets(1, 1, 1, 1), seleccionado1, 1, 25));

                verf = true;
            }
            if (barraN1.isSelected()) {
                BarraNavegacion.setBackground(seleccionado1);
                verf = true;
            }
            if (verf == false) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un elemento de la pantalla.");
            }

        }

    }//GEN-LAST:event_seleccionColor3MouseClicked

    private void seleccionFondoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionFondoActionPerformed
        Object elegido = seleccionFondo.getSelectedItem();

        String eleccion = (String) elegido;

        if (eleccion.equals("Color Solido")) {
            Color seleccionado = JColorChooser.showDialog(this, "Seleccione un color:", Color.LIGHT_GRAY);
            if (seleccionado != null) {

                colorFondoP(seleccionado);

                ocultarFondo(false);
                FondoPantallaOg.setComponentZOrder(BarraTareas, 0);
                FondoPantallaOg.repaint();
            }

        } else if (eleccion.equals("Imagen")) {

            Imagen.setVisible(true);
        }
    }//GEN-LAST:event_seleccionFondoActionPerformed
    boolean nevadaElegido = false;
    boolean tardeElegido = false;
    boolean rachoElegido = false;
    boolean lagoElegido = false;
    boolean porDefecto = false;

    private void lagoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lagoMouseClicked
        nevadaElegido = false;
        tardeElegido = false;
        rachoElegido = false;
        lagoElegido = true;
        porDefecto = false;
        porDefecto2 = false;
        porDefecto3 = false;
    }//GEN-LAST:event_lagoMouseClicked

    private void nevadaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nevadaMouseClicked
        nevadaElegido = true;
        tardeElegido = false;
        rachoElegido = false;
        lagoElegido = false;
        porDefecto = false;
        porDefecto2 = false;
        porDefecto3 = false;
    }//GEN-LAST:event_nevadaMouseClicked

    private void tardeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tardeMouseClicked
        nevadaElegido = false;
        tardeElegido = true;
        rachoElegido = false;
        lagoElegido = false;
        porDefecto = false;
        porDefecto2 = false;
        porDefecto3 = false;
    }//GEN-LAST:event_tardeMouseClicked

    private void ranchoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ranchoMouseClicked
        nevadaElegido = false;
        tardeElegido = false;
        rachoElegido = true;
        lagoElegido = false;
        porDefecto = false;
        porDefecto2 = false;
        porDefecto3 = false;
    }//GEN-LAST:event_ranchoMouseClicked

    private void elegirFondomMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_elegirFondomMouseClicked
        if (imagenPers == true && nevadaElegido == false && tardeElegido == false && rachoElegido == false && lagoElegido == false
                && porDefecto == false && porDefecto2 == false && porDefecto3 == false) {
            ocultarFondo(true);
            fondoImagen.setIcon(imagenFondoPersonaliz);
            FondoPantallaOg.setComponentZOrder(BarraTareas, 0);
            FondoPantallaOg.repaint();
        } else {
            ocultarFondo(true);
            if (nevadaElegido == true) {
                imagenPers = false;
                cambiarFondo(1);
            } else if (tardeElegido == true) {
                imagenPers = false;
                cambiarFondo(2);
            } else if (rachoElegido == true) {
                imagenPers = false;
                cambiarFondo(3);
            } else if (lagoElegido == true) {
                imagenPers = false;
                cambiarFondo(4);
            } else if (porDefecto == true) {
                imagenPers = false;
                cambiarFondo(5);
            } else if (porDefecto2 == true) {
                imagenPers = false;
                cambiarFondo(6);
            } else if (porDefecto3 == true) {
                imagenPers = false;
                cambiarFondo(7);
            } else {
                imagenPers = false;
                JOptionPane.showMessageDialog(this, "Debe darle click a un fondo, posterior a eso confirmar su seleccion.");

            }
        }

    }//GEN-LAST:event_elegirFondomMouseClicked

    private void PantallaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PantallaMouseClicked
        panelPantalla.setVisible(true);
        panelFuentes.setVisible(false);
    }//GEN-LAST:event_PantallaMouseClicked

    private void PantallaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PantallaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PantallaActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        Color colorEleccion = JColorChooser.showDialog(this, "Seleccione un color", Color.LIGHT_GRAY);
        ejemploFuente.setForeground(colorEleccion);
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void spinnerFuente1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinnerFuente1StateChanged

        String fuente = (String) spinnerFuente.getValue();
        Font fuenteActual = ejemploFuente1.getFont();
        int estilo = fuenteActual.getStyle();
        int tamaño = fuenteActual.getSize();
        Font fuenteEjemplo = new Font(fuente, estilo, tamaño);
        ejemploFuente1.setFont(fuenteEjemplo);
        Color colorEleccion = ejemploFuente1.getForeground();
        ejemploFuente1.setForeground(colorEleccion);
    }//GEN-LAST:event_spinnerFuente1StateChanged

    private void tamañoFuente1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tamañoFuente1StateChanged
        Font fuenteActual = ejemploFuente1.getFont();
        String nombreActual = fuenteActual.getName();
        int estilo = fuenteActual.getStyle();
        int tamaño = (int) tamañoFuente1.getValue();
        Font fuenteEjemplo = new Font(nombreActual, estilo, tamaño);
        ejemploFuente1.setFont(fuenteEjemplo);
        Color colorEleccion = ejemploFuente1.getForeground();
        ejemploFuente1.setForeground(colorEleccion);
    }//GEN-LAST:event_tamañoFuente1StateChanged

    private void Plain1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Plain1MouseClicked
        estiloFuenteN = 0;
        Font fuenteActual = ejemploFuente1.getFont();
        String nombreActual = fuenteActual.getName();
        int tamaño = fuenteActual.getSize();
        Font fuenteEjemplo = new Font(nombreActual, estiloFuenteN, tamaño);
        Color colorEleccion = ejemploFuente1.getForeground();
        ejemploFuente1.setFont(fuenteEjemplo);
        ejemploFuente1.setForeground(colorEleccion);
    }//GEN-LAST:event_Plain1MouseClicked

    private void Bold1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Bold1MouseClicked
        estiloFuenteN = 1;
        Font fuenteActual = ejemploFuente1.getFont();
        String nombreActual = fuenteActual.getName();
        int tamaño = fuenteActual.getSize();
        Font fuenteEjemplo = new Font(nombreActual, estiloFuenteN, tamaño);
        ejemploFuente1.setFont(fuenteEjemplo);
        Color colorEleccion = ejemploFuente1.getForeground();
        ejemploFuente1.setForeground(colorEleccion);
    }//GEN-LAST:event_Bold1MouseClicked

    private void Italic1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Italic1MouseClicked
        estiloFuenteN = 2;
        Font fuenteActual = ejemploFuente1.getFont();
        String nombreActual = fuenteActual.getName();
        int tamaño = fuenteActual.getSize();
        Font fuenteEjemplo = new Font(nombreActual, estiloFuenteN, tamaño);
        Color colorEleccion = ejemploFuente1.getForeground();
        ejemploFuente1.setFont(fuenteEjemplo);
        ejemploFuente1.setForeground(colorEleccion);
    }//GEN-LAST:event_Italic1MouseClicked

    private void BoldItalic1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BoldItalic1MouseClicked
        estiloFuenteN = 3;
        Font fuenteActual = ejemploFuente1.getFont();
        String nombreActual = fuenteActual.getName();
        int tamaño = fuenteActual.getSize();
        Font fuenteEjemplo = new Font(nombreActual, estiloFuenteN, tamaño);
        Color colorEleccion = ejemploFuente1.getForeground();
        ejemploFuente1.setFont(fuenteEjemplo);
        ejemploFuente1.setForeground(colorEleccion);
    }//GEN-LAST:event_BoldItalic1MouseClicked

    private void guardarArchivoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guardarArchivoMouseClicked


    }//GEN-LAST:event_guardarArchivoMouseClicked
    public boolean revisarCarpeta(File Carpeta) {
        boolean parar = true;

        if (Carpeta != null && Carpeta.listFiles() != null) {
            File[] archivos1 = Carpeta.listFiles();

            for (int i = 0; i < archivos1.length; i++) {
                File archivoExistente = archivos1[i];

                if (archivoExistente.isDirectory()) {
                    parar = false;
                }
            }
        }

        return parar;
    }

    public carpetasArchivos retornarCarpeta(File Carpeta, carpetasArchivos carpetaLocalizar2) {
        if (revisarCarpeta(Carpeta) == false) {

            ArrayList<archivosTxt> archivosExis = new ArrayList<>();
            ArrayList<carpetasArchivos> carpetaExis = new ArrayList<>();

            if (Carpeta != null && Carpeta.listFiles() != null) {

                File[] archivos1 = Carpeta.listFiles();
                SimpleDateFormat formatoDia2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");

                for (int i = 0; i < archivos1.length; i++) {

                    File archivoExistente = archivos1[i];

                    if (archivoExistente.isFile() && archivoExistente.getName().toLowerCase().endsWith(".txt")) {

                        String nombre2 = archivoExistente.getName();
                        long tamañoBytes2 = archivoExistente.length();
                        double tamañoKb2 = tamañoBytes2 / 1024.0;

                        long fechaMiliD2 = archivoExistente.lastModified();
                        String dia2 = formatoDia2.format(new Date(fechaMiliD2));

                        String tipo2 = ".txt";
                        String ruta2 = archivoExistente.getAbsolutePath();

                        archivosTxt archivoEx = new archivosTxt(nombre2, archivoExistente, dia2, tipo2, ruta2, tamañoKb2);

                        archivosExis.add(archivoEx);

                        boolean archivoYaExiste = false;

                        for (int a = 0; a < listaArchivos.size(); a++) {
                            if (listaArchivos.get(a).rutA.equals(ruta2)) {
                                archivoYaExiste = true;
                            }
                        }

                        if (archivoYaExiste == false) {
                            listaArchivos.add(archivoEx);
                        }
                    }

                    if (archivoExistente.isDirectory()) {

                        File Carpeta2 = archivoExistente;

                        String nombreCa2 = Carpeta2.getName();
                        long tamañoBytesCa2 = Carpeta2.length();
                        double tamañoKbC2 = tamañoBytesCa2 / 1024.0;

                        long fechaMilis2 = Carpeta2.lastModified();
                        Date fechaCa2 = new Date(fechaMilis2);

                        SimpleDateFormat formatoDiaC2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");

                        String tipoCa2 = "Carpeta";
                        String diaCa2 = formatoDiaC2.format(fechaCa2);
                        String rutaCa2 = Carpeta2.getAbsolutePath();

                        carpetasArchivos carpetaLocalizar3 = new carpetasArchivos(nombreCa2, Carpeta2, diaCa2, tipoCa2, tamañoBytesCa2, rutaCa2);

                        carpetaLocalizar3 = retornarCarpeta(Carpeta2, carpetaLocalizar3);

                        boolean verf = false;

                        for (int r = 0; r < listaCarpetas.size(); r++) {
                            if (listaCarpetas.get(r).ruta.equals(carpetaLocalizar3.ruta)) {
                                verf = true;
                            }
                        }

                        if (verf == false) {
                            listaCarpetas.add(carpetaLocalizar3);
                        }

                        carpetaExis.add(carpetaLocalizar3);
                    }
                }

                for (int i = 0; i < archivosExis.size(); i++) {
                    carpetaLocalizar2.añadirArchivo(archivosExis.get(i));

                }

                for (int i = 0; i < carpetaExis.size(); i++) {

                    carpetaLocalizar2.añadirCarpeta(carpetaExis.get(i));
                }
            }

            return carpetaLocalizar2;

        } else {

            ArrayList<archivosTxt> archivosExis = new ArrayList<>();

            if (Carpeta != null && Carpeta.listFiles() != null) {

                File[] archivos1 = Carpeta.listFiles();
                SimpleDateFormat formatoDia2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");

                for (int i = 0; i < archivos1.length; i++) {

                    File archivoExistente = archivos1[i];

                    if (archivoExistente.isFile() && archivoExistente.getName().toLowerCase().endsWith(".txt")) {

                        String nombre2 = archivoExistente.getName();
                        long tamañoBytes2 = archivoExistente.length();
                        double tamañoKb2 = tamañoBytes2 / 1024.0;

                        long fechaMiliD2 = archivoExistente.lastModified();

                        String dia2 = formatoDia2.format(new Date(fechaMiliD2));

                        String tipo2 = ".txt";
                        String ruta2 = archivoExistente.getAbsolutePath();

                        archivosTxt archivoEx = new archivosTxt(nombre2, archivoExistente, dia2, tipo2, ruta2, tamañoKb2);

                        archivosExis.add(archivoEx);

                        boolean archivoYaExiste = false;

                        for (int a = 0; a < listaArchivos.size(); a++) {

                            if (listaArchivos.get(a).rutA.equals(ruta2)) {
                                archivoYaExiste = true;
                            }
                        }

                        if (archivoYaExiste == false) {
                            listaArchivos.add(archivoEx);
                        }
                    }
                }

                for (int i = 0; i < archivosExis.size(); i++) {
                    carpetaLocalizar2.añadirArchivo(archivosExis.get(i));

                }
            }

            boolean verf = false;

            for (int i = 0; i < listaCarpetas.size(); i++) {
                if (listaCarpetas.get(i).ruta.equals(carpetaLocalizar2.ruta)) {
                    verf = true;
                }
            }

            if (verf == false) {
                listaCarpetas.add(carpetaLocalizar2);
            }

            return carpetaLocalizar2;
        }

    }

    ArrayList<archivosTxt> listaArchivos = new ArrayList<>();
    ArrayList<carpetasArchivos> listaCarpetas = new ArrayList<>();
    private void guardarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarArchivoActionPerformed

        exploradorPc.setVisible(true);

        int elegir = exploradorPc.showSaveDialog(this);

        if (elegir == exploradorPc.APPROVE_OPTION) {

            try {

                File archivo1 = new File(exploradorPc.getSelectedFile() + ".txt");

                if (archivo1.exists() == false) {
                    archivo1.createNewFile();
                }

                FileWriter guardar = new FileWriter(archivo1);
                guardar.write(ejemploFuente1.getText());
                guardar.close();
                String nombre = archivo1.getName();

                long tamañoBytes = archivo1.length();
                double tamañoKb = tamañoBytes / 1024.0;

                long fechaMiliD = archivo1.lastModified();
                Date fecha = new Date(fechaMiliD);

                SimpleDateFormat formatoDia = new SimpleDateFormat("dd/MM/yyyy HH:mm");

                String tipo = ".txt";
                String dia = formatoDia.format(fecha);
                String ruta = archivo1.getAbsolutePath();

                archivosTxt nuevoArchivo = new archivosTxt(nombre, archivo1, dia, tipo, ruta, tamañoKb);

                File Carpeta = archivo1.getParentFile();

                String nombreCa = Carpeta.getName();

                long tamañoBytesCa = Carpeta.length();
                double tamañoKbC = tamañoBytesCa / 1024.0;

                long fechaMilis = Carpeta.lastModified();
                Date fechaCa = new Date(fechaMilis);

                SimpleDateFormat formatoDiaC = new SimpleDateFormat("dd/MM/yyyy HH:mm");

                String tipoCa = "Carpeta";
                String diaCa = formatoDiaC.format(fechaCa);
                String rutaCa = Carpeta.getAbsolutePath();

                carpetasArchivos carpetaLocalizar = new carpetasArchivos(nombreCa, Carpeta, diaCa, tipoCa, tamañoBytesCa, rutaCa);

                carpetaLocalizar = retornarCarpeta(Carpeta, carpetaLocalizar);
                boolean verf = true;
                for (int i = 0; i < listaCarpetas.size(); i++) {
                    if (listaCarpetas.get(i).ruta.equals(rutaCa)) {
                        listaCarpetas.set(i, carpetaLocalizar);
                        verf = false;
                        break;
                    } else {

                    }
                }
                if (verf == true) {
                    listaCarpetas.add(carpetaLocalizar);
                }
                cargarBarra();
                JOptionPane.showMessageDialog(null, "Archivo guardado con éxito");

                ejemploFuente1.setText(null);

            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, "Ha ocurrido un error.");
            }
        }
    }//GEN-LAST:event_guardarArchivoActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        Color colorEleccion = JColorChooser.showDialog(this, "Seleccione un color", Color.LIGHT_GRAY);
        ejemploFuente1.setForeground(colorEleccion);
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked

        if (fondoG.isSelected() == false && fondoOp.isSelected() == false && BarraN2.isSelected() == false) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una de las casillas");
            return;
        }

        Color colorEleccion = JColorChooser.showDialog(this, "Seleccione un color", Color.LIGHT_GRAY);
        if (fondoG.isSelected()) {
            fondoGen.setBackground(colorEleccion);
        }
        if (fondoOp.isSelected()) {
            fondoOpc.setBackground(colorEleccion);
            crearTexto.setBackground(colorEleccion);
        }
        if (BarraN2.isSelected()) {
            opcionesEditor.setBackground(colorEleccion);
        }

    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void personalizarEditorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_personalizarEditorMouseClicked
        panelColoresElementos.setVisible(true);
    }//GEN-LAST:event_personalizarEditorMouseClicked

    private void personalizarEditor2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_personalizarEditor2MouseClicked

    }//GEN-LAST:event_personalizarEditor2MouseClicked

    private void fondoGenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fondoGenMouseClicked
        int boton = evt.getButton();
        int x = evt.getX();
        int y = evt.getY();
        if (boton == 3) {
            PopUp.show(fondoGen, x, y);
        }
    }//GEN-LAST:event_fondoGenMouseClicked

    private void personalizarEditor2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_personalizarEditor2ActionPerformed
        panelColoresElementos.setVisible(true);
    }//GEN-LAST:event_personalizarEditor2ActionPerformed

    private void abrirArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirArchivoActionPerformed
        exploradorPc.setVisible(true);

        if (exploradorPc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File archivo = exploradorPc.getSelectedFile();
            cargarBarra();
            try (Scanner lector = new Scanner(archivo)) {
                ejemploFuente1.setText("");

                while (lector.hasNextLine()) {
                    ejemploFuente1.append(lector.nextLine() + "\n");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Ha ocurrido un error, asegurese de seleccionar el archivo correcto.");

            }
        }
    }//GEN-LAST:event_abrirArchivoActionPerformed

    private void crearTextoWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_crearTextoWindowOpened

    }//GEN-LAST:event_crearTextoWindowOpened

    private void BorrarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BorrarBotonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BorrarBotonActionPerformed

    private void AñadirBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AñadirBotonActionPerformed

        CrearCarpeta.pack();
        CrearCarpeta.setLocationRelativeTo(null);

        CrearCarpeta.setVisible(true);
    }//GEN-LAST:event_AñadirBotonActionPerformed

    private void AñadirBotonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AñadirBotonMouseClicked
//        tablaExplorador.setVisible(false);
        CrearCarpeta.pack();
        CrearCarpeta.setLocationRelativeTo(null);

        CrearCarpeta.setVisible(true);

    }//GEN-LAST:event_AñadirBotonMouseClicked

    private void exploradorArchivosWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exploradorArchivosWindowActivated

    }//GEN-LAST:event_exploradorArchivosWindowActivated

    private void exploradorArchivosWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exploradorArchivosWindowOpened

        Timer timer = new Timer(1000, e -> {
            filaSeleccion = tablaExplorador.getSelectedRow();
            if (filaSeleccion != -1 && tablaArchivos.isVisible() == false) {
                String archivoC = (String) tablaExplorador.getValueAt(filaSeleccion, 0);
                for (int i = 0; i < listaCarpetas.size(); i++) {
                    if (listaCarpetas.get(i).getNombre().equals(archivoC)) {
                        ruta.setText(listaCarpetas.get(i).ruta);
                    }
                }
            }

            if (filaSeleccion == -1) {
                DefaultTableModel modeloTabla = new DefaultTableModel() {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                tablaExplorador.setModel(modeloTabla);
                modeloTabla.addColumn("Nombre");
                modeloTabla.addColumn("Fecha de Modificacion");
                modeloTabla.addColumn("Tipo");
                modeloTabla.addColumn("Tamaño");
                tablaExplorador.getColumnModel().getColumn(0).setCellRenderer((t, v, s, f, r, c) -> {
                    JLabel l = new JLabel(String.valueOf(v));
                    if (r >= 0) {
                        l.setIcon(new ImageIcon(getClass().getResource("/Imagenes/LogoTipos/C2.png")));
                    }
                    return l;
                });

                for (int i = 0; i < listaCarpetas.size(); i++) {
                    carpetasArchivos temp = listaCarpetas.get(i);
                    String tamaño = String.format("%.2f", temp.getTamaño());
                    String[] datos = {temp.getNombre(), temp.getFechaMod(), temp.getTipo(), tamaño + " bytes"};
                    modeloTabla.addRow(datos);

                }
            } else {

            }

        });

        timer.start();


    }//GEN-LAST:event_exploradorArchivosWindowOpened
    File rutaGuardar;
    boolean carpeta = false;
    boolean archivos = false;

    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseClicked
//        String rutaCompu = System.getProperty("user.home");

        for (int i = 0; i < listaCarpetas.size(); i++) {
            if (nombreCarpeta.getText().equals(listaCarpetas.get(i).getNombre())) {
                JOptionPane.showMessageDialog(null, "Ya existe una carpeta con ese nombre. Intente de nuevo.");
                return;
            }

        }
        if (nombreCarpeta.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe asignarle un nombre a la carpeta.");
            return;
        }
        if (rutaGuardar == null) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una ruta para la carpeta.");
            return;

        }

        File carpeta = new File(rutaGuardar, nombreCarpeta.getText());
        carpeta.mkdirs();
        String ruta = carpeta.getAbsolutePath();
        String nombreCa = carpeta.getName();
        long tamañoBytesCa = carpeta.length();
        double tamañoKbC = tamañoBytesCa / 1024.0;
        long fechaMilis = carpeta.lastModified();
        Date fechaCa = new Date(fechaMilis);
        SimpleDateFormat formatoDiaC = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String tipoCa = "Carpeta";
        String diaCa = formatoDiaC.format(fechaCa);
        carpetasArchivos carpetaNueva = new carpetasArchivos(nombreCa, carpeta, diaCa, tipoCa, tamañoBytesCa, ruta);
        listaCarpetas.add(carpetaNueva);
        cargarBarra();
        JOptionPane.showMessageDialog(null, "Carpeta exitosamente creada!");
        nombreCarpeta.setText(null);
        tablaExplorador.clearSelection();
        CrearCarpeta.dispose();

    }//GEN-LAST:event_jButton6MouseClicked

    private void seleccionarRutaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarRutaMouseClicked
        exploradorPc2.setVisible(true);
        exploradorPc2.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (exploradorPc2.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File seleccion = exploradorPc2.getSelectedFile();
            rutaGuardar = seleccion.isDirectory() ? seleccion : seleccion.getParentFile();

        }
    }//GEN-LAST:event_seleccionarRutaMouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed
    int columnaSeleccion = 0;
    int filaSeleccion = 0;
    private void BorrarBotonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BorrarBotonMouseClicked

        eliminarCarpeta();


    }//GEN-LAST:event_BorrarBotonMouseClicked

    private void tablaExploradorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaExploradorMouseClicked
        int boton = evt.getButton();
        int x = evt.getX();
        int y = evt.getY();
        if (boton == 3) {
            OpcionesCarpetas.show(tablaExplorador, x, y);
        } else if (evt.getButton() == java.awt.event.MouseEvent.BUTTON1 && evt.getClickCount() == 2) {
//            tablaCarpetas.setVisible(false);
//            tablaArchivos.setVisible(true);
//            BorrarBoton.setVisible(false);
//            BorrarBotonArchivos.setVisible(true);
//            regresar.setVisible(true);
        }
    }//GEN-LAST:event_tablaExploradorMouseClicked

    private void exploradorArchivosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exploradorArchivosMouseClicked
        tablaExplorador.clearSelection();
        tablaArchivosTxt.clearSelection();
    }//GEN-LAST:event_exploradorArchivosMouseClicked
    public void eliminarCarpeta() {
        try {
            columnaSeleccion = tablaExplorador.getSelectedColumn();
            filaSeleccion = tablaExplorador.getSelectedRow();
            String carpetaElegidaN = (String) tablaExplorador.getValueAt(filaSeleccion, 0);
            int sentencia = 0;
            for (int i = 0; i < listaCarpetas.size(); i++) {
                if (listaCarpetas.get(i).getNombre().equals(carpetaElegidaN)) {
                    sentencia = i;
                    carpetasArchivos carpetaSeleccionada = listaCarpetas.get(i);
                    File temp = carpetaSeleccionada.ubicacionCarpeta;
                    Path rutaCarp = temp.toPath();
                    eliminarCarpetaEscondida(listaCarpetas, carpetaElegidaN);
                    if (Files.exists(rutaCarp)) {
                        try {
                            Files.walk(rutaCarp)
                                    .sorted(Comparator.reverseOrder())
                                    .map(Path::toFile)
                                    .forEach(File::delete);

                        } catch (Exception E) {
                            JOptionPane.showMessageDialog(null, "Error, por alguna razon no se pudo borrar");
                        }

                    }
                }

            }
            listaCarpetas.remove(listaCarpetas.get(sentencia));
            JOptionPane.showMessageDialog(null, "Carpeta exitosamente eliminada.");
            tablaExplorador.clearSelection();
        } catch (Exception E) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una carpeta");
            return;
        }

    }
    private void EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarActionPerformed
        eliminarCarpeta();
    }//GEN-LAST:event_EliminarActionPerformed

    private void tablaArchivosTxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaArchivosTxtMouseClicked
        int button = evt.getButton();
        int x = evt.getX();
        int y = evt.getY();

        if (button == 3) {
            OpcionesArchivos.show(tablaArchivosTxt, x, y);

        }
    }//GEN-LAST:event_tablaArchivosTxtMouseClicked

    private void AbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AbrirActionPerformed
        tablaArchivosTxt.clearSelection();
        filaSeleccion = tablaExplorador.getSelectedRow();
        if (filaSeleccion == -1) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un archivo de la lista");
            return;
        }
        String archivoElegidoN = (String) tablaExplorador.getValueAt(filaSeleccion, 0);

        String archivoElegidoN2 = (String) tablaExplorador.getValueAt(filaSeleccion, 2);
        if (archivoElegidoN2.equals("Carpeta")) {

            for (int i = 0; i < listaCarpetas.size(); i++) {

                if (listaCarpetas.get(i).getNombre().equals(archivoElegidoN)) {
                    subCarpetaElegida = archivoElegidoN;

                }
            }

        }
        tablaCarpetas.setVisible(false);
        tablaArchivos.setVisible(true);
        BorrarBoton.setVisible(false);
        BorrarBotonArchivos.setVisible(true);
        regresar.setVisible(true);
    }//GEN-LAST:event_AbrirActionPerformed
    int filaSeleccion2 = 0;
    int columnaSeleccion2 = 0;

    public boolean verificarListado(String carpetaNombre) {
        ArrayList<archivosTxt> archivosC = new ArrayList<>();
        ArrayList<carpetasArchivos> archivosCa = new ArrayList<>();
        boolean verf = false;
        for (int i = 0; i < listaCarpetas.size(); i++) {

            carpetasArchivos temp = listaCarpetas.get(i);
            if (temp.getNombre().equals(carpetaNombre)) {
                archivosC = temp.regresarArchivos();
                archivosCa = temp.regresarCarpeta();
            }

        }
        if (archivosC.isEmpty() || archivosCa.isEmpty()) {
            verf = true;
        }

        return verf;
    }
    String carpetaElegida = "";
    String subCarpetaElegida = "";
    private void tablaArchivosTxtAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tablaArchivosTxtAncestorAdded
        String carpetaElegidaC = "";
        try {
            carpetaElegidaC = (String) tablaExplorador.getValueAt(filaSeleccion, 0);
            carpetaElegida = carpetaElegidaC;
        } catch (Exception E) {

        }
        try {
            subCarpetaElegida = carpetaElegida;

//                    carpetaElegida = (String) tablaExplorador.getValueAt(filaSeleccion, 0);
        } catch (Exception E) {

        }

//        if (verificarListado(carpetaElegida) == true) {
//
        ////            JOptionPane.showMessageDialog(null, "Esta Carpeta no tiene archivos");
////            tablaCarpetas.setVisible(true);
////            tablaArchivos.setVisible(false);
////            regresar.setVisible(false);
//        } else {
         
            Timer timer = new Timer(1000, e -> {

            filaSeleccion2 = tablaArchivosTxt.getSelectedRow();

            if (filaSeleccion2 != -1 && tablaCarpetas.isVisible() == false) {
                String fSN = (String) tablaArchivosTxt.getValueAt(filaSeleccion2, 0);
                for (int i = 0; i < listaCarpetas.size(); i++) {
                    if (listaCarpetas.get(i).getNombre().equals(subCarpetaElegida)) {
                        ArrayList<archivosTxt> archivosC = listaCarpetas.get(i).regresarArchivos();
                        ArrayList<carpetasArchivos> carpetas = listaCarpetas.get(i).regresarCarpeta();
                        for (int j = 0; j < archivosC.size(); j++) {
                            if (archivosC.get(j).getNombreA().equals(fSN)) {
                                ruta.setText(archivosC.get(j).rutA);
                            }
                        }
                        for (int j = 0; j < carpetas.size(); j++) {
                            if (carpetas.get(j).getNombre().equals(fSN)) {
                                ruta.setText(carpetas.get(j).ruta);
                            }
                        }

                    }
                }
            }
            if (filaSeleccion2 == -1) {

                DefaultTableModel modeloTablaA = new DefaultTableModel() {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };

                tablaArchivosTxt.setModel(modeloTablaA);
                modeloTablaA.addColumn("Nombre");
                modeloTablaA.addColumn("Fecha de Modificacion");
                modeloTablaA.addColumn("Tipo");
                modeloTablaA.addColumn("Tamaño");
                tablaArchivosTxt.getColumnModel().getColumn(0).setCellRenderer((t, v, s, f, r, c) -> {
                    JLabel l = new JLabel(String.valueOf(v));
                    if (r >= 0) {
                        String columnaTipo = (String) tablaArchivosTxt.getValueAt(r, 2);

                        ImageIcon logo1 = new ImageIcon(getClass().getResource("/Imagenes/LogoTipos/C2.png"));
                        ImageIcon logo2 = new ImageIcon(getClass().getResource("/Imagenes/LogoTipos/Ed2.png"));
                        if (columnaTipo.equals("Carpeta")) {
                            l.setIcon(logo1);
                        } else {
                            l.setIcon(logo2);
                        }

                    }
                    return l;
                });
                ArrayList<archivosTxt> archivos1 = new ArrayList<>();
                ArrayList<carpetasArchivos> archivosC = new ArrayList<>();

                for (int i = 0; i < listaCarpetas.size(); i++) {

                    carpetasArchivos temp = listaCarpetas.get(i);
                    if (temp.getNombre().equals(subCarpetaElegida)) {
                        archivos1 = temp.regresarArchivos();
                        archivosC = temp.regresarCarpeta();
                    }

                }

//                if (archivosC != null) {
                for (int i = 0; i < archivosC.size(); i++) {

                    carpetasArchivos temp = archivosC.get(i);
                    String tamaño = String.format("%.2f", temp.getTamaño());
                    String[] datos = {temp.getNombre(), temp.getFechaMod(), temp.getTipo(), tamaño + " bytes"};
                    modeloTablaA.addRow(datos);
                }
//                }
                if (archivos1.isEmpty()) {

                }
                for (int i = 0; i < archivos1.size(); i++) {

                    archivosTxt archivosN = archivos1.get(i);
                    String tamaño = String.format("%.2f", archivosN.getTamañoA());
                    String[] datos = {archivosN.getNombreA(), archivosN.getFechaModificado(), archivosN.getTipoA(), tamaño + " bytes"};
                    modeloTablaA.addRow(datos);
                }

            } else {

            }

        });

        timer.start();
//        }


    }//GEN-LAST:event_tablaArchivosTxtAncestorAdded

    private void regresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_regresarMouseClicked
        tablaCarpetas.setVisible(true);
        tablaArchivos.setVisible(false);
        BorrarBotonArchivos.setVisible(false);
        BorrarBoton.setVisible(true);
        regresar.setVisible(false);
        tablaExplorador.clearSelection();
    }//GEN-LAST:event_regresarMouseClicked

    private void AbrirArchivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AbrirArchivosActionPerformed

        filaSeleccion2 = tablaArchivosTxt.getSelectedRow();
        if (filaSeleccion2 == -1) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un archivo de la lista");
            return;
        }
        String archivoElegidoN = (String) tablaArchivosTxt.getValueAt(filaSeleccion2, 0);

        String archivoElegidoN2 = (String) tablaArchivosTxt.getValueAt(filaSeleccion2, 2);
        if (archivoElegidoN2.equals("Carpeta")) {

            for (int i = 0; i < listaCarpetas.size(); i++) {

                if (listaCarpetas.get(i).getNombre().equals(archivoElegidoN)) {
                    subCarpetaElegida = archivoElegidoN;

                }
            }
            tablaArchivosTxt.clearSelection();
        }
        if (archivoElegidoN2.equals(".txt")) {
            cargarBarra();
            for (int i = 0; i < listaArchivos.size(); i++) {
                archivosTxt archivoSelec = listaArchivos.get(i);
                if (archivoSelec.getNombreA().equals(archivoElegidoN)) {
                    exploradorArchivos.setVisible(false);
                    crearTexto.pack();
                    crearTexto.setLocationRelativeTo(this);
                    crearTexto.setVisible(true);
                    File archivo = archivoSelec.getArchivo();

                    try (Scanner lector = new Scanner(archivo)) {
                        ejemploFuente1.setText("");

                        while (lector.hasNextLine()) {
                            ejemploFuente1.append(lector.nextLine() + "\n");
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "Ha ocurrido un error, asegurese de seleccionar el archivo correcto.");

                    }

                }
            }
            JOptionPane.showMessageDialog(null, "Archivo abierto.");
        }


    }//GEN-LAST:event_AbrirArchivosActionPerformed
    public void eliminarCarpetaEscondida(ArrayList<carpetasArchivos> listaCarpetas, String nombre) {
        for (int i = 0; i < listaCarpetas.size(); i++) {
            carpetasArchivos carpetaEleg = listaCarpetas.get(i);

            ArrayList<carpetasArchivos> revisarCarpetas = carpetaEleg.regresarCarpeta();
            for (int j = 0; j < revisarCarpetas.size(); j++) {

                if (revisarCarpetas.get(j).getNombre().equals(nombre)) {

                    carpetasArchivos carpetaSeleccionada = revisarCarpetas.get(j);
                    File temp = carpetaSeleccionada.ubicacionCarpeta;
                    Path rutaCarp = temp.toPath();

                    if (Files.exists(rutaCarp)) {
                        try {
                            Files.walk(rutaCarp)
                                    .sorted(Comparator.reverseOrder())
                                    .map(Path::toFile)
                                    .forEach(File::delete);

                        } catch (Exception E) {
                            JOptionPane.showMessageDialog(null, "Error, por alguna razon no se pudo borrar");
                        }

                    }

                    revisarCarpetas.remove(j);

                }
            }
        }
    }

    public void setProgreso(int porcentaje) {
        barraProgreso.setValue(porcentaje);
    }

    public void eliminarArchivos() {
        filaSeleccion2 = tablaArchivosTxt.getSelectedRow();
        if (filaSeleccion2 == -1) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un archivo de la lista");
            return;
        }
        String archivoElegidoN = (String) tablaArchivosTxt.getValueAt(filaSeleccion2, 0);
        String archivoElegidoN2 = (String) tablaArchivosTxt.getValueAt(filaSeleccion2, 2);
        int sentencia = 0;
        boolean verf = false;
        if (archivoElegidoN2.equals("Carpeta")) {
            try {

                for (int i = 0; i < listaCarpetas.size(); i++) {
                    carpetasArchivos carpetaEleg = listaCarpetas.get(i);

                    eliminarCarpetaEscondida(listaCarpetas, archivoElegidoN);
                    if (listaCarpetas.get(i).getNombre().equals(archivoElegidoN)) {
                        verf = true;
                        sentencia = i;

                        carpetasArchivos carpetaSeleccionada = listaCarpetas.get(i);
                        File temp = carpetaSeleccionada.ubicacionCarpeta;
                        Path rutaCarp = temp.toPath();

                        if (Files.exists(rutaCarp)) {
                            try {
                                Files.walk(rutaCarp)
                                        .sorted(Comparator.reverseOrder())
                                        .map(Path::toFile)
                                        .forEach(File::delete);

                            } catch (Exception E) {
                                JOptionPane.showMessageDialog(null, "Error, por alguna razon no se pudo borrar");
                            }

                        }

                    }

                }
                if (verf == true) {
                    listaCarpetas.remove(listaCarpetas.get(sentencia));
                }

                JOptionPane.showMessageDialog(null, "Carpeta exitosamente eliminada.");
                tablaArchivosTxt.clearSelection();
            } catch (Exception E) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar una carpeta");
                return;
            }
        }
        if (archivoElegidoN2.equals(".txt")) {
            for (int i = 0; i < listaArchivos.size(); i++) {
                archivosTxt archivoSelec = listaArchivos.get(i);
                if (archivoSelec.getNombreA().equals(archivoElegidoN)) {
                    for (int t = 0; t < listaCarpetas.size(); t++) {
                        carpetasArchivos carpetaEleg = listaCarpetas.get(t);
                        ArrayList<archivosTxt> archivos = carpetaEleg.regresarArchivos();
                        for (int j = 0; j < archivos.size(); j++) {
                            if (archivos.get(j).getNombreA().equals(archivoSelec.getNombreA())) {
                                carpetaEleg.eliminarArchivo(j);
                            }
                        }
                    }
                    sentencia = i;
                    File archivo = archivoSelec.getArchivo();
                    try {
                        archivo.delete();

                    } catch (Exception e) {

                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Archivo exitosamente eliminado");

            listaArchivos.remove(listaArchivos.get(sentencia));
            tablaArchivosTxt.clearSelection();
            filaSeleccion2 = tablaArchivosTxt.getSelectedRow();
        }

    }
    private void EliminarArchivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarArchivosActionPerformed
        cargarBarra();
        eliminarArchivos();

    }//GEN-LAST:event_EliminarArchivosActionPerformed

    private void BorrarBotonArchivosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BorrarBotonArchivosMouseClicked
        cargarBarra();
        eliminarArchivos();
    }//GEN-LAST:event_BorrarBotonArchivosMouseClicked

    private void BorrarBotonArchivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BorrarBotonArchivosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BorrarBotonArchivosActionPerformed

    private void jButton9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseClicked
        panelColoresElementos.setVisible(false);
    }//GEN-LAST:event_jButton9MouseClicked

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void calculadoraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_calculadoraMouseClicked
        Calculadora.pack();
        Calculadora.setLocationRelativeTo(this);
        Calculadora.setVisible(true);
    }//GEN-LAST:event_calculadoraMouseClicked

    private void calculadoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calculadoraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_calculadoraActionPerformed

    private void TicTacToeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TicTacToeMouseClicked
        XO.pack();
        XO.setLocationRelativeTo(this);
        XO.setVisible(true);

    }//GEN-LAST:event_TicTacToeMouseClicked

    private void TicTacToeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TicTacToeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TicTacToeActionPerformed

    private void unoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_unoMouseClicked
        pantalla.append("1");
    }//GEN-LAST:event_unoMouseClicked

    private void dosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dosMouseClicked
        pantalla.append("2");
    }//GEN-LAST:event_dosMouseClicked

    private void tresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tresMouseClicked
        pantalla.append("3");
    }//GEN-LAST:event_tresMouseClicked

    private void cuatroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cuatroMouseClicked
        pantalla.append("4");
    }//GEN-LAST:event_cuatroMouseClicked

    private void cincoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cincoMouseClicked
        pantalla.append("5");
    }//GEN-LAST:event_cincoMouseClicked

    private void seisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seisMouseClicked
        pantalla.append("6");
    }//GEN-LAST:event_seisMouseClicked

    private void sieteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sieteMouseClicked
        pantalla.append("7");
    }//GEN-LAST:event_sieteMouseClicked

    private void ochoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ochoMouseClicked
        pantalla.append("8");
    }//GEN-LAST:event_ochoMouseClicked

    private void nueveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nueveMouseClicked
        pantalla.append("9");
    }//GEN-LAST:event_nueveMouseClicked

    private void ceroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ceroMouseClicked
        pantalla.append("0");
    }//GEN-LAST:event_ceroMouseClicked

    private void limpiarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_limpiarMouseClicked
        pantalla.setText(null);
    }//GEN-LAST:event_limpiarMouseClicked

    private void dividirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dividirMouseClicked
        primerNumero = Float.parseFloat(pantalla.getText());
        operador = "÷";
        pantalla.setText("");

    }//GEN-LAST:event_dividirMouseClicked
    float primerNumero;
    float segundoNumero;
    String operador;
    private void menosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menosMouseClicked
        primerNumero = Float.parseFloat(pantalla.getText());
        operador = "-";
        pantalla.setText("");
    }//GEN-LAST:event_menosMouseClicked

    private void resultadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resultadoMouseClicked
        segundoNumero = 0;
        try {
            segundoNumero = Float.parseFloat(pantalla.getText());
        } catch (Exception E) {

        }

        switch (operador) {
            case "+":
                pantalla.setText(noCero(primerNumero + segundoNumero));

                break;
            case "-":
                pantalla.setText(noCero(primerNumero - segundoNumero));
                break;
            case "x":
                pantalla.setText(noCero(primerNumero * segundoNumero));
                break;
            case "÷":
                if (segundoNumero == 0) {
                    pantalla.setText("Syntax Error");
                } else {
                    pantalla.setText(noCero(primerNumero / segundoNumero));
                }

                break;
            default:

        }

    }//GEN-LAST:event_resultadoMouseClicked

    private void multiplicar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_multiplicar1MouseClicked
        primerNumero = Float.parseFloat(pantalla.getText());
        operador = "x";
        pantalla.setText("");
    }//GEN-LAST:event_multiplicar1MouseClicked

    private void masMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_masMouseClicked
        primerNumero = Float.parseFloat(pantalla.getText());
        operador = "+";
        pantalla.setText("");
    }//GEN-LAST:event_masMouseClicked

    private void ceroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ceroActionPerformed

    }//GEN-LAST:event_ceroActionPerformed

    private void puntoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_puntoMouseClicked
        if (!pantalla.getText().contains(".")) {
            pantalla.append(".");
        }
    }//GEN-LAST:event_puntoMouseClicked
    boolean estado = true;
    Icon turno = new ImageIcon(getClass().getResource("/Imagenes/LogoTipos/X.png"));
    Icon siguienteTurno = new ImageIcon(getClass().getResource("/Imagenes/LogoTipos/O.png"));
    String turnoTexto = "X";

    String siguienteJuego = "O";
    JLabel lbs[] = new JLabel[9];

    public void XO() {
        pos1.setIcon(defecto);
        pos2.setIcon(defecto);
        pos3.setIcon(defecto);
        pos4.setIcon(defecto);
        pos5.setIcon(defecto);
        pos6.setIcon(defecto);
        pos7.setIcon(defecto);
        pos8.setIcon(defecto);
        pos9.setIcon(defecto);

        lbs[0] = pos1;
        lbs[1] = pos2;
        lbs[2] = pos3;
        lbs[3] = pos4;
        lbs[4] = pos5;
        lbs[5] = pos6;
        lbs[6] = pos7;
        lbs[7] = pos8;
        lbs[8] = pos9;
    }
    Icon defecto = new ImageIcon(getClass().getResource("/Imagenes/LogoTipos/Default.png"));

    public void presionar(int casilla) {
        if (((ImageIcon) lbs[casilla - 1].getIcon()).getImage().equals(((ImageIcon) defecto).getImage()) && estado == true) {
            lbs[casilla - 1].setIcon(turno);
            cambiarTurno();
            comprobarGanador();
        }

    }
    int VS[][] = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9},
        {1, 4, 7},
        {2, 5, 8},
        {3, 6, 9},
        {1, 5, 9},
        {3, 5, 7}};

    public void comprobarGanador() {
        for (int i = 0; i < VS.length; i++) {
            if (((ImageIcon) lbs[VS[i][0] - 1].getIcon()).getImage().equals(((ImageIcon) iconoX).getImage())
                    && ((ImageIcon) lbs[VS[i][1] - 1].getIcon()).getImage().equals(((ImageIcon) iconoX).getImage())
                    && ((ImageIcon) lbs[VS[i][2] - 1].getIcon()).getImage().equals(((ImageIcon) iconoX).getImage())) {
                lbs[VS[i][0] - 1].setBackground(new Color(101, 247, 101));
                lbs[VS[i][1] - 1].setBackground(new Color(101, 247, 101));
                lbs[VS[i][2] - 1].setBackground(new Color(101, 247, 101));
                puntajeX.setText(Integer.toString(Integer.parseInt(puntajeX.getText()) + 1));
                turnoMostrar.setText("Ha ganado X");
                willyCelebra.setVisible(true);
                estado = false;
            }
            if (((ImageIcon) lbs[VS[i][0] - 1].getIcon()).getImage().equals(((ImageIcon) iconoO).getImage())
                    && ((ImageIcon) lbs[VS[i][1] - 1].getIcon()).getImage().equals(((ImageIcon) iconoO).getImage())
                    && ((ImageIcon) lbs[VS[i][2] - 1].getIcon()).getImage().equals(((ImageIcon) iconoO).getImage())) {

                lbs[VS[i][0] - 1].setBackground(new Color(101, 247, 101));
                lbs[VS[i][1] - 1].setBackground(new Color(101, 247, 101));
                lbs[VS[i][2] - 1].setBackground(new Color(101, 247, 101));
                puntajeO.setText(Integer.toString(Integer.parseInt(puntajeX.getText()) + 1));
                turnoMostrar.setText("Ha ganado O");
                willyCelebra.setVisible(true);
                estado = false;
            }

        }
    }
    Icon iconoO = new ImageIcon(getClass().getResource("/Imagenes/LogoTipos/O.png"));
    Icon iconoX = new ImageIcon(getClass().getResource("/Imagenes/LogoTipos/X.png"));

    public void cambiarTurno() {
        if (((ImageIcon) turno).getImage().equals(((ImageIcon) iconoX).getImage())) {
            turno = new ImageIcon(getClass().getResource("/Imagenes/LogoTipos/O.png"));
            turnoTexto = "O";
        } else {
            turno = new ImageIcon(getClass().getResource("/Imagenes/LogoTipos/X.png"));
            turnoTexto = "X";
        }
        turnoMostrar.setText("Turno de: " + turnoTexto);
    }
    private void pos3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pos3MousePressed
        presionar(3);
    }//GEN-LAST:event_pos3MousePressed

    private void pos1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pos1MousePressed
        presionar(1);
    }//GEN-LAST:event_pos1MousePressed

    private void pos2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pos2MousePressed
        presionar(2);
    }//GEN-LAST:event_pos2MousePressed

    private void pos4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pos4MousePressed
        presionar(4);
    }//GEN-LAST:event_pos4MousePressed

    private void pos5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pos5MousePressed
        presionar(5);
    }//GEN-LAST:event_pos5MousePressed

    private void pos6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pos6MousePressed
        presionar(6);
    }//GEN-LAST:event_pos6MousePressed

    private void pos7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pos7MousePressed
        presionar(7);
    }//GEN-LAST:event_pos7MousePressed

    private void pos8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pos8MousePressed
        presionar(8);
    }//GEN-LAST:event_pos8MousePressed

    private void pos9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pos9MousePressed
        presionar(9);
    }//GEN-LAST:event_pos9MousePressed

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        willyCelebra.setVisible(false);
        for (int i = 0; i < lbs.length; i++) {
            lbs[i].setIcon(defecto);
            lbs[i].setBackground(Color.WHITE);
        }
        turno = siguienteTurno;
        turnoTexto = siguienteJuego;
        if (siguienteTurno == iconoO) {
            siguienteJuego = "X";
            siguienteTurno = iconoX;
        } else {
            siguienteTurno = iconoO;
            siguienteJuego = "O";
        }
        turnoMostrar.setText("Turno de: " + turnoTexto);
        estado = true;
    }//GEN-LAST:event_jButton4MouseClicked

    private void elegirFondomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_elegirFondomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_elegirFondomActionPerformed

    private void wDefectoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wDefectoMouseClicked
        nevadaElegido = false;
        tardeElegido = false;
        rachoElegido = false;
        lagoElegido = false;
        porDefecto = true;
        porDefecto2 = false;
        porDefecto3 = false;
    }//GEN-LAST:event_wDefectoMouseClicked

    private void lagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lagoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lagoActionPerformed

    private void CrearCarpetaWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_CrearCarpetaWindowClosed
        tablaExplorador.clearSelection();
    }//GEN-LAST:event_CrearCarpetaWindowClosed

    private void rutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rutaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rutaActionPerformed

    private void exploradorPc2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exploradorPc2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_exploradorPc2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void AñadirBoton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AñadirBoton1MouseClicked
        mostrarCarpeta.pack();
        mostrarCarpeta.setLocationRelativeTo(this);
        mostrarCarpeta.setVisible(true);

    }//GEN-LAST:event_AñadirBoton1MouseClicked

    private void AñadirBoton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AñadirBoton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AñadirBoton1ActionPerformed

    private void jButton7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseClicked
        if (nombreCarpeta1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe asignarle un nombre a la carpeta.");
            return;
        }

        String nombreC = "";
        try {
//            nombreC = (String) tablaExplorador.getValueAt(filaSeleccion, 0);
            nombreC = carpetaElegida;
        } catch (Exception E) {

        }

        String rutaC = "";
        carpetasArchivos carpetaTemp = new carpetasArchivos();
        for (int i = 0; i < listaCarpetas.size(); i++) {
            if (listaCarpetas.get(i).getNombre().equals(nombreC)) {
                rutaC = listaCarpetas.get(i).ruta;
                carpetaTemp = listaCarpetas.get(i);
            }
        }
        File carpeta = new File(rutaC, nombreCarpeta1.getText());
        carpeta.mkdirs();

        String ruta = carpeta.getAbsolutePath();
        String nombreCa = carpeta.getName();
        long tamañoBytesCa = carpeta.length();
        double tamañoKbC = tamañoBytesCa / 1024.0;
        long fechaMilis = carpeta.lastModified();
        Date fechaCa = new Date(fechaMilis);
        SimpleDateFormat formatoDiaC = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String tipoCa = "Carpeta";
        String diaCa = formatoDiaC.format(fechaCa);
        carpetasArchivos carpetaNueva = new carpetasArchivos(nombreCa, carpeta, diaCa, tipoCa, tamañoBytesCa, ruta);
        carpetaTemp.añadirCarpeta(carpetaNueva);

        cargarBarra();
        JOptionPane.showMessageDialog(null, "Carpeta exitosamente creada!");
        nombreCarpeta1.setText(null);
        tablaArchivosTxt.clearSelection();

        mostrarCarpeta.dispose();

    }//GEN-LAST:event_jButton7MouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
//        if (nombreCarpeta1.getText().isEmpty()) {
//            JOptionPane.showMessageDialog(null, "Debe asignarle un nombre a la carpeta.");
//            return;
//        }
//
//        seleccionarRuta.setVisible(false);
//        ubicacionRutan.setVisible(false);
//
//        String nombreC = "";
//        try {
//            nombreC =carpetaElegida;
//        } catch (Exception E) {
//
//        }
//
//        String rutaC = "";
//        carpetasArchivos carpetaTemp = new carpetasArchivos();
//        for (int i = 0; i < listaCarpetas.size(); i++) {
//            if (listaCarpetas.get(i).getNombre().equals(nombreC)) {
//                rutaC = listaCarpetas.get(i).ruta;
//                carpetaTemp = listaCarpetas.get(i);
//            }
//        }
//        File carpeta = new File(rutaC, nombreCarpeta1.getText());
//        carpeta.mkdirs();
//        String ruta = carpeta.getAbsolutePath();
//        String nombreCa = carpeta.getName();
//        long tamañoBytesCa = carpeta.length();
//        double tamañoKbC = tamañoBytesCa / 1024.0;
//        long fechaMilis = carpeta.lastModified();
//        Date fechaCa = new Date(fechaMilis);
//        SimpleDateFormat formatoDiaC = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//        String tipoCa = "Carpeta";
//        String diaCa = formatoDiaC.format(fechaCa);
//        carpetasArchivos carpetaNueva = new carpetasArchivos(nombreCa, carpeta, diaCa, tipoCa, tamañoBytesCa, ruta);
//        carpetaTemp.añadirCarpeta(carpetaNueva);
//
//        JOptionPane.showMessageDialog(null, "Carpeta exitosamente creada!");
//        nombreCarpeta.setText(null);
//        tablaExplorador.clearSelection();
//        CrearCarpeta.dispose();
//        seleccionarRuta.setVisible(true);
//        ubicacionRutan.setVisible(true);
    }//GEN-LAST:event_jButton7ActionPerformed
    Timer timerAnimacion;

    public void setProgresoAnimado(int valorDestino, Runnable alFinalizar) {
        // Si ya hay una animación en curso, la detenemos
        if (timerAnimacion != null && timerAnimacion.isRunning()) {
            timerAnimacion.stop();
        }

        // Crear un timer que actualiza la barra cada 15 milisegundos
        timerAnimacion = new javax.swing.Timer(15, e -> {
            int valorActual = barraProgreso.getValue();

            if (valorActual < valorDestino) {
                barraProgreso.setValue(valorActual + 1); // Incrementa suavemente
            } else if (valorActual > valorDestino) {
                barraProgreso.setValue(valorActual - 1);
            } else {
                timerAnimacion.stop();
                if (valorDestino >= 100 && alFinalizar != null) {
                    alFinalizar.run();
                }

            }
        });

        timerAnimacion.start();
    }
    Random R = new Random();
    String mensajes[] = {"WitZig proviene del Aleman.", "Mi nombre es Wally",
        "Ve y busca Happy Studios :D", "Venus gira al reves",
        "Las ovejas pueden reconocer rostros", "Los koalas duermen 22 horas.",
        "La miel nunca caduca", "Rusia tiene mas superficie que pluton",
        "Las Jirafas no tienen cuerdas vocales",
        "Un rayo es mas caliente que el sol",
        "Tu nariz puede recordar 50,000 olores.", "Los flamingos nacen de color gris",
        "Los tiburones no tienen huesos.", "Las mariposas saborean con patas",
        "El pulpo tiene tres corazones",
        "Las bananas son bayas", "Los caballos no pueden vomitar",
        "Saturno flotaría en agua", "Las nutrias se dan la mano",
        "Las vacas tienen mejores amigas",
        "Urano huele a huevo podrido", "Los gatos no sienten dulce",
        "Los cerdos no miran arriba",
        "El sol es una estrella",
        "El agua hirviendo congela rápido",
        "La Luna se está alejando",
        "Un día en Mercurio dura años",
        "Las hormigas nunca duermen",
        "Los perros huelen el miedo",
        "Las huellas de koala parecen humanas",
        "El caracol duerme tres años", "La sangijuela tiene 32 cerebros",
        "El sudor de hipopótamo es rojo", "Los delfines duermen con un ojo",
        "Los patos tienen eco nulo", "El elefante no puede saltar",
        "Las estrellas fugaces son polvo", "El mar cubre casi todo",
        "La Tierra pesa casi nada", "El hielo es transparente",
        "Los búhos no mueven ojos", "Las cebras son negras con blanco",
        "El camello guarda grasa, no agua", "El bambú crece súper rápido",
        "Los mosquitos prefieren sangre O", "El aguacate es una baya",
        "Las ranas beben por la piel", "Los osos polares son negros",
        "El perezoso tarda en digerir", "Las medusas no tienen cerebro",
        "El magma bajo tierra es lava", "El diamante es puro carbono",
        "Los cocodrilos no envejecen bien"};
    ImageIcon[] arregloIconos = new ImageIcon[]{
        new ImageIcon(getClass().getResource("/Wally/W3.png")),
        new ImageIcon(getClass().getResource("/Wally/W4.png")),
        new ImageIcon(getClass().getResource("/Wally/W5.png")),
        new ImageIcon(getClass().getResource("/Wally/W6.png")),
        new ImageIcon(getClass().getResource("/Wally/W7.png")),
        new ImageIcon(getClass().getResource("/Wally/W8.png")),
        new ImageIcon(getClass().getResource("/Wally/W9.png")),
        new ImageIcon(getClass().getResource("/Wally/W10.png")),
        new ImageIcon(getClass().getResource("/Wally/W11.png")),};

    public void BienvenidaEntrar() {
//        Wally1.setIcon(arregloIconos[R.nextInt(0, 8)]);

    }

    public void cargarBarra() {
        barraProgreso.putClientProperty("JComponent.roundRect", true);
        barraProgreso.putClientProperty("JProgressBar.square", false);
        BarraProgreso.pack();

        BarraProgreso.setLocationRelativeTo(this);
        Wally.setIcon(arregloIconos[R.nextInt(0, 8)]);
        mensaje.setText(mensajes[R.nextInt(0, 52)]);
        barraProgreso.setValue(0);

        SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws Exception {
                for (int i = 0; i <= 100; i += 10) {
                    Thread.sleep(150);
                    publish(i);
                }
                return null;
            }

            @Override
            protected void process(java.util.List<Integer> chunks) {
                int valor = chunks.get(chunks.size() - 1);

                setProgresoAnimado(valor, null);
            }

            @Override
            protected void done() {
                setProgresoAnimado(100, () -> {
                    BarraProgreso.dispose();
                });
            }
        };

        worker.execute();
        BarraProgreso.setVisible(true);
    }
    private void WitZigMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_WitZigMouseClicked
        entrada.pack();
        entrada.setLocationRelativeTo(this);
        entrada.setVisible(true);
        BienvenidaEntrar();
    }//GEN-LAST:event_WitZigMouseClicked

    private void SnakeBotonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SnakeBotonMouseClicked
        Snake.pack();
        Snake.setLocationRelativeTo(this);
        Snake.setTitle("Snake (Controles: WASD)");
        Snake.setVisible(true);
        Snake.setFocusable(true);
        Snake.requestFocusInWindow();

    }//GEN-LAST:event_SnakeBotonMouseClicked

    private void SnakeBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SnakeBotonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SnakeBotonActionPerformed

    private void calculadora1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_calculadora1MouseClicked
        
        Paint.pack();
        Paint.setLocationRelativeTo(this);
        Paint.setTitle("Paint");
        Paint.setVisible(true);
    }//GEN-LAST:event_calculadora1MouseClicked

    private void calculadora1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calculadora1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_calculadora1ActionPerformed

    private void SnakeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SnakeKeyPressed
        // TODO add your handling code here:

        switch (evt.getKeyCode()) {

            case KeyEvent.VK_A:

                panelSnake.cambiarDireccion("iz");
                break;
            case KeyEvent.VK_D:

                panelSnake.cambiarDireccion("de");
                break;
            case KeyEvent.VK_W:

                panelSnake.cambiarDireccion("ar");
                break;
            case KeyEvent.VK_S:

                panelSnake.cambiarDireccion("ab");
                break;
            default:
                break;
        }
    }//GEN-LAST:event_SnakeKeyPressed

    private void RJuegoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RJuegoMouseClicked
        panelSnake.reiniciarJuego();
        Snake.setVisible(false);
        Snake.setVisible(true);
        Snake.setFocusable(true);
        Snake.requestFocusInWindow();
    }//GEN-LAST:event_RJuegoMouseClicked

    private void ApagarSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ApagarSActionPerformed
        System.exit(0);
    }//GEN-LAST:event_ApagarSActionPerformed

    private void panel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel1MouseDragged
        Pintado.GuardarPuntos(evt.getX(), evt.getY());
        Dibujar();

    }//GEN-LAST:event_panel1MouseDragged

    private void panel1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel1MouseMoved
        Dibujar();
    }//GEN-LAST:event_panel1MouseMoved

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        Pintado.borrarPantalla();
        panel1.repaint();
    }//GEN-LAST:event_jButton5MouseClicked
    int tamX = 10;
    int tamY = 10;
    private void cambiarTamañoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cambiarTamañoMouseClicked
        int tamaño = (int) tamañoPincel.getValue();
        tamX = tamaño;
        tamY = tamaño;
        panel1.repaint();

    }//GEN-LAST:event_cambiarTamañoMouseClicked

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseClicked
        Color colorEleccion = JColorChooser.showDialog(this, "Seleccione un color", Color.LIGHT_GRAY);
        g.setColor(colorEleccion);
        panel1.repaint();

    }//GEN-LAST:event_jButton8MouseClicked

    private void PaintWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_PaintWindowOpened
        g = panel1.getGraphics();
    }//GEN-LAST:event_PaintWindowOpened

    private void contraseñaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_contraseñaMouseEntered

    }//GEN-LAST:event_contraseñaMouseEntered

    private void contraseñaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_contraseñaMouseClicked
        contraseña.setText("");
        contraseña.setForeground(Color.BLACK);
    }//GEN-LAST:event_contraseñaMouseClicked

    private void usuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usuarioMouseClicked
        usuario.setText("");
        usuario.setForeground(Color.BLACK);
    }//GEN-LAST:event_usuarioMouseClicked
    private void configurarIconosJTree() {
        // Se asigna directamente el renderizador al jTree1 sobrescribiendo el método en línea
        arbolUsuarios.setCellRenderer(new DefaultTreeCellRenderer() {

            // 1. Cargas las imágenes una sola vez aquí
            ImageIcon iconoUsuarios = new ImageIcon(getClass().getResource("/Imagenes/LogoTipos/U1.png"));
            ImageIcon iconoAdmin = new ImageIcon(getClass().getResource("/Imagenes/LogoTipos/U3.png"));
            ImageIcon iconoInvitado = new ImageIcon(getClass().getResource("/Imagenes/LogoTipos/U2.png"));

            // 2. Sobrescribes el comportamiento de dibujado
            @Override
            public Component getTreeCellRendererComponent(JTree tree, Object value,
                    boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {

                super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

                DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) value;
                String categoria = nodo.getUserObject().toString();
                Object clase = nodo.getUserObject();

                if (categoria.equals("Usuarios")) {
                    setIcon(iconoUsuarios);
                } else if (categoria.equals("Administradores")) {
                    setIcon(iconoAdmin);
                } else if (categoria.equals("Invitados")) {
                    setIcon(iconoInvitado);
                } else if (clase instanceof Usuario) {
                    Usuario user = (Usuario) clase;
                    if (user.getNombreUsuario().equals("Admin")) {
                        setIcon(iconoAdmin);
                    }
                    if (user.getTipoUsuario().equals("Administrador")) {
                        setIcon(iconoAdmin);
                    } else if (user.getTipoUsuario().equals("Invitado")) {
                        setIcon(iconoInvitado);
                    }

                }

                return this;
            }
        });
    }

    Usuario usuarioAdminDef;
    String usuarioSesion;
    int contador4 = 0;
    private void jButton10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton10MouseClicked
        if (usuario.getText().isEmpty() || contraseña.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No puede dejar nada en blanco.");
            return;
        }
        cargarBarra();
        if (usuario.getText().equals("Admin") && contraseña.getText().equals("Admin") && contador4 == 0) {

            contador4++;

            if (contador4 == 1) {
                usuarioAdminDef = retornarInicio();
            }

            fondoIniciar.setVisible(false);
            BarraNavegacion.setVisible(true);
            BarraTareas.setVisible(true);
            FondoPantalla.setVisible(true);

            boolean verf = false;
            for (Usuario User : Usuarios) {
                if (User.getNombreUsuario().equals("Admin")) {
                    verf = true;
                }
            }
            if (verf == false) {

                Usuario usuarioAdmin = retornarInicio();
                usuarioSesion = usuarioAdmin.getNombreUsuario();
                Usuarios.add(usuarioAdmin);

            } else {
                usuarioSesion = "Admin";
            }

//            realizarCambio(usuarioAdmin);
//
//            return;
        }
        String nombreU = usuario.getText();
        String contraU = contraseña.getText();
        for (int i = 0; i < Usuarios.size(); i++) {

            Usuario usuarioEle = Usuarios.get(i);

            if (nombreU.equals(usuarioEle.getNombreUsuario()) && usuarioEle.getContraseñaUsuario().equals(contraU)) {

                bienvenida.setText("Bienvenido, estimado(a) " + nombreU);
                user.setText(nombreU);
                if (usuarioEle.isAdministrador() == true) {
                    rolTipo.setText("Designacion: Admin");
                } else {
                    rolTipo.setText("Designacion: Invitado");
                }
                fondoIniciar.setVisible(false);
                BarraNavegacion.setVisible(true);
                BarraTareas.setVisible(true);
                FondoPantalla.setVisible(true);
                String sesionAnterior = usuarioSesion;
                usuarioSesion = usuarioEle.getNombreUsuario();
                String ruta = usuarioEle.getRutaArchivo();

                if (ruta == null) {
                    realizarCambio(usuarioAdminDef);
                    if (sesionAnterior.equals("Admin")) {
                        Usuario cambioUser = usuarioAdminDef;
                        Usuarios.set(i, cambioUser);
                        realizarCambio(cambioUser);
                    } else {
                        Usuario cambioUser = guardarUsuario(usuarioEle);
                        Usuarios.set(i, cambioUser);
                        realizarCambio(cambioUser);
                    }
                    Usuario cambioUser = guardarUsuario(usuarioEle);
                    Usuarios.set(i, cambioUser);
                    realizarCambio(cambioUser);

                } else {
                    cargarDatos(ruta);

                }
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.");
    }//GEN-LAST:event_jButton10MouseClicked
    ArrayList<Usuario> Usuarios = new ArrayList<>();

    private void jButton11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton11MouseClicked
        if (nombreUs.getText().isEmpty() || contraUs.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No puede dejar nada en blanco");
            return;
        }
        String nombre = nombreUs.getText();
        for (int i = 0; i < Usuarios.size(); i++) {
            if (nombre.equals(Usuarios.get(i).getNombreUsuario())) {
                JOptionPane.showMessageDialog(this, "Ese nombre de usuario ya existe, utilice otro.");
                return;
            }
        }
        String contra = contraUs.getText();
        String tipoU = (String) tipo.getSelectedItem();
        Usuario nuevoU = new Usuario(nombre, contra, tipoU);
//        nuevoU=formarInicio(usuarioAdminDef);
        Usuarios.add(nuevoU);

        cargarBarra();
        JOptionPane.showMessageDialog(this, "Usuario exitosamente creado.");
        nombreUs.setText("");
        contraUs.setText("");
        int tipo1 = tipo.getSelectedIndex();
        cargarContenidoArbol();
        DefaultTreeModel modeloArbol = (DefaultTreeModel) arbolUsuarios.getModel();
        modeloArbol.reload();


    }//GEN-LAST:event_jButton11MouseClicked
    public void cargarContenidoArbol() {
        DefaultMutableTreeNode Usuarios2 = new DefaultMutableTreeNode("Usuarios");
        DefaultMutableTreeNode Invitados = new DefaultMutableTreeNode("Invitados");
        DefaultMutableTreeNode Administradores = new DefaultMutableTreeNode("Administradores");

        Usuarios2.add(Invitados);
        Usuarios2.add(Administradores);
        DefaultTreeModel modeloArbol = new DefaultTreeModel(Usuarios2);
        arbolUsuarios.setModel(modeloArbol);
        DefaultMutableTreeNode raiz = (DefaultMutableTreeNode) modeloArbol.getRoot();

        for (Usuario user : Usuarios) {
            if (user.tipoUsuario.equals("Administrador") || user.isAdministrador() == true) {
                DefaultMutableTreeNode nodoElegido = (DefaultMutableTreeNode) raiz.getChildAt(1);
                DefaultMutableTreeNode nodoNuevo = new DefaultMutableTreeNode(user);
                nodoElegido.add(nodoNuevo);
            } else if (user.tipoUsuario.equals("Invitado") || user.isAdministrador() == false) {
                DefaultMutableTreeNode nodoElegido = (DefaultMutableTreeNode) raiz.getChildAt(0);
                DefaultMutableTreeNode nodoNuevo = new DefaultMutableTreeNode(user);
                nodoElegido.add(nodoNuevo);
            }
        }

    }

    public void guardarContenido() {

        if (usuarioSesion == null) {
            return;
        }

        for (int i = 0; i < Usuarios.size(); i++) {

            if (Usuarios.get(i).getNombreUsuario().equals(usuarioSesion)) {

                Usuario actualizado = guardarUsuario(Usuarios.get(i));

                Usuarios.set(i, actualizado);
                if (fondoImagen.isVisible() == false) {
                    actualizado.cambiarVisible(false);
                } else if (fondoImagen.isVisible() == true) {
                    actualizado.cambiarVisible(true);
                }

                return;
            }
        }
    }
    private void LogOutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogOutMouseClicked
        cargarBarra();
        guardarContenido();
        fondoIniciar.setVisible(true);
        BarraNavegacion.setVisible(false);
        BarraTareas.setVisible(false);
        FondoPantalla.setVisible(false);
        fondoIniciar.requestFocusInWindow();
    }//GEN-LAST:event_LogOutMouseClicked

    private void LogInMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogInMouseClicked
        cargarBarra();
        guardarContenido();
        fondoIniciar.setVisible(true);
        BarraNavegacion.setVisible(false);
        BarraTareas.setVisible(false);
        FondoPantalla.setVisible(false);
        fondoIniciar.requestFocusInWindow();
    }//GEN-LAST:event_LogInMouseClicked

    private void crearUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_crearUsuariosMouseClicked
        cargarContenidoArbol();
        AdminCuentas.pack();
        AdminCuentas.setLocationRelativeTo(this);
        AdminCuentas.setVisible(true);

    }//GEN-LAST:event_crearUsuariosMouseClicked

    private void verUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_verUsuariosMouseClicked
        Informacion.pack();
        Informacion.setLocationRelativeTo(this);
        Informacion.setVisible(true);
    }//GEN-LAST:event_verUsuariosMouseClicked
    public void mostrarInfoTabla() {
        DefaultTableModel modeloTabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modeloTabla.addColumn("  Usuarios");
        modeloTabla.addColumn("  Tipo");
        for (Usuario user : Usuarios) {
            String[] datos = {user.getNombreUsuario(), user.getTipoUsuario()};
            modeloTabla.addRow(datos);
        }

        tablaUsuarios.setModel(modeloTabla);
        tablaUsuarios.getColumnModel().getColumn(0).setCellRenderer((t, v, s, f, r, c) -> {
            JLabel l = new JLabel(String.valueOf(v));
            String dato = (String) tablaUsuarios.getValueAt(r, 1);
            ImageIcon admin = new ImageIcon(getClass().getResource("/Imagenes/LogoTipos/U5.png"));
            ImageIcon Inv = new ImageIcon(getClass().getResource("/Imagenes/LogoTipos/U4.png"));
            if (dato.equals("Administrador")) {
                l.setIcon(admin);
            }
            if (dato.equals("Invitado")) {
                l.setIcon(Inv);
            }

            return l;
        });
    }
    private void InformacionWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_InformacionWindowActivated
        mostrarInfoTabla();

    }//GEN-LAST:event_InformacionWindowActivated

    private void tablaUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaUsuariosMouseClicked
        int boton = evt.getButton();
        if (admin == true) {
            if (boton == 3) {
                int X = evt.getX();
                int Y = evt.getY();
                ModificarUsuarios.show(tablaUsuarios, X, Y);
            }
        }
    }//GEN-LAST:event_tablaUsuariosMouseClicked

    private void elimUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_elimUActionPerformed
        int Row = tablaUsuarios.getSelectedRow();
        if (Row != -1) {
            String Nombre = (String) tablaUsuarios.getValueAt(Row, 0);
            for (int i = 0; i < Usuarios.size(); i++) {
                Usuario user = Usuarios.get(i);

                if (Nombre.equals(usuarioSesion) && !Nombre.equals("Admin")) {
                    guardarContenido();
                    fondoIniciar.setVisible(true);
                    BarraNavegacion.setVisible(false);
                    BarraTareas.setVisible(false);
                    FondoPantalla.setVisible(false);
                    Informacion.dispose();

                }
                if (Nombre.equals("Admin")) {
                    JOptionPane.showMessageDialog(this, "El usuario Admin esta incluido por defecto, no se puede borrar");
                    return;
                }
                if (user.getNombreUsuario().equals(Nombre)) {
                    String archivoBin = user.getRutaArchivo();
                    if (archivoBin != null) {
                        File archivoBinario = new File(archivoBin);
                        if (archivoBinario.exists()) {
                            archivoBinario.delete();
                        }
                    }
//                    File archivoBinario = new File(archivoBin);
//                    if (archivoBinario.exists()) {
//                        archivoBinario.delete();
//                    }

                    Usuarios.remove(i);
                    cargarBarra();
                    JOptionPane.showMessageDialog(this, "Usuario exitosamente Eliminado");
                    tablaUsuarios.repaint();
                    arbolUsuarios.repaint();
                    mostrarInfoTabla();

                }
            }

        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un usuario");
        }
    }//GEN-LAST:event_elimUActionPerformed

    private void jButton12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton12MouseClicked
        int Row = tablaUsuarios.getSelectedRow();
        if (Row != -1) {
            String Nombre = (String) tablaUsuarios.getValueAt(Row, 0);
            for (int i = 0; i < Usuarios.size(); i++) {
                Usuario user = Usuarios.get(i);
                if (Nombre.equals(usuarioSesion) && !Nombre.equals("Admin")) {
                    usuarioSesion = Nombre;

                }
                if (Nombre.equals("Admin")) {
                    JOptionPane.showMessageDialog(this, "El usuario Admin no se puede modificar.");
                    return;
                }
                if (user.getNombreUsuario().equals(Nombre)) {
                    String nuevoNombre = nuevoU.getText();
                    String nuevaContra = ncontraU.getText();
                    for (Usuario Usuario1 : Usuarios) {
                        if (Usuario1.getNombreUsuario().equals(nuevoNombre)) {
                            JOptionPane.showMessageDialog(this, "Ya existe un usuario con este nombre.");
                            return;
                        }
                    }
                    File archivoAntiguo = new File("config_" + user.getNombreUsuario() + ".dat");
                    if (archivoAntiguo.exists()) {
                        File archivoNuevo = new File("config_" + nuevoNombre + ".dat");

                        archivoAntiguo.renameTo(archivoNuevo);

                        user.setNombreUsuario(nuevoNombre);
                        user.setContraseñaUsuario(nuevaContra);
                        String ruta = "config_" + nuevoNombre + ".dat";
                        user.setRutaArchivo(ruta);
                        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ruta))) {

                            salida.writeObject(user);

                        } catch (IOException e) {

                        }

                    } else {
                        user.setNombreUsuario(nuevoNombre);
                        user.setContraseñaUsuario(nuevaContra);
                    }

                    cargarBarra();
                    mostrarInfoTabla();
                    JOptionPane.showMessageDialog(this, "Usuario exitosamente Modificado");
                    ModificarUsuario.dispose();
                    tablaUsuarios.repaint();
                    arbolUsuarios.repaint();

                }
            }

        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un usuario");
        }
    }//GEN-LAST:event_jButton12MouseClicked

    private void modUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modUActionPerformed
        ModificarUsuario.pack();
        ModificarUsuario.setLocationRelativeTo(this);
        ModificarUsuario.setVisible(true);
    }//GEN-LAST:event_modUActionPerformed
    boolean imagenPers = false;
    Icon imagenFondoPersonaliz;
    private void jButton13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton13MouseClicked
        exploradorPc.setVisible(true);

        if (exploradorPc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File archivo = exploradorPc.getSelectedFile();
            try {
                Image imagen = ImageIO.read(archivo);
                if (imagen == null) {
                    JOptionPane.showMessageDialog(this, "Debe seleccionar una imagen.");
                    return;
                }
            } catch (IOException ex) {

            }

            Icon nuevoFondo = new ImageIcon(archivo.getAbsolutePath());
            Image imagenOriginal = ((ImageIcon) nuevoFondo).getImage();

            Image imagenEscalada = imagenOriginal.getScaledInstance(1386, 779, Image.SCALE_SMOOTH);

            Icon iconoF = new ImageIcon(imagenEscalada);
            imagenFondoPersonaliz = iconoF;
            imagenPers = true;
            nevadaElegido = false;
            tardeElegido = false;
            rachoElegido = false;
            lagoElegido = false;
            porDefecto = false;
            porDefecto2 = false;
            porDefecto3 = false;
            cargarBarra();
            JOptionPane.showMessageDialog(this, "Imagen exitosamente cargada.");

        } else {
            imagenPers = false;
        }
    }//GEN-LAST:event_jButton13MouseClicked

    private void jButton14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton14MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jButton14MouseClicked

    private void wDefecto1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wDefecto1MouseClicked
        nevadaElegido = false;
        tardeElegido = false;
        rachoElegido = false;
        lagoElegido = false;
        porDefecto = false;
        porDefecto2 = false;
        porDefecto3 = true;
    }//GEN-LAST:event_wDefecto1MouseClicked
    boolean porDefecto2 = false;
    boolean porDefecto3 = false;
    private void wDefecto2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wDefecto2MouseClicked
        nevadaElegido = false;
        tardeElegido = false;
        rachoElegido = false;
        lagoElegido = false;
        porDefecto = false;
        porDefecto2 = true;
        porDefecto3 = false;
    }//GEN-LAST:event_wDefecto2MouseClicked
    public Usuario retornarInicio() {
        String nombre = "Admin";
        String contra = "Admin";
        String tipoU = "Administrador";

        Color barraNC = BarraNavegacion.getBackground();
        Color barraC = BarraTareas.getBackground();
        Color fondoC = FondoPantalla.getBackground();
        Icon fondoI = fondoImagen.getIcon();
        Font fontG = ejemploFuente.getFont();
        Color colorF = ejemploFuente.getForeground();
        Color FondoOP = fondoOpc.getBackground();
        Color fondoGe = fondoGen.getBackground();
        Color opcionesEd = opcionesEditor.getBackground();
        ArrayList<archivosTxt> listaA = new ArrayList<>(listaArchivos);
        ArrayList<carpetasArchivos> listaC = new ArrayList<>(listaCarpetas);
        String archivo1 = "config_" + nombre + ".dat";
        Usuario nuevoUsuario = new Usuario(FondoOP, fondoGe, opcionesEd, true, nombre, contra, tipoU, fondoC, barraC, barraNC, fontG, fondoI, listaA, listaC, archivo1, colorF);

        realizarCambio(nuevoUsuario);
        return nuevoUsuario;
    }

    public Usuario guardarUsuario(Usuario usuario) {
        String nombre = usuario.getNombreUsuario();
        String contra = usuario.getContraseñaUsuario();
        String tipoU = usuario.getTipoUsuario();

        Color barraNC = BarraNavegacion.getBackground();
        Color barraC = BarraTareas.getBackground();
        Color fondoC = FondoPantalla.getBackground();
        Icon fondoI = fondoImagen.getIcon();

        Font fontG = ejemploFuente.getFont();
        Color colorF = ejemploFuente.getForeground();
        Color FondoOP = fondoOpc.getBackground();
        Color fondoGe = fondoGen.getBackground();
        Color opcionesEd = opcionesEditor.getBackground();

        ArrayList<archivosTxt> listaA = new ArrayList<>(listaArchivos);
        ArrayList<carpetasArchivos> listaC = new ArrayList<>(listaCarpetas);
        String archivo1 = "config_" + nombre + ".dat";
        boolean visibilidad = false;
        if (fondoImagen.isVisible() == true) {
            visibilidad = true;
        } else {
            visibilidad = false;
        }
        Usuario nuevoUsuario = new Usuario(FondoOP, fondoGe, opcionesEd, visibilidad, nombre, contra, tipoU, fondoC, barraC, barraNC, fontG, fondoI, listaA, listaC, archivo1, colorF);

        guardarDatos(nuevoUsuario, archivo1);
        return nuevoUsuario;
    }

    public void realizarCambio(Usuario usuario) {
        String nombre = usuario.getNombreUsuario();
        String contra = usuario.getContraseñaUsuario();
        String tipoU = usuario.getTipoUsuario();

        Color barraNC = usuario.getColorNavigator();
        Color barraC = usuario.getColorBarra();
        BarraTareas.setBorder(new com.formdev.flatlaf.ui.FlatLineBorder(new java.awt.Insets(1, 1, 1, 1), barraC, 1, 25));
        Color fondoC = usuario.getColorFondo();
        Icon fondoI = usuario.getFondoImagen();

        Font fontG = usuario.getFontGeneral();
        Color colorF = usuario.getColorFont();

        Color FondoOP = usuario.getBarraNE();
        Color fondoGe = usuario.getFondoNE();
        Color opcionesEd = usuario.getFondoOP();

        listaArchivos = usuario.retornarArchivos();
        listaCarpetas = usuario.retornarCarpetas();

        String archivo1 = usuario.getRutaArchivo();
        if (tipoU.equals("Administrador")) {
            admin = true;
        } else {
            admin = false;
        }
        mostrarConf();
        fondoOpc.setBackground(FondoOP);
        fondoGen.setBackground(fondoGe);
        opcionesEditor.setBackground(opcionesEd);
        BarraNavegacion.setBackground(barraNC);
        BarraTareas.setBackground(barraC);
        FondoPantalla.setBackground(fondoC);
        fondoImagen.setIcon(fondoI);
        if (usuario.verificarVisible() == false) {
            fondoImagen.setVisible(false);
        } else if (usuario.verificarVisible() == true) {
            fondoImagen.setVisible(true);
        }
        ejemploFuente.setFont(fontG);
        ejemploFuente.setForeground(colorF);
        cambiarFontPantallaInicio(fontG, colorF);
//        cambiarColorFontPI(colorF);
    }

    public Usuario formarInicio(Usuario usuario2) {
        String nombre = usuario2.getNombreUsuario();
        String contra = usuario2.getContraseñaUsuario();
        String tipoU = usuario2.getTipoUsuario();

        Color barraNC = usuario2.getColorNavigator();
        Color barraC = usuario2.getColorBarra();
        Color fondoC = usuario2.getColorFondo();
        Icon fondoI = usuario2.getFondoImagen();

        Font fontG = usuario2.getFontGeneral();
        Color colorF = usuario2.getColorFont();
        Color FondoOP = usuario2.barraNE;
        Color fondoGe = usuario2.getFondoNE();
        Color opcionesEd = usuario2.getFondoOP();
        ArrayList<archivosTxt> listaA = new ArrayList<>(listaArchivos);
        ArrayList<carpetasArchivos> listaC = new ArrayList<>(listaCarpetas);

        String archivo1 = usuario2.getRutaArchivo();
        Usuario usuarioDEF = new Usuario(FondoOP, fondoGe, opcionesEd, true, nombre, contra, tipoU, fondoC, barraC, barraNC, fontG, fondoI, listaA, listaC, archivo1, colorF);
        return usuarioDEF;
    }

    public void mostrarConf() {
        if (admin == true) {
            crearUsuarios.setVisible(true);
        } else {
            crearUsuarios.setVisible(false);
        }
    }

    public void guardarDatos(Usuario usuario, String archivo) {
        try (FileOutputStream fileOut = new FileOutputStream(archivo); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(usuario);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public void cargarDatos(String archivo) {

        Usuario usuario = null;

        try (FileInputStream fileIn = new FileInputStream(archivo); ObjectInputStream in = new ObjectInputStream(fileIn)) {

            usuario = (Usuario) in.readObject();

            realizarCambio(usuario);

        } catch (IOException | ClassNotFoundException e) {

        }

    }

    public void cargarUsuariosGuardados() {

        File carpeta = new File(".");

        File[] archivos = carpeta.listFiles();

        for (File archivo : archivos) {

            if (archivo.getName().startsWith("config_") && archivo.getName().endsWith(".dat")) {

                try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo))) {

                    Usuario usuario = (Usuario) in.readObject();

                    Usuarios.add(usuario);

                } catch (Exception e) {

                }
            }
        }
    }
    Graphics g;

    public void Dibujar() {
        for (int i = 0; i < Pintado.listaX().size(); i++) {

            int x = Pintado.listaX().get(i);
            int y = Pintado.listaY().get(i);
            g.fillOval(x, y, tamX, tamY);
        }
    }

    public String noCero(float resultado) {
        String retorno = "";

        retorno = Float.toString(resultado);
        if (resultado % 1 == 0) {
            retorno = retorno.substring(0, retorno.length() - 2);
        }

        return retorno;
    }

    public void colorFondoP(Color color) {
        FondoPantalla.setBackground(color);

    }

    public void ocultarEdicionPantalla(boolean mostrar) {

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Principal().setVisible(true));

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Abrir;
    private javax.swing.JMenuItem AbrirArchivos;
    private javax.swing.JDialog AdminCuentas;
    private javax.swing.JMenuItem ApagarS;
    private javax.swing.JButton AñadirBoton;
    private javax.swing.JButton AñadirBoton1;
    private javax.swing.JCheckBox BarraN2;
    private javax.swing.JMenuBar BarraNavegacion;
    private javax.swing.JDialog BarraProgreso;
    private javax.swing.JPanel BarraTareas;
    private javax.swing.JButton Bold;
    private javax.swing.JButton Bold1;
    private javax.swing.JButton BoldItalic;
    private javax.swing.JButton BoldItalic1;
    private javax.swing.JButton BorrarBoton;
    private javax.swing.JButton BorrarBotonArchivos;
    private javax.swing.JDialog Calculadora;
    private javax.swing.JPanel ColorSolido1;
    private javax.swing.JPanel ColorSolido2;
    private javax.swing.JDialog CrearCarpeta;
    private javax.swing.JMenuItem Eliminar;
    private javax.swing.JMenuItem EliminarArchivos;
    private javax.swing.JPanel FondoPantalla;
    private javax.swing.JPanel FondoPantallaOg;
    private javax.swing.JButton Fuentes;
    private javax.swing.JPanel Imagen;
    private javax.swing.JDialog Informacion;
    private javax.swing.JButton Italic;
    private javax.swing.JButton Italic1;
    private javax.swing.JMenu LogIn;
    private javax.swing.JMenu LogOut;
    private javax.swing.JMenu LogoWitZig;
    private javax.swing.JMenu ModificarPantalla;
    private javax.swing.JDialog ModificarUsuario;
    private javax.swing.JPopupMenu ModificarUsuarios;
    private javax.swing.JPopupMenu OpcionesArchivos;
    private javax.swing.JPopupMenu OpcionesCarpetas;
    private javax.swing.JDialog Paint;
    private javax.swing.JButton Pantalla;
    private javax.swing.JMenuItem Personalizar;
    private javax.swing.JButton Plain;
    private javax.swing.JButton Plain1;
    private javax.swing.JPopupMenu PopUp;
    private javax.swing.JPopupMenu PopUpMenu;
    private javax.swing.JButton RJuego;
    private javax.swing.JDialog Snake;
    private javax.swing.JButton SnakeBoton;
    private javax.swing.JButton TicTacToe;
    private javax.swing.JLabel Wally;
    private javax.swing.JButton WitZig;
    private javax.swing.JDialog XO;
    private javax.swing.JMenuItem abrirArchivo;
    private javax.swing.JTree arbolUsuarios;
    private javax.swing.JMenu archivo;
    private javax.swing.JButton aspectosExternos;
    private javax.swing.JRadioButton barraN1;
    private javax.swing.JProgressBar barraProgreso;
    private javax.swing.JPanel barraSuperior;
    private javax.swing.JRadioButton barraT1;
    private javax.swing.JLabel bienvenida;
    private javax.swing.JLabel bienvenida1;
    private javax.swing.JButton calculadora;
    private javax.swing.JButton calculadora1;
    private javax.swing.JButton cambiarTamaño;
    private javax.swing.JButton cero;
    private javax.swing.JButton cinco;
    private javax.swing.JButton confirmarFuente;
    private javax.swing.JPanel contenedor;
    private javax.swing.JTextField contraUs;
    private javax.swing.JTextField contraseña;
    private javax.swing.JPanel crearCarpeta;
    private javax.swing.JPanel crearCarpeta1;
    private javax.swing.JDialog crearTexto;
    private javax.swing.JMenu crearUsuarios;
    private javax.swing.JButton cuatro;
    private javax.swing.JButton dividir;
    private javax.swing.JButton dos;
    private javax.swing.JPanel editarColoresPantalla;
    private javax.swing.JButton editorTexto1;
    private javax.swing.JTextArea ejemploFuente;
    private javax.swing.JTextArea ejemploFuente1;
    private javax.swing.JPanel eleccionExternos;
    private javax.swing.JPanel eleccionPantalla;
    private javax.swing.JButton elegirFondom;
    private javax.swing.JMenuItem elimU;
    private javax.swing.JDialog entrada;
    private javax.swing.JLabel estiloFuente;
    private javax.swing.JLabel estiloFuente1;
    private javax.swing.JDialog exploradorArchivos;
    private javax.swing.JFileChooser exploradorPc;
    private javax.swing.JFileChooser exploradorPc2;
    private javax.swing.JButton explorarArchivos;
    private javax.swing.JLabel fecha;
    private javax.swing.JPanel fondo;
    private javax.swing.JCheckBox fondoG;
    private javax.swing.JPanel fondoGen;
    private javax.swing.JLabel fondoImagen;
    private javax.swing.JPanel fondoIniciar;
    private javax.swing.JCheckBox fondoOp;
    private javax.swing.JPanel fondoOpc;
    private javax.swing.JButton fondoPm;
    private javax.swing.JMenuItem guardarArchivo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JButton lago;
    private javax.swing.JButton limpiar;
    private javax.swing.JButton mas;
    private javax.swing.JButton menos;
    private javax.swing.JLabel mensaje;
    private javax.swing.JMenuItem modU;
    private javax.swing.JDialog mostrarCarpeta;
    private javax.swing.JLabel mostrarU;
    private javax.swing.JButton multiplicar1;
    private javax.swing.JTextField ncontraU;
    private javax.swing.JButton nevada;
    private javax.swing.JTextField nombreCarpeta;
    private javax.swing.JTextField nombreCarpeta1;
    private javax.swing.JTextField nombreUs;
    private javax.swing.JButton nueve;
    private javax.swing.JTextField nuevoU;
    private javax.swing.JButton ocho;
    private javax.swing.JMenuBar opcionesEditor;
    private javax.swing.JPanel panel1;
    private javax.swing.JPanel panelColoresElementos;
    private javax.swing.JPanel panelFuentes;
    private javax.swing.JPanel panelPantalla;
    private javax.swing.JTextArea pantalla;
    private javax.swing.JMenu personalizarEditor;
    private javax.swing.JMenuItem personalizarEditor2;
    private javax.swing.JDialog personalizarPantalla;
    private javax.swing.JLabel pos1;
    private javax.swing.JLabel pos2;
    private javax.swing.JLabel pos3;
    private javax.swing.JLabel pos4;
    private javax.swing.JLabel pos5;
    private javax.swing.JLabel pos6;
    private javax.swing.JLabel pos7;
    private javax.swing.JLabel pos8;
    private javax.swing.JLabel pos9;
    private javax.swing.JLabel puntajeO;
    private javax.swing.JLabel puntajeX;
    private javax.swing.JButton punto;
    private javax.swing.JButton rancho;
    private javax.swing.JButton regresar;
    private javax.swing.JButton resultado;
    private javax.swing.JLabel rolTipo;
    private javax.swing.JTextField ruta;
    private javax.swing.JButton seis;
    private javax.swing.JButton seleccionColor3;
    private javax.swing.JComboBox<String> seleccionFondo;
    private javax.swing.JButton seleccionarRuta;
    private javax.swing.JButton siete;
    private javax.swing.JSpinner spinnerFuente;
    private javax.swing.JSpinner spinnerFuente1;
    private javax.swing.JScrollPane tablaArchivos;
    private javax.swing.JTable tablaArchivosTxt;
    private javax.swing.JScrollPane tablaCarpetas;
    private javax.swing.JTable tablaExplorador;
    private javax.swing.JTable tablaUsuarios;
    private javax.swing.JSpinner tamañoFuente;
    private javax.swing.JSpinner tamañoFuente1;
    private javax.swing.JSpinner tamañoPincel;
    private javax.swing.JButton tarde;
    private javax.swing.JComboBox<String> tipo;
    private javax.swing.JLabel tituloConfiguracion;
    private javax.swing.JLabel tituloConfiguracion1;
    private javax.swing.JLabel tituloConfiguracion2;
    private javax.swing.JLabel tituloConfiguracion3;
    private javax.swing.JLabel tituloConfiguracion4;
    private javax.swing.JLabel tituloConfiguracion5;
    private javax.swing.JLabel tituloConfiguracion6;
    private javax.swing.JButton tres;
    private javax.swing.JLabel turnoMostrar;
    private javax.swing.JLabel ubicacionRutan;
    private javax.swing.JButton uno;
    private javax.swing.JLabel user;
    private javax.swing.JTextField usuario;
    private javax.swing.JMenu verUsuarios;
    private javax.swing.JButton wDefecto;
    private javax.swing.JButton wDefecto1;
    private javax.swing.JButton wDefecto2;
    private javax.swing.JLabel willyCelebra;
    // End of variables declaration//GEN-END:variables
}
