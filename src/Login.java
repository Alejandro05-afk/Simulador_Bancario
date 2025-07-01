import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private JButton ingresarButton;
    private JTextField textUsuario;
    private JPasswordField passwordField1;
    private JPanel Login;

    public Login() {
        // Configuración de la ventana
        setTitle("Sistema Bancario");
        setSize(600, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana

        // Establecer el panel como contenido de la ventana
        setContentPane(Login);

        // Acción del botón ingresar
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = textUsuario.getText();
                String password = new String(passwordField1.getPassword());

                // Validar credenciales
                if (usuario.equals("cliente123") && password.equals("clave456")) {
                    // Si las credenciales son correctas, mostrar la ventana principal
                    BancoForm bancoForm = new BancoForm(usuario); // Pasar el nombre del cliente
                    bancoForm.setVisible(true);
                    dispose(); // Ocultar la ventana de login
                } else {
                    // Mostrar mensaje de error
                    JOptionPane.showMessageDialog(Login.this, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Hacer visible la ventana
        setVisible(true);
    }
}
