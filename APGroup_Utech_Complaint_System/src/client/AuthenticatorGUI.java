package client;

import server.AuthenticatorImplement;
import server.Authenticator;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class AuthenticatorGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> userTypeComboBox;
    private Authenticator authService;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                AuthenticatorGUI window = new AuthenticatorGUI();
                window.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public AuthenticatorGUI() {
        initialize();
    }

    private void initialize() {
        setTitle("Welcome to the University of Technology Complaint System");
        setBounds(100, 100, 800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel lblTitle = new JLabel("Login Screen");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setBounds(330, 20, 200, 40);
        getContentPane().add(lblTitle);

        JLabel lblUsername = new JLabel("ID");
        lblUsername.setBounds(400, 100, 81, 16);
        getContentPane().add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(400, 140, 81, 16);
        getContentPane().add(lblPassword);

        usernameField = new JTextField();
        usernameField.setBounds(490, 94, 230, 28);
        getContentPane().add(usernameField);
        usernameField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(490, 134, 230, 28);
        getContentPane().add(passwordField);

        JLabel lblUserType = new JLabel("User Type");
        lblUserType.setBounds(400, 60, 81, 16);
        getContentPane().add(lblUserType);

        userTypeComboBox = new JComboBox<>();
        userTypeComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Student", "Staff"}));
        userTypeComboBox.setBounds(490, 56, 230, 27);
        getContentPane().add(userTypeComboBox);

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authenticateUser();
            }
        });
        btnLogin.setBounds(490, 180, 117, 29);
        getContentPane().add(btnLogin);

        JButton btnSignUp = new JButton("Sign Up");
        btnSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SignUpWindowGUI(this);
            }
        });
        btnSignUp.setBounds(360, 330, 80, 20);
        getContentPane().add(btnSignUp);

        try {
            String filePath = "C:/Users/alexa/OneDrive/School/Utech%202022-2023/SEM2/Advanced%20Programming/Code/APGroup_Utech_Complaint_System/images/utech-logo-nobg.png";
            String decodedFilePath = URLDecoder.decode(filePath, StandardCharsets.UTF_8);
            File imageFile = new File(decodedFilePath);
            BufferedImage myPicture = ImageIO.read(imageFile);

            int imageWidth = 150;
            int imageHeight = 150;

            Image scaledImage = myPicture.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);
            JLabel picLabel = new JLabel(new ImageIcon(scaledImage));
            picLabel.setBounds(30, 100, imageWidth, imageHeight);
            getContentPane().add(picLabel);
        } catch (IOException e) {
            e.printStackTrace();
        }
        authService = new AuthenticatorImplement();

        btnSignUp.addActionListener(e -> {
            SignUpWindowGUI signUpWindowGUI = new SignUpWindowGUI();
            setVisible(false);
        });
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
            // closes the login form
            dispose();

            // shows the appropriate dashboard based on user type and role
            if ("Student".equals(userType)) {
            	String studentId = authService.getStudentIdAfterLogin(identifier);
                
                StudentDashboardGUI studentDashboardGUI = new StudentDashboardGUI(studentId);
                studentDashboardGUI.setVisible(true);
            } else {
                String role = authService.getStaffRole(identifier);
                if ("advisor".equals(role)) {
                    AdvisorDashboardGUI advisorDashboardGUI = new AdvisorDashboardGUI();
                    advisorDashboardGUI.setVisible(true);
                } else if ("supervisor".equals(role)) {
                    SupervisorDashboardGUI supervisorDashboardGUI = new SupervisorDashboardGUI();
                    supervisorDashboardGUI.setVisible(true);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid ID or password.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}