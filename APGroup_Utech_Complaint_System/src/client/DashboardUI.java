package client;

import javax.swing.JFrame;

public class DashboardUI extends JFrame {

	 private String identifier;
	    private String userType;

	    public DashboardUI(String identifier, String userType) {
	        this.identifier = identifier;
	        this.userType = userType;
	        // initializes the dashboard
	        initialize();
	    }

	    private void initialize() {
	        // sets up the main JFrame
	        setTitle("Dashboard");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setBounds(100, 100, 800, 600);

	        // adds components to the dashboard based on the userType
	        if ("Student".equals(userType)) {
	            displayStudentDashboard();
	        } else {
	            displayStaffDashboard();
	        }
	    }

	    private void displayStudentDashboard() {
	        // adds components specific to the Student dashboard
	    }

	    private void displayStaffDashboard() {
	        // adds components specific to the Staff dashboard
	    }
}
