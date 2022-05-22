package views;

import helpers.LayoutConstraints;
import views.panels.Customers;
import views.panels.Products;
import views.panels.Users;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Logged extends JFrame {
    private LayoutConstraints layoutConstraints;

    private String USERS_ITEM = "users";
    private String CUSTOMERS_ITEM = "customers";
    private String PRODUCTS_ITEM = "products";

    public Logged() {
        this.layoutConstraints = new LayoutConstraints();
        this.init();
    }

    private void init() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 500);

        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        this.setResizable(false);

        this.changeContent(USERS_ITEM);
    }

    public void changeContent(String menuItem) {
        GridBagConstraints constraints = layoutConstraints.constraints();
        layoutConstraints.addLine();

        this.getContentPane().removeAll();

        if (menuItem == USERS_ITEM) {
            this.setTitle("Produtos | Usuários");
            Users usersPanel =  new Users();
            layoutConstraints.allSpace();
            this.add(usersPanel, constraints);
        }

        if (menuItem == CUSTOMERS_ITEM) {
            this.setTitle("Produtos | Clientes");
            Customers customersPanel =  new Customers();
            layoutConstraints.allSpace();
            this.add(customersPanel, constraints);
        }

        if (menuItem == PRODUCTS_ITEM) {
            this.setTitle("Produtos | Produtos");
            Products productsPanel =  new Products();
            layoutConstraints.allSpace();
            this.add(productsPanel, constraints);
        }

        layoutConstraints.addLine();
        JPanel buttons = this.getMenu();
        layoutConstraints.dynamicSpace();
        this.add(buttons, constraints);

        this.repaint();
        this.revalidate();
    }

    public JPanel getMenu() {
        GridLayout layout = new GridLayout(1, 0, 10, 15);
        JPanel panel = new JPanel(layout);

        JButton usersBtn = new JButton("Usuários");
        usersBtn.addActionListener(e -> this.goToUsers(e));
        panel.add(usersBtn);

        JButton customersBtn = new JButton("Clientes");
        customersBtn.addActionListener(e -> this.goToCustomers(e));
        panel.add(customersBtn);

        JButton productsBtn = new JButton("Produtos");
        productsBtn.addActionListener(e -> this.goToProducts(e));
        panel.add(productsBtn);

        JButton logoutBtn = new JButton("Sair");
        logoutBtn.addActionListener(e -> this.logout());
        panel.add(logoutBtn);

        return panel;
    }

    public void goToUsers(ActionEvent e) {
        this.changeContent(USERS_ITEM);
    }

    public void goToCustomers(ActionEvent e) {
        this.changeContent(CUSTOMERS_ITEM);
    }

    public void goToProducts(ActionEvent e) {
        this.changeContent(PRODUCTS_ITEM);
    }

    public void logout() {
        Login login = new Login();
        login.setVisible(true);

        this.dispose();
    }
}
