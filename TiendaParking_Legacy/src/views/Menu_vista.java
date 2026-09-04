package views;

import controller.Controlador;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Menu principal como JFrame con botones para cada opcion.
public class Menu_vista {

    private final Controlador obj_controller;
    private JFrame frame;

    public Menu_vista(Controlador obj_controller) {
        this.obj_controller = obj_controller;
    }

    public void mostrarMenu() {
        frame = new JFrame("MENU TIENDAPARKING");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 380);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(10, 10));

        JLabel titulo = new JLabel("TiendaParking", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        titulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 5, 0));
        frame.add(titulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridLayout(6, 1, 6, 6));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

        JButton btn1 = new JButton("1. Registrar Chofer");
        JButton btn2 = new JButton("2. Registrar Carro");
        JButton btn3 = new JButton("3. Registrar Motor");
        JButton btn4 = new JButton("4. Registrar Pasajero");
        JButton btn5 = new JButton("5. Confirmar y Guardar en Base de Datos");
        JButton btn0 = new JButton("0. Salir");

        btn1.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                new Chofer_vista().registrar_chofer(null, obj_controller);
            }
        });
        btn2.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                new Carro_vista().registrar_carro(null, obj_controller);
            }
        });
        btn3.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                new Motor_vista().registrar_motor(null, obj_controller);
            }
        });
        btn4.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                new Pasajero_vista().registrar_pasajero(null, obj_controller);
            }
        });
        btn5.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                confirmarEnBD();
            }
        });
        btn0.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                frame.dispose();
                System.exit(0);
            }
        });

        panelBotones.add(btn1);
        panelBotones.add(btn2);
        panelBotones.add(btn3);
        panelBotones.add(btn4);
        panelBotones.add(btn5);
        panelBotones.add(btn0);

        frame.add(panelBotones, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    // Opcion 5: validar ficha y persistir en BD con resultado en dialogo
    private void confirmarEnBD() {
        if (!obj_controller.validarFichaCompleta()) {
            JOptionPane.showMessageDialog(frame,
                "Faltan datos en la Ficha de Viaje.\nRegistre Carro, Motor, Chofer y Pasajero antes de confirmar.",
                "Ficha Incompleta", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String resultado = obj_controller.persistirFicha();
        JOptionPane.showMessageDialog(frame, resultado,
            resultado.startsWith("Registro") ? "Exito" : "Error BD",
            resultado.startsWith("Registro") ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
    }

    // Metodos legacy para compatibilidad con llamadas de consola (no se usan en modo GUI)
    public int mostrarMenu(java.util.Scanner s) { return -1; }
    public void mostrarOpcionInvalida() {}
    public void mostrarSalida() {}
}
