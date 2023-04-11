package client;

import javax.swing.*;
import java.awt.*;

public class AdvisorDashboardGUI extends JFrame {

    public AdvisorDashboardGUI() {
    	setTitle("Student Service Advisor Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createMenuBar();
        setLayout(new BorderLayout());

        setVisible(true);
    }

    private void createMenuBar() {
        JPanel menuBar = new JPanel();
        menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.Y_AXIS));

        JButton homeButton = new JButton("Home");
        menuBar.add(homeButton);

        JButton liveChatButton = new JButton("LiveChat");
        menuBar.add(liveChatButton);

        JButton settingsButton = new JButton("Settings");
        menuBar.add(settingsButton);

        JButton logoutButton = new JButton("Logout");
        menuBar.add(logoutButton);

        getContentPane().add(menuBar, BorderLayout.WEST);
    }
	
}
