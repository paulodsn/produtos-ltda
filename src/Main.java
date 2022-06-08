
import views.Login;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new Login();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });
    }
}
