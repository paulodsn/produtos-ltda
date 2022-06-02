package views.reports;

import controller.OrderController;
import helpers.CustomTableModel;
import helpers.LayoutConstraints;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class OrderReport extends JFrame {
    private List<database.model.OrderReport> orderReportList;
    private CustomTableModel tableModel;
    private OrderController orderController;
    private LayoutConstraints layoutConstraints;

    public OrderReport() {
        this.tableModel = new CustomTableModel();
        this.layoutConstraints = new LayoutConstraints();
        this.orderController = new OrderController();

        this.init();
    }

    private void init() {
        this.setTitle("Produtos | Relatório de Vendas");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 400);
        this.setResizable(false);

        GridBagConstraints constraints = layoutConstraints.constraints();
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);

        JTable table = getTableReport();
        layoutConstraints.allSpace();
        this.getContentPane().add(table);

        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane, constraints);
    }

    public JTable getTableReport() {
        JTable table = new JTable(tableModel);
        tableModel.addColumn("Cliente");
        tableModel.addColumn("Endereço");
        tableModel.addColumn("Número do pedido");
        tableModel.addColumn("Produto");
        tableModel.addColumn("Preço");
        tableModel.addColumn("Quantidade");

        table.getColumnModel().getColumn(0)
                .setPreferredWidth(120);
        table.getColumnModel().getColumn(1)
                .setPreferredWidth(120);
        table.getColumnModel().getColumn(2)
                .setPreferredWidth(40);
        table.getColumnModel().getColumn(3)
                .setPreferredWidth(100);
        table.getColumnModel().getColumn(4)
                .setPreferredWidth(40);
        table.getColumnModel().getColumn(5)
                .setPreferredWidth(40);

        this.fetchReportData();
        return table;
    }

    private void fetchReportData() {
        this.orderReportList = this.orderController.getOrderByCustomer();
        tableModel.setRowCount(0);

        for(database.model.OrderReport o: this.orderReportList) {
            tableModel.addRow(new Object[]{
                    o.getCustomerName(),
                    o.getCustomerAddress(),
                    o.getOrderId(),
                    o.getProductName(),
                    o.getPrice(),
                    o.getQuantity(),
            });
        }
    }
}
