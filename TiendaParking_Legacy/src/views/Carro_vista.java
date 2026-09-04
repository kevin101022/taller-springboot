package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import controller.Controlador;
import model.Carro_modelo;

// Vista para registrar un carro usando JDialog modal.
public class Carro_vista {

    public void registrar_carro(Scanner obj_teclado, Controlador obj_controller) {
        JDialog dialog = new JDialog((Frame) null, "Registro de Carro", true);
        dialog.setSize(400, 220);
        dialog.setLocationRelativeTo(null);
        dialog.setAlwaysOnTop(true);
        dialog.setLayout(new BorderLayout(10, 10));

        JPanel mainPanel = new JPanel(new GridLayout(3, 2, 8, 8));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        mainPanel.add(new JLabel("Placa (min. 3 caracteres):"));
        JTextField txtPlaca = new JTextField(15);
        mainPanel.add(txtPlaca);

        mainPanel.add(new JLabel("Marca:"));
        JTextField txtMarca = new JTextField(15);
        mainPanel.add(txtMarca);

        mainPanel.add(new JLabel("Modelo:"));
        JTextField txtModelo = new JTextField(15);
        mainPanel.add(txtModelo);

        dialog.add(mainPanel, BorderLayout.CENTER);

        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnRegistrar = new JButton("Registrar");

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String placa  = txtPlaca.getText().trim();
                String marca  = txtMarca.getText().trim();
                String modelo = txtModelo.getText().trim();

                if (placa.isEmpty() || placa.length() < 3) {
                    JOptionPane.showMessageDialog(dialog, "La placa debe tener al menos 3 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (marca.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "La marca no puede estar vacia.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (modelo.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "El modelo no puede estar vacio.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    Carro_modelo obj_carro = obj_controller.registrarCarro(placa, marca, modelo);
                    String mensaje = "Carro registrado temporalmente en la Ficha de Viaje:\n"
                            + "Placa: "  + obj_carro.getPlaca_carro()  + "\n"
                            + "Marca: "  + obj_carro.getMarca_carro()  + "\n"
                            + "Modelo: " + obj_carro.getModelo_carro();
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
