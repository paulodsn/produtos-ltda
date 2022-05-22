package views.panels;

import helpers.LayoutConstraints;

import javax.swing.*;
import java.awt.*;

public class Customers extends JPanel {
    private LayoutConstraints layoutConstraints;

    public Customers() {
        this.layoutConstraints = new LayoutConstraints();
        this.init();
    }

    private void init() {
        GridBagConstraints constraints = layoutConstraints.constraints();
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);

        this.setBackground(Color.gray);

        layoutConstraints.addLine();
        JLabel titleLabel = new JLabel("Customers");
        this.add(titleLabel, constraints);
        layoutConstraints.addLine();
    }
}
