package client;

import server.AuthenticatorImplement;
import server.Authenticator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthenticatorGUI {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> userTypeComboBox;
    private Authenticator authService;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                AuthenticatorGUI window = new AuthenticatorGUI();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public AuthenticatorGUI() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblUsername = new JLabel("ID");
        lblUsername.setBounds(62, 91, 81, 16);
        frame.getContentPane().add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(62, 133, 81, 16);
        frame.getContentPane().add(lblPassword);

        usernameField = new JTextField();
        usernameField.setBounds(155, 85, 230, 28);
        frame.getContentPane().add(usernameField);
        usernameField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(155, 127, 230, 28);
        frame.getContentPane().add(passwordField);

        JLabel lblUserType = new JLabel("User Type");
        lblUserType.setBounds(62, 49, 81, 16);
        frame.getContentPane().add(lblUserType);

        userTypeComboBox = new JComboBox<>();
        userTypeComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Student", "Staff"}));
        userTypeComboBox.setBounds(155, 45, 230, 27);
        frame.getContentPane().add(userTypeComboBox);

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authenticateUser();
            }
        });
        btnLogin.setBounds(155, 196, 117, 29);
        frame.getContentPane().add(btnLogin);

        authService = new AuthenticatorImplement();
    }

    private void authenticateUser() {
        String identifier = usernameField.getText();
        String password = String.valueOf(passwordField.getPassword());
        String userType = (String) userTypeComboBox.getSelectedItem();
        boolean isAuthenticated;

        if ("Student".equals(userType)) {
            isAuthenticated = authService.authenticatorStudent(identifier, password);
        } else {
            isAuthenticated = authService.authenticatorStaff(identifier, password);
        }

        if (isAuthenticated) {
            // Show the main application window and close the login form
        	JFrame mainAppWindow = new JFrame();
        	mainAppWindow.setBounds(100, 100, 800, 600);
        	mainAppWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	mainAppWindow.setVisible(true);
            frame.dispose();
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid ID or password.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}