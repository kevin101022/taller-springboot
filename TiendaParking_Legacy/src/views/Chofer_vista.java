package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import controller.Controlador;
import model.chofer_modelo;

// Vista para registrar un chofer usando JDialog modal.
public class Chofer_vista {

    public void registrar_chofer(Scanner obj_teclado, Controlador obj_controller) {
        JDialog dialog = new JDialog((Frame) null, "Registro de Chofer", true);
        dialog.setSize(400, 260);
        dialog.setLocationRelativeTo(null);
        dialog.setAlwaysOnTop(true);
        dialog.setLayout(new BorderLayout(10, 10));

        JPanel mainPanel = new JPanel(new GridLayout(4, 2, 8, 8));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        mainPanel.add(new JLabel("Cedula (solo numeros):"));
        JTextField txtCedula = new JTextField(15);
        mainPanel.add(txtCedula);

        mainPanel.add(new JLabel("Nombre:"));
        JTextField txtNombre = new JTextField(15);
        mainPanel.add(txtNombre);

        mainPanel.add(new JLabel("Apellido:"));
        JTextField txtApellido = new JTextField(15);
        mainPanel.add(txtApellido);

        mainPanel.add(new JLabel("Licencia:"));
        JTextField txtLicencia = new JTextField(15);
        mainPanel.add(txtLicencia);

        dialog.add(mainPanel, BorderLayout.CENTER);

        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnRegistrar = new JButton("Registrar");

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cedula   = txtCedula.getText().trim();
                String nombre   = txtNombre.getText().trim();
                String apellido = txtApellido.getText().trim();
                String licencia = txtLicencia.getText().trim();

                if (cedula.isEmpty() || !cedula.matches("\\d+")) {
                    JOptionPane.showMessageDialog(dialog, "La cedula debe contener solo numeros y no puede estar vacia.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "El nombre no puede estar vacio.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (apellido.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "El apellido no puede estar vacio.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (licencia.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "La licencia no puede estar vacia.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    chofer_modelo obj_chofer = obj_controller.registrarChofer(nombre, apellido, cedula, licencia);
                    String mensaje = "Chofer registrado temporalmente en la Ficha de Viaje:\n"
                            + "Cedula: "   + obj_chofer.getCedula_chofer()   + "\n"
                            + "Nombre: "   + obj_chofer.getNombre_chofer()   + "\n"
                            + "Apellido: " + obj_chofer.getApellido_chofer() + "\n"
                            + "Licencia: " + obj_chofer.getLicencia_chofer();
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
