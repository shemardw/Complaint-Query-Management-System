package client;

import javax.swing.*;

import server.Complaint;
import server.ComplaintCRUD;
import server.ComplaintCRUDImplement;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;

public class StudentDashboardGUI extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JTable complaintsTable;
    private String studentId;

    public StudentDashboardGUI(String studentId) {
    	this.studentId = studentId; // sets the studentId for this instance

        setTitle("Student Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        createMenuBar();
        createMainPanel();
        loadTrackComplaintsData(); // add this line to load the data when the dashboard is opened

        getContentPane().add(mainPanel, BorderLayout.CENTER);
    }

    // setup the side bar / menu bar
    private void createMenuBar() {
        int sideBarWidth = 200; // set the width of the side bar

        JPanel menuBar = new JPanel();
        menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.Y_AXIS));
        menuBar.setBackground(new Color(0x2F2278)); // set background color to #2F2278 (nazy blue)
        menuBar.setPreferredSize(new Dimension(sideBarWidth, getHeight())); // Set preferred size for the menuBar

        JButton homeButton = new JButton("Home");
        homeButton.setForeground(Color.YELLOW); // Set text color to white
        homeButton.setBackground(new Color(0x2F2278)); // Set background color to #2F2278 (nazy blue)
        homeButton.setAlignmentX(Component.CENTER_ALIGNMENT); // centers the button
        homeButton.setMaximumSize(new Dimension(sideBarWidth, homeButton.getPreferredSize().height)); // Set maximum width
        menuBar.add(homeButton);

        JButton trackComplaintsButton = new JButton("Track Complaints");
        trackComplaintsButton.setForeground(Color.WHITE); // sets text color to white
        trackComplaintsButton.setBackground(new Color(0x2F2278)); // sets background color to #2F2278
        trackComplaintsButton.setAlignmentX(Component.CENTER_ALIGNMENT); // centers the button
        trackComplaintsButton.setMaximumSize(new Dimension(sideBarWidth, trackComplaintsButton.getPreferredSize().height)); // set maximum width
        menuBar.add(trackComplaintsButton);

        JButton newComplaintButton = new JButton("New Complaint");
        newComplaintButton.setForeground(Color.WHITE); // set text color to white
        newComplaintButton.setBackground(new Color(0x2F2278)); // set background color to #2F2278
        newComplaintButton.setAlignmentX(Component.CENTER_ALIGNMENT); // center the button
        newComplaintButton.setMaximumSize(new Dimension(sideBarWidth, newComplaintButton.getPreferredSize().height)); // set maximum width
        menuBar.add(newComplaintButton);
        
        JButton liveChatButton = new JButton("Live Chat");
        liveChatButton.setForeground(Color.WHITE); // set text color to white
        liveChatButton.setBackground(new Color(0x2F2278)); // set background color to #2F2278
        liveChatButton.setAlignmentX(Component.CENTER_ALIGNMENT); // center the button
        liveChatButton.setMaximumSize(new Dimension(sideBarWidth, liveChatButton.getPreferredSize().height)); // set maximum width
        menuBar.add(liveChatButton);
       
        JButton logoutButton = new JButton("Logout");
        logoutButton.setForeground(Color.WHITE); // set text color to white
        logoutButton.setBackground(new Color(0x2F2278)); // set background color to #2F2278
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT); // center the button
        logoutButton.setMaximumSize(new Dimension(sideBarWidth, logoutButton.getPreferredSize().height)); // set maximum width
        menuBar.add(logoutButton);

        getContentPane().add(menuBar, BorderLayout.WEST);
        
        // listeners
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "homePanel");
            }
        });

        trackComplaintsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "trackComplaintsPanel");
                loadTrackComplaintsData();
            }
        });

        newComplaintButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "newComplaintPanel");
            }
        });

        liveChatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "liveChatPanel");
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogout();
            }
        });
    }
    
    private void createMainPanel() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        JPanel homePanel = createHomePanel();
        JPanel trackComplaintsPanel = createTrackComplaintsPanel();
        JPanel newComplaintPanel = createNewComplaintPanel();
        JPanel liveChatPanel = createLiveChatPanel();

        mainPanel.add(homePanel, "homePanel");
        mainPanel.add(trackComplaintsPanel, "trackComplaintsPanel");
        mainPanel.add(newComplaintPanel, "newComplaintPanel");
        mainPanel.add(liveChatPanel, "liveChatPanel");
    }

    // Home Panel
    private JPanel createHomePanel() {
        JPanel homePanel = new JPanel();
        JLabel welcomeLabel = new JLabel("Welcome to the Student Home Page");
        homePanel.add(welcomeLabel);
        return homePanel;
    }
    
    // Track Complaints
    private JPanel createTrackComplaintsPanel() {
    	JPanel trackComplaintsPanel = new JPanel(new BorderLayout());
        JLabel trackComplaintsHeading = new JLabel("Track Complaints");
        trackComplaintsPanel.add(trackComplaintsHeading, BorderLayout.NORTH);

        // adds filter UI components
        JPanel filterPanel = new JPanel(new FlowLayout());
        JComboBox<String> filterTypeComboBox = new JComboBox<>(new String[]{"id", "category", "status", "advisor"});
        JTextField filterTextField = new JTextField(20);
        filterPanel.add(new JLabel("Filter by:"));
        filterPanel.add(filterTypeComboBox);
        filterPanel.add(filterTextField);
        trackComplaintsPanel.add(filterPanel, BorderLayout.SOUTH);

        // fetch the complaints from the database
        ComplaintCRUDImplement complaintCRUD = new ComplaintCRUDImplement();
        List<Complaint> complaints = complaintCRUD.getAllStudentComplaints(studentId);

        // create the table model and set it to the JTable
        complaintsTable = new JTable(); // Assign the new JTable directly to the class-level variable
        ComplaintsTableModel tableModel = new ComplaintsTableModel(complaints);
        complaintsTable.setModel(tableModel);

        TableRowSorter<TableModel> sorter = new TableRowSorter<>(complaintsTable.getModel());
        complaintsTable.setRowSorter(sorter);
        JScrollPane scrollPane = new JScrollPane(complaintsTable);
        trackComplaintsPanel.add(scrollPane, BorderLayout.CENTER);


        // actionListener for the filter type combo box
        filterTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filterType = (String) filterTypeComboBox.getSelectedItem();
                String filterString = filterTextField.getText();
                applyComplaintsFilter(filterString, filterType);
            }
        });

        // documentListener for the filter text field
        filterTextField.getDocument().addDocumentListener(new DocumentListener() {
            private void updateFilter() {
                String filterType = (String) filterTypeComboBox.getSelectedItem();
                String filterString = filterTextField.getText();
                applyComplaintsFilter(filterString, filterType);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                updateFilter();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateFilter();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateFilter();
            }
        });

        return trackComplaintsPanel;
    }

 // New Complaint
    private JPanel createNewComplaintPanel() {
        JPanel newComplaintPanel = new JPanel(new BorderLayout());
        JLabel newComplaintHeading = new JLabel("New Complaint");
        newComplaintPanel.add(newComplaintHeading, BorderLayout.NORTH);

        // adds form components
        JPanel formPanel = new JPanel(new GridLayout(0, 2));
        JTextField categoryField = new JTextField();
        JTextArea descriptionField = new JTextArea();
        JButton submitButton = new JButton("Submit");

        formPanel.add(new JLabel("Category:"));
        formPanel.add(categoryField);
        formPanel.add(new JLabel("Description:"));
        formPanel.add(descriptionField);

        newComplaintPanel.add(formPanel, BorderLayout.CENTER);

        // adds submit button
        JPanel submitButtonPanel = new JPanel(new FlowLayout());
        submitButtonPanel.add(submitButton);
        newComplaintPanel.add(submitButtonPanel, BorderLayout.SOUTH);

        // adds submit button action listener
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Complaint newComplaint = new Complaint();
                newComplaint.setStudentId(studentId); // Set the studentId from the current student object
                newComplaint.setStaffId(null); // No staffId assigned initially
                newComplaint.setCategory(categoryField.getText());
                newComplaint.setDescription(descriptionField.getText());
                newComplaint.setStatus("New"); // Set the initial status
                newComplaint.setCreateAt(new Date()); // Set the current date and time
                newComplaint.setUpdatedAt(new Date()); // Set the current date and time

                // adds the new complaint to the database
                ComplaintCRUDImplement complaintCRUD = new ComplaintCRUDImplement();
                boolean success = complaintCRUD.addComplaint(newComplaint);

                if (success) {
                    JOptionPane.showMessageDialog(newComplaintPanel, "Complaint submitted successfully.");
                    categoryField.setText("");
                    descriptionField.setText("");
                } else {
                    JOptionPane.showMessageDialog(newComplaintPanel, "Error submitting complaint. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        return newComplaintPanel;
    }

    private JPanel createLiveChatPanel() {
        JPanel liveChatPanel = new JPanel();
        // adds components and logic for live chat
        return liveChatPanel;
    }
    
    private void handleLogout() {
        int confirmationResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Log out confirmation", JOptionPane.YES_NO_OPTION);

        if (confirmationResult == JOptionPane.YES_OPTION) {
            // close the current window
            this.dispose();

            // shows the AuthenticatorGUI
            AuthenticatorGUI authenticatorGUI = new AuthenticatorGUI();
            authenticatorGUI.setVisible(true);
            authenticatorGUI.pack();
            authenticatorGUI.setLocationRelativeTo(null);
        }
    }
    
    private void loadTrackComplaintsData() {
        // declares the complaints variable outside of the try-catch block and initialize it with an empty list
        List<Complaint> complaints = new ArrayList<>();

        try {
            ComplaintCRUD complaintCRUD = new ComplaintCRUDImplement(); // creates an instance of ComplaintCRUDImplement
            complaints = complaintCRUD.getAllStudentComplaints(studentId); // calls the method on the instance
        } catch (Exception e) {
            e.printStackTrace();
        } // fetch the complaints data

        ComplaintsTableModel complaintsTableModel = new ComplaintsTableModel(complaints); // create a table model with the fetched data
        complaintsTable.setModel(complaintsTableModel); // set the table model for the complaints table
    }

    
    private void updateComplaintsTable(String userId, String category) {
        ComplaintCRUD complaintCRUD = new ComplaintCRUDImplement();
        List<Complaint> complaints = complaintCRUD.trackComplaints(userId, category);
        ComplaintsTableModel tableModel = new ComplaintsTableModel(complaints);
        complaintsTable.setModel(tableModel);
    }
    
    private void applyComplaintsFilter(String filterString, String filterType) {
        TableRowSorter<TableModel> sorter = (TableRowSorter<TableModel>) complaintsTable.getRowSorter();
        RowFilter<TableModel, Object> rowFilter = null;

        if (filterString != null && !filterString.isEmpty()) {
            switch (filterType) {
                case "id":
                    rowFilter = RowFilter.regexFilter(filterString, 0); // 0 is the column index for "id"
                    break;
                case "category":
                    rowFilter = RowFilter.regexFilter(filterString, 1); // 1 is the column index for "category"
                    break;
                case "status":
                    rowFilter = RowFilter.regexFilter(filterString, 2); // 2 is the column index for "status"
                    break;
                case "advisor":
                    rowFilter = RowFilter.regexFilter(filterString, 3); // 3 is the column index for "advisor"
                    break;
            }
        }

        if (sorter != null) {
            sorter.setRowFilter(rowFilter);
        }
    }

    private void handleNewComplaint() {
        
    }

    private void handleLiveChat() {
        
    }

    /*
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                StudentDashboardGUI studentDashboardGUI = new StudentDashboardGUI();
                studentDashboardGUI.setVisible(true);
            }
        });
    }*/
}