package views.forms;

import controller.CustomerController;
import database.model.Customer;
import database.model.User;
import helpers.LayoutConstraints;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CustomerForm extends JFrame {
    private JPanel panel;
    private LayoutConstraints layoutConstraints;
    private CustomerController customerController;

    private JTextField nameInput;
    private JTextField emailInput;

    public CustomerForm() {
        this.layoutConstraints = new LayoutConstraints();
        this.customerController = new CustomerController();

        this.init();
    }

    private void init() {
        this.setTitle("Produtos | Formulário de Clientes");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 400);
        this.setResizable(false);

        JPanel form = getUserForm();
        this.getContentPane().add(form);
    }

    public JPanel getUserForm() {
        GridBagConstraints constraints = layoutConstraints.constraints();

        GridBagLayout layout = new GridBagLayout();
        panel = new JPanel(layout);

        // Name group
        layoutConstraints.addLine();
        JLabel titleLabel = new JLabel("Nome");
        panel.add(titleLabel, constraints);
        layoutConstraints.addLine();

        this.nameInput = new JTextField(30);
        panel.add(nameInput, constraints);
        layoutConstraints.addLine();

        // Email group
        layoutConstraints.addLine();
        JLabel emailLabel = new JLabel("Email");
        panel.add(emailLabel, constraints);
        layoutConstraints.addLine();

        this.emailInput = new JTextField(30);
        panel.add(emailInput, constraints);
        layoutConstraints.addLine();


        JButton loginButton = new JButton("Salvar");
        loginButton.addActionListener(e -> this.actionSaveCustomer(e));
        panel.add(loginButton, constraints);

        return panel;
    }

    private void actionSaveCustomer(ActionEvent e) {
        String name = this.nameInput.getText();
        String email = this.emailInput.getText();

        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);

        this.customerController.create(customer);
        this.dispose();
    }
}
