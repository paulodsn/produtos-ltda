package views.panels;

import controller.ProductController;
import database.model.Product;
import helpers.CustomTableModel;
import helpers.LayoutConstraints;
import views.forms.ProductForm;
import views.reports.OrderReport;
import views.reports.ProfitReport;

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

        layoutConstraints.addColumn();
        JButton orderReportButton = new JButton("Relatório de vendas");
        orderReportButton.addActionListener(e -> this.goToOrderReport(e));
        this.add(orderReportButton, constraints);

        layoutConstraints.addColumn();
        JButton profitReportButton = new JButton("Relatório de Lucro");
        profitReportButton.addActionListener(e -> this.goToProfitReport(e));
        this.add(profitReportButton, constraints);
        layoutConstraints.addLine();

        JTable table = this.createTable();
        layoutConstraints.allSpace();
        constraints.gridwidth = 4;
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
        table.getColumnModel().getColumn(2)
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

    private void goToOrderReport(ActionEvent e) {
        OrderReport orderReport = new OrderReport();
        orderReport.setVisible(true);
    }

    private void goToProfitReport(ActionEvent e) {
        ProfitReport profitReport = new ProfitReport();
        profitReport.setVisible(true);
    }
}
