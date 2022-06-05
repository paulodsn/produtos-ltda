package views.forms;

import controller.CustomerController;
import database.model.Customer;
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
    private JTextField phoneInput;
    private JTextField addressInput;
    private JTextField cpfInput;

    public CustomerForm() {
        this.layoutConstraints = new LayoutConstraints();
        this.customerController = new CustomerController();

        this.init();
    }

    private void init() {
        this.setTitle("Produtos | Formulário de Clientes");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 700);
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

        // CPF group
        layoutConstraints.addLine();
        JLabel cpfLabel = new JLabel("CPF");
        panel.add(cpfLabel, constraints);
        layoutConstraints.addLine();

        this.cpfInput = new JTextField(30);
        panel.add(cpfInput, constraints);
        layoutConstraints.addLine();

        // Address group
        layoutConstraints.addLine();
        JLabel adressLabel = new JLabel("Endereço");
        panel.add(adressLabel, constraints);
        layoutConstraints.addLine();

        this.addressInput = new JTextField(30);
        panel.add(addressInput,constraints);
        layoutConstraints.addLine();

        // Phone group
        layoutConstraints.addLine();
        JLabel phoneLabel = new JLabel("Telefone");
        panel.add(phoneLabel, constraints);
        layoutConstraints.addLine();

        this.phoneInput = new JTextField(30);
        panel.add(phoneInput, constraints);
        layoutConstraints.addLine();

        JButton loginButton = new JButton("Salvar");
        loginButton.addActionListener(e -> this.actionSaveCustomer(e));
        panel.add(loginButton, constraints);

        return panel;
    }

    private void actionSaveCustomer(ActionEvent e) {
        String name = this.nameInput.getText();
        String email = this.emailInput.getText();
        String phone = this.phoneInput.getText();
        String adress = this.addressInput.getText();
        String cpf = this.cpfInput.getText();

        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setAdress(adress);
        customer.setCpf(cpf);

        this.customerController.create(customer);
        this.dispose();
    }
}
