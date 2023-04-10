package client;

import server.Authenticator;
import server.AuthenticatorImplement;
import server.StaffCRUDImplement;
import server.StudentCRUDImplement;
import server.Staff;
import server.Student;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpWindowGUI {
    private JFrame signUpFrame;
    private JComboBox<String> userTypeComboBox;
    private JComboBox<String> roleComboBox;
    private JTextField firstNameField, lastNameField, emailField, contactNumberField, idField;
    private JPasswordField passwordField;
    private Authenticator authService;

    public SignUpWindowGUI() {
        initialize();
    }

    private void initialize() {
        authService = new AuthenticatorImplement();

        signUpFrame = new JFrame("Sign Up");
        signUpFrame.setBounds(100, 100, 800, 400);
        signUpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        signUpFrame.getContentPane().setLayout(null);

        // User type
        JLabel lblUserType = new JLabel("User Type");
        lblUserType.setBounds(100, 60, 81, 16);
        signUpFrame.getContentPane().add(lblUserType);

        userTypeComboBox = new JComboBox<>();
        userTypeComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Student", "Staff"}));
        userTypeComboBox.setBounds(190, 56, 230, 27);
        signUpFrame.getContentPane().add(userTypeComboBox);

        // Role
        JLabel lblRole = new JLabel("Role");
        lblRole.setBounds(100, 100, 81, 16);
        signUpFrame.getContentPane().add(lblRole);

        roleComboBox = new JComboBox<>();
        roleComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Advisor", "Supervisor"}));
        roleComboBox.setBounds(190, 96, 230, 27);
        signUpFrame.getContentPane().add(roleComboBox);

        userTypeComboBox.addActionListener(e -> {
            String userType = (String) userTypeComboBox.getSelectedItem();
            roleComboBox.setVisible("Staff".equals(userType));
            lblRole.setVisible("Staff".equals(userType));
        });

        roleComboBox.setVisible(false);
        lblRole.setVisible(false);

        // Add other input fields, labels, and buttons for registration
        // ID
        JLabel lblId = new JLabel("ID");
        lblId.setBounds(100, 140, 81, 16);
        signUpFrame.getContentPane().add(lblId);

        idField = new JTextField();
        idField.setBounds(190, 134, 230, 28);
        signUpFrame.getContentPane().add(idField);
        idField.setColumns(10);

        // First name
        JLabel lblFirstName = new JLabel("First Name");
        lblFirstName.setBounds(100, 180, 81, 16);
        signUpFrame.getContentPane().add(lblFirstName);

        firstNameField = new JTextField();
        firstNameField.setBounds(190, 174, 230, 28);
        signUpFrame.getContentPane().add(firstNameField);
        firstNameField.setColumns(10);

        // Last name
        JLabel lblLastName = new JLabel("Last Name");
        lblLastName.setBounds(100, 220, 81, 16);
        signUpFrame.getContentPane().add(lblLastName);

        lastNameField = new JTextField();
        lastNameField.setBounds(190, 214, 230, 28);
        signUpFrame.getContentPane().add(lastNameField);
        lastNameField.setColumns(10);

        // Email
        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(100, 260, 81, 16);
        signUpFrame.getContentPane().add(lblEmail);

        emailField = new JTextField();
        emailField.setBounds(190, 254, 230, 28);
        signUpFrame.getContentPane().add(emailField);
        emailField.setColumns(10);

        // Contact number
        JLabel lblContactNumber = new JLabel("Contact Number");
        lblContactNumber.setBounds(100, 300, 110, 16);
        signUpFrame.getContentPane().add(lblContactNumber);

        contactNumberField = new JTextField();
        contactNumberField.setBounds(190, 294, 230, 28);
        signUpFrame.getContentPane().add(contactNumberField);
        contactNumberField.setColumns(10);

        // Password
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(450, 140, 81, 16);
        signUpFrame.getContentPane().add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setBounds(540, 134, 230, 28);
        signUpFrame.getContentPane().add(passwordField);

        // Sign up button
        JButton btnSignUp = new JButton("Sign Up");
        btnSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
        btnSignUp.setBounds(540, 180, 117, 29);
        signUpFrame.getContentPane().add(btnSignUp);

        signUpFrame.setVisible(true);
    }

    private void registerUser() {
        String userType = (String) userTypeComboBox.getSelectedItem();
        String id = idField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String contactNumber = contactNumberField.getText();
        String password = String.valueOf(passwordField.getPassword());

        boolean isRegistered = false;

        if ("Student".equals(userType)) {
            Student student = new Student(id, firstName, lastName, email, contactNumber, password);
            StudentCRUDImplement studentCRUD = new StudentCRUDImplement();
            isRegistered = studentCRUD.addStudent(student);
        } else {
            String role = (String) roleComboBox.getSelectedItem();
            Staff staff = new Staff(id, firstName, lastName, email, contactNumber, password, role);
            StaffCRUDImplement staffCRUD = new StaffCRUDImplement();
            isRegistered = staffCRUD.addStaff(staff);
        }

        if (isRegistered) {
            JOptionPane.showMessageDialog(signUpFrame, "Registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            signUpFrame.dispose();
            AuthenticatorGUI window = new AuthenticatorGUI();
            window.getFrame().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(signUpFrame, "Registration failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}