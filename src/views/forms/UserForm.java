package views.forms;

import controller.UserController;
import database.model.User;
import helpers.LayoutConstraints;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UserForm extends JFrame {
    private JPanel panel;
    private LayoutConstraints layoutConstraints;
    private UserController userController;

    private JTextField nameInput;
    private JTextField emailInput;
    private JPasswordField passwordInput;
    private JComboBox roleComboBox;

    public UserForm() {
        this.layoutConstraints = new LayoutConstraints();
        this.userController = new UserController();

        this.init();
    }

    private void init() {
        this.setTitle("Produtos | Formulário de Usuário");
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

        // Type group
        layoutConstraints.addLine();
        JLabel roleLabel = new JLabel("Cargo");
        panel.add(roleLabel, constraints);
        layoutConstraints.addLine();

        String[] choices = {"Atendente", "Administrador"};
        this.roleComboBox = new JComboBox<String>(choices);
        panel.add(roleComboBox, constraints);
        layoutConstraints.addLine();

        // Email group
        layoutConstraints.addLine();
        JLabel emailLabel = new JLabel("Email");
        panel.add(emailLabel, constraints);
        layoutConstraints.addLine();

        this.emailInput = new JTextField(30);
        panel.add(emailInput, constraints);
        layoutConstraints.addLine();

        // Password group
        JLabel passwordLabel = new JLabel("Senha");
        panel.add(passwordLabel, constraints);
        layoutConstraints.addLine();

        this.passwordInput = new JPasswordField(30);
        panel.add(passwordInput, constraints);
        layoutConstraints.addLine();

        JButton loginButton = new JButton("Salvar");
        loginButton.addActionListener(e -> this.actionSaveUser(e));
        panel.add(loginButton, constraints);

        return panel;
    }

    private void actionSaveUser(ActionEvent e) {
        String name = this.nameInput.getText();
        String email = this.emailInput.getText();
        String password = String.valueOf(this.passwordInput.getPassword());
        String role = this.roleComboBox.getSelectedItem().toString();
        Integer roleId = role == "Administrador" ? 1 : 0;

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(roleId);

        this.userController.create(user);
        this.dispose();
    }
}
