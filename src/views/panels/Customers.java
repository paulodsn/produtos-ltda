package views.panels;

import controller.CustomerController;
import database.model.Customer;
import helpers.CustomTableModel;
import helpers.LayoutConstraints;
import views.forms.CustomerForm;
import views.forms.ProductForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class Customers extends JPanel {
    private List<Customer> customers;
    private CustomTableModel tableModel = new CustomTableModel();

    private LayoutConstraints layoutConstraints;
    private CustomerController customerController;

    public Customers() {
        this.layoutConstraints = new LayoutConstraints();
        this.customerController = new CustomerController();

        this.init();
    }

    private void init() {
        GridBagConstraints constraints = layoutConstraints.constraints();
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);

        layoutConstraints.addLine();
        JButton addButton = new JButton("Adicionar cliente");
        addButton.addActionListener(e -> this.goToCustomerForm(e));
        this.add(addButton, constraints);

        layoutConstraints.addColumn();
        JButton refreshButton = new JButton("Atualizar a lista");
        refreshButton.addActionListener(e -> this.findCustomers());
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
        tableModel.addColumn("Email");
        table.getColumnModel().getColumn(0)
                .setPreferredWidth(60);
        table.getColumnModel().getColumn(1)
                .setPreferredWidth(120);
        this.findCustomers();

        return table;
    }

    private void findCustomers() {
        this.customers = this.customerController.list();
        tableModel.setRowCount(0);

        for(Customer c: this.customers) {
            tableModel.addRow(new Object[]{ c.getName(), c.getEmail() });
        }
    }

    private void goToCustomerForm(ActionEvent e) {
        CustomerForm customerForm = new CustomerForm();
        customerForm.setVisible(true);
    }
}
