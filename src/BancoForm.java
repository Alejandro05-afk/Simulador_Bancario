import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BancoForm extends JFrame {
    private JButton depositoButton;
    private JButton retiroButton;
    private JButton transferenciaButton;
    private JButton salirButton;
    private JLabel labelNombreCliente;
    private JLabel labelSaldo;
    private JPanel Banco;
    private JTextArea historialTextArea;

    // Variables para el nombre del cliente y saldo
    private String nombreCliente;
    private double saldo;

    public BancoForm(String nombreCliente) {
        this.nombreCliente = nombreCliente; // Asignar el nombre del cliente
        this.saldo = 1000.00; // Saldo inicial

        // Configuración de la ventana
        setTitle("Banco - Cliente");
        setSize(600, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana

        // Inicializar componentes desde el formulario existente
        // Aquí deberías cargar el formulario, si es necesario.

        // Establecer el panel como contenido de la ventana
        setContentPane(Banco);

        // Inicializar las etiquetas con el nombre del cliente y el saldo
        labelNombreCliente.setText("Cliente: " + nombreCliente);
        labelSaldo.setText("Saldo: $" + String.format("%.2f", saldo));

        // Acción de los botones
        depositoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarDeposito();
            }
        });

        retiroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarRetiro();
            }
        });

        transferenciaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarTransferencia();
            }
        });

        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salirAplicacion();
            }
        });

        setVisible(true); // Hacer visible la ventana
    }

    private void realizarDeposito() {
        String montoStr = JOptionPane.showInputDialog(this, "Ingrese el monto a depositar:");
        if (montoStr != null) {
            double monto = Double.parseDouble(montoStr);
            saldo += monto; // Actualizar saldo
            labelSaldo.setText("Saldo: $" + String.format("%.2f", saldo)); // Actualizar etiqueta
            historialTextArea.append("Depósito: " + monto + "\n");
        }
    }

    private void realizarRetiro() {
        String montoStr = JOptionPane.showInputDialog(this, "Ingrese el monto a retirar:");
        if (montoStr != null) {
            double monto = Double.parseDouble(montoStr);
            if (monto <= saldo) {
                saldo -= monto; // Actualizar saldo
                labelSaldo.setText("Saldo: $" + String.format("%.2f", saldo)); // Actualizar etiqueta
                historialTextArea.append("Retiro: " + monto + "\n");
            } else {
                JOptionPane.showMessageDialog(this, "Saldo insuficiente", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void realizarTransferencia() {
        // Solicitar el nombre del destinatario
        String destinatario = JOptionPane.showInputDialog(this, "Ingrese el nombre del destinatario:");
        if (destinatario != null && !destinatario.trim().isEmpty()) {
            // Solicitar el monto a transferir
            String montoStr = JOptionPane.showInputDialog(this, "Ingrese el monto a transferir:");
            if (montoStr != null) {
                double monto = Double.parseDouble(montoStr);
                // Verificar que el monto no supere el saldo
                if (monto <= saldo) {
                    saldo -= monto; // Restar del saldo
                    labelSaldo.setText("Saldo: $" + String.format("%.2f", saldo)); // Actualizar etiqueta
                    historialTextArea.append("Transferencia a " + destinatario + ": " + monto + "\n");
                    JOptionPane.showMessageDialog(this, "Transferencia exitosa a " + destinatario + " por $" + String.format("%.2f", monto));
                } else {
                    JOptionPane.showMessageDialog(this, "Saldo insuficiente", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "El nombre del destinatario no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void salirAplicacion() {
        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea salir?", "Salir", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            dispose(); // Cerrar la aplicación
        }
    }

    public static void main(String[] args) {
        new BancoForm("Cliente Ejemplo"); // Iniciar la aplicación con un nombre de cliente
    }
}
