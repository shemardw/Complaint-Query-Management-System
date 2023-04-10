package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import server.Student;
import server.StudentCRUDImplement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class StudentApp {
	
    private JFrame frame;
    private JTextField studentIdField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField contactNumberField;
    private JTextField passwordField;
    private StudentCRUDImplement studentCRUD;
    
    private static final Logger logger = LogManager.getLogger(Student.class);

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                StudentApp window = new StudentApp();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
        // logging
        logger.info("This is an info message");
        logger.warn("This is a warning message");
        logger.error("This is an error message");
        logger.fatal("This is a fatal error message");
    }

    public StudentApp() {
        studentCRUD = new StudentCRUDImplement();
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(7, 2));

        frame.getContentPane().add(new JLabel("Student ID:"));
        studentIdField = new JTextField();
        frame.getContentPane().add(studentIdField);

        frame.getContentPane().add(new JLabel("First Name:"));
        firstNameField = new JTextField();
        frame.getContentPane().add(firstNameField);

        frame.getContentPane().add(new JLabel("Last Name:"));
        lastNameField = new JTextField();
        frame.getContentPane().add(lastNameField);

        frame.getContentPane().add(new JLabel("Email:"));
        emailField = new JTextField();
        frame.getContentPane().add(emailField);

        frame.getContentPane().add(new JLabel("Contact Number:"));
        contactNumberField = new JTextField();
        frame.getContentPane().add(contactNumberField);

        frame.getContentPane().add(new JLabel("Password:"));
        passwordField = new JTextField();
        frame.getContentPane().add(passwordField);

        JButton addButton = new JButton("Sign Up");
        frame.getContentPane().add(addButton);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        frame.pack();
    }

    private void addStudent() {
        String studentId = studentIdField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String contactNumber = contactNumberField.getText();
        String password = passwordField.getText();

        Student newStudent = new Student(studentId, firstName, lastName, email, contactNumber, password);
        boolean added = studentCRUD.addStudent(newStudent);

        if (added) {
            JOptionPane.showMessageDialog(frame, "Student added successfully.");
        } else {
            JOptionPane.showMessageDialog(frame, "Failed to add student.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}