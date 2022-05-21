package views;

import helpers.LayoutConstraints;
import controller.Auth;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Login extends JFrame {
    LayoutConstraints layoutConstraints;
    Auth authService;

    private JPanel panel;
    private JTextField emailInput;
    private JPasswordField passwordInput;


    public Login() {
        this.authService = new Auth();
        this.layoutConstraints = new LayoutConstraints();

        this.init();
    }

    private void init() {
        this.setTitle("Produtos | Login");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 500);
        this.setResizable(false);

        JPanel form = getLoginForm();
        this.getContentPane().add(form);
    }

    public JPanel getLoginForm() {
        GridBagConstraints constraints = layoutConstraints.constraints();

        GridBagLayout layout = new GridBagLayout();
        panel = new JPanel(layout);

        layoutConstraints.addLine();
        JLabel titleLabel = new JLabel("Login");
        panel.add(titleLabel, constraints);
        layoutConstraints.addLine();

        JLabel emailLabel = new JLabel("Email");
        panel.add(emailLabel, constraints);
        layoutConstraints.addColumn();

        emailInput = new JTextField(30);
        panel.add(emailInput, constraints);
        layoutConstraints.addLine();

        JLabel passwordLabel = new JLabel("Senha");
        panel.add(passwordLabel, constraints);
        layoutConstraints.addColumn();

        passwordInput = new JPasswordField(30);
        panel.add(passwordInput, constraints);
        layoutConstraints.addLine();

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> this.actionLogin(e));
        panel.add(loginButton, constraints);

        return panel;
    }

    public void actionLogin(ActionEvent e) {
        String email = this.emailInput.getText();
        String password = String.valueOf(this.passwordInput.getPassword());

        boolean IsValidUser = this.authService.login(email, password);

        if (IsValidUser) {
            Users users = new Users();
            users.setVisible(true);

            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this.panel, "Email ou senha inválido!");
        }
    }
}
