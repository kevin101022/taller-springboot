package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import controller.Controlador;
import model.Motor_modelo;

// Vista para registrar un motor usando JDialog modal.
public class Motor_vista {

    public void registrar_motor(Scanner obj_teclado, Controlador obj_controller) {
        JDialog dialog = new JDialog((Frame) null, "Registro de Motor", true);
        dialog.setSize(400, 250);
        dialog.setLocationRelativeTo(null);
        dialog.setAlwaysOnTop(true);
        dialog.setLayout(new BorderLayout(10, 10));

        JPanel mainPanel = new JPanel(new GridLayout(3, 2, 8, 8));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        mainPanel.add(new JLabel("Numero de Serie:"));
        JTextField txtNumeroSerie = new JTextField(15);
        mainPanel.add(txtNumeroSerie);

        mainPanel.add(new JLabel("Tipo de Motor:"));
        JTextField txtTipo = new JTextField(15);
        mainPanel.add(txtTipo);

        mainPanel.add(new JLabel("Caballos de Fuerza:"));
        JTextField txtCaballos = new JTextField(15);
        mainPanel.add(txtCaballos);

        dialog.add(mainPanel, BorderLayout.CENTER);

        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnRegistrar = new JButton("Registrar");

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String serie = txtNumeroSerie.getText().trim();
                String tipo  = txtTipo.getText().trim();
                String caballos = txtCaballos.getText().trim();

                if (serie.isEmpty() || !serie.matches("[a-zA-Z0-9]+")) {
                    JOptionPane.showMessageDialog(dialog, "El numero de serie debe ser alfanumerico y no puede estar vacio.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (tipo.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "El tipo de motor no puede estar vacio.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (caballos.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Los caballos de fuerza no pueden estar vacios.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    Motor_modelo obj_motor = obj_controller.registrarMotor(tipo, caballos, serie);
                    String mensaje = "Motor registrado temporalmente en la Ficha de Viaje:\n"
                            + "Numero de Serie: " + obj_motor.getNumero_serie() + "\n"
                            + "Tipo de Motor: "   + obj_motor.getTipo_motor() + "\n"
                            + "Caballos Fuerza: " + obj_motor.getCaballos_fuerza();
                    JOptionPane.showMessageDialog(dialog, mensaje, "Exito", JOptionPane.INFORMATION_MESSAGE);
                    dialog.dispose();
                } catch (RuntimeException ex) {
                    JOptionPane.showMessageDialog(dialog, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panelBoton.add(btnRegistrar);
        dialog.add(panelBoton, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }
}
