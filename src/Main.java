import views.Logged;
import views.Login;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // TODO: Change screen to login
            Logged login = new Logged();
            login.setVisible(true);
        });
    }
}
