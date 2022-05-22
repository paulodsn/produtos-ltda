package views.panels;

import controller.ProductController;
import database.model.Product;
import helpers.CustomTableModel;
import helpers.LayoutConstraints;
import views.forms.ProductForm;
import views.forms.UserForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class Products extends JPanel {
    private List<Product> products;
    private CustomTableModel tableModel = new CustomTableModel();

    private LayoutConstraints layoutConstraints;
    private ProductController productController;

    public Products() {
        this.layoutConstraints = new LayoutConstraints();
        this.productController = new ProductController();

        this.init();
    }

    private void init() {
        GridBagConstraints constraints = layoutConstraints.constraints();
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);

        layoutConstraints.addLine();
        JButton addButton = new JButton("Adicionar produto");
        addButton.addActionListener(e -> this.goToProductForm(e));
        this.add(addButton, constraints);

        layoutConstraints.addColumn();
        JButton refreshButton = new JButton("Atualizar a lista");
        refreshButton.addActionListener(e -> this.findProducts());
        this.add(refreshButton, constraints);
        layoutConstraints.addLine();

        JTable table = this.createTable();
        layoutConstraints.allSpace();
        constraints.gridwidth = 2;
        this.add(table, constraints);
        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane, constraints);
    }

    private JTable createTable() {
        JTable table = new JTable(tableModel);
        tableModel.addColumn("Nome");
        tableModel.addColumn("Preço");
        tableModel.addColumn("Estoque");
        table.getColumnModel().getColumn(0)
                .setPreferredWidth(60);
        table.getColumnModel().getColumn(1)
                .setPreferredWidth(40);
        table.getColumnModel().getColumn(1)
                .setPreferredWidth(40);
        this.findProducts();

        return table;
    }

    private void findProducts() {
        this.products = this.productController.list();
        tableModel.setRowCount(0);

        for(Product p: this.products) {
            tableModel.addRow(new Object[]{ p.getName(), p.getPrice(), p.getStock() });
        }
    }

    private void goToProductForm(ActionEvent e) {
        ProductForm productForm = new ProductForm();
        productForm.setVisible(true);
    }
}
