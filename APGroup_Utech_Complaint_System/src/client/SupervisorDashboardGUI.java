package client;

import javax.swing.*;
import java.awt.*;

public class SupervisorDashboardGUI extends JFrame {

    public SupervisorDashboardGUI() {
        setTitle("Supervisor Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createMenuBar();
        setLayout(new BorderLayout());
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SupervisorDashboardGUI supervisorDashboardGUI = new SupervisorDashboardGUI();
            supervisorDashboardGUI.setVisible(true);
        });
    }
}