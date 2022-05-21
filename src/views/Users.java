package views;

import helpers.LayoutConstraints;

import javax.swing.*;
import java.awt.*;

public class Users extends JFrame {
    LayoutConstraints layoutConstraints;

    private JPanel panel;

    public Users() {
        this.layoutConstraints = new LayoutConstraints();

        this.init();
    }

    private void init() {
        this.setTitle("Produtos | Login");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 500);
        this.setResizable(false);

        JPanel form = getList();
        this.getContentPane().add(form);
    }

    public JPanel getList() {
        GridBagConstraints constraints = layoutConstraints.constraints();

        GridBagLayout layout = new GridBagLayout();
        panel = new JPanel(layout);

        layoutConstraints.addLine();
        JLabel titleLabel = new JLabel("Users");
        panel.add(titleLabel, constraints);
        layoutConstraints.addLine();

        return panel;
    }
}
