package client;

import javax.swing.*;
import java.awt.*;

public class DashboardUI extends JFrame {

    private String identifier;
    private String userType;

    public DashboardUI(String identifier, String userType) {
        this.identifier = identifier;
        this.userType = userType;
        initialize();
    }

    private void initialize() {
        setTitle("Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        getContentPane().setLayout(new BorderLayout());

        if ("Student".equals(userType)) {
            StudentDashboard();
        } else {
            StaffDashboard();
        }
    }

    private void StudentDashboard() {
        // Create components for the Student dashboard
        JPanel serviceSelectionPanel = createServiceSelectionPanel();
        getContentPane().add(serviceSelectionPanel, BorderLayout.NORTH);

        JTabbedPane tabbedPane = new JTabbedPane();
        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        JPanel pastComplaintsQueriesPanel = createPastComplaintsQueriesPanel();
        tabbedPane.addTab("Past Complaints & Queries", pastComplaintsQueriesPanel);

        JPanel viewSpecificComplaintQueryPanel = createViewSpecificComplaintQueryPanel();
        tabbedPane.addTab("View Specific Complaint or Query", viewSpecificComplaintQueryPanel);
    }

    private JPanel createServiceSelectionPanel() {
        // Create and configure the service selection panel
        JPanel serviceSelectionPanel = new JPanel();

        JLabel serviceLabel = new JLabel("Select service:");
        serviceSelectionPanel.add(serviceLabel);

        JComboBox<String> serviceComboBox = new JComboBox<>(new String[]{"Complaint", "Query"});
        serviceSelectionPanel.add(serviceComboBox);

        return serviceSelectionPanel;
    }

    private JPanel createPastComplaintsQueriesPanel() {
        // Create and configure the past complaints and queries panel
        JPanel pastComplaintsQueriesPanel = new JPanel(new BorderLayout());

        JList<String> pastComplaintsQueriesList = new JList<>(new String[]{"Complaint 1", "Query 1", "Complaint 2"});
        pastComplaintsQueriesPanel.add(new JScrollPane(pastComplaintsQueriesList), BorderLayout.CENTER);

        return pastComplaintsQueriesPanel;
    }

    private JPanel createViewSpecificComplaintQueryPanel() {
        // Create and configure the view specific complaint or query panel
        JPanel viewSpecificComplaintQueryPanel = new JPanel(new BorderLayout());

        JTextArea complaintQueryDetailsTextArea = new JTextArea("Complaint/query details...");
        viewSpecificComplaintQueryPanel.add(new JScrollPane(complaintQueryDetailsTextArea), BorderLayout.CENTER);

        return viewSpecificComplaintQueryPanel;
    }

    private void StaffDashboard() {
        // Create components for the Staff dashboard
        if ("Student Services Advisor".equals(userType)) {
            displayAdvisorDashboard();
        } else if ("Student Services Supervisor".equals(userType)) {
            displaySupervisorDashboard();
        }
    }

    private void displayAdvisorDashboard() {
        JTabbedPane tabbedPane = new JTabbedPane();
        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        JPanel assignedIssuesListPanel = createAssignedIssuesListPanel();
        tabbedPane.addTab("Assigned Issues", assignedIssuesListPanel);

        JPanel issueDetailsPanel = createIssueDetailsPanel();
        tabbedPane.addTab("Issue Details", issueDetailsPanel);

        JPanel responsePanel = createResponsePanel();
        tabbedPane.addTab("Respond", responsePanel);
    }

    private JPanel createAssignedIssuesListPanel() {
        // Create and configure the assigned issues list panel
        JPanel assignedIssuesListPanel = new JPanel(new BorderLayout());

        JList<String> assignedIssuesList = new JList<>(new String[]{"Issue 1", "Issue 2", "Issue 3"});
        assignedIssuesListPanel.add(new JScrollPane(assignedIssuesList), BorderLayout.CENTER);

        return assignedIssuesListPanel;
    }

    private JPanel createIssueDetailsPanel() {
        // Create and configure the issue details panel
        JPanel issueDetailsPanel = new JPanel(new BorderLayout());

        JTextArea issueDetailsTextArea = new JTextArea("Issue details...");
        issueDetailsPanel.add(new JScrollPane(issueDetailsTextArea), BorderLayout.CENTER);

        return issueDetailsPanel;
    }

    private JPanel createResponsePanel() {
        // Create and configure the response panel
        JPanel responsePanel = new JPanel(new BorderLayout());

        JTextArea responseTextArea = new JTextArea("Type your response...");
        responsePanel.add(new JScrollPane(responseTextArea), BorderLayout.CENTER);

        JButton sendResponseButton = new JButton("Send Response");
        responsePanel.add(sendResponseButton, BorderLayout.SOUTH);

        return responsePanel;
    }

    private void displaySupervisorDashboard() {
        JTabbedPane tabbedPane = new JTabbedPane();
        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        JPanel serviceStatsPanel = createServiceStatsPanel();
        tabbedPane.addTab("Service Stats", serviceStatsPanel);

        JPanel assignIssuePanel = createAssignIssuePanel();
        tabbedPane.addTab("Assign Issue", assignIssuePanel);

        JPanel issuesByCategoryPanel = createIssuesByCategoryPanel();
        tabbedPane.addTab("Issues by Category", issuesByCategoryPanel);

        JPanel viewIssueDetailsPanel = createIssueDetailsPanel();
        tabbedPane.addTab("View Issue Details", viewIssueDetailsPanel);
    }

    private JPanel createServiceStatsPanel() {
        // Create and configure the service stats panel
        JPanel serviceStatsPanel = new JPanel();
        serviceStatsPanel.add(new JLabel("Service stats..."));

        return serviceStatsPanel;
    }

    private JPanel createAssignIssuePanel() {
        // Create and configure the assign issue panel
        JPanel assignIssuePanel = new JPanel();
        assignIssuePanel.add(new JLabel("Assign issue..."));

        return assignIssuePanel;
    }

    private JPanel createIssuesByCategoryPanel() {
        // Create and configure the issues by category panel
        JPanel issuesByCategoryPanel = new JPanel(new BorderLayout());

        JList<String> issuesByCategoryList = new JList<>(new String[]{"Issue 1", "Issue 2", "Issue 3"});
        issuesByCategoryPanel.add(new JScrollPane(issuesByCategoryList), BorderLayout.CENTER);

        return issuesByCategoryPanel;
    }
}
