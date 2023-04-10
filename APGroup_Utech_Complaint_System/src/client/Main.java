package client;

import java.awt.EventQueue;

public class Main {

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

}
