package views.forms;

import controller.ProductController;
import database.model.Product;
import helpers.LayoutConstraints;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ProductForm extends JFrame {
    private JPanel panel;
    private LayoutConstraints layoutConstraints;
    private ProductController productController;

    private JTextField nameInput;
    private JTextField priceInput;
    private JTextField stockInput;

    public ProductForm() {
        this.layoutConstraints = new LayoutConstraints();
        this.productController = new ProductController();

        this.init();
    }

    private void init() {
        this.setTitle("Produtos | Formulário de produto");
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

        // Price group
        layoutConstraints.addLine();
        JLabel priceLabel = new JLabel("Preço");
        panel.add(priceLabel, constraints);
        layoutConstraints.addLine();

        this.priceInput = new JTextField(30);
        panel.add(priceInput, constraints);
        layoutConstraints.addLine();

        // Stock group
        layoutConstraints.addLine();
        JLabel stockLabel = new JLabel("Quantidade em estoque");
        panel.add(stockLabel, constraints);
        layoutConstraints.addLine();

        this.stockInput = new JTextField(30);
        panel.add(stockInput, constraints);
        layoutConstraints.addLine();

        JButton loginButton = new JButton("Salvar");
        loginButton.addActionListener(e -> this.actionSaveUser(e));
        panel.add(loginButton, constraints);

        return panel;
    }

    private void actionSaveUser(ActionEvent e) {
        String name = this.nameInput.getText();
        Double price = Double.parseDouble(this.priceInput.getText());
        Integer stock = Integer.parseInt(this.stockInput.getText());

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);

        this.productController.create(product);
        this.dispose();
    }
}
